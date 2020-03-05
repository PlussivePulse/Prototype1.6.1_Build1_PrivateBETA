package servicePackage;

import java.awt.Toolkit;

import ccc.interaction.global.notificationBox;
import ccc.interaction.global.popUpBox;
import ccc.interaction.global.components.internalFileBridge;
import ccc.interaction.global.components.tabbedPaneIconLoader;
import ccc.interaction.global.database.DateAndTime;
import ccc.interaction.internalFeatures.serviceChecker;
import ccc.mainComponent.UI.mainRunnerCore;
import dataInteractingAbility.filesCore;
import internalSecurity.emergencyStop;
import internalSecurity.staticAccessIS;

public final class serviceLoader{
	
	private static ccc.interaction.global.globalInteractionService gIS;
	private static ccc.mainComponent.GISSupporter gISSupport;
	private static ccc.interaction.global.database.globalInteractionDB globalIDB = new ccc.interaction.global.database.globalInteractionDB();
	private static ccc.interaction.global.addons.globalInteractionComponent globalIC;
	private static ccc.interaction.global.popUpBox popUpCore;
	private static ccc.mainComponent.GISSupporter gISSup;
	private static notificationBox notiBox;
	private static ccc.interaction.global.notificationFrame notiFrame;
	private static internalSecurity.emergencyStop emergStop;
	private static ccc.mainComponent.Systems Systems;
	private static ccc.interaction.internalFeatures.serviceChecker serviceC;
	private static ccc.interaction.global.components.tabbedPaneIconLoader tabLoad;
	
	private static int loadCount = 0;
	/////////////////////////////////////////////////////////////////////////////
	
	public static ccc.interaction.global.globalInteractionService getGLobalInteractionService(){return gIS;}
	public static ccc.mainComponent.GISSupporter getGISSupport(){return gISSupport;}
	public static internalSecurity.emergencyStop getemerg(){return emergStop;}
	
	public static void preLoadHQ() {
		mainRunnerCore.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		serviceC = new serviceChecker();
		tabLoad = new tabbedPaneIconLoader();
		tabLoad.loadPicture();
		filesCore.preLoad();
		internalFileBridge.preLoad();
	}
	public static void loadID(String value) {
		String[] splitter = value.split(",");
		if(value.equals("gIS")) {
			gIS = new ccc.interaction.global.globalInteractionService();
			loadCount++;
		}
		
		else if(value.equals("gISSupport")) {
			gISSupport = new ccc.mainComponent.GISSupporter();
			if(gISSupport!=null)
			{
				if(gISSupport.local_traySupport()==true||gISSupport.local_traySupport()==false) {
					loadCount++;
				}
			}
		}
		else if (value.equals("emergStop")) {
			staticAccessIS.emergencyStop();
			popUpBox.alertError("Emergency Stop initiated.", Class.class.toString());
			if(emergStop != null) {loadCount++;}
		}
		else if (splitter.length>0)
			if (splitter[0].equals("emergStop")) {
			staticAccessIS.emergencyStop(splitter[1]);
			//popUpBox.alertError("Emergency Stop initiated.", Class.class.toString());
			if(emergStop != null) {loadCount++;}
		}
		
		if(DateAndTime.datNull()==false) {
			loadCount++;
		}else {
			DateAndTime.setLocalDaT();
			if(DateAndTime.datNull()==false) {
				loadCount++;
			}else {
				popUpBox.alertError("Unable to load DateAndTime.","ServiceLoader Alert :");
			}
		}
	}
	
	public static ccc.interaction.global.globalInteractionService getGIS(){
		return gIS;
	}
	
	public static ccc.mainComponent.Systems SystemLoad() {
		Systems = new ccc.mainComponent.Systems();
		return Systems;
		
	}
}
