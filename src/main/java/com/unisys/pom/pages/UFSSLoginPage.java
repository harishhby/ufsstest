package com.unisys.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.unisys.pom.base.BasePage;
import com.unisys.pom.util.Constants;

public class UFSSLoginPage extends BasePage {

	@FindBy(xpath=Constants.LOGIN_USERNAME)
	public WebElement un;

	@FindBy(xpath=Constants.LOGIN_PASSWORD)
	public WebElement pwd;

	@FindBy(xpath=Constants.LOGIN_BUTTON)
	public WebElement loginButton;


	public UFSSLoginPage(WebDriver driver,ExtentTest test)
	{
		super(driver,test);
	}

	public Object doLogin(String Un,String Pwd)
	{
		test.log(LogStatus.INFO, "Logging in...");
		un.sendKeys(Un);
		pwd.sendKeys(Pwd);
		loginButton.click();
		boolean loginSuccess=isElementPresent(Constants.HOME_MENU_ICON);
		if(loginSuccess)	
		{
			test.log(LogStatus.PASS, "Login Success");
			UFSSLandingPage landingPage=new UFSSLandingPage(driver, test);
			PageFactory.initElements(driver, landingPage);
			return landingPage;
		}
		else
		{
			test.log(LogStatus.INFO, "Login Unsuccessfull");
			UFSSLoginPage loginPage=new UFSSLoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		}
	}

}
