package cn.edu.cqut.pojo;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 13:33
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 留言
 **/
public class Message {

    private Integer id;// 留言id
    private String content;// 留言内容
    private String createTime;// 留言创建时间
    private String reply;// 留言的回复
    private Integer EmployeeId;// 回复留言人的id
    private String replyTime;// 回复留言的时间
    private Integer status;// 留言的状态
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
        return EmployeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        EmployeeId = employeeId;
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
