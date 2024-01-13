package org.example.ch2;

import java.io.*;

public class FilterStream {

    /**
     * fin과 bin모두 read()메서드를 이용해서 data.txt 파일로부터 데이터를 읽을 수 있음.
     * -> 같은 소스에 연결된 다른 스트림을 호출하게 되면 스트림의 규칙들을 위반할 수가 있음.
     * 실제 읽고 쓰는 일을 하기 위해 필터체인(filter chain)의 마지막 필터만을 사용해야 함.
     * */
    public void preFilter() throws FileNotFoundException {
        FileInputStream fin = new FileInputStream("data.txt");
        BufferedInputStream bin = new BufferedInputStream(fin);
    }

    /**
     * 내장 입력 스트림의 레퍼런스(in)을 의도적으로 덮어쓰는 방법
     * 이로써 실수로 내장된 스트림에 접근하여 버퍼를 손상시키는 일은 발생하지 않는다.
     * */
    public void afterFilter() throws FileNotFoundException {
        InputStream in = new FileInputStream("data.txt");
        in = new BufferedInputStream(in);
    }
}
