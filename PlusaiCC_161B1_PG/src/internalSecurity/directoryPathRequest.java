package internalSecurity;

//A6 Development Component

import ccc.interaction.global.popUpBox;

public class directoryPathRequest extends userLevel{

	public static boolean OSDriveBypass = false; //caution soft security flaw
	public static boolean oneTimeByPass = false;
	private int currentLevel = -1;
	private int storeSave = 0;
	
	public static void oneTimeBypass() {
		oneTimeByPass = true;
	}
	
	public boolean checkDirectoryRequest(String directory) {
		if(oneTimeByPass == true) {
			OSDriveBypass = true;
			storeSave = currentLevel;
			currentLevel = 1;
		}
		
		boolean permissionAllow = false;
		if(super.getUserLevel()==0) {
			currentLevel = 1; //administrator
			permissionAllow = true;
		}else if(super.getUserLevel()==-2){
			popUpBox.warningBox("Security Feature Failure : Unable to check Mac OS support.", this.getClass().getName());

		}else if((super.getUserLevel()!=-2 && currentLevel != 1) && OSDriveBypass == true){
			//internal and creator execute path.
			
		}else if(OSDriveBypass == true) {
			permissionAllow = true;
			//announce bypass.
		}else if((super.getUserLevel()!=-2 && currentLevel != 1) && OSDriveBypass == false){
			String OSdrive = System.getenv("SystemDrive");
			if(directory.substring(0, 2).equals(OSdrive)) {
				permissionAllow = false;
				popUpBox.alertError("No permission - directory request.", "Custom : Internal Security");
			}else {permissionAllow = true;}
		}
		
		if(oneTimeByPass == true) {
			OSDriveBypass = false;
			currentLevel = storeSave;
		}
		return permissionAllow;
	}
	
	public String getOSDrive() {return System.getenv("SystemDrive");}
	
	public void secSoftCheck() {
		if(OSDriveBypass == true) {
			popUpBox.warningBox("OSDriveBypass = true, all file directory can be access without authentication by the system. \nPlease perform system check if this is not intended as admin use.", "Caution : dirtryPathReq. soft security alert");
		}
	}
}
