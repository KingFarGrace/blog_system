package com.kingfar.blog.mapper;

import com.kingfar.blog.entity.UserLoginData;
import com.kingfar.blog.entity.UserVerifyData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author ZHANGKAIHENG
 */
@Mapper
@Repository
public interface AccountMapper {
    public UserVerifyData selectByUsername(String username);
    public UserLoginData queryLoginData(String username);
    public int setSignUpInfo(@Param("usr") String username, @Param("pwd") String password, @Param("ctime") Date ctime);
}
