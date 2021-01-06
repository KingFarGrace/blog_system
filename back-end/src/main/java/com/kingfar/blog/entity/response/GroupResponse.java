package com.kingfar.blog.entity.response;

import com.kingfar.blog.entity.GroupData;
import lombok.Data;

import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class GroupResponse extends Response {
    private List<GroupData> groups;

    public GroupResponse(int code, String msg, List<GroupData> groups) {
        super(code, msg);
        this.groups = groups;
    }

    @Override
    protected int groupCode() {
        return 6;
    }
}
