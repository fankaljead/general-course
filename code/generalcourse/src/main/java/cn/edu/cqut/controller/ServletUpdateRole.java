package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Role;
import cn.edu.cqut.service.role.IRoleService;
import cn.edu.cqut.service.role.RoleService;
import cn.edu.cqut.util.Result;
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
 *     - roleId: 角色id
 *     - roleName: 角色名字
 *     - description: 角色描述
 *     - ownModuleIds: 角色拥有的模块id(一个数组)
 *           ~~~
 * 		     [100000, 100001 ,...]
 * 		     ~~~
 *
 *     **后台返回int**
 *     - result: 成功1，失败0
 */
@WebServlet(name = "ServletUpdateRole", urlPatterns = {"/updateRole"})
public class ServletUpdateRole extends HttpServlet {

    private IRoleService service = new RoleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // 获取前台参数
        String roleName = request.getParameter("roleName");
        String description = request.getParameter("description");
        String roleIdString = request.getParameter("roleId");
        Integer roleId = 0;

        // 判断前台传的值是否为空
        if (!(roleIdString == null || roleIdString.equals("") || roleIdString.equals("undefined"))) {
            roleId = Integer.parseInt(roleIdString);
        } else {
            out.println(Result.FAILED);
        }

        // 获取前台传入的 moduleId数组
        String ownModuleIdsString = request.getParameter("ownModuleIds");

        List<Integer> ownModuleIds = new ArrayList<Integer>();
        JSONArray array = JSONArray.parseArray(ownModuleIdsString);
        for (int i = 0; i < array.size(); i++) {
            ownModuleIds.add(Integer.valueOf(array.getString(i)));
        }

        Role role = new Role();
        role.setName(roleName);
        role.setId(roleId);
        role.setDescription(description);

        // 向前台返回更新结果
        out.println(service.updateRole(role, ownModuleIds));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
