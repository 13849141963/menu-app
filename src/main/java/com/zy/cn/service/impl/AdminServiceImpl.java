package com.zy.cn.service.impl;

import com.zy.cn.dao.AdminDAO;
import com.zy.cn.entity.AdminEntity;
import com.zy.cn.exception.ServiceException;
import com.zy.cn.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("adminService")
@Transactional(propagation = Propagation.REQUIRED,
                isolation = Isolation.REPEATABLE_READ,
                readOnly = false,
                timeout = -1,
                rollbackFor = RuntimeException.class)
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminDAO adminDAO;

    @Override
    public boolean saveAdminEntity(AdminEntity adminEntity) {
        try{
            adminDAO.insert(adminEntity);
        }catch (Exception exception){
            return false;
        }
        return true;

    }

    @Override
    public boolean updateAdminEntity(AdminEntity adminEntity) {
        try{
            //校验管理员
            checkAdmin(adminEntity);
            adminDAO.update(adminEntity);
        }catch (Exception exception){
           return false;
        }
       return true;
    }

    //根据名字,邮箱,手机号查询管理员
    @Transactional(readOnly=true,propagation = Propagation.SUPPORTS)
    @Override
    public AdminEntity queryEntity(AdminEntity adminEntity){
        //校验管理员
        checkAdmin(adminEntity);
        return adminDAO.queryEntity(adminEntity);
    }




    private void checkAdmin(AdminEntity adminEntity){
        if(null == adminEntity){
            throw new ServiceException("管理员不能为空");
        }
        if(!"".equals(adminEntity.getName())&& null != adminEntity.getName()){
            return;
        }
        if(!"".equals(adminEntity.getEmail())&& null != adminEntity.getEmail()){
            return;
        }
        if(!"".equals(adminEntity.getMobile())&& null != adminEntity.getMobile()){
            return;
        }
        throw new ServiceException("管理员名称,邮箱,手机号不能全部为空!!!");
    }
}
