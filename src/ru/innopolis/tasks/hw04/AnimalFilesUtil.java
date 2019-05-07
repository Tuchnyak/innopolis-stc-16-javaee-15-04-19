package ru.innopolis.tasks.hw04;

import ru.innopolis.tasks.hw02.task03.entities.Sex;
import ru.innopolis.tasks.hw04.comparators.ComparatorByAnimalNickname;
import ru.innopolis.tasks.hw04.comparators.ComparatorByAnimalOwner;
import ru.innopolis.tasks.hw04.comparators.ComparatorByAnimalWeight;
import ru.innopolis.tasks.hw04.entities.Animal;
import ru.innopolis.tasks.hw04.entities.Person;

import java.io.InvalidObjectException;
import java.util.*;

/**
 * Класс для обеспечения функциональности картотеки
 */
public class AnimalFilesUtil {

    /**
     * Общий счёт записей в картотеке
     */
    private static int INDEX_COUNT = 0;

    /**
     * Метод добавления новой записи о животтном
     *
     * @param animal    объект для добавления
     * @param animals   основное хранилище
     * @param nicknames отображение кличек на ID
     * @throws InvalidObjectException исключение, генерирующееся при наличии записи в картотеке
     */
    public static void addAnimalToFiles(Animal animal, Map<Integer, Animal> animals, Map<String, Set<Integer>> nicknames) throws InvalidObjectException {

        if (!animals.values().contains(animal)) {
            animal.setId(INDEX_COUNT);
            animals.put(INDEX_COUNT, animal);

            Set<Integer> tmpNickNameIds = nicknames.get(animal.getNickname());
            if (tmpNickNameIds == null) {
                tmpNickNameIds = new HashSet<>();
                nicknames.put(animal.getNickname(), tmpNickNameIds);
            }

            tmpNickNameIds.add(INDEX_COUNT);

            INDEX_COUNT++;
        } else {
            throw new InvalidObjectException("Такой питомец уже заведён в картотеку!");
        }

    }

    /**
     * Поиск о картотеке всех животных с определённой кличкой
     *
     * @param animals   основное хранилище
     * @param nicknames отображение кличек на ID
     * @param nickname  клички для поиска
     * @return список найденных животных с указанной кличкой или пустой список
     */
    public static List<Animal> searchRecordByNickname(String nickname, Map<Integer, Animal> animals, Map<String, Set<Integer>> nicknames) {

        List<Animal> listToReturn = new ArrayList<>();
        String nickLower = nickname.toLowerCase();
        Set<Integer> ids = nicknames.get(nickLower);

        if (ids == null)
            return listToReturn;

        for (int i : ids) {
            listToReturn.add(animals.get(i));
        }

        return listToReturn;
    }

    /**
     * Редактирование записи о животном по ID
     *
     * @param animalId  номер записи для получения нужного объекта
     * @param nickName  новая кличка
     * @param weight    новый вес
     * @param ownerName новое имя владельца
     * @param ownerSex  новый пол владельца
     * @param ownerAge  новый возраст владельца
     * @param animals   основное хранилище
     * @param nicknames отображение кличек на ID
     */
    public static void editRecordById(int animalId,
                                      String nickName,
                                      double weight,
                                      String ownerName,
                                      Sex ownerSex,
                                      int ownerAge,
                                      Map<Integer, Animal> animals,
                                      Map<String, Set<Integer>> nicknames) {

        Animal animalToEdit = animals.get(animalId);

        boolean isNicknameChanged = false;

        if (!animalToEdit.getNickname().equals(nickName))
            isNicknameChanged = true;

        if (isNicknameChanged) {
            Set<Integer> tmpNickNameIdsToDelete = nicknames.get(animalToEdit.getNickname());
            tmpNickNameIdsToDelete.remove(animalToEdit.getId());
            Set<Integer> tmpNickNameIdsToAdd = null;
            if (!nicknames.containsKey(nickName)) {
                tmpNickNameIdsToAdd = new HashSet<>();
                nicknames.put(nickName, tmpNickNameIdsToAdd);
            } else {
                tmpNickNameIdsToAdd = nicknames.get(nickName);
            }
            tmpNickNameIdsToAdd.add(animalId);
        }

        Person owner = new Person(ownerAge, ownerSex, ownerName);

        animalToEdit.setNickname(nickName);
        animalToEdit.setWeight(weight);
        animalToEdit.setOwner(owner);

    }

    /**
     * Вывод в консоль отсортированного списка записей о животных
     *
     * @param animals отображение для поиска
     */
    public static void printAnimals(Map<Integer, Animal> animals) {

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
