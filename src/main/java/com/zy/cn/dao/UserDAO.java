package com.zy.cn.dao;

import com.zy.cn.entity.User;
import com.zy.cn.entity.vo.UserPowerVo;
import com.zy.cn.entity.vo.UserRoleVo;

import java.util.List;

public interface UserDAO extends BaseDAO<User>{
    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //根据用户的信息查询存在的用户
    List<User> query(User user);

    //查询用户对应的角色
    List<UserRoleVo> queryUserRoles(Long id);

    //查询用户对应的权限
    List<UserPowerVo> queryUserPowers(Long id);
}