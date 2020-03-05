package ccc.interaction.global.webserviceScript.interact;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FUNCTION_getUnreadGmail {
	public static void get(WebDriver driver, String email, String password) {
		LOGIN_Gmail.login(driver, email, password);
		List<WebElement> unreadEmail = LOGIN_Gmail.loadedDriver.findElements(By.className("zE"));
	}
}
