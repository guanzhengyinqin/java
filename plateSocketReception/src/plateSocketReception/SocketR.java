package plateSocketReception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketR {
	
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(9967);
			Socket sc = s.accept();
			InputStream in = sc.getInputStream();
			int t = 1;
			while(true){
				byte[] lData = new byte[4];
				in.read(lData);
				
				
				for (byte b : lData) {
					System.out.print(b+"\t");
				}
				System.out.println();
				
				
				int length = byteArrayToInt(lData);
				byte[] data = null;
				int[] doF = batches(length,1024);
				if(doF[0]==-1){
					System.out.println("长度："+length);
					byte[] temp = new byte[length];
					in.read(temp);
					data = temp;
				}else{
					
					/*
					System.out.println("分解的长度："+length);
					
					
					byte[] temp = new byte[length];
					byte[] temp2 = new byte[1024];
					
					for(int i=0;i<doF[0];i++){
						int readLength = in.read(temp2);
						if(readLength!=1024){
							int a = 1024-readLength;
							byte[] temp3 = new byte[a];
							in.read(temp3);
							System.arraycopy(temp3, 0, temp2, readLength, temp3.length);
						}
						System.arraycopy(temp2, 0, temp, i*temp2.length, temp2.length);
						
					}
					
					byte[] endData = new byte[doF[1]];
					in.read(endData);
					System.arraycopy(endData, 0, temp, temp2.length*doF[0], endData.length);
					data = temp;
					*/
					
					
					/*
					System.out.println("分解的长度："+length);
					byte[] temp = new byte[length];
					byte[] temp2 = new byte[1];
					for(int i=0;i<length;i++){
						in.read(temp2);
						System.arraycopy(temp2, 0, temp, i*temp2.length, temp2.length);
					}
					*/
					/*
					System.out.println("分解的长度："+length);
					
					
					byte[] temp = new byte[length];
					byte[] temp2 = new byte[2048];
					
					for(int i=0;i<doF[0];i++){
						byte[] temp3 = readData(in,temp2);
						System.arraycopy(temp3, 0, temp, i*temp3.length, temp3.length);
					}
					
					byte[] endData = new byte[doF[1]];
					in.read(endData);
					System.arraycopy(endData, 0, temp, temp2.length*doF[0], endData.length);
					data = temp;
					*/
					
					byte[] dt2 = new byte[length];
					
					byte[] dt = readData(in,dt2);
					
					//byte[] data = new byte[length];
					//in.read(data);
					File of = new File("D:/RRR"+t+".jpg");
					of.createNewFile();
					OutputStream o = new FileOutputStream(of);
					
					o.write(dt);
					o.flush();
					o.close();
					t++;
					
					
				}
				
				
				
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
    
    /**
     * 计算分批数据
     * @param length
     * @return
     */
    private static int[] batches(int length,int frequencyLength){
    	int[] result = new int[2];
    	int readLength = frequencyLength;
    	if(length>readLength){
    		result[0] = length / readLength;
    		result[1] = length % readLength;
    	}else{
    		result[0] = -1;
    	}
    	return result;
    }
    
    
    private static byte[] readData(InputStream in,byte[] bData) throws IOException{
    	int readLength = in.read(bData);
    	if(readLength!=bData.length){
    		byte[] temp2 = readData(in,new byte[bData.length-readLength]);
    		System.arraycopy(temp2, 0, bData, readLength, temp2.length);
    		return bData;
    	}else{
    		return bData;
    	}
    }
    

}
