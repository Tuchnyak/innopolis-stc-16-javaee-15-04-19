package ru.innopolis.tasks.hw07;

import ru.innopolis.tasks.hw07.entities.Master;
import ru.innopolis.tasks.hw07.entities.PlainObject;
import ru.innopolis.tasks.hw07.entities.Slave;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Задание 1. Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 * <p>
 * Задание 2. Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).
 * Требование: Использовать готовые реализации (Jaxb, jackson и т.д.) запрещается.
 *
 * @author Georgii Shchennikov
 */
public class SerializationDemo {

    /**
     * Константа для пути к файлу для первого задания
     */
    private static final String FILE_TASK_01 = "./src/ru/innopolis/tasks/hw07/files/file_task01.bin";
    private static final String FILE_TASK_01_REFLECTION = "./src/ru/innopolis/tasks/hw07/files/file_task01_reflection.bin";

    /**
     * Константа для пути к файлу для второго задания
     */
    private static final String FILE_TASK_02 = "./src/ru/innopolis/tasks/hw07/files/file_task02.bin";

    public static void main(String[] args) {

        // task01
        PlainObject plainObject = new PlainObject("dormama", "galactic", 'z', 12, 1.5d, true);

        SerUtil.serialize(plainObject, FILE_TASK_01);
        PlainObject dePlob = SerUtil.deSerialize(FILE_TASK_01);

        System.out.println("\"Плоский\" объект из файла:");
        System.out.println(dePlob);
        System.out.println();

        // task02
        Slave slave = new Slave("Dobby", 159, false);
        Master master = new Master("Lucius", 54, slave);

        SerUtil.serializeObject(master, FILE_TASK_02);
        Master deMaster = (Master) SerUtil.deSerializeObject(FILE_TASK_02);

        System.out.println("Объект из файла:");
        System.out.println(deMaster);
        System.out.println();

        // task01 + Рефлексия
        PlainObject plobRef = new PlainObject("ref_obj", "cosmic", 'a', 1, 42.42d, false);
        SerUtil.serializeWithReflection(plobRef, FILE_TASK_01_REFLECTION);
        PlainObject dePlobRef = SerUtil.deSerialize(FILE_TASK_01_REFLECTION);

        System.out.println("\"Плоский\" объект из файла, который был сериализовани с применением Рефлексии:");
        System.out.println(dePlobRef);

    }
}
