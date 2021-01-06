package com.kingfar.blog.service;

import com.kingfar.blog.entity.MessageData;
import com.kingfar.blog.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public void sendMessage(MessageData messageData) {
        messageMapper.insertMessage(messageData.getFromUser(), messageData.getContent(), messageData.getToUser());
    }

    @Override
    public List<MessageData> disMessage(String fromUser, String toUser) {
        return messageMapper.queryMessageByToAndFrom(fromUser, toUser);
    }

    @Override
    public List<String> notify(String receiver) {
        return messageMapper.getSenders(receiver);
    }

    @Override
    public void changeState(String sender, String receiver) {
        messageMapper.changeIsReadState(sender, receiver);
    }
}
