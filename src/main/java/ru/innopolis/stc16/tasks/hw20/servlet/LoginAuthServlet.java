package ru.innopolis.stc16.tasks.hw20.servlet;

import ru.innopolis.stc16.tasks.hw20.service.UserService;
import ru.innopolis.stc16.tasks.hw20.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/login")
public class LoginAuthServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        userService = new UserServiceImpl(connection);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        boolean hasSuchUser = userService.checkUser(name, password);

        HttpSession session = req.getSession();
        if (hasSuchUser) {
            session.setAttribute("user", name);
        } else {
            session.setAttribute("user", null);
        }

        resp.sendRedirect((String) session.getAttribute("urlToForward"));
    }
}
