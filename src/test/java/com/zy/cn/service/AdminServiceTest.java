package com.zy.cn.service;

import com.zy.cn.ApplicationContext;
import com.zy.cn.entity.AdminEntity;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceTest extends ApplicationContext {


    @Autowired
    private AdminService adminService;
    //
    @Test
    public void test(){
      /*  AdminEntity adminEntity = new AdminEntity();
        adminEntity.setPassword("123456");
        adminEntity.setName("admin");
        AdminEntity adminEntity1 = adminService.queryEntity(adminEntity);
        System.out.println("==============");*/

        Md5Hash md5Hash = new Md5Hash("12345","111100",1024);

        System.out.println(md5Hash);
    }

}
