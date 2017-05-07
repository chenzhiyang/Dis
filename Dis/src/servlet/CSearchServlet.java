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
import com.dao.ActivityDao;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CSearchServlet
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
        String keywords = request.getParameter("keywords");
        String jy = request.getParameter("jy");
        try {
            System.out.println(keywords);
            System.out.println(jy);
            DataOutputStream output = new DataOutputStream((OutputStream)response.getOutputStream());
            if (dao.canFindActivities(keywords)) {
                ArrayList<Activity> list = dao.findActivities(keywords);
                System.out.println("success");
                String jsonString = JSON.toJSONString(list);
                output.writeUTF(jsonString);
            } else {
                System.out.println("\u6ca1\u627e\u5230");
                output.writeUTF("failure");
            }
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
