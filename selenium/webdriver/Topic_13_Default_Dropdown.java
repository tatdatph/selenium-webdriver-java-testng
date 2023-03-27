package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand = new Random();
	Select select;
	String Firstname, Lastname, Email, Company, Password;
	String date, month, year;
	String KiemtraEmail;
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		Firstname = "Pham";
		Lastname = "Tat Dat";
		Email = "autotest" + rand.nextInt(9999) + "@gmail.com";
		Company = "Auto";
		Password = "abc246810";

		date = "22";
		month = "May";
		year = "1990";

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[text()='Register']")).click();

		driver.findElement(By.xpath("//label[text()='Male']")).click();
		
		driver.findElement(By.cssSelector("#FirstName")).sendKeys(Firstname);
		driver.findElement(By.id("Lastname")).sendKeys(Lastname);
		
		select = new Select(driver.findElement(By.cssSelector("select#DateOfBirthDay")));
		select.selectByVisibleText(date);
		
		select = new Select(driver.findElement(By.cssSelector("select#DateOfBirthMonth")));
		select.selectByVisibleText(month);
				
		select = new Select(driver.findElement(By.cssSelector("select#DateOfBirthYear")));
		select.selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(Email);
		driver.findElement(By.id("Company")).sendKeys(Company);
		driver.findElement(By.id("Password")).sendKeys(Password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(Password);
		
		driver.findElement(By.id("register-button")).click();
		
		sleep(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		
		
		//Login
		driver.findElement(By.cssSelector("a.ico-login")).click();
		sleep(2);
		driver.findElement(By.cssSelector("input#Email")).sendKeys(Email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(Password);
		
		sleep(3);
		
		//MyAcc
		driver.findElement(By.cssSelector("a.ico-account")).click();	
		//Check ten
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), Firstname);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), Lastname);
		
		//Check ngay thang da chon
		
		select = new Select(driver.findElement(By.cssSelector("select#DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		select = new Select(driver.findElement(By.cssSelector("select#month")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.cssSelector("select#year")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		KiemtraEmail = driver.findElement(By.cssSelector("input.email")).getAttribute("value");
		Assert.assertEquals(KiemtraEmail, Email);
		
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