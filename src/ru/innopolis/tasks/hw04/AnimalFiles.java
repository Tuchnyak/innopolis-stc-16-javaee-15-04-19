package ru.innopolis.tasks.hw04;

import ru.innopolis.tasks.hw04.entities.Person;
import ru.innopolis.tasks.hw02.task03.entities.Sex;
import ru.innopolis.tasks.hw04.entities.Animal;

import java.io.InvalidObjectException;
import java.util.*;

/**
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 * <p>
 * Реализовать:
 * - метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * - поиск животного по его кличке (поиск должен быть эффективным)
 * - изменение данных животного по его идентификатору
 * - вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 *
 * @author Georgii Shchennikov
 */
public class AnimalFiles {

    /**
     * Отображение для хранения записей о животных с уникальными ID
     */
    private static Map<Integer, Animal> animals = new TreeMap<>();

    /**
     * Отображение, параллельное основному, для получения ID при поиске по кличке
     */
    private static Map<String, Set<Integer>> nicknames = new TreeMap<>();

    public static void main(String[] args) {

        System.out.println("Проверим печать пустого каталога");
        AnimalFilesUtil.printAnimals(animals);
        System.out.println();

        Animal a1 = new Animal("Barsik", 5d, new Person(78, new Sex(Sex.WOMAN), "Babushka Klava"));
        Animal a2 = new Animal("Pogrom", 3.5d, new Person(25, new Sex(Sex.MAN), "Semyon Polishchuk"));
        Animal a3 = new Animal("Marsel", 1.5, new Person(25, new Sex(Sex.MAN), "Ross Geller"));
        Animal a4 = new Animal("Barsik", 4.5, new Person(25, new Sex(Sex.MAN), "Dmitry Potapenko"));

        try {
            AnimalFilesUtil.addAnimalToFiles(a1, animals, nicknames);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        try {
            AnimalFilesUtil.addAnimalToFiles(a2, animals, nicknames);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        try {
            AnimalFilesUtil.addAnimalToFiles(a3, animals, nicknames);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        try {
            AnimalFilesUtil.addAnimalToFiles(a4, animals, nicknames);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

        System.out.println("Проверим печать заполненного каталога");
        AnimalFilesUtil.printAnimals(animals);
        System.out.println();

        System.out.println("Попробуем найти в каталоге Барсика");
        List<Animal> search = AnimalFilesUtil.searchRecordByNickname("Barsik", animals, nicknames);
        for (Animal a : search) {
            System.out.println(a);
        }
        System.out.println();

        System.out.println("Попробуем отредактировать запись №2");
        AnimalFilesUtil.editRecordById(2, "Marsel Star", 2d, "Excecutive producer", new Sex(Sex.MAN), 46, animals, nicknames);
        AnimalFilesUtil.printAnimals(animals);
        System.out.println();

        System.out.println("Попробуем добавить животное во второй раз в виде нового объекта");
        Animal a2Again = new Animal("Pogrom", 3.5d, new Person(25, new Sex(Sex.MAN), "Semyon Polishchuk"));
        try {
            AnimalFilesUtil.addAnimalToFiles(a2Again, animals, nicknames);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

    }


}
