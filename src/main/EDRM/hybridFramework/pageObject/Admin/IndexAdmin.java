package main.EDRM.hybridFramework.pageObject.Admin;

import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.calender.CalenderHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class IndexAdmin extends TestBase{
			  
			private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			ActionHelper actionHelper;
			public ExcelReader xls;

			Logger log = LoggerHelper.getLogger(IndexAdmin.class);
			  
			@FindBy(how=How.XPATH,using="//input[@id='ColumnName']")
			public WebElement ColumnNameField;
			
			@FindBy(how=How.XPATH,using="//input[@id='Label']")
			public WebElement LabelField;
			  
			@FindBy(how=How.XPATH,using="//input[@id='Description']")
			public WebElement DescriptionField;		
			  
			@FindBy(how=How.XPATH,using="//input[@id='IsSearchable']")
			public WebElement OptimisedForSearchingChkbx;

			@FindAll({
				@FindBy(how=How.XPATH,using="//input[@id='FlexibleFieldType']"),
				@FindBy(how=How.XPATH,using="//div[@class='dropdown bootstrap-select disabled form-control dropup']")
			})	
		    public WebElement FieldTypeField;
			  
			@FindBy(how=How.XPATH,using="//input[@id='DefaultValue']")
			public WebElement DefaultValueField;		
				  
			@FindBy(how=How.XPATH,using="//input[@class='form-control spinedit noSelect']")
			public WebElement sizeField;	
				  
			@FindBy(how=How.XPATH,using="//span[@class='glyphicon glyphicon-chevron-down']")
			public WebElement sizeFieldDown;	
				  
			@FindBy(how=How.XPATH,using="//span[@class='glyphicon glyphicon-chevron-up']")
			public WebElement sizeFieldUp;	
			  
			@FindBy(how=How.XPATH,using="//input[@id='IsMandatory']")
			public WebElement IsMandatoryChkbx;					  
					
			public String tempDocument = "//li[@id=' category_${id}']//span[text()='%s']";
			  
 
	public IndexAdmin(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		actionHelper=new ActionHelper(driver);
		xls = new ExcelReader();
	}
	
	public void selectDocument (String documentName) {
		String docName = String.format(tempDocument, documentName);
		WebElement docNameXpath = driver.findElement(By.xpath(docName));
		boolean status = verificationHelper.isElementDisplayed(By.xpath(docName));
		if(status) {
			waitHelper.waitForElementClickable(driver, docNameXpath, 35).click();
		}
 	  }

	
	public void checkOptimisedForSearchingCheckBox() {
		Actions actions = new Actions(driver);
		actions.moveToElement(OptimisedForSearchingChkbx).build().perform();
		boolean OptimisedForSearchingChkbxStatus = verificationHelper.isElementSelected(OptimisedForSearchingChkbx);
		if(OptimisedForSearchingChkbxStatus == false) {
//			actions.click(OptimisedForSearchingChkbx).build().perform();
			click(OptimisedForSearchingChkbx, "---------->> Clicking on Optimised for searching checkbox");
			sleepFor(3000);
			}
	}
	
	public void uncheckOptimisedForSearchingCheckBox() {
		boolean OptimisedForSearchingChkbxStatus = verificationHelper.isElementSelected(OptimisedForSearchingChkbx);
		if(OptimisedForSearchingChkbxStatus == true) {
			OptimisedForSearchingChkbx.click();
			sleepFor(1000);
		}
	}	
}
