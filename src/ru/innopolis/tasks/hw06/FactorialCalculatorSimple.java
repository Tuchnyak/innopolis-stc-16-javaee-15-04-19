package ru.innopolis.tasks.hw06;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class FactorialCalculatorSimple implements Callable<BigInteger> {

    private int n;
    private Map<Integer, BigInteger> factorials;

    public <T extends ConcurrentHashMap<Integer, BigInteger>> FactorialCalculatorSimple(int n, T factorials) {
        this.n = n;
        this.factorials = factorials;
    }

    @Override
    public BigInteger call() {

        if (factorials.containsKey(n)) return factorials.get(n);

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

        if (isKeysContainsValueLessThanN) {
            return calculateFactorialWithPart(lessKey);
        }

        return calculateFullFactorial();
    }

    private BigInteger calculateFactorialWithPart(int lessKey) {

        BigInteger fact = factorials.get(lessKey);

        for (int i = lessKey + 1; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }

        return fact;
    }

    private BigInteger calculateFullFactorial() {

        BigInteger fact = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }

        return fact;
    }

}
