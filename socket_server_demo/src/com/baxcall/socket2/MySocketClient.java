package com.baxcall.socket2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class MySocketClient implements Runnable {
	
	private Socket socket;
	
	private InputStream in;
	private OutputStream out;
	
	public MySocketClient(Socket socket){
		this.socket = socket;
		try{
			if(null!=socket){
				this.in = socket.getInputStream();
				this.out = socket.getOutputStream();
			}else{
				throw new NullPointerException();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
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
				System.out.println("data"+showBytes(data));
				
				byte verifyData = verify(data);
				System.out.println("计算的校验码"+showBytes(verifyData));
				
				
				//System.out.println("读取的校验码"+showBytes(verify));
				
				
				if(verifyData==in.read()){
					System.out.println("比对成功:"+verifyData);
				}
				
			
				
				if("BAXC".equals(flag)){
					
				}else{
					socket.close();
					break;
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static String showBytes(byte... bytes){
		String str = "";
		for(int i = 0;i<bytes.length;i++){
			str += toHexString(bytes[i])+", ";
		}
		return str;
	}

    public static String toHexString(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
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

}
