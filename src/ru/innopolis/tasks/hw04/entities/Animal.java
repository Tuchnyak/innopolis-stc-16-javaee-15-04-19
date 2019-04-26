package ru.innopolis.tasks.hw04.entities;

import java.util.Objects;

/**
 * У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 */
public class Animal {

    private int id;
    private String nickname;
    private double weight;
    private Person owner;

    public Animal(String nickname, double weight, Person owner) {
        id = 0;
        this.nickname = nickname.toLowerCase();
        this.weight = weight;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id == 0)
            this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname.toLowerCase();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0 &&
                Objects.equals(nickname, animal.nickname) &&
                Objects.equals(owner, animal.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, weight, owner);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", weight=" + weight +
                ", owner=" + owner +
                '}';
    }

}
