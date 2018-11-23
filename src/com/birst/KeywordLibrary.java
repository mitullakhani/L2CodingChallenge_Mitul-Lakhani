package com.birst;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;
import net.bytebuddy.implementation.bytecode.Throw;
import pageObjects.Birst;
import pageObjects.Google;


public class KeywordLibrary {
	
	static WebDriver driver;
	public static String result;

	public static String baseURL = null;
	
	
	public static void callMethods(String Keyword, String Param1, String Param2, String Param3, String Param4, String Param5) throws Exception, Exception
	{
		Method method = KeywordLibrary.class.getMethod(Keyword, String.class, String.class, String.class,String.class, String.class);
		method.invoke(Keyword, Param1, Param2, Param3, Param4, Param5);
	}
	
	public static void openBrowser(String Param1, String Param2, String Param3, String Param4, String Param5) throws Exception, Exception
	{
		try{
			
			switch(Param1)
			{  
			
		    case "Chrome": 
		    { 	   	
		    		System.setProperty("webdriver.chrome.driver", "F:\\Selenium Jars\\chromedriver_237.exe");
		    		driver = new ChromeDriver();
		    		driver.manage().window().maximize();
		    		driver.get(Param2);
		    		
		    		baseURL = Param2;
		    		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    		
		    		result = "Pass";
		    		
			}
		   break;  
		   
		    case "Firefox": 
		    { 	   	
		    		System.setProperty("webdriver.gecko.driver", "F:\\Selenium Jars\\geckodriver.exe");
		    		driver = new FirefoxDriver();
		    		driver.manage().window().maximize();
		    		driver.get(Param2);
		    		
		    		baseURL = Param2;
		    		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    		
		    		result = "Pass";
			}
		   break; 
		   
		    case "IE": 
		    { 	   	
		    	 	System.setProperty("webdriver.ie.driver", "F:\\Selenium Jars\\IEDriverServer.exe");
		    		driver = new InternetExplorerDriver();
		    		driver.manage().window().maximize();
		    		driver.get(Param2);
		    		
		    		baseURL = Param2;
		    		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    		
		    		result = "Pass";
			}
		   break; 
		   
			}
		   		
		}
		catch(Exception e)
		{
			result = "Fail";
			System.out.println("openBrowser");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static void enterText(String Param1, String Param2, String Param3, String Param4, String Param5) throws Exception
	{	
		try
		{

			Google.searchBox(driver).sendKeys(Param1);
			Google.searchBox(driver).sendKeys(Keys.ENTER);
			result = "Pass";
		}
		catch(Exception e)
		{
			result = "Fail";
			e.printStackTrace();
		}
				
	}
	
	
	public static void pageTitleVerifcation(String Param1, String Param2, String Param3, String Param4, String Param5) throws Exception
	{	
		try
		{
	    if(driver.getTitle().contains(Param1))
	    {
	    	System.out.println("Page title is Google");
	    }
	    else
	    {
	    	System.out.println("Page title is not Google");
	    }
	    result = "Pass";
		}
		catch (Exception e) 
		{
			result = "Fail";
			e.printStackTrace();
		}

	}
	
	public static void searchResultsVerifcation(String Param1, String Param2, String Param3, String Param4, String Param5) throws Exception
	{	
		try
		{
			List<WebElement> searchResults = Google.searchResults(driver);
		    for(WebElement temp : searchResults)
		    {
		    	if(temp.getText().contains(Param1))
		    	{
		    		System.out.println(Param1 + " is found in google search results");
		    		temp.click();
		    		break;
		    	}
		    }

	    result = "Pass";
		}
		catch (Exception e) 
		{
			result = "Fail";
			e.printStackTrace();
		}

	}
	
	public static void logoVerifcation(String Param1, String Param2, String Param3, String Param4, String Param5) throws Exception
	{	
		try
		{
			if(driver.findElement(By.xpath("//img[@class = 'desktop-logo white']")).isDisplayed())
		    {
		    	System.out.println("Logo is available");
		    }
		    else
		    {
		    	System.out.println("Logo is not available");
		    	throw new Exception();
		    }


	    result = "Pass";
		}
		catch (Exception e) 
		{
			result = "Fail";
			e.printStackTrace();
		}

	}
	
	public static void linkClick(String Param1, String Param2, String Param3, String Param4, String Param5) throws Exception
	{	
		try
		{
			
			switch(Param1)
			{  
			
		    case "Resources": 
		    { 	
		Birst.resourcesLink(driver).click();
	    result = "Pass";
		}
		    break;  
			   
		    case "Webinar Card": 
		    { 	 
		    	List<WebElement> birstResources = Birst.birstResourcesResults(driver);
			    Birst.privacyCloseIcon(driver).click();
			    for(WebElement temp : birstResources)
			    {
			    	if(temp.findElement(By.tagName("a")).getText().contains(Param2))
			    	{
			    		temp.findElement(By.tagName("a")).click();
			    		break;
			    	}
			    	
			    }

		    }
		    break;
			}
		}
		catch (Exception e) 
		{
			result = "Fail";
			e.printStackTrace();
		}

	}
	
	
	public static void dropDownValueSelection(String Param1, String Param2, String Param3, String Param4, String Param5) throws Exception, Exception
	{
		try{
			
			switch(Param1)
			{  
			
		    case "Filter by Type": 
		    { 	   	
		    	Select filterByType = new Select(Birst.fiterByType(driver));
			    filterByType.selectByVisibleText("Webinar");

		    		
		    		
			}
		   break;  
		   
		    case "Filter by Role": 
		    { 	   	
		    	Select filterByRole = new Select(Birst.fiterByRole(driver));
			    filterByRole.selectByVisibleText("BI/IT");
			    Thread.sleep(5000);

			}
		   break; 
		   
		   
			}
			result = "Pass"; 		
		}
		catch(Exception e)
		{
			result = "Fail";
			e.printStackTrace();
		}
	}
	
	
	
	public static void WebinarPageHeaderVerification(String Param1, String Param2, String Param3, String Param4, String Param5)
	{
		try{
			if(Birst.webinarPageHeader(driver).getText().contains(Param1))
		    {
		    	System.out.println("Text on webinar page is same as expected");
		    }
		    else
		    {
		    	System.out.println("Text on webinar page is not same as expected");
		    	throw new Exception();
		    }
			result = "Pass"; 
		}
		catch(Exception e)
		{
			result = "Fail";
			
			e.printStackTrace();
		}
				
	}

	public static void browserClose(String Param1, String Param2, String Param3, String Param4, String Param5)
	{
		try{
			driver.quit();
			
			result = "Pass";
		}
		catch(Exception e)
		{
			result = "Fail";
			
			e.printStackTrace();
		}
				
	}
	
	
}
