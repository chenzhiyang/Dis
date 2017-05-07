/*
 * Decompiled with CFR 0_121.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.ServletOutputStream
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package servlet;

import com.dao.CollectionDao;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CCollectServlet
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
        CollectionDao dao = new CollectionDao();
        String a_id = request.getParameter("a_id");
        String c_id = request.getParameter("c_id");
        String isCollected = request.getParameter("flag");
        try {
            DataOutputStream output = new DataOutputStream((OutputStream)response.getOutputStream());
            if (isCollected.equals("true") || dao.isCollected(Integer.parseInt(a_id), Integer.parseInt(c_id))) {
                dao.deleteCollextion(Integer.parseInt(a_id), Integer.parseInt(c_id));
                System.out.println("\u5df2\u53d6\u6d88\u6536\u85cf");
                output.writeUTF("remove");
                output.close();
            } else if (isCollected.equals("false")) {
                dao.addCollection(Integer.parseInt(a_id), Integer.parseInt(c_id));
                System.out.println("\u6210\u529f\u6536\u85cf");
                output.writeUTF("done");
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
