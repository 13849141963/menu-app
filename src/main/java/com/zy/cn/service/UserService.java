package com.zy.cn.service;

import com.zy.cn.entity.User;
import com.zy.cn.entity.vo.UserPowerVo;
import com.zy.cn.entity.vo.UserRoleVo;

import java.util.List;

public interface UserService {

    /*public static void main(String[] args) {

        //https://blog.csdn.net/preferG/article/details/77950221
        springboot 注解式缓存//https://blog.csdn.net/u014104286/article/details/79125141
        https://www.cnblogs.com/scwanglijun/p/3760177.html
    }*/


    public Boolean insert(User user);

    public Boolean update(User user);

    public User queryById(Long id);

    public List<User> query(User user);

    public List<UserRoleVo> queryUserRoles(Long id);

    public List<UserPowerVo> queryUserPowers(Long id);







}
