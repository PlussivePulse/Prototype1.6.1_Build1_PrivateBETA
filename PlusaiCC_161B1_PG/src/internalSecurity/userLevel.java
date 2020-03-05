package internalSecurity;

import com.sun.security.auth.module.NTSystem;

import ccc.interaction.global.globalInteractionService;
import ccc.interaction.global.popUpBox;

public class userLevel {
	
	private Process userLevel;
	private String OSname;
	private ccc.interaction.global.globalInteractionService gISCore = new ccc.interaction.global.globalInteractionService();
	private securityErrorCode secErr = new securityErrorCode();
	private static int idLevel = -1;
	
	@Deprecated()
	protected int getUserLevel0() {
		
		String[] osVersion = gISCore.getOsVersion().split(" ");
		this.OSname = osVersion[0];
		if(osVersion[0].contains("windows")) { //Support for windows only
			try {
				String userCheck = "reg query \"HKU\\S-1-5-19\"";
				userLevel = Runtime.getRuntime().exec(userCheck);
				userLevel.waitFor();
				idLevel = userLevel.exitValue();
				if(idLevel==-1) {
					popUpBox.alertError("Internal System Unknown Error : UserLevel = -1 (WIndows)", (String) new Throwable().getStackTrace()[0].getClassName());
				}
			}catch(Exception e){
				popUpBox.alertError("Rare Internal System Unknown Error : Exception e." + e.toString() + "(Window)" , (String) new Throwable().getStackTrace()[0].getClassName());	
			}
		}else if(OSname.equals("mac os")){
			popUpBox.alertError("The program doesn't support Mac OS userLevel check", "userLevel : Attention");
			idLevel = -2; //mac os incompatibility.
		}
		if(directoryPathRequest.OSDriveBypass==true) {
			idLevel = 0;
		}
		return idLevel;
	}
		
	protected static int getUserLevel() {
			

		if(globalInteractionService.getOsVersion().contains("Windows")) {
			String groups[] = (new com.sun.security.auth.module.NTSystem()).getGroupIDs();
			
			for(String group : groups) {
				if(group.contentEquals("S-1-5-32-544")) {
					idLevel = 0;
				}
			}
			
			if(idLevel==-1) {
				popUpBox.alertError("Internal System Unknown Error : UserLevel = -1 (WIndows)", (String) new Throwable().getStackTrace()[0].getClassName());
			}
		}else if(globalInteractionService.getOsVersion().equals("mac os")){
			popUpBox.alertError("The program doesn't support Mac OS userLevel check", "userLevel : Attention");
			idLevel = -2; //mac os incompatibility.
		}
		
		return idLevel;
	}
	
	private void modifyProcess() {
		
	}
}
