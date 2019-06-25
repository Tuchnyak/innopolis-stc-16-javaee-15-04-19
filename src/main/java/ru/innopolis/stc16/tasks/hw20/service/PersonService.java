package ru.innopolis.stc16.tasks.hw20.service;

import ru.innopolis.stc16.tasks.hw20.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getList();

    boolean addPerson(String name, String birth, String email, String phone);
}
