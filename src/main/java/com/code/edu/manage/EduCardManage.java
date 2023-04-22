package com.code.edu.manage;

import com.code.edu.common.Context;
import com.code.edu.model.EduCard;
import com.code.edu.service.EduCardService;
import com.code.edu.service.impl.EduCardServiceImpl;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EduCardManage {
    private final static Logger logger = LoggerFactory.getLogger(EduCardServiceImpl.class);

    @Autowired
    private EduCardService eduCardService;

    public void clearResource() {
        List<EduCard> cardList = eduCardService.findAll();
        Set<String> resourceSet = cardList.stream()
                .map(eduCard -> Lists.newArrayList(eduCard.getImgUrl(), eduCard.getAudioUrl()))
                .flatMap(List::parallelStream)
                .filter(StringUtils::isNotBlank)
                .map(path -> path.replace(Context.FILE_HOST, ""))
                .collect(Collectors.toSet());
        try {
            String uploadDir = Context.uploadAddr();
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
                        Files.delete(file);
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
        } catch (IOException e) {
            logger.error("clearResource fail.", e);
        }
    }

    public static void main(String[] args) {
        try {
            String uploadDir = Context.uploadAddr();
            Files.walkFileTree(Paths.get(uploadDir), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    logger.warn("preVisitDirectory:{}", dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String filePath = file.toString().replaceAll("\\\\", "/").replace(uploadDir, "");
                    logger.warn("visitFile:{}", filePath);
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
        } catch (IOException e) {
            logger.error("clearResource fail.", e);
        }
    }
}
