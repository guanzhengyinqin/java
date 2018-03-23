package com.baxcall.inter.impl;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Administrator on 2018/1/16.
 */

public class Main {
    public static void main(String[] args){

    	
        try {
            Socket socket = new Socket("192.168.3.30", 9999);

            TransferDeal transferDeal = new SocketTransferDeal("BAXC".getBytes(),"CXAB".getBytes());

            

            //设置读取数据监听
            transferDeal.setOnReadDataListener(new OnReadDataListener() {
                @Override
                public void onReadData(int command, byte[] datas) {
                    System.out.print("收到命令："+command+", 内容为："+new String(datas));
                }
            });
            
          //初始化
          transferDeal.init(socket.getInputStream(), socket.getOutputStream());
	        
	        transferDeal.setHeads(new Heads() {
				
				@Override
				public void getHeads(byte[] flag, byte[] command, byte[] dataLength, byte[] flag_2) {
					// TODO Auto-generated method stub
					System.out.println(new String(flag));
					System.out.println(new String(flag_2));
				}
				
			});

            //写入数据
            //transferDeal.write(1,"hello".getBytes());

            //不想使用时释放资源
            //transferDeal.release();

        } catch (IOException e) {
            e.printStackTrace();
        }
        

    	

    }
    

    
    

}
