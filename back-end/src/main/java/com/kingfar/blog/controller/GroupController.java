package com.kingfar.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.kingfar.blog.entity.GroupData;
import com.kingfar.blog.entity.response.GroupResponse;
import com.kingfar.blog.entity.response.Response;
import com.kingfar.blog.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping("/load")
    Response load(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        List<GroupData> groups;
        try {
            groups = groupService.getGroups(username);
        } catch (Exception e) {
            return new GroupResponse(1, "failed to load group", null);
        }
        return new GroupResponse(0, "success", groups);
    }

}
