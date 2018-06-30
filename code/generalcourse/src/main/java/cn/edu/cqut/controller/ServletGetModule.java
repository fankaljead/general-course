package cn.edu.cqut.controller;

import cn.edu.cqut.service.module.IModuleService;
import cn.edu.cqut.service.module.ModuleService;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGetModule", urlPatterns = {"/getModule"})
public class ServletGetModule extends HttpServlet {
    private IModuleService service = new ModuleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        Integer account = (Integer) request.getSession().
                getAttribute(request.getRequestedSessionId() + "_account");

        System.out.println("account:" + account);

        JSONArray jsonArray = service.getModule(account);

        PrintWriter out = response.getWriter();

        out.println(jsonArray);

        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
