package cn.edu.cqut.pojo;

import cn.edu.cqut.util.Column;
import cn.edu.cqut.util.Table;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:58
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 子模块
 **/

@Table(name = "submodule", caption = "submodule")
public class SubModule extends Entity {
    public static final Integer DISABLE = 0;// 禁用模块
    public static final Integer ENABLE = 1;// 启用模块

    @Column(isId = true, type = "int", name = "id", caption = "subModuleId")
    private Integer id;// 子模块id

    @Column(name = "name", caption = "subModuleName")
    private String name;// 子模块名称

    @Column(type = "int", name = "status", caption = "status")
    private Integer status = SubModule.ENABLE;// 模块状态 默认启用

    @Column(type = "int", name = "parentModuleId", caption = "parentModuleId")
    private Integer parentId;// 父模块id

    public SubModule() {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
