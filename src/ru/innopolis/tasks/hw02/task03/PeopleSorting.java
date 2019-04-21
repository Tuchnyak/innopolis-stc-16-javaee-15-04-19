package ru.innopolis.tasks.hw02.task03;

import ru.innopolis.tasks.hw02.task03.entities.Person;
import ru.innopolis.tasks.hw02.task03.entities.Sex;
import ru.innopolis.tasks.hw02.task03.sorters.Sorter;
import ru.innopolis.tasks.hw02.task03.sorters.SorterByComparator;
import ru.innopolis.tasks.hw02.task03.sorters.SorterByQSort;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Дан массив объектов Person. Класс Person характеризуется полями age (возраст, целое число 0-100),
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка).
 * <br>
 * <ul>
 * <li>Создать два класса, методы которых будут реализовывать сортировку объектов.</li>
 * <li>Предусмотреть единый интерфейс для классов сортировки.</li>
 * <li>Реализовать два различных метода сортировки этого массива по правилам:</li>
 * </ul>
 * <br>
 * первые идут мужчины<br>
 * выше в списке тот, кто более старший<br>
 * имена сортируются по алфавиту<br>
 * <p>
 * Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 * <p>
 * Предусмотреть генерацию исходного массива (10000 элементов и более).
 * <p>
 * Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 * <p>
 *
 * @author Georgii Shchennikov
 */
public class PeopleSorting {

    /**
     * Константа для хранения количества объектов Person
     */
    private static final int AMOUNT_OF_PEOPLE = 10001;


    public static void main(String[] args) {

        Person[] people = new Person[AMOUNT_OF_PEOPLE];

        fillPeople(people);

        System.out.println("===>>> Быстрая сортировка:");
        Sorter sorterQSort = new SorterByQSort();
        Person[] peopleSortedByQSort = sorterQSort.sortPeople(Arrays.asList(people));
        printPeople(peopleSortedByQSort);

        System.out.println("\n===>>> Сортировка компараторами:");
        Sorter sorterComparator = new SorterByComparator();
        Person[] peopleSortedByComparator = sorterComparator.sortPeople(Arrays.asList(people));
        printPeople(peopleSortedByComparator);

    }


    /**
     * Метод для вывода в консоль массива
     * @param people массив для вывода
     */
    private static void printPeople(Person[] people) {
        System.out.println("\n************");
        for (Person p : people) {
            System.out.println(p);
        }
    }


    /**
     * Метод для наполнения массива объектами Person со случайным содержимым
     * @param people массив для заполнения
     */
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


    /**
     * Метод для генерации случайной строки состоящей из последовательности заглавных латинских букв
     * @param random объект SecureRandom для генерации случайного целого числа
     * @return случайная строка
     */
    private static String generateNamePart(SecureRandom random) {

        StringBuilder sb = new StringBuilder();
        int partLength = 5;

        for (int i = 0; i < partLength; i++) {
            sb.append((char) (65 + random.nextInt(26)));
        }

        return sb.toString();
    }


}
