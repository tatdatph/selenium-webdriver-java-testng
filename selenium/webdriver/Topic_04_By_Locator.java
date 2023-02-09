package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_By_Locator {
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
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	// http://live.techpanda.org/index.php/customer/account/login/
	// Email address Textbox
	// HTML element
	// <input type="email" autocapitalize="off" autocorrect="off" spellcheck="false"
	// name="login[username]" value="" id="email" class="input-text 
	// required-entry validate-email" title="Email Address">
	
	// HTML
	// 1 - Tên thẻ - tagname: input
	// 2 - Tên thuộc tính - attribute name:  type/autocapitalize/autocorrect/spellcheck
	// name/value/class
	// 3 - Giá trị thuộc tính - attribute value: email/off/off/false/login[username]
	
	
	// Có 3 thuộc tính trùng với locator của Selenium: ID, Class,Name
	
	
	//Xpath
	//Format: //tagname[@attribute_name='atribbute_value']
	// Ex: //input[@id='email']
	
	//Css Format: tagname[attribute_name='atribbute_value']
	// Ex: input[id='email']
	
	
	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("email")).sendKeys("user@autotest.com");
	}

	@Test 
	public void TC_02_Class() {
		driver.findElement(By.className("button"));
	}

	@Test 
	public void TC_03_Name() {
		driver.findElement(By.name("login[username]"));
	}
	
	@Test 
	public void TC_04_Tagname() {
		// verify xem 1 page có bao nhiêu element giống nhau: link/button/
		driver.findElements(By.tagName("a"));
	}
	
	
	@Test 
	public void TC_05_LinkText() {
		//Chỉ dùng được với link (tuyệt đối)
		driver.findElement(By.linkText("SEARCH TERMS"));
		driver.findElement(By.linkText("ADVANCED SEARCH"));
	}
	
	@Test 
	public void TC_06_Partial_LinkText() {
		//Link tương đôi, chứa 1 phần
		driver.findElement(By.partialLinkText("SEARCH"));
		driver.findElement(By.partialLinkText("ADVANCED"));

	}
	
	@Test 
	public void TC_07_Css() {
		//Css với ID
		driver.findElement(By.cssSelector("input[id='email']"));
		//Css với Class
		driver.findElement(By.cssSelector("div[class='col-1 new-users']"));
		//Css với Name
		driver.findElement(By.cssSelector("input[name='login[password]']"));
		//Css với tagname
		driver.findElement(By.cssSelector("input"));
		//Css với Link
		driver.findElement(By.cssSelector("a[title='Search Terms']"));
		//Css với partial Link
		driver.findElement(By.cssSelector("a[title*='Terms']"));		
		
	}
	
	@Test 
	public void TC_08_XPath() {
		//XPath với ID
		driver.findElement(By.xpath("//input[@id='email']"));
		//XPath với Class
		driver.findElement(By.xpath("//div[@class='col-1 new-users']"));
		//XPath với Name
		driver.findElement(By.xpath("//input[@name='login[password]']"));
		//XPath với tagname
		driver.findElement(By.xpath("//a"));
		//XPath với Link
		driver.findElement(By.xpath("//a[@title='Search Terms']"));
		driver.findElement(By.xpath("//a[text()='Search Terms']"));
		//XPath với partial Link
		driver.findElement(By.xpath("//a[contains(@title,'Advanced')]"));	
		driver.findElement(By.xpath("//a[contains(text(),'Advanced')]"));	

	}

	@AfterClass
	public void afterClass() {
	driver.quit();
	}
}