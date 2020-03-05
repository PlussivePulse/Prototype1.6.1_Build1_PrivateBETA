package ccc.mainComponent.regconizeCore;

public class commandListsAddOn_iD {

	public static String check(String type, String target) {
		String returner = "null";
		if(target.toLowerCase().contains("lastsystemgreet")) {
			if(type.equals("get")) {
				returner = target  + " value = " + SystemInteractionData.getLastGreeting();
			}else if(type.equals("set")){
				returner = target  + " value = " + SystemInteractionData.getLastGreeting();
			}
		}else {
			returner = target + " variable couldn't be found or protected.";
		}
		return returner;
	}
}
