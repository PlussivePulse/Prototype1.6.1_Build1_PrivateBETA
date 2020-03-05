package internalSecurity;

import servicePackage.serviceLoader;

public class emergencyStop{
	
	static void oncePop() {
		serviceLoader loadSrv = new serviceLoader();
		loadSrv.getGIS().emergencyStopCT(); //update 1.1
	}
	
	static void oncePop(String input) {
		serviceLoader loadSrv = new serviceLoader();
		loadSrv.getGIS().custom_emergencyStopCT(input);
	}
	
	protected static void autoCheckOvrUse() {
		
	}
}
