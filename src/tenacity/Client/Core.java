package tenacity.Client;


public class Core {
	public static NetworkCore networkCore;
	
	public static void main(String args[]) throws Exception {
		GUI.initGUI();
		GUI.println("Client app");
		
		networkCore = new NetworkCore();
	}

}
