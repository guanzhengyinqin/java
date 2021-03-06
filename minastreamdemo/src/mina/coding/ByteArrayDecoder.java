package mina.coding;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import mina.message.MessageInfo;
import mina.util.MinaUtil;

public class ByteArrayDecoder extends CumulativeProtocolDecoder {

	DataOutputStream sout;
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		try{
			ioBuffer.setAutoExpand(false);
			//ioBuffer.set
			MessageInfo messageInfo = (MessageInfo) session.getAttribute("messageInfo");
			if(null==messageInfo){
				messageInfo = new MessageInfo();
				messageInfo.setHasHeadInfo(true);
				if(ioBuffer.limit()>10){
					
					byte[] head = new byte[2];
					byte[] instruct = new byte[4];
					byte[] length = new byte[4];
					
					ioBuffer.get(head);
					ioBuffer.get(instruct);
					ioBuffer.get(length);
					int msLength = MinaUtil.byteArrayToInt(length);
					String strHead = new String(head,"utf-8");
					String strInstruct = new String(instruct,"utf-8");
					
					messageInfo.setHead(strHead);
					messageInfo.setInstruct(strInstruct);
					messageInfo.setMessageLength(msLength);
					if(ioBuffer.limit()-10==msLength){
						byte[] content = new byte[msLength];
						ioBuffer.get(content);
						String strContent = new String(content,"utf-8");
						System.out.println("内容："+strContent);
						messageInfo.setHasHeadInfo(true);
					}else{
						
						
						
						byte[] con = new byte[ioBuffer.limit()-ioBuffer.position()];
						ioBuffer.get(con);
						messageInfo.setReadingLength((messageInfo.getReadingLength()-10)+con.length);
						//DataOutputStream sout = null;
						if(null==sout){
							File f = new File("D:\\nioStreamTest\\receive\\c.jpg");
							if(!f.exists()){
								f.createNewFile();
							}
							sout = new DataOutputStream(new FileOutputStream(f));
						}
						
						if(messageInfo.getMessageLength()==messageInfo.getReadingLength()){
							messageInfo.setHasHeadInfo(true);
						}else{
							messageInfo.setHasHeadInfo(false);
						}
						
						sout.write(con);
						//ioBuffer.flip();
						//ioBuffer.clear();
						
					}
					
					session.setAttribute("messageInfo",messageInfo);
					
					if(!("ba".equals(strHead))){
						session.close(true);
					}
					
				}
			}else{
				
				if(messageInfo.isHasHeadInfo()){
					System.out.println("有头部信息");
					
				}else{
					//ioBuffer.flip();
					System.out.println("bufferCapacity:\t"+ioBuffer.capacity());
					byte[] con = new byte[ioBuffer.limit()-ioBuffer.position()];
					//ioBuffer.position(messageInfo.getReadingLength());
					ioBuffer.get(con);
					messageInfo.setReadingLength(messageInfo.getReadingLength()+con.length);
					
					if(null==sout){
						File f = new File("D:\\nioStreamTest\\receive\\c.jpg");
						if(!f.exists()){
							f.createNewFile();
						}
						sout = new DataOutputStream(new FileOutputStream(f));
					}
					
					if(messageInfo.getMessageLength()==messageInfo.getReadingLength()){
						messageInfo.setHasHeadInfo(true);
					}else{
						messageInfo.setHasHeadInfo(false);
					}
					
					sout.write(con);
					//ioBuffer.flip();
					//ioBuffer.clear();
				}
				
			}
			
			System.out.println("头："+messageInfo.getHead());
			System.out.println("指令："+messageInfo.getInstruct());
			System.out.println("长度："+messageInfo.getMessageLength());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

}
