package cn.edu.cqut.pojo;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:39
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 权限
 **/
public class Permission {

    private Integer id;// 权限id
    private Integer roleId;// 角色id
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
