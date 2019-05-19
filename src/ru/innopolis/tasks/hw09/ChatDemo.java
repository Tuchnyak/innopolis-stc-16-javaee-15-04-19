package ru.innopolis.tasks.hw09;

import ru.innopolis.tasks.hw09.listener.TcpServerClientListener;
import ru.innopolis.tasks.hw09.server.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

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



    public static void main(String[] args) throws IOException {

        new Server().start();

    }

}
