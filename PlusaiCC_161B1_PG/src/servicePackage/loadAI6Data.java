package servicePackage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import ccc.interaction.internalFeatures.alarmClock;
import ccc.mainComponent.UIComponents.mainRunnerCoreManager;
import ccc.mainComponent.UIComponents.restartApplication;
import ccc.mainComponent.experimental.DEBUG;
import ccc.mainComponent.regconizeCore.AI6DATAUpdater;
import ccc.mainComponent.regconizeCore.SystemInteractionData;
import dataInteractingAbility.ReadText;
import dataInteractingAbility.createFile;
import dataInteractingAbility.filesCore;
import dataInteractingAbility.writeText;

public class loadAI6Data extends ReadText{
	private static boolean founded = false;
	private static String target = "";
	private static String targets= "";

	public static void load() {
		String data = "";
		File locate = null;
		String target = "";
		try {
			locate = new File(filesCore.getDir());
			
			File checkFile = new File(locate.getParentFile()+"\\AI6Data.txt");
			File checkFile2 = new File(locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt");
			
			if(checkFile.exists()&&founded  == false) {
				data = loadAllText(locate.getParentFile()+"\\AI6Data.txt");
				DEBUG.print("Found local AI6Data.");
				target = locate.getParentFile()+"\\AI6Data.txt";
				founded = true;
			}else if(checkFile2.exists() && founded == false){
				data = loadAllText(locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt"); 
				DEBUG.print("Found alternative AI6Data.");
				target = locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt";
				founded = true;
			}else {
				//create new one
				try {
					createFile.createFile(locate.getParentFile()+"\\AI6CoreFolder", "AI6Data", "txt");
					target = locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt";
					writeText.Write("[Core]=\n", target);
					writeText.Write("version=1.0\n", target);
					writeText.Write("ccversion=Control Center Panel V.1.0\n", target);
					writeText.Write("[LocalMemory]=\n", target);
					writeText.Write("todayLastGreet=\n", target);
					writeText.Write("[Alarm]=\n", target);
					//writeText.Write("alarm=12:11:2019:19:52:00:repeat", target);
					data = loadAllText(locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt"); 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}catch (NullPointerException ex) {
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		refresh(data);
			
			 
		 }

	public static void refresh(String data) {
		String[][] dF = defaultloadsettingFormat(data,"=");
		for(int i = 0; i<dF.length; i++) {
			 
			if(dF[i][0].equals("[Core]=")) {
				 targets = "Core";
			 }else if(dF[i][0].equals("[Alarm]=")) {
				 targets = "Alarm";
			 }
			 
			 if(targets!=null) {
				 if(targets.equals("Core")) {
					 if(dF[i][0].equals("version")) {
						 AI6DATAUpdater.updateVersion(dF[i][1], ReadText.class); 
					 }else if(dF[i][0].equals("ccversion")) {
						 mainRunnerCoreManager.updateCCVersion(dF[i][1]);
					 } 
				 }
				 if(targets.equals("LocalMemory")) {
					 if(dF[i][0].equals("todayLastGreet")) {
						 SystemInteractionData.systemLastGreet = dF[i][1];
					 }else if(dF[i][0].equals("ccversion")) {
						 mainRunnerCoreManager.updateCCVersion(dF[i][1]);
					 } 
				 }
				 
				 if(targets.equals("Alarm")) {
					 if(dF[i][0].contains("alarm")) {
					 //int numAlarm = Integer.valueOf(dF[i][1].substring(5));
					 alarmClock.setAlarm(dF[i][1]);
				 }	 
			 }
		 }
	}
		 
			 //File checkFile = new File(locate.getParentFile()+"\\AI6Data.txt");
			 //File checkFile2 = new File(locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt");
			 //if(!checkFile.exists()||checkFile2.exists()) {
				 //new restartApplication();
			 //}
	}
}
