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

import java.util.ArrayList;
import java.util.List;

/**
 * @author WUHAIYUAN
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/display")
    Response display(@RequestBody JSONObject jsonObject){
        List<MessageData> messageDataList;
        try {
            String fromUser = jsonObject.getString("fromUser");
            String toUser = jsonObject.getString("toUser");

            messageDataList = messageService.disMessage(fromUser, toUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse(4,"failed to display message!",null);
        }
        return new MessageResponse(0,"success!",messageDataList);
    }

    @PostMapping("/send")
    Response send(@RequestBody MessageData messageData){

        try {
            messageService.sendMessage(messageData);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse(3,"failed to send message!",null);
        }
        return new MessageResponse(0,"success!",null);
    }
}
