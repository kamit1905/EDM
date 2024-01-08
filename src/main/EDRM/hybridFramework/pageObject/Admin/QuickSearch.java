package main.EDRM.hybridFramework.pageObject.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class QuickSearch extends TestBase{
			  
			private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			ActionHelper actionHelper;
			public ExcelReader xls;

			Logger log = LoggerHelper.getLogger(QuickSearch.class);
			  
			@FindBy(how=How.XPATH,using="//button[@data-id='flexibleFields']")
			public WebElement documentdd;
			
			@FindBy(how=How.XPATH,using="//a[@data-title='Add new flexible field']")
			public WebElement btnDocumentAdd;
			
			@FindAll(@FindBy(how = How.XPATH, using = "//tr[@class='flexibleFieldOrder']//td[1]"))
			public List<WebElement> allDocuments;
			
			@FindAll(@FindBy(how = How.XPATH, using = "//div[@id='bs-select-2']//ul[@class='dropdown-menu inner show']/li/a//span[@class='text']"))
			public List<WebElement> allDocumentsFromSearch;
			
//			@FindAll({
//				@FindBy(how=How.XPATH,using="//input[@id='FlexibleFieldType']"),
//				@FindBy(how=How.XPATH,using="//div[@class='dropdown bootstrap-select disabled form-control dropup']")
//			})	
//		    public WebElement FieldTypeField;
			  
					
			public String tmpDocument = "//tr[@class='flexibleFieldOrder']//td[1][text()='%s']",
			tmpDeleteDocument = "//tr[@class='flexibleFieldOrder']//td[1][text()='%s']/following-sibling::td/a[1]",
			tmpMoveUpDocument = "//tr[@class='flexibleFieldOrder']//td[1][text()='%s']/following-sibling::td/a[2]",
			tmpMoveDownDocument = "//tr[@class='flexibleFieldOrder']//td[1][text()='%s']/following-sibling::td/a[3]";
 
	public QuickSearch(WebDriver driver) {
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
	
	public void deleteDocument (String documentName) {
		String docName = String.format(tmpDeleteDocument, documentName);
		WebElement docNameXpath = driver.findElement(By.xpath(docName));
		boolean status = verificationHelper.isElementDisplayed(By.xpath(docName));
		if(status) {
			waitHelper.waitForElementClickable(driver, docNameXpath, 35).click();
		}
 	  }
	
	public void moveUpDocument(String documentName) {
		String moveUp = String.format(tmpMoveUpDocument, documentName);
		WebElement moveUpXpath = driver.findElement(By.xpath(moveUp));
		click(moveUpXpath, "Clicking on move Up Icon for the Document " + documentName);
	}
	
	public void moveDownDocument(String documentName) {
		String moveDown = String.format(tmpMoveDownDocument, documentName);
		WebElement moveDownXpath = driver.findElement(By.xpath(moveDown));
		click(moveDownXpath, "Clicking on move Up Icon for the Document " + documentName);
	}	
	
	public void addDocument(List<String> DocumentName,String getEnvVariable) {
		  if(getEnvVariable.equals("Enterprise_R551")) {
			  for(int i=0; i<DocumentName.size();i++) {
				  click(documentdd, "Clicking on mail status drop down");
			  		dropdownHelper.selectFromAutocompleDDWithoutInput(DocumentName.get(i), documentdd,getEnvVariable);
			  		click(btnDocumentAdd, "Clicking on mail status add");
			  	} 
		  }else {
			  for(int i=0; i<DocumentName.size();i++) {
			  		dropdownHelper.selectFromAutocompleDDWithoutInput(DocumentName.get(i), documentdd);
			  		click(btnDocumentAdd, "Clicking on mail status add");
			  	}   
		  }
	  }
	
	public ArrayList<String> getListOfDocuments (){
//		ArrayList<String> listOfDocs = new ArrayList<String>();
		List<WebElement> list = allDocuments;
		ArrayList<String> all_list = new ArrayList<String>();
		sleepFor(2000);
		for(int i=0; i<list.size(); i++){
			all_list.add(list.get(i).getText());
//					getAttribute("data-value"));
		}
		return all_list;
	}
	
	public ArrayList<String> searchListOfDocuments() {
//		ArrayList<String> listOfDocs = new ArrayList<String>();
		List<WebElement> list = allDocumentsFromSearch;
		ArrayList<String> all_list = new ArrayList<String>();
		sleepFor(2000);
		for(int i=0; i<list.size()-1; i++){
			all_list.add(list.get(i).getText());
//					getAttribute("data-value"));
		}
		return all_list;
		//div[@id='bs-select-2']//ul[@class='dropdown-menu inner show']/li/a
	}
}
