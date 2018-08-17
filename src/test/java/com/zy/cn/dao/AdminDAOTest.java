package com.zy.cn.dao;

import com.zy.cn.ApplicationContext;
import com.zy.cn.entity.AdminEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class AdminDAOTest extends ApplicationContext{

    @Autowired
    private AdminDAO adminDAO;
    //
    @Test
    public void testqueryAdmin(){

        AdminEntity adminEntity = new AdminEntity();
        //adminEntity.setName("win7");
        adminEntity.setPassword("72ef1e7d3a341d4fbff3ea0b9b6486f4");
        AdminEntity adminEntity1 = adminDAO.queryEntity(adminEntity);
        adminEntity.setEmail("1191918342@qq.com");
        //adminEntity.setMobile("1191918342@qq.com");
        System.out.println(adminEntity1.getName());
        AdminEntity adminEntity3 = adminDAO.queryEntity(adminEntity);
        AdminEntity adminEntity2 = adminDAO.queryEntity(adminEntity);
        AdminEntity adminEntity4 = adminDAO.queryEntity(adminEntity);

    }

}
