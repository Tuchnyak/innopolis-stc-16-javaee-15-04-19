package ru.innopolis.tasks.hw02.task03.entities;

import ru.innopolis.tasks.hw02.task03.sortExceptions.SortExceptionChecker;

/**
 * Класс Person характеризуется полями age (возраст, целое число 0-100),
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка).
 */

public class Person implements Comparable<Person> {

    private int age;
    private Sex sex;
    private String name;


    public Person(int age, Sex sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Sex: " + sex.getSexValue() + ", Age: " + age + ", Person: " + name;
    }

    @Override
    public int compareTo(Person p) {

        SortExceptionChecker.checkNameAge(this, p);

        if (sex.getSexValue().equals(Sex.MAN) && p.getSex().getSexValue().equals(Sex.WOMAN)) {
            return -1;
        }

        if (sex.getSexValue().equals(Sex.WOMAN) && p.getSex().getSexValue().equals(Sex.MAN)) {
            return 1;
        }

        if (age > p.getAge()) {
            return -1;
        }

        if (age < p.getAge()) {
            return 1;
        }

        return name.compareTo(p.getName());
    }


}
