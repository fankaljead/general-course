package cn.edu.cqut.pojo;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:25
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 栏目
 **/
public class Colunm {

    public final static Integer LEVEL_ZERO = 0;// 一级栏目
    public final static Integer LEVEL_ONE = 1;// 二级栏目

    private Integer id;// 栏目id
    private String name;// 栏目名称
    private Integer level;// 栏目等级
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
