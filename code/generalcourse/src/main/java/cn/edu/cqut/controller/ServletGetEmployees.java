package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Employee;
import cn.edu.cqut.service.employee.EmployeeService;
import cn.edu.cqut.service.employee.IEmployeeService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 获取人员
 * 前台参数
 * - roleId: (0 只有角色id为0的才是没有被分配角色的， 可选)
 *     - pageIndex: 当前页 (可选)
 *     - pageSize: 页面大小 (可选)
 *     - condition: 查询条件 (可选)
 */
@WebServlet(name = "ServletGetEmployees", urlPatterns = {"/getEmployees"})
public class ServletGetEmployees extends HttpServlet {

    private IEmployeeService service = new EmployeeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String roleIdString = request.getParameter("roleId");
        String pageIndexString = request.getParameter("pageIndex");
        String pageSizeString = request.getParameter("pageSize");
        String condition = request.getParameter("condition");

        Integer roleId = 0;
        Integer pageIndex = 0;
        Integer pageSize = 0;

        // 判断前台传的值是否为空
        if (!(roleIdString == null || roleIdString.equals("") || roleIdString.equals("undefined"))) {
            roleId = Integer.parseInt(roleIdString);
        }

        // 判断前台传的值是否为空
        if (!(pageIndexString == null || pageIndexString.equals("") || pageIndexString.equals("undefined"))) {
            pageIndex= Integer.parseInt(pageIndexString);
        }

        // 判断前台传的值是否为空
        if (!(pageSizeString == null || pageSizeString.equals("") || pageSizeString.equals("undefined"))) {
            pageSize = Integer.parseInt(pageSizeString);
        }

        PrintWriter out = response.getWriter();

        JSONArray array = new JSONArray(service.getEmployees(roleId, pageIndex, pageSize, condition));

        out.println(array);

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
