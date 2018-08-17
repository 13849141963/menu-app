package com.zy.cn.dao;

import com.zy.cn.entity.MenuEntity;

public interface MenuDAO extends BaseDAO<MenuEntity>{


    int deleteByPrimaryKey(Integer id);

    int insertSelective(MenuEntity record);

    MenuEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuEntity record);

    int updateByPrimaryKey(MenuEntity record);
}