package ccc.mainComponent.regconizeCore;

import ccc.mainComponent.experimental.DEBUG;

public class AI6DATAUpdater extends AI6DataHolder{
	public static void updateVersion(String data, Class classObj) {
		if(classObj.getName().contains("dataInteractingAbility.ReadText")) {
			AI6DataHolder.SETversionDAATA(data);	
		}

	}
}
