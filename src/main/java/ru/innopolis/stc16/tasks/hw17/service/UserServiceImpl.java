package ru.innopolis.stc16.tasks.hw17.service;

import ru.innopolis.stc16.tasks.hw17.dao.UserDAO;
import ru.innopolis.stc16.tasks.hw17.dao.jdbc.UserDAOImpl;
import ru.innopolis.stc16.tasks.hw17.entity.User;

import java.sql.Connection;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private final UserDAO userDAO;

    public UserServiceImpl(Connection con) {
        userDAO = new UserDAOImpl(con);
    }

    @Override
    public boolean checkUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        return userDAO.checkUser(user);
    }
}
