package ccc.interaction.global.webserviceScript.interact;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LOGIN_Gmail {
	public static WebDriver loadedDriver;
	
	public static void login(WebDriver driver, String email, String password) {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(email);
		driver.findElement(By.xpath("//div[@id='identifierNext']")).click();
		WebElement passwordButton = driver.findElement(By.xpath("//input[@name='password']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(passwordButton));
		passwordButton.sendKeys(password);
		driver.findElement(By.xpath("//div[@id='passwordNext']")).click();
		loadedDriver = driver;
	}
}
