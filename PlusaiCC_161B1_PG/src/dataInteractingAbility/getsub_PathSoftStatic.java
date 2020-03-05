package dataInteractingAbility;

import java.io.File;

import ccc.interaction.global.popUpBox;
import internalSecurity.directoryPathRequest;
import internalSecurity.securityErrorCode;

public class getsub_PathSoftStatic{
	
//Remade version of the original get_subPath from previous project.
//Code lost and unable to recover.
//static version, purposely made for fileCore.
	
	private static java.io.File targetFolder;
	private static File[] targetSubFolder;
	private static String [][] targetFileList;
	
	protected static directoryPathRequest dPR = new directoryPathRequest(); //static version only
	private securityErrorCode sErr = new securityErrorCode();
	
	protected static String[][] getList(String direction){
		if(dPR.checkDirectoryRequest(direction)==true) {
			targetFolder = new java.io.File(direction);
			targetSubFolder = targetFolder.listFiles();
			targetFileList = new String[2][targetSubFolder.length];
			dataInjector(direction);
		}else {
			popUpBox.alertError("No Permission Error.", (String) new Throwable().getStackTrace()[0].getMethodName());
		}
		
		return targetFileList;
	}

	private static void dataInjector(String direction) {
		for(int i = 0; i<targetFileList[0].length-1; i++) {
			targetFileList[0][i] = targetSubFolder[i].getName();
			targetFileList[1][i] = direction + targetSubFolder[i].getName();
		}
	}
	
}
