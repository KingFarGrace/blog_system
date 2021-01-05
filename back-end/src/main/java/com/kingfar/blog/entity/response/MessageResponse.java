package com.kingfar.blog.entity.response;

import com.kingfar.blog.entity.MessageData;

import java.util.List;

/**
 * @author WUHAIYUAN
 */
public class MessageResponse extends  Response{

    private List<MessageData> messageDataList;

    @Override
    protected int groupCode() {
        return 5;
    }

    public MessageResponse(int code, String msg, List<MessageData> messageDataList) {
        super(code, msg);
        this.messageDataList = messageDataList;
    }
}
