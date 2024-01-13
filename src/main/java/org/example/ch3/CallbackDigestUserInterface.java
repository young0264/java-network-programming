package org.example.ch3;

import javax.xml.bind.DatatypeConverter;

public class CallbackDigestUserInterface {


    /** 메인스레드와 별도로 실행 */
    public static void receiveDigest(byte[] digest, String name) {
        StringBuilder result = new StringBuilder(name);
        result.append(": ");
        result.append(DatatypeConverter.printHexBinary(digest));
        System.out.println(result);
    }

    public static void main(String[] args) {
        for (String filename : args) {
            //다이제스트 계산
            CallbackDigest cb = new CallbackDigest(filename);
            Thread t = new Thread(cb);
            t.start();
        }
    }
}
