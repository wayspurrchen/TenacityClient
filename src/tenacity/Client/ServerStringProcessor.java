package tenacity.Client;

/* 
 * 
 * This determines how to print strings based on the first string passed to it.
 * PRNT = PRINT LINE REGULARLY.
 * COLPRNT = PRINT WITH COLOR PARSER.
 * 
 * 
 */

public class ServerStringProcessor {
	String input;
	
	ServerStringProcessor(String text) {
		System.out.println("Server string processor started with text "+text);
		input = text;
		printServerString();
	}
	
	void printServerString() {
		if (input.startsWith("PRNT")) {
			System.out.println("Plain printing: "+input);
			GUI.println(input.substring(5));
		} else if (input.startsWith("COLPRNT")) {
			System.out.println("Printing with parsed color: "+input);
			GUI.printParseColor(input.substring(8), true);
		} else {
			System.out.println("ERROR: RECEIVED "+input+" WITHOUT DATA PREFIX");
		}
	}
	
}
