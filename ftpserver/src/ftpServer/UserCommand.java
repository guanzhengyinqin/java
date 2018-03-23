package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class UserCommand implements Command {

	@Override
	public void getResult(String data, Writer writer, ControllerThread t) {
		// TODO Auto-generated method stub
		String response = "";
		if(Share.users.containsKey(data)){
			ControllerThread.USER.set(data);
			response = "331";
		}else{
			response = "501";
		}
		
		try{
			writer.write(response);
			writer.write("\r\n");
			writer.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
