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

import com.Email.EmailUtils;
import com.cj.discount.model.User;
import com.dao.UserDao;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegServlet
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
        String address = "null";
        UserDao dao = new UserDao();
        HashMap<String, String> errors = new HashMap<String, String>();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            address = request.getParameter("address");
            String mail = request.getParameter("mail");
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setType(type);
            user.setAddress(address);
            user.setMail(mail);
            user.setActive(false);
            boolean existName = dao.usernameCheck(user);
            boolean existUsername = dao.nameCheck(user);
            boolean existMail = dao.mailCheck(user);
            if (mail == null || "".equals(mail)) {
                errors.put("email", "email\u4e0d\u80fd\u4e3a\u7a7a!");
            } else if (mail != null && !mail.matches("[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\\.[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-])*")) {
                errors.put("email", "email\u683c\u5f0f\u4e0d\u6b63\u786e!");
            }
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
                if (user != null) {
                    dao.addUser(user);
                    EmailUtils sentemali = new EmailUtils(user, 1);
                    sentemali.start();
                    request.getSession().setAttribute("user", (Object)user);
                    request.getRequestDispatcher("/registerSuccess.jsp").forward((ServletRequest)request, (ServletResponse)response);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", (Object)"\u6ce8\u518c\u5931\u8d25\uff01\uff01");
            request.getRequestDispatcher("/message.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
    }

    public void init() throws ServletException {
    }
}
