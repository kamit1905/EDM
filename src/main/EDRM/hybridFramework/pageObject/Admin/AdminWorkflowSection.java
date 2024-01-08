package main.EDRM.hybridFramework.pageObject.Admin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.Ordering;

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
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;

public class AdminWorkflowSection extends TestBase{
		
	private WebDriver driver;
			WaitHelper waitHelper;
			AlertHelper alertHelper;
			NavigationMenu navigationMenu;
			DropdownHelper dropdownHelper;
			VerificationHelper verificationHelper;
			WindowHelper windowHelper;
			ActionHelper actionHelper;
			Logger log = LoggerHelper.getLogger(AdminWorkflowSection.class);
	
			@FindBy(how=How.XPATH,using="//input[@id='CampaignId']")
			public WebElement txtCampaignName;
			
			@FindBy(how=How.XPATH,using="//select[@id='AvailableFields']")
			public WebElement ddAvailableFieldsPriorityMng;
			
			@FindBy(how=How.XPATH,using="//select[@id='selectedFields']")
			public WebElement ddSelectedFieldsPriorityMng;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//a[@data-original-title='Add']"),
				@FindBy(how=How.XPATH,using="//a[@data-bs-original-title='Add']")
			})
			public WebElement lnkAddArrowPriorityMng;
			
			@FindBy(how=How.XPATH,using="//a[@data-original-title='Remove']")
			public WebElement lnkRemoveArrowPriorityMng;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//a[@data-original-title='RemoveAll']"),
				@FindBy(how=How.XPATH,using="//a[@data-bs-original-title='Remove All']")
			})
			public WebElement lnkRemoveAllArrowPriorityMng;
			
			@FindBy(how=How.XPATH,using="//input[@id='OrderSequenceAsc' and @value=0]")
			public WebElement rdoDescendingOrderPriorityMng;
			
			@FindBy(how=How.XPATH,using="//input[@id='OrderSequenceAsc' and @value=1]")
			public WebElement rdoAscendingOrderPriorityMng;		
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//button[@data-original-title='Add weighted value to selected field']"),
				@FindBy(how=How.XPATH,using="//button[@data-bs-original-title='Add weighted value to selected field']")
			})
			public WebElement btnAddWeightedValuePriorityMng;
			
			@FindBy(how=How.XPATH,using="//input[@id='ExcludeOthers']")
			public WebElement chkExcludeAllOthersPriorityMng;
			
			@FindBy(how=How.XPATH,using="//div[@class='modal-button']//button[@id='dialogButton2']")
			public WebElement btnNoDelete;
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div/button[@data-original-title='Select Campaign']"),
				@FindBy(how=How.XPATH,using="//div/button[@data-bs-original-title='Select Campaign']")
			})
			public WebElement btnCampaignIcon;	
			
			@FindBy(how=How.XPATH,using="//div[@id='campaign']//ul/li/a")
			public List<WebElement> lstCampaignOptions;	
			
			@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Date Received:')])[1]")
			public WebElement clmDateReceived;
			
			@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Document Type:')])[1]")
			public WebElement clmDocumentType;
			
			//public By btnCampaignDropdown = By.xpath("//div/button[@data-original-title='Select Campaign']");
			
			@FindAll({
				@FindBy(how=How.XPATH,using="//div/button[@data-original-title='Select Campaign']"),
				@FindBy(how=How.XPATH,using="//div/button[@data-bs-original-title='Select Campaign']"),
			})
			public WebElement btnCampaignDropdown;
			
			@FindBy(how=How.XPATH,using="//th[text()='Weighted Value']")
			public WebElement weightedColumnTable;
			
			@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Doc Ref:')])[1]")
			public WebElement docRefColumn;
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='Document Type']")
			public WebElement ddDocumentType;
			
			@FindBy(how = How.XPATH,using = "//td[contains(text(),'Overwrite all users level campaigns with team level campaign')]/ancestor::tr[1]/td[1]/input")
			public WebElement overwriteUsersCheckbox;
			
			@FindBy(how = How.XPATH,using = "//input[@id='TemplateName']")
			public WebElement templateNameTxt;			

			@FindBy(how = How.XPATH,using = "//input[@id='TemplateDescription']")
			public WebElement templateDescriptionTxt;		
			
			@FindBy(how = How.XPATH,using = "//input[@id='Comment']")
			public WebElement templateVersionCommentTxt;		

			@FindBy(how = How.XPATH,using = "//button[@data-id='RoutingColumn']")			//created by sagar on 29.07.2023
			public WebElement routingColumnDD;		
							
			@FindBy(how = How.XPATH,using = "//div[text()='STREET_SEARCH_KEY']")			//created by sagar on 29.07.2023
			public WebElement streetSearchKeyInRoutingColumnDD;		
			
			@FindBy(how = How.XPATH,using = "//input[@id='Comment']")			//created by sagar on 29.07.2023
			public WebElement versionComment;		
			
			@FindBy(how = How.XPATH,using = "//button[@data-id='MailRoutingRuleSet']")					//created by sagar on 29.07.2023
			public WebElement ruleSetDD;			
						
			@FindBy(how = How.XPATH,using = "//button[@data-id='Target']")								//created by sagar on 29.07.2023
			public WebElement roleDD;		
			
			@FindBy(how = How.XPATH,using = "//h4[@id='myModalLabel']/span[text()='Edit Allocation Step']")								//created by sagar on 29.07.2023
			public WebElement modalPopupTitle;
			
			@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Date Received:')])[1]")
			public WebElement targetDate;



			
			public String tmpLblTextbox  = "//label[text()='%s']/..//input",
					campaignTableId = "priorityManagerTable",
					tmpIconWeightedValues = "//button[@data-original-title='%s']";
			
			public String tmpNECDMIconWeightedValues = "//button[@data-bs-original-title='%s']";
			
	public AdminWorkflowSection(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu = new NavigationMenu(driver);
		dropdownHelper = new DropdownHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		windowHelper = new WindowHelper(driver);
		actionHelper=new ActionHelper(driver);
	}
	
	/**
	 * Add campaign name in workflow
	 * @param campaignName
	 */
	public void addCampaignName(String campaignName) {
		//refreshPage();
		sleepFor(1000);
		sendKeys(txtCampaignName, campaignName, "Entering campaign as "+campaignName);
	}
	
	/**
	 * Select from available fields in priority management
	 * @param fieldName field Name to be selected 
	 */
	public void selectAvailableFields(String fieldName) {
		WebElement element=driver.findElement(By.xpath("//select[@id='AvailableFields']//option[text()='"+fieldName+"']"));
		//new JavaScriptHelper(driver).scrollToElement(element);
		element.click();
		dropdownHelper.selectFromDropdown(ddAvailableFieldsPriorityMng, fieldName);
	}
	
	/**
	 * Select selected fields and click on field name
	 * @param fieldName field name which needs to be clicked
	 */
	public void selectSelectedFields(String fieldName) {
		actionHelper.moveToElementAndClick(ddSelectedFieldsPriorityMng.findElement(By.xpath("./option[text()='"+fieldName+"']")));
	}
	
	/**
	 * Select ascending order radio button in workflow form
	 */
	public void selectAscendingOrderRadioButton() {
		click(rdoAscendingOrderPriorityMng, "Clicking on ascending radio button");
	}
	
	/**
	 * Select descending order radio button in workflow form
	 */
	public void selectDescendingOrderRadioButton() {
		click(rdoDescendingOrderPriorityMng, "Clicking on descending radio button");
	}
	
	/**
	 * Click on add field of worklow form
	 */
	public void clickOnAddField() {
	    sleepFor(500);
		waitHelper.waitForElementClickable(driver, lnkAddArrowPriorityMng, 20);
		click(lnkAddArrowPriorityMng,"Clicking on add field link");	
		sleepFor(1000);
	}
	
	public void ClickOnRemoveAllField() {
	    sleepFor(500);
		waitHelper.waitForElementClickable(driver, lnkRemoveAllArrowPriorityMng, 20);
		click(lnkRemoveAllArrowPriorityMng,"Clicking on remove all field link");	
		sleepFor(1000);

	}
	
	/**
	 * Click on add weighted value to field of workflow form
	 * @param value value of weighted value
	 * @param label label of weighted value
	 * @param type e.g Text
	 */
	public void clickOnAddWeightedValueToField(String value, String label, String type) {
		click(btnAddWeightedValuePriorityMng, "Clicking on add weighted value button");
		windowHelper.waitForModalDialog("Select Value To Add");
		enterWeightedTextValue(value, label);
		actionHelper.pressTab();
		sleepFor(3000);
		windowHelper.clickOnModalFooterButton("Ok");
	}
	
	/**
	 * Enter weighted text value of workflow form
	 * @param value value of weighted field
	 * @param label label of weighted field
	 */
	public void enterWeightedTextValue(String value, String label) {
		String tmpEleXpath = String.format(tmpLblTextbox, label);
		WebElement ele = driver.findElement(By.xpath(tmpEleXpath));
		sendKeys(ele, value, "Entering value as "+value);
	}
	
	/**
	 * Get weighted text value of workflow
	 * @param label value of label which needs to be checked
	 * @return text value of label
	 */
	public String getWeightedTextValue(String label) {
		String tmpEleXpath = String.format(tmpLblTextbox, label);
		WebElement ele = driver.findElement(By.xpath(tmpEleXpath));
		return verificationHelper.getText(ele);
	}
	
	/**
	 * Click on edit weighted value of field
	 */
	public void clickOnEditWeightedValueToField(String environment) {
		clickOnWeightedValuesIcon("Edit weighted value of selected field",environment);
		windowHelper.waitForModalDialog("Edit Value");
	}
	
	/**
	 * Click on weighted values icon in workflow form
	 * @param iconTooltip 
	 */
	public void clickOnWeightedValuesIcon(String iconTooltip,String environment) {
		//if(!environment.equals("Enterprise_R540")) {
		if(environment.equals("Enterprise_Sp1s") ||environment.equals("Enterprise_R522") || environment.equals("Enterprise_R530") || environment.equals("Enterprise_R531") ) {
			String iconWeightedXpath = String.format(tmpIconWeightedValues, iconTooltip);
			WebElement iconWeightedValues = driver.findElement(By.xpath(iconWeightedXpath));
			click(iconWeightedValues, "Clicking on weighted values for "+iconTooltip);
		}else {
			String iconWeightedXpath = String.format(tmpNECDMIconWeightedValues, iconTooltip);
			WebElement iconWeightedValues = driver.findElement(By.xpath(iconWeightedXpath));
			click(iconWeightedValues, "Clicking on weighted values for "+iconTooltip);
		}
	}
	
	
	/**
	 * This method help in selecting selected fields
	 * @param fieldName This parameter name should be exactly same which is shown in selected fields
	 */
	public void clickOnSpecifiedSelectedField(String fieldName) {
		dropdownHelper.selectFromDropdown(ddSelectedFieldsPriorityMng, fieldName);
	}
	
	/**
	 * Check exclude all others priority manage checkbox
	 */
	public void checkExcludeAllOthers() {
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		click(chkExcludeAllOthersPriorityMng, "Checking exclude all others");
	}
	
	/**
	 * Get filtered campaign row text
	 * @return campagin name
	 */
	public String getFilteredCampaignRowText() {
		String campaignName =navigationMenu.getfilteredRowElement(campaignTableId).getAttribute("innerText");
		return campaignName;
	}
	
	/**
	 * Click on filtered campaign row text
	 */
	public void clickOnFilteredCampaignRowText() {
		navigationMenu.clickOnFilteredRow(campaignTableId);
	}
	
	/**
	 * Click ok on delete popups
	 */
	public void clickOkOnDeletePopup() {
		windowHelper.waitForPopup("Delete");
		windowHelper.clickOkOnPopup();
	}
	
	/**
	 * Click no on delete popup
	 */
	public void clickNoOnDeletePopup() {
		windowHelper.waitForPopup("Delete");
		click(btnNoDelete,"Clicking on No on delete popup");
	}
	
	/**
	 * Check whether campaign dropdown button shown or not
	 * @return true,false based on dropdown button visiblity
	 */
	public boolean isCampaignDropdownButtonShown() {
		//return verificationHelper.isElementDisplayed(btnCampaignDropdown);
		return verificationHelper.isElementDisplayedByEle(btnCampaignDropdown);
	}
	
	/**
	 * Click on campaign dropdown get text
	 * @return campaign dropdown options
	 */
	public List<String> clickOnCampaignDropdownGetText(String environmentVar) {
		if(!environmentVar.equals("Enterprise_Sp1s")) {
			click(btnCampaignIcon, "Clicking on campaign button");
		}
		/*
		 * try { Thread.sleep(2000); } catch (InterruptedException e) {
		 * log.info("Exception while waiting for campaign dropdown options "+e); }
		 */
		Iterator<WebElement> itr  = lstCampaignOptions.iterator();
		List <String>options=new ArrayList<String>();
		while(itr.hasNext()){
			 
			options.add(itr.next().getText());
			
		}
		log.info("Options are "+options);
		return options;
	}
	
	/**
	 * Get date received column values for all rows
	 * @return List of dates in received column values
	 * @throws ParseException
	 */
	public List<Date> getDateReceivedColumnValuesForAllRows() throws ParseException {
		String index = clmDateReceived.getAttribute("data-column-index");
		int actualIndex = Integer.parseInt(index)+1;
		//List<WebElement> dateEle = driver.findElements(By.xpath("//tr[contains(@role,'row')]/td["+actualIndex+"]"));
		List<WebElement> dateEle = driver.findElements(By.xpath("//tr[@class='odd' or @class='even']/td["+actualIndex+"]"));
		List<Date> dates = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		
		for(WebElement ele:dateEle) {
			dates.add( format.parse(ele.getText()));
		}
		log.info("Dates "+dates);
		return dates;
	}
	
	/**
	 * Check if dates are in descending order or not
	 * @param dates list of date values
	 * @return true,false based on descending values
	 */
	public Boolean checkIfDatesAreInDescedningOrder(List<Date> dates) {
		boolean descending = true;	
		for(int i=1; i<dates.size() && descending ;i++) {
				descending = descending && (dates.get(i).before(dates.get(0)) || dates.get(i).equals(dates.get(0))) ;
		}
			return descending;
	}
	
	/**
		 * Check if dates are in ascending order or not
	 * @param dates list of date values
	 * @return true,false based on ascending values
	 */
	public Boolean checkIfDatesAreInAscendingOrder(List<Date> dates) {
		boolean ascending = true;	
		for(int i=1; i<dates.size() && ascending ;i++) {
				ascending = ascending && (dates.get(i).after(dates.get(0)) || dates.get(i).equals(dates.get(0))) ;
		}
			return ascending;
	}
	
	/**
	 * Check whether All edit weighted popup button present
	 * @return true,false based on button presence
	 */
	public boolean isAllEditWeightedPopupButtonsPresent() {
		boolean buttonPresence = windowHelper.isModalFooterButtonDisplayed("Ok") &&
				windowHelper.isModalFooterButtonDisplayed("Advanced")&&
				windowHelper.isModalFooterButtonDisplayed("Cancel");
		return buttonPresence;
	}

	public List<String> getDocRefNamesforDocuments() {
		return null;
	}
	
	
	/*
	 * Added refresh code to check weather edit Icon of selected field is enable or not
	 */
	public void verifyEditIconForSelectedField() {
		//WebElement ele=driver.findElement(By.xpath("//th[text()='Weighted Value']"));
		boolean status=new VerificationHelper(driver).isElementDisplayedByEle(weightedColumnTable);
		
		if(status) {
			log.info("Weighted Element is presetn");
		}else {
			sleepFor(1000);
			refreshPage();
		}
	}
	
	
	/**
	 * Click on remove weighted value of field
	 */
	public void clickOnRemoveWeightedValueToField(String environment) {
		clickOnWeightedValuesIcon("Remove weighted value of selected field",environment);
	}
	
	
	/**
	 * Get doc ref column values for all rows
	 * 
	 *
	 */
	public List<String> getDocRefColumnValuesForAllRows() throws ParseException {
		String index = docRefColumn.getAttribute("data-column-index");
		int actualIndex = Integer.parseInt(index)+1;
		sleepFor(1000);
		List<WebElement> docrefEle = driver.findElements(By.xpath("//tr[contains(@role,'row')]/td["+actualIndex+"]"));
		List<String> docrefvalue = new ArrayList<>();
		
		for(WebElement ele:docrefEle) {
			docrefvalue.add(ele.getText());
		}
		return docrefvalue;
	}
	
	public void AddDocumentTypeWeightedValues(String docType) {
		click(btnAddWeightedValuePriorityMng, "Clicking on add weighted value button");
		new WindowHelper(driver).waitForModalDialog("Select Value To Add");
		sleepFor(1000);
		new DropdownHelper(driver).selectFromAutocompleDD(docType, ddDocumentType);
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
	}
	
	//created by sagar on 29.07.2023				//method is incomplete --> dont know how to complete
	public void addWorkflowTemplate(String TemplateNameTxt, String TemplateDescriptionTxt, String TemplateVersionCommentTxt, String teamName, String getEnvVariable)
	{
		TemplateDescriptionTxt = TemplateDescriptionTxt == null ? "" :TemplateDescriptionTxt;
		TemplateNameTxt = "Street_"+generateRandomNumber();
    	TemplateDescriptionTxt = "Route By Street Name";
    	TemplateVersionCommentTxt = "Initial Version1";	
    	
    	navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Workflow Templates Maintenance", "Workflow");
		sleepFor(2000);
		navigationMenu.clickOnIcon("Add a new workflow template", "Actions");
    	sendKeys(templateNameTxt, TemplateNameTxt, "Entering value as "+TemplateNameTxt);
    	sendKeys(templateDescriptionTxt, TemplateDescriptionTxt, "Entering value as "+TemplateDescriptionTxt);    	
    	sendKeys(templateVersionCommentTxt, TemplateVersionCommentTxt, "Entering value as "+TemplateVersionCommentTxt);
		sleepFor(2000);
		navigationMenu.clickOnIcon("Save changes made to workflow", "Actions");    	
	}
	
	//created by sagar on 29.07.2023
	public void selectRole (String Role,String getEnvVariable) 
	{
		new DropdownHelper(driver).selectFromAutocompleDD(Role, roleDD);
	}
	
	//created by sagar on 29.07.2023
	public void selectRuleSet (String RuleSet,String getEnvVariable) 
	{
		new DropdownHelper(driver).selectFromAutocompleDD(RuleSet, ruleSetDD);
	}
	
	//created by sagar on 29.07.2023
	public void editAllocationStep(String template, String Role, String RuleSet, String getEnvVariable) {
		navigationMenu.searchInFilter(template);
		sleepFor(1000);	
		
		new CapturePage(driver).clickOnFirstItemOfIntray();
		navigationMenu.clickOnIconMenu("Edit selected workflow template", "Actions");
		boolean status=new VerificationHelper(driver).isElementDisplayedByEle(navigationMenu.filterTxt);			//Adding because getting blank page on tags on click of save
	 	sleepFor(1000);
	 	if(!status) {
		 refreshPage();
	 	}
	 	navigationMenu.searchInFilter(template);
		sleepFor(1000);	
		new CapturePage(driver).clickOnFirstItemOfIntray();
		navigationMenu.clickOnIconMenu("Edit selected step", "Steps");
//		windowHelper.waitForModalFormDialog("Edit Allocation Step");
		waitHelper.waitForElementVisible(modalPopupTitle, 20, 1);
		AssertionHelper.verifyTrue(verificationHelper.isElementEnabled(streetSearchKeyInRoutingColumnDD), "The element street search key is not selected");
		selectRole(Role, getEnvVariable);
		selectRuleSet(RuleSet, getEnvVariable);
		sleepFor(1000);	
        new WindowHelper(driver).clickOnModalFooterButton("Save ");
		navigationMenu.waitForIcon("Save changes made to workflow");        
	}
	
	public void editWorkflowTemplateForRangeRouting(String template, String Role, String RuleSet, String versionComment, String getEnvVariable){
		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Workflow Templates Maintenance", "Workflow");
		sleepFor(2000);
		editAllocationStep(template, Role, RuleSet, getEnvVariable);
		sendKeys(templateVersionCommentTxt, versionComment, "Entering template version comment as "+versionComment);
		navigationMenu.clickOnIconMenu("Save changes made to workflow", "Actions");
		sleepFor(4000);
	    navigationMenu.waitForIcon("Add a new workflow template", "Actions");
	    
	    //verify workflow template updated for Range routing
	    getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIcon("Workflow Templates Maintenance", "Workflow");
		sleepFor(2000);
		navigationMenu.searchInFilter(template);
		sleepFor(2000);
		navigationMenu.clickOnFilteredRow("workflowTemplatesTable");
		sleepFor(2000);
		String ActualComment = navigationMenu.getColumnValueFromTable("Comment");
		AssertionHelper.verifyText(versionComment, ActualComment);
	}

}
