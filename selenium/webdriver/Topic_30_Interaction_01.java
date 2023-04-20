package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_Interaction_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action; 

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleep(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

	//@Test 
	public void TC_02_HoverToElement() {
		driver.get("http://www.myntra.com/");
		//Web da chan auto
		action.moveToElement(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']"))).perform();
		sleep(5);
		action.click(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Home & Bath']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());

	}
	
	//@Test
	public void TC_04_ClickHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		action.clickAndHold(driver.findElement(By.xpath("//li[text()='1']"))).perform();
		
		action.moveToElement(driver.findElement(By.xpath("//li[text()='4']"))).perform();
		action.release().perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='1' and contains(@class,'ui-selected')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='2' and contains(@class,'ui-selected')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='3' and contains(@class,'ui-selected')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[text()='4' and contains(@class,'ui-selected')]")).isDisplayed());
		
		
		List<WebElement> tatCaSo = driver.findElements(By.cssSelector("ol#selectable li"));
		action.clickAndHold(tatCaSo.get(0)).moveToElement(tatCaSo.get(3)).release().perform();
		sleep(1);
		
		List<WebElement> soDuocChon = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(soDuocChon.size(), 4);
		
	}
	
	//@Test
	public void TC_05_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Scroll to Element
		
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
		
	}	
	
	//@Test
	public void TC_06_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		driver.findElement(By.cssSelector("li.context-menu-icon-quit"));
		
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).getAttribute("class").contains("context-menu-hover"));
		
		driver.findElement(By.cssSelector("li.context-menu-icon-quit")).click();
		
		//Accept alert
		driver.switchTo().alert().accept();
		
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).getAttribute("class").contains("context-menu-hover"));
		
	}		
	
	@Test
	public void TC_07_DragDrop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		action.dragAndDrop(driver.findElement(By.cssSelector("div#draggable")), driver.findElement(By.cssSelector("div#droptarget"))).perform();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
		
		String mauSac = driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color");
		
		System.out.println(mauSac);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color"), "rgb(3, 169, 244)");


		
		
		
		
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
		//driver.quit();
	}
}