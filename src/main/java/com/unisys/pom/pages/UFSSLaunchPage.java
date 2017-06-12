package com.unisys.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.unisys.pom.base.BasePage;
import com.unisys.pom.util.Constants;

public class UFSSLaunchPage extends BasePage {	
	

	public UFSSLaunchPage(WebDriver driver,ExtentTest test)
	{
		super(driver,test);
	}

	public Object gotoLoginPage()
	{
		test.log(LogStatus.INFO, "Go to URL "+Constants.getEnvDetails().get("url"));
		driver.get(Constants.getEnvDetails().get("url"));
		/*LoginPage loginPage=new LoginPage(driver,test);
		PageFactory.initElements(driver, loginPage);*/
		
		boolean UrlLaunchSuccess=isElementPresent(Constants.LOGIN_USERNAME);
		if(UrlLaunchSuccess)	
		{	
			test.log(LogStatus.INFO, "URL Launch SUCCESSFUL");
			UFSSLoginPage loginPage=new UFSSLoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;		
		}
		else
		{
			//test.log(LogStatus.INFO, "URL Launch NOT Successfull");
			UFSSLaunchPage lPage=new UFSSLaunchPage(driver, test);
			PageFactory.initElements(driver, lPage);
			reportFailure("URL Launch UNSUCCESSFUL");
			return lPage;
			
		}
		
	}

}
