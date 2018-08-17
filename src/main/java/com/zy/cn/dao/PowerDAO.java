package com.zy.cn.dao;

import com.zy.cn.entity.Power;

public interface PowerDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);
}