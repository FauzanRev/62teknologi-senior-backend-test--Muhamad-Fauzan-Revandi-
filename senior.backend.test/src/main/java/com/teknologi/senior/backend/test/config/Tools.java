package com.teknologi.senior.backend.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class Tools {
    public <T> T convertStringToObject(String data, Class<T> type) {
        return new Gson().fromJson(data,type);
    }
}
