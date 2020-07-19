package com.downloader.loader;

import com.downloader.loader.targetfile.DownLoadThread;
import com.downloader.loader.targetfile.TargetFileDefinition;
import sun.net.www.protocol.ftp.FtpURLConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FTPDownLoader implements DownLoader {

    URL url;
    String targetFilePath;
    Thread[] threads = new Thread[10];
    TargetFileDefinition fileDefinition = new TargetFileDefinition();
    int length;
    FtpURLConnection connection;

    public FTPDownLoader(String url, String targetFilePath) {
        try {
            this.url = new URL(url);
            this.targetFilePath=targetFilePath;
            this.connection = (FtpURLConnection) this.url.openConnection();
            length = connection.getContentLength()/10+1;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTargetFile(String targetFilePath) {
        this.targetFilePath = targetFilePath;
    }

    @Override
    public String getTargetFile() {
        return targetFilePath;
    }

    @Override
    public void setURL(URL url) {
        this.url=url;
    }

    @Override
    public URL getURL() {
        return url;
    }

    @Override
    public URLConnection getConnection() {
        return connection;
    }

    @Override
    public void downLoadStart() {
        putThread();
        for (Thread t :threads) {
            t.start();
        }
    }

    public void putThread(){
        for (int i=0;i<threads.length;i++){
            try {
                threads[i] = new DownLoadThread(i,length,targetFilePath,url.openConnection(),this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public TargetFileDefinition getTargetFileDefinition() {
        return fileDefinition;
    }
}
