package com.kingfar.blog.controller;


import com.alibaba.fastjson.JSONObject;
import com.kingfar.blog.entity.MessageData;
import com.kingfar.blog.entity.response.MessageResponse;
import com.kingfar.blog.entity.response.Response;
import com.kingfar.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WUHAIYUAN
 * <p>
 * MessageResponse code
 * 500: success
 * 501: notify error -> failed to notify messages
 * 502: change state error -> failed to change is-read state
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/display")
    Response display(@RequestBody JSONObject jsonObject) {
        String fromUser = jsonObject.getString("fromUser");
        String toUser = jsonObject.getString("toUser");
        List<MessageData> messageDataList;
        try {
            messageDataList = messageService.disMessage(fromUser, toUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse(4, "failed to display messages", null);
        }
        Map<String, Object> respMap = new HashMap<>(1);
        respMap.put("messages", messageDataList);
        return new MessageResponse(0, "success", respMap);
    }

    @PostMapping("/send")
    Response send(@RequestBody MessageData messageData) {
        try {
            messageService.sendMessage(messageData);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse(3, "failed to send message", null);
        }
        return new MessageResponse(0, "success", null);
    }

    @PostMapping("/notify")
    Response notify(@RequestBody JSONObject jsonObject) {
        String receiver = jsonObject.getString("username");
        List<String> senders;
        try {
            senders = messageService.notify(receiver);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse(1, "failed to notify messages", null);
        }
        Map<String, Object> respMap = new HashMap<>(1);
        respMap.put("senders", senders);
        return new MessageResponse(0, "success", respMap);
    }

    @PostMapping("/changeState")
    Response changeState(@RequestBody JSONObject jsonObject) {
        String sender = jsonObject.getString("fromUser");
        String receiver = jsonObject.getString("toUser");
        try {
            messageService.changeState(sender, receiver);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse(2, "failed to change is-read state", null);
        }
        return new MessageResponse(0, "success", null);
    }
}
