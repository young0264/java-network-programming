package org.example.ch12;

import java.io.IOException;
import java.net.*;

public class DaytimeUDPClient {
    private final static int PORT = 13;
    private static final String HOSTNAME = "localhost";

    public static void main(String[] args) {
        // try-with-resources, *AutoCloseable을 구현한 객체만 try문을 벗어나면 close 메서드를 호출해줌
        try (DatagramSocket socket = new DatagramSocket(0)) { //포트 0번에 대해 소켓을 염
            socket.setSoTimeout(10000); //타임아웃 설정(전송 실패 사실을 모르기에 udp에서 더 중요)

            // (송신)패킷 설정 (원격 호스트와 포트 제공)
            InetAddress host = InetAddress.getByName(HOSTNAME);
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host, PORT);

            // (수신)패킷 설정 : 빈바이트 배열을 포함해야함
            // 전체 응답을 충분히 담을 크기여야함
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);

            //소켓을 통해 패킷을 보내고, 받는다.
            socket.send(request);
            socket.receive(response);
            String result = new String(response.getData(), 0, response.getLength(), "US-ASCII");
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
