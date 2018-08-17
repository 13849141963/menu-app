package com.zy.cn.dao;

import com.zy.cn.entity.User_Role;

public interface User_RoleDAO {
    int deleteByPrimaryKey(Long id);

    int insert(User_Role record);

    int insertSelective(User_Role record);

    User_Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User_Role record);

    int updateByPrimaryKey(User_Role record);
}