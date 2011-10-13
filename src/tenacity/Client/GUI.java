package tenacity.Client;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;

public class GUI {	
	
	static public Container pane;
	static public JFrame frame;
	static Font font = new Font("Arial",Font.PLAIN,12);
	static public JTextPane ta1;
	static public JScrollPane ta1Pane;
	static public JTextField entry;
	static public StyledDocument doc;
	static public JLabel timeLabel;
	static public StyleManager styleManager;
	
	static void initGUI() {
		    	
		    	frame = new JFrame("Tenacity Client");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
		        pane = frame.getContentPane();
		        
		        pane.setLayout(new GridBagLayout());
		        GridBagConstraints c = new GridBagConstraints();
		
		        ta1 = new JTextPane();
		        ta1Pane = new JScrollPane(ta1);
		        ta1Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		        ta1Pane.setAutoscrolls(true);
		        ta1.setBackground(StyleManager.bgColor);
		        ta1.setForeground(StyleManager.fgColor);
		        c.fill = GridBagConstraints.BOTH;
		        c.weightx = 1.0;
		        c.weighty = 1.0;
		        c.gridx = 0;
		        c.gridy = 0;
		        c.gridwidth = 3;
		        c.gridheight = 1;
		        doc = ta1.getStyledDocument();
		        pane.add(ta1Pane, c);
		        
		        entry = new JTextField();
		        entry.addActionListener(new GUIActionListener()); // this adds the class
		        entry.setBackground(StyleManager.bgColor);
		        entry.setForeground(StyleManager.fgColor);
		        c.fill = GridBagConstraints.BOTH;
		        c.weightx = 1.0;
		        c.weighty = 0.005;
		        c.gridx = 0;
		        c.gridy = 2;
		        c.gridwidth = 1;
		        c.gridheight = 1;
		        pane.add(entry, c);
		        
		        styleManager = new StyleManager();
		        
		        //Size and display the window.
		        frame.setSize(800,600);
		        frame.setVisible(true);
		    }
		    
			public static void print(String str) {
		        try {
		        	System.out.println(str+" printed");
					doc.insertString(doc.getLength(), str, doc.getStyle("regular"));
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
			    
			public static void print(String str, boolean newline) {
			    try {
					doc.insertString(doc.getLength(), str, doc.getStyle("regular"));
					if (newline) {
						doc.insertString(doc.getLength(), "\n", null);
					}
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public static void printColor(String col, String str) {
			    try {
			    	System.out.println(str+" printed with color "+col);
					doc.insertString(doc.getLength(), str, doc.getStyle(col));
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public static void printColor(String col, String str, boolean newline) {
			    try {
					doc.insertString(doc.getLength(), str, doc.getStyle(col));
					if (newline) {
						doc.insertString(doc.getLength(), "\n", null);
					}
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//This method is pretty cool, just saying. If you pass any text to it with the string "\\_STYLE\" inside of it where "style"
			//is any of the styles from StyleManager in addStylesToDocument, it applies the styles to them. Pretty sexy if you ask me.
			public static void printParseColor(String str) {
			    try {
			    	//str = ENTIRE string
			    	String styleStr = null; // String that will hold the name of the style
			    	String segment = str; // INITIALIZES SEGMENT VAR TO STRING INPUTED
			    	int indBegin = 0;
			    	int indEnd = 0;
			    	if (segment.contains("\\_")) {
			    		doc.insertString(doc.getLength(), str.substring(0,str.indexOf("\\_")), doc.getStyle("regular")); // Is there regular text before a colorcode tag? If so, this prints it normally
			        	while (segment.contains("\\_")) {
			        		indBegin = segment.indexOf("\\_")+2; // Sets an index beginning here for 2 after \_, the place where the style string begins
			        		indEnd = segment.substring(indBegin+1).indexOf("\\")+indBegin+1; // Sets the end to be at the end of the closing \
			        		styleStr = segment.substring(indBegin,indEnd); // Sets the styleStr to be whatever is between indBegin and indEnd
			        		String tmpSeg = segment.substring(indEnd+1); // Sets tmpSeg to grab the end of the closing colorcode tag (\), or the beginning of actual text
			        		if (tmpSeg.contains("\\_"))  tmpSeg = tmpSeg.substring(0,tmpSeg.indexOf("\\_")); // If there's another colorcode coming up, set the tmpSeg to end before it
			        		doc.insertString(doc.getLength(), tmpSeg, doc.getStyle(styleStr));
			        		segment = segment.substring(indEnd+1);
			        	}
			    	} else doc.insertString(doc.getLength(), str, doc.getStyle("regular")); // Is there no color code defined? Just print whatever, then (though this shouldn't happen!)
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			
			public static void printParseColor(String str, boolean newline) { // ParseColor with optional newline argument
			    try {
			    	//str = ENTIRE string
			    	String styleStr = null; // String that will hold the name of the style
			    	String segment = str; // INITIALIZES SEGMENT VAR TO STRING INPUTED
			    	int indBegin = 0;
			    	int indEnd = 0;
			    	if (segment.contains("\\_")) {
			    		doc.insertString(doc.getLength(), str.substring(0,str.indexOf("\\_")), doc.getStyle("regular")); // Is there regular text before a colorcode tag? If so, this prints it normally
			        	while (segment.contains("\\_")) {
			        		indBegin = segment.indexOf("\\_")+2; // Sets an index beginning here for 2 after \_, the place where the style string begins
			        		indEnd = segment.substring(indBegin+1).indexOf("\\")+indBegin+1; // Sets the end to be at the end of the closing \
			        		styleStr = segment.substring(indBegin,indEnd); // Sets the styleStr to be whatever is between indBegin and indEnd
			        		String tmpSeg = segment.substring(indEnd+1); // Sets tmpSeg to grab the end of the closing colorcode tag (\), or the beginning of actual text
			        		if (tmpSeg.contains("\\_"))  tmpSeg = tmpSeg.substring(0,tmpSeg.indexOf("\\_")); // If there's another colorcode coming up, set the tmpSeg to end before it
			        		doc.insertString(doc.getLength(), tmpSeg, doc.getStyle(styleStr));
			        		segment = segment.substring(indEnd+1);
			        	}
			    	} else doc.insertString(doc.getLength(), str, doc.getStyle("regular"));// Is there no color code defined? Just print whatever, then (though this shouldn't happen!)
					if (newline==true) doc.insertString(doc.getLength(), "\n", doc.getStyle("regular"));
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			
			public static void printColoredVariable(String color, String str, String var) {
			    try {
					doc.insertString(doc.getLength(), str, doc.getStyle(color));
					doc.insertString(doc.getLength(), " "+var+"\n", doc.getStyle("regular"));
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public static void printColoredVariable(String color, String str, String var, boolean newline) {
			    try {
					doc.insertString(doc.getLength(), str, doc.getStyle(color));
					doc.insertString(doc.getLength(), " "+var, doc.getStyle("regular"));
					if (newline) {
						doc.insertString(doc.getLength(), "\n", null);
					}
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public static void println(String str) {
			    try {
					doc.insertString(doc.getLength(), str+"\n", doc.getStyle("regular"));
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		    
		    public static JTextField getEntryField() {
		    	return entry;
		    }
		    
		    public static String getEntryText(){
				return entry.getText();
		    }
		    
		    public static void setEntryText(String str){
		    	entry.setText(str);
		    }
		    
		    public static void clearEntry(){
		    	entry.setText("");
		    }
		    
		}