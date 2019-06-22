package ru.innopolis.stc16.tasks.hw18.servlet;

import ru.innopolis.stc16.tasks.hw18.entity.Person;
import ru.innopolis.stc16.tasks.hw18.service.PersonService;
import ru.innopolis.stc16.tasks.hw18.service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(
        urlPatterns = "/person/list",
        loadOnStartup = 1
)
public class ListPersonServlet extends HttpServlet {
    private PersonService personService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        personService = new PersonServiceImpl(connection);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> res = personService.getList();
        req.setAttribute("persons", res);
        req.getRequestDispatcher("/persons.jsp")
                .forward(req, resp);
    }
}
