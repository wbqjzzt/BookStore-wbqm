package com.wbqm.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Properties;

public class SendEmail {
    private static final String fromUser = "2365277849@qq.com";

    private static final String auth = "wjgyieaolraqdjfc";

    public static void send(String toUser, String title, String content) {
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.qq.com");
        prop.put("mail.smtp.host", "smtp.qq.com");
        prop.put("mail.smtp.auth", "true");

        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.port", "465");

        Session session = Session.getDefaultInstance(prop);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(fromUser));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
            message.setSubject(title);
            Multipart multipart = new MimeMultipart();
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(content, "text/html;charset=utf-8");
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.qq.com", fromUser, auth);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
