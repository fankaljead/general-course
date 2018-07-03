package cn.edu.cqut.controller;

import cn.edu.cqut.service.employee.EmployeeService;
import cn.edu.cqut.service.employee.IEmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletDeleteByEmployeeId", urlPatterns = {"/deleteByEmployeeId"})
public class ServletDeleteByEmployeeId extends HttpServlet {
    private IEmployeeService service = new EmployeeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");


        String employeeIdString = request.getParameter("employeeId");
        Integer employeeId = 0;

        // 判断前台传的值是否为空
        if (!(employeeIdString == null || employeeIdString.equals("") || employeeIdString.equals("undefined"))) {
            employeeId = Integer.parseInt(employeeIdString);
        }

        PrintWriter out = response.getWriter();

        out.println(service.deleteByEmployeeId(employeeId));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
