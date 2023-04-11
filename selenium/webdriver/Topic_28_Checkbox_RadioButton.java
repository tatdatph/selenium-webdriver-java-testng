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

public class Topic_28_Checkbox_RadioButton {
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
	public void TC_01_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleep(8);

		By DualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");

		// Check da duoc chon chua sau do chon
		if (!driver.findElement(DualZoneCheckbox).isSelected()) {
			driver.findElement(DualZoneCheckbox).click();
			sleep(2);
		}
		// Check lai
		Assert.assertTrue(driver.findElement(DualZoneCheckbox).isSelected());

		// Check da chon chua thi bo chon
		if (driver.findElement(DualZoneCheckbox).isSelected()) {
			driver.findElement(DualZoneCheckbox).click();
			sleep(2);
		}

		// Check lai xem da bo chon chua
		Assert.assertTrue(driver.findElement(DualZoneCheckbox).isSelected());
	}

	// @Test
	public void TC_02_RadioButton() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		By Patrol147kW = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");

		driver.findElement(Patrol147kW).click();

		// Kiem tra
		Assert.assertTrue(driver.findElement(Patrol147kW).isSelected());

	}

	// @Test
	public void TC_03_SelectAllCheckbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// dung 1 list de chua tat ca checkbox
		List<WebElement> listCheckbox = driver
				.findElements(By.xpath("//div[@class='form-single-column']//input[@class='form-checkbox']"));

		// Click het
		for (WebElement duyetlanluot : listCheckbox) {
			if (!duyetlanluot.isSelected() && duyetlanluot.getAttribute("value").equals("Tuberculosis")) {
				duyetlanluot.click();
			}
		}

		// Verify chi co Tuberculosis duoc chon
		for (WebElement duyetlanluot : listCheckbox) {
			if (duyetlanluot.getAttribute("value").equals("Tuberculosis")) {
				Assert.assertTrue(duyetlanluot.isSelected());
			}
		}
	}

	// @Test
	public void TC_04() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

	}

	@Test
	public void TC_05_Custom() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).getAttribute("aria-checked"),
				"false");

		driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).click();
		sleep(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).getAttribute("aria-checked"),
				"true");

		// Chon het cac tinh
		List<WebElement> listCheck = driver.findElements(By.xpath("//div[@role='checkbox']"));

		for (WebElement duyetCacCheckBox : listCheck) {
			if (duyetCacCheckBox.getAttribute("aria-checked").equals("false")) {
				duyetCacCheckBox.click();
				sleep(1);
			}
		}

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