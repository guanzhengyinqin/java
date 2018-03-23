package com.baxcall.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class SocketClient implements Runnable {
	
	private Socket socket;
	
	private InputStream in;
	private OutputStream out;
	
	public SocketClient(Socket socket){
		this.socket = socket;
		try{
			if(null!=socket){
				this.in = socket.getInputStream();
				this.out = socket.getOutputStream();
				String message = "testgasdftest";
				out.write(assemble(new byte[][]{"BAXC".getBytes(),intToByteArray(3),intToByteArray(message.getBytes().length),message.getBytes(),"CXAB".getBytes()}));
			}else{
				throw new NullPointerException();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] heads = new byte[4];
		while(true){
			try{
				in.read(heads);
				String flag = new String(heads,"utf-8");
				System.out.println("flag:"+flag);
				in.read(heads);
				int command = byteArrayToInt(heads);
				System.out.println("Command:"+command);
				in.read(heads);
				int dataLength = byteArrayToInt(heads);
				System.out.println("dataLength"+dataLength);
				byte[] data = new byte[dataLength];
				in.read(data);
				byte verifyData = verify(data);
				
				byte[] verify = new byte[1];
				
				if(verifyData==verify[0]){
					System.out.println("verify:"+verify);
				}
				
				
				in.read(verify);
				
				if("BAXC".equals(flag)){
					
				}else{
					socket.close();
					break;
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
				break;
			}
		}
	}
	
	
	private byte verify(byte[] data){
		byte b = data[0];
		for(int i = 1; i < data.length;i++){
			b = (byte) (b ^ data[i]);
		}
		return b;
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
    
    private static byte verify1(byte[] data){
		byte b = data[0];
		for(int i = 1; i < data.length;i++){
			b = (byte) (b ^ data[i]);
		}
		return b;
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
	
	public static void main(String[] args) {
		
		byte[] array = {2,3,4,4,3,5,6,6,8,15};
		
		
		
		byte a = verify1(array);
		
		System.out.println(a);
		
		/*
		int[] array = {2,3,4,4,3,5,6,6,5};

		int v = 0;

		for (int i = 0;i < array.length;i++) {
			System.out.println(Integer.toBinaryString(array[i]));
			v ^= array[i];
			System.out.println(Integer.toBinaryString(v));
		}
		*/

		/*
		System.out.println(Integer.toBinaryString(2));
		System.out.println(Integer.toBinaryString(1));
		
		System.out.println(Integer.toBinaryString(1 ^ 2));
		*/

	}
	

}
