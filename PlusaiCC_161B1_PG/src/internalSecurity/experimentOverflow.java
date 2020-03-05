package internalSecurity;

import ccc.interaction.global.popUpBox;

public class experimentOverflow {

	private static int TrayCore = 0;
	
	public boolean checkOvrf(String idReq) {
		boolean flow = false;
		int check = 0;
		if(idReq.equals("TrayCore")) {
			TrayCore++;
			if(TrayCore > 255) {
				flow = true;
				check = TrayCore;}
			else if(TrayCore > 2){
				popUpBox.alertError("Not overflow, run multiple times : " +  check, "");
			}
		}
		
		return flow;
	}
	
}
