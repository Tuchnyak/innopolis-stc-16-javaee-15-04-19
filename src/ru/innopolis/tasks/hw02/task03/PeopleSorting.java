package ru.innopolis.tasks.hw02.task03;

import ru.innopolis.tasks.hw02.task03.entities.Person;
import ru.innopolis.tasks.hw02.task03.entities.Sex;
import ru.innopolis.tasks.hw02.task03.sorters.Sorter;
import ru.innopolis.tasks.hw02.task03.sorters.SorterByComparator;
import ru.innopolis.tasks.hw02.task03.sorters.SorterQSort;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Дан массив объектов Person. Класс Person характеризуется полями age (возраст, целое число 0-100),
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка).
 * <p>
 * <ul>
 * <li>Создать два класса, методы которых будут реализовывать сортировку объектов.</li>
 * <li>Предусмотреть единый интерфейс для классов сортировки.</li>
 * <li>Реализовать два различных метода сортировки этого массива по правилам:</li>
 * </ul>
 * <p>
 * первые идут мужчины<br/>
 * выше в списке тот, кто более старший<br/>
 * имена сортируются по алфавиту<br/>
 * <p>
 * Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 * <p>
 * Предусмотреть генерацию исходного массива (10000 элементов и более).
 * <p>
 * Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 * <p>
 * * Выполнил:
 * * Щенников Г. О.
 */
public class PeopleSorting {

    private static final int AMOUNT_OF_PEOPLE = 10;

    public static void main(String[] args) {

        Person[] people = new Person[AMOUNT_OF_PEOPLE];

        fillPeople(people);

        printPeople(people);

//        Sorter sorterByComparator = new SorterByComparator();
//        sorterByComparator.sortPeople(people);
        Sorter sorterQSort = new SorterQSort();

        printPeople(sorterQSort.sortPeople(Arrays.asList(people)));

    }

    @Deprecated
    private static void printPeople(Person[] people) {
        System.out.println("\n************");
        for (Person p : people) {
            System.out.println(p);
        }
    }


    private static void fillPeople(Person[] people) {

        SecureRandom random = new SecureRandom();

        int maxAge = 100;
        String namePart = "citizen_";

        for (int i = 0; i < AMOUNT_OF_PEOPLE; i++) {

            int age = random.nextInt(maxAge + 1);
            Sex s = new Sex(Sex.getRandomGender());

            Person p = new Person(age, s, namePart + generateNamePart(random));
            people[i] = p;

        }

    }


    private static String generateNamePart(SecureRandom random) {

        StringBuilder sb = new StringBuilder();
        int partLength = 5;

        for (int i = 0; i < partLength; i++) {
            sb.append((char) (65 + random.nextInt(26)));
        }

        return sb.toString();
    }

}
