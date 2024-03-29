package org.example.ch3;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnDigest extends Thread {

    private String filename;
    private byte[] digest;

    public ReturnDigest(String filename) {
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
            digest = sha.digest();

            /** 동기화 블록 */
            synchronized (System.out) {
                /** 공유자원으로써 동기화 문제 부분 */
                System.out.println("input : ");
                System.out.println(DatatypeConverter.printHexBinary(digest));
                System.out.println();
                /** end */
            }

        } catch (IOException | NoSuchAlgorithmException e) {
            System.out.println(e);
        }
    }

    public byte[] getDigest() {
        return digest;
    }
}
