package com.kingfar.blog.mapper;

import com.kingfar.blog.entity.GroupData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
@Mapper
@Repository
public interface GroupMapper {
    List<GroupData> getGroups(String username);

    List<String> getFriends(int gid);

    int addGroup(@Param("owner") String owner,
                 @Param("gname") String gname);

    int addFriend(@Param("gid") int gid,
                  @Param("username") String username);

    int deleteGroup(int gid);

    int deleteFriend(@Param("gid") int gid,
                     @Param("username") String username);

    int changeGroup(@Param("old") int oldGid,
                    @Param("new") int newGid,
                    @Param("username") String username);
}
