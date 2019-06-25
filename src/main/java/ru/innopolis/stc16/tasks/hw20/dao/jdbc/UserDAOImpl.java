package ru.innopolis.stc16.tasks.hw20.dao.jdbc;

import ru.innopolis.stc16.tasks.hw20.dao.UserDAO;
import ru.innopolis.stc16.tasks.hw20.entity.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    private final Connection connection;

    public UserDAOImpl(Connection con) {
        this.connection = con;
    }

    private static final String SELECT_USER_SQL_TEMPLATE =
            "select id, name, password from user where name = ?";

    @Override
    public boolean checkUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_SQL_TEMPLATE)) {
            statement.setString(1, user.getName());
            User userFromDB = null;
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userFromDB = new User();
                    userFromDB.setId(resultSet.getInt(1));
                    userFromDB.setName(resultSet.getString(2));
                    userFromDB.setPassword(resultSet.getString(3));
                }
            }
            return user.equals(userFromDB);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An exception occurred on the DAO layer.", e);
        }
        return false;
    }
}
