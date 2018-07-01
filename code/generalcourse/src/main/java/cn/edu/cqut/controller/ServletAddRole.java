package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Role;
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
 *     - roleName: 角色名字
 *     - createTime: 创建时间
 *     - description: 角色描述
 *     - ownModuleIds: 角色拥有的模块id(一个数组)
 *
 *     **后台返回int**
 *     - result: 成功1，失败0
 */
@WebServlet(name = "ServletAddRole", urlPatterns = {"/addRole"})
public class ServletAddRole extends HttpServlet {
    private IRoleService service = new RoleService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String roleName = request.getParameter("roleName");
        String createTime = request.getParameter("createTime");
        String description = request.getParameter("description");
        String ownModuleIdsString = request.getParameter("ownModuleIds");

        List<Integer> ownModuleIds = new ArrayList<Integer>();
        JSONArray array = JSONArray.parseArray(ownModuleIdsString);
        for (int i = 0; i < array.size(); i++) {
            ownModuleIds.add(Integer.valueOf(array.getString(i)));
        }

        Role role = new Role();
        role.setName(roleName);
        role.setCreateTime(createTime);
        role.setDescription(description);

        PrintWriter out = response.getWriter();

        out.println(service.addRole(role, ownModuleIds));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
