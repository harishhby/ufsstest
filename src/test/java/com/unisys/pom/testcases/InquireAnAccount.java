package com.unisys.pom.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.unisys.pom.pages.UFSSCustomerAccntLinkPage;
import com.unisys.pom.pages.UFSSLandingPage;
import com.unisys.pom.pages.UFSSLaunchPage;
import com.unisys.pom.pages.UFSSLoginPage;
import com.unisys.pom.pages.UFSSMortgageAccountPage;
import com.unisys.pom.pages.UFSSMortgageTxnSummaryPage;
import com.unisys.pom.testcases.base.BaseTest;
import com.unisys.pom.util.Constants;
import com.unisys.pom.util.DataUtil;

public class InquireAnAccount extends BaseTest {
	String testCaseName="InquireAnAccount";
	public String actualResult="";	

	@Test(dataProvider="getData")	
	public void doLogin(Hashtable<String, String> data) throws InterruptedException
	{
		test=extent.startTest(testCaseName+" - "+data.get("Browser"),"Inquire an account with different set of data");
		if(!DataUtil.isTestExecutable(xls, testCaseName) ||  data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}


		test.log(LogStatus.INFO,"Starting Login Test");
		init(data.get("Browser"));		
		test.log(LogStatus.INFO, data.get("Browser")+" Browser opened Successfully - ");

		UFSSLaunchPage launchpage=new UFSSLaunchPage(driver,test);
		PageFactory.initElements(driver, launchpage);

		Object pageLauch=launchpage.gotoLoginPage();	
		if(pageLauch instanceof UFSSLoginPage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("Element Not Fount");
		}



		Object page=((UFSSLoginPage) pageLauch).doLogin(data.get("Username"), data.get("Password"));		
		if(page instanceof UFSSLandingPage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("User NOT Logged in");
		}
		

		UFSSLandingPage landingPage=new UFSSLandingPage(driver,test);
		PageFactory.initElements(driver, landingPage);

		Object browsePage =landingPage.browseFullList(data.get("BrowseFullListName"));
		if(browsePage instanceof UFSSMortgageAccountPage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("User NOT Presented with Mortgage Account Details Page");
		}
		test.log(LogStatus.INFO, "User Presented with Mortgage Account Details Page");

		UFSSMortgageAccountPage ufssMortgageAccntPage=new UFSSMortgageAccountPage(driver,test);
		PageFactory.initElements(driver, ufssMortgageAccntPage);
		ufssMortgageAccntPage.inquireAccntNum(data.get("AccountNum"));

		/*Object obj=ufssMortgageAccntPage.txnSummary(data.get("MortgageAccntNum"));
		if(obj instanceof UFSSMortgageTxnSummaryPage)
			actualResult="Success";
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("User NOT Presented With Mortgage Transaction Summary Page");
		}*/


		UFSSMortgageTxnSummaryPage txnSumPage=new UFSSMortgageTxnSummaryPage(driver,test);
		PageFactory.initElements(driver, txnSumPage);
		txnSumPage.txnCMSVal(data.get("MortgageAccntNum"));

		//txnSumPage.

		Object objAccnt=txnSumPage.cusAccntVal(data.get("Customernum"));
		if(objAccnt instanceof UFSSMortgageAccountPage)
		{
			actualResult="Success";
		}
		else
			actualResult="Unsuccessful";
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("Navigation to MTG11 Failed");
		}

		UFSSCustomerAccntLinkPage cusAccntLinkPage=new UFSSCustomerAccntLinkPage(driver,test);
		PageFactory.initElements(driver, cusAccntLinkPage);
		cusAccntLinkPage.cusAccntLink(data.get("BrowseFullListName"),data.get("InValidAccntnum"));		

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
			try
			{
				UFSSLandingPage landingPage=new UFSSLandingPage(driver, test);
				landingPage.getMenu().signOut();
				driver.quit();
			}
			catch(Exception e)
			{
				driver.quit();
			}
		}
	}

	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(xls, testCaseName);
	}
}
