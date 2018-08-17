package com.zy.cn.service;

import com.zy.cn.ApplicationContext;
import com.zy.cn.entity.AdminEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceTest extends ApplicationContext {


    @Autowired
    private AdminService adminService;
    //
    @Test
    public void test(){
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setPassword("123456");
        adminEntity.setName("win9");
        adminService.queryEntity(adminEntity);
    }

}
