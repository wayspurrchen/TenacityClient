package tenacity.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

public class GUIActionListener implements ActionListener {
	
	
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==GUI.getEntryField())	{
			try {
				new InputProcessor(GUI.getEntryText());
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (SocketException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
