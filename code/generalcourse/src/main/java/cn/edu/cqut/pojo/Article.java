package cn.edu.cqut.pojo;

import cn.edu.cqut.util.Status;
import cn.edu.cqut.util.WhetherTop;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:00
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 文章类
 **/
public class Article {

    private Integer id;// 文章id
    private String title;// 文章标题
    private Integer employeeId;// 发布文章人的id
    private Integer columnId;// 发布的栏目id
    private String content;// 文章内容
    private String createTime;// 文章创建时间
    private Integer whetherTop = WhetherTop.UN_TOP;// 默认为不置顶
    private Integer status = Status.NOT_AUDIT;// 默认为未审核

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getWhetherTop() {
        return whetherTop;
    }

    public void setWhetherTop(Integer whetherTop) {
        this.whetherTop = whetherTop;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
