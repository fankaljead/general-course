package cn.edu.cqut.dao;

/**
 * @author 周翔辉
 * @create: 2018年06月30日
 * @time: 14:22
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 获取角色
 **/
public class RoleDao {
    public String getRoleByAccount(Integer account) {
        return "select * from employee, role where employee.account=" +account +
                " and employee.roleId=role.id";
    }
}
