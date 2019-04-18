package ru.innopolis.tasks.hw02.task01;

import java.io.IOException;

/**
 * Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой.
 * <p>
 * Смоделировав ошибку «NullPointerException»
 * Смоделировав ошибку «ArrayIndexOutOfBoundsException»
 * Вызвав свой вариант ошибки через оператор throw
 *
 * Щенников Г. О.
 */

public class HelloWorld {

    public static void main(String[] args) {

        HelloWorld hw = new HelloWorld();

        try {
            hw.generateNPE();

        } catch (RuntimeException e) {
            printMessage();
            e.printStackTrace();
        }

        try {
            hw.generateArrayIndexOutOfBoundsException();

        } catch (RuntimeException e) {
            printMessage();
            e.printStackTrace();
        }

        try {
            hw.generateMyThrow();

        } catch (IOException e) {
            printMessage();
            e.printStackTrace();
        }

    }

    /**
     * Моделирование ошибки NullPointerException
     */
    private void generateNPE() {

        String str = null;
        str.length();

    }

    /**
     * Моделирование ошибки ArrayIndexOutOfBoundsException
     */
    private void generateArrayIndexOutOfBoundsException() {

        int[] arr = new int[5];
        arr[5] = 1;

    }

    /**
     * Вызов своего варианта ошибки с использованием throw
     * @throws IOException пробрасывание в учебных целях
     */
    private void generateMyThrow() throws IOException {

        throw new IOException();

    }

    /**
     * Вспомогательный метод для вывода фразы на экран
     */
    private static void printMessage() {

        System.out.println("\nВот что случилось:");

    }

}
