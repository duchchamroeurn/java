package selenium.test.java;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeEmulatorTest {

	public static void main(String[] args) {
		// here creating our first map for deviceName
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "Apple iPhone 4");

		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);					
		
		// setting DesiredCapabilities for chrome
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		
		File file = new File("src\\test\\resources\\browser_drivers\\chrome_driver_2.19.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		WebDriver driver = new ChromeDriver(capabilities);

		// opting paytm mobile website
		driver.get("http://paytm.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("mobileNumber")).sendKeys("99999978609");
	}

}