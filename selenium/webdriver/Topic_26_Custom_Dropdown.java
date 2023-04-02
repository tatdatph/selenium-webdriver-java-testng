package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Custom_Dropdown {
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
	public void TC_01_Jquery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "2");
	}

	@Test
	public void TC_02_() {

	}

	public void selectItemDropdown(String xpathParent, String xpathChild, String expectedText) {

		// Click theo manual
		driver.findElement(By.xpath(xpathParent)).click();
		sleep(1);

		// Cho cac item load ra het, trong 30s
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));
		sleep(1);
		// Tim het cac element
		driver.findElements(By.xpath(xpathChild));

		// Cho vao list
		List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));
		sleep(1);

		// Duyet qua tung item
		for (int i = 0; i < allItems.size(); i++) {
			// Get text cua tung item
			String itemText = allItems.get(i).getText();
			// Click chon
			if (itemText.equals(expectedText)) {
				// Scroll toi element
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItems.get(i));
				allItems.get(i).click();
				// Thoat
				break;
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
		driver.quit();
	}
}