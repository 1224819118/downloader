package com.downloader.test;

import com.downloader.loader.DownLoader;
import com.downloader.loader.HTTPDownLoaderImpl;

import java.io.IOException;

public class main2 {
    public static void main(String[] args) throws IOException {
        DownLoader downLoader = new HTTPDownLoaderImpl("http://baiduliulanqi.00791.com/style/images/img-1-1.png","/Users/caohao/IdeaProjects/downloader/src/main/resources/test2.png");
        downLoader.downLoadStart();
    }
}
