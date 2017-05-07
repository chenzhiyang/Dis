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

import com.cj.discount.model.Customer;
import com.dao.CustomerDao;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerUpdateServlet
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
        HttpSession session = request.getSession();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            String id = (String)session.getAttribute("c_id");
            user.setId(Integer.parseInt(id));
            boolean existName = dao.usernameCheck(user);
            boolean existUsername = dao.nameCheck(user);
            if (existName || existUsername) {
                System.out.println("\u7528\u6237\u540d\u6216\u6635\u79f0\u5df2\u5b58\u5728\uff01");
                request.setAttribute("cflag", (Object)"exist");
                request.getRequestDispatcher("/customer_update.jsp").forward((ServletRequest)request, (ServletResponse)response);
            } else {
                request.setAttribute("cflag", (Object)"unexist");
                dao.updateCustomerById(user);
                ArrayList<Customer> list = dao.getAllCustomer();
                session.setAttribute("customers", list);
                response.sendRedirect(String.valueOf(request.getContextPath()) + "/customer_manager.jsp");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws ServletException {
    }
}
