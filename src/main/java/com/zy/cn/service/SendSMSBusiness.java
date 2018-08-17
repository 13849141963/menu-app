package com.zy.cn.service;
import com.zy.cn.disposition.SendSMS1;
import org.springframework.stereotype.Service;

@Service("sendSMSBusiness")
public class SendSMSBusiness {
    /*****
     * 发送短信验证码
     * @param name 用户名
     * @param code 验证码
     * @return 是否发送成功
     */
    public boolean sendSMS(String name,String code){

        //调用接口发送短信
        boolean send_result_ = SendSMS1.sendSMS(name, code);

        if(send_result_){
            return true;
        }
        return false;
    }

}
