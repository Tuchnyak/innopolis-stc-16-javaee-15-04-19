package ru.innopolis.stc16.tasks.hw20.service;

import ru.innopolis.stc16.tasks.hw20.dao.PersonDAO;
import ru.innopolis.stc16.tasks.hw20.dao.jdbc.PersonDAOImpl;
import ru.innopolis.stc16.tasks.hw20.dao.jdbc.PersonDAOProxy;
import ru.innopolis.stc16.tasks.hw20.entity.Person;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonServiceImpl implements PersonService {
    private Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
    private final PersonDAO personDAO;

    public PersonServiceImpl(Connection con) {
        personDAO = new PersonDAOProxy(con);
    }

    @Override
    public List<Person> getList() {
        return personDAO.getList();
    }

    @Override
    public boolean addPerson(String name, String birth, String email, String phone) {
        Person person = new Person.Builder(name)
                .withBirthDate(safeParseDate(birth))
                .withEmail(email)
                .withPhone(phone).build();

        return personDAO.addPerson(person);
    }

    private Date safeParseDate(String birthStr) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return format.parse(birthStr);
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Date parsing error", e);
            throw new RuntimeException(e);
        }
    }

}