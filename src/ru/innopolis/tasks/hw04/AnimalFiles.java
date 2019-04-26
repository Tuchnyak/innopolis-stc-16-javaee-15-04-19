package ru.innopolis.tasks.hw04;

import ru.innopolis.tasks.hw04.comparators.*;
import ru.innopolis.tasks.hw04.entities.Person;
import ru.innopolis.tasks.hw02.task03.entities.Sex;
import ru.innopolis.tasks.hw04.entities.Animal;

import java.io.InvalidObjectException;
import java.util.*;

/**
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 * <p>
 * Реализовать:
 * - метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * - поиск животного по его кличке (поиск должен быть эффективным)
 * - изменение данных животного по его идентификатору
 * - вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 *
 * @author Georgii Shchennikov
 */
public class AnimalFiles {

    /**
     * Общий счёт записей в картотеке
     */
    private static int INDEX_COUNT = 0;

    /**
     * Отображение для хранения записей о животных с уникальными ID
     */
    private static Map<Integer, Animal> animals;

    /**
     * Отображение, параллельное основному, для получения ID при поиске по кличке
     */
    private static Map<Integer, String> nicknames;

    static {
        animals = new TreeMap<>();
        nicknames = new TreeMap<>();
    }


    public static void main(String[] args) {

        System.out.println("Проверим печать пустого каталога");
        Util.printAnimals();
        System.out.println();

        Animal a1 = new Animal("Barsik", 5d, new Person(78, new Sex(Sex.WOMAN), "Babushka Klava"));
        Animal a2 = new Animal("Pogrom", 3.5d, new Person(25, new Sex(Sex.MAN), "Semyon Polishchuk"));
        Animal a3 = new Animal("Marsel", 1.5, new Person(25, new Sex(Sex.MAN), "Ross Geller"));
        Animal a4 = new Animal("Barsik", 4.5, new Person(25, new Sex(Sex.MAN), "Dmitry Potapenko"));

        try {
            Util.addAnimalToFiles(a1);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        try {
            Util.addAnimalToFiles(a2);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        try {
            Util.addAnimalToFiles(a3);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }
        try {
            Util.addAnimalToFiles(a4);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

        System.out.println("Проверим печать заполненного каталога");
        Util.printAnimals();
        System.out.println();

        System.out.println("Попробуем найти в каталоге Барсика");
        List<Animal> search = Util.searchRecordByNickname("Barsik");
        for (Animal a : search) {
            System.out.println(a);
        }
        System.out.println();

        System.out.println("Попробуем отредактировать запись №2");
        Util.editRecordById(2, "Marsel Star", 2d, "Excecutive producer", new Sex(Sex.MAN), 46);
        Util.printAnimals();
        System.out.println();

        System.out.println("Попробуем добавить животное во второй раз в виде нового объекта");
        Animal a2Again = new Animal("Pogrom", 3.5d, new Person(25, new Sex(Sex.MAN), "Semyon Polishchuk"));
        try {
            Util.addAnimalToFiles(a2Again);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }


    }


    /**
     * Вложенный класс для обеспечения функциональности картотеки
     */
    private static class Util {

        /**
         * Метод добавления новой записи о животтном
         * @param animal объект для добавления
         * @throws InvalidObjectException исключение, генерирующееся при наличии записи в картотеке
         */
        public static void addAnimalToFiles(Animal animal) throws InvalidObjectException {

            if (!animals.values().contains(animal)) {
                animal.setId(INDEX_COUNT);
                animals.put(INDEX_COUNT, animal);
                nicknames.put(INDEX_COUNT, animal.getNickname());
                INDEX_COUNT++;
            } else {
                throw new InvalidObjectException("Такой питомец уже заведён в картотеку!");
            }

        }

        /**
         * Поиск о картотеке всех животных с определённой кличкой
         * @param nickname клички для поиска
         * @return список найденных животных с указанной кличкой или пустой список
         */
        public static List<Animal> searchRecordByNickname(String nickname) {

            List<Animal> listToReturn = new ArrayList<>();
            String nickLower = nickname.toLowerCase();

            List<Map.Entry<Integer, String>> nicknamesEntries = new ArrayList<>(nicknames.entrySet());
            nicknamesEntries.sort(new ComparatorByNickNameInMapEntry());

            int firstEntryInList = findFirstEntryPositionByBinarySearch(nickLower, nicknamesEntries);

            if (firstEntryInList >= 0) {

                for (int i = firstEntryInList; i < nicknamesEntries.size(); i++) {
                    if (nicknamesEntries.get(i).getValue().equals(nickLower))
                        listToReturn.add(animals.get(nicknamesEntries.get(i).getKey()));
                    else
                        break;
                }

                if (firstEntryInList > 0) {
                    for (int i = firstEntryInList - 1; i >= 0; i--) {
                        if (nicknamesEntries.get(i).getValue().equals(nickLower))
                            listToReturn.add(animals.get(nicknamesEntries.get(i).getKey()));
                        else
                            break;
                    }
                }

            }

            if (!listToReturn.isEmpty())
                listToReturn.sort(new ComparatorByAnimalId());

            return listToReturn;
        }

        /**
         * Вспомогательный метод бинарного поиска для нахождения совпадения по кличке
         * @param nickname кличка для поиска
         * @param nicknamesEntries список для поиска
         * @return позиция в списке, -1 - если кличка не найдена в списке
         */
        private static int findFirstEntryPositionByBinarySearch(String nickname, List<Map.Entry<Integer, String>> nicknamesEntries) {

            int low = 0;
            int max = nicknamesEntries.size() - 1;

            while (low <= max) {

                int mid = (max - low) / 2;
                Map.Entry<Integer, String> guess = nicknamesEntries.get(mid);

                if (guess.getValue().equals(nickname.toLowerCase())) return mid;

                if (guess.getValue().compareTo(nickname) > 0) max = mid - 1;
                else low = mid + 1;

            }

            return -1;
        }

        /**
         * Редактирование записи о животном по ID
         * @param animalId номер записи для получения нужного объекта
         * @param nickName новая кличка
         * @param weight новый вес
         * @param ownerName новое имя владельца
         * @param ownerSex новый пол владельца
         * @param ownerAge новый возраст владельца
         */
        private static void editRecordById(int animalId, String nickName, double weight, String ownerName, Sex ownerSex, int ownerAge) {

            Animal animalToEdit = animals.get(animalId);

            Person owner = new Person(ownerAge, ownerSex, ownerName);

            animalToEdit.setNickname(nickName);
            animalToEdit.setWeight(weight);
            animalToEdit.setOwner(owner);

            nicknames.put(animalId, nickName.toLowerCase());

        }

        /**
         * Вывод в консоль отсортированного списка записей о животных
         */
        private static void printAnimals() {

            List<Animal> list = new ArrayList<>(animals.values());

            list.sort(new ComparatorByAnimalWeight());
            list.sort(new ComparatorByAnimalNickname());
            list.sort(new ComparatorByAnimalOwner());

            if (animals.isEmpty()) {
                System.out.println("===>>> Каталог пуст!");
            } else {
                for (Animal a : list) {
                    System.out.println("Хозяин: " + a.getOwner().getName()
                            + ", кличка питомца: " + a.getNickname()
                            + ", вес: " + a.getWeight()
                            + ", запись номер: " + a.getId());
                }
            }

        }
    }


}
