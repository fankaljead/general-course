package cn.edu.cqut.controller;

import cn.edu.cqut.service.message.IMessageService;
import cn.edu.cqut.service.message.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGetMessages", urlPatterns = {"/getMessages"})
public class ServletGetMessages extends HttpServlet {

    private IMessageService service = new MessageService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String statusString = request.getParameter("status");
        String pageIndexString = request.getParameter("pageIndex");
        String pageSizeString = request.getParameter("pageSize");

        Integer status = 2;
        Integer pageIndex = 0;
        Integer pageSize = 0;

        // 判断前台传的值是否为空
        if (!(statusString == null || statusString.equals("") || statusString.equals("undefined"))) {
            status = Integer.parseInt(statusString);
        }

        // 判断前台传的值是否为空
        if (!(pageIndexString == null || pageIndexString.equals("") || pageIndexString.equals("undefined"))) {
            pageIndex= Integer.parseInt(pageIndexString);
        }

        // 判断前台传的值是否为空
        if (!(pageSizeString == null || pageSizeString.equals("") || pageSizeString.equals("undefined"))) {
            pageSize = Integer.parseInt(pageSizeString);
        }

        PrintWriter out = response.getWriter();

        out.println(service.getMessages(pageIndex, pageSize, status));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
