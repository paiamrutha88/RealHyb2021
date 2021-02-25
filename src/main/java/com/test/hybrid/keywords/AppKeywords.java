package com.test.hybrid.keywords;

import com.aventstack.extentreports.Status;

public class AppKeywords extends GenericKeywords {

	
	public void validateLogin()
	{
		
	}
	
	public void validateTitle()
	{
		test.log(Status.INFO, "Validating Title " + prop.getProperty(objectKey));
	}
	
	public void defaultLogin()
	{
		String username=envProp.getProperty("username");
		String password=envProp.getProperty("password");
		
		test.log(Status.INFO, "Default usernme : "+username+" Default Password : " +password);
	}
}
