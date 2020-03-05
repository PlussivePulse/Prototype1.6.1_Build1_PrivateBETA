package ccc.interaction.global;

import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;

import ccc.interaction.global.addons.globalInteractionComponent;
import ccc.mainComponent.GISSupporter;

public class globalInteractionService{
	
	static ccc.interaction.global.database.globalInteractionDB globalIDB = new ccc.interaction.global.database.globalInteractionDB();
	static ccc.interaction.global.addons.globalInteractionComponent globalIC;
	static ccc.interaction.global.popUpBox popUpCore;
	static ccc.mainComponent.GISSupporter gISSup;
	static notificationBox notiBox;
	static ccc.interaction.global.notificationFrame notiFrame;
	
	public static boolean popUpCoreAC = true;
	public static boolean TrayBoxAC = false;
	public static boolean FrameAC = false;

	public final boolean requireStabilizer = true;

	globalConfigLoader configLoader = new globalConfigLoader(localClassName());
	
	public static String getOsVersion() { //public since it does not required protection
		return globalIDB.getOSData().getOSName();
	}

	protected static void popUp(String text, Class<?> classObject, String title) {
		popUpBox.infoBox(text, classObject, title); //Preferred popUpBox instead of popUpCore since it is more stable
	}
	
	protected String localClassName() { //Default in almost every important class.
		return this.getClass().getName();
	}

	// Add-ons : Tray Section ///////////////////////////////////////////////////////////////
	
	@SuppressWarnings("static-access")
	public static void loadTrayService(boolean trayAvaibility, StackTraceElement e) {
		String callBack = String.valueOf(e.hashCode());
		System.out.println(callBack);
		if(trayAvaibility==true) {
			globalIC = new ccc.interaction.global.addons.globalInteractionComponent();
			globalIC.pushDefaultNotification();
		}else {
			popUpCore.alertError("Illegal command execution detected at loadTrayService-globalInteractionService\n Impossible execution by code tempering.", "Internal Security Handler");
			emergencyStopCT();
			
		}
	}
	
	@SuppressWarnings("static-access")
	public void loadTray() {
		try {
			notiBox = new notificationBox();
			notiBox.loadTrayService();
			TrayBoxAC = notiBox.trayActive;
		} catch (AWTException e) {
			popUpCore.alertError(e.toString(), this.getClass().getName());
			e.printStackTrace();
		}
	}
	
	protected void loadTrayService(){ //bypass vulnerability.
			globalIC = new ccc.interaction.global.addons.globalInteractionComponent();
			globalIC.pushDefaultNotification();
	}
	
	// End Tray //////////////////////////////////////////////////////////////////////////////
	
	//Emergency Stop Frame
	public static void emergencyStopCT() { //code tempering detected stop.
		notiFrame = new notificationFrame();
		notiFrame.emergencyStopScreenCT();
	}
	
	public static void custom_emergencyStopCT(String input) { //code tempering detected stop.
		notiFrame = new notificationFrame();
		notiFrame.custom_emergencyStopScreenCT(input);
	}
	
	public static void pushNotiTray(String boxType, String Title, String message, MessageType messageType) {
		globalInteractionComponent.pushNotification(boxType, Title, message, messageType);}
	
	public static void pushSelector(ArrayList lists) {allBoxLoader.boxLoad(lists);}
	public static void pushSelector(String[] lists) {allBoxLoader.boxLoad(lists);
	
	}
}
