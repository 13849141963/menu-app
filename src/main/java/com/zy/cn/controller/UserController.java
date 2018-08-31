package com.zy.cn.controller;

import com.zy.cn.controller.management.AdminController;
import com.zy.cn.controller.util.CookieUtil;
import com.zy.cn.controller.util.ResultUtil;
import com.zy.cn.entity.User;
import com.zy.cn.service.UserService;
import com.zy.cn.util.Base64Util;
import com.zy.cn.util.Constant;
import com.zy.cn.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.cs.US_ASCII;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    private final static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultUtil login(User user,HttpServletRequest request, String verify, HttpServletResponse response){
        ResultUtil result = new ResultUtil();
        logger.info("<<<<登陆校验开始>>>>>");
        //从cookie中获取验证码
        Cookie cookis_Code = CookieUtil.getCookieByName(request, "code");
        String code = cookis_Code.getValue().split("@")[0];
        //校验验证码数据
        if(null == verify  || "".equals(verify)){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("验证码不能为空！");
            return result;
        }
        //判断Cookie的时间大于两分钟之后重新刷新验证码
        long times = Calendar.getInstance().getTimeInMillis();
        String validTime = cookis_Code.getValue().split("@")[1];
        //获取分钟数
        Long minutes = (times - Long.parseLong(validTime)) / (1000 * 60);
        if(minutes>2){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("验证码已失效！");
            return result;
        }
        //校验验证码
        if(!code.equalsIgnoreCase(verify)){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("验证码不正确:"+verify);
            return result;
        }

        //校验
        if(!StringUtils.isEmpty(user.getName()) && !StringUtils.isEmpty(user.getPassword())){
            //对用户名,密码,验证码进行based4解密
            User decode_user = decode(user);

            //通过shrio获取主体对象
            Subject subject = SecurityUtils.getSubject();

            //通过自定义的Realm进行校验用户的信息
            try{
                //创建令牌
                UsernamePasswordToken token = new UsernamePasswordToken(decode_user.getName(), decode_user.getPassword());
                //开启记住我功能
                token.setRememberMe(true);
                subject.login(token);
            }catch (UnknownAccountException e) {
                result.setCode(Constant.USER_ERROR_DODE);
                result.setMsg("用户名不存在！");
                return result;
            }catch (IncorrectCredentialsException e) {
                result.setCode(Constant.USER_ERROR_DODE);
                result.setMsg("密码错误！");
                return result;
            }catch (LockedAccountException e){
                result.setCode(Constant.USER_ERROR_DODE);
                result.setMsg("账号已被锁定,请联系管理员！");
                return result;
            }catch (Exception s){
                result.setCode(Constant.USER_ERROR_DODE);
                result.setMsg("ssss！");
                return result;
            }
            result.setCode(Constant.USER_CORRENT_DODE);
            result.setStatus(Constant.RESULT_TRUE);

        }
        logger.info("<<<<登陆校验结束>>>>>");
        return result;
    }

    //对用户名,密码解密
    private User decode(User user){
        User admin = new User();
        String name = new String(Base64Util.decode(user.getName()));
        String password = new String(Base64Util.decode(user.getPassword()));
        admin.setName(name);
        admin.setPassword(password);
        return admin;
    }

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public User info(){
        Session session = ShiroUtils.getSession();
        return (User)session.getAttribute("user");
    }
    /*User getUser(){
        return ShiroUtils.getUserEntity();
    }*/








    //@RequiresRoles({"user"})注解是shiro的角色注解,只有该用户拥有user角色就可以访问该方法,
    @RequestMapping("/rest")
    @ResponseBody
    //@RequiresRoles({"user"})
    @RequiresPermissions("save")//注解式shiro权限的注解,只有该用户拥有该权限在能访问此方法
    public String ss(){
        return "ss";
    }

}
