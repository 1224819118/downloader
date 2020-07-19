package com.downloader.loader.targetfile;

import com.downloader.loader.DownLoader;
import com.downloader.loader.exception.TargetFileException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;


public class DownLoadThread extends Thread {
    int concurrentStartIndex;//当前线程所负责的数据块的下标
    int blockLength;//当前线程所负责的块的大小
    TargetFile targetFile;//目标文件
    URLConnection connection;//链接
    DownLoader downLoader;

    public DownLoadThread(int concurrentStartIndex, int blockLength, String path, URLConnection connection,DownLoader downLoader) throws FileNotFoundException {
        this.concurrentStartIndex = concurrentStartIndex;
        this.blockLength = blockLength;
        this.targetFile = new TargetFile(path,"rw");
        this.connection = connection;
        this.downLoader = downLoader;
    }

    @Override
    public void run() {
        try {
            int startIndex = concurrentStartIndex * blockLength;
            InputStream inputStream = connection.getInputStream();
            inputStream.skip(startIndex);
            byte[] bytes = new byte[1024];
            int hash=0;//这一块缓冲区写了多少
            int now=0;//当前块已经写满了多少
            while (now<blockLength&&(hash = inputStream.read(bytes))!=-1){
                targetFile.write((startIndex+now),hash,bytes);
                now+=hash;
            }
            TargetFileDefinition targetFileDefinition = downLoader.getTargetFileDefinition();
            int finished = targetFileDefinition.getFinished();
            finished = finished+10;
            System.out.println("当前任务完成%"+finished);
            targetFileDefinition.setFinished(finished);
            inputStream.close();
            targetFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TargetFileException e) {
            e.printStackTrace();
        }
    }
}
