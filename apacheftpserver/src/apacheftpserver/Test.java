package apacheftpserver;
import java.util.ArrayList;
import java.util.List;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

public class Test {
	
	public static void main(String[] args) {
		FtpServerFactory serverFactory = new FtpServerFactory();
		
		BaseUser user = new BaseUser();
		user.setName("admin");
		user.setPassword("123456");
		user.setHomeDirectory("D:/LinuxSoft");
		
		List<Authority> authorities = new ArrayList<>();
		authorities.add(new WritePermission());
		user.setAuthorities(authorities);
		
		BaseUser user2 = new BaseUser();
		user2.setHomeDirectory("D:/picture");
		user2.setName("User");
		user2.setPassword("123456");
		user2.setAuthorities(authorities);
		
		try {
			
			
			
			serverFactory.getUserManager().save(user);
			serverFactory.getUserManager().save(user2);
			
			serverFactory.setCommandFactory(new CommandFactoryImpl(new UploadFileAction() {
				@Override
				public void getUploadFileName(String pathAndName) {
					// TODO Auto-generated method stub
					System.out.println("*****************上传的文件和路径*****************");
					System.out.println("-----------------"+pathAndName+"-----------------");
				}
			}));
			System.out.println("目录："+serverFactory.getUserManager().getUserByName(""));
			FtpServer server = serverFactory.createServer();
			
			
			
			server.start();
		} catch (FtpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
