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

@WebServlet(name = "ServletGetPermissionByRoleId", urlPatterns = {"/getPermissionByRoleId"})
public class ServletGetPermissionByRoleId extends HttpServlet {
    private IRoleService service = new RoleService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");


        String roleIdString = request.getParameter("roleId");
        System.out.println("roleId:"+roleIdString);
        Integer roleId = 0;

        // 判断前台传的值是否为空
        if (!(roleIdString == null || roleIdString.equals("") || roleIdString.equals("undefined"))) {
            roleId = Integer.parseInt(roleIdString);
        }

        PrintWriter out = response.getWriter();
        JSONArray array = service.getPermissionsByRoleId(roleId);
        System.out.println(array);
        out.println(array);

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
