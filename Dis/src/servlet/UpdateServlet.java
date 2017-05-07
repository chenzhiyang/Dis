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
import com.cj.discount.model.User;
import com.dao.ActivityDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateServlet
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
        Activity activity = new Activity();
        ActivityDao aDao = new ActivityDao();
        HttpSession session = request.getSession();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromTime = null;
        Date toTime = null;
        java.sql.Date fromDate = null;
        java.sql.Date toDate = null;
        String activityName = request.getParameter("activityName");
        String content = request.getParameter("content");
        try {
            fromTime = sdf.parse(request.getParameter("fromTime"));
            toTime = sdf.parse(request.getParameter("toTime"));
            fromDate = new java.sql.Date(fromTime.getTime());
            toDate = new java.sql.Date(toTime.getTime());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            if (request.getSession().getAttribute("user") != null) {
                user = (User)request.getSession().getAttribute("user");
                if (request.getSession().getAttribute("activity") != null) {
                    activity = (Activity)request.getSession().getAttribute("activity");
                    activity.setFromDate(fromDate);
                    activity.setToDate(toDate);
                    activity.setName(activityName);
                    activity.setContent(content);
                    activity.setType("\u5ba1\u6838\u4e2d");
                    aDao.updateActivity(activity);
                    ArrayList<Activity> list = aDao.getAllActivities(user);
                    session.setAttribute("activities", list);
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/activity.jsp");
                }
            } else {
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/activity.jsp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }
}
