package ru.innopolis.tasks.hw09.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
     * Отображение сокетов на их выходные потоки
     */
    private ConcurrentHashMap<Socket, PrintWriter> serverSocketsOutput;

    /**
     * Очередь общих сообщений
     */
    private Queue<String> msgsQueue;

    public <T extends ConcurrentHashMap<Socket, String>,
            D extends ConcurrentHashMap<Socket, PrintWriter>,
            E extends ConcurrentLinkedQueue<String>> TcpServerClientListener
            (
                    Socket clientSocket,
                    T serverSocketsMap,
                    D serverSocketsOutput,
                    E msgsQueue
            ) {
        this.clientSocket = clientSocket;
        this.serverSocketsMap = serverSocketsMap;
        this.serverSocketsOutput = serverSocketsOutput;
        this.msgsQueue = msgsQueue;
        System.out.println(">>> Слушатель клиента создан");
    }

    @Override
    public void run() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String msg;
            boolean isFirstLineFromClient = true;
            while (!(msg = reader.readLine()).equalsIgnoreCase(EXIT_COMMAND)) {
                if (isFirstLineFromClient) {
                    System.out.println(">>> Имя сокета " + msg);
                    serverSocketsMap.put(clientSocket, msg);
                    serverSocketsOutput.put(clientSocket, new PrintWriter(clientSocket.getOutputStream()));
                    System.out.println(">>> Сокет добавлен, имя: " + serverSocketsMap.get(clientSocket));
                    isFirstLineFromClient = false;
                } else {
                    String fullMsg = serverSocketsMap.get(clientSocket).concat(": ").concat(msg);
                    System.out.println(">>> " + fullMsg);
                    msgsQueue.add(fullMsg);
                }
            }

            System.out.println(">>> ".concat(serverSocketsMap.get(clientSocket).concat(" ").concat("Отключаем.")));
            msgsQueue.add(serverSocketsMap.get(clientSocket).concat(" ").concat("покинул чат."));
            clientSocket.close();
            serverSocketsMap.remove(clientSocket);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
