package ccc.mainComponent;

import java.util.ArrayList;

import ccc.interaction.global.popUpBox;
import ccc.mainComponent.experimental.DEBUG;
import ccc.mainComponent.regconizeCore.prefixTransformer;
import ccc.mainComponent.regconizeCore.unCoreProcessor;
import ccc.mainComponent.regconizeCore.wordsTransformer;
import ccc.mainComponent.regconizeCore.languages.engDB;
import servicePackage.stabilizer;

public class Systems {

	private boolean empty = false;
	private String handle;
	private String output;
	
	public static String oldText = "";
	public static String infrontTextCustom = "";
	private static boolean allSystemLoaded = false;
	private static boolean executed = false;
	
	public String submitString(String text) {
		
		if(isAllSystemLoaded() == false) {
			popUpBox.alertError("System is still starting, please wait...", "System internal alert.");
		}else {
	
			oldText = text;
			if(!infrontTextCustom.equals("")) {
				text = infrontTextCustom +" "+ "-" + text ;
			}
			//DEBUG.print("CUSTOM " + infrontTextCustom);
			globalID.addID(1.0);
			prefixTransformer.command = false; //patch command not resetting
			handle = unCoreProcessor.getInput(wordsTransformer.Transform(text));
			
			if(handle.contains("[SYSTEMRequest]")) {
				String concept[] = handle.split(":");
				resetAll();
				submitString(concept[2]);
			}
			
			
			//text reset
			text = text.replaceAll("-", " ");
			
			DEBUG.print(handle);
			DEBUG.print("\n" + (text));
			
			output = "[" + globalID.getpureId() + "]";
			
			if(text.equals("<empty>")&&executed == false) {
				globalID.addID(0.1);
				handle = text + "\n" + globalID.getId() + AIname.getName() + " : The input can't be empty. <Stabilizer=true>";
				empty = true;
	
			}
			
	
			
			if(empty==true) {
				
				text = output + handle;
			}else {
				if(engDB.wrongSpacing==false) {
					text = "[" + globalID.getpureId() + "] " + userID.userID + " : " + text;
				}else {
					text = "[" + globalID.getpureId() + "] " + userID.userID + " : " + engDB.wSHandler;
					engDB.wSHandler = ""; //reset patch memoryLeak
				}
				globalID.addID(0.1);
				text = text + "\n[" + globalID.getpureId() + "]" + handle;
			}

		}
		text = text.replace("systemPrint", "");
		resetAll(); //bug patch
		return text;
		
	}

	public static boolean isAllSystemLoaded() {
		return allSystemLoaded;
	}

	public static void setAllSystemLoaded(boolean allSystemLoaded) {
		Systems.allSystemLoaded = allSystemLoaded;
	}
	public static void setSystemExecuted(boolean allSystemLoaded) {
		Systems.executed = allSystemLoaded;
	}
	public void resetAll() {
		
		wordsTransformer.output = null;
		this.empty = false; //bug patch
		prefixTransformer.command = false;
		unCoreProcessor.customRe = ""; //bugPatcher
		unCoreProcessor.stackingMemory = "";
		unCoreProcessor.contain = new ArrayList<String>(); //reset Memory
		unCoreProcessor.understandStatus = new ArrayList<Boolean>(); //reset Memory
		executed = false;
	}

	public static void setSystemEmpty(boolean e) {
		
	}
}
