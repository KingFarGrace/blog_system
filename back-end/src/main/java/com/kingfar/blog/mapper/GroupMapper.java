package com.kingfar.blog.mapper;

import com.kingfar.blog.entity.GroupData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
@Mapper
@Repository
public interface GroupMapper {
    List<GroupData> getGroups(String username);
}
