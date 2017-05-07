/*
 * Decompiled with CFR 0_121.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package servlet;

import com.Email.EmailUtils;
import com.cj.discount.model.User;
import com.dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/servlet/ForGotPwdServlet"})
public class ForGotPwdServlet
extends HttpServlet {
    private static final long serialVersionUID = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        UserDao userDao = new UserDao();
        User user = new User();
        try {
            user = userDao.getUserByUsername(userName);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null) {
            request.setAttribute("errorMsg", (Object)(String.valueOf(userName) + "\uff0c\u4e0d\u5b58\u5728\uff01"));
            request.getRequestDispatcher("/forgotpwd.jsp").forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        EmailUtils sentemali = new EmailUtils(user, 2);
        sentemali.start();
        request.setAttribute("sendMailMsg", (Object)("\u60a8\u7684\u7533\u8bf7\u5df2\u63d0\u4ea4\u6210\u529f\uff0c\u53ef\u80fd\u4f1a\u6709\u5ef6\u8fdf\uff0c\u8bf7\u7a0d\u540e\u67e5\u770b\u60a8\u7684" + user.getMail() + "\u90ae\u7bb1\u3002"));
        request.getRequestDispatcher("/forgotPwdSuccess.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
