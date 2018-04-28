package selenium.test.java;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class HtmlUnitBrowserTest {

	public static void main(String[] args) {
		WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lst-ib']")));
		WebElement webElement = driver.findElement(By.xpath("//*[@id='lst-ib']"));
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
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://www.google.com");
		driver.manage().window().maximize();
		System.out.println( driver.getTitle() );
		assertEquals("Google", driver.getTitle());
	}

}
