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
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class DocumentTypes extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			AdminDocumentSection adminDoc;

			Logger log = LoggerHelper.getLogger(DocumentTypes.class);

			@FindBy(how=How.XPATH,using="//button[@data-id='GroupId']")
			public WebElement ddGroup;															//created by sagar on 24.07.2023

			@FindBy(how=How.XPATH,using="//table[@id='documentTypesTable']//td")
			public WebElement documentTypesTableEmpty;											//created by sagar on 24.07.2023

			@FindBy(how=How.XPATH,using="//button[@data-id='DocumentTypeId']")
			public WebElement documentTypeDD;													//created by sagar on 24.07.2023
	
          @FindAll({
        	@FindBy(how = How.XPATH,using="//*[@id='MainSection']//input[@aria-activedescendant='bs-select-4-0']"),
        	@FindBy(how = How.XPATH,using="//*[@id='MainSection']//input[@aria-autocomplete='list']")
        })
        public WebElement documentTypeTxt;													//created by sagar on 24.07.2023	

        @FindBy(how=How.XPATH,using="//table[@id='documentTypeGroupDataTable']//td")		//created by sagar on 24.07.2023
		public WebElement documentTypeGroupDataTableEmpty;           

 
        //created by sagar on 24.07.2023          
	public DocumentTypes(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		adminDoc = new AdminDocumentSection(driver); 
		navigationMenu = new NavigationMenu(driver);
	}
	
	//created by sagar on 24.07.2023
	public void selectGroup(String group,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			click(ddGroup, "Clickin on Drop down button");
			dropdownHelper.selectFromAutocompleDDWithoutInput(group, ddGroup, getEnvVariable);
		}else {
			dropdownHelper.selectFromAutocompleDDWithoutInput(group, ddGroup);
		}
	}
	
	//created by sagar on 24.07.2023
	public void selectDocumentType(String groupName,String getEnvVariable) {
		click(documentTypeDD, "Clicking on Drop down button");
		sendKeysWithoutClear(documentTypeTxt, groupName, "Entering document type");
		new ActionHelper(driver).pressEnter();
	} 
	
	
}
