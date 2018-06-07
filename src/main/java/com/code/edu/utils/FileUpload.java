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
//        String suffixName = name.substring(name.lastIndexOf("."));
        File tempFilePath = new File(path);
        if(!tempFilePath.exists()){
            tempFilePath.mkdirs();
        }
        File tempFile = new File(tempFilePath,name);
        if(!tempFile.exists()){
            file.transferTo(tempFile);
        }
        return name;
    }
}
