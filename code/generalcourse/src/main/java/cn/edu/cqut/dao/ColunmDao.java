package cn.edu.cqut.dao;

import cn.edu.cqut.util.DBUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 19:22
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 对栏目的操作 数据库
 **/
public class ColunmDao {
    /**
     * 获取栏目
     * @param level
     * @param colunmId
     * @return
     */
    public JSONArray getColunm(Integer level, Integer colunmId) {
        JSONArray array = new JSONArray();

        StringBuffer sql = new StringBuffer();
        sql.append("select * from colunm where level=" + level);

        if (colunmId != null) {
            sql.append(" and parentId=" + colunmId);
        }

        try {
            ResultSet resultSet = DBUtil.query(sql.toString());

            while (resultSet.next()) {
                JSONObject object = new JSONObject();
                object.put("columnId", resultSet.getInt("id"));
                object.put("columnName", resultSet.getString("name"));
                object.put("level", resultSet.getInt("level"));
                object.put("parentId", resultSet.getInt("parentId"));

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
}
