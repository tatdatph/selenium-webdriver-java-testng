package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_38_Wait_P2 {
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

		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_02_Implicit() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	// @Test
	public void TC_03_Sleep() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		sleep(10);
		driver.findElement(By.id("finish")).isDisplayed();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	// @Test
	public void TC_04_Explicit_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");

	}

	// @Test
	public void TC_05_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");

	}

	// @Test
	public void TC_06_() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 15);

		// Cho hien thi bang
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

		// Cho hien thi ngay 22
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='22']/parent::td"))).click();

		// Cho loading bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
				"div#ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1 div.raDiv")));

		// Cho hien thi thong tin
		explicitWait.until(ExpectedConditions.textToBe(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"),
				"Thursday, June 22, 2023"));
	}

	@Test
	public void TC_07_UploadFile() {
		explicitWait = new WebDriverWait(driver, 30);
		String hanoi = "hanoi.jpg";
		String sydney = "sydney.jpg";
		String seattle = "seattle.jpg";

		String hanoiPath = projectPath + File.separator + "uploadFiles" + File.separator + hanoi;
		String sydneyPath = projectPath + File.separator + "uploadFiles" + File.separator + sydney;
		String seattlePath = projectPath + File.separator + "uploadFiles" + File.separator + seattle;

		driver.get("https://gofile.io/?t=uploadFiles");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-secondary"))).click();

		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));

		By uploadFile = By.xpath("//input[@type='file']");
		driver.findElement(uploadFile).sendKeys(hanoiPath + "\n" + seattlePath + "\n" + sydneyPath);

		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("div.mainUploadInitInfo div.spinner-border")));

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.progress-bar")));

		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.mainUploadSuccess div.border-success"),
				"Your files have been successfully uploaded"));
		
		driver.findElement(By.cssSelector("div.mainUploadSuccessLink a")).click();
		
		// Verify
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='hanoi.jpg']/parent::a/parent::div/following-sibling::div[3]//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='hanoi.jpg']/parent::a/parent::div/following-sibling::div[3]//span[text()='Play']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='seattle.jpg']/parent::a/parent::div/following-sibling::div[3]//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='seattle.jpg']/parent::a/parent::div/following-sibling::div[3]//span[text()='Play']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='sydney.jpg']/parent::a/parent::div/following-sibling::div[3]//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='sydney.jpg']/parent::a/parent::div/following-sibling::div[3]//span[text()='Play']")).isDisplayed());
		
		
	}

	// @Test
	public void TC_08_FluentWait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
	}
	
	// @Test
	public void TC_09_FluentWait_2() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start button"))).click();
		
		
	}	
	
	// @Test
	public void TC_09_Visible() {
		
	}	     
	
	// @Test
	public void TC_010_Visible() {
		
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