package com.kingfar.blog.service;

import com.kingfar.blog.entity.GroupData;

import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
public interface GroupService {

    List<GroupData> getGroups(String username);

}
