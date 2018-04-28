package selenium.test.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InternetExplorerBrowserTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.ie.driver", "D:\\Mobile C&C\\_project\\bizMOB Client Test\\driver\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
	}

}
