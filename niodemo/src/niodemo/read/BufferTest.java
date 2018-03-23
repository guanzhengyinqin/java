package niodemo.read;

import java.nio.ByteBuffer;

public class BufferTest {
	
	public static void main(String[] args) {
		byte[] b = new byte[10];
		for (int i = 0; i < 10; i++) {
			b[i] = (byte) (i+1);
		}
		ByteBuffer buffer = ByteBuffer.wrap(b);
		
		for (byte c : b) {
			System.out.print(c+"\t");
		}
		
		System.out.println();
		
		System.out.println("未执行flip方法前position:"+buffer.position());
		System.out.println("未执行flip方法前limit:"+buffer.limit());
		byte[] c = new byte[5];
		buffer.get(c, 0, c.length);
		buffer.flip();
		System.out.println("已执行flip方法后position:"+buffer.position());
		System.out.println("已执行flip方法后limit:"+buffer.limit());
		
		
		for (byte c2 : c) {
			System.out.print(c2+"\t");
		}
		
	}

}
