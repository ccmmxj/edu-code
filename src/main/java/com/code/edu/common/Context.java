package com.code.edu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Context {
    Logger logger = LoggerFactory.getLogger(Context.class);
    static String fileDir(){
        String os = System.getProperty("os.name");
        logger.info("system ============> {}",os);
        if(os.toLowerCase().startsWith("win")){
            return "D:/javaSoft/nginx-1.12.2/nginx-1.12.2/html/";
        }else{
            return "/usr/local/nginx/html/";
        }
    }
//    Long DEFAULT_COMPANY_ID = 1L;
    String HOST = "http://edu.ccmmxj.club/";
    String UPLOAD_PATH = "upload/";
    String DEL_PATH = "del/";
    String FILE_HOST = HOST + UPLOAD_PATH;
    String DEL_FILE_HOST = HOST + DEL_PATH;
    String FILE_DIR = fileDir() + UPLOAD_PATH;
    String DEL_DIR = fileDir() + DEL_PATH;
}
