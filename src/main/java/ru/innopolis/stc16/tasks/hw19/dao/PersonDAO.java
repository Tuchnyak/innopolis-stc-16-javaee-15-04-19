package ru.innopolis.stc16.tasks.hw19.dao;

import ru.innopolis.stc16.tasks.hw19.entity.Person;

import java.util.List;

public interface PersonDAO {

    List<Person> getList();

    boolean addPerson(Person person);
}
