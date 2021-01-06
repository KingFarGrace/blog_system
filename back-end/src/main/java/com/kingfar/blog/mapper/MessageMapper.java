package com.kingfar.blog.mapper;

import com.kingfar.blog.entity.MessageData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    List<MessageData> queryMessageByToAndFrom(@Param("fromUser") String fromUser,
                                              @Param("toUser") String toUser);

    int insertMessage(@Param("fromUser") String fromUser,
                      @Param("content") String content,
                      @Param("toUser") String touser);

    List<String> getSenders(String receiver);

    int changeIsReadState(@Param("sender") String sender,
                          @Param("receiver") String receiver);
}
