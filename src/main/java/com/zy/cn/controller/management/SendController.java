package com.zy.cn.controller.management;
import com.zy.cn.controller.util.ResultUtil;
import com.zy.cn.entity.AdminEntity;
import com.zy.cn.service.SendSMSBusiness;
import com.zy.cn.util.Constant;
import com.zy.cn.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Controller
@RequestMapping("/send/messge")
public class SendController {

    @Autowired
    private SendSMSBusiness sendSMSBusiness;


    @ResponseBody
    @RequestMapping("/sms")
    public ResultUtil sendSMS(AdminEntity adminEntity, HttpServletResponse response){
        ResultUtil result = new ResultUtil();
        if(StringUtils.isEmpty(adminEntity.getName())){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("用户名不能为空！");
            return result;
        }

        //获取定长的验证码
        long randomLong = RandomUtil.randomLong(6);
        //转成String字符串类型
        String code = toString().valueOf(randomLong);

        boolean send_result = sendSMSBusiness.sendSMS(adminEntity.getName(), code);

        if(!send_result){
            result.setCode(Constant.USER_ERROR_DODE);
            result.setMsg("验证码发送失败！");
            return result;
        }

        //发送成功
        long times = Calendar.getInstance().getTimeInMillis();
        //将验证码放入cookie中
        Cookie auth_code = new Cookie("auth_code", code+"@"+times);
        //短信有效时间为5分钟
        auth_code.setMaxAge(60*5);
        response.addCookie(auth_code);
        result.setCode(Constant.USER_CORRENT_DODE);
        return result;
    }


}
