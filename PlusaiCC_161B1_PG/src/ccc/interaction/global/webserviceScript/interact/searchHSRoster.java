package ccc.interaction.global.webserviceScript.interact;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class searchHSRoster {
	public static void search(WebDriver driver, String school) {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.athletic.net/");
		WebElement searchButton = driver.findElement(By.className("ng-tns-c128-0"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		driver.findElement(By.className("ng-tns-c128-0")).click();
		WebElement searchBar = driver.findElement(By.xpath("//input[@type='text']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(searchBar));
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(school);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.ENTER);;
	}
}
