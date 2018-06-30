package cn.edu.cqut.dao;





import cn.edu.cqut.pojo.Entity;
import cn.edu.cqut.util.DBUtil;
import cn.edu.cqut.util.ObjectToSql;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityDao<T> {

    /**
     * 新增
     * @param o 一个对象
     * @return 是否新增成功
     * @throws Exception
     */
    public static boolean save(Entity o) throws InvocationTargetException, IllegalAccessException, SQLException, IOException, ClassNotFoundException {
        return DBUtil.execute(ObjectToSql.getSave(o));
    }

    /**
     * 通过传入一个对象删除一条数据
     * @param o
     * @return
     * @throws Exception
     */
    public static boolean deleteById(Object o) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, IOException, ClassNotFoundException {
        return DBUtil.execute(ObjectToSql.getDelete(o));
    }

    /**
     * 删除
     * @param id
     * @param clazz
     * @return
     * @throws Exception
     */
    public static boolean deleteByID(Integer id, Class clazz) throws SQLException, IOException, ClassNotFoundException {
        return DBUtil.execute(ObjectToSql.getDelete(id, clazz));
    }

    /**
     * 更新
     * @param o
     * @return
     * @throws Exception
     */
    public static boolean update(Entity o) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, IOException, ClassNotFoundException {
        return DBUtil.execute(ObjectToSql.getUpdate(o));
    }

    /**
     * 查询所有
     * @param clazz
     * @return
     */
    public static ResultSet queryAll(Class<? extends Entity> clazz) throws IOException, ClassNotFoundException, SQLException {
        try {
            return DBUtil.query(ObjectToSql.getQueryAll(clazz));
        } catch (SQLException e) {
            e.printStackTrace();
            return DBUtil.query(ObjectToSql.getQueryAll(clazz));
        }
    }

    public static ResultSet query(Integer id, Class<? extends Entity> clazz) throws SQLException, IOException, ClassNotFoundException {
        return DBUtil.query(ObjectToSql.getQuery(id, clazz));
    }

    /**
     * 获取刚注册的id
     * @param clazz
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Integer getSavedId(Class<? extends Entity> clazz) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = DBUtil.query(ObjectToSql.getSavedId(clazz));

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            return id;
        } else {
            return 0;
        }
    }
}
