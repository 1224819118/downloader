package com.downloader.loader;

import com.downloader.loader.targetfile.TargetFile;
import com.downloader.loader.targetfile.TargetFileDefinition;
import com.downloader.loader.util.BTexper;

import java.net.URL;
import java.net.URLConnection;

public class BTDownLoader implements DownLoader {

    BTexper bTexper = new BTexper();

    @Override
    public void setTargetFile(String targetFilePath) {

    }

    @Override
    public String getTargetFile() {
        return null;
    }

    @Override
    public void setURL(URL url) {

    }

    @Override
    public URL getURL() {
        return null;
    }

    @Override
    public URLConnection getConnection() {
        return null;
    }

    @Override
    public void downLoadStart() {

    }

    @Override
    public TargetFileDefinition getTargetFileDefinition() {
        return null;
    }
}
