package com.zy.cn.dao;

import com.zy.cn.entity.Role_power;

public interface Role_powerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role_power record);

    int insertSelective(Role_power record);

    Role_power selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role_power record);

    int updateByPrimaryKey(Role_power record);
}