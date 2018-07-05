package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Employee;
import cn.edu.cqut.service.role.IRoleService;
import cn.edu.cqut.service.role.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 分配角色
 */
@WebServlet(name = "ServletAssignRole", urlPatterns = {"/assignRole"})
public class ServletAssignRole extends HttpServlet {

    private IRoleService service = new RoleService();

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

        String roleIdString = request.getParameter("roleId");
        Integer roleId = 0;

        // 判断前台传的值是否为空
        if (!(roleIdString == null || roleIdString.equals("") || roleIdString.equals("undefined"))) {
            roleId = Integer.parseInt(roleIdString);
        }

        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setRoleId(roleId);

        PrintWriter out = response.getWriter();

        out.println(service.assignRole(employee));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
