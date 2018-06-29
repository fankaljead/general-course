package cn.edu.cqut.pojo;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:41
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 角色
 **/
public class Role {
    private Integer id;// 角色id
    private String name;// 角色姓名
    private String createTime;// 角色创建时间
    private String description;// 对角色的描述

    public Role() {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
