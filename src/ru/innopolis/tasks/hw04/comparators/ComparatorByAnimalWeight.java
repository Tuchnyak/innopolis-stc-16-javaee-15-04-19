package ru.innopolis.tasks.hw04.comparators;

import ru.innopolis.tasks.hw04.entities.Animal;

import java.util.Comparator;

public class ComparatorByAnimalWeight implements Comparator<Animal> {

    @Override
    public int compare(Animal a1, Animal a2) {
        return Double.compare(a1.getWeight(), a2.getWeight());
    }

}
