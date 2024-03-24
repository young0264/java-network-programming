package org.example.ch12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaytimeUDPServer {
    private final static int PORT = 13;
    private final static Logger audit = Logger.getLogger("requests");
    private final static Logger errors = Logger.getLogger("errors");

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            while (true) {
                try {
                    //요청을 수신할 패킷 생성, udp 패킷이 도착할 때까지 무한히 대기하는 메서드.
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(request);
                    String daytime = new Date().toString();
                    byte[] data = daytime.getBytes("US-ASCII");

                    //수신 받은곳과 같은 socket을 통해 응답
                    DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                    socket.send(response);
                    audit.info(daytime + " " + request.getAddress());
                } catch (IOException e) {
                    errors.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        } catch (IOException e) {
            errors.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}