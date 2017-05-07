/*
 * Decompiled with CFR 0_121.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  javax.servlet.ServletException
 *  javax.servlet.ServletOutputStream
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package servlet;

import com.alibaba.fastjson.JSON;
import com.cj.discount.model.Activity;
import com.cj.discount.model.SubAct;
import com.dao.ActivityDao;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CGetOneServlet
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
        ActivityDao dao = new ActivityDao();
        Activity activity = new Activity();
        SubAct subActivity = new SubAct();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = request.getParameter("id");
        try {
            System.out.println(id);
            DataOutputStream output = new DataOutputStream((OutputStream)response.getOutputStream());
            activity = dao.getActivityById(Integer.parseInt(id));
            subActivity.setId(activity.getId());
            subActivity.setName(activity.getName());
            subActivity.setContent(activity.getContent());
            subActivity.setPicture(activity.getPicture());
            subActivity.setType(activity.getType());
            subActivity.setFromDate(sdf.format(activity.getFromDate()));
            subActivity.setToDate(sdf.format(activity.getToDate()));
            subActivity.setZan(activity.getZan());
            String jsonString = JSON.toJSONString((Object)subActivity);
            output.writeUTF(jsonString);
            System.out.println("\u5df2\u7ecf\u627e\u5230");
            output.close();
        }
        catch (Exception e) {
            DataOutputStream output2 = new DataOutputStream((OutputStream)response.getOutputStream());
            System.out.println("failure");
            output2.writeUTF("failure");
            output2.close();
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }
}
