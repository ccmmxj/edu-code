package com.code.edu.controller;

import com.code.edu.utils.Result;
import com.code.edu.utils.ResultFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping("baidu")
public class BaiduTokenController {
    private static final String API_KEY = "QYNfDGgpIUSqRsHP7hlB256j";
    private static final String SECRET_KEY = "8ARfD3VF9iCvjWEduR2WAE8jfExBKwPC ";
    private static final String BAIDU_TOKEN_URL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials";


    @GetMapping("token")
    public Result<String> getToken() {
        BufferedReader reader = null;
        try {
            URL url = new URL(BAIDU_TOKEN_URL + "&client_id=" + API_KEY + "&client_secret=" + SECRET_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
             reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";
            while(StringUtils.isNotBlank(line = reader.readLine())){
                result += line;
            }
            return ResultFactory.newInstaceSuccessResult("成功",200L,result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader == null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultFactory.newInstaceSuccessResult("失败",500L,"");
    }
}
