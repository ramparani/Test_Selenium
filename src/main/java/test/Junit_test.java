package test;

import java.util.ArrayList;
import java.util.Set;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import static org.junit.Assert.*;


import java.util.concurrent.TimeUnit;

public class Junit_test {
	WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/rampa/Downloads/chromedriver.exe");

	}

	// Launch Web site
	@Test
	public void maintest() throws IOException {
		driver = new ChromeDriver();
		driver.get("https://thawing-stream-73911.herokuapp.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Maximize the browser
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/form/div[1]/div[1]/input"))
				.sendKeys("Ram");

		driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/form/div[1]/div[2]/input"))
				.sendKeys("Parani");
		driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/form/div[2]/input"))
				.sendKeys("testramparani10@gmail.com");
		driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/form/div[3]/input"))
				.sendKeys("1234567890");
		driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/form/div[4]/input")).sendKeys("1234@A");
		driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/form/div[5]/input")).sendKeys("1234@A");
		driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/form/div[6]/input"))
				.sendKeys("C:\\Users\\rampa\\Downloads\\Leadership Principles.docx");

		// considering there is only on e tab opened

		driver.findElement(By.linkText("terms of service")).click();

		String parent_window = driver.getWindowHandle();
		System.out.println("First Window Handle is : " + driver.getWindowHandle());
		Set<String> allWindows_1 = driver.getWindowHandles();
		System.out.println("Total Windows : " + allWindows_1.size());
		ArrayList<String> tabs = new ArrayList<String>(allWindows_1);
		System.out.println(tabs);
		System.out.println("Still now focus on : " + driver.getWindowHandle());
		System.out.println("Shifting focus to : " + tabs.get(1));
		driver.switchTo().window(tabs.get(1));

		System.out.println("First Child Handle : " + driver.getTitle() + " : " + driver.getWindowHandle());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		driver.findElement(By.xpath("/html/body/app-root/div/app-terms/div[3]/button[1]")).click();
		System.out.println("Accepted");
		System.out.println("Closing First Child Handle : " + driver.getWindowHandle());

		System.out.println("Shifting focus to : " + parent_window);
		driver.switchTo().window(parent_window);
		System.out.println("Parent Window Handle : " + driver.getTitle() + " : " + driver.getWindowHandle());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,300)");

		driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/form/button")).submit();
		//String Url="https://thawing-stream-73911.herokuapp.com/register";
		String actualUrl=driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div")).getText();
		 String expectedUrl= driver.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div")).getText();
		 TakesScreenshot ts=(TakesScreenshot)driver;
		  File source=ts.getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(source, new File("./Screenshots/Screen11.png"));
		  System.out.println("Screenshot taken for Successfull Registration");
		
		 
		  
		  assertEquals(expectedUrl,actualUrl);
		System.out.println("Assertion passed");
	}

	@After
	public void close() {
	driver.close();

	}

}
