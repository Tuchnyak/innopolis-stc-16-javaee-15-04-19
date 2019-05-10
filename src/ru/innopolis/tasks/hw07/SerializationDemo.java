package ru.innopolis.tasks.hw07;

import ru.innopolis.tasks.hw07.entities.Master;
import ru.innopolis.tasks.hw07.entities.PlainObject;
import ru.innopolis.tasks.hw07.entities.Slave;

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

        // task01
        PlainObject plainObject = new PlainObject("dormama", "galactic", 'z', 12, 1.5d, true);

        executePlainObjectSerialization(plainObject, FILE_TASK_01);
        PlainObject dePlob = executePlainObjectDeserialization(FILE_TASK_01);

        System.out.println("\"Плоский\" объект из файла:");
        System.out.println(dePlob);
        System.out.println();

        // task02
        Slave slave = new Slave("Dobby", 159, false);
        Master master = new Master("Lucius", 54, slave);

        executeObjectSerialization(master, FILE_TASK_02);
        Master deMaster = (Master) executeObjectDeserialization(FILE_TASK_02);

        System.out.println("Объект из файла:");
        System.out.println(deMaster);

    }

    /**
     * Сериализация "плоского" объекта
     *
     * @param object "плоский" объект для сериалихации
     * @param file   путь к файлу для записи объекта
     */
    private static void executePlainObjectSerialization(Object object, String file) {

        PlainObject plainObject = (PlainObject) object;

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {

            dos.writeUTF(plainObject.getModel());
            dos.writeUTF(plainObject.getType());
            dos.writeChar(plainObject.getSign());
            dos.writeInt(plainObject.getAmount());
            dos.writeDouble(plainObject.getMass());
            dos.writeBoolean(plainObject.isNew());

            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Десериализайия "плоского" объекта из файла
     *
     * @param file путь к файлу с сериолизованным объектом
     * @return восстановленный из файла объект
     */
    private static PlainObject executePlainObjectDeserialization(String file) {

        PlainObject plob = null;

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {

            String model = dis.readUTF();
            String type = dis.readUTF();
            char sign = dis.readChar();
            int amount = dis.readInt();
            double mass = dis.readDouble();
            boolean isNew = dis.readBoolean();

            plob = new PlainObject(model, type, sign, amount, mass, isNew);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return plob;
    }

    private static void executeObjectSerialization(Object object, String file) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            oos.writeObject(object);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Object executeObjectDeserialization(String file) {

        Object obj = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            obj = ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }

}
