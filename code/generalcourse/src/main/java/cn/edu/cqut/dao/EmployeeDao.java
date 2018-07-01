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

    /**
     * 将新增人员的 account 设成跟 id 一样的值
     * @return
     */
    public String setAccount(Integer employeeId) {
        StringBuffer sql = new StringBuffer();

        sql.append("update employee set account=" + employeeId + " where id=" + employeeId);

        return sql.toString();
    }


    /**
     * 获取人员
     * @param roleId
     * @param pageIndex
     * @param pageSize
     * @param condition
     * @return
     */
    public String getEmployees(Integer roleId, Integer pageIndex, Integer pageSize, String condition) {
        StringBuffer sql = new StringBuffer();

        sql.append("select * from employee ");

        // 判断roleId条件是否有and
        boolean haveAnd = false;

        // 搜索条件不为空时加入like子句
        if (!(condition == null || condition.equals("") || condition.equals("undefined"))) {
            haveAnd = true;
            sql.append(" where employee.id like '%" + condition + "%' ");
            sql.append(" or employee.name like '%" + condition + "%' ");
            sql.append(" or employee.sex like '%" + condition + "%' ");
            sql.append(" or employee.account like '%" + condition + "%' ");
        }


        // 当栏目id不为空时，添加栏目的查询条件
        if (!(roleId == null || roleId == 0)) {
            if (haveAnd) {
                sql.append(" and ");
            } else {
                sql.append(" where ");
            }
            sql.append(" roleId=" + roleId);
        }

        // 分页
        if (pageIndex == null || pageIndex == 0) {
            pageIndex = ResourceDao.DEFAULT_PAGE_INDEX;
        }

        if (pageSize == null || pageSize == 0) {
            pageSize = ResourceDao.DEFAULT_PAGE_SIZE;
        }

        // 获取的第一条记录的下标
        Integer start = (pageIndex - 1) * pageSize;
        sql.append(" limit " + start + "," + pageSize);

        return sql.toString();
    }
}
