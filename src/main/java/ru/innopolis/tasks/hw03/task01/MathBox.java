package ru.innopolis.tasks.hw03.task01;

import java.util.*;

/**
 * Класс содержащий уникальные цифроввые значения
 */
public class MathBox {

    /**
     * Хранилиище уникальных цифровых значений
     */
    private Set<Number> numbers;

    public MathBox(Number[] numbers) {
        this.numbers = new HashSet<>(Arrays.asList(numbers));
    }


    /**
     * Расчёт суммы значений
     *
     * @return сумма всех элементов
     */
    public Double summator() {

        double summ = 0d;

        for (Number n : numbers) {
            summ += n.doubleValue();
        }

        return summ;
    }

    /**
     * Деление всех элементов на один делитель
     *
     * @param arg общий делитель
     */
    public void splitter(int arg) {

        Set<Number> tmpSet = new HashSet<>();

        for (Number n : numbers) {
            tmpSet.add(n.doubleValue() / arg);
        }

        numbers = tmpSet;

    }

    /**
     * Находит и удаляет целочисленное значение
     *
     * @param i значение для удаления
     */
    public void integerDeleter(Integer i) {

        Iterator<Number> iterator = numbers.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().intValue() == i) {
                iterator.remove();
            }
        }

    }

    @Override
    public int hashCode() {

        int hash = 1;

        for (Number n : numbers) {
            hash = (int) (hash * (n.intValue() + 1) + summator());
        }

        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof MathBox)) return false;

        MathBox objBox = (MathBox) obj;

        if (this.hashCode() != objBox.hashCode()) return false;

        return this.summator().doubleValue() == objBox.summator().doubleValue();

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Mathbox:\n");

        for (Number n : numbers) {
            sb.append(n).append(", ");
        }

        return sb.toString().substring(0, sb.toString().length() - 2);
    }

}
