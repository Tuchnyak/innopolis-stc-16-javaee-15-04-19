package ru.innopolis.tasks.hw04.entities;

import ru.innopolis.tasks.hw02.task03.entities.Sex;

/**
 * Класс Person характеризуется полями age (возраст, целое число 0-100),
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка).
 * Убрано генерирование исключения при сравнении одинаковых объектов
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

        if (Sex.MAN.equals(sex.getSexValue()) && Sex.WOMAN.equals(p.getSex().getSexValue())) {
            return -1;
        }

        if (Sex.WOMAN.equals(sex.getSexValue()) && Sex.MAN.equals(p.getSex().getSexValue())) {
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
