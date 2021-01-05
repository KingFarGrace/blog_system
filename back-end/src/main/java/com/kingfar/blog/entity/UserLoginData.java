package com.kingfar.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZHANGKAIHENG
 */
@Data
public class UserLoginData implements Serializable {
    private long uid;
    private String username;
    private String mail;
    private int age;
    private String sex;
    private String signature;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;
    private int blogCount;
}
