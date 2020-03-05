package ccc.interaction.global.database;

public class osVersionIdentifier{
	
	private String osName = "";
	private String osVersion = "";
	private String osArch = "";
	
	public osVersionIdentifier() {
		if(osName.equals("")) loadOSName();
		if(osVersion.equals("")) loadOSVersion();
	}
	
	private void loadOSName() {this.osName = System.getProperty("os.name");}
	private void loadOSVersion() {this.osVersion = System.getProperty("os.version");}
	private void loadOSArch() {this.osArch = System.getProperty("os.arch");}
	
	public String getOSName() {
		return osName;
	}
}
