package plateSocketReception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketS {

	public static void main(String[] args) {
		File f = new File("D:/a2.jpg");
		File f2= new File("D:/a1.jpg");
		Socket s;
		OutputStream out = null;
		try {
			s = new Socket("192.168.3.30", 9876);
			out = s.getOutputStream();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			InputStream in = new FileInputStream(f);
			int l = (int) f.length();
			byte[] oData = new byte[l];
			in.read(oData);
			byte[] dLength = intToByteArray(l);
			
			byte[] data = new byte[oData.length+dLength.length];
			System.arraycopy(dLength, 0, data, 0, dLength.length);
			System.arraycopy(oData, 0, data, dLength.length, oData.length);
			
			
			out.write(data);
			out.flush();
			in.close();
			
			
			/*
			int l2 = (int) f2.length();
			byte[] oD = new byte[l2];
			InputStream i = new FileInputStream(f2);
			i.read(oD);
			byte[] dLe = intToByteArray(l2);
			
			byte[] data2 = new byte[oD.length+dLe.length];
			System.arraycopy(dLe, 0, data2, 0, dLe.length);
			System.arraycopy(oD, 0, data2, dLe.length, oD.length);
			
			out.write(data2);
			out.flush();
			i.close();
			*/
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
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
	
}
