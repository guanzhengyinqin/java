package mina.message;

public class MessageInfo {
	
	private String head;			//头信息	长度2个字符
	private String instruct;		//指令	长度4个字符
	private int messageLength;		//消息总长度	长度4个字符
	private int readingLength;		//已经读取的数据长度	长度4个字符
	
	private boolean hasHeadInfo;	//消息是否是包含头部的新消息
	
	
	public MessageInfo(){
		readingLength = 10;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getInstruct() {
		return instruct.trim();
	}

	public void setInstruct(String instruct) {
		this.instruct = instruct;
	}

	public int getMessageLength() {
		return messageLength;
	}

	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}

	public int getReadingLength() {
		return readingLength;
	}

	public void setReadingLength(int readingLength) {
		this.readingLength = readingLength;
	}

	public boolean isHasHeadInfo() {
		return hasHeadInfo;
	}

	public void setHasHeadInfo(boolean hasHeadInfo) {
		this.hasHeadInfo = hasHeadInfo;
	}
	
	
	
	

}
