package selenium.test.java;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhantomjsBrowserTest {

	public static void main(String[] args) {
		File file = new File("D:/Mobile C&C/_project/bizMOB Client Test/driver/phantomjs.exe");
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
		WebDriver driver = new PhantomJSDriver();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
		WebElement webElement = driver.findElement(By.name("q"));
		webElement.sendKeys("Hello World");
		webElement.submit();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.r")));		
		List<WebElement> list = driver.findElements(By.cssSelector("h3.r")); 
		for(WebElement element : list){
			System.out.println( element.getText() );
		}
	}
	
	@Test
	public void test(){
		System.setProperty("phantomjs.binary.path", "D:\\Mobile C&C\\Project\\bizMOB Client Test\\driver\\phantomjs.exe");
		WebDriver driver = new PhantomJSDriver();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		System.out.println( driver.getTitle() );
		assertEquals("Google", driver.getTitle());
	}

}
