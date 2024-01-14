package org.example.ch3;

import java.io.*;
import java.util.Date;

public class LogFile {

    private Writer out;

    public LogFile(Writer out) {
        this.out = out;
    }

    public LogFile(File f) throws IOException {
        FileWriter fileWriter = new FileWriter(f);
        this.out = new BufferedWriter(fileWriter);
    }

    Thread.MIN
//    /** 이 부분에서 동기화 이슈 발생 가능 */
//    public void writeEntry(String message) throws IOException {
//        Date date = new Date();
//        out.write(date.toString());
//        out.write('\t');
//        out.write(message);
//        out.write("\r\n");
//    }

//    /** 방법1 : Writer의 객체인 out을 동기화 하는 것 */
//    public void writeEntry(String message) throws IOException {
//        synchronized (out){
//            Date date = new Date();
//            out.write(date.toString());
//            out.write('\t');
//            out.write(message);
//            out.write("\r\n");
//        }
//    }
//
//    /** 방법2 : LogFile 객체 자체를 동기화 */
//    public void writeEntry(String message) throws IOException {
//        synchronized (this){
//            Date date = new Date();
//            out.write(date.toString());
//            out.write('\t');
//            out.write(message);
//            out.write("\r\n");
//        }
//    }

    public synchronized void writeEntry(String message) throws IOException {
        Date date = new Date();
        out.write(date.toString());
        out.write('\t');
        out.write(message);
        out.write("\r\n");
    }

    public void close() throws IOException {
        synchronized (this)
        out.flush();
        out.close();
    }
}
