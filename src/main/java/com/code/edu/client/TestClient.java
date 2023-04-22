package com.code.edu.client;

import com.code.edu.client.impl.TestClientImpl;
import com.code.edu.model.EduCard;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(name = "edu", fallback = TestClientImpl.class,configuration = FeignClientsConfiguration.class)
public interface TestClient {
    @GetMapping("manage/eduLearnAll")
    List<EduCard> eduLearnAll();
//    @GetMapping(value = "/eduLearnOne")
//    EduCard eduLearnOne(Long id);
}
