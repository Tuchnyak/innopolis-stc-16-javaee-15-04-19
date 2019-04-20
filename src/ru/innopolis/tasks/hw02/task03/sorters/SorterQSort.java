package ru.innopolis.tasks.hw02.task03.sorters;

import ru.innopolis.tasks.hw02.task03.entities.Person;

import java.security.SecureRandom;
import java.util.*;

public class SorterQSort implements Sorter {

    @Override
    public Person[] sortPeople(List<Person> people) {

        long start = System.currentTimeMillis();

        people = sortByAge(people);

        long end = System.currentTimeMillis();

        System.out.println("Quick sort time: " + (end - start) + " milliseconds");

        return people.toArray(new Person[people.size()]);
    }

    private List<Person> sortByAge(List<Person> people) {

        if (people.size() < 2) {

            return people;
        } else {

            int randomIndex = (int)(Math.random() * people.size());

            Person pivotPerson = people.get(randomIndex);

            List<Person> leftPart = new LinkedList<>();
            List<Person> rightPart = new LinkedList<>();

            for (Person p : people) {
                if (p.getAge() > pivotPerson.getAge()) {
                    leftPart.add(p);
                } else {
                    rightPart.add(p);
                }

            }

            List<Person> sewedList = new LinkedList<>();

            sewedList.addAll(sortByAge(leftPart));
            sewedList.addAll(sortByAge(rightPart));

            return sewedList;
        }

    }


}
