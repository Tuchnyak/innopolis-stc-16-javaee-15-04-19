package ru.innopolis.tasks.hw09.listener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class BroadcastListener extends Thread {

    private boolean isRunning = true;
    private DatagramSocket socket = null;

    private byte[] buff = new byte[256]; //TODO проверить и откорректировать

    public BroadcastListener(int broadcastPort) {
        try {
            socket = new DatagramSocket(broadcastPort);
            socket.setBroadcast(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        DatagramPacket packet;

        while (isRunning) {
            packet = new DatagramPacket(buff, buff.length);
            try {
                if (socket != null) {
                    socket.setSoTimeout(10000);
                    socket.receive(packet);
                    System.out.println(new String(packet.getData()));
                }
            } catch (IOException ignored) {
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }

    }

    public void stopListener() {
        isRunning = false;
    }

}
