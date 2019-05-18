package ru.innopolis.tasks.hw09;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <b>Задание 1.</b> Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
 * Каждое сообщение сервер подписывает именем клиента и рассылает всем клиентам (broadcast).
 * <p>
 * <b>Задание 2.</b>  Усовершенствовать задание 1:
 * <p>
 * a.      добавить возможность отправки личных сообщений (unicast).
 * <p>
 * b.      добавить возможность выхода из чата с помощью написанной в чате команды «quit»
 */
public class ChatDemo {

    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_PATH = "./src/ru/innopolis/tasks/hw09/app.properties";

    static {
        try (InputStream is = new FileInputStream(PROPERTIES_PATH)) {
            PROPERTIES.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        PROPERTIES.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));

    }

}
