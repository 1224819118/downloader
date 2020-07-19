package com.downloader.loader;

import com.downloader.loader.targetfile.DownLoadThread;
import com.downloader.loader.targetfile.TargetFile;
import com.downloader.loader.targetfile.TargetFileDefinition;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 这里描述了http协议之下的两种下载器的规范
 */
public abstract class HTTPDownLoader implements DownLoader {
    URL url;
    String targetFilePath;
    Thread[] threads = new Thread[10];
    TargetFileDefinition fileDefinition = new TargetFileDefinition();
    int length;

    public HTTPDownLoader(String url, String targetFilePath) {
        try {
            this.url = new URL(url);
            this.targetFilePath=targetFilePath;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void downLoadStart(){
        putThread();
        for (Thread thread:threads) {
            thread.start();
        }
    }

    public abstract void putThread();

    @Override
    public void setTargetFile(String targetFilePath) {
        this.targetFilePath=targetFilePath;
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

    public TargetFileDefinition getTargetFileDefinition() {
        return fileDefinition;
    }

    public void setTargetFileDefinition(TargetFileDefinition fileDefinition) {
        this.fileDefinition = fileDefinition;
    }
}
