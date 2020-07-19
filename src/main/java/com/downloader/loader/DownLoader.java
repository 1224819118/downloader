package com.downloader.loader;

import com.downloader.loader.targetfile.TargetFile;
import com.downloader.loader.targetfile.TargetFileDefinition;

import java.net.URL;
import java.net.URLConnection;

/**
 * DownLoader
 * 下载器的总接口，这里描述了各种情况下实现的下载器都要遵循的规范。
 */
public interface DownLoader {

    /**
     * 为下载器设置要下载的目标文件
     * @param targetFilePath
     */
    public void setTargetFile(String targetFilePath);

    /**
     * 获取目标文件
     * @return
     */
    public String getTargetFile();

    /**
     * 设置下载的URL
     * @param url
     */
    public void setURL(URL url);

    /**
     * 返回当前下载目标的URL
     * @return
     */
    public URL getURL();

    /**
     * 建立并返回连接
     * @return
     */
    public URLConnection getConnection();

    public void downLoadStart();

    public TargetFileDefinition getTargetFileDefinition();

}
