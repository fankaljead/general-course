package cn.edu.cqut.service.message;

import cn.edu.cqut.dao.BaseDao;
import cn.edu.cqut.dao.EntityDao;
import cn.edu.cqut.dao.MessageDao;
import cn.edu.cqut.pojo.Message;
import cn.edu.cqut.util.DBUtil;
import cn.edu.cqut.util.Result;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author 周翔辉
 * @create: 2018年06月29日
 * @time: 21:51
 * @project_name: generalcourse
 * @email: 728678732@qq.com
 * @description: 对留言进行数据库操作
 **/
public class MessageService implements IMessageService{
    private MessageDao dao = new MessageDao();

    public Integer addMessage(Message message) {
        try {
            EntityDao.save(message);

            return Result.SUCCESS;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Result.FAILED;
    }
}
