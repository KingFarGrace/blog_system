package com.kingfar.blog.mapper;

import com.kingfar.blog.entity.UserLoginData;
import com.kingfar.blog.entity.UserVerifyData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZHANGKAIHENG
 */
@Mapper
@Repository
public interface UserMapper {
    public UserVerifyData selectByUsername(String username);
    public UserLoginData queryLoginData(String username);
}
