package com.zy.cn.service;
import com.zy.cn.entity.AdminEntity;
/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-10 23:20:23
 */
public interface AdminService  {


   public boolean saveAdminEntity(AdminEntity adminEntity);



   public boolean updateAdminEntity(AdminEntity adminEntity);


   public AdminEntity queryEntity(AdminEntity adminEntity);
}

