package com.unisys.pom.util;

import java.util.Hashtable;

public class Constants {
	
	//LOCAL_SINGLE_BROWSER_RUN =true means local single browsers parallel on local 
	//GRID_RUN = false means local grid
	
	//GRID_RUN = true means remote grid
	
	
	public static final boolean LOCAL_SINGLE_BROWSER_RUN = true;
	public static final boolean GRID_RUN = false;
	
	//Environment
	public static final String ENV="UAT"; //PROD, UAT,SAT 
	
	// URLs-prod
	public static final String PROD_HOMEPAGE_URL = "http://ustr-erl-7965.na.uis.unisys.com:8080/selling-point-service/#/";
	public static final String PROD_USERNAME = "admin";
	public static final String PROD_PASSWORD = "admin";

	// URLs-uat
	public static final String UAT_HOMEPAGE_URL = "http://gb-regtstnl-v/UFSSUI_W21R1AUT/UFSSIspecs/UFSSGateway.aspx";
	public static final String UAT_USERNAME = "admin";
	public static final String UAT_PASSWORD = "admin";
	
	public static Hashtable<String,String> table;
	public static Hashtable<String,String> getEnvDetails(){
		if(table==null){
			table = new Hashtable<String,String>();
			if(ENV.equals("PROD")){
				table.put("url", PROD_HOMEPAGE_URL);
				table.put("username", PROD_USERNAME);
				table.put("password", PROD_PASSWORD);
			}else if(ENV.equals("UAT")){
				table.put("url", UAT_HOMEPAGE_URL);
				table.put("username", UAT_USERNAME);
				table.put("password", UAT_PASSWORD);
			}
		}
		return table;
	}

	//Paths
	public static final String IE_DRIVER_EXE=System.getProperty("user.dir")+"/drivers/IEDriverServer.exe";
	//public static final String IE_DRIVER_EXE_LINUX_Change=System.getProperty("user.dir")+"/drivers/IEDriverServer.exe";
	
	public static final String CHROME_DRIVER_EXE=System.getProperty("user.dir")+"/drivers/chromedriver.exe";
	public static final String CHROME_DRIVER_EXE_Linux = System.getProperty("user.dir")+"/drivers/chromedriver";
	
	public static final String FIREFOX_DRIVER_EXE=System.getProperty("user.dir")+"/drivers/geckodriver.exe";
	public static final String FIREFOX_DRIVER_EXE_Linux = System.getProperty("user.dir")+"/drivers/geckodriver";
	
	public static final String REPORTS_PATH = System.getProperty("user.dir")+"/Report/";
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"/data/Data.xlsx";
	public static final String TESTDATA_SHEET = "TestData";
	public static final String TESTCASES_SHEET = "TestCases";
	public static final String RUNMODE_COL = "Runmode";

	//================================Locators=====================================================
	
	//LoginPage
	public static final String LOGIN_USERNAME = "//*[contains(@id, 'Login_txtUserId')]";
	public static final String LOGIN_PASSWORD = "//*[contains(@id, 'Login_txtPassword')]";
	public static final String LOGIN_BUTTON = "//*[contains(@id, 'Login_BtnLogin')]";
	public static final String LAUNCHPAGE_LINK = "//*[@id='hlHomeIcon']";
	
	//LandingPage
	public static final String HOME_MENU_ICON = "//*[@id='hlHomeIcon']";
	public static final String SIGNOFF_LNK = "//*[contains(text(),'Sign Off')]";
	public static final String FIND_A_FUNCTION_TXTBX = "//*[contains(@id, 'NEXT_SCRN')]";
	public static final String SEARCH_BUTTON = "//*[contains(@id, 'FwdBtn')]";
	
	public static final String ERROR_LBL = "//div[contains(text(),'Error')]";
	
	//MortgageAccountDetailsPage
	public static final String MORTGAGE_LABEL = "//span[contains(text(),'MORTGAGE ACCOUNT DETAILS')]";
	public static final String ACCOUNT_NUMBER_TXTFLD = "//input[contains(@id,'EXT_DISP')]";
	public static final String INQUIRE_BTN = "//input[contains(@id,'enqBtn')]";
	public static final String TOTAL_CMS = "//input[contains(@id,'EXT_DISP')]";
	//public static final String TOTAL_CMS_AMOUNT = "//span[contains(@id,'CMS_TOTX')]";
	public static final String TOTAL_CMS_AMOUNT = "//span[contains(text(),'NO FURTHER RECORDS ARE AVAILABLE')]";	
	
	
	//Mortgage Transaction Summary Page
	public static final String TXN_SUMMARY_LBL = "//span[contains(text(),'MORTGAGE TRANSACTION SUMMARY')]";
	public static final String TXN_CMS_LBL = "//li[contains(text(),'CMS')]";
	public static final String CURRENCY_LBL = "//*[@formattype='Currency']";
	public static final String TOTAL_ROWS = "//*[contains(@id,'CopyFromGrid')]/tbody/tr";
	
	
	//CUSTOMERS LINKED TO AN ACCOUNT
	public static final String CUS_ACCNT_LBL = "//*[contains(text(),'CUSTOMERS LINKED TO AN ACCOUNT')]";
	public static final String CUS_DETAILS = "//*[@commandname='SEL_LINE__AT_00']";
	
	
	
	
	
	
	
	//================================Locators=====================================================

	
}
