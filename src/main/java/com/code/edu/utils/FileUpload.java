package com.code.edu.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public final class FileUpload {
    private FileUpload(){
        super();
    }
    public static String uploadFile(MultipartFile file ,String path) throws IOException{
        String name = file.getOriginalFilename();
        String suffixName = name.substring(name.lastIndexOf("."));
        String fileName = name.substring(0,name.lastIndexOf("."));
        File tempFilePath = new File(path);
        if(!tempFilePath.exists()){
            tempFilePath.mkdirs();
        }
        File tempFile = new File(tempFilePath,name);
        if(tempFile.exists()){
            name = fileName + System.currentTimeMillis() + suffixName;
            tempFile = new File(tempFilePath,name);
        }
        //是否可执行
        if(!tempFile.canExecute()){
            tempFile.setExecutable(true,false);
        }
        //是否可写
        if(!tempFile.canWrite()){
            tempFile.setWritable(true,false);
        }
        //是否可读
        if(!tempFile.canRead()){
            tempFile.setReadable(true,false);
        }
        file.transferTo(tempFile);
        return name;
    }
}
