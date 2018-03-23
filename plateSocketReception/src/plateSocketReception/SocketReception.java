package plateSocketReception;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketReception {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ssSSS");
	
	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(8766);
			Socket sc = s.accept();
			InputStream in = sc.getInputStream();
			
			while(true){
				byte[] flag = new byte[2];
				in.read(flag);
				String flagStr = new String(flag);
				System.out.println("Flag--->"+flagStr);
				System.out.print("flag--����\t");
				for (byte b : flag) {
					System.out.print(b+"\t");
				}
				if("bx".equals(flagStr)){
					
					byte[] typed = new byte[1];
					System.out.println();
					in.read(typed);
					System.out.print("type--人脸\t"+typed[0]);
					if(typed[0]==1){
						System.out.println("����");
						byte[] dLength = new byte[4];
						in.read(dLength);
						int dataLength = byteArrayToInt(dLength);
						
						int[] doFor = batches(dataLength);
						byte[] data;
						if(doFor[0]==-1){
							byte[] temp = new byte[dataLength];
							in.read(temp);
							data = temp;
						}else{
							byte[] temp = new byte[51200*doFor[0]+doFor[1]];
							byte[] temp1 = new byte[51200];
							for(int i=0;i<doFor[0];i++){
								in.read(temp1);
								System.arraycopy(temp1, 0, temp, i*temp1.length, temp1.length);
							}
							byte[] temp2 = new byte[doFor[1]];
							in.read(temp2);
							System.arraycopy(temp2, 0, temp, doFor[0]*temp1.length, temp2.length);
							data = temp;
						}
						
						
						//in.read(data);
						File f = new File("D:/cameraFile/socketReception/"+sdf.format(new Date())+".jpg");
						f.createNewFile();
						OutputStream out = new FileOutputStream(f);
						out.write(data);
						out.flush();
						out.close();
					}else if(typed[0]==2){
						System.out.println("车牌图片");
						byte[] resultD = new byte[20];
						in.read(resultD);
						//System.out.print("��������--\t");
						
						String plateStr = new String(resultD,Charset.forName("UTF-8"));
						
						byte[] dLength = new byte[4];
						in.read(dLength);
						System.out.println("大小"+dLength+"字节");
						
						int dataLength = byteArrayToInt(dLength);
						
						int[] doFor = batches(dataLength);
						byte[] data;
						
						
						if(doFor[0]==-1){
							byte[] temp = new byte[dataLength];
							in.read(temp);
							data = temp;
						}else{
							byte[] temp = new byte[51200*doFor[0]+doFor[1]];
							System.out.println(temp.length);
							byte[] temp1 = new byte[51200];
							for(int i=0;i<doFor[0];i++){
								in.read(temp1);
								System.arraycopy(temp1, 0, temp, i*temp1.length, temp1.length);
							}
							byte[] temp2 = new byte[doFor[1]];
							in.read(temp2);
							System.arraycopy(temp2, 0, temp, doFor[0]*temp1.length, temp2.length);
							data = temp;
						}
						
						
						byte[] flag2 = new byte[2];
						in.read(flag2);
						System.out.println("��β:"+new String(flag2));
						//in.reset();
						
						
						
						//in.read(data);
						String path = "D:/cameraFile/socketReception/"+sdf.format(new Date())+"_"+plateStr.trim()+".jpg";
						System.out.println("�ļ�·����"+path);
						File f = new File(path);
						f.createNewFile();
						OutputStream out = new FileOutputStream(f);
						out.write(data);
						out.flush();
						out.close();
					}
				}else{
					sc.close();
					break;
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
     * �����������
     * @param length
     * @return
     */
    private static int[] batches(int length){
    	int[] result = new int[2];
    	int readLength = 51200;
    	if(length>readLength){
    		result[0] = length / readLength;
    		result[1] = length % readLength;
    	}else{
    		result[0] = -1;
    	}
    	return result;
    }

}
