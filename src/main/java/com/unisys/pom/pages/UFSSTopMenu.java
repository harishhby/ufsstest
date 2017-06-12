package com.unisys.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.unisys.pom.util.Constants;

public class UFSSTopMenu {
	WebDriver driver;
	ExtentTest test;

	public UFSSTopMenu(WebDriver driver,ExtentTest test)
	{
		this.driver=driver;
		this.test=test;
	}
	
	@FindBy(xpath=Constants.SIGNOFF_LNK)
	public WebElement signOff;

	public void signOut()
	{
		signOff.click();
	}

	public void gotoSttings()
	{
		test.log(LogStatus.INFO, "Going to Setings");

	}

	public void search()
	{

	}
}
