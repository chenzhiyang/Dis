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

import com.cj.discount.model.User;
import com.dao.UserDao;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BusinessUpdateServlet
extends HttpServlet {
    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        User user = new User();
        UserDao dao = new UserDao();
        HttpSession session = request.getSession();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String address = request.getParameter("address");
            String mail = request.getParameter("mail");
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setType(type);
            user.setAddress(address);
            user.setMail(mail);
            String id = (String)session.getAttribute("b_id");
            user.setId(Integer.parseInt(id));
            boolean existName = dao.usernameCheck(user);
            boolean existUsername = dao.nameCheck(user);
            boolean existMail = dao.mailCheck(user);
            if (existMail) {
                System.out.println("\u8be5\u90ae\u7bb1\u5df2\u6ce8\u518c\u8fc7");
                request.setAttribute("flag1", (Object)"exist");
                request.getRequestDispatcher("/register.jsp").forward((ServletRequest)request, (ServletResponse)response);
            } else if (existName || existUsername) {
                System.out.println("\u7528\u6237\u5df2\u5b58\u5728\uff01");
                request.setAttribute("flag2", (Object)"exist");
                request.getRequestDispatcher("/register.jsp").forward((ServletRequest)request, (ServletResponse)response);
            } else {
                request.setAttribute("flag1", (Object)"unexist");
                request.setAttribute("flag2", (Object)"unexist");
                dao.addUser(user);
                user = dao.getUserByUsername(user.getUsername());
                HttpSession session1 = request.getSession();
                session1.setAttribute("user", (Object)user);
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/login.jsp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }
}
