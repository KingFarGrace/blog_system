package com.kingfar.blog.service;

import com.kingfar.blog.entity.MessageData;

import java.util.List;

/**
 * @author WUHAIYUAN
 */
public interface MessageService {

    void sendMessage(MessageData messageData);

    List<MessageData> disMessage(String fromUser, String toUser);

    List<String> notify(String receiver);

    void changeState(String sender, String receiver);
}
