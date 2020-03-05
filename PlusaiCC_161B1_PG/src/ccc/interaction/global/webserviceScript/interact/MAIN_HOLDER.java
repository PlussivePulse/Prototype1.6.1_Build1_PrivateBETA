package ccc.interaction.global.webserviceScript.interact;

import java.util.concurrent.TimeUnit;

import ccc.interaction.global.components.seleniumDriverHelper;
import ccc.mainComponent.UI.seleniumPanel;

public class MAIN_HOLDER {
	public static void defaultsetDriver() {
		if(seleniumDriverHelper.publicDriver == null) {
			seleniumDriverHelper.loadPD("chrome");
		
		//seleniumDriverHelper.setDriver("chrome");
		//seleniumDriverHelper.publicDriver = new ChromeDriver();
		seleniumDriverHelper.publicDriver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);
		seleniumDriverHelper.publicDriver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		//seleniumDriverHelper.publicDriver.manage().window().maximize();
		}
		
		if(seleniumPanel.initialized == false) {
			seleniumPanel panel = new seleniumPanel();
		}else {
			if(!seleniumPanel.frame.isVisible()) {
				seleniumPanel.frame.setVisible(true);
			}
		}
	}
}
