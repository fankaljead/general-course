package cn.edu.cqut.controller;

import cn.edu.cqut.service.Resource.IResourceService;
import cn.edu.cqut.service.Resource.ResourceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGetResources", urlPatterns = {"/getResources"})
public class ServletGetResources extends HttpServlet {
    private IResourceService service = new ResourceService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        // 从前天获取值
        String columnIdString = request.getParameter("columnId");
        String title = request.getParameter("title");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String pageIndexString = request.getParameter("pageIndex");
        String pageSizeString = request.getParameter("pageSize");

        Integer columnId = 0;
        Integer pageIndex = 0;
        Integer pageSize = 0;

        // 判断前台传的值是否为空
        if (!(columnIdString == null || columnIdString.equals("") || columnIdString.equals("undefined"))) {
            columnId = Integer.parseInt(columnIdString);
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

        out.println(service.getResources(columnId, title, startTime, endTime, pageIndex, pageSize));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
