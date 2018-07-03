package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Employee;
import cn.edu.cqut.service.employee.EmployeeService;
import cn.edu.cqut.service.employee.IEmployeeService;
import cn.edu.cqut.util.Sex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUpdateEmployee", urlPatterns = {"/updateEmployee"})
public class ServletUpdateEmployee extends HttpServlet {
    private IEmployeeService service = new EmployeeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String employeeName = request.getParameter("employeeName");
        String employeeIdString = request.getParameter("employeeId");
        String sexString = request.getParameter("sex");
        Integer sex = Sex.UNKNOWN;
        Integer employeeId = 0;

        // 判断前台传的值是否为空
        if (!(sexString == null || sexString.equals("") || sexString.equals("undefined"))) {
            sex = Integer.parseInt(sexString);
        }

        // 判断前台传的值是否为空
        if (!(employeeIdString == null || employeeIdString.equals("") || employeeIdString.equals("undefined"))) {
            employeeId = Integer.parseInt(employeeIdString);
        }

        Employee employee = new Employee();
        employee.setName(employeeName);
        employee.setId(employeeId);

        PrintWriter out = response.getWriter();

        out.println(service.updateEmployee(employee));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
