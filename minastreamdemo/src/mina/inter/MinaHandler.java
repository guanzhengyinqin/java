package mina.inter;

import org.apache.mina.core.session.IoSession;

public interface MinaHandler {

	void receive(IoSession session, byte[] bytes);
	
}
