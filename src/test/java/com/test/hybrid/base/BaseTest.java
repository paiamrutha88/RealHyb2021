package com.test.hybrid.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.test.hybrid.driver.DriverScript;
import com.test.hybrid.keywords.AppKeywords;
import com.test.hybrid.reports.ExtentManager;
import com.test.hybrid.util.DataUtil;
import com.test.hybrid.util.Xls_Reader;

public class BaseTest {
	
	public Properties envProp; //prod, uat.prop
	public Properties prop; //env.prop
	public Xls_Reader xls;
	public String testName=null;
	public String suiteName=null;
	public DriverScript ds;
	public ExtentReports rep;
	public ExtentTest test;
	
	
	@BeforeTest
	public void init()
	{
		testName= this.getClass().getSimpleName();
		String suite[]= this.getClass().getPackage().getName().split("\\.");
		
		suiteName = suite[suite.length-1];
		
		System.out.println(testName);
		System.out.println(suiteName);
		
		
		prop=new Properties();
		envProp= new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//Env.properties");
			
			prop.load(fis);
			
			String env= prop.getProperty("env");
			
			fis= new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//"+env+".properties");
			envProp.load(fis);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		xls = new Xls_Reader(prop.getProperty(suiteName+"_xls"));
		
		ds= new DriverScript();
		ds.setEnvProp(envProp);
		ds.setProp(prop);
		
		
		
		
	}
	
	@BeforeMethod
	public void initTest()
	{
		rep =  new ExtentManager().getInstance(prop.getProperty("reportPath"));
		test = rep.createTest(testName);
		ds.setTest(test);
	}
	

	
	@AfterMethod
	public void quit()
	{
		if(ds != null)
		ds.quit();
		
		if(rep !=null)
			rep.flush();
			
	}
	
	
	@DataProvider
	public Object [][] getData()
	{
		return DataUtil.getTestData(testName, xls);
		
	}
}
