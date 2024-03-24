package org.example.ch11;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ExamServer {


    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(19)); //자바7이후 직접 바인드 가능
        SocketChannel clientChannel = serverSocketChannel.accept();

        clientChannel.configureBlocking(false); // 클라이언트 채널 nonblock
        serverSocketChannel.configureBlocking(false); // 서버채널 nonblock

        Selector selector = Selector.open();// 셀렉터를 사용해서 준비된 연결만을 찾기
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //
        SelectionKey key = clientChannel.register(selector, SelectionKey.OP_WRITE); //client는 채널에 데이터를 쓸 준비가 되었는지 물음

    }
}
