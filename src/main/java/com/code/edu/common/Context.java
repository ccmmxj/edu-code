package com.code.edu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Context {
    Logger logger = LoggerFactory.getLogger(Context.class);
    static String uploadAddr(){
        String os = System.getProperty("os.name");
        logger.info("system ============> {}",os);
        if(os.toLowerCase().startsWith("win")){
            return "D:/javaSoft/nginx-1.12.2/nginx-1.12.2/html/upload/";
        }else{
            return "/urs/local/nginx/html/upload/";
        }
    }
//    Long DEFAULT_COMPANY_ID = 1L;
    String FILE_HOST = "http://220.190.182.158/upload/";
}
