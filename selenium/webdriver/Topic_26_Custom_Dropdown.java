package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
	public void TC_01_() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//Clcik theo manual
		driver.findElement(By.cssSelector("span#number-button")).click();
		
		//Cho cac item load ra het, trong 30s
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu>li>div")));
		
		//Tim het cac element
		driver.findElements(By.cssSelector("ul#number-menu>li>div"));
		
		//Cho vao list
		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu>li>div"));
		
		//Duyet qua tung item
		for (int i = 0; i < allItems.size(); i++) {
			//Get text cua tung item
			String itemText = allItems.get(i).getText();
			//Click chon
			if (itemText.equals("5")) {
				allItems.get(i).click();
			//Thoat
				break;
								
			}
		}
		
		for (WebElement tempElement : allItems) {
			
		}
		
		
		
	}

	@Test 
	public void TC_02_() {

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}