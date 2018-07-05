package cn.edu.cqut.dao;



import cn.edu.cqut.pojo.Entity;
import cn.edu.cqut.util.Column;
import cn.edu.cqut.util.DBUtil;
import cn.edu.cqut.util.ObjectToSql;
import cn.edu.cqut.util.Result;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库连接和处理<br>
 *
 * @author 周翔辉
 * @create 2018/6/22
 * @time 15:45
 * @project_name questionnaire
 * @email 728678732@qq.com
 */
public class BaseDao {

    /**
     * 查询所有，并且返回一个数组
     * @param id
     * @param clazz
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static List<Entity> query(Integer id, Class<? extends Entity> clazz) throws SQLException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ArrayList<Entity> list = new ArrayList<Entity>();

        ResultSet resultSet = DBUtil.query(ObjectToSql.getQuery(id, clazz));

        Field[] fields = clazz.getDeclaredFields();

        while (resultSet.next()) {
            Object o = clazz.newInstance();
            for (Field field : fields
                 ) {
                field.setAccessible(true);
                if (field.toGenericString().contains("static")) {
                    continue;
                }

                Column column = field.getAnnotation(Column.class);
                String type = column.type();

                if(type.equals("int")) {
                    field.set(o, resultSet.getInt(field.getName()));
                } else if(type.equals("double")) {
                    field.set(o, resultSet.getDouble(field.getName()));
                } else {
                    field.set(o, resultSet.getString(field.getName()));
                }
            }

            list.add((Entity) o);
        }

        return list;
    }

    /**
     * 通用更新方法
     * @param entity
     * @return
     */
    public static Integer update(Entity entity) {
        try {
            EntityDao.update(entity);

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

    /**
     * 新增通用方法
     * @param entity
     * @return
     */
    public static Integer save(Entity entity) {
        try {
            EntityDao.save(entity);
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

    /**
     * 删除通用方法
     * @param id
     * @param entity
     * @return
     */
    public static Integer deleteById(Integer id, Class entity) {
        try {
            EntityDao.deleteByID(id, entity);

            return Result.SUCCESS;
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
