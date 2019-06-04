package ru.innopolis.tasks.hw02.task03.sorters;

import ru.innopolis.tasks.hw02.task03.entities.Person;

import java.util.List;

/**
 * Интерфейс, определяющий методы сортировки объектов Person
 */
public interface Sorter {

    /**
     * Метод сортировки коллекций Person
     * @param people коллекция для сортировки
     * @return отсортированный массив
     */
    Person[] sortPeople(List<Person> people);

}
