package cn.edu.cqut.controller;

import cn.edu.cqut.service.employee.EmployeeService;
import cn.edu.cqut.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletLogin", urlPatterns = {"/login"})
public class ServletLogin extends HttpServlet {
    EmployeeService service = new EmployeeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer account = Integer.parseInt(request.getParameter("account"));// 获取用户登录账号
        String password = request.getParameter("password");// 获取用户登录密码

        PrintWriter out = response.getWriter();

        Integer result = service.login(account, password);

        // 登录成功则将 account存入 session
        if (result == Result.SUCCESS) {
            request.getSession().setAttribute(request.getRequestedSessionId() + "_account", account);
        }

        out.println(result);

        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
