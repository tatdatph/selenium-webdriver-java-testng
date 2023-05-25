package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_38_Wait_P1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Displayed() {
		driver.get("https://www.facebook.com/");
		
		// Element co trong giao dienj va trong DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name='login']")));
		
	}
	

	@Test
	public void TC_02_Undisplayed_IN_DOM() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	}

	
	@Test
	public void TC_03_Undisplayed_Not_In_DOM() {
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));

	}

	
	@Test
	public void TC_04_Presence() {
		driver.get("https://www.facebook.com/");
		//Dieu kien bat buoc la phai co trong HTML
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[name=''login]")));
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleep(2);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
			}

	
	@Test
	public void TC_05_Staleness() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		//Thoi diem nay co trong DOM
		WebElement confirmEmaiTextBox = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		//Thoi diem khong co trong DOM. Can check
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmaiTextBox));
		explicitWait.until(ExpectedConditions.invisibilityOf(confirmEmaiTextBox));
		
		
	}

	private void sleep(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}