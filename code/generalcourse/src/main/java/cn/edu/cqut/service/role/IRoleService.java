package cn.edu.cqut.service.role;

import cn.edu.cqut.pojo.Employee;
import cn.edu.cqut.pojo.Role;

import java.util.List;

/**
 * @author 周翔辉
 * @create: 2018年06月30日
 * @time: 14:17
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 角色
 **/
public interface IRoleService {
    /**
     * 获取角色
     * @return
     */
    public List<Role> getRoles();

    /**
     * 通过账号获取 角色信息
     * @param account
     * @return
     */
    public Role getRoleByAccount(Integer account);

    /**
     * 给用户分配角色
     * @param employee
     * @return
     */
    public Integer assignRole(Employee employee);

    /**
     * 新增角色
     * @param role
     * @param ownModuleIds
     * @return
     */
    public Integer addRole(Role role, List<Integer> ownModuleIds);

    /**
     * 修改角色
     * @param role
     * @param ownModuleIds
     * @return
     */
    public Integer updateRole(Role role, List<Integer> ownModuleIds);

    /**
     * 删除角色
     * @param roleIds
     * @return
     */
    public Integer deleteRoles(List<Integer> roleIds);
}
