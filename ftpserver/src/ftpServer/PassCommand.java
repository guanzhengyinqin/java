package ftpServer;

import java.io.IOException;
import java.io.Writer;

public class PassCommand implements Command {

	@Override
	public void getResult(String data, Writer writer, ControllerThread t) {
		// TODO Auto-generated method stub
		System.out.println("execute the pass command");
		System.out.println("the data is "+ data);
		//获得用户名
		String key = ControllerThread.USER.get();
		String pass = Share.users.get(key);
		
		String response = null;
		if(pass.equals(data)){
			System.out.println("登入成功");
			Share.loginedUser.add(key);
			t.setLogin(true);
			response = "230 User" +key+"logged in";
		}else{
			System.out.println("登入失败，密码错误");
			response = "530    密码错误";
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
