package cn.edu.cqut.pojo;

import cn.edu.cqut.util.Column;
import cn.edu.cqut.util.Table;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:33
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 留言
 **/
@Table(name = "message", caption = "message")
public class Message extends Entity {

    @Column(isId = true, type = "int", name = "id", caption = "messageId")
    private Integer id;// 留言id

    @Column(name = "content", caption = "content")
    private String content;// 留言内容

    @Column(name = "createTime", caption = "createTime")
    private String createTime;// 留言创建时间

    @Column(name = "reply", caption = "reply")
    private String reply;// 留言的回复

    @Column(type = "int", name = "employeeId", caption = "employeeId")
    private Integer employeeId;// 回复留言人的id

    @Column(name = "replyTime", caption = "replyTime")
    private String replyTime;// 回复留言的时间

    @Column(type = "int", caption = "staus", name = "status")
    private Integer status;// 留言的状态

    @Column(name = "moduleId", caption = "moduleId")
    private Integer moduleId;// 留言板块的id

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}
