package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class PortCommand implements Command {

	@Override
	public void getResult(String data, Writer writer, ControllerThread t) {
		// TODO Auto-generated method stub
		String response = "200 the port an ip have been transfered";
		try{
			String[] iAP = data.split(",");
			String ip = iAP[0]+"."+iAP[1]+"."+iAP[2]+"."+iAP[3];
			String port = Integer.toString(256*Integer.parseInt(iAP[4])+Integer.parseInt(iAP[5]));
			System.out.println("ip is "+ip);
			System.out.println("port is "+port);
			t.setDataIp(ip);
			t.setDataPort(port);
			writer.write(response);
			writer.write("\r\n");
			writer.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
