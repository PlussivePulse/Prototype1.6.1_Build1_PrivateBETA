package ccc.interaction.global.webserviceScript.interact;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ccc.interaction.global.popUpBox;

public class LOGIN_Instargram {
	public static void login(WebDriver driver, String username, String password) {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.instagram.com/accounts/login/?source=auth_switcher");
		WebElement loginUsr = driver.findElement(By.name("username"));
		loginUsr.sendKeys(username);
		WebElement loginPws = driver.findElement(By.name("password"));
		loginPws.sendKeys(password);
		WebElement loginButton = driver.findElement(By.xpath("//div[text()='Log In']"));
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
}
