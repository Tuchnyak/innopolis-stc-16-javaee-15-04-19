package ru.innopolis.tasks.hw03.task01;

import java.util.Arrays;

/**
 * Задание 1. Написать класс MathBox, реализующий следующий функционал:
 * <p>
 * Конструктор на вход получает массив Number. Элементы не могут повторяться.
 * Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 * Существует метод summator, возвращающий сумму всех элементов коллекции.
 * Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель,
 * являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 * Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox
 * для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap).
 * Выполнение контракта обязательно!
 * Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 *
 * @author Georgii Shchennikov
 */

public class Hw03Task01 {

    public static void main(String[] args) {

        Number[] numbers = {0f, 1f, 2f, 3f};

        MathBox mathBox = new MathBox(numbers);
        MathBox mathBox1 = new MathBox(numbers);

        System.out.println("Проверяем метод equals()");
        System.out.println(mathBox.equals(mathBox1));
        System.out.println();

        System.out.println("Проверяем метод hashCode()");
        System.out.println(mathBox.hashCode());
        System.out.println(mathBox1.hashCode());
        System.out.println();

        System.out.println("Выводим результат работы метода summator()");
        System.out.println(mathBox.summator());
        System.out.println();

        mathBox.splitter(4);

        System.out.println("Проверяем метод equals() после работы splitter()");
        System.out.println(mathBox.equals(mathBox1));
        System.out.println();

        System.out.println("Проверяем toString()");
        System.out.println(mathBox);
        System.out.println(mathBox1);
        System.out.println();

        mathBox1.integerDeleter(1);
        System.out.println("Выводим объект после работы метода integerDeleter()");
        System.out.println(mathBox1);

    }

}
