package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Colunm;
import cn.edu.cqut.service.colunm.ColunmService;
import cn.edu.cqut.service.colunm.IColunmService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 新增栏目
 * 前台参数
 *
 * columnName: 栏目名称
 * parentId: 父栏目id
 */
@WebServlet(name = "ServletAddColumn", urlPatterns = {"/addColumn"})
public class ServletAddColumn extends HttpServlet {

    private IColunmService service = new ColunmService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        String columnName = request.getParameter("columnName");
        String parentIdString = request.getParameter("parentId");
        Integer parentId = 0;

        // 判断前台传的值是否为空
        if (!(parentIdString == null || parentIdString.equals("") || parentIdString.equals("undefined"))) {
            parentId = Integer.parseInt(parentIdString);
        }

        Colunm colunm = new Colunm();
        colunm.setName(columnName);
        colunm.setParentId(parentId);
        colunm.setLevel(1);

        PrintWriter out = response.getWriter();

        out.println(service.addColumn(colunm));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
