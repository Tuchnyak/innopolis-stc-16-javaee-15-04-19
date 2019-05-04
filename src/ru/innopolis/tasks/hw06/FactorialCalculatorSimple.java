package ru.innopolis.tasks.hw06;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Класс для вычисления факториала числа n.
 */
public class FactorialCalculatorSimple implements Callable<BigInteger> {

    private int n;
    private Map<Integer, BigInteger> factorials;
    private CountDownLatch countDownLatch;

    public <T extends ConcurrentHashMap<Integer, BigInteger>> FactorialCalculatorSimple(int n, T factorials, CountDownLatch countDownLatch) {
        this.n = n;
        this.factorials = factorials;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public BigInteger call() {

        if (factorials.containsKey(n)) {
            countDownLatch.countDown();
            return factorials.get(n);
        }

        Set<Integer> keys = new TreeSet<>(factorials.keySet());
        boolean isKeysContainsValueLessThanN = false;
        int lessKey = 0;

        for (int x : keys) {
            if (x < n) {
                isKeysContainsValueLessThanN = true;
                lessKey = x;
            }
            if (x > lessKey) break;
        }

        BigInteger result;

        if (isKeysContainsValueLessThanN) {
            result = calculateFactorialWithPart(lessKey);
        } else {
            result = calculateFullFactorial();
        }

        factorials.put(n, result);

        countDownLatch.countDown();

        return result;
    }

    /**
     * Вычисление факториала на основе предыдущих вычислений
     *
     * @param lessKey число, для которого есть значение посчитанного факториала
     * @return факториал n
     */
    private BigInteger calculateFactorialWithPart(int lessKey) {

        BigInteger fact = factorials.get(lessKey);

        for (int i = lessKey + 1; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }

        return fact;
    }

    /**
     * Вычисление факториала, для которого нет подходящих предыдущих вычислений
     *
     * @return факториал n
     */
    private BigInteger calculateFullFactorial() {

        BigInteger fact = BigInteger.valueOf(1);

        for (int i = 2; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }

        return fact;
    }

}
