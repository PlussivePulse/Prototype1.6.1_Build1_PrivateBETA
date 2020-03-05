package internalSecurity;

import ccc.interaction.global.popUpBox;

public class errorHandle {

	public errorHandle(String method) {
		popUpBox.alertError(method, "Error");
	}
	
	public void impError(String method) {
		popUpBox.alertError(method,"Security Alert : Impossible error by code tempering.");
		
	}
}
