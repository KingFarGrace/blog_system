package com.kingfar.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginTest() throws Exception {
        Map<String,String> userMap =new HashMap<>();
        userMap.put("username","ZHANG");
        userMap.put("password","123456");
        String jsonString = JSONObject.toJSONString(userMap);
        MvcResult mvcResult = this.mockMvc.perform(post("/user/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void signUpTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/user/signUp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "Lee")
                .param("password", "123456"))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}