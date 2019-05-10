package ru.innopolis.tasks.hw03.task03;

import java.util.*;

/**
 * Класс содержащий уникальные цифроввые значения
 */
public class MathBox extends ObjectBox {

    public MathBox(Number[] numbers) {

        super(new HashSet<>(Arrays.asList(numbers)));

    }

    @Override
    public void addObject(Object o) {
        if (o instanceof Number) {
            super.addObject(o);
        } else {
            throw new NumberFormatException("Нельзя добавлять не Number!");
        }
    }

    @Override
    public boolean deleteObject(Object o) {
        return super.deleteObject(o);
    }

    @Override
    public String dump() {
        return super.dump();
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

        setObjects(tmpSet);

    }

    /**
     * Находит и удаляет целочисленное значение
     *
     * @param i значение для удаления
     */
    public void integerDeleter(Integer i) {

        getObjects().removeIf(o -> ((Number) o).intValue() == i);

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
