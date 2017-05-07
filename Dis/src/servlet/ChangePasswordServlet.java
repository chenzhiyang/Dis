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
 *  javax.servlet.http.HttpSession
 */
package servlet;

import com.Email.GenerateLinkUtils;
import com.cj.discount.model.User;
import com.dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value={"/servlet/ChangePasswordServlet"})
public class ChangePasswordServlet
extends HttpServlet {
    private static final long serialVersionUID = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameter("newPassword2");
        String CHECK_CODE = (String)request.getSession().getAttribute("CHECK_CODE");
        request.setAttribute("checkCode", (Object)CHECK_CODE);
        HashMap<String, String> errors = new HashMap<String, String>();
        if (newPassword == null || "".equals(newPassword)) {
            errors.put("newPassword", "\u65b0\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a\uff01");
        }
        if (newPassword2 == null || "".equals(newPassword2)) {
            errors.put("newPassword2", "\u786e\u8ba4\u65b0\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a\uff01");
        }
        if (!newPassword.equals(newPassword2)) {
            errors.put("passwordError", "\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4\uff01");
        }
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/servlet/ResetPasswordServlet?userName=" + userName).forward((ServletRequest)request, (ServletResponse)response);
            return;
        }
        UserDao userDao = new UserDao();
        User user = new User();
        try {
            user = userDao.getUserByUsername(userName);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if (GenerateLinkUtils.verifyCheckcode2(user, CHECK_CODE)) {
            user.setPassword(newPassword);
            try {
                userDao.updateUserById(user);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/resetpwdsuccess.jsp").forward((ServletRequest)request, (ServletResponse)response);
        } else {
            request.setAttribute("message", (Object)"\u5bc6\u7801\u66f4\u6539\u9519\u8bef");
            RequestDispatcher rd = request.getRequestDispatcher("/message.jsp");
            rd.forward((ServletRequest)request, (ServletResponse)response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
