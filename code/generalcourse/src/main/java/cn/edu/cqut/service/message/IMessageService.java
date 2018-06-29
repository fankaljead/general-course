package cn.edu.cqut.service.message;

import cn.edu.cqut.pojo.Message;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 21:50
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 对留言进行数据库操作
 **/
public interface IMessageService {
    /**
     * 新增一条留言
     * @param message
     * @return Result.SUCCESS 成功 Result.FAILED 失败
     */
    public Integer addMessage(Message message);
}
