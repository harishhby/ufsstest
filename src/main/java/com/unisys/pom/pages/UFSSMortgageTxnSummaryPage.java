package com.unisys.pom.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.unisys.pom.base.BasePage;
import com.unisys.pom.util.Constants;

public class UFSSMortgageTxnSummaryPage extends BasePage{

	@FindBy(xpath=Constants.FIND_A_FUNCTION_TXTBX)
	public WebElement findFunctionTxtBx;
	
	@FindBy(xpath=Constants.SEARCH_BUTTON)
	public WebElement searchBtn;
	
	@FindBy(xpath=Constants.ACCOUNT_NUMBER_TXTFLD)
	public WebElement accntNum;
	
	@FindBy(xpath=Constants.INQUIRE_BTN)
	public WebElement inquireBtn;
	
	@FindBy(xpath=Constants.CURRENCY_LBL)
	public WebElement currency;
	
	@FindBy(xpath=Constants.TOTAL_ROWS)
	List<WebElement> totalRows;
	
	@FindBy(xpath=Constants.CUS_DETAILS)
	public WebElement cusVal;
	
	
	public UFSSMortgageTxnSummaryPage(WebDriver driver,ExtentTest test){
		super(driver,test);
	}

	public void txnCMSVal(String mrtgagenum) {
		
		findFunctionTxtBx.sendKeys(mrtgagenum);
		test.log(LogStatus.INFO, "Entered a Mortgage Number ....");
		
		searchBtn.click();
		test.log(LogStatus.INFO, "Clicked on Forward Button");
		
		
		
		boolean prodSuccessMessage=isElementPresent(Constants.TXN_SUMMARY_LBL);
		if(prodSuccessMessage)	
		{
			String cur=currency.getText().toString();
			List <WebElement> rows =totalRows;
			int row=rows.size();
			reportPass("Navigated to MTG11 - MTG11 CMS displayed is "+cur.replace("�", "\u00A3")+ " And there are "+row+" number of rows displayed");
		}
		else
		{
			
			reportFailure("Navigation to MTG11 Failed");
		}		
		
	}
	
	
	public Object cusAccntVal(String cusNum)
	{
		findFunctionTxtBx.sendKeys(cusNum);
		test.log(LogStatus.INFO, "Entered a Customer Number ....");
		
		searchBtn.click();
		test.log(LogStatus.INFO, "Clicked on Forward Button");
		
		
		boolean txnPage=isElementPresent(Constants.CUS_ACCNT_LBL);
		if(txnPage)	
		{
			String cusDetails=cusVal.getText();
			reportPass("CUS07 Validation - 1 Customer has been linked to the account. Customer details is "+cusDetails);
			
			UFSSMortgageAccountPage accntLinkedPage=new UFSSMortgageAccountPage(driver,test);
			PageFactory.initElements(driver, accntLinkedPage);
			return accntLinkedPage;	
				
			
		}
		else
		{
			reportFailure("Navigation to CUS07 failed");
			
			UFSSMortgageTxnSummaryPage accntPage=new UFSSMortgageTxnSummaryPage(driver,test);
			PageFactory.initElements(driver, accntPage);
			return accntPage;
			
			
		}
	}
	public Object cusAccnt()
	{
		
		boolean txnPage=isElementPresent(Constants.CUS_ACCNT_LBL);
		if(txnPage)	
		{
			
			UFSSMortgageAccountPage accntLinkedPage=new UFSSMortgageAccountPage(driver,test);
			PageFactory.initElements(driver, accntLinkedPage);
			return accntLinkedPage;	
			
		}
		else
		{
			
			UFSSMortgageTxnSummaryPage accntPage=new UFSSMortgageTxnSummaryPage(driver,test);
			PageFactory.initElements(driver, accntPage);
			return accntPage;
			
		}
	}
}
