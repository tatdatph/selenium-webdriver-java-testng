package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_WebElement_Exercise {
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
	public void Element_TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleep(3);

		if (driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed()) {
			driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");
			System.out.println("Email is displayed");
		} else {
			System.out.println("Email is not displayed");
		}

		if (driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed()) {
			driver.findElement(By.xpath("//input[@id='under_18']")).click();
			System.out.println("Under 18 is displayed");
		} else {
			System.out.println("Under 18 is not displayed");
		}

		if (driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed()) {
			driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");
			System.out.println("Education is displayed");
		} else {
			System.out.println("Education is not displayed");
		}

		if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
			System.out.println("User5 is displayed");
		} else {
			System.out.println("User5 is not displayed");
		}

	}

	// @Test
	public void Element_TC_02_Enable() {

		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (driver.findElement(By.xpath("//input[@id='mail']")).isEnabled()) {
			System.out.println("Email is enable");
		} else {
			System.out.println("Email is disable");
		}

		if (driver.findElement(By.xpath("//select[@id='job1']")).isEnabled()) {
			System.out.println("Job 1 is enable");
		} else {
			System.out.println("Job 1 is disable");
		}

		if (driver.findElement(By.xpath("//label[text()='Development']")).isEnabled()) {
			System.out.println("Development 1 is enable");
		} else {
			System.out.println("Development is disable");
		}

		if (driver.findElement(By.xpath("//input[@id='disable_password']")).isEnabled()) {
			System.out.println("Password 1 is enable");
		} else {
			System.out.println("Password is disable");
		}

		if (driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled()) {
			System.out.println("slider-2 1 is enable");
		} else {
			System.out.println("slider-2 is disable");
		}

	}

	// @Test
	public void Element_TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//input[@id='under_18']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());

		driver.findElement(By.xpath("//input[@id='java']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='java']")).isSelected());

		// Bỏ chọn và check
		driver.findElement(By.xpath("//input[@id='java']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='java']")).isSelected());

	}

	@Test
	public void Element_TC_04_KetHop() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@test.com");

		// Có 1 chữ thường
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("a");
		driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// Có 1 chữ hoa
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("A");
		driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// Có 1 số
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("1");
		driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// Có 1 ký tự đặc biệt
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("@");
		driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// Có 8 ký tự
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

		// Password chuẩn
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("Abc@1234");
		driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();

		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

		// Check box
		driver.findElement(By.xpath("//input[@name='marketing_newsletter']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='marketing_newsletter']")).isSelected());

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