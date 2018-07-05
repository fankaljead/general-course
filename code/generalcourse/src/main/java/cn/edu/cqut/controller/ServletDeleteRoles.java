package cn.edu.cqut.controller;

import cn.edu.cqut.service.role.IRoleService;
import cn.edu.cqut.service.role.RoleService;
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

/**
 *     **前台参数**
 *     - roleIds: 被删除角色的id
 *      ~~~
 * 		[100000, 100001 ,...]
 * 		~~~
 *
 *     **后台返回int**
 *     - result: 成功1，失败0
 */
@WebServlet(name = "ServletDeleteRoles", urlPatterns = {"/deleteRoles"})
public class ServletDeleteRoles extends HttpServlet {
    private IRoleService service = new RoleService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String roleIdsString = request.getParameter("roleIds");

        List<Integer> roleIds = new ArrayList<Integer>();
        JSONArray array = JSONArray.parseArray(roleIdsString);
        for (int i = 0; i < array.size(); i++) {
            roleIds.add(Integer.valueOf(array.getString(i)));
        }

        out.println(service.deleteRoles(roleIds));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
