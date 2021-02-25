package com.test.hybrid.util;

import java.util.Hashtable;

public class DataUtil {
	
	
	
	
	
	
	public static Object[][] getTestData(String testName, Xls_Reader xls)
	{
		// Steps :
		 // find row number of the test case
		 // find total columns in test case
		 // find total rows in test case
		
	
		
		
		
		int testStartRowNum=1; //assume
		
		//---------------------------------------------
		
		while (!xls.getCellData(Constants.DATA_SHEET, 0, testStartRowNum).equals(testName))
		{
			testStartRowNum ++;
		}
		
		//System.out.println("Row number of "+testName+ " is :" +testStartRowNum);
		
		//---------------------------------------------
		
		int colStartRowNum = testStartRowNum +1;
		
		int totalCols=0;
		
		while (!xls.getCellData(Constants.DATA_SHEET, totalCols, colStartRowNum).equals(""))
		{
			totalCols++;
		}
		
	//	System.out.println("Total Columns :"+totalCols);
		
		//---------------------------------------------
		
		int dataStartRowNum=testStartRowNum+2;
		int totalRows=0;
		
		while (!xls.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum).equals(""))
		{
			totalRows++;
			dataStartRowNum++;
		}
		
		
		//System.out.println("Total rows : "+totalRows);
		
		
		//--------------------------------------------------
		
		dataStartRowNum=testStartRowNum+2;
		
		Hashtable <String, String> table=null;
		
		Object [][] myData = new Object [totalRows][1];
		int i=0;
		for (int rNum=dataStartRowNum; rNum<dataStartRowNum+totalRows; rNum++ )
		{
			table= new Hashtable<String,String>(); // one hashtable for each excel data row
			
			for (int cNum=0; cNum<totalCols; cNum++)
			{
				//System.out.print(xls.getCellData("Data", cNum, rNum)+ "\t");
				
				String data= xls.getCellData(Constants.DATA_SHEET, cNum, rNum);
				String key= xls.getCellData(Constants.DATA_SHEET, cNum, colStartRowNum);
				
				//System.out.println(key + "---------"+data);
				
				//putting key, values in hashtable
				
				table.put(key, data);
						
			}
			
			
			myData [i][0]= table; //putting hashtable in each row of array
			i++;
			
		//	System.out.println("\n");
		}
		
		
		return myData;
	
	}
	
	
	
	public static boolean isSkip(String testName, Xls_Reader xls)
	{
		int rowCount= xls.getRowCount(Constants.TESTCASES_SHEET);
		
		for (int n=2;n<= rowCount; n++)
		{
			String testCaseName= xls.getCellData(Constants.TESTCASES_SHEET, Constants.TCID_COLUMN, n);
			if (testCaseName.equals(testName))
			{
			String runmode= xls.getCellData(Constants.TESTCASES_SHEET, Constants.RUNMODE_COLUMN, n);
				if (runmode.equals(Constants.RUNMODE_NO))
					return true;
				else
					return false;
			}
			
		}
		return true;
	}

	
	
} 

