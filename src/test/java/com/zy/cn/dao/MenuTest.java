package com.zy.cn.dao;

import com.zy.cn.ApplicationContext;
import com.zy.cn.entity.Menu;
import com.zy.cn.entity.vo.MenuVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuTest extends ApplicationContext{


    @Autowired
    private MenuDAO menuDAO;


    //
    @Test
    public void test(){

        List<Menu> menus = menuDAO.queryAll();
        for (Menu menu : menus) {

            System.out.println(menu.getName());

            List<Menu> lists = menu.getLists();
            for (Menu list : lists) {
                System.out.println(list.getName());
            }

            System.out.println("=====================");
        }

    }




}
