package com.kingfar.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class GroupData implements Serializable {
    private int gid;
    private String gname;
    private List<String> friends;
}
