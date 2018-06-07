package com.code.edu.controller;

import com.code.edu.common.Context;
import com.code.edu.utils.FileUpload;
import com.code.edu.utils.Result;
import com.code.edu.utils.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("manage")
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("upload/{type}")
    @ResponseBody
    public Result upload(MultipartFile file, @PathVariable String type, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");

        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

        response.setHeader("Access-Control-Max-Age", "0");

        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");

        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("XDomainRequestAllowed","1");
        try {
            String result = FileUpload.uploadFile(file,Context.uploadAddr() + type);
            return ResultFactory.newInstaceSuccessResult("上传成功",200L,Context.FILE_HOST + type + "/" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultFactory.newInstaceFailResult("上传失败",200L,null);
    }
}
