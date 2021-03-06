package ru.innopolis.tasks.hw02.task02;

import java.security.SecureRandom;

/**
 * Составить программу, генерирующую N случайных чисел.
 * Для каждого числа k вычислить квадратный корень q.
 * Если квадрат целой части (q числа) равен k, то вывести это число на экран.
 * Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 * <p>
 *
 * @author Georgii Shchennikov
 */

public class NRandom {

    /**
     * Константа для числа генерируемых элементов
     */
    private static final int N_RANDOM_NUMBERS = 100000;

    public static void main(String[] args) {

        int[] arr = new int[N_RANDOM_NUMBERS];

        fillArray(arr);

        printAllQ(arr);

    }


    /**
     * Метод, содержащий логику задания
     *
     * @param arr work to with
     */
    private static void printAllQ(int[] arr) {

        for (int i : arr) {

            try {
                int q = getRoot(i);

                if (i == (int) Math.pow(q, 2)) {
                    System.out.println(q);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }


    /**
     * Метод для вычисления квадратного корня и генерации исключения для отрицательных чисел
     *
     * @param i число для вычисления корня
     * @return результат вычисления корня, приведённый к int
     * @throws Exception при отрицательном i
     */
    private static int getRoot(int i) throws NumberFormatException {

        if (i < 0) throw new NumberFormatException("Отрицательное значение!");

        return (int) Math.sqrt(i);
    }


    /**
     * Заполнение массива случайными целыми числами
     *
     * @param arr ссылка на массив для заполнения
     */
    private static void fillArray(int[] arr) {

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < N_RANDOM_NUMBERS; i++) {

            arr[i] = random.nextInt();

        }

    }


}
