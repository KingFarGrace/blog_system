package com.kingfar.blog.service;

import com.kingfar.blog.entity.MessageData;
import com.kingfar.blog.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageMapper messageMapper;

    @Override
    public void sendMessage(MessageData messageData) {
        messageMapper.insertMessage(messageData.getFromuser(),messageData.getContent(),messageData.getTouser());
    }

    @Override
    public List<MessageData> disMessage(String fromuser, String touser) {
        return messageMapper.queryMessageByToAndFrom(fromuser,touser);
    }
}
