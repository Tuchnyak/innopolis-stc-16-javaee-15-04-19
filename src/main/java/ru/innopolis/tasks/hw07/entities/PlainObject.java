package ru.innopolis.tasks.hw07.entities;

/**
 * Класс для "плоского" объекта
 */
public class PlainObject {

    private String model;
    private String type;
    private char sign;
    private int amount;
    private double mass;
    private boolean isNew;

    public PlainObject(String model, String type, char sign, int amount, double mass, boolean isNew) {
        this.model = model;
        this.type = type;
        this.sign = sign;
        this.amount = amount;
        this.mass = mass;
        this.isNew = isNew;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public char getSign() {
        return sign;
    }

    public int getAmount() {
        return amount;
    }

    public double getMass() {
        return mass;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return "PlainObject{" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", sign=" + sign +
                ", amount=" + amount +
                ", mass=" + mass +
                ", isNew=" + isNew +
                '}';
    }
}
