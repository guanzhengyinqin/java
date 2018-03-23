package apacheftpserver;

import org.apache.ftpserver.command.Command;
import org.apache.ftpserver.command.CommandFactory;
import org.apache.ftpserver.command.CommandFactoryFactory;

public class CommandFactoryImpl implements CommandFactory {

	private CommandFactoryFactory commandFactoryFactory = new CommandFactoryFactory();
	private CommandFactory commandFactory;
	private STORcommand storCommand;
	public CommandFactoryImpl(UploadFileAction uploadFileAction) {
		// TODO Auto-generated constructor stub
		commandFactoryFactory.setUseDefaultCommands(true);
		this.commandFactory = commandFactoryFactory.createCommandFactory();
		this.storCommand = new STORcommand(uploadFileAction);
	}


	
	@Override
	public Command getCommand(String cmdName) {
		// TODO Auto-generated method stub
		if (cmdName == null || cmdName.equals("")) {
            return null;
        }
		System.out.println("收到命令--->"+cmdName);
        String upperCaseCmdName = cmdName.toUpperCase();
        if("STOR".equals(upperCaseCmdName)){
        	return storCommand;
        }else{
        	return commandFactory.getCommand(upperCaseCmdName);
        }
        
	}

}
