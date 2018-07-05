package cn.edu.cqut.service.message;

import cn.edu.cqut.pojo.Message;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

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

    /**
     * 获取留言
     * @param pageIndex
     * @param pageSize
     * @param status
     * @return
     */
    public JSONArray getMessages(Integer pageIndex, Integer pageSize, Integer status);

    /**
     * 删除留言
     * @param array
     * @return
     */
    public Integer deleteMessages(List<Integer> messageIds);

    /**
     * 回复留言
     * @param message 留言对象
     * @return Result.SUCCESS 成功 Result.FAILED 失败
     */
    public Integer replyMessage(Message message);
}
