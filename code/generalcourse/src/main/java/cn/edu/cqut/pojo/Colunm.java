package cn.edu.cqut.pojo;

import cn.edu.cqut.util.Column;
import cn.edu.cqut.util.Table;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:25
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 栏目
 **/
@Table(name = "colunm", caption = "column")
public class Colunm extends Entity {

    public final static Integer LEVEL_ZERO = 0;// 一级栏目
    public final static Integer LEVEL_ONE = 1;// 二级栏目

    @Column(isId = true, type = "int", name = "id", caption = "columnId")
    private Integer id;// 栏目id

    @Column(caption = "columnName", name = "name")
    private String name;// 栏目名称

    @Column(type = "int", name = "level", caption = "level")
    private Integer level;// 栏目等级

    @Column(type = "int", name = "parentId", caption = "parentModuleId")
    private Integer parentId;// 父栏目id

    public Colunm() {
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
