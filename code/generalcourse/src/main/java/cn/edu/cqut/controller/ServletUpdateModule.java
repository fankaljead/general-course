package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.SubModule;
import cn.edu.cqut.service.module.IModuleService;
import cn.edu.cqut.service.module.ModuleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUpdateModule")
public class ServletUpdateModule extends HttpServlet {

    private IModuleService service = new ModuleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String moduleIdString = request.getParameter("moduleId");
        Integer moduleId = 0;

        // 判断前台传的值是否为空
        if (!(moduleIdString == null || moduleIdString.equals("") || moduleIdString.equals("undefined"))) {
            moduleId = Integer.parseInt(moduleIdString);
        }

        String parentModuleIdString = request.getParameter("parentModuleId");
        Integer parentModuleId = 0;

        // 判断前台传的值是否为空
        if (!(parentModuleIdString == null || parentModuleIdString.equals("") || parentModuleIdString.equals("undefined"))) {
            parentModuleId = Integer.parseInt(parentModuleIdString);
        }

        String statusString = request.getParameter("status");
        Integer status = 0;

        // 判断前台传的值是否为空
        if (!(statusString == null || statusString.equals("") || statusString.equals("undefined"))) {
            status = Integer.parseInt(statusString);
        }

        String moduleName = request.getParameter("moduleName");

        SubModule module = new SubModule();
        module.setId(moduleId);
        module.setName(moduleName);
        module.setStatus(status);
        module.setParentId(parentModuleId);

        PrintWriter out = response.getWriter();

        out.println(service.updateModule(module));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
