package selenium.test.java;



import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeEmulatorWithSize {

	public static void main(String[] args) {
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("width", 560);
		deviceMetrics.put("height", 640);
		deviceMetrics.put("pixelRatio", 4.0);
		
		Map<String, Object> mobileEmulation = new HashMap<String, Object>();
		mobileEmulation.put("deviceMetrics", deviceMetrics);
		mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
		
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		
		WebDriver driver = new ChromeDriver(capabilities);
		driver.get("http://paytm.com");
		driver.findElement(By.id("mobileNumber")).sendKeys("99999978609");
	}

}

// Read more:
// http://www.abodeqa.com/2015/08/28/mobile-emulation-in-google-chrome-using-selenium-webdriver/#ixzz3lA5j7jh8
// https://sites.google.com/a/chromium.org/chromedriver/mobile-emulation