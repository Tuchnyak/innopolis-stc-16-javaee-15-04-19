package ru.innopolis.tasks.hw02.task03.sorters;

import ru.innopolis.tasks.hw02.task03.entities.Person;
import ru.innopolis.tasks.hw02.task03.entities.Sex;
import ru.innopolis.tasks.hw02.task03.sortExceptions.SortExceptionChecker;

import java.util.*;

public class SorterByComparator implements Sorter {

    @Override
    public Person[] sortPeople(List<Person> people) {

        long start = System.currentTimeMillis();

        people.sort(new ComparatorName());
        people.sort(new ComparatorAge());
        people.sort(new ComparatorSex());

        long end = System.currentTimeMillis();

        System.out.println("\nComparator time: " + (end - start) + " milliseconds");

        return people.toArray(new Person[people.size()]);
    }


    /**
     * Класс-компаратор для сортировки по типу Sex
     */
    private class ComparatorSex implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {

            SortExceptionChecker.checkNameAge(p1, p2);

            if (p1.getSex().getSexValue().equals(Sex.MAN) && p2.getSex().getSexValue().equals(Sex.WOMAN)) return -1;
            if (p1.getSex().getSexValue().equals(Sex.WOMAN) && p2.getSex().getSexValue().equals(Sex.MAN)) return 1;

            return 0;
        }

    }


    /**
     * Класс-компаратор для сортировки по возрасту
     */
    private class ComparatorAge implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {

            SortExceptionChecker.checkNameAge(p1, p2);

            return Integer.compare(p2.getAge(), p1.getAge());
        }

    }


    /**
     * Класс-компаратор для сортировки по имени
     */
    private class ComparatorName implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {

            SortExceptionChecker.checkNameAge(p1, p2);

            return p1.getName().compareTo(p2.getName());
        }

    }


}
