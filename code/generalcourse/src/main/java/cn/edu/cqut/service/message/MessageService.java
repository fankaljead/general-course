package cn.edu.cqut.service.message;

import cn.edu.cqut.dao.BaseDao;
import cn.edu.cqut.dao.EntityDao;
import cn.edu.cqut.dao.MessageDao;
import cn.edu.cqut.pojo.Message;
import cn.edu.cqut.util.DBUtil;
import cn.edu.cqut.util.Result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
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

    public JSONArray getMessages(Integer pageIndex, Integer pageSize, Integer status) {
        JSONArray array = new JSONArray();

        String sql = dao.getMessages(pageIndex, pageSize, status);

        try {
            ResultSet set = DBUtil.query(sql);
            // 全部留言 status = 2
            if (status == Message.ALL) {
                while (set.next()) {
                    JSONObject object = new JSONObject();

                    object.put("messageId", set.getInt("message.id"));
                    object.put("moduleId", set.getInt("message.moduleId"));
                    object.put("status", set.getInt("message.status"));
                    object.put("content", set.getString("message.content"));
                    object.put("createTime", set.getString("message.createTime"));
                    object.put("reply", set.getString("message.reply"));
                    object.put("replyTime", set.getString("message.replyTime"));

                    array.add(object);
                }
            } else if (status == Message.HAVE_REPLY) { // 已经回复的留言 status = 1
                while (set.next()) {
                    JSONObject object = new JSONObject();

                    object.put("messageId", set.getInt("message.id"));
                    object.put("moduleId", set.getInt("message.moduleId"));
                    object.put("content", set.getString("message.content"));
                    object.put("createTime", set.getString("message.createTime"));
                    object.put("reply", set.getString("message.reply"));
                    object.put("replyTime", set.getString("message.replyTime"));

                    array.add(object);
                }
            } else { // 未被回复的留言 status = 0
                while (set.next()) {
                    JSONObject object = new JSONObject();

                    object.put("messageId", set.getInt("message.id"));
                    object.put("moduleId", set.getInt("message.moduleId"));
                    object.put("content", set.getString("message.content"));
                    object.put("createTime", set.getString("message.createTime"));

                    array.add(object);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return array;
    }


    public Integer deleteMessages(JSONArray array) {
        if (array == null) {
            return Result.FAILED;
        }
        for (int i = 0; i < array.size(); i++) {
            System.out.println("id: " + JSONObject.parseObject(array.getString(i)).get("messageId"));
            try {
                EntityDao.deleteByID((Integer) JSONObject.parseObject(array.getString(i)).get("messageId"), Message.class);
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.FAILED;
            } catch (IOException e) {
                e.printStackTrace();
                return Result.FAILED;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return Result.FAILED;
            }
        }
        return Result.SUCCESS;
    }

    /**
     * 回答留言
     * @param message 留言对象
     * @return
     */
    public Integer replyMessage(Message message) {
        try {
            EntityDao.update(message);
            return Result.SUCCESS;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
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
