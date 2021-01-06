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
 *
 * GroupResponse code
 * 400: success
 * 401: load error -> failed to load groups
 * 402: addGroup error -> failed to add group
 * 403: addFriend error -> failed to add friend
 * 404: deleteGroup error -> failed to delete group
 * 405: deleteFriend error -> failed to delete friend
 * 406: changeGroup error -> failed to change group
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
            return new GroupResponse(1, "failed to load groups", null);
        }
        return new GroupResponse(0, "success", groups);
    }

    @PostMapping("/addGroup")
    Response addGroup(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String gname = jsonObject.getString("gname");
        List<GroupData> groups;
        try {
            groupService.addGroup(username, gname);
            groups = groupService.getGroups(username);
        } catch (Exception e) {
            return new GroupResponse(2, "failed to add group", null);
        }
        return new GroupResponse(0, "success", groups);
    }

    @PostMapping("/addFriend")
    Response addFriend(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        int gid = jsonObject.getInteger("gid");
        String name = jsonObject.getString("name");
        List<GroupData> groups;
        try {
            groupService.addFriend(gid, name);
            groups = groupService.getGroups(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new GroupResponse(3, "failed to add friend", null);
        }
        return new GroupResponse(0, "success", groups);
    }

    @PostMapping("/deleteGroup")
    Response deleteGroup(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        int gid = jsonObject.getInteger("gid");
        List<GroupData> groups;
        try {
            groupService.deleteGroup(gid);
            groups = groupService.getGroups(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new GroupResponse(4, "failed to delete group", null);
        }
        return new GroupResponse(0, "success", groups);
    }

    @PostMapping("/deleteFriend")
    Response deleteFriend(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        int gid = jsonObject.getInteger("gid");
        String name = jsonObject.getString("name");
        List<GroupData> groups;
        try {
            groupService.deleteFriend(gid, name);
            groups = groupService.getGroups(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new GroupResponse(5, "failed to delete friend", null);
        }
        return new GroupResponse(0, "success", groups);
    }

    @PostMapping("/changeGroup")
    Response changeGroup(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        int oldGid = jsonObject.getInteger("oldGid");
        int newGid = jsonObject.getInteger("newGid");
        String name = jsonObject.getString("name");
        List<GroupData> groups;
        try {
            groupService.changeGroup(oldGid, newGid, name);
            groups = groupService.getGroups(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new GroupResponse(6, "failed to change group", null);
        }
        return new GroupResponse(0, "success", groups);
    }

}
