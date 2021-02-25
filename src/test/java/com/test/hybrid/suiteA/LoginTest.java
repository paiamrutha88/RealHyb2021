package com.test.hybrid.suiteA;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.test.hybrid.base.BaseTest;
import com.test.hybrid.reports.ExtentManager;
import com.test.hybrid.util.Constants;
import com.test.hybrid.util.DataUtil;

public class LoginTest extends BaseTest{
	
	@Test(dataProvider="getData")
	public void login(Hashtable <String, String> data)
	{
		
		
		
		test.log(Status.INFO, "Starting with test : "+testName);
		
		//this is run mode of data set
		
		if (DataUtil.isSkip(testName, xls) || data.get(Constants.RUNMODE_COLUMN).equals(Constants.RUNMODE_NO))
		{
			test.log(Status.SKIP, "test skipped " +testName +" "+ data.toString());
			throw new SkipException("Runmode set to No for the data set or test case");
		}
			
		
		ds.executeKeywords(testName, xls, data);
		System.out.println(data.get("Runmode"));
		
		
	}
	
	
	

}
