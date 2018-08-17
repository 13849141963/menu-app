package com.zy.cn.service.impl;
import com.zy.cn.annonations.Cache;
import com.zy.cn.dao.UserDAO;
import com.zy.cn.entity.User;
import com.zy.cn.entity.vo.UserPowerVo;
import com.zy.cn.entity.vo.UserRoleVo;
import com.zy.cn.service.UserService;
import com.zy.cn.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED,
        isolation = Isolation.REPEATABLE_READ,
        readOnly = false,
        timeout = -1,
        rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Boolean insert(User user) {
        //校验用户信息
        Boolean aBoolean = generalCheck(user);
        try{
            if(!aBoolean){
                return Constant.RESULT_FALSE;
            }
            userDAO.insert(user);
        }catch (Exception exception){
            return Constant.RESULT_FALSE;
        }
        return Constant.RESULT_TRUE;
    }

    //修改用户信息
    @Override
    public Boolean update(User user) {
        //校验用户信息
        Boolean aBoolean = generalCheck(user);
        try{
            if(!aBoolean){
                return Constant.RESULT_FALSE;
            }
            userDAO.updateByPrimaryKey(user);
        }catch (Exception exception){
            return Constant.RESULT_FALSE;
        }
        return Constant.RESULT_TRUE;
    }

    //根据id查询用户的信息
    @Override
    public User queryById(Long id) {
        return userDAO.selectByPrimaryKey(id);
    }
    //查询用户
    @Cache
    @Override
    public List<User> query(User user) {
        return userDAO.query(user);
    }
    //查询用户对应的角色
    @Override
    public List<UserRoleVo> queryUserRoles(Long id) {
        if(!StringUtils.isEmpty(id)){
            List<UserRoleVo> userRoleVos = userDAO.queryUserRoles(id);
            if(!userRoleVos.isEmpty()){
                return userRoleVos;
            }
            return null;
        }
        return null;
    }



    //查询用户对应的权限
    @Override
    public List<UserPowerVo> queryUserPowers(Long id) {
        if(!StringUtils.isEmpty(id)){
            List<UserPowerVo> userPowerss = userDAO.queryUserPowers(id);
            if(!userPowerss.isEmpty()){
                return userPowerss;
            }
            return null;
        }
        return null;
    }


    //校验用户信息
    private Boolean generalCheck(User user){
        if(StringUtils.isEmpty(user)){
            return false;
        }
        if(StringUtils.isEmpty(user.getName())){
            return false;
        }
        if(StringUtils.isEmpty(user.getNetworkname())){
            return false;
        }
        return true;
    }

}
