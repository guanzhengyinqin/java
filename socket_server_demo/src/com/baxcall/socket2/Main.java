package com.baxcall.socket2;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MySocketServer server = new MySocketServer(9999);

		
//		System.out.println(showBytes(verify(new byte[]{(byte)0xff, (byte)0xf1})));
		
	}


	public static byte verify(byte[] data){
		byte b = data[0];
		for(int i = 1; i < data.length;i++){
			b = (byte) (b ^ data[i]);
		}
		return b;
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
	
}
