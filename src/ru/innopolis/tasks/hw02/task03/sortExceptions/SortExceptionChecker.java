package ru.innopolis.tasks.hw02.task03.sortExceptions;

import ru.innopolis.tasks.hw02.task03.entities.Person;

public class SortExceptionChecker {

    public static void checkNameAge(Person p1, Person p2) throws SortException {

        if (p1.getName().equals(p2.getName()) && p1.getAge() == p2.getAge())
            throw new SortException("\n**********\nИмя и возраст двух человек полностью совпадают!\n"
                    + p1.getName() + " " + p1.getAge()
                    + "\n" + p2.getName() + " " + p2.getAge() +
                    "\n**********");

    }

}
