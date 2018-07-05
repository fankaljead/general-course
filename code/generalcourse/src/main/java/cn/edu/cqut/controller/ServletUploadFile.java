package cn.edu.cqut.controller;

import cn.edu.cqut.dao.BaseDao;
import cn.edu.cqut.dao.EntityDao;
import cn.edu.cqut.pojo.Article;
import cn.edu.cqut.service.Resource.IResourceService;
import cn.edu.cqut.service.Resource.ResourceService;
import cn.edu.cqut.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ServletUploadFile", urlPatterns = {"/uploadFile"})

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class ServletUploadFile extends HttpServlet {
    private IResourceService service = new ResourceService();

    /**
     * 文件保存路径 相对于web项目的路径
     */
    private static final String SAVE_DIR = "uploadFiles";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前web项目的绝对路径
        String appPath = request.getServletContext().getRealPath("");

        // 保存路径
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        // 创建目录
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        Integer isSuccess = Result.FAILED;

        for (Part part : request.getParts()
             ) {
            String fileName = extractFileName(part);
            // 重新命名文件
            fileName = new File(fileName).getName();
            String path = savePath + File.separator + fileName;
            part.write(path);

            // 存入数据库
            cn.edu.cqut.pojo.File pFile = new cn.edu.cqut.pojo.File();
            pFile.setName(fileName);
            System.out.println("savePath:" + savePath);
            path = path.replaceAll("\\\\", "/").replaceAll("//", "/");
            pFile.setPath(path);
            try {
                pFile.setArticleId(EntityDao.getSavedId(Article.class));
                service.uploadFile(pFile);

                isSuccess = Result.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                isSuccess = Result.FAILED;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                isSuccess = Result.FAILED;
            }
        }

        PrintWriter out = response.getWriter();
        out.println(isSuccess);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 从http头部解析文件名字
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
