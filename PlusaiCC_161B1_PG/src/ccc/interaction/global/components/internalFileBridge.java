package ccc.interaction.global.components;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Files;

import ccc.interaction.global.globalInteractionService;
import ccc.interaction.global.popUpBox;
import ccc.mainComponent.UI.mainRunnerCore;
import ccc.mainComponent.experimental.DEBUG;
import dataInteractingAbility.createFile;
import dataInteractingAbility.filesCore;
import servicePackage.serviceLoader;

public class internalFileBridge {
	public static String mainFolderName = "AI6CoreFolder";
	private static int MprocessCount1 = 3;
	private static int MprocessCount2 = 1;
	private static int successCount = 0;
	public static void preLoad() {
		if(mainRunnerCore.displayer != null)
		mainRunnerCore.addMaximumBarLoad(MprocessCount1 + MprocessCount2);
	}
	public void seleniumDriverCOM() {
		int i = 0;
		DEBUG.print("DEBUG : selenium Driver Assistant software activated.");
		File folderCreation = null;
		try {folderCreation = new File(filesCore.getDir());}
		catch (URISyntaxException e) {e.printStackTrace();}
		
		File isExisted = new File(folderCreation.getParent()+"\\" + mainFolderName + "\\" + "seleniumDriver");
	
		// Section 1 : Check Folder
		if(isExisted.exists()) {
			//DEBUG.print("DEBUG : seleniumDriver directory detected. +\n" + isExisted.getAbsolutePath());
		}else {
			createFile.createFolder(folderCreation.getParent() + "\\" + mainFolderName, "seleniumDriver");
			//DEBUG.print("DEBUG : seleniumDriver directory create command triggered.");
		}
		// Section 2 : Check Driver
		// Section 3 : Copy Driver
		//File winchrome79 = new File(getClass().getResource("/driver/winchromedriver79.exe").getFile());
		////////////////////////////////////////////// Mac
		if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {
			URL wc79 = getClass().getResource("/driver/winchromedriver79.exe");
			File wc79d = new File(isExisted.getAbsolutePath()+"\\winchromedriver79.exe");
			URL wc80 = getClass().getResource("/driver/winchromedriver80.exe");
			File wc80d = new File(isExisted.getAbsolutePath()+"\\winchromedriver80.exe");
			URL wc81 = getClass().getResource("/driver/winchromedriver81.exe");
			File wc81d = new File(isExisted.getAbsolutePath()+"\\winchromedriver81.exe");
			//DEBUG.print("DEBUG : Default window driver is being install.");
			
			try {
				FileUtils.copyURLToFile(wc79, wc79d);
				i++;
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers (" + i + "/" + MprocessCount1 + ")" + " - Window Chrome Driver V.79",0);
				successCount++;
			} catch (IOException e) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 1 + "/" + MprocessCount1 + ")",0);
			} catch (NullPointerException n) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 1 + "/" + MprocessCount1 + ")" + " - File not found",0);
				//serviceLoader.loadID("emergStop,Possible Code Tempering Detected.");
			}
			
			try {
				FileUtils.copyURLToFile(wc80, wc80d);
				i++;
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers (" + i + "/" + MprocessCount1 + ")" + " - Window Chrome Driver V.80",0);
				successCount++;
			} catch (IOException e) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 2 + "/" + MprocessCount1 + ")",0);
			} catch (NullPointerException n) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 2 + "/" + MprocessCount1 + ")" + " - File not found",0);
				//serviceLoader.loadID("emergStop,Possible Code Tempering Detected.");
			}
		
			try {
				FileUtils.copyURLToFile(wc81, wc81d);
				i++;
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers (" + i + "/" + MprocessCount1 + ")" + " - Window Chrome Driver V.81 NEW",0);
				successCount++;
			} catch (IOException e) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 3 + "/" + MprocessCount1 + ")",0);
			} catch (NullPointerException n) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 3 + "/" + MprocessCount1 + ")" + " - File not found",0);
				//serviceLoader.loadID("emergStop,Possible Code Tempering Detected.");
			}
		}else if(globalInteractionService.getOsVersion().toLowerCase().contains("mac")) {
			URL mc79 = getClass().getResource("/driver/macchromedriver79");
			File mc79d = new File(isExisted.getAbsolutePath()+"\\macchromedriver79");
			URL mc80 = getClass().getResource("/driver/macchromedriver80");
			File mc80d = new File(isExisted.getAbsolutePath()+"\\macchromedriver80");
			URL mc81 = getClass().getResource("/driver/macchromedriver81");
			File mc81d = new File(isExisted.getAbsolutePath()+"\\macchromedriver81");
			//DEBUG.print("DEBUG : Default Mac driver is being install.");
			
			try {
				FileUtils.copyURLToFile(mc79, mc79d);
				i++;
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers (" + i + "/" + MprocessCount1 + ")" + " - Mac Chrome Driver V.79 NEW",0);
				successCount++;
			} catch (IOException e) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 1 + "/" + MprocessCount1 + ")",0);
			} catch (NullPointerException n) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 1 + "/" + MprocessCount1 + ")" + " - File not found",0);
				popUpBox.alertError("Unable to find a file. Mac Chrome Driver V.79[", "Class : " + this.getClass() + "");
				//serviceLoader.loadID("emergStop,Possible Code Tempering Detected.");
			}
			
			
			try {
				FileUtils.copyURLToFile(mc80, mc80d);
				i++;
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers (" + i + "/" + MprocessCount1 + ")" + " - Mac Chrome Driver V.80 NEW",0);
				successCount++;
			} catch (IOException e) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 2 + "/" + MprocessCount1 + ")",0);
			} catch (NullPointerException n) {
				popUpBox.alertError("Unable to find a file. Mac Chrome Driver V.80", "Class : " + this.getClass() + "");
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 2 + "/" + MprocessCount1 + ")" + " - File not found",0);
				//serviceLoader.loadID("emergStop,Possible Code Tempering Detected.");
			}
		
			try {
				FileUtils.copyURLToFile(mc81, mc81d);
				i++;
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers (" + i + "/" + MprocessCount1 + ")" + " - Mac Chrome Driver V.81 NEW",0);
				successCount++;
			} catch (IOException e) {
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 3 + "/" + MprocessCount1 + ")",0);
			} catch (NullPointerException n) {
				popUpBox.alertError("Unable to find a file. Mac Chrome Driver V.81", "Class : " + this.getClass() + "");
				mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - solid drivers failed to install. (" + 3 + "/" + MprocessCount1 + ")" + " - File not found",0);
				//serviceLoader.loadID("emergStop,Possible Code Tempering Detected.");
			}
		}
		
		if(successCount == MprocessCount1) {
			mainRunnerCore.lblSeleniumstatus.setText("Available");
		}else if(successCount != MprocessCount1 && successCount > 0) {
			mainRunnerCore.lblSeleniumstatus.setText("Partial");
		}else if(successCount == 0) {
			mainRunnerCore.lblSeleniumstatus.setText("Unavailable");
		}
	}
	
	public void openCVCOM() {
		File folderCreation = null;
		try {folderCreation = new File(filesCore.getDir());}
		catch (URISyntaxException e) {e.printStackTrace();}
		
		File isExisted = new File(folderCreation.getParent()+"\\" + mainFolderName + "\\" + "openCV_Driver");
		if(isExisted.exists()) {
			//DEBUG.print("DEBUG : openCV_Driver directory detected. +\n" + isExisted.getAbsolutePath());
		}else {
			createFile.createFolder(folderCreation.getParent() + "\\" + mainFolderName, "openCV_Driver");
			//DEBUG.print("DEBUG : openCV_Driver directory create command triggered.");
		}
		
		URL openCV = getClass().getResource("/driver/opencv_java410.dll");
		File openCVd = new File(isExisted.getAbsolutePath()+"\\opencv_java410.dll");
		URL openCVHarr = getClass().getResource("/driver/haarcascade_frontalface_alt.xml");
		File openCVHarrd = new File(isExisted.getAbsolutePath()+"\\haarcascade_frontalface_alt.xml");
		try {
			FileUtils.copyURLToFile(openCV, openCVd);
			mainRunnerCore.addBarLoad("System : [Component Service] internalFileBridge - openCV file.",0);
			FileUtils.copyURLToFile(openCVHarr, openCVHarrd);
			mainRunnerCore.addBarLoad("System : Component Haar-Cascade camera algorithm added.",0);
			mainRunnerCore.lblCamerastatus.setText("Available");
		} catch (IOException e) {
			e.printStackTrace();
			mainRunnerCore.lblCamerastatus.setText("Not Available");
		}
		
		File PicDatabase = new File(folderCreation.getParent()+"\\" + internalFileBridge.mainFolderName + "\\" + "Database\\FaceRegcognize");
		if(PicDatabase.exists()) {
			DEBUG.print("DEBUG : openCV_Driver Picture Database directory detected. +\n" + isExisted.getAbsolutePath());
		}else {
			createFile.createFolder(folderCreation.getParent() + "\\" + mainFolderName + "\\Database" , "FaceRegcognize");
			DEBUG.print("DEBUG : openCV_Driver Picture Database directory create command triggered.");
		}
	
	}
}
