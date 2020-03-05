import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class Login {
 
 public static void main(String[] args) throws InterruptedException {
 
 System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
 WebDriver driver = new ChromeDriver();
 driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 driver.manage().window().maximize();
 driver.get("https://my.cwu.edu/psc/IHPRD/EMPLOYEE/EMPL/s/WEBLIB_PTBR.ISCRIPT1.FieldFormula.IScript_StartPage");
 //Send email address
 driver.findElement(By.xpath("//input[@id='username']")).sendKeys("KajonsuksaP");
 driver.findElement(By.xpath("//input[@id='password']")).sendKeys("194215217HNT_s");
 driver.findElement(By.name("_eventId_proceed")).click();
 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//driver.close();

 }


}

