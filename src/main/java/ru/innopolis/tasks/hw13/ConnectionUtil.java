package ru.innopolis.tasks.hw13;

import ru.innopolis.tasks.hw13.properties.PropsUtil;

import java.sql.*;

/**
 * Вспомогательный класс для получения подключения к БД.
 */
public class ConnectionUtil {

    public static Connection getConnection() {
        String dbUrl = PropsUtil.getDataBaseConnectionUrl();
        String dbUser = PropsUtil.getDataBaseUser();
        String dbPassword = PropsUtil.getDataBasePassword();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println(">>> Connection has been established");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean closeConnection(Connection cn){
        try {
            cn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
