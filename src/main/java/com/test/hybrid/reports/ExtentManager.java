package com.test.hybrid.reports;

//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html


import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	public static String screenShotFolderPath;

	public static ExtentReports getInstance(String reportPath) {
		if (extent == null) {
			String fileName= "Report.html";
			Date d=new Date();
			String folderName=d.toString().replace(":", "_").replace(" ", "_");
			new File(reportPath+folderName+"//screenshots").mkdirs();
			reportPath=reportPath+folderName+"\\";
			screenShotFolderPath=reportPath+"screenshots";// so that i can access this variable in Generic Keywords class
			createInstance(reportPath+fileName);

			/*
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "2.53.0").addSystemInfo(
					"Environment", "QA");
			*/
		}
		return extent;
	}
	
	public static ExtentReports createInstance(String fileName)
	{
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Extent Reports - Hybrid Framework");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Reports - Automation testing");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
		
	}
}
