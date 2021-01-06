package com.kingfar.blog.service;

import com.kingfar.blog.entity.GroupData;
import com.kingfar.blog.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Override
    public List<GroupData> getGroups(String username) {
        return groupMapper.getGroups(username);
    }

    @Override
    public void addGroup(String owner, String gname) {
        groupMapper.addGroup(owner, gname);
    }

    @Override
    public void addFriend(String owner, String username) {
        int gid = groupMapper.getDefaultGid(owner);
        groupMapper.addFriend(gid, username);
    }

    @Override
    public void deleteGroup(int gid) {
        groupMapper.deleteGroup(gid);
    }

    @Override
    public void deleteFriend(int gid, String username) {
        groupMapper.deleteFriend(gid, username);
    }

    @Override
    public void changeGroup(int oldGid, int newGid, String username) {
        groupMapper.changeGroup(oldGid, newGid, username);
    }

}
