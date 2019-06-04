package ru.innopolis.tasks.hw04.comparators;

import java.util.Comparator;
import java.util.Map;

/**
 * Компаратор для сравнения по кличкам в составе Map.Entry
 */
public class ComparatorByNickNameInMapEntry implements Comparator<Map.Entry<Integer, String>> {

    @Override
    public int compare(Map.Entry<Integer, String> e1, Map.Entry<Integer, String> e2) {

        return e1.getValue().compareTo(e2.getValue());
    }

}
