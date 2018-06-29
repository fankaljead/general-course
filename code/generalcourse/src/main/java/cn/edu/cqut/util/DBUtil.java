package cn.edu.cqut.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接和操作<br>
 *
 * @author 周翔辉
 * @create 2018/6/22
 * @time 15:59
 * @project_name generalcourse
 * @email 728678732@qq.com
 */
public class DBUtil {
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream inputStream = DBUtil.class.getResourceAsStream("/mysqlConfig.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String driverName = properties.getProperty("driverName");
        String url = properties.getProperty("url");
        String userName = properties.getProperty("userName");
        String userPWD = properties.getProperty("password");

        Class.forName(driverName);

        Connection connection = DriverManager.getConnection(url, userName, userPWD);

        return connection;
    }


    public static boolean execute(String sql) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        //execute返回是否有结果集
        return statement.execute(sql);
    }


    public static Statement getStatement() throws SQLException, IOException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        return statement;
    }

    /**
     * 查询
     * @param sql
     * @return
     * @throws Exception
     */
    public static ResultSet query(String sql) throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = getStatement().executeQuery(sql);
        return resultSet;
    }

}
