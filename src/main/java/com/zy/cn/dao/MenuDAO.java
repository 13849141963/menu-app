package com.zy.cn.dao;

import com.zy.cn.entity.Menu;
import com.zy.cn.entity.vo.MenuVo;

import java.util.List;

public interface MenuDAO extends BaseDAO<Menu>{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

}