package ru.innopolis.stc16.tasks.hw18.service;

import ru.innopolis.stc16.tasks.hw18.entity.Person;

import java.util.List;

public interface PersonService {

    List<Person> getList();

    boolean addPerson(String name, String birth, String email, String phone);
}
