package com.zy.cn.dao;

import com.zy.cn.ApplicationContext;
import com.zy.cn.entity.User;
import com.zy.cn.entity.vo.UserPowerVo;
import com.zy.cn.entity.vo.UserRoleVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class UserDAOTest extends ApplicationContext {


    @Autowired
    private UserDAO userDAO;
    //
    @Test
    public void test(){

        User user = new User();
        user.setName("zhangsan");
       user.setPassword("8e921ea08e7755852503fc31d22d9e45");

        List<UserPowerVo> userPowerVos = userDAO.queryUserPowers(1l);

        for (UserPowerVo userPowerVo : userPowerVos) {
            System.out.println("权限名称:"+userPowerVo.getPowerName());
        }


        /*List<User> query = userDAO.query(user);
        for (User userEntity : query) {
            System.out.println(userEntity.getName()+"+");
        }*/

    }


    //查询用户对应的角色
    //
    @Test
    public void testUser_Roles(){
        List<UserRoleVo> userRoleVos = userDAO.queryUserRoles(1L);
        for (UserRoleVo userRoleVo : userRoleVos) {
            System.out.println(userRoleVo.getRoleName());
        }
    }

    //
    //  查询用户对应的权限
    @Test
    public void testUser_Powers(){
        List<UserPowerVo> userRoleVos = userDAO.queryUserPowers(1l);
        for (UserPowerVo userRoleVo : userRoleVos) {
            System.out.println(userRoleVo.getPowerName());
        }
        System.out.println("-==========================");
        List<UserPowerVo> userRoleVo1s = userDAO.queryUserPowers(1l);
        List<UserPowerVo> userRoleVo11s = userDAO.queryUserPowers(1l);
        List<UserPowerVo> userRol1eVos = userDAO.queryUserPowers(1l);


    }


}
