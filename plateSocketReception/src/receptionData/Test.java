package receptionData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
	
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(8766);
			Socket sc = s.accept();
			InputStream in = sc.getInputStream();
			int t = 1;
			while(true){
				byte[] lData = new byte[4];
				in.read(lData);
				int length = byteArrayToInt(lData);
				System.out.println("收到文件，文件大小："+length+"字节");
				byte[] dt2 = new byte[length];
				
				//byte[] dt = readData(in,dt2);
				//readData(in,dt2,0,dt2.length);
				
				readData(in,dt2);
				
				byte[] type = new byte[5];
				
				in.read(type);
				
				String typeStr = new String(type);
				System.out.println("文件类型："+typeStr.trim());
				//byte[] data = new byte[length];
				//in.read(data);
				File of = new File("D:/RRR"+t+"."+typeStr.trim());
				of.createNewFile();
				OutputStream o = new FileOutputStream(of);
				
				o.write(dt2);
				o.flush();
				o.close();
				t++;

				
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    private static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    private static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
    
    
//    private static byte[] readData(InputStream in,byte[] bData) throws IOException{
//    	int readLength = in.read(bData);
//    	if(readLength!=bData.length){
//    		byte[] temp2 = readData(in,new byte[bData.length-readLength]);
//    		System.arraycopy(temp2, 0, bData, readLength, temp2.length);
//    		return bData;
//    	}else{
//    		return bData;
//    	}
//    }

//    private static void readData(InputStream in,byte[] bData) throws IOException{
//		readData(in,bData,0,bData.length);
//    }
    
//    private static void readData(InputStream in,byte[] bData,int off,int length) throws IOException{
//    	int readLength = in.read(bData, off, length);
//    	if(readLength!=length){
//    		readData(in,bData,readLength+off,length-readLength);
//    	}
//    }
    
//    private static void readData(InputStream in,byte[] bData,int off,int length) throws IOException{
//
//    	while(true){
//    		int readLength = in.read(bData, off, length);
//        	if(readLength!=length){
//        		off = readLength+off;
//        		length = length-readLength;
//        	}else{
//        		break;
//        	}
//    	}
//    }
    
//    private static void readData(InputStream in,byte[] bData,int off,int length) throws IOException{
//    	int readLength = 0;
//		do{
//			off = readLength+off;
//    		length = length-readLength;
//    		readLength = in.read(bData, off, length);
//    	}while(readLength!=length);
//    }
    
    /**
     * 
     * @param in
     * @param bData
     * @throws IOException
     */
    private static void readData(InputStream in,byte[] bData) throws IOException{
    	int off = 0;
    	int length = bData.length;
    	int readLength = 0;
		do{
			off = readLength+off;
    		length = length-readLength;
    		readLength = in.read(bData, off, length);
    	}while(readLength!=length);
    }
    

}
