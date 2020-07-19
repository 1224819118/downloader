package com.downloader.test;

import com.downloader.loader.DownLoader;
import com.downloader.loader.HTTPSDownLoaderImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class main3 {
    public static void main(String[] args) throws IOException, NoSuchProviderException, NoSuchAlgorithmException {
        DownLoader downLoader = new HTTPSDownLoaderImpl("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm","/Users/caohao/IdeaProjects/downloader/src/main/resources/test1.htm");
        downLoader.downLoadStart();
    }
}
