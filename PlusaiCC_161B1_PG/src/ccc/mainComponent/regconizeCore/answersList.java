package ccc.mainComponent.regconizeCore;

import ccc.interaction.global.globalInteractionService;
import ccc.mainComponent.globalID;
import ccc.mainComponent.experimental.DEBUG;
import internalSecurity.*;
import servicePackage.serviceLoader;

public final class answersList {
	
	public final static String pronounStart[] = {"they","we","our","null"};
	public final static String shortPronounStart[] = {"them","themself","ours", "yours"};
	public final static String singlePronounStart[] = {"I","he","she","it","my"};
	
	private static int groupingNeed = 0;
	public final static String connectingNow1[] = {"is","am"};
	public final static String connectingNow1plural[] = {"are"};
	public final static String connectingPast1[] = {"was"};
	public final static String connectingPast11plural[] = {"were"};
	
	private static int ovflrCheck = 0;
	private static boolean isOverflow = false;
	private static String overFlowText = "";

	public static String pronounScan(String input) {
		String store = "";
		
		if(!globalID.isEmergencyStopState())
		if(!isOverflow)
			overFlowCheck();
		for(int i = 0; i<4; i++) {
			
			if(input.equals(pronounStart[i])) {
				store = "(pronounStart)" + input;
				groupingNeed = 1;
				overFlowText = "store";
			}else if(input.equals(singlePronounStart[i])) {
				store = "(singlePronounStart)" + input;
				groupingNeed = 2;
				overFlowText = "store";
			}else if(input.toLowerCase().equals(shortPronounStart[i])){
				store = "(singleInput)" + input;
				groupingNeed = 0;
				overFlowText = "store";
			}
			
		}
		resetPC();
		return store;
	}
	
	private void checkOvrfl() {if(ovflrCheck==2) {isOverflow = true;}}
	private static void overFlowCheck() {
		ovflrCheck++;
		if(ovflrCheck >1) {
			emergencyStop e = serviceLoader.getemerg();
			if(overFlowText.equals("")) {
				overFlowText = "unknown words - possible first run check code malfunction or code tempering.";
			}
			globalInteractionService.custom_emergencyStopCT("Rare Custom EmergencyStop, we have detected a possible <font color = 'red'>stack overflow error. <font color = 'white'>Please check or contact admin of the process(es) you have been doing. <br/> From : regcognizeCore - while transforming first part of the text : " + overFlowText );
		}
	}
	public static void resetOvrflr() {ovflrCheck = 0;}
	
	private int getGN(){return groupingNeed;}
	
	private static void resetPC() {
		groupingNeed = 0;
		resetOvrflr();
	}
}
