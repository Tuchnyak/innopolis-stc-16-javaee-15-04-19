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

        serialize(plainObject, FILE_TASK_01);
        PlainObject dePlob = deSerialize(FILE_TASK_01);

        System.out.println("\"Плоский\" объект из файла:");
        System.out.println(dePlob);
        System.out.println();

        // task02
        Slave slave = new Slave("Dobby", 159, false);
        Master master = new Master("Lucius", 54, slave);

        serializeObject(master, FILE_TASK_02);
        Master deMaster = (Master) deSerializeObject(FILE_TASK_02);

        System.out.println("Объект из файла:");
        System.out.println(deMaster);
        System.out.println();

        // task01 + Рефлексия
        PlainObject plobRef = new PlainObject("ref_obj", "cosmic", 'a', 1, 42.42d, false);
        serializeWithReflection(plobRef, FILE_TASK_01_REFLECTION);
        PlainObject dePlobRef = deSerialize(FILE_TASK_01_REFLECTION);

        System.out.println("\"Плоский\" объект из файла, который был сериализовани с применением Рефлексии:");
        System.out.println(dePlobRef);

    }

    /**
     * Сериализация "плоского" объекта
     *
     * @param object "плоский" объект для сериалихации
     * @param file   путь к файлу для записи объекта
     */
    private static void serialize(Object object, String file) {

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
    private static PlainObject deSerialize(String file) {

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

    /**
     * Сериализация объекта с сылочными полями
     *
     * @param object объект для сериализации
     * @param file   путь к файлу для записи
     */
    private static void serializeObject(Object object, String file) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            oos.writeObject(object);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Десериализайия из файла объекта с сылочными полями
     *
     * @param file путь к файлу для чтения
     * @return объект из файла
     */
    private static Object deSerializeObject(String file) {

        Object obj = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            obj = ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * Сериализация "плоского" объекта с примитивными полями или строками, реализованная при помощи Рефлексии
     *
     * @param object объект для сериализации
     * @param file   путь к файлу для записи
     */
    private static void serializeWithReflection(Object object, String file) {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {

            for (Field f : object.getClass().getDeclaredFields()) {

                if (Modifier.isPrivate(f.getModifiers()))
                    f.setAccessible(true);

                Class<?> fieldType = f.getType();

                switch (fieldType.getTypeName()) {
                    case "java.lang.String":
                        dos.writeUTF((String) f.get(object));
                        break;
                    case "char":
                        dos.writeChar(f.getChar(object));
                        break;
                    case "byte":
                        dos.writeByte(f.getByte(object));
                        break;
                    case "short":
                        dos.writeShort(f.getShort(object));
                        break;
                    case "int":
                        dos.writeInt(f.getInt(object));
                        break;
                    case "long":
                        dos.writeLong(f.getLong(object));
                        break;
                    case "float":
                        dos.writeFloat(f.getFloat(object));
                        break;
                    case "double":
                        dos.writeDouble(f.getDouble(object));
                        break;
                    case "boolean":
                        dos.writeBoolean(f.getBoolean(object));
                        break;
                }
                dos.flush();

            }

        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }


}
