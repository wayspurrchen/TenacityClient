package tenacity.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSession extends Thread {
	
	public Socket socket;
	public boolean connected;
    PrintWriter out = null;
    BufferedReader in = null;
	
	ClientSession() {
		
	}
	
	public void connect(InetAddress address, int port) throws IOException {
        
        try {
        	socket = new Socket(address, port);
        	out = new PrintWriter(socket.getOutputStream(), true);
        	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        	connected = true;
        	start();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: "+address.getHostAddress());
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+address.getHostAddress());
        }
	}
	
	public void run() {
		System.out.println("ClientSession running");
		String fromServer;
		try {
			while ((fromServer = in.readLine()) != null) {
			    System.out.println("Server: " + fromServer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
