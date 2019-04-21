package ru.innopolis.tasks.hw02.task03.entities;

import java.security.SecureRandom;

/**
 * Класс для организации соответствующего поля в Person
 */
public class Sex {

    // константы признаков пола
    public static final String MAN = "man";
    public static final String WOMAN = "woman";

    private String gender;

    public Sex(String gender) {
        this.gender = gender.toLowerCase();
    }

    public String getSexValue() {
        return gender;
    }

    /**
     * Метод для генерации случайного пола
     * @return случайно сгенерированный пол Sex.WOMAN или Sex.MAN
     */
    public static String getRandomGender() {
        SecureRandom random = new SecureRandom();
        int sign = random.nextInt(2);

        if (sign == 0) return MAN;

        return WOMAN;
    }

}
