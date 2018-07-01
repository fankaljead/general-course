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
    /**
     * 新增留言
     * @param message
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String addMessage(Message message) throws InvocationTargetException, IllegalAccessException {
        return ObjectToSql.getSave(message);
    }

    /**
     * 获取留言
     * @param pageIndex
     * @param pageSize
     * @param status
     * @return
     */
    public String getMessages(Integer pageIndex, Integer pageSize, Integer status) {
        StringBuffer sql = new StringBuffer();

        sql.append("select * from message ");

        if (status != Message.ALL) {
            sql.append("where status=" + status);
        }

        // 分页
        if (pageIndex == null || pageIndex == 0) {
            pageIndex = ResourceDao.DEFAULT_PAGE_INDEX;
        }

        if (pageSize == null || pageSize == 0) {
            pageSize = ResourceDao.DEFAULT_PAGE_SIZE;
        }


        // 按照时间排序，由新到旧
        sql.append(" order by message.createTime desc");

        // 获取的第一条记录的下标
        Integer start = (pageIndex - 1) * pageSize;
        sql.append(" limit " + start + "," + pageSize);

        System.out.println("message sql:" + sql);

        return sql.toString();
    }

    public String replyMessage(Integer employeeId, String content, String replyTime, Integer status) {
        StringBuffer sql = new StringBuffer();

        sql.append("update ");

        return sql.toString();
    }
}
