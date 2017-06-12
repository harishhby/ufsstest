package com.unisys.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.unisys.pom.base.BasePage;
import com.unisys.pom.util.Constants;

public class UFSSCustomerAccntLinkPage extends BasePage{

	@FindBy(xpath=Constants.FIND_A_FUNCTION_TXTBX)
	public WebElement findFunctionTxtBx;
	
	@FindBy(xpath=Constants.SEARCH_BUTTON)
	public WebElement searchBtn;
	
	@FindBy(xpath=Constants.ACCOUNT_NUMBER_TXTFLD)
	public WebElement accntNum;
	
	@FindBy(xpath=Constants.INQUIRE_BTN)
	public WebElement inquireBtn;
	
	@FindBy(xpath=Constants.ERROR_LBL)
	public WebElement errLabel;


	public UFSSCustomerAccntLinkPage(WebDriver driver,ExtentTest test){
		super(driver,test);
	}
	
	public void inquireAnAccount(String acntNum)
	{
		accntNum.sendKeys(acntNum);
		test.log(LogStatus.INFO, "Entered an Account Number ....");
		
		inquireBtn.click();	
		test.log(LogStatus.INFO, "Clicked On Inquire Button");
		
		boolean prodSuccessMessage=isElementPresent(Constants.TOTAL_CMS_AMOUNT);
		if(prodSuccessMessage)	
		{
			reportPass("Navigated to MTG02 and Inquired for an account "+acntNum);
		}
		else
		{
			
			reportFailure("Navigation to MTG02 Failed");
		}
	}

	public void cusAccntLink(String mrtgagenum, String acntNum) {
		
		findFunctionTxtBx.sendKeys(mrtgagenum);
		test.log(LogStatus.INFO, "Entered a Mortgage Number ....");
		
		searchBtn.click();
		test.log(LogStatus.INFO, "Clicked on Forward Button");
		
		boolean prodSuccessMessage=isElementPresent(Constants.MORTGAGE_LABEL);
		if(prodSuccessMessage)	
		{
			
			accntNum.sendKeys(acntNum);
			inquireBtn.click();
			
			if(errLabel.isDisplayed())
			{
				reportPass("Navigated to MTG02 and Inquired for an account "+acntNum+" "+errLabel.getText());
			}
			else
			{
				reportFailure("Navigated to MTG02 Failed");
			}
		}
		else
		{
			
			reportFailure("MTG02 Validation - Inquired for the account and CMS is NOT populated");
		}		
		
	}
	
	/*public Object cusAccnt(String cusNum)
	{
		findFunctionTxtBx.sendKeys(cusNum);
		test.log(LogStatus.INFO, "Entered a Customer Number ....");
		
		searchBtn.click();
		test.log(LogStatus.INFO, "Clicked on Forward Button");
		
		
		boolean txnPage=isElementPresent(Constants.CUS_ACCNT_LBL);
		if(txnPage)	
		{
			UFSSMortgageAccountPage accntLinkedPage=new UFSSMortgageAccountPage(driver,test);
			PageFactory.initElements(driver, accntLinkedPage);
			reportPass("CUSTOMERS LINKED ACCOUNT Validated");
			return accntLinkedPage;	
			
		}
		else
		{
			
			UFSSCustomerAccntLinkPage accntPage=new UFSSCustomerAccntLinkPage(driver,test);
			PageFactory.initElements(driver, accntPage);
			reportFailure("Customer account not found");
			return accntPage;
			
		}
	}*/
}
