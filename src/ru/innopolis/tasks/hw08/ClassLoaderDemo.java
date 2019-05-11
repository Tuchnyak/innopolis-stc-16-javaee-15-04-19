package ru.innopolis.tasks.hw08;

import ru.innopolis.tasks.hw08.somePackage.Worker;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Дан интерфейс
 * <p>
 * public interface Worker {
 * void doWork();
 * }
 * <p>
 * Необходимо написать программу, выполняющую следующее:
 * <p>
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork()
 * в файле SomeClass.java.
 * <p>
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 *
 * @author Georgii Shchennikov
 */
public class ClassLoaderDemo {

    private static final String PATH_TO_JAVA_FILE = "./src/ru/innopolis/tasks/hw08/somePackage/SomeClass.java";
    private static final String METHOD_NAME = "doWork()";

    public static void main(String[] args) {

        String codeToAdd = readFromConsole();

        includeCodeToJavaClassFile(codeToAdd, METHOD_NAME, PATH_TO_JAVA_FILE);

        compileCode(PATH_TO_JAVA_FILE);

        executeCode(PATH_TO_JAVA_FILE);

    }

    /**
     * Построчное считывание кода с консоли
     *
     * @return строка считанного кода
     */
    private static String readFromConsole() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str;
        StringBuilder sb = new StringBuilder();

        try {
            while (!(str = reader.readLine()).isEmpty()) {
                sb.append(str).append("\n\r");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return sb.toString();
    }

    /**
     * Добавление кода из строкового аргумента в файл класса
     *
     * @param codeToAdd код для добавления
     */
    private static void includeCodeToJavaClassFile(String codeToAdd, String methodName, String pathToClassFile) {

        StringBuilder sb = null;

        try {

            List<String> lines = Files.readAllLines(Paths.get(pathToClassFile));
            sb = new StringBuilder();

            for (String s : lines) {
                if (s.contains(methodName)) {
                    sb.append(s).append("\n");
                    sb.append(codeToAdd);
                } else {
                    sb.append(s).append("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (sb != null) {
            try (FileOutputStream fos = new FileOutputStream(pathToClassFile)) {
                fos.write(sb.toString().getBytes());
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Компиляция байт-кода обновлённого файла класса
     *
     * @param filePath путь к файлу с классом
     */
    private static void compileCode(String filePath) {

        JavaCompiler jCompiler = ToolProvider.getSystemJavaCompiler();
        jCompiler.run(System.in, System.out, System.err, Paths.get(filePath).toFile().getAbsolutePath());

    }

    /**
     * Загрузка класса из байт-кода и выполнение метода
     *
     * @param pathToJavaFile путь к .java файлу рядом с которым должен лежать файл с байт-кодом
     */
    private static void executeCode(String pathToJavaFile) {

        String pathToClassFile = pathToJavaFile.substring(0, pathToJavaFile.length() - 5).concat(".class");

        ClassLoader ccl = new CustomClassLoader(pathToClassFile);

        try {
            Class<?> someClassClass = ccl.loadClass("ru.innopolis.tasks.hw08.somePackage.SomeClass");
            Worker someWorker = (Worker) someClassClass.newInstance();
            someWorker.doWork();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

}
