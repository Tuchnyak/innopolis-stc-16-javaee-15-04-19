package ru.innopolis.tasks.hw11;

import ru.innopolis.tasks.hw05.task02.TextFilesGenerator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Задание 1. Необходимо создать программу, которая продемонстрирует утечку памяти в Java.
 * При этом объекты должны не только создаваться, но и периодически частично удаляться,
 * чтобы GC имел возможность очищать часть памяти.
 * Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 * <p>
 * Задание 2. Доработать программу так, чтобы ошибка OutOfMemoryError возникала в Metaspace /Permanent Generation
 */
public class GcDemo {

    private static final int AMOUNT = 100_000_000;

    public static void main(String[] args) {

//        getOutOfMemoryErrorSimple();
        getOutOfMemoryErrorMetaSpace();

    }

    private static void getOutOfMemoryErrorSimple() {
        List<Integer> list = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < AMOUNT; i++) {
            list.add(random.nextInt());
            if (i % 5 == 0) {
                list.remove(0);
            }
        }
        /*
        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at java.util.Arrays.copyOf(Arrays.java:3181)
        at java.util.ArrayList.grow(ArrayList.java:265)
        at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
        at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
        at java.util.ArrayList.add(ArrayList.java:462)
        at ru.innopolis.tasks.hw11.GcDemo.getOutOfMemoryErrorSimple(GcDemo.java:29)
        at ru.innopolis.tasks.hw11.GcDemo.main(GcDemo.java:21)

        Process finished with exit code 1
         */
    }

    private static void getOutOfMemoryErrorMetaSpace() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < AMOUNT; i++) {
            list.add(TextFilesGenerator.genParagraph());
            if (i % 5 == 0) {
                list.remove(0);
            }
        }
        /*Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            at java.util.Arrays.copyOfRange(Arrays.java:3520)
            at sun.security.provider.NativePRNG$RandomIO.implNextBytes(NativePRNG.java:553)
            at sun.security.provider.NativePRNG$RandomIO.access$400(NativePRNG.java:331)
            at sun.security.provider.NativePRNG.engineNextBytes(NativePRNG.java:220)
            at java.security.SecureRandom.nextBytes(SecureRandom.java:468)
            at java.security.SecureRandom.next(SecureRandom.java:491)
            at java.util.Random.nextInt(Random.java:390)
            at ru.innopolis.tasks.hw05.task02.TextFilesGenerator.genWord(TextFilesGenerator.java:74)
            at ru.innopolis.tasks.hw05.task02.TextFilesGenerator.genSentence(TextFilesGenerator.java:99)
            at ru.innopolis.tasks.hw05.task02.TextFilesGenerator.genParagraph(TextFilesGenerator.java:144)
            at ru.innopolis.tasks.hw11.GcDemo.getOutOfMemoryErrorMetaSpace(GcDemo.java:54)
            at ru.innopolis.tasks.hw11.GcDemo.main(GcDemo.java:24)

            Process finished with exit code 1
            */
    }

}
