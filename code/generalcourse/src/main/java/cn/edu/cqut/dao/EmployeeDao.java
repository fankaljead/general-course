package cn.edu.cqut.dao;

import cn.edu.cqut.util.DBUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 14:27
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 数据库操作人员
 **/
public class EmployeeDao {
    /**
     * 查询登录密码
     * @param account
     * @return
     */
    public String queryPassword(Integer account) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.query("select password from employee where account=" + account);

        // 判断是否有返回值
        if (resultSet.next()) {
            // 返回密码
            String password = resultSet.getString("password");
            if (password == "" || password.equals("") || password == null) {
                return null;
            }
            return password;
        }
//        System.out.println("password" + resultSet.getString("password"));
        return null;
    }
}
