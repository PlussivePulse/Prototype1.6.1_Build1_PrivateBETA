package ccc.interaction.global.database;

import java.awt.AWTException;

import ccc.interaction.global.popUpBox;

public class globalInteractionDB {
	osVersionIdentifier osData = new osVersionIdentifier(); //doesn't need to be extended.
	ccc.interaction.global.addons.TrayCore trayCore ;
	
	public osVersionIdentifier getOSData() { //considered as public data
		return osData;
	}
	
	public ccc.interaction.global.addons.TrayCore trayCVariableUpdater() {
		try {
			trayCore = new ccc.interaction.global.addons.TrayCore();
		} catch (AWTException e) {
			popUpBox.alertError(String.valueOf(e), this.getClass().getName());
		}
		return trayCore;
	}
}
