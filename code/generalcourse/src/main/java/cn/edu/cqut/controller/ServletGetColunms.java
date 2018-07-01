package cn.edu.cqut.controller;

import cn.edu.cqut.service.colunm.ColunmService;
import cn.edu.cqut.service.colunm.IColunmService;
import cn.edu.cqut.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGetColunms", urlPatterns = {"/getColumns"})
public class ServletGetColunms extends HttpServlet {

    IColunmService service = new ColunmService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        try {
            String levelString = request.getParameter("level");
            String columnIdString = request.getParameter("columnId");

            Integer level = 0;
            Integer columnId = 0;

            // 判断前台传的值是否为空
            if (!(levelString == null || levelString.equals("") || levelString.equals("undefined"))) {
                level = Integer.parseInt(levelString);
            }

            // 判断前台传的值是否为空
            if (!(columnIdString == null || columnIdString.equals("") || columnIdString.equals("undefined"))) {
                columnId = Integer.parseInt(columnIdString);
            }

            out.println(service.getColunms(level, columnId));

        } catch (NumberFormatException e){
            out.println(Result.FAILED);
        }

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
