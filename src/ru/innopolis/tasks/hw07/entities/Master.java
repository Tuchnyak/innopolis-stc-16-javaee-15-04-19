package ru.innopolis.tasks.hw07.entities;

import java.io.Serializable;

public class Master implements Serializable {

    private String name;
    private int age;
    private Slave slave;

    public Master(String name, int age, Slave slave) {
        this.name = name;
        this.age = age;
        this.slave = slave;
        this.slave.setHasMaster(true);
    }

    @Override
    public String toString() {
        return "Master{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", slave=" + slave +
                '}';
    }

}
