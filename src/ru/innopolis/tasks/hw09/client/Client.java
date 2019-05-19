package ru.innopolis.tasks.hw09.client;

import ru.innopolis.tasks.hw09.listener.BroadcastListener;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class Client {

    private static final String PROPERTIES_PATH = "./src/ru/innopolis/tasks/hw09/app.properties";
    private static final Properties PROPERTIES = new Properties();
    /**
     * Команда завершения работы
     */
    private static final String EXIT_COMMAND = "EXIT";

    static {
        try (InputStream is = new FileInputStream(PROPERTIES_PATH)) {
            PROPERTIES.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        BroadcastListener broadcastListener = new BroadcastListener(Integer.parseInt(PROPERTIES.getProperty("broadcast.port")));
        broadcastListener.start();

        int serverPort = Integer.parseInt(PROPERTIES.getProperty("server.port"));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             Socket socket = new Socket(PROPERTIES.getProperty("localhost.ip"), serverPort);
             BufferedWriter bufferedWriter = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()))) {

            System.out.println("Type your name:");

            String msg;
            while (!(msg = reader.readLine()).equalsIgnoreCase(EXIT_COMMAND)) {
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            broadcastListener.stopListener();
        }

    }

}
