package dataInteractingAbility;

import java.io.File;

import ccc.interaction.global.popUpBox;
import internalSecurity.directoryPathRequest;
import internalSecurity.securityErrorCode;

public class getsub_PathSoft{
	
//Remade version of the original get_subPath from previous project.
//Code lost and unable to recover.
//A5 feature mimic to A6.
	
	private java.io.File targetFolder;
	private File[] targetSubFolder;
	private String [][] targetFileList;
	
	private directoryPathRequest dPR = new directoryPathRequest();
	private securityErrorCode sErr = new securityErrorCode();
	
	public final boolean requireStabilizer = false;
	
	public String[][] getList(String direction){
		if(dPR.checkDirectoryRequest(direction)==true) {
			this.targetFolder = new java.io.File(direction);
			targetSubFolder = targetFolder.listFiles();
			for(File path : targetSubFolder) {
				//System.out.println(path);
			}
			targetFileList = new String[2][targetSubFolder.length];
			dataInjector(direction);
		}else {
			popUpBox.alertError("No Permission Error.", (String) new Throwable().getStackTrace()[0].getMethodName());
		}
		
		return targetFileList;
	}

	private void dataInjector(String direction) {
		for(int i = 0; i<targetFileList[0].length-1; i++) {
			targetFileList[0][i] = targetSubFolder[i].getName();
			targetFileList[1][i] = direction + targetSubFolder[i].getName();
		}
	}
	
	/*private void dataInjectorLoader(String direction) { //FAILED
		File subStitude = new File(direction);
		String newStorage[][] = new String[targetFileList.length][targetSubFolder.length + subStitude.listFiles().length];
		for(int i = 0; i<targetFileList[0].length-1; i++) {
			targetFileList[0][i] = targetSubFolder[i].getName();
			targetFileList[1][i] = direction + targetSubFolder[i].getName();
	}}*/
	
	
	
}
