package com.downloader.test;

import com.downloader.loader.DownLoader;
import com.downloader.loader.HTTPDownLoaderImpl;
import com.downloader.loader.targetfile.TargetFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://baiduliulanqi.00791.com/style/images/img-1-1.png");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        for (int i = 0; i < 10; i++) {
            new testthread((HttpURLConnection)url.openConnection(),i).start();
        }
    }
}
class testthread extends Thread{
    public volatile static int finished=0;
    int blocklength;
    int index;
    HttpURLConnection connection;
    public testthread(HttpURLConnection connection,int index) {
        this.index = index;
        this.connection = connection;
        blocklength = connection.getContentLength()/10+1;
    }

    @Override
    public void run() {
        try {
            RandomAccessFile file = new RandomAccessFile("/Users/caohao/IdeaProjects/downloader/src/main/resources/test.png","rw");
            InputStream inputStream = connection.getInputStream();
            inputStream.skip(index*blocklength);
            file.seek(index*blocklength);
            byte[] bytes = new byte[1024];
            int hash=0;
            int concurrentlength=0;
            while (concurrentlength<blocklength&&(hash = inputStream.read(bytes))!=-1){
                file.write(bytes,0,hash);
                concurrentlength+=hash;
            }
            finished=finished+10;
            System.out.println("已经完成了%"+finished);
            inputStream.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}