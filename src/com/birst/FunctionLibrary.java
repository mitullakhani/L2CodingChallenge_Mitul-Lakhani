package com.birst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FunctionLibrary {
	
	public static String switchWindow(StopWatch pageLoad, String pw, WebDriver driver)
	{String url = null;
		Set<String> cw = driver.getWindowHandles();
	    
		if(cw.size()!=1)
		{
		for(String temp : cw)
	    {
	    	if(!temp.equals(pw))
	    	{
	    		driver.switchTo().window(temp);
	    		url = driver.getCurrentUrl();
	    		pageLoad.stop();
	    		driver.close();
	    		driver.switchTo().window(pw);
	    	}
	    	
	    }
		}
		else
    	{
    	url = driver.getCurrentUrl();
    	pageLoad.stop();
    	driver.navigate().back();
    	}
		return url;
	}
	
	public static String switchWindow_Card(String pw, WebDriver driver)
	{String url = null;
		Set<String> cw = driver.getWindowHandles();
	    
		if(cw.size()!=1)
		{
		for(String temp : cw)
	    {
	    	if(!temp.equals(pw))
	    	{
	    		driver.switchTo().window(temp);
	    		url = driver.getCurrentUrl();
	    		driver.close();
	    		driver.switchTo().window(pw);
	    	}
	    	
	    }
		}
		else
    	{
    	url = driver.getCurrentUrl();
    	driver.navigate().back();
    	}
		return url;
	}
	
	public static String httpStatus(String url) throws Exception, Exception
	{String httpstatus;
		HttpClient client = HttpClientBuilder.create().build();
	HttpGet request = new HttpGet(url);
	HttpResponse response = client.execute(request);
	if(response.getStatusLine().getStatusCode() != 200)
	{
		httpstatus = "KO";
	}
	else
	{
		httpstatus = "OK";
	}
	return httpstatus; 
	}
	
	public static void webDriverWait(WebDriver driver, WebElement element, int time) throws Exception, Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, time);
    	wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void scrollWindow(WebDriver driver)
	{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, -25000);");
	}

	
	public static String getImageURL(WebDriver driver, WebElement element) 
	{
		//element.getAttribute("style").lastIndexOf("url(");
		//element.getAttribute("style").lastIndexOf(")");
		String imageURL = element.getAttribute("style").substring(element.getAttribute("style").lastIndexOf("url(")+5, element.getAttribute("style").lastIndexOf(")")-1);
		return imageURL;
	}
	
	public String getExcelData(String excelPath, String sheetName, int row, int col) throws Exception 
	{	File mf = new File(excelPath);
		FileInputStream fis = new FileInputStream(mf);
		Workbook wb;
		String cellValue = null;
		if(excelPath.endsWith("x"))
		{//XML format latest version
			wb = new XSSFWorkbook(fis);
		}
		else
		{//Older 97-2003 format
			
			wb = new HSSFWorkbook(fis);
		}	
		
		Sheet s = wb.getSheet(sheetName);
		Row r = s.getRow(row-1);
		Cell c= r.getCell(col-1);
		//cellValue = c.getStringCellValue();
		switch(c.getCellTypeEnum())
        {
        case NUMERIC:
           int cellValueNumeric = (int) c.getNumericCellValue();
            cellValue = String.valueOf(cellValueNumeric);
            break;
        case STRING:
        	cellValue = c.getStringCellValue();
            break;
        default:
            break;

        }
		return cellValue;
		
	}

	public void writeExcelData(String excelPath, String sheetName, int row, int col, String value) throws Exception 
	{	File mf = new File(excelPath);
		FileInputStream fis = new FileInputStream(mf);
		Workbook wb;
		String cellValue = null;
		if(excelPath.endsWith("x"))
		{//XML format latest version
			wb = new XSSFWorkbook(fis);
		}
		else
		{//Older 97-2003 format
			wb = new HSSFWorkbook(fis);
		}	
		
		Sheet s = wb.getSheet(sheetName);
		Row r = s.getRow(row-1);
		Cell c= r.createCell(col-1);
		//cellValue = c.getStringCellValue();
		
		c.setCellValue(value);
		
		FileOutputStream fout = new FileOutputStream(mf);
		wb.write(fout);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
