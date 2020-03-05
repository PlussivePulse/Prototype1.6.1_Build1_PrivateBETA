package ccc.interaction.global.components;

import java.io.File;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.popUpBox;
import ccc.interaction.global.addons.chromeVersionGetter;
import ccc.mainComponent.experimental.DEBUG;
import dataInteractingAbility.filesCore;
import servicePackage.serviceLoader;

public class seleniumDriverHelper {

	public static WebDriver publicDriver;
	public static String stateSave = "";
	
	public static void loadPD(String driver) {
		DEBUG.print("seleniumDriverHelper : Setting Driver.");
		setDriver(driver);
		//if(driver.equals("chrome") && !stateSave.equals(driver)) {
			//publicDriver = new ChromeDriver();
			//stateSave = driver;
		//}
	}
	
	public static WebDriver getDriver() {
		return publicDriver;
		
	}
	
	@SuppressWarnings("static-access")
	public static void setDriver(String program) {
		
		File folderCreation = null;
		try {folderCreation = new File(filesCore.getDir());}
		catch (URISyntaxException e) {e.printStackTrace();}
		
		File target = new File(folderCreation.getParent()+"\\" + internalFileBridge.mainFolderName + "\\" + "seleniumDriver");
		
		if(program.contentEquals("chrome")) {
			if(serviceLoader.getGIS().getOsVersion().toLowerCase().contains("window")) {
				if(chromeVersionGetter.getSoftVersion().split("=")[1].equals("79")) {
					System.setProperty("webdriver.chrome.driver", target.getAbsolutePath() + "/winchromedriver79.exe");
				}else if(chromeVersionGetter.getSoftVersion().split("=")[1].equals("80")) {
					System.setProperty("webdriver.chrome.driver", target.getAbsolutePath() + "/winchromedriver80.exe");
				}else if(chromeVersionGetter.getSoftVersion().split("=")[1].equals("81")) {
				System.setProperty("webdriver.chrome.driver", target.getAbsolutePath() + "/winchromedriver81.exe");
			}
			}else if(serviceLoader.getGIS().getOsVersion().toLowerCase().contains("mac")) {
				//popUpBox.warningBox("This is beta and experimental support for Mac.\nMany bugs may exist in this version.", "Experimental : Untested Support");
				autoNotificationPusher.notPush("WARNING", "Experimental : Untested Support", "This is beta and experimental support for Mac.\nMany bugs may exist in this version.", "AUTO");
				if(chromeVersionGetter.getSoftVersion().split("=")[1].equals("79")) {
					System.setProperty("webdriver.chrome.driver", target.getAbsolutePath() + "/macchromedriver79");
				}else if(chromeVersionGetter.getSoftVersion().split("=")[1].equals("80")) {
					System.setProperty("webdriver.chrome.driver", target.getAbsolutePath() + "/macchromedriver80");
				}else if(chromeVersionGetter.getSoftVersion().split("=")[1].equals("81")) {
					System.setProperty("webdriver.chrome.driver", target.getAbsolutePath() + "/macchromedriver81");
				}
			}
		}else if(program.contentEquals("ie")) {
			
		}
	}
}
