package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Browser_Command {
	WebDriver driver; // Biến khai báo để tương tác vs browser
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
	public void TC_01_Browser() {
	// Các command để tương tác với Browser sẽ thông qua biến "driver" (khai báo ở trên). Bắt đầy bằng driver. + hậu tố WebDriver
*		driver.close(); //Dùng để đóng 1 tab
		
**		driver.quit(); //Dùng để close cả trình duyệt
		
		driver.findElement(null); //Mục đích để tìm 1 element với 1 locator nào đó: id, class, name, xpath, css...
		
		driver.findElements(null); //Tìm nhiều element với locator
**		driver.findElements(By.xpath("//a[@href]")); //Tìm tất cả đường link
		
**		driver.get(""); //Mở ra một trang web
		
		driver.getCurrentUrl(""); //Lấy ra URL của page hiện tại mình đang đứng
			//Kiểm tra cách 1
*			Assert.assertEquals(driver.getCurrentUrl(""), "https://facebook.com"); 

			// Kiểm tra bằng cách 2: khai báo biến. Nếu cần dùng lại mới khai báo biến
			String curURL = driver.getCurrentUrl();
			Assert.assertEquals(curURL, "https://facebook.com");
			
		driver.getPageSource(); //Lấy ra code HTML/CSS/JS của page hiện tại đang đứng
**		Assert.assertTrue(driver.getPageSource().contains("abc xyz")); 
		
		driver.getTitle(); //Lấy ra title của page hiện tại
*		Assert.assertEquals(driver.getTitle(), "Facebook");
		
*		driver.getWindowHandle(); //Lấy ra ID của tab/windows hiện tại
		driver.getWindowHandles();//Lấy ra tất cả ID của Tab/Windows
		
		driver.manage();
			//Xử lý Cookies
			driver.manage().addCookie(null);
			
			//Chờ cho Element xuất hiện trong vòng bao lâu
**			driver.manage().timeouts().implicitlyWait(1, TimeUnit.HOURS);
			
			//Chờ cho page được load trong bao lâu
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
			
			//Chờ cho đoạnm script được thực thi xong
			driver.manage().timeouts().setScriptTimeout(15, TimeUnit.MINUTES);
			
			// Test GUI, điều chỉnh kích thước

**			driver.manage().window().maximize();

		driver.navigate(); //Giả lập hành động người dùng	
			driver.navigate().back();
			driver.navigate().forward();
			driver.navigate().refresh();
			driver.navigate().to("google.com");
			driver.navigate().to(new URL("google.com"));
			
			//Thường dùng
			driver.get(osName);
			
		driver.switchTo();
			//Switch vào Windows/Tab
*			driver.switchTo().window("");
			//Switch vào Frame/Iframe 
*			driver.switchTo().frame(1);
			//Switch vào Alert
*			driver.switchTo().alert();	
	
	
	}
	
	

	@Test 
	public void TC_02_Element() {
	// Các command để tương tác với Browser sẽ thông qua việc "findElement"
		driver.findElement(By.xpath("a"));
		
	}

	@Test 
	public void TC_03_TIPS() {
	// Chia ra gồm 3 nhóm chính. Tên hàm thể hiện rõ chức năng
	// 1. Hàm để tương tác, action => không trả về (return) dũ liệu là hàm void
		// Click, sendKeys, select,...
		driver.findElement(By.xpath("")).click();
		
	// 2. Lấy ra dữ liệu cho mục đích khác
		//Bắt đầu bằng tiền tố "get", có trả về dữ liệu. Để kiếm tra tính đúng đắn của dữ liệu (equals)
		// getText, getCurrentUrl, getTitle....
		// Assert thuộc thư viện của TestNG, JUnit.... Hiện tại dùng TestNG cho automation test
		driver.getTitle(By.id(""));
		Assert.assertEquals("", "");
		
		
	// 3. Kiểm tra dữ liệu 
		// Kiểm tra dữ liệu dạng boolean True/False >> Hàm assert
		// isDisplayed, isEnabled, isSelected
		
		
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}