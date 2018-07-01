package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Role;
import cn.edu.cqut.service.role.IRoleService;
import cn.edu.cqut.service.role.RoleService;
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
 *     **前台参数**
 *     - 无
 *
 *     **后台返回JSON数组**
 *     - roleId: 角色id
 *     - roleName: 角色名称
 *     - createTime: 角色被创建时间
 *     - description: 角色的描述
 */
@WebServlet(name = "ServletGetRoles", urlPatterns = {"/getRoles"})
public class ServletGetRoles extends HttpServlet {

    private IRoleService service = new RoleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        List<Role> roles = service.getRoles();

        JSONArray array = new JSONArray();

        for (Role role: roles
             ) {
            JSONObject object = new JSONObject();
            object.put("roleId", role.getId());
            object.put("roleName", role.getName());
            object.put("createTime", role.getCreateTime());
            object.put("description", role.getDescription());

            array.add(object);
        }

        PrintWriter out = response.getWriter();

        out.println(array);

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
