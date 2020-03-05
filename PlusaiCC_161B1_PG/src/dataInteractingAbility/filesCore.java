package dataInteractingAbility;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import ccc.interaction.global.TextReaderOne;
import ccc.interaction.global.directorySelector;
import ccc.interaction.global.popUpBox;
import ccc.mainComponent.experimental.DEBUG;

//first fileCore development

public final class filesCore {
	private static java.io.File fileLoader;
	
	public final boolean requireStabilizer = false;
	public static String currentLocation;
	
	//@Deprecated(since="B1.0-undocumented08062019")
	public boolean fileExist(String direction) {
		boolean founded = false;
		String[][] tempFileDirc = getsub_PathSoftStatic.getList(direction); //security flaw
		if(fileLoader != null) {
			fileLoader = new java.io.File(direction);
			founded = fileLoader.exists();
		}
		popUpBox.alertError("FileCore method named fileExist has been illegally executed.", "Alert : Deprecated method has been executed");;
		return founded;
	}
	
	public static boolean checkFileExist(String directory){ //first version to use as fileCore.
		boolean founded = false; //default
		boolean authorize = getsub_PathSoftStatic.dPR.checkDirectoryRequest(directory);
		if(authorize == true) {
			fileLoader = new java.io.File(directory);
			founded = fileLoader.exists();
		}
		return founded;	
	}
	
	protected boolean checkFileIfExist(String directory){ //bypass method only for creator
		boolean founded = false; //default
		fileLoader = new java.io.File(directory);
		founded = fileLoader.exists();
		return founded;	
	}
	
	//@Deprecated(since="08252019-undocumented")
	public static String[][] loadAll() {
		String[][] path = getsub_PathSoftStatic.getList("C:\\");
		return path;
	}
	
	public static ArrayList loadAllDirectory() {
		ArrayList path = getAbsolutePath_Substitude.loadAll();
		return path;
	}
	
	public static String[][] loadDirectory(){
		getsub_PathSoft pathSoft = new getsub_PathSoft();
		String[][] path = pathSoft.getList("C:\\");
		return path;
		
	}
	
	public static String findFile(String target) {
		@SuppressWarnings("unchecked")
		String pathFound = getAbsolutePath_Substitude.loadAllFinder(target);

		return pathFound;
	}
	
	public static void createFile(String name, String direction) {
		createFile.createFile(name, direction);
	}
	
	public static void readText(String des) {
		TextReaderOne reader = new TextReaderOne();
		File fileObj = new File(des);
		reader.loadText(fileObj);
	}
	
	public static void getAllHidden() {
		
		ArrayList<File> allDir = loadAllDirectory();
		
		for(File eachFile:allDir) {
			if(eachFile.isHidden()) {
				DEBUG.print("\n" + eachFile);
			}
		}
	}
	
	public static String getDir() throws URISyntaxException {
		return new File(filesCore.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
	}
	
	public static void moveEmg() {
		try {fileTransport.transferBase(directorySelector.DirectorySelector());}
		catch(NullPointerException ex) {}
	}
	
	public static void preLoad() {
		try {
			currentLocation = getDir();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
