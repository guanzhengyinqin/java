package socket.server;

public class DufaultHandler implements ReadData {

	@Override
	public void read(byte[] data) {
		// TODO Auto-generated method stub
		System.out.println(new String(data));
	}
	
	@Override
	public void read(String data) {
		// TODO Auto-generated method stub
		System.out.println(data);
	}
	
}
