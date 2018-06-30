package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Message;
import cn.edu.cqut.service.message.IMessageService;
import cn.edu.cqut.service.message.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAddMessage", urlPatterns = "/addMessage")
public class ServletAddMessage extends HttpServlet {
    private IMessageService service = new MessageService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String content = request.getParameter("content");
        String createTime = request.getParameter("createTime");
        String moduleIdString = request.getParameter("moduleId");
        Integer moduleId = 0;

        // 判断前台传的值是否为空
        if (!(moduleIdString == null || moduleIdString.equals("") || moduleIdString.equals("undefined"))) {
            moduleId = Integer.parseInt(moduleIdString);
        }

        Message message = new Message();
        message.setContent(content);
        message.setContent(createTime);
        message.setModuleId(moduleId);

        PrintWriter out = response.getWriter();

        out.println(service.addMessage(message));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
