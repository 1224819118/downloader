package com.downloader.loader.targetfile;

import com.downloader.loader.exception.TargetFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TargetFile extends RandomAccessFile {
    int length;
    public TargetFile(String name, String mode) throws FileNotFoundException {
        super(name, mode);
    }

    public TargetFile(File file, String mode) throws FileNotFoundException {
        super(file, mode);
    }

    /**
     * 将start到hash之间的数据写入
     * @param start
     * @param hash
     * @throws TargetFileException
     */
    public void write(int start,int hash,byte[] date) throws TargetFileException {
        if (hash>1024||start<0||hash<0){
            throw new TargetFileException("文件访问下标异常");
        }
        try {
            this.seek(start);
            this.write(date,0,hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        this.setLength(length);
    }

}
