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

/**
 * 新增用户
 * 前台参数
 *
 * employeeName: 用户名字
 * sex: 用户性别
 * password: 密码
 */
@WebServlet(name = "ServletAddEmployee", urlPatterns = {"/addEmployee"})
public class ServletAddEmployee extends HttpServlet {

    private IEmployeeService service = new EmployeeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String employeeName = request.getParameter("employeeName");
        String password = request.getParameter("password");
        String sexString = request.getParameter("sex");
        Integer sex = Sex.UNKNOWN;

        // 判断前台传的值是否为空
        if (!(sexString == null || sexString.equals("") || sexString.equals("undefined"))) {
            sex = Integer.parseInt(sexString);
        }

        Employee employee = new Employee();
        employee.setName(employeeName);
        employee.setSex(sex);
        employee.setPassword(password);

        PrintWriter out = response.getWriter();

        out.println(service.addEmployee(employee));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
