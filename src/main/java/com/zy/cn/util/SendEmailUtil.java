package com.zy.cn.util;
import java.util.Properties;  
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
/****
 * 发送163邮件的工具类  
 * @author Administrator
 *
 */
public class SendEmailUtil {
	/**
	 * 
	 * @param emailAadress:邮箱地址
	 * @param code:验证码
	 * @param cryptographPassword:密文密码
	 */
    public static void testJavaMail(String emailAadress,String code){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.163.com");//指定邮件的发送服务器地址
        props.put("mail.smtp.auth", "true");//服务器是否要验证用户的身份信息

        Session session = Session.getInstance(props);//得到Session
        session.setDebug(true);//代表启用debug模式，可以在控制台输出smtp协议应答的过程

		try {
		        //创建一个MimeMessage格式的邮件
		        MimeMessage message = new MimeMessage(session);
		
		        //设置发送者
		        Address fromAddress = new InternetAddress("13849141963@163.com");//邮件地址
		        message.setFrom(fromAddress);//设置发送的邮件地址
		        //设置接收者
		        Address toAddress = new InternetAddress(emailAadress);//要接收邮件的邮箱
		        message.setRecipient(RecipientType.TO, toAddress);//设置接收者的地址
		
		        //设置邮件的主题
		        message.setSubject("中旺房产");
		        //设置邮件的内容
		        message.setText("【中旺房产】您好，欢迎注册中旺房产,您的验证码为:"+code);
		        //保存邮件
		        message.saveChanges();
		
		
		        //得到发送邮件的服务器(这里用的是smtp服务器)
		        Transport transport = session.getTransport("smtp");
		
		        //发送者的账号连接到smtp服务器上  @163.com可以不写
		        transport.connect("smtp.163.com","13849141963","zy13849141963"); 
		        //发送信息
		        transport.sendMessage(message, message.getAllRecipients());
		        //关闭服务器通道
		        transport.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
    	//SendEmailUtil.testJavaMail("17310649163@163.com","9999","11");
	}
}
