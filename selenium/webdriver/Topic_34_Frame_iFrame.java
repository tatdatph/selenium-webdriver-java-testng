package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_34_Frame_iFrame {
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
		driver.get("https://skills.kynaenglish.vn/");
		sleep(5);
		
		
		WebElement frameElement = driver.findElement(By.cssSelector("div.face-content iframe"));
		
		//Check
		Assert.assertTrue(frameElement.isDisplayed());
		
		//So luong like
		driver.switchTo().frame(frameElement);
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "164K likes");
		
		// ve trang chu
		driver.switchTo().defaultContent();
		
		//vao chat
		sleep(2);
		
		
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Pham Tat Dat");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		driver.findElement(By.cssSelector("select#serviceSelect")).click();
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		sleep(3);
	
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> danhSachExcel = driver.findElements(By.xpath("//img[contains(@alt,'Excel')]"));
		
		System.out.println(danhSachExcel.size());
	}

	@Test
	public void TC_02_() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("autotest@gmail.net");
		
		driver.findElement(By.cssSelector("a.btn-primary")).click();
		sleep(5);
		
		driver.switchTo().defaultContent();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
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
		driver.quit();
	}
}