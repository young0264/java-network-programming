package org.example.ch3;

import javax.xml.bind.DatatypeConverter;

public class ReturnDigestUserInterface {

    public static void main(String[] args) {

        /** 예제3 : 폴링(polling) */
        ReturnDigest[] digests = new ReturnDigest[args.length];

        for (int i = 0; i < args.length; i++) {
            digests[i] = new ReturnDigest(args[i]);
            digests[i].start();
        }

        for (int i = 0; i < args.length; i++) {
            while (true) {
                byte[] digest = digests[i].getDigest(); /** 값이 안들어오면 계속 점유함*/
                /**
                 * 이 check point : 반복적으로 digest 변수가 null인지 검사하고 null이 아닌 경우에만 값을 사용
                 * 폴링(polling)이라고 볼 수 있음.
                 * */
                if (digest != null) {
                    StringBuilder result = new StringBuilder(args[i]);
                    result.append(": ");
                    result.append(DatatypeConverter.printHexBinary(digest));
                    System.out.println(result);
                    break;
                }
            }
        }


        /** 예제2 : 경쟁 조건 (race condition)*/
        for (int i = 0; i < args.length; i++) {
            digests[i] = new ReturnDigest(args[i]); /** (2) 여기의 호출이 먼저 끝나야함 */
            /**
             * 여기서 생성되는 스레드가 끝나기 전이나 아직 실행되지 않았을 때
             * 두번째 for-loop가 접근하게 되면 경쟁상태가 발생했다 봄
             * */
            digests[i].start();
        }

        for (int i = 0; i < args.length; i++) {
            //결과 출력
            StringBuffer result = new StringBuffer(args[i]);
            result.append(": ");
            byte[] digest = digests[i].getDigest(); /** (1) 호출전에 */
            result.append(DatatypeConverter.printHexBinary(digest));

            System.out.println(result);
        }


        /** 예제1 */
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
