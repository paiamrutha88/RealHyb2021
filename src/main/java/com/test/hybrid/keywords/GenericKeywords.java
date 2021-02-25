package com.test.hybrid.keywords;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class GenericKeywords {
	
	public Properties prop;
	public Properties envProp;
	public String objectKey;
	public String dataKey;
	public Hashtable <String, String> data;
	public WebDriver driver;
	public ExtentTest test;
	
	
	public void OpenBrowser()
	{
		String browser = data.get(dataKey);
		
		if (browser.equalsIgnoreCase("Chrome"))
			driver=new ChromeDriver();
		else if (browser.equalsIgnoreCase("mozilla"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("IE"))
			driver = new InternetExplorerDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver=new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void click()
	{
		getObject(objectKey).click();
	}
	
	public void type()
	{
		getObject(objectKey).sendKeys(data.get(dataKey));
	}
	
	public void navigate()
	{
		test.log(Status.INFO,"Navigating to " +prop.getProperty(objectKey) );
		driver.get(prop.getProperty(objectKey));
	}
	
	public void validateTitle()
	{
		test.log(Status.INFO,"Validating title " +prop.getProperty(objectKey) );
		
		String expTitle = prop.getProperty(objectKey);
		String actTitle= driver.getTitle();
		
		if(!expTitle.equals(actTitle))
		{
			//report failure
			reportFailure("Title does not match");
		}
	}
	
	public void validateElementPresent()
	{
		if (!isElementPresent(objectKey))
		{
			reportFailure("Element not present"+ objectKey);
		}
	}
	
	public WebElement getObject(String objectKey)
	{
		WebElement e = null;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		try
		{
		if (objectKey.endsWith("_xpath"))
				e=driver.findElement(By.xpath(prop.getProperty(objectKey)));
		else if (objectKey.endsWith("_id"))
			e=driver.findElement(By.id(prop.getProperty(objectKey)));
		else if (objectKey.endsWith("_linktext"))
			e=driver.findElement(By.linkText(prop.getProperty(objectKey)));
		else if (objectKey.endsWith("_css"))
			e=driver.findElement(By.cssSelector(prop.getProperty(objectKey)));
		else if (objectKey.endsWith("_classname"))
			e=driver.findElement(By.className(prop.getProperty(objectKey)));
		
		
		
		
		wait.until(ExpectedConditions.visibilityOf(e));
		wait.until(ExpectedConditions.elementToBeClickable(e));
		}
		catch (Exception ex)
		{
			reportFailure("Object not found"+ objectKey);
		}
		
		return e;
		
	}
	
	
	
	
	public boolean isElementPresent(String objectKey)
	{
		List <WebElement> list = null;
		
		if (objectKey.endsWith("_xpath"))
			list=driver.findElements(By.xpath(prop.getProperty(objectKey)));
		else if (objectKey.endsWith("_id"))
			list=driver.findElements(By.id(prop.getProperty(objectKey)));
		else if (objectKey.endsWith("_linktext"))
			list=driver.findElements(By.linkText(prop.getProperty(objectKey)));
		else if (objectKey.endsWith("_css"))
			list=driver.findElements(By.cssSelector(prop.getProperty(objectKey)));
		else if (objectKey.endsWith("_classname"))
			list=driver.findElements(By.className(prop.getProperty(objectKey)));
		
		if (list.size() == 0)
			return false;
		else
			return true;
					
	}
	
	
	public void reportFailure(String message)
	{
		//fail test case
		//take screen shot
		//embed in reports
		
		test.log(Status.FAIL, message);
		Assert.fail(message);
		
		
	}
	
	public void quit()
	{
		if(driver != null)
			driver.quit();
			
	}
	
	
	
	
	
	
	
	

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public Properties getEnvProp() {
		return envProp;
	}

	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
	}

	public String getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	public Hashtable<String, String> getData() {
		return data;
	}

	public void setData(Hashtable<String, String> data) {
		this.data = data;
	}

	public ExtentTest setExtentTest() {
		return test;
	}

	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}
	
	
	
	

}
