package ru.innopolis.tasks.hw04.comparators;

import ru.innopolis.tasks.hw04.entities.Animal;

import java.util.Comparator;

/**
 * Компаратор для сравнения по владельцам объектов Animal
 */
public class ComparatorByAnimalOwner implements Comparator<Animal> {

    @Override
    public int compare(Animal a1, Animal a2) {
        return a1.getOwner().compareTo(a2.getOwner());
    }

}
