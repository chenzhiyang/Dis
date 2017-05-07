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

import com.cj.discount.model.Activity;
import com.cj.discount.model.Customer;
import com.cj.discount.model.User;
import com.dao.ActivityDao;
import com.dao.CustomerDao;
import com.dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ActivityServlet
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
        ActivityDao aDao = new ActivityDao();
        UserDao bDao = new UserDao();
        CustomerDao cDao = new CustomerDao();
        HttpSession session = request.getSession();
        Activity activity = new Activity();
        try {
            if (session.getAttribute("user") != null) {
                User user = (User)session.getAttribute("user");
                if (!user.getUsername().equals("admin")) {
                    if (request.getParameter("action") != null) {
                        this.action = request.getParameter("action");
                        if (this.action.equals("delete")) {
                            String id = request.getParameter("id");
                            aDao.deleteActivityById(Integer.parseInt(id));
                            this.setList(user, session, aDao);
                            response.sendRedirect(String.valueOf(request.getContextPath()) + "/activity.jsp");
                        } else if (this.action.equals("update")) {
                            String id = request.getParameter("id");
                            activity = aDao.getActivityById(Integer.parseInt(id));
                            session.setAttribute("activity", (Object)activity);
                            response.sendRedirect(String.valueOf(request.getContextPath()) + "/update.jsp");
                        }
                    } else {
                        this.setList(user, session, aDao);
                        response.sendRedirect(String.valueOf(request.getContextPath()) + "/activity.jsp");
                    }
                } else {
                    ArrayList<Activity> list1 = aDao.getAllActivities();
                    ArrayList<User> list2 = bDao.getAllUsers();
                    ArrayList<Customer> list3 = cDao.getAllCustomer();
                    session.setAttribute("activities", list1);
                    session.setAttribute("users", list2);
                    session.setAttribute("customers", list3);
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/manager.jsp");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }

    private void setList(User user, HttpSession session, ActivityDao dao) throws SQLException {
        ArrayList<Activity> list = dao.getAllActivities(user);
        session.setAttribute("activities", list);
    }
}
