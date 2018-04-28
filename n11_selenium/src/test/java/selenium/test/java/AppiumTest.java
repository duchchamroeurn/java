package selenium.test.java;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppiumTest {
	
	private AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // set up appium
    	File app = new File("D:/wamp/www/angular/tobb/platforms/android/build/outputs/apk/android-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		capabilities.setCapability("device", "selendroid");
        capabilities.setCapability("deviceName","EP7338C6YX");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.ionicframework.myapp745050");
        capabilities.setCapability("appActivity", "com.ionicframework.myapp745050.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void webView()  {    	
    	Set<String> contextNames = driver.getContextHandles();
    	for (String contextName : contextNames) {
    	    System.out.println(contextName);
    	}
    	//driver.context((String) contextNames.toArray()[1]);
    	driver.context("WEBVIEW_0");

    	// ----- web testing - BOF
    	
    	WebDriverWait wait = new WebDriverWait(driver, 5);
		
		WebElement username = driver.findElement(By.cssSelector("input[ng-model='loginData.username']"));
		wait.until(ExpectedConditions.visibilityOf(username));
		username.clear();
		username.sendKeys("sangkhim");
		
		WebElement password = driver.findElement(By.cssSelector("input[ng-model='loginData.password']"));
		wait.until(ExpectedConditions.visibilityOf(password));
		password.clear();
		password.sendKeys("passw0rd");	
		password.submit();
		
		// ----- web testing - EOF

    	driver.context("NATIVE_APP");
    	
    	// ----- native testing - BOF    	
    	
    	WebDriver driver1 = new Augmenter().augment(driver);
		File file  = ((TakesScreenshot)driver1).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("D:/appium/Screenshot.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
		File logFile = new File("D:/appium/logcat.log");
		PrintWriter log_file_writer;
		try {
			log_file_writer = new PrintWriter(logFile);
			for (LogEntry temp : logEntries) {
				log_file_writer.println(temp);
			}
			log_file_writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	// ----- native testing - EOF
    }
    
}
