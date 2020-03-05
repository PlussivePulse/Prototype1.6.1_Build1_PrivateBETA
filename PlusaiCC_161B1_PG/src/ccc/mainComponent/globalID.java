package ccc.mainComponent;

import java.util.ArrayList;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.globalInteractionService;
import ccc.interaction.global.notificationFrame;
import ccc.interaction.global.popUpBox;
import ccc.mainComponent.experimental.DEBUG;

//created in A3 removed since then. Re-create in A6 with aiming of stableness.

public class globalID {
	private static double id = -1.0;
	private static int idD = 0;
	private static String invalidValue = "Invalid Value";
	private static String container;
	public static int alertCool = 0;
	
	public static int errorPopC = 0; //assume value
	private static int errorCount = 0; //load from txt later.
	private static ArrayList <Exception> errorStr = new ArrayList<Exception>();
	private static int pendingQuestion = 0;
	
	public boolean AItalking = false;
	
	private static boolean emergencyStopState = false;
	private static boolean isDouble;
	private static double roundNum=10.0;
	private static int idDD;
	
	public static void addErrorCount(Exception e){
		errorCount++;
		if(errorPopC!=errorCount) {
			errorPopC=errorCount;
			autoNotificationPusher.notPush("INFO", "Automatic Error Notifier", "Exception Error has been logged : " + e.toString(), Class.class.getClass(), "");
		}
		errorStr.add(e);
	}
	
	public static void addID(double value) {
		
		int checkL = 0;
		
		if(value==1) {
			idD = 0;
		}//patch idD not reseting.

		if(value >= 2.0 || value < -1.0) {
			//emergencyStop
			
		}
		
		
		else if(value > 1.0) {
			String check = value + "";
			check = check.substring(1);
			checkL = check.length();
			
			//<none> will cause no popup
		}else if(value == 1.0) {
			if(id%1.0!=0) {
				id = (double) (int) id;
				idD = 0;
				isDouble = false;
			}
			id++;
		}else if(value <= 1&&idD==0) {			
			for(double i = value; i>=value; i=-0.1) {
				//add ID
				if(id+value<1.0) {
					id = id + value;
				}else {
					idD++;
					//id = Math.round((id*1.00)-1)/1.00 + 0.10 + (idD/100);
					roundNum = 100.00;
					//id = id + value;
				}
				
				isDouble = true;
				}
		}else if(value <= 1&&idD>0) {			
			for(double i = value; i>=value; i=-0.1) {
					idD++;
					//id = Math.round((id*1.00)-1)/1.00 + 0.10 + (idD/100);
					//roundNum = 100.00;
					//id = id + value;
				}
				
				isDouble = true;
		}else if(value < 0.1) {			
			for(double i = value; i>=value; i=-0.1) {
				idDD++;
				//id = Math.round((id*1.00)-1)/1.00 + 0.10 + (idD/100);
				//roundNum = 100.00;
				//id = id + value;
			}
			
			isDouble = true;
		}else {
				//announce invalid decimal point
			if(idD==0) {
				id = id + value;
				isDouble = false;
			}
				//autoNotificationPusher.notPush("ALERT", invalidValue, "ID value is invalid : " + value + ". (Value must be as folloing format - 1.x)",Class.class ,"AUTO","globalID" , true);		
		}
		
		if(isDouble == true)
		id = Math.round(id*roundNum)/roundNum; //patch 0.x
	}
	
	private static void getClassObj() {
		container =  Class.class.getClass().getName();
	}
	
	public static void pendSoundAlert() {
		if(alertCool>-1) {
			alertCool++;
		}else{
			if(alertCool==-1) {
				popUpBox.alertError("Soundboard is not compatible.", "SoundBoard : Unable to play sound");
			}
		}
	}

	public static boolean isEmergencyStopState() {
		if(emergencyStopState==true) {
			String stopKey = "";
			if(globalInteractionService.getOsVersion().contains("Windows")) {
				stopKey = "Alt + F4";
			}else {
				stopKey = "ShotKeys";
			}
			globalInteractionService.custom_emergencyStopCT("[Emergency Stop Screen] :  <br/>The Program need to be stopped due to an unknown error or condition.</br>Please check logs for futher information. <br/>Click " + stopKey + " to exit this page. And perform program process terminate.");
		}
			
		return emergencyStopState;}
	public static void setEmergencyStopState(boolean emergencyStopState) {globalID.emergencyStopState = emergencyStopState;}

	public static int getPendingQuestion() {
		return pendingQuestion;
	}

	public static void addPendingQuestion() {
		globalID.pendingQuestion++;
	}

	public static String getId() {
		int tempIdD = idD-1;
		String returning = "" + id;
		int test = (int) (id*10);
		String tempIDString = id+"";
		int partLocator = tempIDString.indexOf(".");
		tempIDString = tempIDString.substring(0,partLocator);
		int idMultiplier = (int) id*10;
		if(idD>=0&&idMultiplier+idD<10) {
			returning = "" + tempIDString + "."+ (test+idD) + "" ;
		}else {
			returning = "" + tempIDString + "."+ (idD) + "" ;
		}
		return "["+returning+"] ";
	}
	
	public static String getpureId() {
		int tempIdD = idD-1;
		String returning = "" + id;
		int test = (int) (id*10);
		String tempIDString = id+"";
		int partLocator = tempIDString.indexOf(".");
		tempIDString = tempIDString.substring(0,partLocator);
		int idMultiplier = (int) id*10;
		if(idD>=0&&idMultiplier+idD<10) {
			returning = "" + tempIDString + "."+ (test+idD) + "" ;
		}else {
			returning = "" + tempIDString + "."+ (idD) + "" ;
		}
		return returning;
	}

	public static void setId(double id) {
		globalID.id = id;
	}
	
	

}
