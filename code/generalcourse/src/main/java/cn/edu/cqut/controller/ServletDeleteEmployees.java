package cn.edu.cqut.controller;

import cn.edu.cqut.service.employee.EmployeeService;
import cn.edu.cqut.service.employee.IEmployeeService;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 删除员工
 * 前台传递参数
 * employeeIds:
 * 		[
 *             {
 * 				employeeId: 0,
 *            },
 *            {
 * 				employeeId: 1,
 *            }
 * 			...
 * 		]
 */
@WebServlet(name = "ServletDeleteEmployees", urlPatterns = {"/deleteEmployees"})
public class ServletDeleteEmployees extends HttpServlet {

    private IEmployeeService service = new EmployeeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String messageIdsString = request.getParameter("employeeIds");

        JSONArray array = JSONArray.parseArray(messageIdsString);

        PrintWriter out = response.getWriter();

        out.println(service.deleteEmployees(array));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
