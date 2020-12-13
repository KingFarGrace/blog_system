package com.kingfar.blog.shiro;

import com.kingfar.blog.entity.UserVerifyData;
import com.kingfar.blog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ZHANGKAIHENG
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("execute => Authorization");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // TODO add permissions
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("execute => Authentication");
        // TODO encrypt password
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserVerifyData user = userService.verify(token.getUsername());
        if(user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
    }
}
