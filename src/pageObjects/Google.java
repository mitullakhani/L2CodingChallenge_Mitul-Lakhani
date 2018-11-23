package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Google {
	
	//public static WebDriver driver;
	public static WebElement element;
	public static List<WebElement> elements;
	
	public static WebElement searchBox(WebDriver driver)
	{
		element = driver.findElement(By.name("q"));
		return element;	
	}	
	
	public static List<WebElement> searchResults(WebDriver driver)
	{
	
		elements = driver.findElements(By.xpath("//h3[@class = 'LC20lb']"));
		return elements;	
	}
		
	
	
}
