package ccc.mainComponent;

public class ServiceLoad {
	
	public boolean official_trayNotification = false;
	
	public void setTrayNotification(boolean value) {this.official_trayNotification = value;}
	
	public void loadStable() { //re-config class variable value
		official_trayNotification = false;
	}
}
