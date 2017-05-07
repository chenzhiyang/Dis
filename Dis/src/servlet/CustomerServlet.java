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

import com.cj.discount.model.Customer;
import com.dao.CustomerDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerServlet
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
        CustomerDao cDao = new CustomerDao();
        HttpSession session = request.getSession();
        try {
            if (request.getParameter("action") != null) {
                this.action = request.getParameter("action");
                if (this.action.equals("delete")) {
                    String id = request.getParameter("id");
                    cDao.deleteCustomerById(Integer.parseInt(id));
                    ArrayList<Customer> list = cDao.getAllCustomer();
                    session.setAttribute("customers", list);
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/customer_manager.jsp");
                } else if (this.action.equals("update")) {
                    Customer customer = new Customer();
                    String id = request.getParameter("id");
                    customer = cDao.getCustomerById(Integer.parseInt(id));
                    session.setAttribute("customer", (Object)customer);
                    session.setAttribute("c_id", (Object)id);
                    response.sendRedirect(String.valueOf(request.getContextPath()) + "/customer_update.jsp");
                }
            } else {
                ArrayList<Customer> list = cDao.getAllCustomer();
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
