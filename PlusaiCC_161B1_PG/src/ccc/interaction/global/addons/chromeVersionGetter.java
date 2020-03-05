package ccc.interaction.global.addons;

import ccc.interaction.global.cmdExecute;

public class chromeVersionGetter {

	public static String getVersion() {
		String returning = cmdExecute.executeCheck("wmic datafile where name=\"C:\\\\Program Files (x86)\\\\Google\\\\Chrome\\\\Application\\\\chrome.exe\" get Version /value");
		return returning;
	}
	
	public static String getSoftVersion() {
		String returning = cmdExecute.executeCheck("wmic datafile where name=\"C:\\\\Program Files (x86)\\\\Google\\\\Chrome\\\\Application\\\\chrome.exe\" get Version /value");
		returning = returning.substring(0,returning.indexOf("."));
		return returning;
	}
}
