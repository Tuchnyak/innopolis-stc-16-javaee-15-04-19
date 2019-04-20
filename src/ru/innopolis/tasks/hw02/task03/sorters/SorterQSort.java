package ru.innopolis.tasks.hw02.task03.sorters;

import ru.innopolis.tasks.hw02.task03.entities.Person;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

public class SorterQSort implements Sorter {

    @Override
    public Person[] sortPeople(Person[] people) {

        long start = System.currentTimeMillis();

        SecureRandom random = new SecureRandom();

        people = sortByAge(people, random);

        long end = System.currentTimeMillis();

        System.out.println("Quick sort time: " + (end - start) + " milliseconds");

        return people;
    }

    private Person[] sortByAge(Person[] people, SecureRandom random) {

        if (people.length < 2) {

//            if (people[0].getAge() > people[1].getAge())
//                return people;
//
//            Person temp = people[0];
//            people[0] = people[1];
//            people[1] = temp;

            return people;
        } else {

            int randomIndex = random.nextInt(people.length);

            Person pivotPerson = people[randomIndex];

            Person[] leftArr = new Person[people.length];
            Person[] rightArr = new Person[people.length];

            int leftIndex = 0;
            int rightIndex = 0;

            for (Person p : people) {
                if (p.getAge() > pivotPerson.getAge()) {
                    leftArr[leftIndex] = p;
                    leftIndex++;
                } else {
                    rightArr[rightIndex] = p;
                    rightIndex++;
                }

            }

            return sewArrays(sortByAge(trimNvl(leftArr), random), sortByAge(trimNvl(rightArr), random));
        }

    }

    private Person[] sewArrays(Person[] people1, Person[] people2) {

        Person[] arr = new Person[people1.length + people2.length];

        System.arraycopy(people1, 0, arr, 0, people1.length);
        System.arraycopy(people2, 0, arr, people1.length, people2.length);

        return arr;
    }

    private Person[] trimNvl(Person[] arr) {

        int notNullCount = (int) Arrays.stream(arr).filter(Objects::nonNull).count();
        Person[] newArr = new Person[notNullCount];

        System.arraycopy(arr, 0, newArr, 0, notNullCount);

        return newArr;
    }


}
