package com.zy.cn.entity.vo;

import com.zy.cn.entity.User;

import java.util.Date;

/****
 * 用户对应的角色
 */
public class UserRoleVo {


    private Long userId;

    private String userName;

    private Long roleId;

    private String roleName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
