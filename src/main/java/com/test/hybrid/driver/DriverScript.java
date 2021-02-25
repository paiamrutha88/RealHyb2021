package com.test.hybrid.driver;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.test.hybrid.keywords.AppKeywords;
import com.test.hybrid.util.Constants;
import com.test.hybrid.util.Xls_Reader;

public class DriverScript {
	
	public Properties envProp; //prod, uat.prop
	public Properties prop;
	public AppKeywords app;
	public ExtentTest test;
	
	
	public void executeKeywords(String testName, Xls_Reader xls, Hashtable <String, String> data)
	{
		app = new AppKeywords(); 
		
		
		app.setProp(prop);
		app.setEnvProp(envProp);
		
		app.setData(data);
		int rows = xls.getRowCount(Constants.KEYWORDS_SHEET);
		
		for (int rNum=0; rNum<rows; rNum++)
		{
		String tcId = xls.getCellData("Keywords", "TCID", rNum+1);
		
		if(tcId.equals(testName)) {
		String keyword = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.KEYWORD_COLUMN, rNum+1);
		String objectKey = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.OBJECT_COLUMN, rNum+1);
		String dataKey = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.DATA_COLUMN, rNum+1);
		String testData = data.get(dataKey);
		
		
		app.setDataKey(dataKey);
		app.setObjectKey(objectKey);
		app.setExtentTest(test);
		
		Method method;
		
		
		try {
			method=app.getClass().getMethod(keyword);
			method.invoke(app);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		test.log(Status.INFO,tcId+" "+keyword+" "+prop.getProperty(objectKey)+" "+testData);
		}
		
		
		}
		
		
	}
	
	
	public void quit()
	{
		if (app != null)
			app.quit();
			
	}

	public Properties getEnvProp() {
		return envProp;
	}

	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}


	public ExtentTest getTest() {
		return test;
	}


	public void setTest(ExtentTest test) {
		this.test = test;
	}
	
	

	
	

}
