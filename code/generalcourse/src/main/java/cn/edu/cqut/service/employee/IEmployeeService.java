package cn.edu.cqut.service.employee;

import cn.edu.cqut.pojo.Employee;

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
     * @return  Result.SUCCESS 成功 Result.FAILED 失败
     */
    public Integer addEmployee(Employee employee);
}
