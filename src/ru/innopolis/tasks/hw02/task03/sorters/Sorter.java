package ru.innopolis.tasks.hw02.task03.sorters;

import ru.innopolis.tasks.hw02.task03.entities.Person;

import java.util.List;

public interface Sorter {

    Person[] sortPeople(List<Person> people);

}
