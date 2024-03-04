package org.example.ch9;

import java.io.IOException;
import java.net.ServerSocket;

public class RandomPort {

    /** 포트를 지정하지 않고 소켓 생성시 random port use */
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(0);
            System.out.println("This server runs on port " +
                    serverSocket.getLocalPort());
        } catch (IOException ex){
            System.err.println(ex);
        }
    }
}
