package com.kingfar.blog.mapper;

import com.kingfar.blog.entity.MessageData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    List<MessageData> queryMessageByToAndFrom(@Param("fromuser") String fromuser,@Param("touser") String touser);

    int insertMessage(@Param("fromuser") String fromuser,@Param("content") String content,@Param("touser") String touser);
}
