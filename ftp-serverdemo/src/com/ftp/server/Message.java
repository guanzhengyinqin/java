package com.ftp.server;


public class Message 
{
     private String type;//保存信息类型，分号之前的部分
     private String body;//分号之后的部分
     private boolean valid;//信息是否有效，也就是信息是否含有
     public Message(String messageLine)
     {
    	 valid=false;
    	 type=body=null;
    	 int pos=messageLine.indexOf(":");
    	 if(pos>=0)
    	{
    		 type=messageLine.substring(0, pos).toUpperCase();//分号之前的字符
    		 body=messageLine.substring(pos+1);//分号之后的字符
    		 valid=true;//信息有效
    	}
    	 
     }
     public Message(String type,String body)
	 {
		 valid=true;
		 this.type=type;
		 this.body=body;
	 }
     /*返回信息的类型*/
     public String getType()
     {
    	 return type;
     }
     public String getBody()
     {
    	 return body;
     }
     public boolean isValid()
     {
    	 return valid;
     }
}
