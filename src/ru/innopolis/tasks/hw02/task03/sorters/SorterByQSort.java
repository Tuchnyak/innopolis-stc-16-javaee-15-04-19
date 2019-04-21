package ru.innopolis.tasks.hw02.task03.sorters;

import ru.innopolis.tasks.hw02.task03.entities.Person;

import java.util.*;

public class SorterByQSort implements Sorter {

    @Override
    public Person[] sortPeople(List<Person> people) {

        long start = System.currentTimeMillis();

        people = qSort(people);

        long end = System.currentTimeMillis();

        System.out.println("\nQuick sort time: " + (end - start) + " milliseconds");

        return people.toArray(new Person[people.size()]);
    }


    /**
     * Метод быстрой сортировки
     * @param people коллекция для сортировки
     * @return отсортированная коллекция
     */
    private List<Person> qSort(List<Person> people) {

        if (people.size() < 2) {

            return people;
        } else {

            List<Person> innerList = new LinkedList<>(people);

            int randomIndex = (int)(Math.random() * innerList.size());

            Person pivotPerson = innerList.get(randomIndex);

            innerList.remove(randomIndex);

            List<Person> leftPart = new LinkedList<>();
            List<Person> rightPart = new LinkedList<>();

            for (Person p : innerList) {
                if (p.compareTo(pivotPerson) <= 0) {
                    leftPart.add(p);
                } else {
                    rightPart.add(p);
                }

            }

            List<Person> sewedList = new LinkedList<>();
            sewedList.addAll(qSort(leftPart));
            sewedList.add(pivotPerson);
            sewedList.addAll(qSort(rightPart));

            return sewedList;
        }

    }


}
