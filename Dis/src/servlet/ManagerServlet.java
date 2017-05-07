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
import com.dao.ActivityDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerServlet
extends HttpServlet {
    private String action;

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ActivityDao dao = new ActivityDao();
        HttpSession session = request.getSession();
        try {
            if (request.getParameter("action") != null) {
                this.action = request.getParameter("action");
                if (this.action.equals("pass")) {
                    String id = request.getParameter("id");
                    if (!dao.getActivityById(Integer.parseInt(id)).getType().equals("\u5ba1\u6838\u4e2d")) {
                        session.setAttribute("action", (Object)"noAction");
                        response.sendRedirect(String.valueOf(request.getContextPath()) + "/activity_manager.jsp");
                    } else {
                        session.setAttribute("action", (Object)"action");
                        dao.passActivityById(Integer.parseInt(id));
                        ArrayList<Activity> list = dao.getAllActivities();
                        session.setAttribute("activities", list);
                        response.sendRedirect(String.valueOf(request.getContextPath()) + "/activity_manager.jsp");
                    }
                } else if (this.action.equals("reject")) {
                    String id = request.getParameter("id");
                    session.setAttribute("action", (Object)"action");
                    dao.rejectActivityById(Integer.parseInt(id));
                    ArrayList<Activity> list = dao.getAllActivities();
                    session.setAttribute("activities", list);
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/activity_manager.jsp");
                } else if (this.action.equals("delete")) {
                    String id = request.getParameter("id");
                    session.setAttribute("action", (Object)"action");
                    dao.deleteActivityById(Integer.parseInt(id));
                    ArrayList<Activity> list = dao.getAllActivities();
                    session.setAttribute("activities", list);
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/activity_manager.jsp");
                }
            } else {
                ArrayList<Activity> list = dao.getAllActivities();
                session.setAttribute("activities", list);
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/activity_manager.jsp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }
}
