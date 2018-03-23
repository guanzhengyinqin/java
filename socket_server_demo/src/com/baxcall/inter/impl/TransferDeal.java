package com.baxcall.inter.impl;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 协议打包  包头+内容主体+包尾
 */

public interface TransferDeal {
    /**
     * 写入数据
     * @param datas 数据内容
     */
    void write(int command, byte[] datas);

    /**
     * 设置读取监听
     * @param onReadDataListener 监听接口
     */
    void setOnReadDataListener(OnReadDataListener onReadDataListener);

	public void setHeads(Heads head);
    
    /**
     * 初始化
     * @param inputStream 输入流
     * @param outputStream 输出流
     */
    void init(InputStream inputStream, OutputStream outputStream);

    /**
     * 释放资源
     */
    void release();
}
