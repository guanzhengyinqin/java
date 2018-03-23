package socketClient;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Main {
	
	private static String head1 = "bx";
	
	private static String instruct1 = "bb";
	
	private static DataInputStream in;
	private static DataOutputStream out;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Client c = new Client("192.168.3.30", 9956);
		//c.connect();
		
		/*
		try {
			
			System.out.println(generate("PTMMWn3o2jB10EtqAtZVNB230p1G0Xjb"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
		
		
		Socket socket = new Socket("192.168.3.30",9655);
		
		
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		Scanner scan = new Scanner(System.in);
		while(true){
			String a = scan.next();
			byte[] strb = a.getBytes("utf-8");
			byte[] headInfo = headInfo(head1, instruct1, strb.length);
			byte[] data = new byte[headInfo.length+strb.length];
			System.arraycopy(headInfo, 0, data, 0, headInfo.length);
			System.arraycopy(a.getBytes(), 0, data, headInfo.length, strb.length);
			
			out.write(data);
			
		}
		
		
		
		
		
		
		/*
		Scanner input = new Scanner(System.in);
		
		String head = "ba";
		
		String instruct = "bb";
		
		String message = input.next();
		
		byte[] headAndInstruct = new byte[10+message.length()];
		
		System.arraycopy(head.getBytes(), 0, headAndInstruct, 0, head.length());
		System.arraycopy(instruct.getBytes(), 0, headAndInstruct, 2, instruct.length());
		
		
		System.arraycopy(Util.intToByteArray(message.length()),0, headAndInstruct, 6, 4);
		*/
		
		
		
		/*
		String a = new Scanner(System.in).next();
		byte[] headData = headInfo(head1, instruct1, a.length());
		
		byte[] send = new byte[headData.length+a.length()];
		System.arraycopy(headData, 0, send, 0, 10);
		System.arraycopy(a.getBytes(),0 , send, 10, a.length());
		
		for (byte b : send) {
			System.out.print(b+"\t");
		}
		System.out.println();
		ByteArrayInputStream bin = new ByteArrayInputStream(send);
		

		byte[] inr = new byte[2];
		bin.read(inr);
		System.out.println(new String(inr));
		byte[] ins = new byte[4];
		bin.read(ins);
		System.out.println(new String(ins).trim());
		byte[] datal = new byte[4];
		bin.read(datal);
		int datalength = Util.byteArrayToInt(datal);
		System.out.println(datalength);
		byte[] dataleng = new byte[datalength];
		bin.read(dataleng);
		System.out.println(new String(dataleng));
		*/
		
		
		
		
		
		
		/*
		Socket socket = new Socket("",1);
		DataInputStream in = null;
		DataOutputStream out = null;
		*/
		/*
		try{
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			while(true){
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		
		
	}
	
	
	
	public static byte[] headInfo(String head,String instruct,int dataLength){
		byte[] result = new byte[10];
		System.arraycopy(head.getBytes(), 0, result, 0, head.length());
		System.arraycopy(instruct.getBytes(), 0, result, 2, instruct.length());
		System.arraycopy(Util.intToByteArray(dataLength), 0, result, 6, 4);
		return result;
	}
	
	
	
	public static String generate(String m) throws Exception{
		String idxx = m;									// 传给你的数据

        String idxs = Activation.decrypt(idxx);				// 解密后的数据

        String[] vvv = idxs.split(" ");
//        Log.e("xxxx","vvv"+vvv[0]+vvv[1]+vvv[2]);

        //System.out.println(vvv.length);
        
        String IMEI = vvv[0];  								//imei 
        String Mac = vvv[1];  								//mac
        String x2 = vvv[2];  								// 身份证串号
        
        if(IMEI.equals("null")){

        	IMEI = "";
        }
        if(Mac.equals("null")){

        	Mac = "";
        }
        if(x2.equals("null")){
            x2 = "";

        }
        
//        Log.e("xxxx","vvv"+xx+"/"+x1+"/"+x2);
        String machineCode = null;
        if(!IMEI.isEmpty() && !Mac.isEmpty()){
//            Log.e("xxxx","1111");
            String EI1 = IMEI.substring(0, 1);
            String EI2 = IMEI.substring(5, 6);
            String EI3 = IMEI.substring(9, 10);
            String EI4 = IMEI.substring(12, 13);
            String EI5 = IMEI.substring(14, 15);

            String MC1 = Mac.substring(1, 2);
            String MC2 = Mac.substring(3, 4);
            String MC3 = Mac.substring(10, 11);
            String MC4 = Mac.substring(15, 16);

            int EI = Integer.parseInt(EI1 + EI2 + EI3 + EI4 + EI5);

            String EI32 = Long.toString(EI, 32);

            
            
            try {
                machineCode = Activation.encrypt(EI32 + MC1 + MC2 + MC3 + MC4);
              //  machine_code_view1.setText(machineCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(!IMEI.isEmpty() && Mac.isEmpty()){
//            Log.e("xxxx","222");
            try {
            	machineCode = Activation.encrypt(IMEI);
              //  machine_code_view1.setText(machineCode);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }else if(IMEI.isEmpty() && !Mac.isEmpty()){
//            Log.e("xxxx","333");
            try {
            	machineCode = Activation.encrypt(Mac);
              //  machine_code_view1.setText(machineCode);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }else if(!x2.isEmpty()){
//            Log.e("xxxx","4444");
            try {
            	machineCode =Activation.encrypt(x2);
//                machine_code_view1.setText(machineCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        return machineCode;
        
	}

}
