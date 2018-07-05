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

        if (colunmId != 0) {
            sql.append(" and parentId=" + colunmId);
        }

        if (level == -1 && colunmId == 0) {
            sql = new StringBuffer();
            sql.append("select * from colunm");
        }

        System.out.println("level sql:" + sql);

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

    /**
     * 获取所有二级栏目
     * @return
     */
    public String getSecondColumns() {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT\n" +
                "\t*\n" +
                "FROM\n" +
                "\t colunm \n" +
                "JOIN colunm AS parent ON (parent.id = colunm.parentId)");

        return sql.toString();
    }
}
