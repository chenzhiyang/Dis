/*
 * Decompiled with CFR 0_121.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 */
package servlet;

import com.cj.discount.model.User;
import com.dao.UserDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BusinessServlet
extends HttpServlet {
    private String action;

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        UserDao bDao = new UserDao();
        HttpSession session = request.getSession();
        try {
            if (request.getParameter("action") != null) {
                this.action = request.getParameter("action");
                if (this.action.equals("delete")) {
                    String id = request.getParameter("id");
                    bDao.deleteBusinessById(Integer.parseInt(id));
                    ArrayList<User> list = bDao.getAllUsers();
                    session.setAttribute("users", list);
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/business_manager.jsp");
                } else if (this.action.equals("update")) {
                    User user = new User();
                    String id = request.getParameter("id");
                    user = bDao.getUserById(Integer.parseInt(id));
                    session.setAttribute("b_id", (Object)id);
                    session.setAttribute("business", (Object)user);
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/business_update.jsp");
                }
            } else {
                ArrayList<User> list = bDao.getAllUsers();
                session.setAttribute("users", list);
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/business_manager.jsp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }
}
