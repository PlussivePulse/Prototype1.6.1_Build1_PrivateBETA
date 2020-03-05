package ccc.interaction.global;

import java.awt.AWTException;

import ccc.interaction.global.addons.TrayCore;

//A6 Function Add-On Control
public final class notificationBox extends globalInteractionService{

	private String classString;
	private globalConfigLoader gConfigLoader;
	private popUpBox popUp = new popUpBox();
	private boolean globalLoadValue;
	private boolean trayAvaibility;
	public boolean trayActive = false;
	
	public String classType = "addonsLoader";
	
	private ccc.mainComponent.GISSupporter giss = new ccc.mainComponent.GISSupporter();
	private ccc.interaction.global.addons.TrayCore trayNotification;
	
	@SuppressWarnings("static-access")
	public notificationBox(@SuppressWarnings("rawtypes") Class classObject, boolean globalLoad) {
		classString = classObject.getName();
		this.globalLoadValue = globalLoad;
		if(globalLoad==true) {
			//store alert somewhere here
			gConfigLoader = new globalConfigLoader(this.getClass().getName());
			this.loadTrayService();
		}else if(globalLoad==false) {
			popUp.infoBox
					("Global Load = " +
					String.valueOf(globalLoad),
					notificationBox.class,
					(String) new Throwable().getStackTrace()[0].getMethodName());
			//Prompt user to set the data
			this.loadTrayService();
					
		}else{}
	}
	
	public notificationBox() throws AWTException { //handle empty passing
		trayNotification = new ccc.interaction.global.addons.TrayCore();
	}
	
	public void pushBox() {
		
	}
	
	protected void loadTrayService() {
		if(giss.local_traySupport()==true) {
			this.trayAvaibility = true;
			super.loadTrayService(trayAvaibility, new Throwable().getStackTrace()[0]); //this is quick command to load object constructor.
			trayActive = true;
		}else {
			popUpBox.alertError("Tray is not supported.", (String) new Throwable().getStackTrace()[0].getMethodName());
			this.trayAvaibility = false;
		}
	}
	
	public String getHashCode() {
		return String.valueOf(new Throwable().getStackTrace()[0].hashCode());
	}
	
	private boolean runNotification() {
		return trayActive;
	}
	
}
//java.util.Arrays.toString((java.lang.reflect.Field[])(Object.class.getClass().getFields())) //Get Class Method Variable
//java.util.Arrays.toString((java.lang.reflect.Field[])classObject.getDeclaredFields())