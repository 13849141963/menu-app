package com.zy.cn.controller.management;
import com.zy.cn.controller.util.ResultUtil;
import com.zy.cn.entity.AdminEntity;
import com.zy.cn.service.AdminService;
import com.zy.cn.util.Base64Util;
import com.zy.cn.controller.util.CookieUtil;
import com.zy.cn.util.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**********
 * 管理员的Controller
 */
@Controller
@RequestMapping("/management/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private final static Logger logger = LoggerFactory.getLogger(AdminController.class);
    @RequestMapping("/login")
    @ResponseBody
    public ResultUtil queryByNameMobileEmail(AdminEntity adminEntity, HttpServletRequest request, String verify, HttpServletResponse response){
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
        //校验用户信息
        checkAdminEntity(adminEntity);
        //对用户名,密码,验证码进行based4解密
        AdminEntity  decode_admin = decode(adminEntity);

        AdminEntity admin_entity = adminService.queryEntity(decode_admin);
        if(null == admin_entity){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("用户或密码错误！");
            return result;
        }

        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken(decode_admin.getName(),decode_admin.getPassword()));


        //放入cookis中
        Cookie admin = new Cookie("admin", admin_entity.getId() + "&" + admin_entity.getName() + "&" + admin_entity.getPassword());
        //设置Cookie有效时间为1小时
        admin.setMaxAge(60*60);
        response.addCookie(admin);
        result.setCode(Constant.USER_CORRENT_DODE);
        result.setData(admin_entity);
        logger.info("<<<<登陆校验结束>>>>>");
        return result;
    }


    /*****
     * 注册管理员
     * @param adminEntity
     * @param auth_code
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public ResultUtil saveAdmin(AdminEntity adminEntity,String auth_code,HttpServletRequest request){
        ResultUtil result = new ResultUtil();
        logger.info("<<<<注册管理员开始>>>>>");

        //获取cookid中的短信验证码
        Cookie auth = CookieUtil.getCookieByName(request, "auth_code");
        if(null == auth_code||"".equals(auth_code)){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("短信验证码不能为空！");
            return result;
        }

        if(null==auth){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("验证码已失效,请重新发送！");
            return result;
        }
        //=============多此一举===================
        //获取分钟数,超过五分钟就会失效
        Long minutes = (Calendar.getInstance().getTimeInMillis() - Long.parseLong(auth.getValue().split("@")[1])) / (1000 * 60);
        if(minutes>5){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("验证码已失效,请重新发送！");
            return result;
        }
        //=======================================
        //校验用户数据
        Boolean aBoolean = checkAdminEntity(adminEntity);
        if(!aBoolean || StringUtils.isEmpty(adminEntity.getPassword())){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("用户名或密码不能为空！");
            return result;
        }
        //===================用户可以重名注册=============
        //判断用户名是否存在
        /*AdminEntity admin_entity = adminService.queryEntity(adminEntity);
        if(null == admin_entity){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("用户或密码错误！");
            return result;
        }*/
        //注册用户
        boolean register_resule = adminService.saveAdminEntity(adminEntity);
        if(!register_resule){
            //注册失败
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("注册失败请重新注册！");
            return result;
        }

        //成功注册用户
        result.setCode(Constant.USER_CORRENT_DODE);
        logger.info("<<<<注册管理员结束>>>>>");
        return result;
    }


    /*****
     * 修改管理员的信息
     * @param adminEntity
     * @return
     *
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultUtil uptdateAdmin(AdminEntity adminEntity,HttpServletRequest request){
        ResultUtil result = new ResultUtil();
        logger.info("<<<<修改管理员信息开始>>>>>");
        if(StringUtils.isEmpty(adminEntity.getPassword())){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("密码不能为空！");
            return result;
        }
        boolean b = adminService.updateAdminEntity(adminEntity);
        if(!b){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("修改密码失败！");
            result.setStatus(Constant.RESULT_FALSE);
            return result;
        }
        result.setStatus(Constant.RESULT_TRUE);
        result.setCode(Constant.USER_CORRENT_DODE);
        logger.info("<<<<修改管理员信息开始>>>>>");
        return result;
    }

    /*****
     * 退出清空
     * @param adminEntity
     * @return
     */








    //对用户名,密码解密
    private AdminEntity decode(AdminEntity adminEntity){
        AdminEntity admin = new AdminEntity();
        checkAdminEntity(adminEntity);
        String name = new String(Base64Util.decode(adminEntity.getName()));
        String password = new String(Base64Util.decode(adminEntity.getPassword()));
        admin.setName(name);
        admin.setPassword(password);
        return admin;

    }

    //校验用户数据
    private Boolean checkAdminEntity(AdminEntity adminEntity){
        if(null == adminEntity){
            return false;
        }
        return true;
    }
}
