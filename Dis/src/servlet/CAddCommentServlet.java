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

import com.dao.CommentDao;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CAddCommentServlet
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
        CommentDao dao = new CommentDao();
        String a_id = request.getParameter("a_id");
        String c_id = request.getParameter("c_id");
        String content = request.getParameter("content");
        try {
            DataOutputStream output = new DataOutputStream((OutputStream)response.getOutputStream());
            dao.addComment(Integer.parseInt(a_id), Integer.parseInt(c_id), content);
            System.out.println("\u8bc4\u8bba\u6210\u529f");
            output.writeUTF("success");
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
