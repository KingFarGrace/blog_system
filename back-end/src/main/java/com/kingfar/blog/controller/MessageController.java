package com.kingfar.blog.controller;


import com.alibaba.fastjson.JSONObject;
import com.kingfar.blog.entity.MessageData;
import com.kingfar.blog.entity.response.MessageResponse;
import com.kingfar.blog.entity.response.Response;
import com.kingfar.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/display")
    Response display(@RequestBody JSONObject jsonObject){
        String fromuser = jsonObject.getString("fromuser");
        String touser = jsonObject.getString("touser");

        List<MessageData> messageDataList = messageService.disMessage(fromuser, touser);

        return new MessageResponse(0,"success",messageDataList);
    }

    @PostMapping("/send")
    void send(@RequestBody MessageData messageData){
        messageService.sendMessage(messageData);
    }

}
