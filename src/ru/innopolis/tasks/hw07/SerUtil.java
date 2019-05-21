package ru.innopolis.tasks.hw07;

import ru.innopolis.tasks.hw07.entities.PlainObject;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class SerUtil {


    private static final String STR = "java.lang.String";
    private static final String CHAR = "char";
    private static final String BYTE = "byte";
    private static final String SHORT = "short";
    private static final String INT = "int";
    private static final String LONG = "long";
    private static final String FLOAT = "float";
    private static final String DOUBLE = "double";
    private static final String BOOL = "boolean";

    /**
     * Сериализация "плоского" объекта
     *
     * @param object "плоский" объект для сериалихации
     * @param file   путь к файлу для записи объекта
     */
    public static void serialize(Object object, String file) {
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
    public static PlainObject deSerialize(String file) {
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
    public static void serializeObject(Object object, String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(object);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Десериализация из файла объекта с сылочными полями
     *
     * @param file путь к файлу для чтения
     * @return объект из файла
     */
    public static Object deSerializeObject(String file) {
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
    public static void serializeWithReflection(Object object, String file) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            for (Field f : object.getClass().getDeclaredFields()) {
                if (Modifier.isPrivate(f.getModifiers()))
                    f.setAccessible(true);
                Class<?> fieldType = f.getType();
                switch (fieldType.getTypeName()) {
                    case STR:
                        dos.writeUTF((String) f.get(object));
                        break;
                    case CHAR:
                        dos.writeChar(f.getChar(object));
                        break;
                    case BYTE:
                        dos.writeByte(f.getByte(object));
                        break;
                    case SHORT:
                        dos.writeShort(f.getShort(object));
                        break;
                    case INT:
                        dos.writeInt(f.getInt(object));
                        break;
                    case LONG:
                        dos.writeLong(f.getLong(object));
                        break;
                    case FLOAT:
                        dos.writeFloat(f.getFloat(object));
                        break;
                    case DOUBLE:
                        dos.writeDouble(f.getDouble(object));
                        break;
                    case BOOL:
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
