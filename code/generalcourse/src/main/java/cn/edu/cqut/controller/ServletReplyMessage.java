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

@WebServlet(name = "ServletReplyMessage", urlPatterns = {"/replyMessage"})
public class ServletReplyMessage extends HttpServlet {

    private IMessageService service = new MessageService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String reply = request.getParameter("reply");
        String replyTime = request.getParameter("replyTime");
        String messageIdString = request.getParameter("messageId");
        Integer account = (Integer) request.getSession().
                getAttribute(request.getRequestedSessionId() + "_account");
        Integer messageId = 0;

        // 判断前台传的值是否为空
        if (!(messageIdString == null || messageIdString.equals("") || messageIdString.equals("undefined"))) {
            messageId = Integer.parseInt(messageIdString);
        }

        Message message = new Message();
        message.setReply(reply);
        message.setReplyTime(replyTime);
        message.setEmployeeId(account);
        message.setId(messageId);

        PrintWriter out = response.getWriter();

        out.println(service.replyMessage(message));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
