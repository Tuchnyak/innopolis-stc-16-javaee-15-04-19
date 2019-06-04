package ru.innopolis.tasks.hw08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Кастомный загрузчик классов для конкретного учебного класса
 */
public class CustomClassLoader extends ClassLoader {

    /**
     * Константа с именем учёбного класса
     */
    private static final String CLASS_TO_LOAD = "ru.innopolis.tasks.hw08.somePackage.SomeClass";

    /**
     * Путь к байт-коду класса
     */
    private String classFile;

    public CustomClassLoader(String classFile) {
        this.classFile = classFile;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        if (CLASS_TO_LOAD.equals(name))
            return findClass(name);

        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        if (CLASS_TO_LOAD.equals(name)) {

            try {
                byte[] classBytes = Files.readAllBytes(Paths.get(classFile));

                return defineClass(name, classBytes, 0, classBytes.length);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return super.findClass(name);
    }

}
