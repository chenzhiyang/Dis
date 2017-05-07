/*
 * Decompiled with CFR 0_121.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletRequest
 */
package com.Email;

import com.cj.discount.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletRequest;

public class GenerateLinkUtils {
    private static final String CHECK_CODE = "checkCode";

    public static String generateActivateLink(User user) {
        return "http://localhost:8080/Dis/servlet/ActivateAccountServlet?username=" + user.getUsername() + "&" + "checkCode" + "=" + GenerateLinkUtils.generateCheckcode(user);
    }//地址换到服务器时需换掉localhost

    public static String generateResetPwdLink(User user) {
        return "http://localhost:8080/Dis/servlet/ResetPasswordServlet?userName=" + user.getUsername() + "&" + "checkCode" + "=" + GenerateLinkUtils.generateCheckcode(user);
    }

    public static String generateCheckcode(User user) {
        String username = null;
        String password = null;
        if (user.getUsername() != null) {
            username = user.getUsername();
        }
        if (user.getPassword() != null) {
            password = user.getPassword();
        }
        return GenerateLinkUtils.md5(String.valueOf(username) + ":" + password);
    }

    public static boolean verifyCheckcode(User user, ServletRequest request) {
        String checkCode = request.getParameter("checkCode");
        return GenerateLinkUtils.generateCheckcode(user).equals(checkCode);
    }

    public static boolean verifyCheckcode2(User user, String checkCode) {
        return GenerateLinkUtils.generateCheckcode(user).equals(checkCode);
    }

    private static String md5(String string) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
            md.update(string.getBytes());
            byte[] md5Bytes = md.digest();
            return GenerateLinkUtils.bytes2Hex(md5Bytes);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytes2Hex(byte[] byteArray) {
        StringBuffer strBuf = new StringBuffer();
        int i = 0;
        while (i < byteArray.length) {
            if (byteArray[i] >= 0 && byteArray[i] < 16) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(byteArray[i] & 255));
            ++i;
        }
        return strBuf.toString();
    }
}
