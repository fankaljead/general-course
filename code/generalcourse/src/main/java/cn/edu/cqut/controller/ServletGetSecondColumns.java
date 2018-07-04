package cn.edu.cqut.controller;

import cn.edu.cqut.service.colunm.ColunmService;
import cn.edu.cqut.service.colunm.IColunmService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGetSecondColumns", urlPatterns = {"/getSecondColumns"})
public class ServletGetSecondColumns extends HttpServlet {
    private IColunmService service = new ColunmService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        out.println(service.getSecondColumns());

        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
