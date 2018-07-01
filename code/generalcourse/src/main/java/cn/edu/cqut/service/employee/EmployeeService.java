package cn.edu.cqut.service.employee;

import cn.edu.cqut.dao.BaseDao;
import cn.edu.cqut.dao.EmployeeDao;
import cn.edu.cqut.dao.EntityDao;
import cn.edu.cqut.pojo.Employee;
import cn.edu.cqut.pojo.Message;
import cn.edu.cqut.util.DBUtil;
import cn.edu.cqut.util.Result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                Integer employeeId = EntityDao.getSavedId(Employee.class);
                DBUtil.execute(employeeDao.setAccount(employeeId));
                return employeeId;
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

    /**
     * 删除人员
     * @param array
     * @return
     */
    public Integer deleteEmployees(JSONArray array) {
        if (array == null) {
            return Result.FAILED;
        }
        for (int i = 0; i < array.size(); i++) {
            System.out.println("id: " + JSONObject.parseObject(array.getString(i)).get("employeeId"));
            try {
                EntityDao.deleteByID((Integer) JSONObject.parseObject(array.getString(i)).get("employeeId"), Message.class);
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.FAILED;
            } catch (IOException e) {
                e.printStackTrace();
                return Result.FAILED;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return Result.FAILED;
            }
        }
        return Result.SUCCESS;
    }

    /**
     * 获取人员
     * @param roleId 角色id
     * @param pageIndex
     * @param pageSize
     * @param condition 条件
     * @return
     */
    public List<Employee> getEmployees(Integer roleId, Integer pageIndex, Integer pageSize, String condition) {
        List<Employee> employees = new ArrayList<Employee>();

        try {
            ResultSet set = DBUtil.query(employeeDao.getEmployees(roleId, pageIndex, pageSize, condition));

            while (set.next()) {
                Employee employee = new Employee();
                employee.setId(set.getInt("id"));
                employee.setSex(set.getInt("sex"));
                employee.setRoleId(set.getInt("roleId"));
                employee.setName(set.getString("name"));
                employee.setAccount(set.getInt("account"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
