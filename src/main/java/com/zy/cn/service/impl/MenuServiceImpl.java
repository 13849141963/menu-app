package com.zy.cn.service.impl;

import com.zy.cn.dao.MenuDAO;
import com.zy.cn.entity.MenuEntity;
import com.zy.cn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/****
 * 菜单
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDAO menuDAO;

    @Override
    public List<MenuEntity> queryAll() {
        return menuDAO.queryAll();
    }
}
