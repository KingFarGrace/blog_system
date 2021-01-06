package com.kingfar.blog.service;

import com.kingfar.blog.entity.GroupData;

import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
public interface GroupService {

    List<GroupData> getGroups(String username);

    void addGroup(String owner, String gname);

    void addFriend(int gid, String username);

    void deleteGroup(int gid);

    void deleteFriend(int gid, String username);

    void changeGroup(int oldGid, int newGid, String username);

}
