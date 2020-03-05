package ccc.mainComponent;

//develop interface sample in A5 recreate attempt in A6

public class wordFormatter {
	
	private static String illegalText = "";
	
	public String properTitle(String text) {// formatter for Title popUp
		if(text.contains(":")) {
			text.replace(" : ", "");
			text = checkTrickSpace(text);
		}
		return text;
	}
	
	public String checkTrickSpace(String text) { //subGlobal
		String checkText = text.toLowerCase();
		if(!checkText.contains("abcdefghijklmnopqrstuvwxyz")) {
			if(checkText.contains(illegalText)){
				for (int i = 0; i<checkText.length()-1; i ++) {
					char lock = checkText.charAt(i);
					String targetFinder = lock+"";
					if(targetFinder.contains(illegalText)) {
						checkText = checkText.substring(0,i) + checkText.substring(i+1);
					}
				}
				if(checkText.substring(checkText.length()-1).contains(illegalText)){
					checkText = checkText.substring(0, checkText.length()) + checkText.substring(checkText.length()-1);
				}
			}
		}
		return text;
	}
}
