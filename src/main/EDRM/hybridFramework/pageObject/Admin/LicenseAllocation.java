package main.EDRM.hybridFramework.pageObject.Admin;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class LicenseAllocation extends TestBase{
	
	private WebDriver driver;
	public CapturePage capture;

			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			LoginPage login;
			HomePage home;
			WindowHelper windowHelper;
			JavaScriptHelper jsHelper;
			Logger log = LoggerHelper.getLogger(LicenseAllocation.class);
	

						
            
            @FindBy(how = How.XPATH,using="//button[@data-id='LicenceTypeId']")
            public WebElement licenseTypeDD; 
					
			@FindBy(how=How.XPATH,using="//table[@id='bulkLicenceAllocationUserDataTable']//input[@class='SelectedLicenceOverride']")
			public WebElement bulkLicenceAllocationUserDataTableSelect;			
		
            
//            @FindBy(how = How.XPATH,using="//button[@data-button='DeselectAll']")
//            public WebElement deselectAll; 			
			
			
        public LicenseAllocation(WebDriver driver) {
		this.driver=driver;
		jsHelper = new JavaScriptHelper(driver);
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		login = new LoginPage(driver);
		home=new HomePage(driver);
		capture = new CapturePage(driver);

        }
	 
    	//created by sagar on 24.07.2023
    	public void licenceTypeDD (String licenseType) {
       		new DropdownHelper(driver).selectFromAutocompleDD(licenseType, licenseTypeDD);
    	}
    		
    		
    	//created by sagar on 24.07.2023
    	public void licenseAllocation(String userName, String licenseType) {
    		getApplicationUrl();
    		//sleepFor(1000);
    		navigationMenu.clickOnTab("Administration");
    		navigationMenu.clickOnIconMenu("Bulk Licence Allocation", "User");
    		navigationMenu.waitForIcon("Cancel changes");
    		
    		licenceTypeDD(licenseType);
    		sleepFor(1000);
//    		navigationMenu.clickOnIconMenu("DeselectAll", "Actions");
//    		deselectAll.click();
    		
    		navigationMenu.searchInFilter(userName);
    		sleepFor(1000);
    		navigationMenu.clickOnFilteredRow("bulkLicenceAllocationUserDataTable");  
//    		bulkLicenceAllocationUserDataTableSelect.click();
    		sleepFor(1000);
    		navigationMenu.clickOnIconMenu("Save Licence Allocation", "Actions");
    	}
    	
}
	