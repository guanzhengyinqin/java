package com.baxcall.inter.impl;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2018/1/16.
 */

public class SocketTransferDeal implements TransferDeal,Runnable {

	private OnReadDataListener onReadDataListener;
	private InputStream in;
	private OutputStream out;
	private Heads objHead;
	private boolean run;
	private byte[] head;
	private byte[] stern;
	
	public SocketTransferDeal(byte[] head,byte[] stern) {
		this.head = head;
		this.stern = stern;
	}
	
	@Override
	public void setHeads(Heads head){
		this.objHead = head;
	}
	
    @Override
    public void write(int command,byte[] datas) {
    	byte[] bCommand = intToByteArray(command);
    	byte[] dataLength = intToByteArray(datas.length);
    	try {
			out.write(assemble(new byte[][]{head,bCommand,dataLength,datas,stern}));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void setOnReadDataListener(OnReadDataListener onReadDataListener) {
    	this.onReadDataListener = onReadDataListener;
    }

    @Override
    public void init(InputStream inputStream, OutputStream outputStream) {
    	
    	if(null!=inputStream && null!= outputStream && null!=onReadDataListener){
    		run = true;
    		in = inputStream;
    		out = outputStream;
    		new Thread(this).start();
    	}else{
    		throw new NullPointerException("初始化错误！");
    	}
    	
    }

    @Override
    public void release() {
    	run = false;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(run){
			
			byte[] flag = new byte[4];
			byte[] command = new byte[4];
			byte[] dataLength = new byte[4];
			
			byte[] flag_2 = new byte[4];
			
			try {
				in.read(flag);
				in.read(command);
				in.read(dataLength);
				int dLength = byteArrayToInt(dataLength);
				byte[] data = new byte[dLength];
				in.read(data);
				in.read(flag_2);
				onReadDataListener.onReadData(byteArrayToInt(command), data);
				if(null!=objHead){
					objHead.getHeads(flag, command, dataLength, flag_2);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
	}
	
	private int byteArrayToInt(byte[] b) {
        return   b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    private byte[] intToByteArray(int a) {
        return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
    
    
    
    private byte[] assemble(byte[][] bytes){
    	int length = 0;
    	for(byte[] b:bytes){
    		length += b.length;
    	}
    	
    	byte[] result = new byte[length];
    	
    	for(int i=0;i<bytes.length;i++){
    		if(i==0){
    			System.arraycopy(bytes[i], 0, result, 0, bytes[i].length);
    		}else{
    			int index = 0;
    			for(int j=0;j<i;j++){
    				index += bytes[j].length;
    			}
    			System.arraycopy(bytes[i], 0, result,index, bytes[i].length);
    		}
    	}
    	
    	return result;
    	
    }
	
}
