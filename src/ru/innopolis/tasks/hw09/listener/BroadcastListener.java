package ru.innopolis.tasks.hw09.listener;

import java.io.IOException;
import java.net.*;

/**
 * Широковещательный слушатель
 */
public class BroadcastListener extends Thread {

    private static final int RECEIVE_TIMEOUT = 60000;

    private boolean isRunning = true;
    private DatagramSocket socket = null;

    public BroadcastListener(int broadcastPort) {
        try {
            socket = new DatagramSocket(null);
            socket.setReuseAddress(true);
            socket.setBroadcast(true);
            socket.bind(new InetSocketAddress(broadcastPort));
        } catch (SocketException e) {
            e.printStackTrace();
            stopListener();
        }
    }

    @Override
    public void run() {

        DatagramPacket packet;

        while (isRunning) {
            try {
                byte[] buff = new byte[256];
                packet = new DatagramPacket(buff, buff.length);

                if (socket != null) {
                    socket.setSoTimeout(RECEIVE_TIMEOUT);
                    socket.receive(packet);
                    System.out.println((new String(packet.getData())).trim());
                }
            } catch (IOException e) {
                System.out.println(">>> broadcast: ".concat(e.getMessage()));
            }
        }
        if (socket != null) {
            socket.close();
        }
    }

    /**
     * Прекращение работы слушателя
     */
    public void stopListener() {
        isRunning = false;
    }

}
