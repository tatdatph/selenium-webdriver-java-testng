package webdriver;

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

public class Topic_32_PopUp {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		driver.get("https://ngoaingu24h.vn/");
		driver.findElement(By.cssSelector("button.login_")).click();

		Assert.assertTrue(
				driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.modal-dialog")).isDisplayed());

		// Nhap thong tin
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input"))
				.sendKeys("automationfc");
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input"))
				.sendKeys("automationfc");

		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();

		// Login fail
		Assert.assertEquals(
				driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.error-login-panel")).getText(),
				"Tài khoản không tồn tại!");

		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
		sleep(1);
		Assert.assertFalse(
				driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] div.modal-dialog")).isDisplayed());
	}

	@Test
	public void TC_02_Kyna_InDOM() {
		driver.get("https://skills.kynaenglish.vn/");

		driver.findElement(By.cssSelector("a.login-btn")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());

		// Dien thong tin
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation,@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleep(1);
		// Check
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");

		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();

		Assert.assertFalse(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());

	}

	@Test
	public void TC_03_Tiki_NotInDOM() {
		driver.get("https://tiki.vn/");
		
		driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống");
		
		driver.findElement(By.cssSelector("img.close-img")).click();
		
		List<WebElement> timKiem = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		Assert.assertEquals(timKiem.size(), 0);
		
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