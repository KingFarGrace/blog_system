package com.kingfar.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoad() throws Exception {
        Map<String,String> map =new HashMap<>();
        map.put("pageIndex","1");
        String jsonString = JSONObject.toJSONString(map);
        MvcResult mvcResult = this.mockMvc.perform(post("/article/load")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDk5NDU1NzksInVzZXJuYW1lIjoiWkhBTkcifQ.HThTeb4mNGYDimDI5JaGTO26NsGDmTqCyCpntL_47I4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}