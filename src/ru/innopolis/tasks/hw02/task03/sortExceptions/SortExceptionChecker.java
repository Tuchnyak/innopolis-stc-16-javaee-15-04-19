package ru.innopolis.tasks.hw02.task03.sortExceptions;

import ru.innopolis.tasks.hw02.task03.entities.Person;

/**
 * Класс для проверки двух объектов Person на одинаковые имена и возраст
 */
public class SortExceptionChecker {


    /**
     * Проверка двух объектов Person на одинаковые имена и возраст
     * @param p1 первый объект для сравнения
     * @param p2 второй объект для сравнения
     * @throws SortException пробрасываемое исключение, если имена и возраст двух объектов Person совпадают
     */
    public static void checkNameAge(Person p1, Person p2) throws SortException {

        if (p1.getName().equals(p2.getName()) && p1.getAge() == p2.getAge())
            throw new SortException("\n**********\nИмя и возраст двух человек полностью совпадают!\n"
                    + p1.getName() + " " + p1.getAge()
                    + "\n" + p2.getName() + " " + p2.getAge() +
                    "\n**********");

    }

}
