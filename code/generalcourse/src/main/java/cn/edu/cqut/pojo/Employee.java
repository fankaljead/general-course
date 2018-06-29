package cn.edu.cqut.pojo;

import cn.edu.cqut.util.Column;
import cn.edu.cqut.util.Sex;
import cn.edu.cqut.util.Table;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:14
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 人员类
 **/
@Table(name = "employee", caption = "employee")
public class Employee extends Entity {

    @Column(isId = true, type = "int", name = "id", caption = "employeeId")
    private Integer id;// 人员id

    @Column(name = "name", caption = "employeeName")
    private String name;// 人员姓名

    @Column(type = "int", name = "sex", caption = "sex")
    private Integer sex = Sex.UNKNOWN;// 性别默认为未知

    @Column(type = "int", name = "account", caption = "account")
    private Integer account;// 人员的账号

    @Column(name = "password", caption = "password")
    private String password;// 人员的密码

    @Column(type = "int", name = "roleId", caption = "roleId")
    private Integer roleId;// 人员的角色 只能为单个角色

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
