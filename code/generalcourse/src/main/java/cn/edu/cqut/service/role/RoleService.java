package cn.edu.cqut.service.role;

import cn.edu.cqut.dao.BaseDao;
import cn.edu.cqut.dao.RoleDao;
import cn.edu.cqut.pojo.Employee;
import cn.edu.cqut.pojo.Role;
import cn.edu.cqut.util.DBUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<Role> getRoles() {
        return null;
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

    public Integer assignRole(Employee employee) {
        return BaseDao.update(employee);
    }
}
