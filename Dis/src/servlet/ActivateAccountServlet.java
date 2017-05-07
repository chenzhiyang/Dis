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

import com.Email.GenerateLinkUtils;
import com.cj.discount.model.User;
import com.dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ActivateAccountServlet
extends HttpServlet {
    private static final long serialVersionUID = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDao dao = new UserDao();
        User user = new User();
        try {
            user = dao.getUserByUsername(username);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        user.setActive(GenerateLinkUtils.verifyCheckcode(user, (ServletRequest)request));
        try {
            dao.updateUserById(user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("user", (Object)user);
        request.getRequestDispatcher("/accountActivateSuccess.jsp").forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
