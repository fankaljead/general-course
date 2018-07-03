package cn.edu.cqut.controller;

import cn.edu.cqut.service.Resource.IResourceService;
import cn.edu.cqut.service.Resource.ResourceService;
import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * deleteResources 删除资源
 * 前台参数
 *
 * resourceIds: 所选要删除资源的id
 * 后台返回int
 *
 * result: 成功1，失败0
 */
@WebServlet(name = "ServletDeleteResources", urlPatterns = {"/deleteResources"})
public class ServletDeleteResources extends HttpServlet {
    private IResourceService service = new ResourceService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String resourceIdsString = request.getParameter("resourceIds");
        System.out.println("resource id:" + resourceIdsString);

        List<Integer> resourceIds = new ArrayList<Integer>();
        JSONArray array = JSONArray.parseArray(resourceIdsString);
        for (int i = 0; i < array.size(); i++) {
            resourceIds.add(Integer.valueOf(array.getString(i)));
        }

        out.println(service.deleteResources(resourceIds));

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
