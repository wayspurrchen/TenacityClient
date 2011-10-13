package tenacity.Client;

import java.net.Socket;

public class NetworkCore {
	
	public static Socket socket;
	static ClientSession clientSession;
	
	public NetworkCore() {
		clientSession = new ClientSession();
	}
	
}
