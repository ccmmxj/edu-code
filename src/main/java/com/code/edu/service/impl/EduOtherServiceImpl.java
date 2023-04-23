package com.code.edu.service.impl;

import com.code.edu.common.Context;
import com.code.edu.dto.TableData;
import com.code.edu.model.EduCard;
import com.code.edu.model.Resource;
import com.code.edu.service.EduCardService;
import com.code.edu.service.EduOtherService;
import com.code.edu.utils.TableDataFactory;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class EduOtherServiceImpl implements EduOtherService {
    private final static Logger logger = LoggerFactory.getLogger(EduOtherServiceImpl.class);

    @Autowired
    private EduCardService eduCardService;

    @Override
    public int clearResource() {
        List<EduCard> cardList = eduCardService.findAll();
        Set<String> resourceSet = cardList.stream()
                .map(eduCard -> Lists.newArrayList(eduCard.getImgUrl(), eduCard.getAudioUrl()))
                .flatMap(List::parallelStream)
                .filter(StringUtils::isNotBlank)
                .map(path -> path.replace(Context.FILE_HOST, ""))
                .collect(Collectors.toSet());
        try {
            String uploadDir = Context.FILE_DIR;
            AtomicInteger count = new AtomicInteger(0);
            Files.walkFileTree(Paths.get(uploadDir), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    logger.warn("preVisitDirectory:{}", dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String filePath = file.toString().replaceAll("\\\\", "/").replace(uploadDir, "");
                    if (!resourceSet.contains(filePath)) {
                        logger.warn("del free resource:{}", file);
                        Path delPath = Paths.get(Context.DEL_DIR + filePath);
                        if (Files.notExists(delPath.getParent())) {
                            Files.createDirectories(delPath.getParent());
                        }
                        Files.move(file, delPath, StandardCopyOption.REPLACE_EXISTING);
                        count.getAndIncrement();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    logger.error(String.format("clear file:[%s] fail", file), exc);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    logger.warn("postVisitDirectory:{}", dir);
                    return FileVisitResult.CONTINUE;
                }
            });
            return count.get();
        } catch (IOException e) {
            logger.error("clearResource fail.", e);
        }
        return -1;
    }

    @Override
    public TableData<Resource> findDelResourceTable(TableData<Resource> tableData, String title) {
        String delDir = Context.DEL_DIR;
        try {
            AtomicReference<IOException> error = new AtomicReference<>();
            List<Resource> resourceList = Lists.newArrayList();
            Files.walkFileTree(Paths.get(delDir), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    logger.warn("preVisitDirectory:{}", dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String pt = file.toString().replaceAll("\\\\", "/");
                    String[] filePath = pt.replace(Context.fileDir(), "").split("/");
                    String name = filePath[filePath.length - 1];
                    if (StringUtils.isNotBlank(title) && !name.contains(title)) {
                        return FileVisitResult.CONTINUE;
                    }
                    Resource resource = new Resource();
                    resource.setName(name);
                    resource.setType(filePath[filePath.length - 2]);
                    resource.setUrl(pt.replace(Context.fileDir(), Context.HOST));
                    resourceList.add(resource);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    error.set(exc);
                    return FileVisitResult.TERMINATE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    logger.warn("postVisitDirectory:{}", dir);
                    return FileVisitResult.CONTINUE;
                }
            });
            if (Objects.nonNull(error.get())) {
                throw error.get();
            }
            tableData.setPageNumber(1);
            tableData.setPageSize(resourceList.size());
            return TableDataFactory.newInstaceSuccessResult(tableData, resourceList.size(), resourceList);
        } catch (IOException e) {
            logger.error("查询失败", e);
        }
        return TableDataFactory.newInstaceFailResult();
    }

    @Override
    public String restoreResource(String url) {
        if (StringUtils.isBlank(url)) {
            logger.warn("请求为空");
            return StringUtils.EMPTY;
        }
        String filePath = url.replace(Context.DEL_FILE_HOST, "");

        Path delPath = Paths.get(Context.DEL_DIR + filePath);
        if (Files.notExists(delPath)) {
            logger.warn("资源不存在");
            return StringUtils.EMPTY;
        }
        try {
            Path uploadPath = Paths.get(Context.FILE_DIR + filePath);
            if (Files.notExists(uploadPath.getParent())) {
                Files.createDirectories(uploadPath.getParent());
            }
            Files.move(delPath, uploadPath, StandardCopyOption.REPLACE_EXISTING);
            return Context.FILE_HOST + filePath;
        } catch (IOException e) {
            logger.error("恢复失败", e);
            return "error";
        }
    }
}
