package org.example.ch7;

import org.example.ch5.QueryString;

import java.io.*;
import java.net.*;

public class FormPoster {
    private URL url;
    private QueryString query = new QueryString();
    public FormPoster(URL url) throws MalformedURLException {
        if (!url.getProtocol().toLowerCase().startsWith("http")) {
            throw new IllegalArgumentException("http url로만 posting 가능");
        }
        this.url = url;
    }

    public void add(String name, String value) {
        query.add(name, value);
    }

    public URL getUrl() {
        return this.url;
    }

    public InputStream post() throws IOException {
        //연결을 열고 POST로 전송하기 위한 준비
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);
        try (OutputStreamWriter out = new OutputStreamWriter(
                uc.getOutputStream(), "UTF-8")) {

            //POST라인, Content-type헤더, Content-length 헤더들은
            //URLConnection에 의해 보내지므로 우리는 단지 데이터만 보내면 된다.
            out.write(query.toString());
            out.write("\r\n");
            out.flush();
        }

        //서버 응답 반환
        return uc.getInputStream();
    }

    public static void main(String[] args) throws MalformedURLException {
        URL url;
        if (args.length > 0) {
            try {
                url = new URL(args[0]);
            } catch (MalformedURLException ex) {
                System.out.println("Usage : java FormPoster url");
                return;
            }
        } else {
            try{
                url = new URL("https://www.naver.com");
            }catch (MalformedURLException ex){
                System.out.println("ex : "  + ex);
                return;
            }
        }
        FormPoster poster = new FormPoster(url);
        poster.add("name", "Elliotte Rusty Harold");
        poster.add("email", "ny2485@naver.com");

        try (InputStream in = poster.post()) {
            //응답읽기
            Reader r = new InputStreamReader(in);
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }



}
