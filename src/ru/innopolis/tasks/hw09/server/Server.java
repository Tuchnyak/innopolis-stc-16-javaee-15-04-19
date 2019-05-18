package ru.innopolis.tasks.hw09.server;

import ru.innopolis.tasks.hw09.listener.TcpServerClientListener;

import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class Server extends Thread {

    private static final String PROPERTIES_PATH = "./src/ru/innopolis/tasks/hw09/app.properties";
    private static final Properties PROPERTIES = new Properties();
    private static final String EXIT_COMMAND = "STOP SERVER";

    private volatile static boolean isServerRun = true;

    static {
        try (InputStream is = new FileInputStream(PROPERTIES_PATH)) {
            PROPERTIES.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отображение сокетов на их имена
     */
    private ConcurrentHashMap<Socket, String> serverSocketsMap = new ConcurrentHashMap<>();
    /**
     * Очередь общих сообщений
     */
    private ConcurrentLinkedQueue<String> msgsQueue = new ConcurrentLinkedQueue<>();

    private final int port;
    private final String host;
    private final String broadcastIp;
    private final int broadcastPort;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public Server() {
        this.port = Integer.parseInt(PROPERTIES.getProperty("server.port"));
        this.host = PROPERTIES.getProperty("localhost.ip");
        this.broadcastIp = PROPERTIES.getProperty("broadcast.ip");
        this.broadcastPort = Integer.parseInt(PROPERTIES.getProperty("broadcast.port"));
    }

    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(port, 0, InetAddress.getByName(host));
             DatagramSocket datagramSocket = new DatagramSocket()) {

            datagramSocket.setBroadcast(true);

            // приём новых подключений
            executorService.execute(() -> {
                while (isServerRun) {
                    try (Socket socket = serverSocket.accept()) {
                        if (!serverSocketsMap.containsKey(socket)) {
                            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                                String socketName = reader.readLine();
                                serverSocketsMap.put(socket, socketName);
                                executorService.execute(new TcpServerClientListener(socket, serverSocketsMap, msgsQueue));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });

            // вычитывание сообщений из очереди и отправка сообщений в бродкаст
            executorService.execute(() -> {
                while (isServerRun) {
                    LockSupport.parkNanos(100_000_000L);
                    if (!msgsQueue.isEmpty()) {
                        sendBroadcastMessage(msgsQueue.poll(), datagramSocket);
                    }
                }
            });

            // прекращение работы сервера
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                while (isServerRun) {
                    if (reader.readLine().equalsIgnoreCase(EXIT_COMMAND)) {
                        isServerRun = false;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendBroadcastMessage(String msg, DatagramSocket datagramSocket) {

        byte[] buff = msg.getBytes();

        try {
            DatagramPacket datagramPacket = new DatagramPacket(
                    buff,
                    buff.length,
                    InetAddress.getByName(broadcastIp),
                    broadcastPort);
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
