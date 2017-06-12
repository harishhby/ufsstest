package com.unisys.pom.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.unisys.pom.pages.UFSSLandingPage;
import com.unisys.pom.pages.UFSSLaunchPage;
import com.unisys.pom.pages.UFSSLoginPage;
import com.unisys.pom.testcases.base.BaseTest;
import com.unisys.pom.util.Constants;
import com.unisys.pom.util.DataUtil;

public class LoginTest extends BaseTest {
	String testCaseName="LoginTest";
	public String actualResult="";	

	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String, String> data)
	{
		test=extent.startTest(testCaseName+" - "+data.get("Browser"),"Login Functionality validation");
		if(!DataUtil.isTestExecutable(xls, testCaseName) ||  data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		

		test.log(LogStatus.INFO,"Starting Login Test");
		init(data.get("Browser"));
		
		if(driver ==null)
		{
			reportFailure("Browser NOT Launched");
		}
		
		test.log(LogStatus.INFO, data.get("Browser")+" Browser opened Successfully - ");

		UFSSLaunchPage launchpage=new UFSSLaunchPage(driver,test);
		PageFactory.initElements(driver, launchpage);

		Object pageLauch=launchpage.gotoLoginPage();	
		if(pageLauch instanceof UFSSLoginPage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("URL of an Application NOT Launched");
		}
		//test.log(LogStatus.PASS, "URL Launched Successfully");
		
		
		Object page=((UFSSLoginPage) pageLauch).doLogin(data.get("Username"), data.get("Password"));		
		if(page instanceof UFSSLandingPage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("User NOT Logged in");
		}
		test.log(LogStatus.PASS, "Login Test Passed");
	}


	@AfterMethod
	public void quit()
	{
		if(extent!=null)
		{
			extent.endTest(test);
			extent.flush();		
		}
		if(driver!=null)
		{
			driver.quit();
		}
	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(xls, testCaseName);
	}
}
