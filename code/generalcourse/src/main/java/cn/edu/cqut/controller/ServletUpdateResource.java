package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Article;
import cn.edu.cqut.service.Resource.IResourceService;
import cn.edu.cqut.service.Resource.ResourceService;
import cn.edu.cqut.util.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUpdateResource", urlPatterns = {"/updateResource"})
public class ServletUpdateResource extends HttpServlet {

    private IResourceService service = new ResourceService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        // 获取参数
        Integer account = (Integer) request.getSession().
                getAttribute(request.getRequestedSessionId() + "_account");

        String typeString = request.getParameter("type");
        Integer type = 0;
        String resourceIdString = request.getParameter("resourceId");
        Integer resourceId = 0;

        // 判断前台传的值是否为空
        if (!(typeString == null || typeString.equals("") || typeString.equals("undefined"))) {
            type = Integer.parseInt(typeString);
        }

        if (!(resourceIdString == null || resourceIdString.equals("") || resourceIdString.equals("undefined"))) {
            resourceId = Integer.parseInt(resourceIdString);
        }

        Article article = new Article();
        article.setEmployeeId(account);
        article.setId(resourceId);

        switch (type) {
            case 0:// 修改

                String columnIdString = request.getParameter("columnId");
                Integer columnId = 0;
                String whetherTopString = request.getParameter("whetherTop");
                Integer whetherTop = 0;
                String title = request.getParameter("title");
                String content = request.getParameter("content");

                if (!(columnIdString == null || columnIdString.equals("") || columnIdString.equals("undefined"))) {
                    columnId = Integer.parseInt(columnIdString);
                }

                if (!(whetherTopString == null || whetherTopString.equals("") || whetherTopString.equals("undefined"))) {
                    whetherTop = Integer.parseInt(whetherTopString);
                }

                article.setColumnId(columnId);
                article.setWhetherTop(whetherTop);
                article.setTitle(title);
                article.setContent(content);

                break;

            case 1:// 审核

                String statusString = request.getParameter("status");
                Integer status = Status.NOT_AUDIT;

                if (!(statusString == null || statusString.equals("") || statusString.equals("undefined"))) {
                    status = Integer.parseInt(statusString);
                }

                article.setStatus(status);

                break;
        }

        PrintWriter out = response.getWriter();

        out.println(service.updateResource(article));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
