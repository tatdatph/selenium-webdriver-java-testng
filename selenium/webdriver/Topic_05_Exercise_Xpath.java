package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Exercise_Xpath {
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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		//Truyen empty
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Check popup bao loi
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");
		
	}

	@Test 
	public void TC_02_Invalid_email () {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			
			//Input
			driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Pham Tat Dat");
			driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("123@456@");
			driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@456@");
			driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0973530314");
			
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			//Check
			Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),"Vui lòng nhập email hợp lệ");

	}

	@Test 
	public void TC_03_Invalid_confirm_email () {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			
			//Input
			driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Pham Tat Dat");
			driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("phamtatdat@gmail.com");
			driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("phamtatdat@gmail.net");
			driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0973530314");
			
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			//Check
			Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),"Email nhập lại không đúng");

	}	
	
	
	@Test 
	public void TC_04_Pass_less_than_6 () {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			
			//Input
			driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Pham Tat Dat");
			driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("phamtatdat@gmail.com");
			driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("phamtatdat@gmail.com");
			driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");
			driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345");
			driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0973530314");
			
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			//Check
			Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

	}	
	
	
	@Test 
	public void TC_05_Incorrect_Pass_confirm () {
			driver.get("https://alada.vn/tai-khoan/dang-ky.html");
			
			//Input
			driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Pham Tat Dat");
			driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("phamtatdat@gmail.com");
			driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("phamtatdat@gmail.com");
			driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345678");
			driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345679");
			driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0973530314");
			
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			//Check
			Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Mật khẩu bạn nhập không khớp");
			
	}	
	
	@Test
	public void TC_6_Invalid_Phone_number () {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Phone it hon 10 so
		//Input
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Pham Tat Dat");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("phamtatdat@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("phamtatdat@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("097353031");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Check
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Số điện thoại phải từ 10-11 số.");
		
		//Dau so khong phai cua nha mang
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("3216549870");
		//Check
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

}			
	
		

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}