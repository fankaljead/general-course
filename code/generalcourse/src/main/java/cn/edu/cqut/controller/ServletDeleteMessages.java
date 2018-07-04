package cn.edu.cqut.controller;

import cn.edu.cqut.service.message.IMessageService;
import cn.edu.cqut.service.message.MessageService;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletDeleteMessages", urlPatterns = {"/deleteMessages"})
public class ServletDeleteMessages extends HttpServlet {
    private IMessageService service = new MessageService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String messageIdsString = request.getParameter("messageIds");

        PrintWriter out = response.getWriter();
        List<Integer> messageIds = new ArrayList<Integer>();
        JSONArray array = JSONArray.parseArray(messageIdsString);

        for (int i = 0; i < array.size(); i++) {
            messageIds.add(Integer.valueOf(array.getString(i)));
        }
        
        out.println(service.deleteMessages(messageIds));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
