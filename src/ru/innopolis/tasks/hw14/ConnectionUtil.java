package ru.innopolis.tasks.hw14;

import ru.innopolis.tasks.hw14.properties.PropsUtil;
import ru.innopolis.tasks.hw14.logger.LoggerUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            LoggerUtil.LOGGER.info(">>> Connection has been established");
            return connection;
        } catch (SQLException e) {
            LoggerUtil.LOGGER.error(">>> Error during getting connection:", e);
            return null;
        }
    }

    public static boolean closeConnection(Connection cn){
        try {
            cn.close();
            LoggerUtil.LOGGER.info(">>> Connection has been closed");
            return true;
        } catch (SQLException e) {
            LoggerUtil.LOGGER.error(">>> Error during closing connection:", e);
            return false;
        }
    }

}
