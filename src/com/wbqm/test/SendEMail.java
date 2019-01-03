package com.wbqm.test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import com.sun.mail.util.MailSSLSocketFactory;

public class SendEMail {
    public static void main(String [] args) throws Exception {

        // 收件人电子邮箱
        String to = "gbwzcv43709@chacuo.net";

        // 发件人电子邮箱
        String from = "2805363696@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("2805363696@qq.com", "odszazielburddje"); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("Happy New Year!");

            // 设置消息体
            message.setText("Thank you! from Hatsune Miku");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from: " + from);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
