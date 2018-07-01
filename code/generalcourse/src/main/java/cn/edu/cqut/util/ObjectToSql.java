package cn.edu.cqut.util;




import cn.edu.cqut.pojo.Article;
import cn.edu.cqut.pojo.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectToSql {

    /**
     * 新增
     * @param o
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static String getSave(Entity o) throws InvocationTargetException, IllegalAccessException {
        Class<? extends Entity> clazz = o.getClass();
        StringBuffer sql = new StringBuffer();

        sql.append("insert into " + clazz.getAnnotation(Table.class).name() + "(");
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields
                ) {
            field.setAccessible(true);
            if (field.toGenericString().contains("static")) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            boolean isId = column.isId();
            if (isId) {
                continue;
            }
            String name = column.name();

            sql.append(name + ", ");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(")");
        sql.append(" values(");
        for (Field field : fields
             ) {
            field.setAccessible(true);
            if (field.toGenericString().contains("static")) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            boolean isId = column.isId();
            if (isId) {
                continue;
            }

            String type = column.type();

            if (type.equals("string")) {
                sql.append("'" + field.get(o) + "', ");
            } else {
                sql.append(field.get(o) + ", ");
            }

        }
        sql.deleteCharAt(sql.length() - 2);
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        System.out.println("add:" + sql.toString());
        return  sql.toString();
    }

    /**
     * 删除
     * @param o
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static String getDelete(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = o.getClass();
        Method method = clazz.getMethod("getId");
        Integer id = (Integer) method.invoke(o);
        StringBuffer sql = new StringBuffer();
        sql.append("delete\nfrom " + clazz.getSimpleName()+ "\nwhere id=" + id);
        return sql.toString();
    }

    public static String getDelete(Integer id, Class<? extends Entity> clazz) {
        return "delete from " + clazz.getAnnotation(Table.class).name() + " where id=" + id;
    }

    /**
     * 修改
     * @param o
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static String getUpdate(Entity o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<? extends Entity> clazz = o.getClass();
        StringBuffer sql = new StringBuffer();

        sql.append("update " + clazz.getAnnotation(Table.class).name() + " set ");

        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (fields[i].toGenericString().contains("static")) {
                continue;
            }
            Object o1 = fields[i].get(o);
            if (o1 == null) {
                continue;
            }
            if (o1 instanceof  String ){
                sql.append(fields[i].getName() + "='" + o1.toString() +"', ");
            }
            else {
                sql.append(fields[i].getName() + "=" + o1.toString() + ", ");
            }
        }
        sql.deleteCharAt(sql.length() - 2);

        Method method = clazz.getMethod("getId");
        Integer id = (Integer) method.invoke(o);
        sql.append("\nwhere id=" + id);

        System.out.println(sql.toString());
        return sql.toString();
    }

    public static String getQueryAll(Class<? extends Entity> clazz) {
        String tableName = clazz.getAnnotation(Table.class).name();
        //tableName = tableName == null ? clazz.getSimpleName() : tableName;
        return "select * from " + tableName;
    }

    /**
     * 将首字母大写
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    public static String getCreateTable(Class<? extends Entity> clazz) {
       StringBuffer sql = new StringBuffer();
       String tableName = clazz.getAnnotation(Table.class).name();
       sql.append("CREATE TABLE " + tableName + "( \n");

       Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields
             ) {
            if (field.toGenericString().contains("static")) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            boolean isId = column.isId();
            String name = column.name();
            String type = column.type();
            if (type.equals("varchar")) {
                type += "(" + column.length() + ")";
            }
            sql.append(name + " " + type);
            if (isId) {
                sql.append(" primary key AUTO_INCREMENT");
            }
            String nullable = column.nullable() ? "" : "not null";
            sql.append(" " + nullable);

            sql.append(",\n");
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append(")");
        sql.append("AUTO_INCREMENT=100000 ;");
       return sql.toString();
    }

    public static String getQuery(Integer id, Class<? extends Entity> clazz) {
        StringBuffer sql = new StringBuffer();

        String name = clazz.getAnnotation(Table.class).name();

        sql.append("select * from " + name + " where ");
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields
             ) {
            if (field.toGenericString().contains("static")) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            boolean isId = column.isId();
            if (isId) {
                sql.append(column.name() + " = " + id);
                break;
            } else {
                continue;
            }
        }

        System.out.println("question sql:" + sql);
        return sql.toString();
    }


    public static String getSavedId(Class<? extends Entity> clazz) {
        StringBuffer sql = new StringBuffer();
        sql.append("select MAX(");

        Field[] fields = clazz.getDeclaredFields();

        for (Field field :
                fields) {
            field.setAccessible(true);
            if (field.toGenericString().contains("static")) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            boolean isId = column.isId();
            if (isId) {
                 sql.append(field.getName() + ") as id from " + clazz.getSimpleName().toLowerCase());
                 break;
            } else {
                continue;
            }
        }

        return sql.toString();
    }
}
