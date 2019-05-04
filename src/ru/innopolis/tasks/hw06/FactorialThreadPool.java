package ru.innopolis.tasks.hw06;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.*;

/**
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива.
 * Использовать пул потоков для решения задачи.
 * <p>
 * Особенности выполнения:
 * <p>
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти,
 * очень вероятен StackOverFlow. Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 * <p>
 * По сути, есть несколько способа решения задания:
 * <p>
 * 1) распараллеливать вычисление факториала для одного числа
 * <p>
 * 2) распараллеливать вычисления для разных чисел
 * <p>
 * 3) комбинированный
 * <p>
 * При чем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого,
 * что будет гораздо быстрее
 *
 * @author Georgii Shchennikov
 */
public class FactorialThreadPool {

    /**
     * Константа длины массива случайных чисел
     */
    private static final int ARRAY_LENGTH = 1000;

    /**
     * Ограничение максимального значения случайного числа для массива
     */
    private static final int RANDOM_BOUND = 1000;

    /**
     * Флаг использования верхнего ограничения для случайных чисел
     */
    private static final boolean USE_RANDOM_BOUND = true;

    /**
     * Массив случайных чисел
     */
    private static final int[] ARRAY_OF_RANDOMS;

    /**
     * Коллекция для хранения вычисленных значений факториалов
     */
    private static ConcurrentHashMap<Integer, BigInteger> factorials;

    static {
        ARRAY_OF_RANDOMS = generateArrayOfRandoms();
        factorials = new ConcurrentHashMap<>();
    }


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CountDownLatch countDownLatch = new CountDownLatch(ARRAY_LENGTH);

        calculateFactorials(executorService, countDownLatch);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printResults();

    }

    /**
     * Запуск многопоточного вычисления факториалов для содержимого массива случайных чисел
     *
     * @param executorService объект Исполнителя для запуска потоков производящих вычисления
     * @param countDownLatch  объект, блокирующий главный поток до тех пор, пока не завершатся все вычисления
     */
    private static void calculateFactorials(ExecutorService executorService, CountDownLatch countDownLatch) {

        for (int i : ARRAY_OF_RANDOMS) {
            executorService.submit(new FactorialCalculatorSimple(i, factorials, countDownLatch));
        }

        executorService.shutdown();
    }

    /**
     * Вывод в консоль сгенерированного массива случайных целых чисел и результатов вычислений
     */
    private static void printResults() {

        Map<Integer, BigInteger> tmpMap = new TreeMap<>(factorials);

        System.out.println("Результаты вычислений (число | факториал):");
        for (Map.Entry<Integer, BigInteger> entry : tmpMap.entrySet()) {
            System.out.println(String.valueOf(entry.getKey()).concat(" | ").concat(String.valueOf(entry.getValue())));
        }

    }

    /**
     * Метод генерации массива случайных чисел
     *
     * @return массив случайных чисел длиной ARRAY_LENGTH
     */
    private static int[] generateArrayOfRandoms() {

        int[] arr = new int[ARRAY_LENGTH];

        SecureRandom random = new SecureRandom();

        if (USE_RANDOM_BOUND) {
            for (int i = 0; i < ARRAY_LENGTH; i++) {
                arr[i] = random.nextInt(RANDOM_BOUND + 1);
            }
        } else {
            for (int i = 0; i < ARRAY_LENGTH; i++) {
                arr[i] = random.nextInt();
            }
        }

        return arr;
    }


}
