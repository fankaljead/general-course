package cn.edu.cqut.service.employee;

import cn.edu.cqut.dao.BaseDao;
import cn.edu.cqut.dao.EmployeeDao;
import cn.edu.cqut.pojo.Employee;
import cn.edu.cqut.util.DBUtil;
import cn.edu.cqut.util.Result;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 14:26
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 对这个文件的描述
 **/
public class EmployeeService implements IEmployeeService{
    private EmployeeDao employeeDao = new EmployeeDao();

    /**
     * 登录方法
     * @param account
     * @param password
     * @return
     */
    public Integer login(Integer account, String password) {
        try {
            String uPassword = employeeDao.queryPassword(account);

            if (uPassword == null) {// 账号不存在
                return -1;
            } else if (uPassword.equals(password)) {// 密码正确
                return Result.SUCCESS;
            } else if (!uPassword.equals(password)) {// 密码不正确
                return Result.FAILED;
            } else { // 未知错误
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 新增用户
     * @param employee
     * @return
     */
    public Integer addEmployee(Employee employee) {

        try {
            if (BaseDao.save(employee) != Result.SUCCESS){
                return Result.FAILED;
            } else {
                DBUtil.execute(employeeDao.setAccount());
                return Result.SUCCESS;
            }

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
