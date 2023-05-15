package webdriver;

import java.awt.Window;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_35_Windows_Tab {
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

	// @Test
	public void TC_01_GitHub() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String currentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleep(3);
		switchByTitle("GOOGLE");
		Assert.assertEquals(driver.getTitle(), "Google");

		switchByTitle("Selenium WebDriver");
		sleep(3);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleep(3);
		switchByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		switchByTitle("Selenium WebDriver");
		sleep(3);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleep(3);
		switchByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		closeWindowsbyID(currentID);

		Assert.assertEquals(driver.getTitle(), "Selenium WebDriver");
	}

	// @Test
	public void TC_02_KYNA() {
		driver.get("https://kyna.vn/");
		

	}

	@Test
	public void TC_03_TechPanda() {
		driver.get("http://live.techpanda.org/");
		String baseID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleep(2);

		driver.findElement(By.xpath(
				"//a[@title='Xperia']/following-sibling::div/h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath(
				"//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleep(5);

		switchByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

		closeWindowsbyID(baseID);
		sleep(2);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();

		driver.switchTo().alert().accept();
		sleep(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"The comparison list was cleared.");

	}
	
	public void TC_04_Cambridge() {
		driver.get("https://dictionary.cambridge.org/vi/");
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
		sleep(2);
		switchByTitle("Login");
		
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		
	}
	
	

	public void switchByTitle(String expectedTitle) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle))
				break;
		}
	}

	public void closeWindowsbyID(String windowID) {
		Set<String> allID = driver.getWindowHandles();

		for (String id : allID) {
			driver.switchTo().window(id);
			if (!id.equals(windowID))
				driver.close();
		}
		driver.switchTo().window(windowID);
	}

	private void sleep(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}