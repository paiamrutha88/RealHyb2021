package com.test.hybrid.suiteA;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.test.hybrid.base.BaseTest;
import com.test.hybrid.reports.ExtentManager;
import com.test.hybrid.util.Constants;
import com.test.hybrid.util.DataUtil;

public class TestB  extends BaseTest{
	
	@Test(dataProvider="getData")
	public void login(Hashtable <String, String> data)
	{
		//this is run mode of data set
		
		
		
		test.log(Status.INFO, "Starting with test : "+testName);
		
		

		
		
		if (DataUtil.isSkip(testName, xls) || data.get(Constants.RUNMODE_COLUMN).equals(Constants.RUNMODE_NO))
			throw new SkipException("Runmode set to No for the data set or test case" +testName);
		System.out.println("Running testB");
		ds.executeKeywords(testName, xls, data);
		
		
		
	}
	
	
	

}
