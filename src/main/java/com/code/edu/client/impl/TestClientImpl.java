package com.code.edu.client.impl;

import com.code.edu.client.TestClient;
import com.code.edu.model.EduCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestClientImpl implements TestClient {
    @Override
    public List<EduCard> eduLearnAll() {
        return new ArrayList<>();
    }
}
