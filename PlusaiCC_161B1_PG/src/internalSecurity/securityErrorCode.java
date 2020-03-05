package internalSecurity;

import java.util.ArrayList;
import java.util.Arrays;

public class securityErrorCode{

	private ccc.interaction.global.globalInteractionService gISCore = new ccc.interaction.global.globalInteractionService();
	
	public String checkError(String errorCode[]) { //private for some class.
		ArrayList<String> errorCodeData = (ArrayList<String>) Arrays.asList(errorCode);
		String errorString = "securityErrorCode-Result-nothingFound";
	    for(int i = 0; i<errorCodeData.size(); i++) {
	    	String currentCode = errorCodeData.get(i);
	    	if(currentCode.contains("unsupportedOS")) {
	    		addErrorString(errorString, gISCore.getOsVersion() + "is unsupported.");
	    	}else if(currentCode.contains("unableToCheckUserLevel")){
	    		addErrorString(errorString, "Error : Unable to check user level.");
	    	}else if(currentCode.equals("ctStop")) {
	    		addErrorString(errorString, "Code Tempering Detected.");
	    	}
	    	if(i<errorCodeData.size()) {errorString += "\n";}
	    }
		return errorString;
		
	}
	
	private String addErrorString(String errorString,String addValue) {
		if(errorString.equals("securityErrorCode-Result-nothingFound")) {errorString = "";}
		errorString += addValue;
		return errorString;
		
	}
}
