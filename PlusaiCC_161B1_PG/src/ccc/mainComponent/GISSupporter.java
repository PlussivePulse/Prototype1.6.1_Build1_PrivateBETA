package ccc.mainComponent;

import ccc.interaction.global.globalInteractionService;
import ccc.interaction.global.popUpBox;
import ccc.interaction.global.addons.TrayCore;
import ccc.interaction.global.database.osVersionIdentifier;

public class GISSupporter {
	
	private boolean local_traySupport;
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean local_traySupport() {
		if(java.awt.SystemTray.isSupported()) {
			if(globalInteractionService.getOsVersion().contains("10")){this.local_traySupport=true;}
			else if(globalInteractionService.getOsVersion().contains("7")){popUpBox.warningBox("Possible Windows 7 unsupported error.", this.getClass().getName());}
		}else {local_traySupport = false;}
		return local_traySupport;
	}
}
