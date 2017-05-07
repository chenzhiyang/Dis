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
import com.cj.discount.model.Customer;
import com.dao.CustomerDao;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CLoginServlet
extends HttpServlet {
    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String LOGIN_FLAG = "";
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Customer user = new Customer();
        user.setUsername(username);
        user.setPassword(password);
        CustomerDao dao = new CustomerDao();
        try {
            boolean isLogin = dao.logoin(user);
            DataOutputStream output = new DataOutputStream((OutputStream)response.getOutputStream());
            if (isLogin) {
                LOGIN_FLAG = "success";
                user = dao.getCustomerByUsername(username);
                String jsonString = JSON.toJSONString((Object)user);
                output.writeUTF(jsonString);
                System.out.println(LOGIN_FLAG);
                output.writeInt(1);
                output.close();
            } else {
                LOGIN_FLAG = "failure";
                System.out.println(LOGIN_FLAG);
                output.writeUTF(LOGIN_FLAG);
                output.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }
}
