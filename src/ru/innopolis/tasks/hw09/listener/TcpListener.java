package ru.innopolis.tasks.hw09.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Слушатель TCP соединения
 */
public class TcpListener extends Thread {

    private InputStream inputStream;
    private String exitCommand;

    public TcpListener(InputStream inputStream, String exitCommand) {
        this.inputStream = inputStream;
        this.exitCommand = exitCommand;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String msg;
            while (true) {
                msg = reader.readLine();
                if (msg != null) {
                    if (msg.equalsIgnoreCase(exitCommand))
                        break;
                    System.out.println(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
