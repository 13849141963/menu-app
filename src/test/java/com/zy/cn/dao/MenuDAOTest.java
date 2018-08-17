package com.zy.cn.dao;

import com.zy.cn.ApplicationContext;
import com.zy.cn.entity.MenuEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuDAOTest extends ApplicationContext{

    @Autowired
    private MenuDAO menuDAO;

    //
    @Test
    public void test(){
        List<MenuEntity> menuEntities = menuDAO.queryAll();
        for (MenuEntity menuEntity : menuEntities) {
            for (MenuEntity entity : menuEntity.getMenus()) {
                System.out.println(entity.getMenu());
            }
        }
    }



}
