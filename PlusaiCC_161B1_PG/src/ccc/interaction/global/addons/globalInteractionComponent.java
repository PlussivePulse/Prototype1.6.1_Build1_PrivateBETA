package ccc.interaction.global.addons;

import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;

import ccc.interaction.global.globalInteractionService;

public class globalInteractionComponent extends globalInteractionService{
	
	private static TrayCore tc;
	private int failSafe = 0;
	private boolean trayActivation = false;
	
	public globalInteractionComponent() {}
	
	public TrayCore getTray() {
		return tc;
		
	}
	
	public void reloadTray() {
		try {tc = new TrayCore();
			trayActivation = true;} 
		catch (AWTException e) {e.printStackTrace();}
	}

	public void pushDefaultNotification() { //use to trigger default class.
		reloadTray(); //to get all needed variable
		tc.trayPushBox("Customized Notification",this.getClass().getName() ,this.getClass().getSimpleName() + "Internal Notification System Active. \n[Warning] Addons is unstable.\nThread is not terminating after run.", MessageType.WARNING);
	}
	
	@SuppressWarnings("static-access")
	public static void pushNotification(String boxType, String Title, String message, MessageType messageType) {
		tc.trayPushBox(boxType, Title, message, messageType);;
	}

}
