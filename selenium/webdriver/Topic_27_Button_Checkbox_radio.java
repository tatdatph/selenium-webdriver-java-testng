package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Button_Checkbox_radio {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String email;
	String password;

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

		email = "phamtatdat@gmail.com";
		password = "abc246810";
	}
	// Button >>> Click chuyển hướng được hoặc show lỗi được
	// Có thể khi không nhập gì, button bị disable

	@Test
	public void TC_01_() {
		driver.get("https://www.fahasa.com/customer/account/create");

		// Click chuyen tab dang nhap
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		// Click dang nhap
		By loginButton = By.cssSelector("button.fhs-btn-login");
		sleep(2);
		// Verify login button
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		// Verify mau, có chứa mã màu cần
		Assert.assertTrue(driver.findElement(loginButton).getCssValue("background").contains("rgb(224, 224, 224)"));

		// Nhap email pass
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys(email);
		driver.findElement(By.cssSelector("input#login_password")).sendKeys(password);
		sleep(1);

		// Check button
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		Assert.assertTrue(driver.findElement(loginButton).getCssValue("background").contains("rgb(201, 33, 39)"));

	}

	@Test
	public void TC_02_() {

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