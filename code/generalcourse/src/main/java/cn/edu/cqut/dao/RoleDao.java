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
    /**
     * 通过账号获取角色
     * @param account
     * @return
     */
    public String getRoleByAccount(Integer account) {
        return "select * from employee, role where employee.account=" +account +
                " and employee.roleId=role.id";
    }

    /**
     * 删除一个角色的所有权限
     * @param roleId
     * @return
     */
    public String deleteOneRolePermission(Integer roleId) {
        StringBuffer sql = new StringBuffer();

        sql.append("delete permission where roleId=" + roleId);

        return sql.toString();
    }
}
