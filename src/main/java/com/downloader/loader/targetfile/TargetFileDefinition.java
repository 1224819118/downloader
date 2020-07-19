package com.downloader.loader.targetfile;

/**
 *用来描述当前文件的信息，例如下载的进度
 */
public class TargetFileDefinition {
    int finished=0;

    public int getFinished() {
        return finished;
    }

    public synchronized void setFinished(int finished) {
        this.finished = finished;
    }
}
