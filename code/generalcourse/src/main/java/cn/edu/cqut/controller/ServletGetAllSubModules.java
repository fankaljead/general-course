package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.SubModule;
import cn.edu.cqut.service.module.IModuleService;
import cn.edu.cqut.service.module.ModuleService;
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
 * getAllSubModules 获取所有子模块
 *
 * 	**前台参数:**
 *     - 无
 *
 * 	**后台返回一个JSON数组，包含父模块基本信息和子模块基本信息**
 *
 *     ~~~
 *     [
 *                {
 * 			moduleId: 模块id，
 * 			moduleName: 模块名字，
 * 			parentModuleId: 父模块id，
 * 			status： 是否被禁用
 *        }
 *         ...
 *     ]
 *     ~~~
 */
@WebServlet(name = "ServletGetAllSubModules", urlPatterns = {"/getAllSubModules"})
public class ServletGetAllSubModules extends HttpServlet {
    private IModuleService service = new ModuleService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        JSONArray array = new JSONArray();
        List<SubModule> subModules = service.getAllSubmodules();

        for (SubModule subModule : subModules
             ) {
            JSONObject object = new JSONObject();
            object.put("moduleId", subModule.getId());
            object.put("moduleName", subModule.getName());
            object.put("parentModuleId", subModule.getParentId());
            object.put("status", subModule.getStatus());

            array.add(object);
        }

        out.println(array);

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
