package org.example.ch2;

import java.io.*;
import java.security.DigestInputStream;

public class OutputStream {
    public static void main(String[] args) throws IOException {
//        generateCharactersPerByte(System.out);
//        FileInputStream
//        ByteArrayInputStream
//        java.net.Socket.sun
//        generateCharactersPerLine(System.out);
//        AutoCloseable
//        java.io.OutputStream
//        DigestInputStream
//        InputStream


    }

    /**
     * write 메소드 구현
     * 한번에 한 바이트씩 출력
     * */
    public static void generateCharactersPerByte(java.io.OutputStream out) throws IOException {

        System.out.println("Start generateCharactersPerByte");
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;

        int start = firstPrintableCharacter;

        while (true) {
            for (int i = start; i < start + numberOfCharactersPerLine; i++) {
                out.write(
                        ((i - firstPrintableCharacter) % numberOfPrintableCharacters)
                        + firstPrintableCharacter);
            }

            out.write('\r'); //캐리지 리턴
            out.write('\n'); //라인피드
            start = ((start + 1) - firstPrintableCharacter)
                    % numberOfPrintableCharacters + firstPrintableCharacter;
        }
    }

    /**
     * write 메서드 구현
     * 한 줄씩 출력하도록 수정
     * */
    public static void generateCharactersPerLine(java.io.OutputStream out) throws IOException {
        System.out.println("Start generateCharactersPerLine");

        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;

        int start = firstPrintableCharacter;
        byte[] line = new byte[numberOfCharactersPerLine + 2];

        while (true) {
            for (int i = start; i < start + numberOfCharactersPerLine; i++) {
                line[i - start] = (byte) ((i - firstPrintableCharacter)
                        % numberOfPrintableCharacters + firstPrintableCharacter);
            }

            line[72] = (byte) '\r'; // CR(캐리지리턴)
            line[73] = (byte) '\n'; // LF(라인피드)

            out.write(line);
            start = ((start + 1) - firstPrintableCharacter)
                    % numberOfPrintableCharacters + firstPrintableCharacter;
        }
    }
}
