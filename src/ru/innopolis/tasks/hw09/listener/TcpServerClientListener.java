package ru.innopolis.tasks.hw09.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Слушатель входящих сообщений конкретного клиента.
 * Хранит сокет на этого клиента.
 * Добавляет сообщения с именем клиента в общую очередь сообщений сервера.
 * При получении команды удаляет сокет из общего отображения и закрывает его.
 */
public class TcpServerClientListener implements Runnable {

    /**
     * Команда завершения работы
     */
    private static final String EXIT_COMMAND = "EXIT";

    /**
     * Сокет на клиента, присылающего сообщения
     */
    private Socket clientSocket;
    /**
     * Отображение сокетов на их имена
     */
    private Map<Socket, String> serverSocketsMap;
    /**
     * Очередь общих сообщений
     */
    private Queue<String> msgsQueue;

    public <T extends ConcurrentHashMap<Socket, String>,
            E extends ConcurrentLinkedQueue<String>> TcpServerClientListener
            (
                    Socket clientSocket,
                    T serverSocketsMap,
                    E msgsQueue
            ) {
        this.clientSocket = clientSocket;
        this.serverSocketsMap = serverSocketsMap;
        this.msgsQueue = msgsQueue;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String msg;
            while (!(msg = reader.readLine()).equalsIgnoreCase(EXIT_COMMAND)) {
                String fullMsg = serverSocketsMap.get(clientSocket).concat(": ").concat(msg);
                System.out.println(fullMsg);
                msgsQueue.add(fullMsg);
            }

            msgsQueue.add(serverSocketsMap.get(clientSocket).concat(" ").concat("Покинул чат."));
            clientSocket.close();
            serverSocketsMap.remove(clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
