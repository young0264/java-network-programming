package org.example.ch3;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestThread extends Thread{

    private String filename;

    public DigestThread(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try{
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while(din.read() != -1);
            din.close();

            byte[] digest = sha.digest();
            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            result.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(result);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * run 메서드는 변경이 불가능. 이건 생성자의 인자를 통해서 설정하는 방식
     * 여기서 발생하는 비동기 문제는 뒤에서 확인
     * */
    public static void main(String[] args) {
        for (String filename : args) {
            Thread digestThread = new DigestThread(filename);
//            digestThread.run(); //thread 직접 호출
            digestThread.start(); //This method is not invoked for the main method thread or "system"
        }
    }
}
