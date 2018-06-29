package cn.edu.cqut.pojo;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:58
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 子模块
 **/
public class SubModule {
    public static final Integer DISABLE = 0;// 禁用模块
    public static final Integer ENABLE = 1;// 启用模块

    private Integer id;// 子模块id
    private String name;// 子模块名称
    private Integer status = SubModule.ENABLE;// 模块状态 默认启用
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
