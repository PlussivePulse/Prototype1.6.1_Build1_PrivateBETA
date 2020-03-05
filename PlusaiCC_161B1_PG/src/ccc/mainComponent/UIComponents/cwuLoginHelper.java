package ccc.mainComponent.UIComponents;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ccc.interaction.global.addons.chromeVersionGetter;
import ccc.interaction.global.components.seleniumDriverHelper;
import servicePackage.serviceLoader;

public class cwuLoginHelper{

	public static void login(String user, String pass) {
		seleniumDriverHelper.setDriver("chrome");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://my.cwu.edu/psc/IHPRD/EMPLOYEE/EMPL/s/WEBLIB_PTBR.ISCRIPT1.FieldFormula.IScript_StartPage");
	
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(user);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pass);
		driver.findElement(By.name("_eventId_proceed")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			driver.wait(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.close();
		
	}
}
