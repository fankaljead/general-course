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
}
