package ru.innopolis.stc16.tasks.hw20.dao.jdbc;

import ru.innopolis.stc16.tasks.hw20.dao.PersonDAO;
import ru.innopolis.stc16.tasks.hw20.entity.Person;

import java.sql.Connection;
import java.util.List;

public class PersonDAOProxy implements PersonDAO {

    private final PersonDAO personDAO;

    public PersonDAOProxy(Connection con) {
        this.personDAO = new PersonDAOImpl(con);
    }

    @Override
    public List<Person> getList() {
        List<Person> people = personDAO.getList();
        for (Person p : people) {
            p.setName(p.getName().concat(" Proxy was here"));
        }
        return people;
    }

    @Override
    public boolean addPerson(Person person) {
        person.setName(person.getName().substring(person.getName().length() - 1).toUpperCase()
                .concat(new StringBuilder(person.getName()).reverse().toString().toLowerCase().substring(1)));
        return personDAO.addPerson(person);
    }
}
