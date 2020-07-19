package com.downloader.loader;


import com.downloader.loader.targetfile.DownLoadThread;
import com.downloader.loader.util.MyX509TrustManager;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class HTTPSDownLoaderImpl extends HTTPDownLoader {
    HttpsURLConnection connection;
    SSLContext sslcontext = SSLContext.getInstance("SSL","SunJSSE");
    public HTTPSDownLoaderImpl(String url, String targetFilePath) throws IOException, NoSuchProviderException, NoSuchAlgorithmException {
        super(url, targetFilePath);
        try {
            sslcontext.init(null, new TrustManager[]{new MyX509TrustManager()}, new java.security.SecureRandom());
            HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslsession) {
                    System.out.println("WARNING: Hostname is not matched for cert.");
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());

        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        connection = (HttpsURLConnection) super.url.openConnection();
        super.length = connection.getContentLength()/10+1;
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

    @Override
    public URLConnection getConnection() {
        return connection;
    }
}
