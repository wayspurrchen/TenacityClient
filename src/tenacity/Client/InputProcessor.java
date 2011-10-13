package tenacity.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class InputProcessor {
	String input;
	String splitInput[];
	
	public InputProcessor(String input) throws NumberFormatException, SocketException {
		this.input = input;
		splitInput = splitInputText();
		processLocalInput();
		System.out.println("entry processor thread "+this+" initiated, local mode");
	}
	
	/*public InputProcessor(DatagramPacket packet, String input) throws UnknownHostException {
		this.input = input;
		splitInput = splitInputText();
		System.out.println("entry processor thread "+this+" initiated, packet mode");
		processPacketInput(packet);
	}*/
	
	String[] splitInputText() {
		String REGEX = " ";
		
		Pattern p = Pattern.compile(REGEX);
	    String[] line = p.split(input); //split debug words into items[] via spaces
	    for (int i = 0; i < line.length; i++) {
	    	line[i].toLowerCase();
	    	System.out.print(line[i]+"="+i+":");
	    } //shows debug info about each item
	    System.out.println();
	    return line;
	}
	
	String stringAfter(String[] stringArray, int after) {
		String fullLine = "";
		for (int i = after+1;i<stringArray.length;i++) {
			fullLine += stringArray[i];
			if (i!=stringArray.length) fullLine+=" ";
		}
		return fullLine;
	}
	
	//Old, used for UDP--no more UDP!
	/*public void processPacketInput(DatagramPacket packet) throws UnknownHostException {
		System.out.println(packet+" "+input);
		if (splitInput[0].equals("SESSIONCREATE")) {
			NetworkCore.clientConnected = true;
			NetworkCore.clientSessionID = Integer.parseInt(splitInput[1]);
			NetworkCore.serverAddress = InetAddress.getByName(splitInput[2]);
			NetworkCore.serverPort = Integer.parseInt(splitInput[3]);
			System.out.println("Client Connected: "+NetworkCore.clientConnected);
			System.out.println("Client session created: "+NetworkCore.clientSessionID);
			System.out.println("Server address: "+NetworkCore.serverAddress);
			System.out.println("Server port: "+NetworkCore.serverPort);
		} else {
			new ServerStringProcessor(input);
		}
	}*/
	
	public void processLocalInput() throws NumberFormatException, SocketException {
		
		if (splitInput[0].equals("connect")) {
			if (splitInput.length==1) {
				//Connects to "self" by default with just the "connect" phrase
				try {
					NetworkCore.clientSession.connect(InetAddress.getLocalHost(), 4444);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (splitInput.length>1) {
				String string = "CONREQ "+NetworkCore.socket.getLocalPort()+" "+splitInput[3];
				try {
		        	InetAddress address = InetAddress.getByName(splitInput[1]);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			if (NetworkCore.clientSession.connected) NetworkCore.clientSession.out.println(input);
		}
		
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}
