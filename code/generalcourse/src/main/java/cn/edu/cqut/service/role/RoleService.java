package cn.edu.cqut.service.role;

import cn.edu.cqut.dao.BaseDao;
import cn.edu.cqut.dao.EntityDao;
import cn.edu.cqut.dao.RoleDao;
import cn.edu.cqut.pojo.Employee;
import cn.edu.cqut.pojo.Permission;
import cn.edu.cqut.pojo.Role;
import cn.edu.cqut.util.DBUtil;
import cn.edu.cqut.util.Result;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 周翔辉
 * @create: 2018年06月30日
 * @time: 14:21
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 角色管理
 **/
public class RoleService implements IRoleService{

    private RoleDao dao = new RoleDao();

    /**
     * 获取所有角色
     * @return
     */
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<Role>();

        try {
            ResultSet set = EntityDao.queryAll(Role.class);

            while (set.next()) {
                Role role = new Role();

                role.setId(set.getInt("id"));
                role.setName(set.getString("name"));
                role.setCreateTime(set.getString("createTime"));
                role.setDescription(set.getString("description"));

                roles.add(role);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    /**
     * 通过account获取角色信息
     * @param account
     * @return
     */
    public Role getRoleByAccount(Integer account) {

        String sql = dao.getRoleByAccount(account);

        Role role = new Role();

        try {
            ResultSet resultSet = DBUtil.query(sql);

            if (resultSet.next()) {
                role.setId(resultSet.getInt("role.id"));
                role.setName(resultSet.getString("role.Name"));
                role.setCreateTime(resultSet.getString("role.createTime"));
                role.setDescription(resultSet.getString("role.description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return role;
    }

    /**
     * 分配角色
     * @param employee
     * @return
     */
    public Integer assignRole(Employee employee) {
        return BaseDao.update(employee);
    }

    /**
     * 新增角色
     * @param role
     * @param ownModuleIds
     * @return
     */
    public Integer addRole(Role role, List<Integer> ownModuleIds) {
        // 添加角色
        Integer isSuccess = BaseDao.save(role);

        // 添加失败直接返回
        if (isSuccess != Result.SUCCESS) {
            return isSuccess;
        }

        try {

            Integer roleId = EntityDao.getSavedId(Role.class);

            isSuccess = assignRolePermissions(roleId, ownModuleIds);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return isSuccess;
    }

    /**
     * 更新权限
     * @param role
     * @param ownModuleIds
     * @return
     */
    public Integer updateRole(Role role, List<Integer> ownModuleIds) {
        // 更新角色基本信息
        Integer isSuccess = BaseDao.update(role);

        try {
            // 先将这个角色的权限删除
            DBUtil.execute(dao.deleteOneRolePermission(role.getId()));

            isSuccess = assignRolePermissions(role.getId(), ownModuleIds);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    /**
     * 删除角色
     * @param roleIds
     * @return
     */
    public Integer deleteRoles(List<Integer> roleIds) {

        Integer isSuccess = Result.FAILED;

        for (int i = 0; i < roleIds.size(); i++) {
            // 先将这个角色的权限删除
            try {
                isSuccess = BaseDao.deleteById(roleIds.get(i), Role.class);
                DBUtil.execute(dao.deleteOneRolePermission(roleIds.get(i)));

                // 添加失败直接返回
                if (isSuccess != Result.SUCCESS) {
                    return isSuccess;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }

    /**
     * 通过角色id和拥有的模块id来添加权限
     * @param roleId
     * @param ownModuleIds
     * @return
     */
    private Integer assignRolePermissions(Integer roleId, List<Integer> ownModuleIds) {
        Integer isSuccess = Result.SUCCESS;
        // 给角色赋权限
        for (int i = 0; i < ownModuleIds.size(); i++) {
            Permission permission = new Permission();
            permission.setModuleId(ownModuleIds.get(i));
            permission.setRoleId(roleId);

            isSuccess = BaseDao.save(permission);

            // 添加失败直接返回
            if (isSuccess != Result.SUCCESS) {
                return isSuccess;
            }
        }

        return isSuccess;
    }
}
