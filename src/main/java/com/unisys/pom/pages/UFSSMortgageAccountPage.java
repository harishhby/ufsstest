package com.unisys.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.unisys.pom.base.BasePage;
import com.unisys.pom.util.Constants;

public class UFSSMortgageAccountPage extends BasePage{

	@FindBy(xpath=Constants.FIND_A_FUNCTION_TXTBX)
	public WebElement findFunctionTxtBx;
	
	@FindBy(xpath=Constants.SEARCH_BUTTON)
	public WebElement searchBtn;
	
	@FindBy(xpath=Constants.ACCOUNT_NUMBER_TXTFLD)
	public WebElement accntNum;
	
	@FindBy(xpath=Constants.INQUIRE_BTN)
	public WebElement inquireBtn;
	
	

	public UFSSMortgageAccountPage(WebDriver driver,ExtentTest test){
		super(driver,test);
	}

	public void inquireAccntNum(String acntNum) {
		
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
	
		public Object txnSummary(String mrtgagenum) {
		
		findFunctionTxtBx.sendKeys(mrtgagenum);
		test.log(LogStatus.INFO, "Entered a Mortgage Number ....");
		
		searchBtn.click();
		test.log(LogStatus.INFO, "Clicked on Forward Button");
		
		
		boolean txnPage=isElementPresent(Constants.TXN_SUMMARY_LBL);
		if(txnPage)	
		{
			UFSSMortgageTxnSummaryPage txnSummaryPage=new UFSSMortgageTxnSummaryPage(driver,test);
			PageFactory.initElements(driver, txnSummaryPage);
			return txnSummaryPage;	
			
		}
		else
		{
			
			UFSSMortgageAccountPage accntPage=new UFSSMortgageAccountPage(driver,test);
			PageFactory.initElements(driver, accntPage);
			return accntPage;
			
		}
	}
}
