package ru.innopolis.tasks.hw02.task03.entities;

import java.security.SecureRandom;

public class Sex {

    public static final String MAN = "man";
    public static final String WOMAN = "woman";

    private String gender;

    public Sex(String gender) {
        this.gender = gender.toLowerCase();
    }

    public String getSexValue() {
        return gender;
    }

    public static String getRandomGender() {
        SecureRandom random = new SecureRandom();
        int sign = random.nextInt(2);

        if (sign == 0) return MAN;

        return WOMAN;
    }

}
