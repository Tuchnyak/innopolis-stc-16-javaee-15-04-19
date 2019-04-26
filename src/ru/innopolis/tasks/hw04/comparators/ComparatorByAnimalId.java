package ru.innopolis.tasks.hw04.comparators;

import ru.innopolis.tasks.hw04.entities.Animal;

import java.util.Comparator;

/**
 * Компаратор для сравнения по ID объектов Animal
 */
public class ComparatorByAnimalId implements Comparator<Animal> {

    @Override
    public int compare(Animal a1, Animal a2) {
        return Integer.compare(a1.getId(), a2.getId());
    }

}
