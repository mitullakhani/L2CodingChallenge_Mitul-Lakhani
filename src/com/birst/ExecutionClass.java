package com.birst;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class ExecutionClass {
	
	static String filePath = "..\\L2Coding_MitulLakhani\\Birst_Master.xls";
	static String reportPath= "..\\L2Coding_MitulLakhani\\HTMLReports.html";
	
@Test
public void testNG() throws Exception
{
	
	ExtentReports report = new ExtentReports(reportPath);
	ExtentTest logger;
	
	List<String> TC = new ArrayList<String>();
	File mf = new File(filePath);
	FileInputStream fis = new FileInputStream(mf);
	HSSFWorkbook wb = new HSSFWorkbook(fis);
	Sheet s = wb.getSheet("IndexSheet");
	for(int z = 1; z<=s.getLastRowNum(); z++)
	{
		Row r = s.getRow(z);
		Cell c = r.getCell(1);
		TC.add(c.getStringCellValue());
	}
			
	for(String temp : TC)
	{
	System.out.println(temp);
	ExcelComm obj = new ExcelComm();
	HashMap<Integer, List<String>> map = obj.readExcel(temp);
	logger=report.startTest(temp);
	for(int i = 1; i <= map.size(); i++ )
	{
	List<String> list1 = map.get(i);
	
	KeywordLibrary.callMethods(list1.get(0), list1.get(1), list1.get(2), list1.get(3), list1.get(4), list1.get(5));
	obj.writeExcel(i, temp);
	if(KeywordLibrary.result.equals("Pass"))
	{
		logger.log(LogStatus.PASS, "Passed "+" - " +list1.get(0));
	}
	else
	{
		logger.log(LogStatus.FAIL, "Failed " +" - "+list1.get(0));
	}
	
	}
	report.endTest(logger);
	report.flush();
	}
}
	public static void main(String[] args) throws Exception {
				// TODO Auto-generated method stub
		ExtentReports report = new ExtentReports(reportPath);
		ExtentTest logger;
		
		List<String> TC = new ArrayList<String>();
		File mf = new File(filePath);
		FileInputStream fis = new FileInputStream(mf);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		Sheet s = wb.getSheet("IndexSheet");
		for(int z = 1; z<=s.getLastRowNum(); z++)
		{
			Row r = s.getRow(z);
			Cell c = r.getCell(1);
			TC.add(c.getStringCellValue());
		}
				
		for(String temp : TC)
		{
		System.out.println(temp);
		ExcelComm obj = new ExcelComm();
		HashMap<Integer, List<String>> map = obj.readExcel(temp);
		logger=report.startTest(temp);
		for(int i = 1; i <= map.size(); i++ )
		{
		List<String> list1 = map.get(i);
		
		KeywordLibrary.callMethods(list1.get(0), list1.get(1), list1.get(2), list1.get(3), list1.get(4), list1.get(5));
		obj.writeExcel(i, temp);
		if(KeywordLibrary.result.equals("Pass"))
		{
			logger.log(LogStatus.PASS, "Passed "+" - "+list1.get(0));
		}
		else
		{
			logger.log(LogStatus.FAIL, "Failed "+" - "+list1.get(0));
		}
		
		}
		report.endTest(logger);
		report.flush();
		}
	}

}
