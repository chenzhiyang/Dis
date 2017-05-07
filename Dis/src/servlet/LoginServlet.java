/*
 * Decompiled with CFR 0_121.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 */
package servlet;

import com.cj.discount.model.Activity;
import com.cj.discount.model.User;
import com.dao.ActivityDao;
import com.dao.UserDao;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet
extends HttpServlet {
    private static final long serialVersionUID = 3091463350951773552L;

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserDao dao = new UserDao();
        ActivityDao aDao = new ActivityDao();
        Date currentTime = new Date();
		ArrayList<Activity> list = new ArrayList<Activity>();
        try {
            HttpSession session;
            list = aDao.getAllActivities();
            for (Activity a :list) {	
                if (!currentTime.after(a.getToDate())) continue;
                aDao.updateType(a);
            }
            String isLogin = dao.logoin(user);
            System.out.println(isLogin);
            if (isLogin == "true") {
                user = dao.getUserByUsername(user.getUsername());
                session = request.getSession();
                session.setAttribute("user", (Object)user);
                request.getRequestDispatcher("/servlet/ActivityServlet").forward((ServletRequest)request, (ServletResponse)response);
            } else if (isLogin == "noactive") {
                session = request.getSession();
                session.setAttribute("message", (Object)("\u7528\u6237\u672a\u6fc0\u6d3b\uff0c\u8bf7\u70b9\u51fb\u94fe\u63a5 \u91cd\u65b0\u53d1\u9001\u90ae\u4ef6\uff0c\u5230\u90ae\u7bb1\u786e\u5b9a\u6fc0\u6d3b<a href='http://192.168.10.1:8080/Dis/servlet/ReSentEmailServlet?username=" + username + "'>\u70b9\u51fb\u53d1\u9001\u90ae\u4ef6</a><br><a href='http://192.168.10.1:8080/Dis/login.jsp'>\u8fd4\u56de\u767b\u5f55</a>"));
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/message.jsp");
            } else {
                session = request.getSession();
                session.setAttribute("eFlag", (Object)"pswerror");
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/login.jsp");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }
}
