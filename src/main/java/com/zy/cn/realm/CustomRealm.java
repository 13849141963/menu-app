package com.zy.cn.realm;
import com.zy.cn.entity.User;
import com.zy.cn.entity.vo.UserPowerVo;
import com.zy.cn.entity.vo.UserRoleVo;
import com.zy.cn.service.UserService;
import com.zy.cn.util.Constant;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@Controller("customRealm")
public class CustomRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    User user = null;

    /****
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取用户名称
        Object principal = token.getPrincipal();
        //获取用户密码
        Object credentials = token.getCredentials();

        user = new User();
        //user.setPassword(String.valueOf(credentials));
        user.setName(String.valueOf(principal));

        //查询用户数据
        List<User> query = userService.query(user);


        //判断该用户是否存在
        if(null!=query.get(0)){

            //账号锁定
            if(query.get(0).getState().equals(Constant.USER_STATS_FAILURE)){
                throw new LockedAccountException("状态失效");
            }

            //把信息交个shrio去做认证
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(query.get(0).getName(), query.get(0).getPassword(), ByteSource.Util.bytes(query.get(0).getSalt().getBytes()), this.getName());
            //把用户的信息放入session中
            //ShiroUtils.setSessionAttribute("user",query.get(0));
            //把用户的信息放入cookie中
            Cookie user1 = new Cookie("user", query.get(0).getId() + "@" + query.get(0).getName());
            return simpleAuthenticationInfo;

        }
        return null;
    }

    /*****
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取用户的账户信息
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();


        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        user = new User();
        user.setName(String.valueOf(primaryPrincipal));

        //通过用户名字查询用户数据
        List<User> query = userService.query(user);
        if(!Constant.USER_DATA_NULL.equals(query.size()) && !query.isEmpty()){

            //查询用户对应的角色
            List<UserRoleVo> userRoleVos = userService.queryUserRoles(query.get(0).getId());

            if(userRoleVos!=null){
                //将角色添加到集合中
                List<String> roles = new ArrayList<String>();
                for (UserRoleVo userRoleVo : userRoleVos) {
                    roles.add(userRoleVo.getRoleName());
                }

                //将角色将角色交给shiro管理
                simpleAuthorizationInfo.addRoles(roles);
            }


            //获取用户的权限
            List<UserPowerVo> userPowerVos = userService.queryUserPowers(query.get(0).getId());

            if(userPowerVos!=null){
                //将用户的权限添加到集合中
                List<String> powers = new ArrayList<String>();
                for (UserPowerVo permissions : userPowerVos) {
                    powers.add(permissions.getPowerName());
                }
                //将用户的权限交给shiro管理
                simpleAuthorizationInfo.addStringPermissions(powers);
            }
            return simpleAuthorizationInfo;
        }
        return simpleAuthorizationInfo;
    }


}
