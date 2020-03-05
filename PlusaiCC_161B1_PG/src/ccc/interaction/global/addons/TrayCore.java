package ccc.interaction.global.addons;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import javax.swing.JFrame;

import ccc.interaction.global.globalInteractionService;
import ccc.interaction.global.notificationBox;
import ccc.mainComponentInternalCustomAnnotations.AddOnFeature;

public class TrayCore extends JFrame{

	private static java.awt.SystemTray TrayClass = java.awt.SystemTray.getSystemTray();
	private static Image icon;
	private static TrayIcon TrayPusher;
	public String classID = this.getClass().getName();
	
	@AddOnFeature()
	public TrayCore() throws java.awt.AWTException{
		icon = Toolkit.getDefaultToolkit().createImage("iconMain.png");
		TrayPusher = new TrayIcon(icon, "TrayCore");
		TrayPusher.setImageAutoSize(true);
		TrayPusher.setToolTip("ccc.interaction.global");
		TrayClass.add(TrayPusher);
		globalInteractionService.TrayBoxAC = true;
	}

	protected static void trayPushBox(String boxType, String Title, String message, MessageType messageType) {
		if(boxType.equals("") || boxType==null) { boxType = "";}
		else {boxType = boxType + " : ";}
		TrayPusher.displayMessage(boxType + Title, message, messageType);
		
	}
}


// make variable static to increase stableness.