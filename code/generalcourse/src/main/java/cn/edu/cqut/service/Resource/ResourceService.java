package cn.edu.cqut.service.Resource;

import cn.edu.cqut.dao.EntityDao;
import cn.edu.cqut.dao.ResourceDao;
import cn.edu.cqut.pojo.Article;
import cn.edu.cqut.util.DBUtil;
import cn.edu.cqut.util.Result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 20:46
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 资源操作 包括文章和文件
 **/
public class ResourceService implements IResourceService{

    private ResourceDao dao = new ResourceDao();

    /**
     * 添加资源 文章
     * @param article
     * @return Result.SUCCESS 成功 Result.FAILED 失败
     */
    public Integer addResource(Article article) {

        try {
            EntityDao.save(article);

            return Result.SUCCESS;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Result.FAILED;
    }

    /**
     * 获取资源
     * @param columnId
     * @param title
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public JSONArray getResources(Integer columnId, String title, String startTime, String endTime, Integer pageIndex, Integer pageSize) {
        JSONArray array = new JSONArray();

        String sql = dao.getResources(columnId, title, startTime, endTime, pageIndex, pageSize);

        try {
            ResultSet resultSet = DBUtil.query(sql);

            while (resultSet.next()) {
                JSONObject object = new JSONObject();

                object.put("resourceId", resultSet.getInt("article.id"));
                object.put("title", resultSet.getString("title"));
                object.put("createTime", resultSet.getString("createTime"));
                object.put("whetherTop", resultSet.getInt("whetherTop"));
                object.put("status", resultSet.getInt("status"));
                object.put("columnId", resultSet.getInt("columnId"));
                object.put("columnName", resultSet.getString("colunm.name"));

                array.add(object);
            }

            return array;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return array;
    }

    /**
     *
     * @param resourceId 资源 article id
     * @return 获取资源详情（既可以获取文章也可以获取文件）
     */
    public JSONObject getResourceContent(Integer resourceId) {

        JSONObject object = new JSONObject();

        String sql = dao.getResourceContent(resourceId);

        try {
            ResultSet resultSet = DBUtil.query(sql);

            if (resultSet.next()) {
                object.put("resourceId", resultSet.getInt("article.id"));
                object.put("content", resultSet.getString("content"));
                object.put("path", resultSet.getString("path"));
                object.put("title", resultSet.getString("title"));
                object.put("createTime", resultSet.getString("createTime"));
                object.put("whetherTop", resultSet.getInt("whetherTop"));
                object.put("status", resultSet.getInt("status"));
                object.put("columnId", resultSet.getInt("columnId"));
                object.put("columnName", resultSet.getString("name"));


                return object;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return object;
    }

    public JSONArray search(String keyWords) {
        JSONArray array = new JSONArray();

        String sql = dao.search(keyWords);

        try {
            ResultSet set = DBUtil.query(sql);

            while (set.next()) {
                JSONObject object = new JSONObject();

                object.put("resourceId", set.getInt("article.id"));
                object.put("title", set.getString("article.title"));
                object.put("createTime", set.getString("article.createTime"));
                object.put("whetherTop", set.getInt("article.whetherTop"));
                object.put("status", set.getInt("article.status"));
                object.put("path", set.getString("file.path"));
                object.put("fileName", set.getString("file.name"));

                array.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return array;
    }

    /**
     * 更新资源
     * @param article
     * @return
     */
    public Integer updateResource(Article article) {

        try {
            EntityDao.update(article);

            return Result.SUCCESS;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Result.FAILED;
    }
}
