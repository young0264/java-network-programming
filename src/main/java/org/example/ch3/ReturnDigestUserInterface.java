package org.example.ch3;

import javax.xml.bind.DatatypeConverter;

public class ReturnDigestUserInterface {

    public static void main(String[] args) {
        for (String filename : args) {

            //digest 게산
            ReturnDigest returnDigest = new ReturnDigest(filename);
            returnDigest.start();

            //result 출력
            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            byte[] digest = returnDigest.getDigest();
            result.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(result);

        }
    }
}
