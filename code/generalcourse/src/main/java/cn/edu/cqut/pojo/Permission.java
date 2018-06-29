package cn.edu.cqut.pojo;

import cn.edu.cqut.util.Column;
import cn.edu.cqut.util.Table;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:39
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 权限
 **/
@Table(name = "permission", caption = "permission")
public class Permission extends Entity {

    @Column(type = "int", isId = true, name = "id", caption = "permissionId")
    private Integer id;// 权限id

    @Column(type = "int", caption = "roleId", name = "roleId")
    private Integer roleId;// 角色id

    @Column(type = "int", name = "moduleId", caption = "moduleId")
    private Integer moduleId;// 模块id

    public Permission() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}
