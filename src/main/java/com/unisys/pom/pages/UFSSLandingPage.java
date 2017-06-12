package com.unisys.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.unisys.pom.base.BasePage;
import com.unisys.pom.util.Constants;

public class UFSSLandingPage extends BasePage{

	public UFSSLandingPage(WebDriver driver,ExtentTest test)
	{
		super(driver,test);
	}

	@FindBy(xpath=Constants.FIND_A_FUNCTION_TXTBX)
	public WebElement findFunctionTxtBx;

	@FindBy(xpath=Constants.SEARCH_BUTTON)
	public WebElement searchBtn;

	@FindBy(xpath=Constants.MORTGAGE_LABEL)
	public WebElement mortgageAccountLbl;


	public Object browseFullList(String funName)
	{
		test.log(LogStatus.INFO, "Browse an Account Details...");
		findFunctionTxtBx.sendKeys(funName);
		test.log(LogStatus.INFO, "Entered "+funName+" in FIND A FUNCTION text filed");
		searchBtn.click();
		test.log(LogStatus.INFO, "Clicked on Forward Button");

		boolean mortgageAccount=isElementPresent(Constants.MORTGAGE_LABEL);

		if(mortgageAccount)
		{
			UFSSMortgageAccountPage mortgageAccountPage=new UFSSMortgageAccountPage(driver,test);
			PageFactory.initElements(driver, mortgageAccountPage);
			return mortgageAccountPage;	
		}
		else
		{
			UFSSLandingPage ufssLandingPage=new UFSSLandingPage(driver, test);
			PageFactory.initElements(driver, ufssLandingPage);
			return ufssLandingPage;	
		}
	}
}
