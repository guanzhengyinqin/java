package com.baxcall.inter.impl;

/**
 * Created by Administrator on 2018/1/16.
 */

public interface OnReadDataListener {
    /**
     * 读取到数据
     * @param command 命令
     * @param datas 数据内容
     */
    void onReadData(int command, byte[] datas);
}
