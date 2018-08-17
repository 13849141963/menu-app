package com.zy.cn.realm;

import com.zy.cn.dao.AdminDAO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthRealm extends AuthorizingRealm {


    @Autowired
    private AdminDAO adminDAO;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用用户名
        Object principal = authenticationToken.getPrincipal();
        //获取永华密码
        Object credentials = authenticationToken.getCredentials();







        System.out.println(credentials);

       /* if(){

        }*/



        return null;
    }


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
