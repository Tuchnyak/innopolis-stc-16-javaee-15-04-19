package ru.innopolis.tasks.hw02.task03.entities;

/**
 * Класс Person характеризуется полями age (возраст, целое число 0-100),
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка).
 */

public class Person {

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
        return "Sex: " + sex.getSexValue() + ", Age: " + age + ", Person: " + name ;
    }
}
