package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	String emailAddress = "automation" + rand.nextInt(99999) + "@gmail.com";
	String firstname1 = "Pham";
	String lastname1 = "Tat Dat";
	String fullname = firstname1 + " " + lastname1;
	String password = "Admin@2014";
	String username = "phamtatdat" + rand.nextInt(99999);
	String EmployeeID;
	String passportID, issuedDate, expiryDate, comment;

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
		
		passportID ="135066835";
		issuedDate ="2018-04-17";
		expiryDate ="2028-04-16";
		comment ="This is test script";
		
	}

	//@Test
	/*public void TC_01_Techpanda_register() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstname);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();	

		driver.findElement(By.xpath("//button[@title='Register']")).click();	
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()=\"Thank you for registering with Main Website Store.\"]")).isDisplayed());
		
		String InfoString = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(InfoString.contains(fullname));
		Assert.assertTrue(InfoString.contains(emailAddress));
		
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
		driver.findElement(By.xpath("//div[@class='page-title']//img")).isDisplayed();
		
		
		sleep(6);
		
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "http://live.techpanda.org/index.php/");

						
		
	}
	*/

	@Test 
	public void TC_02_Orange_HRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		sleep(2);
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleep(3);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleep(3);
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstname1);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastname1);
				
		
		EmployeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
		
		driver.findElement(By.xpath("//div[@class='oxd-switch-wrapper']")).click();
		
		sleep(1);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);		
		
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		
		sleep(10);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstname1);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastname1);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), EmployeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();		
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button")).click();
		sleep(3);
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportID);
		driver.findElement(By.xpath("//label[text()='Issued Date']/parent::div/following-sibling::div//input")).sendKeys(issuedDate);
		driver.findElement(By.xpath("//label[text()='Expiry Date']/parent::div/following-sibling::div//input")).sendKeys(expiryDate);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleep(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='135066835']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='2018-04-17']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='2028-04-16']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//div[text()='This is test script']")).isDisplayed());
		sleep(2);
		
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleep(2);
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleep(1);
		
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleep(5);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstname1);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastname1);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), EmployeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();		
				driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button")).click();
		sleep(3);		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='135066835']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='2018-04-17']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='2028-04-16']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//div[text()='This is test script']")).isDisplayed());
		
		
		
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