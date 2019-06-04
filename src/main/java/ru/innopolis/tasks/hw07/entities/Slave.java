package ru.innopolis.tasks.hw07.entities;

import java.io.Serializable;

public class Slave implements Serializable {

    private String name;
    private int age;
    private boolean hasMaster;

    public Slave(String name, int age, boolean hasMaster) {
        this.name = name;
        this.age = age;
        this.hasMaster = hasMaster;
    }

    public void setHasMaster(boolean hasMaster) {
        this.hasMaster = hasMaster;
    }

    @Override
    public String toString() {
        return "Slave{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hasMaster=" + hasMaster +
                '}';
    }
}
