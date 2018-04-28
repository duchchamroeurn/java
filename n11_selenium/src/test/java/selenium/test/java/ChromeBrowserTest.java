package selenium.test.java;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeBrowserTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Mobile C&C\\_project\\bizMOB Client Test\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/LifeOn-Cafe-757132954431077/");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lst-ib']")));
//		WebElement webElement = driver.findElement(By.xpath("//*[@id='lst-ib']"));
//		webElement.sendKeys("Hello World");	
//		webElement.submit();
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.r")));		
//		List<WebElement> list = driver.findElements(By.cssSelector("h3.r")); 
//		for(WebElement element : list){
//			System.out.println( element.getText() );
//		}
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("_39g5")));		
		List<WebElement> list = driver.findElements(By.cssSelector("uiButtonText")); 
		for(WebElement element : list){
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("uiButtonText")));		
		list = driver.findElements(By.cssSelector("uiButtonText")); 
		for(WebElement element : list){
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}
	}
	
	@Test
	public void test(){
		System.setProperty("webdriver.chrome.driver", "D:\\Mobile C&C\\_project\\bizMOB Client Test\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		System.out.println( driver.getTitle() );
		assertEquals("Google", driver.getTitle());
	}

}
