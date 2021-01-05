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

}
