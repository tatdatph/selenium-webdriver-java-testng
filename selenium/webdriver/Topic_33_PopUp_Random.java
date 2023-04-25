package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_33_PopUp_Random {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
	}

	//@Test
	public void TC_02_InDOM_2() {
		driver.get("https://vnk.edu.vn/");
		sleep(30);

		By popup = By.cssSelector("div#tve_editor");
		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			Assert.assertFalse(driver.findElement(popup).isDisplayed());
			sleep(1);
		}

		driver.findElement(By.cssSelector("button.btn-danger")).click();
		sleep(2);

		Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/lich-khai-giang/");

	}
	@Test
	public void TC_03_NotInDom() {
		driver.get("https://dehieu.vn/");
		
		By popup = By.cssSelector("div.popup-content");
		sleep(5);
		
		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleep(2);
			Assert.assertEquals(driver.findElements(popup).size(), 0);
				}
			driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
			Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");
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