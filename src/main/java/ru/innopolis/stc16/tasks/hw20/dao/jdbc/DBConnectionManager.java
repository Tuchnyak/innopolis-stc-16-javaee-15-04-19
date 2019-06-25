package ru.innopolis.stc16.tasks.hw20.dao.jdbc;

import ru.innopolis.stc16.tasks.hw20.dao.ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager implements ConnectionManager {
    private Connection connection;

    public DBConnectionManager(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        this.connection = DriverManager.getConnection(dbURL, user, pwd);
    }

    public Connection getConnection() {
        return this.connection;
    }
}
