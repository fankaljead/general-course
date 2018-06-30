package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Role;
import cn.edu.cqut.service.role.IRoleService;
import cn.edu.cqut.service.role.RoleService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGetRoleByAccount", urlPatterns = {"/getRoleByAccount"})
public class ServletGetRoleByAccount extends HttpServlet {
    private IRoleService service = new RoleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        Integer account = (Integer) request.getSession().
                getAttribute(request.getRequestedSessionId() + "_account");

        PrintWriter out = response.getWriter();

        Role role = service.getRoleByAccount(account);

        JSONObject object = new JSONObject();
        object.put("roleId", role.getId());
        object.put("roleName", role.getName());
        object.put("createTime", role.getCreateTime());
        object.put("description", role.getDescription());

        out.println(object);

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
