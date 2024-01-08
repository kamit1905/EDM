package main.EDRM.hybridFramework.pageObject.Bundle;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;

public class Bundling extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			CapturePage capture;
			WindowHelper windowHelper;
			ActionHelper actionHelper;
			Logger log = LoggerHelper.getLogger(Bundling.class);
				
			@FindBy(how=How.XPATH,using="//input[@data-val-required='The Name field is required.']")
			public WebElement bundleNameField;
			
			@FindBy(how=How.XPATH,using="//input[@data-val-required='The Is Shared field is required.']")
			public WebElement sharedWithOthersCheckbox;

			@FindBy(how=How.XPATH,using="(//button[@data-bs-original-title='Show Completed'])[1]")
			public WebElement showCompletedBundle;
		
//			@FindBy(how=How.XPATH,using="//table[@id='bundleListDataTable']")
//			public WebElement bundleListDataTableContents;	
	
			@FindBy(how=How.XPATH,using="(//th[@aria-controls='bundleDocumentResultDataTable'])[1]")
			public WebElement typeOfDocumentHeading;				
			
			@FindBy(how=How.XPATH,using="//ul[@id='InnerBundleList']")
			public WebElement innerBundleList;	
			
			
			
//			@FindBy(how=How.XPATH,using="(//table[@id='bundleDocumentResultDataTable']/tbody/tr/td[8])[1]")
//			public WebElement getDocRef1InBundle;
//			
//			@FindBy(how=How.XPATH,using="(//table[@id='bundleDocumentResultDataTable']/tbody/tr/td[8])[2]")
//			public WebElement getDocRef2InBundle;

			@FindBy(how=How.XPATH,using="//div[@class='navbar-header-title navbar-header-title-margin-bottom col-lg-8']")
			public WebElement viewerText;
			
			@FindBy(how=How.XPATH,using="//td[@class='dataTables_empty']")
			public WebElement verifyEmptyTableInTheBundle;
	
			@FindBy(how=How.XPATH,using="//button[@data-button='Collate']")
			public WebElement collateIcon;

//			Collate Include page number in footer options

			@FindBy(how=How.XPATH,using="//input[@id='IncludePageNoInFooter']")
			public WebElement collateIncludePageNumberInFooterCheckBox;
			
			@FindBy(how=How.XPATH,using="//input[@id='PageNoAlignmentLeft']")
			public WebElement collateIncludePageNumberInFooterLeftAlignmentRadioBtn;
			
			@FindBy(how=How.XPATH,using="//input[@id='PageNoAlignmentCenter']")
			public WebElement collateIncludePageNumberInFooterCenterAlignmentRadioBtn;
			
			@FindBy(how=How.XPATH,using="//input[@id='PageNoAlignmentRight']")
			public WebElement collateIncludePageNumberInFooterRightAlignmentRadioBtn;
			
			
//			Collate Include document date in footer
			@FindBy(how=How.XPATH,using="//input[@id='IncludeDocumentDateInFooter']")
			public WebElement collateIncludeDocumentDateInFooterCheckBox;
			
			@FindBy(how=How.XPATH,using="//input[@id='DocumentDateAlignmentLeft']")
			public WebElement collateIncludeDocumentDateInFooterLeftAlignmentRadioBtn;
			
			@FindBy(how=How.XPATH,using="//input[@id='DocumentDateAlignmentCenter']")
			public WebElement collateIncludeDocumentDateInFooterCenterAlignmentRadioBtn;
			
			@FindBy(how=How.XPATH,using="//input[@id='DocumentDateAlignmentRight']")
			public WebElement collateIncludeDocumentDateInFooterRightAlignmentRadioBtn;
			
			
			@FindBy(how=How.XPATH,using="//h3[@id='dialogTitle']")
			public WebElement collagePopupTitle;
			   	

			@FindBy(how=How.XPATH,using="//table[@id='searchResultDataTable']/tbody/tr[1]/td[10]")
			public WebElement fileTypeInsearchResultDataTable;			
			
			@FindBy(how=How.XPATH,using="//table[@id='userDataTable']/tbody/tr[1]/td[1]")
			public WebElement userNameInUserDataTable;
			
			@FindBy(how=How.XPATH,using="//button[@data-icon='AddToBundle']")
			public WebElement AddToBundle;
			
			@FindBy(how=How.XPATH,using="//input[@id='BundleListName']")
			public WebElement AddNameToBundle;
			
			@FindBy(how=How.XPATH,using="//button[@id='collateBundleOk']")
			public WebElement collateBundleOkBtn;			
			
//			@FindAll({
//				@FindBy(how=How.XPATH,using="//div[@class='modal fade in']//div[@style='']//input[@name='Required']"),
//				@FindBy(how=How.XPATH,using="//div[@class='modal fade show']//div[@style='']//input[@name='Required']")
//			})
//			public WebElement txtRequiredDocEditTemplate;
			
			public String innerBundleList1 = "//ul[@id='Bundlelist']//a[text()=%s]";
			
	public Bundling(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		alertHelper=new AlertHelper(driver);
		actionHelper= new ActionHelper(driver);
		capture= new CapturePage(driver);
	}

	public void addNewBundle(String bundleName) {
		navigationMenu.clickOnIcon("Bundles", "Search");
	    navigationMenu.clickOnAddIcon();
	    sleepFor(1000);	
		sendKeys(bundleNameField, bundleName, "Entering bundle name");
		navigationMenu.clickOnIcon("Save changes made to bundle list", "Actions");
	}
	
	public Boolean isShowCompletedBundleButtonPresent() {
		return waitHelper.isElementDisplayedByEle(showCompletedBundle);
	}
	
	public void clickOnInnerBundleFilteredList()
	{
		actionHelper.moveToElementAndClick(innerBundleList);
	}

	public void clickOnInnerBundleFilteredList(String iconTooltip)
	{
		String clickOnInnerBundleFilteredListXpath = String.format(innerBundleList1, iconTooltip);
		WebElement clickOnInnerBundleFilteredList1 = driver.findElement(By.xpath(clickOnInnerBundleFilteredListXpath));
		actionHelper.moveToElementAndClick(clickOnInnerBundleFilteredList1);
	}
	
	public void addDocumentToBundle (String bundleName, String docRef)
	{
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Doc Ref",docRef);
		capture.clickOnDocumentListBtn();
		capture.clickOnFirstItemOfIntray();
	    clickOnAddToBundle();
	    
		sendKeys(AddNameToBundle, bundleName, "Entering bundle name");
		clickOnInnerBundleFilteredList();
//		clickOnInnerBundleFilteredList(bundleName);
		
	}
	
	public void removeTheItemOrDocumentFromTheBundle(String docRef1)
	{
		navigationMenu.searchInFilter(docRef1);
		sleepFor(3000);
		navigationMenu.clickOnFilteredRow("bundleDocumentResultDataTable");
		sleepFor(2000);
		navigationMenu.clickOnIcon("Remove item from document list", "Action");	
		new AlertHelper(driver).acceptAlertIfPresent();
		sleepFor(1000);

	}

	public void collateIncludePageNumberInFooter(String alignmentName) {								// created by sagar to handle page no in footer
//		waitHelper.waitForElementVisible(collateIncludePageNumberInFooterCheckBox, 35, 1);
		try {
		waitHelper.waitForElementClickable(driver, collateIncludePageNumberInFooterCheckBox, 20);
		log.info("Clicking on page no in footer checkBox");
		new ActionHelper(driver).moveToElementAndClick(collateIncludePageNumberInFooterCheckBox);
		}
		catch (Exception e) {
			log.info("Exception while clicking on page no in footer checkBox");
		}
		driver.findElement(By.xpath("//input[@id='PageNoAlignment"+alignmentName+"']")).click();
	}
	
	public void collateIncludeDocumentDateInFooter(String alignmentName) {								// created by sagar to handle document date in footer
//		waitHelper.waitForElementVisible(collateIncludeDocumentDateInFooterCheckBox, 35, 1);
		try {
		waitHelper.waitForElementClickable(driver, collateIncludeDocumentDateInFooterCheckBox, 20);
		log.info("Clicking on document date in footer checkBox");
		new ActionHelper(driver).moveToElementAndClick(collateIncludeDocumentDateInFooterCheckBox);
		}
		catch (Exception e) {
			log.info("Exception while clicking on document date in footer checkBox");
		}
		driver.findElement(By.xpath("//input[@id='DocumentDateAlignment"+alignmentName+"']")).click();
	}

	public void handleCollatePopup()
	{
		 waitHelper.waitForElementVisible(collagePopupTitle, 20, 1);		
		if(new WindowHelper(driver).isPopupDisplayed())
		{
			new NavigationMenu(driver).clickOkOnPopup();
		}
		
	}
		public void checkCollateStatus()
		{
			String status = driver.findElement(By.xpath("//table[@id='bundleListDataTable']/tbody/tr[1]/td[3]")).getText();
			AssertionHelper.verifyTextContains(status, "Complete");

		}


		public void verifyfileTypeInsearchResultDataTable()
		{
			String status = fileTypeInsearchResultDataTable.getText();
			AssertionHelper.verifyTextContains(status, ".pdf");

		}
		
		public void clickOnAddToBundle()
		{
			click(AddToBundle,"Clicking on AddToBundle");

		}		 

		public void clickOncollateBundleOkBtn()
		{
			click(collateBundleOkBtn,"Clicking on collate bundle ok button");

		}			
}
