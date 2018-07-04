package cn.edu.cqut.controller;

import cn.edu.cqut.pojo.File;
import cn.edu.cqut.service.Resource.IResourceService;
import cn.edu.cqut.service.Resource.ResourceService;
import cn.edu.cqut.util.Result;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "ServletDownloadFile", urlPatterns = {"/downloadFile"})
public class ServletDownloadFile extends HttpServlet {

    private IResourceService service = new ResourceService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String fileIdString = request.getParameter("fileId");
        Integer fileId = 0;

        // 判断前台传的值是否为空
        if (!(fileIdString == null || fileIdString.equals("") || fileIdString.equals("undefined"))) {
            fileId = Integer.parseInt(fileIdString);
        }

        File file = service.downloadFile(fileId);

        // 从路径读取文件
        String filePath = file.getPath();
        java.io.File downloadFile = new java.io.File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

        System.out.println("filePath:" + filePath);

        // 使用相对路径
        String relativePath = getServletContext().getRealPath("");

        // 获取context对象
        ServletContext context = getServletContext();

        // 获取文件类型
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // 设为二进制
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // 修改response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // 强迫下载
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // 获取 response 中的 输出流
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
