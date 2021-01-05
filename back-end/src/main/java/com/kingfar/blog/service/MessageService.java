package com.kingfar.blog.service;

import com.kingfar.blog.entity.MessageData;

import java.util.List;

public interface MessageService {

    void sendMessage(MessageData messageData);

    List<MessageData> disMessage(String fromuser,String touser);
}
