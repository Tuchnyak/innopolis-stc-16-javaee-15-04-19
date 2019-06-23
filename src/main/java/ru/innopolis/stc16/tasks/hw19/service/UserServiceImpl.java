package ru.innopolis.stc16.tasks.hw19.service;

import ru.innopolis.stc16.tasks.hw19.dao.UserDAO;
import ru.innopolis.stc16.tasks.hw19.dao.jdbc.UserDAOImpl;
import ru.innopolis.stc16.tasks.hw19.entity.User;

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
