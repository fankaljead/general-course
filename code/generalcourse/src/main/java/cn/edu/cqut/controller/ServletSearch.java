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

@WebServlet(name = "ServletSearch", urlPatterns = {"/search"})
public class ServletSearch extends HttpServlet {
    private IResourceService service = new ResourceService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String keyWords = request.getParameter("keyWords");

        PrintWriter out = response.getWriter();

        out.println(service.search(keyWords));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
