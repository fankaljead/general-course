package cn.edu.cqut.service.employee;

import cn.edu.cqut.pojo.Employee;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 14:24
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 定义employee中使用的方法
 **/
public interface IEmployeeService {
    /**
     * 登录方法
     * @param account
     * @param password
     * @return 1 成功 0 密码错误 -1 没有这个账号
     */
    public Integer login(Integer account, String password);

    /**
     * 添加用户
     * @param employee
     * @return  成功返回账号  失败返回Result.FAILED
     */
    public Integer addEmployee(Employee employee);

    /**
     * 删除人员
     * @param array
     * @return
     */
    public Integer deleteEmployees(JSONArray array);


    /**
     * 获取人员
     * @param roleId 角色id
     * @param pageIndex
     * @param pageSize
     * @param condition 条件
     * @return employee数组
     */
    public List<Employee> getEmployees(Integer roleId, Integer pageIndex, Integer pageSize, String condition);
}
