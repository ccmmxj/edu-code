package com.code.edu.common;

public interface Context {
    static String uploadAddr(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return "D:/javaSoft/nginx-1.12.2/nginx-1.12.2/html/upload/";
        }else{
            return "/urs/local/nginx/html/upload/";
        }
    }
//    Long DEFAULT_COMPANY_ID = 1L;
    String FILE_HOST = "http://220.190.182.158/upload/";
}
