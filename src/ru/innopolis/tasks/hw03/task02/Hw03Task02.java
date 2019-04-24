package ru.innopolis.tasks.hw03.task02;

import ru.innopolis.tasks.hw02.task03.entities.Person;
import ru.innopolis.tasks.hw02.task03.entities.Sex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Задание 2. Создать класс ObjectBox, который будет хранить коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 *
 * @author Georgii Shchennikov
 */

public class Hw03Task02 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2));

        Integer i = 9;
        list.add(i);

        ObjectBox ob = new ObjectBox(list);

        System.out.println(ob.dump());

        ob.deleteObject(i);
        System.out.println(ob.dump());

        ob.deleteObject(1);
        System.out.println(ob.dump());


        List<Person> people = new ArrayList<>();
        Person chandler = new Person(30, new Sex(Sex.MAN), "Chandler");
        Person janice = new Person(30, new Sex(Sex.WOMAN), "Janice");
        people.add(chandler);
        people.add(janice);

        ObjectBox ob2 = new ObjectBox(people);

        System.out.println(ob2.dump());
        ob2.deleteObject(janice);
        System.out.println(ob2.dump());

        ob2.addObject(new Person(30, new Sex(Sex.WOMAN), "Monica"));
        System.out.println(ob2.dump());

    }

}
