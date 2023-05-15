package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_37_UploadFile {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String hanoi = "hanoi.jpg";
	String seattle = "seattle.jpg";
	String sydney = "sydney.jpg";

	String hanoiPath = projectPath + File.separator + "uploadFiles" + File.separator + hanoi;
	String seattlePath = projectPath + File.separator + "uploadFiles" + File.separator + seattle;
	String sydneyPath = projectPath + File.separator + "uploadFiles" + File.separator + sydney;

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
	public void TC_01_SingleFile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadFile = By.xpath("//input[@type='file']");

		driver.findElement(uploadFile).sendKeys(hanoiPath);
		sleep(1);
		driver.findElement(uploadFile).sendKeys(seattlePath);
		sleep(1);
		driver.findElement(uploadFile).sendKeys(sydneyPath);
		sleep(1);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + hanoi + "']")).isDisplayed());

		List<WebElement> danhSach = driver.findElements(By.cssSelector("table button.btn-primary"));

		for (WebElement city : danhSach) {
			city.click();
			sleep(2);

		}

	}

	@Test
	public void TC_02_MultipleFile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFile = By.xpath("//input[@type='file']");

		driver.findElement(uploadFile).sendKeys(hanoiPath + "\n" + seattlePath + "\n" + sydneyPath);
		sleep(2);
		
		List<WebElement> danhSach = driver.findElements(By.cssSelector("table button.btn-primary"));

		for (WebElement city : danhSach) {
			city.click();
			sleep(1);

		}
	}

	@Test
	public void TC_06_() {
		sleep(1);
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
		// driver.quit();
	}
}