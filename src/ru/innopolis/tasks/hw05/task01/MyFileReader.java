package ru.innopolis.tasks.hw05.task01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Написать программу, читающую текстовый файл. Программа должна составлять отсортированный по алфавиту список слов,
 * найденных в файле и сохранять его в файл-результат. Найденные слова не должны повторяться, регистр не должен учитываться.
 * Одно слово в разных падежах – это разные слова.
 */
public class MyFileReader {

    /**
     * Константа для пути к файлу-источнику
     */
    private static final String SOURCE_PATH = "./src/ru/innopolis/tasks/hw05/task01/hw05task01text_source.txt";
    /**
     * Константа для пути к файлу для записи
     */
    private static final String DESTINATION_PATH = "./src/ru/innopolis/tasks/hw05/task01/hw05task01text_destination.txt";
    /**
     * Константа для коллекции с символами, которые не должны войти в строку
     */
    private static final Set<Character> CHARS_TO_IGNORE;

    /**
     * Множество для хранения слов
     */
    private static Set<String> words;

    /**
     * StringBuilder для считывания байт из файла и передачи их в множество words
     */
    private static StringBuilder stringBuilder;

    static {
        CHARS_TO_IGNORE = new HashSet<>(Arrays.asList('.', ',', '?', '!', ':', ';', '*', '"', '-'));
        words = new TreeSet<>();
        stringBuilder = new StringBuilder();
    }

    public static void main(String[] args) {

        getContentFromSource();
        parseStringToCollection();
        writeCollectionToDestinationFile();

    }

    /**
     * Запись байт из файла в StringBuilder
     */
    private static void getContentFromSource() {

        try (FileInputStream fis = new FileInputStream(SOURCE_PATH)) {

            int i;
            while ((i = fis.read()) != -1) {
                char c = (char) i;
                if (!CHARS_TO_IGNORE.contains(c)) {
                    stringBuilder.append(c);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Разбор строки из StringBuilder в множество words с заменой перевода строки на пробел
     * и переводом строк в нижний регистр
     */
    private static void parseStringToCollection() {

        String content = stringBuilder.toString().toLowerCase();

        words.addAll(Arrays.asList(content.replace("\n", " ").split(" ")));
        words.remove("");

    }

    /**
     * Запись слов из множества в файл
     */
    private static void writeCollectionToDestinationFile() {

        try (FileOutputStream fos = new FileOutputStream(DESTINATION_PATH)) {

            for (String s : words) {
                fos.write(s.getBytes());
                fos.write('\n');
                fos.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
