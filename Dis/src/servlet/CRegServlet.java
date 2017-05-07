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
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CRegServlet
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
        Customer user = new Customer();
        CustomerDao dao = new CustomerDao();
        try {
            DataOutputStream output = new DataOutputStream((OutputStream)response.getOutputStream());
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            user.setUsername(username);
            user.setPassword(password);
            boolean existName = dao.usernameCheck(user);
            if (existName) {
                System.out.println("\u7528\u6237\u5df2\u5b58\u5728\uff01");
                String existFlag = "failure";
                output.writeUTF(existFlag);
                System.out.println(existFlag);
                output.close();
            } else {
                dao.addUser(user);
                String existFlag = "success";
                user = dao.getCustomerByUsername(username);
                String jsonString = JSON.toJSONString((Object)user);
                output.writeUTF(jsonString);
                System.out.println(existFlag);
                output.writeInt(1);
                output.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }
}
