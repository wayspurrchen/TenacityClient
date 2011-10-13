package tenacity.Client;

import java.awt.Color;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/*
 * 
 * Manages all colors including string coloration.
 * 
 * 
 * 
 * 
 * 
 */


public class StyleManager {
	public static Color bgColor = new Color(6,9,5);         //Background color of JTextPane ta1
	public static Color fgColor = new Color(210,210,210);   //Foreground color of JTextPane ta1
	public static Color grayedColor = new Color(94,94,94);
	public static Color infoColor = new Color(128,160,130);		//Nice green color for info stuff
	public static Color placeColor = new Color(176,150,224);	//Nice lavender color of places
	public static Color purpleColor = new Color(186,82,141);
	public static Color playerColor = new Color(208,200,122);
	public static Color beingColor = new Color(179,226,188);		//Nice purple color of beings
	public static Color itemColor = new Color(229,225,201);		//Nice gold color of items
	static StyledDocument doc = GUI.doc;
	static Style def;
	
	StyleManager() {
		doc = GUI.doc;
		addStylesToDocument();
	}
	
    public static void addStylesToDocument() {
    	def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
    	
    	Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");
        StyleConstants.setForeground(def, fgColor);
        
        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, 10);

        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);
        
        s = doc.addStyle("info", regular);
        StyleConstants.setForeground(s, infoColor);
        
        s = doc.addStyle("place", regular);
        StyleConstants.setForeground(s, placeColor);
        
        s = doc.addStyle("being", regular);
        StyleConstants.setForeground(s, beingColor);
        
        s = doc.addStyle("item", regular);
        StyleConstants.setForeground(s, itemColor);
        
        s = doc.addStyle("purple", regular);
        StyleConstants.setForeground(s, purpleColor);
        
        s = doc.addStyle("player", regular);
        StyleConstants.setForeground(s, playerColor);
    	
    }
}
