package ru.innopolis.tasks.hw07;

import ru.innopolis.tasks.hw07.entities.PlainObject;

import java.io.*;

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

    /**
     * Константа для пути к файлу для второго задания
     */
    private static final String FILE_TASK_02 = "./src/ru/innopolis/tasks/hw07/files/file_task02.bin";

    public static void main(String[] args) {

        PlainObject plainObject = new PlainObject("dormama", 'z', 12, 1.5d, true);
        executePlainObjectSerialization(plainObject, FILE_TASK_01);
        PlainObject dePlob = executePlainObjectDeserialization(FILE_TASK_01);
        System.out.println(dePlob);

    }

    /**
     * Сериализация "плоского" объекта
     * @param plainObject "плоский" объект для сериалихации
     * @param file путь к файлу для записи объекта
     */
    private static void executePlainObjectSerialization(PlainObject plainObject, String file) {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {

            dos.writeUTF(plainObject.getModel());
            dos.writeChar(plainObject.getSign());
            dos.writeInt(plainObject.getAmount());
            dos.writeDouble(plainObject.getMass());
            dos.writeBoolean(plainObject.isNew());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Десериализайия "плоского" объекта из файла
     * @param file путь к файлу с сериолизованным объектом
     * @return восстановленный из файла объект
     */
    private static PlainObject executePlainObjectDeserialization(String file) {

        PlainObject plob = null;

        try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {

            String model = dis.readUTF();
            char sign = dis.readChar();
            int amount = dis.readInt();
            double mass = dis.readDouble();
            boolean isNew = dis.readBoolean();

            plob = new PlainObject(model, sign, amount, mass, isNew);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return plob;
    }

}
