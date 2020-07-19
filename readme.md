上一篇文章里面，使用Java的connection和url类库实现了多线程下载文件的一个demo，这次我根据之前的思路设计了一下下载器程序的接口，如下图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200720070549697.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMTQ3MTIx,size_16,color_FFFFFF,t_70#pic_center)
这里我根据所下载的文件协议不同分成了http下载，ftp下载和对bt种子文件的解析下载三个分枝。
根据模版方法设计模式的思路，使用这三种下载时就像使用netty一样流程是完全是一摸一样的只需要改变一个名字就行，如下

```java
DownLoader downLoader = new HTTPSDownLoaderImpl("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm","/Users/caohao/IdeaProjects/downloader/src/main/resources/test1.htm");
        downLoader.downLoadStart();
        DownLoader downLoader = new HTTPDownLoaderImpl("http://baiduliulanqi.00791.com/style/images/img-1-1.png","/Users/caohao/IdeaProjects/downloader/src/main/resources/test2.png");
        downLoader.downLoadStart();
```
代码内部借鉴了一部分springioc的设计思路，创建了一个filedefinition来管理信息。并将它内部的一个属性作为了多线程下文件下载进度的一个锁对象。

GitHub地址：[https://github.com/1224819118/downloader](https://github.com/1224819118/downloader)