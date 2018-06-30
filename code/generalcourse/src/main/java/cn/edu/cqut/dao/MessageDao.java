package cn.edu.cqut.dao;

import cn.edu.cqut.pojo.Message;
import cn.edu.cqut.util.ObjectToSql;

import java.lang.reflect.InvocationTargetException;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 21:52
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 返回对数据库操作的sql语句
 **/
public class MessageDao {
    public String addMessage(Message message) throws InvocationTargetException, IllegalAccessException {
        return ObjectToSql.getSave(message);
    }
}
