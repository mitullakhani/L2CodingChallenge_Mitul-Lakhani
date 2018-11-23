package com.birst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelComm {
	String filepath = "..\\L2Coding_MitulLakhani\\Birst_Master.xls";
	
	public HashMap<Integer, List<String>> readExcel(String sheetname) throws Exception
	{
		File mf = new File(filepath);
		FileInputStream fis = new FileInputStream(mf);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		Sheet s = wb.getSheet(sheetname);
		
		HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		
		
		for(int i = 1; i<= s.getLastRowNum(); i++)
		{
			Row r = s.getRow(i);
			
			List<String> row = new ArrayList<String>();
			
			for(int j = 0; j < 6; j++)
			{
				
				Cell c= r.getCell(j);
				
				row.add(c.getStringCellValue());
			}
		
			map.put(i, row);
			
		}

		return map;
	}
	
	public void writeExcel(int i, String sheetname) throws Exception
	{
		File mf = new File(filepath);
		FileInputStream fis = new FileInputStream(mf);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		Sheet s = wb.getSheet(sheetname);
		Row r = s.getRow(i);
		Cell c= r.createCell(6);
		c.setCellValue(KeywordLibrary.result);
		Cell c2= r.createCell(7);
		c2.setCellValue(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
			FileOutputStream fos = new FileOutputStream(mf);
		wb.write(fos);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
	}

}
