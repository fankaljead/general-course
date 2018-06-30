package cn.edu.cqut.controller;

import cn.edu.cqut.service.Resource.IResourceService;
import cn.edu.cqut.service.Resource.ResourceService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGetResourceContent", urlPatterns = {"/getResourceContent"})
public class ServletGetResourceContent extends HttpServlet {

    private IResourceService service = new ResourceService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String resourceIdString = request.getParameter("resourceId");

        Integer resourceId = 0;
        // 判断前台传的值是否为空
        if (!(resourceIdString == null || resourceIdString.equals("") || resourceIdString.equals("undefined"))) {
            resourceId = Integer.parseInt(resourceIdString);
        }

        JSONObject object = service.getResourceContent(resourceId);

        PrintWriter out = response.getWriter();

        out.println(object);

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
