package ru.innopolis.tasks.hw04.comparators;

import ru.innopolis.tasks.hw04.entities.Animal;

import java.util.Comparator;

/**
 * Компаратор для сравнения по кличкам объектов Animal
 */
public class ComparatorByAnimalNickname implements Comparator<Animal> {

    @Override
    public int compare(Animal a1, Animal a2) {
        return a1.getNickname().compareTo(a2.getNickname());
    }

}
