

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ccc.interaction.global.NetworkSurface;
import ccc.interaction.global.allBoxLoader;
import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.globalInteractionService;
import ccc.interaction.global.listSelectorEx;
import ccc.interaction.global.notificationBox;
import ccc.interaction.global.notificationFrame;
import ccc.interaction.global.popUpBox;
import ccc.interaction.global.addons.MoonPhase;
import ccc.interaction.global.addons.TrayCore;
import ccc.interaction.global.addons.UniversityDatabase;
import ccc.interaction.global.addons.chromeVersionGetter;
import ccc.interaction.global.addons.external_freeTTS;
import ccc.interaction.global.addons.programsMaskFilter;
import ccc.interaction.global.components.internalFileBridge;
import ccc.interaction.global.components.seleniumDriverHelper;
import ccc.interaction.global.database.DateAndTime;
import ccc.interaction.global.webserviceScript.interact.FUNCTION_getAllPicture;
import ccc.interaction.global.webserviceScript.interact.FUNCTION_getUnreadGmail;
import ccc.interaction.global.webserviceScript.interact.LOGIN_Gmail;
import ccc.interaction.global.webserviceScript.interact.LOGIN_Instargram;
import ccc.interaction.global.webserviceScript.interact.searchHSRoster;
import ccc.interaction.internalFeatures.alarmClockBeep;
import ccc.interaction.internalFeatures.processControl;
import ccc.interaction.internalFeatures.soundBoard;
import ccc.interaction.internalFeatures.warningBeep;
import ccc.mainComponent.Systems;
import ccc.mainComponent.globalID;
import ccc.mainComponent.experimental.DEBUG;
import dataInteractingAbility.createFile;
import dataInteractingAbility.filesCore;
import dataInteractingAbility.getAbsolutePath_Substitude;
import dataInteractingAbility.getsub_PathSoft;
import dataInteractingAbility.writeText;
import dataInteractingAbility.processesNet.ProcessesNet;
import internalSecurity.directoryPathRequest;
import servicePackage.loadAI6Data;
import servicePackage.stabilizer;

public class test {
	private static File savetarget;

	public static void main(String args[]) throws AWTException {
		//ProcessesNet process = new ProcessesNet();
		//try {
		//	process.getProcess();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		//String test = processControl.boxLoad();
		//System.out.println(test);
		//programsMaskFilter.start();
		/*
		ArrayList path = getAbsolutePath_Substitude.loadAbsolute("C:\\");
		directoryPathRequest.OSDriveBypass = true;
		for(int i = 0; i<path.size(); i++) {
				System.out.println(path.get(i));
		}
		
		//allBoxLoader.boxLoad(path);
		//globalInteractionService.pushSelector(path);
		stabilizer.startlDSS();
	}
		

	//	notificationBox n = new notificationBox(test.class, false);
		//n.loadTray();
		//globalInteractionService glo = new globalInteractionService();
	
	processControl.killProcessMenu();
	//	TrayCore tray = new TrayCore();
	//System.out.println("end");
	//notificationFrame frame = new notificationFrame();
	//frame.generate("Test\nRest this is longe test\n	Rest");
	//directoryPathRequest d = new directoryPathRequest();
	//directoryPathRequest.OSDriveBypass = true;
	//d.checkDirectoryRequest("C:\\Test");
		
	System.out.println(System.getenv("SystemDrive"));

	//System.out.println(glo.TrayBoxAC);
	DateAndTime dat = new DateAndTime();
	dat.setPattern("");
	//System.out.println(dat.getDAT());
	//globalID.addID(1.55555);
	
	stabilizer.setStabilizer(false);
	//soundBoard s = new soundBoard();
	external_freeTTS t = new external_freeTTS();
	//t.readText("Stabilizing environment.");
	//t.start();
	//t.readText("testing");
	autoNotificationPusher.notPush("INFO", "TEST INFO", "System successfully executed", Class.class.getClass(), "AUTO", "", true, true);
	
	//System.out.println(NetworkSurface.isConnected());
	 
	 */
		//DEBUG.print(chromeVersionGetter.getVersion());
		//Systems s = new Systems();
		//s.submitString("They are friendly");
		//s.submitString("give me test");
		
		//loadAI6Data.load();
		/*
		globalID.addID(1);
		//DEBUG.print(globalID.getId()+"");
		globalID.addID(0.9);
		DEBUG.print(globalID.getId()+"");
		//globalID.addID(0.1);
		globalID.addID(0.1);
		DEBUG.print(globalID.getId()+"");
		//globalID.addID(0.1);
		globalID.addID(1.0);
		
		DEBUG.print(globalID.getId()+"");
		globalID.addID(0.1);
		DEBUG.print(globalID.getId()+"");
		globalID.addID(0.1);
		DEBUG.print(globalID.getId()+"");
		globalID.addID(0.1);
		DEBUG.print(globalID.getId()+"");
		globalID.addID(1);
		DEBUG.print(globalID.getId()+"");
		
		DEBUG.print(NetworkSurface.getip());
		*/
		/*try {
			DEBUG.print(new File(filesCore.getDir()).getParentFile() +"\\AI6CoreFolder");
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList path = null;
		try {
			path = getAbsolutePath_Substitude.loadAbsolute(new File(filesCore.getDir()).getParentFile()+"\\AI6CoreFolder");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		directoryPathRequest.OSDriveBypass = true;
		for(int i = 0; i<path.size(); i++) {
				System.out.println(path.get(i));
		}*/
		
		//s.setAllSystemLoaded(true);
		//s.submitString("open uw roster track");
		//s.submitString("/search roster track");
		//s.submitString("/dbLoad wwu book black elk offset=1 reviewed=true");
		//s.submitString("");
		//listSelectorEx test = new listSelectorEx();
		//TrackingPackage_postOffice.login("001");
		//ArrayList<String> testBox1 = new ArrayList<String>();
		//ArrayList<String> testBox2 = new ArrayList<String>();
		//testBox1.add("TESTBOXELEMENT");
		//testBox2.add("TESTBOXRETUENELEMENT");
		//DEBUG.print("" + test.boxLoad("Test", "BoxListNameTest", testBox1, testBox2));
		/*File locate = null;
			try {
				locate = new File(filesCore.getDir());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		
			/*try {
			writeText.addText("alarm=test", locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt" , "[Alarm]=");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeText.update(locate.getParentFile()+"\\AI6CoreFolder\\AI6DataTemp.txt", locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt");
		try {
			writeText.addText("alarm=test", locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt" , "[Alarm]=");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeText.update(locate.getParentFile()+"\\AI6CoreFolder\\AI6DataTemp.txt", locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt");
	*/
			/*try {
				writeText.removeText(locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt","alarm=1:1:2019:0:0:0:noRepeat");
			} catch (IOException e) {
				e.printStackTrace();
			}
			writeText.update(locate.getParentFile()+"\\AI6CoreFolder\\AI6DataTemp.txt", locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt");
			*/	
			//soundBoard.tone(1000, 100, 1.0);
			//new alarmClockBeep();
		//warningBeep w = new warningBeep(2);
		//popUpBox.warningBox("", "Test Warning", -1);
		//autoNotificationPusher.notPush("WARNING1", "TEST", "TEST", Class.class, "AUTO");
		//String MyPhoto = System.getProperty("user.home");
		internalFileBridge com = new internalFileBridge();
		try {
		com.seleniumDriverCOM();
		} catch(Exception e) {
			
		}
		seleniumDriverHelper.loadPD("chrome");

		//WebDriver driver = seleniumDriverHelper.getDriver();
		//WebDriver driver = new ChromeDriver();

		//LOGIN_Instargram.login(driver, "pakinh_kajon", "25900HNT");
		//LOGIN_Gmail.login(driver, "donieharwnartcc@gmail.com", "25900HNT");
		//FUNCTION_getUnreadGmail.get(driver, "donieharwnartcc@gmail.com", "25900HNT");
		//FUNCTION_getAllPicture func = new FUNCTION_getAllPicture();
		//func.loadImageDefault("https://www.google.com");
		//FUNCTION_getAllPicture.getURL(driver,"https://www.google.com");
		//FUNCTION_getAllPicture.getURL("https://www.google.com", driver);
		//File folderCreation = null;
		//try {folderCreation = new File(filesCore.getDir());}
		//catch (URISyntaxException e) {e.printStackTrace();}
		//savetarget = new File(folderCreation.getParent()+"\\" + internalFileBridge.mainFolderName  + "\\Downloads\\ + folderName");
		//if(savetarget.exists()) {
		//	popUpBox.alertError("File already existed.", "File Create Failed :");
		//}else {
		//	DEBUG.print("DEBUG : Creating file download directory command triggered.");
		//	createFile.createFolder(savetarget.getParent(), "test");
		//}
		//autoNotificationPusher.notPush("WARNING", "Experimental : Untested Support", "This is beta and experimental support for Mac.\nMany bugs may exist in this version.", "AUTO");
			// System.out.println(new
			// SimpleDateFormat("EEEE, dd-MMMM-yyyy HH:mm zzzz").format(new Date()));
			//
			Calendar c = Calendar.getInstance();
			 MoonPhase mp = new MoonPhase(c);
			 System.out.printf("Current phase: %f%n", mp.getPhase());
			 System.out.println("Moon Age: " + mp.getMoonAgeAsDays());
			//
			// Calendar c = Calendar.getInstance();
			// c.setTimeInMillis(System.currentTimeMillis());
			// c.add(Calendar.DAY_OF_WEEK, -22);
			//
			// for (int i=0; i< 33; i++){
			// c.add(Calendar.DAY_OF_WEEK, 1);
			// mp.updateCal(c);
			// System.out.format("%1$td-%1$tB,%1$tY  %1$tH:%1$tM:%1$tS  ",c);
			// System.out.printf("%f%n", mp.getPhase());
		
		//searchHSRoster.search(driver, "Stadium High School");
	}
	
			


}