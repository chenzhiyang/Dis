/*
 * Decompiled with CFR 0_121.
 * 
 * Could not load the following classes:
 *  javax.mail.Address
 *  javax.mail.Authenticator
 *  javax.mail.Message
 *  javax.mail.Message$RecipientType
 *  javax.mail.PasswordAuthentication
 *  javax.mail.Session
 *  javax.mail.Transport
 *  javax.mail.internet.InternetAddress
 *  javax.mail.internet.MimeMessage
 *  javax.mail.internet.MimeUtility
 */
package com.Email;

import com.Email.GenerateLinkUtils;
import com.cj.discount.model.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class EmailUtils
extends Thread {
    private static final String FROM = "13120550180@163.com";
    User user = new User();
    int flag = 0;

    public EmailUtils(User user1, int flag1) {
        this.user = user1;
        this.flag = flag1;
    }

    @Override
    public void run() {
        if (this.flag == 1) {
            EmailUtils.sendAccountActivateEmail(this.user);
        }
        if (this.flag == 2) {
            EmailUtils.sendResetPasswordEmail(this.user);
        }
    }

    public static void sendAccountActivateEmail(User user) {
        Session session = EmailUtils.getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject(MimeUtility.encodeText((String)"\u5e10\u6237\u6fc0\u6d3b\u90ae\u4ef6", (String)"gb2312", (String)"B"));
            message.setSentDate(new Date());
            message.setFrom((Address)new InternetAddress("13120550180@163.com"));
            message.setRecipient(Message.RecipientType.TO, (Address)new InternetAddress(user.getMail()));
            message.setContent((Object)("<a href='" + GenerateLinkUtils.generateActivateLink(user) + "'>\u70b9\u51fb\u6fc0\u6d3b\u5e10\u6237</a>"), "text/html;charset=utf-8");
            Transport.send((Message)message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendResetPasswordEmail(User user) {
        Session session = EmailUtils.getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject("\u627e\u56de\u60a8\u7684\u5e10\u6237\u4e0e\u5bc6\u7801");
            message.setSentDate(new Date());
            message.setFrom((Address)new InternetAddress("13120550180@163.com"));
            message.setRecipient(Message.RecipientType.TO, (Address)new InternetAddress(user.getMail()));
            message.setContent((Object)("\u8981\u4f7f\u7528\u65b0\u7684\u5bc6\u7801, \u8bf7\u4f7f\u7528\u4ee5\u4e0b\u94fe\u63a5\u542f\u7528\u5bc6\u7801:<br/><a href='" + GenerateLinkUtils.generateResetPwdLink(user) + "'>\u70b9\u51fb\u91cd\u65b0\u8bbe\u7f6e\u5bc6\u7801</a>"), "text/html;charset=utf-8");
            Transport.send((Message)message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance((Properties)props, (Authenticator)new Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication() {
                String password = null;
                InputStream is = EmailUtils.class.getResourceAsStream("password.dat");
                byte[] b = new byte[1024];
                try {
                    int len = is.read(b);
                    password = new String(b, 0, len);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return new PasswordAuthentication("13120550180@163.com", password);
            }
        });
        return session;
    }

}
