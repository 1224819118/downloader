package com.downloader.loader;

import com.downloader.loader.targetfile.DownLoadThread;
import com.downloader.loader.targetfile.TargetFile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class HTTPDownLoaderImpl extends HTTPDownLoader {

    HttpURLConnection connection;

    public HTTPDownLoaderImpl(String url, String targetFile) throws IOException {
        super(url, targetFile);
        this.connection = (HttpURLConnection) super.url.openConnection();
        super.length = connection.getContentLength()/10+1;
    }

    @Override
    public URLConnection getConnection() {
        return connection;
    }

    @Override
    public void putThread() {
        for (int i=0;i<threads.length;i++){
            try {
                threads[i] = new DownLoadThread(i,length,targetFilePath,super.url.openConnection(),this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
