package ru.innopolis.tasks.hw03.task03;

import java.util.*;

/**
 * Класс содержащий уникальные цифроввые значения
 */
public class MathBox<T extends Number> extends ObjectBox<T> {

    public MathBox(Number[] numbers) {

        super(new HashSet<>((Collection<? extends T>) Arrays.asList(numbers)));

    }

    /**
     * Расчёт суммы значений
     *
     * @return сумма всех элементов
     */
    public Double summator() {

        double summ = 0d;

        for (Object n : getObjects()) {
            summ += ((Number) n).doubleValue();
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

        for (Object n : getObjects()) {
            tmpSet.add(((Number) n).doubleValue() / arg);
        }

        setObjects((Collection<T>) tmpSet);

    }

    /**
     * Находит и удаляет целочисленное значение
     *
     * @param i значение для удаления
     */
    public void integerDeleter(Integer i) {

        getObjects().removeIf(o -> o.intValue() == i);

    }

    @Override
    public int hashCode() {

        int hash = 1;

        for (Object n : getObjects()) {
            hash = (int) (hash * (((Number) n).intValue() + 1) + summator());
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

        for (Object n : getObjects()) {
            sb.append(n).append(", ");
        }

        return sb.toString().substring(0, sb.toString().length() - 2);
    }

}
