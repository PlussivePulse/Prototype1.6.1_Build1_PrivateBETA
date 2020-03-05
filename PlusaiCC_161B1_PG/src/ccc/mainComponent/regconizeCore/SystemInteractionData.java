package ccc.mainComponent.regconizeCore;

public class SystemInteractionData {
	public static String systemLastGreet = "";
	
	public static String getLastGreeting() {
		if(systemLastGreet.equals("")) {
			systemLastGreet = "empty";
		}
		return systemLastGreet;
		
	}
}
