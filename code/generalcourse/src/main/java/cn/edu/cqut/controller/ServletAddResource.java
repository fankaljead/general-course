package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.Article;
import cn.edu.cqut.service.Resource.IResourceService;
import cn.edu.cqut.service.Resource.ResourceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAddResource", urlPatterns = {"/addResource"})
public class ServletAddResource extends HttpServlet {
    private IResourceService service = new ResourceService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");

        // 获取前台参数
        Integer account = (Integer) request.getSession().
                getAttribute(request.getRequestedSessionId() + "_account");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String createTime = request.getParameter("createTime");
        Integer columnId = Integer.valueOf(request.getParameter("columnId"));
        Integer whetherTop = Integer.valueOf(request.getParameter("whetherTop"));

        // 生成 article 对象
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateTime(createTime);
        article.setEmployeeId(account);
        article.setColumnId(columnId);
        article.setWhetherTop(whetherTop);

        PrintWriter out = response.getWriter();

        // 向前台返回是否新增成功
        out.println(service.addResource(article));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
