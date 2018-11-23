package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Birst {
	
	//public static WebDriver driver;
	public static WebElement element;
	public static List<WebElement> elements;
	
	
	public static WebElement logoBirst(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//img[@class = 'desktop-logo white']"));
		return element;	
	}
	
	public static WebElement resourcesLink(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Resources"));
		return element;	
	}
	
	public static WebElement fiterByType(WebDriver driver)
	{
		element = driver.findElement(By.id("resource-type"));
		return element;	
	}
	
	public static WebElement fiterByRole(WebDriver driver)
	{
		element = driver.findElement(By.id("resource-role"));
		return element;	
	}
	
	public static List<WebElement> birstResourcesResults(WebDriver driver)
	{
	
		elements = driver.findElements(By.xpath("//div[@class = 'resource-info']"));
		return elements;	
	}
	
	public static WebElement privacyCloseIcon(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//a[@title = 'Close']"));
		return element;	
	}
	
	public static WebElement webinarPageHeader(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//h1[@class = '']"));
		return element;	
	}
}
