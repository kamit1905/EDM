package main.EDRM.hybridFramework.pageObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
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
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;

public class NavigationMenu extends TestBase{

	
		private WebDriver driver;
		WaitHelper waitHelper;
		private Logger log = LoggerHelper.getLogger(NavigationMenu.class);
		//Popup box 
	    @FindBy(how=How.ID,using="dialogTitle")
		public WebElement dialogTitleId;
		
		@FindBy(how=How.ID,using="dialogMessage")
		public WebElement dialogMessageId;		
		
		@FindBy(how=How.XPATH,using="//button[@id='dialogButton1']")
		public WebElement dialogOkBtn;
		
		@FindBy(how=How.XPATH,using="//*[@class='modal fade in']//button[text()='Ok']")
		public WebElement modalOkBtn;
		
		@FindBy(how=How.XPATH,using="//*[@class='modal-dialog-busy']")
		public WebElement busyDialogIcon ;
		
		@FindBy(how=How.XPATH,using="//a[@data-original-title='Information@Work Enterprise Home']")
		public WebElement homePageIcon ;
		
		@FindBy(how=How.XPATH,using="//li[@id='Capture']")
		//@FindBy(how=How.XPATH,using="//li[@id='Capture']//a[text()='Capture']")		For R540
		public WebElement captureTab;
		
		@FindBy(how=How.XPATH,using="//button[@data-button='Add']")
		public WebElement addIconBtn;
		
		@FindBy(how=How.XPATH,using="//button[@data-button='Edit']")
		public WebElement editIconBtn;
		
		@FindBy(how=How.XPATH,using="//button[@data-button='Cancel']")
		public WebElement btnCancelIcon;
		
		@FindBy(how=How.XPATH,using="//button[@data-button='Save']")
		public WebElement saveIconBtn;
		
		//@FindBy(how=How.XPATH,using="//p[@class='navbar-text ellipsisWrap']")
		@FindBy(how=How.XPATH,using="//p[contains(@class,'navbar-text')]")				//Changed in R550 & it works for other versions also
		public WebElement navBarTitleLbl;
				
		//@FindBy(how=How.XPATH ,using="//input[@type='search']")
		@FindBy(how=How.XPATH ,using="//label[text()='Filter:']/input[@type='search']")      
		public WebElement  filterTxt ;
				@FindBy(how=How.XPATH ,using="(//label[text()='Filter:']/input[@type='search'])[2]")  
        public WebElement documentActionFilterSearch;
		
		@FindBy(how=How.XPATH ,using="(//label[text()='Filter:']/input[@type='search'])[3]")  
        public WebElement intrayActionFilterSearch;		

		@FindBy(how=How.XPATH ,using="//label[text()='Filter:']/input[@type='search']")  
        public WebElement documentAuditFilterSearch;		

		@FindBy(how=How.XPATH ,using="(//label[text()='Filter:']/input[@type='search'])[2]")  
        public WebElement intrayAuditFilterSearch;		

		@FindBy(how=How.XPATH ,using="//input[@type='search' and contains(@aria-controls,'SubGrid')]")
        public WebElement  txtFilterSubgrid;
		
		//input[@type='search' and @aria-controls='searchResultDataTable']
		@FindBy(how=How.XPATH ,using="//input[@type='search' and contains(@aria-controls,'searchResult')]")
        public WebElement  filterTxtSearchResult ;
         
		     
		@FindBy(how=How.XPATH ,using="//input[@type='search' and @aria-controls='userDataTable']")
        public WebElement  filterTxtUsers ;
		  
		@FindBy(how=How.XPATH ,using="//button[@data-button='Delete']")
		public WebElement  btnDeleteIcon ;
		
		@FindAll({
			@FindBy(how=How.XPATH,using="//a[@title='Menu']"),
			@FindBy(how=How.XPATH,using="//button[@title='Menu']")			//for R540 changed by amit
		})				
		//@FindBy(how=How.XPATH,using="//a[@title='Menu']")
		public WebElement gearIconBtn;
		
		@FindAll({
			@FindBy(how=How.XPATH,using="//li[@title='Column Configuration']"),
			@FindBy(how=How.XPATH,using="//a[@title='Column Configuration']")		//For R540	changed by amit	
		})	
		//@FindBy(how=How.XPATH,using="//li[@title='Column Configuration']")
		public WebElement columnConfiglnk;
		
		@FindBy(how=How.XPATH,using="//*[@class='modal-title' and text()='Columns Configuration']")
		public WebElement columnConfigPopupTitle;
		
		@FindBy(how=How.XPATH,using="//li[@title='Mail Status']/div[2]")
		public WebElement columnConfigMailStatusViewBtn;
		
		@FindBy(how=How.XPATH,using="//*[@id='columnConfigurationModal']//button[text()='Ok']")
		public WebElement columnConfigOkbtn;
		
		@FindBy(how=How.XPATH,using="//li[@title='Change to Multiple']/a")
		public WebElement lnkChangeToMultiple;
		
		@FindBy(how=How.XPATH,using="(//table//th[starts-with(@aria-label,'Mail Status:')])[1]")
		public WebElement mailStatusColumn;
		
		@FindBy(how=How.XPATH,using="//ol/li/a")
		public WebElement lnkbreadCrumb;
		
		@FindBy(how=How.XPATH,using="//div[@class='dt-buttons btn-group']/a")
		public WebElement lnkGearIconTable;
		
		@FindBy(how=How.XPATH,using="//ul[@class='dt-button-collection dropdown-menu']")
		public WebElement lstTableMenu;
		
		@FindBy(how=How.XPATH,using="(//table//th[2])[1]")
		public WebElement FirstColumnEle;
		
		@FindBy(how=How.XPATH,using="(//tr[contains(@class,'odd')])[1]")
        public WebElement intrayItemRow;
        
        @FindBy(how=How.XPATH,using="(//tr[contains(@class,'odd')]/td[3])[1]")
        public WebElement intrayItemRowCell;														 						
        @FindBy(how=How.XPATH,using="//li[@title='Select All']/a")
        public WebElement lnkSelectAll;     
        
		@FindBy(how = How.XPATH,using = "//div[@id='pageBusy']//img[@alt='Busy']")			//This will be helpful on rereference
		public WebElement busyDialogue;
		
		@FindBy(how = How.XPATH,using = "//input[@id='multiSelectCheckbox']")
		public WebElement multiSelectCheckbox;
		
		@FindBy(how = How.XPATH,using = "//div[@data-group='Document']/div/table/tbody//div[@data-bs-original-title='Collapse' and @class='more-button fa fa-chevron-left']")
		public WebElement docuemntGrpCollapseBtn;
		
		@FindBy(how = How.XPATH,using = "//span[text()='Doc Ref 2']")
		public WebElement docRef2;
		
		@FindBy(how = How.XPATH,using = "//input[@id='IsQuickSearch']")
		public WebElement quickSearchCheckbox;
		
		@FindBy(how = How.XPATH,using = "//button[@data-id='flexibleFields']")
		public WebElement flexibleFieldDD;
		
		@FindBy(how = How.XPATH,using = "//a[@data-icon='Add']")
		public WebElement addFlexibleBtn;
		
		
		public String expandIconBtnXpath ="(//div[@data-group='%s']//div[starts-with(@class,'more-button')])[2]",
				collapseIconBtnXpath = "(//div[@data-group='%s']//div[starts-with(@class,'more-button')])[1]",																				  
		 		tmpTabXpath = "//li[@id='%s']/a",
				
		 		tmpIconXpath = "//td//button[contains(@data-original-title,'%s')]",			//replace / with //
				
		 		tmpIconXpathAfter540 = "//td//button[contains(@data-bs-original-title,'%s')]",			//For R540
				
				tmpHiddenIconXpath = "//td/button[contains(@newtitlename,'%s')]",
				
				tmpMenuIconXpath = "//div//button[contains(@data-original-title,'%s')]",				//replace / with //
				
				tmpMenuIconXpathAfter540 = "//div//button[contains(@data-bs-original-title,'%s')]",			//For R540
				
				tmpFilteredRowXpath = "//table[@id='%s']/tbody/tr[1]/td[1]",
				tmpFilteredRowLnkXpath="//table[@id='%s']/tbody/tr[1]/td[1]/a",
				
				tmpIconSubMenuXpath = "//ul/li/a[contains(@data-original-title,'%s')]", //Changed since didn't work in Lives active doc types
				
				tmpIconSubMenuXpathAfter540 = "//ul/li/a[contains(@data-bs-original-title,'%s')]",			//For R540
				
				tmpIconSubMenuXpathusingText="//div[@class='btn-group show']//ul/li/a[text()='%s']",																																																				   
				tmpIconSubMenuOldXpath = "//ul[contains(@style,'display: block;')]/li/a[@data-original-title='%s']",
				tmpNoRecordsRowXpath = "//table[@id='%s']/tbody/tr/td",
				tmpBtnGroupHeaderSectionXpath = "//div[@data-group='%s']/div[@class='group-header' and @style='display: block;']",
				tmpLinkGroupHeaderSectionXpath = "(//div[@data-group='%s']/div[@class='group-header']/following-sibling::div/div/button)[last()]", 
				tmpLinkGroupIconXpath ="//div[@data-group='%1$s']/div[@class='group-header']/following-sibling::div/ul/li/button[contains(@data-original-title,'%2$s')]",
				tmpLinkGroupIconXpath1 ="//div[@data-group='%1$s']/div[@class='group-header']/ancestor::div[1]//button[@data-bs-original-title='%2$s']",
				//Accordion which can be expanded
				tmpBtnAccordionXpath = "//button[contains(@class,'accordion') and text()='%s']",
				//Nav tab heading
				tmpNavTabXpath = "//ul[contains(@class,'nav-tabs')]/li/a[text()='%s']",
				//Heading of table (sometimes table has id)
				tmpHeadingTableEle = "//table[@data-configuration-name='%1$s']/thead/tr/th/div[text()='%2$s']/..",
				//tmpRowElement = "(//tr[@role='row']/td[1])[%s]",
				tmpRowElement = "//tr[@role='row']/ancestor::table[1]/tbody/tr[%s]",																																
				tmpTableCellEle="//table[@id='%1$s']/tbody//tr[%2$s]/td[%3$s]",
				//tmpTableColumnEle = "(//table//th[starts-with(@aria-label,'%s:')])[1]",
					tmpTableColumnEle = "//table//th[starts-with(@aria-label,'%s:')][1]",
				//Accordion section
				tmpBtnAccordionSection="//button[contains(@class,'accordion') and text()='%s']",
				tmpBtnDataIconXpath = "//*[@data-button='%s']";
		String navigationBarTitleLabel = "//p[text()='Add %s']";
		String navigationBarTitle = "//p[text()='%s']";
				
		String tmpRowElementInR541 = "//tr[@class='even' or @class='odd']/ancestor::table[1]/tbody/tr[%s]";
		
		private String envType="NECDM_Lives";					//Till 531 Lives
	
		private String tmpTableCellEleByClassName="(//table[@class='%1$s']/tbody//tr[%2$s]/td[%3$s])[1]";		//created by sagar on 12.07.2023
				
				
		public NavigationMenu(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		TestBase.logExtentReport("Navigation menu Object Created");
	}

	
	public void clickOnHomePageIcon() {
		try {
		click(homePageIcon,"Clicking on home page icon");
		 }
		catch(Exception f){
			try {
			if(new AlertHelper(driver).isAlertPresent() != null) { new AlertHelper(driver).acceptAlert();}
			click(homePageIcon,"Clicking on home page icon");
			}
	     catch (Exception e) {
	        log.info("Exception while Clicking on home page icon");
	       }
		 }
	  }
	
		public CapturePage clickOnCaptureTab() {
		waitHelper.waitForElement(captureTab,10);
		click(captureTab, "Clicking on capture tab");
		//new JavaScriptHelper(driver).clickElement(captureTab);
		return new CapturePage(driver);
	}
	
	
	public void clickOnMenuButtonAtTop(WebElement iconElement, WebElement listElement) {
		log.info("Clicking on menu at top for ");
		if(waitHelper.isElementDisplayedByEle(iconElement)) {
			click(iconElement,"Clicking on icon from navigation "+iconElement.getText());
		}
		else {
			listElement.findElement(By.xpath("./ancestor::div[contains(@class,'btn-group')]/div/button")).click();
			click(listElement, "Clicking on listElment of "+listElement.getText());
		}
	}
	
	/**
	 * 
	 * @param tabName :  Specify the tab name with first letter in Uppercase e.g Search
	 */
	public void clickOnTab(String tabName) {
		String fnlTabXpath = String.format(tmpTabXpath , tabName);
		try {
		click(driver.findElement(By.xpath(fnlTabXpath)),"Clicking on tab "+tabName);
		}
		catch (UnhandledAlertException e) {
			//Unhandeled alert exception
			new AlertHelper(driver).acceptAlertIfPresent();
			click(driver.findElement(By.xpath(fnlTabXpath)),"Clicking on tab "+tabName);
		}
		}
		
	
	public void clickOnIcon(String iconTooltip,String Section) {
		sleepFor(500);
		if(envType.equals("Lives")) {
		String fnlIconXpath = String.format(tmpIconXpath , iconTooltip);
		WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
		try {
		waitHelper.waitForElementClickable(driver, iconElment, 5);
		}
		catch (Exception e) {
			expandNavigationMenu(Section);
		}
		waitHelper.waitForElementClickable(driver, iconElment, 5);
		click(iconElment,"Clicking on icon "+iconTooltip);
		}else if(envType.equals("NECDM_Lives")) {
			
			boolean status = new VerificationHelper(driver).isElementDisplayedByEle(docuemntGrpCollapseBtn);
			
			if(status) {
				click(docuemntGrpCollapseBtn, "Clicking on collapse button");
				sleepFor(3000);
				String fnlIconXpath = String.format(tmpIconXpathAfter540 , iconTooltip);
				WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
				try {
				waitHelper.waitForElementClickable(driver, iconElment, 5);
				}
				catch (Exception e) {
					expandNavigationMenu(Section);
				}
				waitHelper.waitForElementClickable(driver, iconElment, 5);
				click(iconElment,"Clicking on icon "+iconTooltip);
			}else {
				String fnlIconXpath = String.format(tmpIconXpathAfter540 , iconTooltip);
				WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
				try {
				waitHelper.waitForElementClickable(driver, iconElment, 5);
				}
				catch (Exception e) {
					expandNavigationMenu(Section);
				}
				waitHelper.waitForElementClickable(driver, iconElment, 5);
				click(iconElment,"Clicking on icon "+iconTooltip);
			}
		
		}
		else {
			
			if(Section.matches("Actions|Search")) 
				{
					try {
					String fnlIconXpath = String.format(tmpIconXpath , iconTooltip);
					WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
					click(waitHelper.waitForElementClickable(driver, iconElment, 20),"Clicking on icon element "+iconTooltip);
				}
				catch (Exception e) {
					WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, Section)));
				click(lnkGroupElement, "Clicking On SectionLink");
				WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,Section,iconTooltip)));
				click(waitHelper.waitForElementClickable(driver, iconElement, 20),"Clicking on icon element");
				}
			}
				
		 else {
			 By BtnGroupElementXpath = By.xpath(String.format(tmpBtnGroupHeaderSectionXpath, Section));
			if(new VerificationHelper(driver).isElementDisplayed(BtnGroupElementXpath)) {
				String fnlIconXpath = String.format(tmpIconXpath , iconTooltip);
				log.info("Final xpath in click On Icon"+fnlIconXpath);
				WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
				waitHelper.waitForElementClickable(driver, iconElment, 5);
				click(iconElment,"Clicking on icon "+iconTooltip);
			}
			else {
				WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, Section)));
				click(lnkGroupElement, "Clicking On SectionLink");
				WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,Section,iconTooltip)));
				click(waitHelper.waitForElementClickable(driver, iconElement, 20),"Clicking on icon element");
			}
		 }
		 }
		}
	
		

	
	/**
	 * Used for 5.22 release icon menu like document type Active 
	 * @param iconTooltip
	 * @param Section
	 */
	public void clickOnIconMenu(String iconTooltip,String Section) {
		sleepFor(500);
		if(envType.equals("Lives")) {
		String fnlIconXpath = String.format(tmpMenuIconXpath , iconTooltip);
		WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
		try {
		waitHelper.waitForElementClickable(driver, iconElment, 5);
		}
		catch (Exception e) {
			expandNavigationMenu(Section);
		}
		waitHelper.waitForElementClickable(driver, iconElment, 5);
		new ActionHelper(driver).moveToElementAndClick(iconElment);
		}else if(envType.equals("NECDM_Lives")){
			String fnlIconXpath = String.format(tmpMenuIconXpathAfter540 , iconTooltip);
			WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
			try {
			waitHelper.waitForElementClickable(driver, iconElment, 5);
			}
			catch (Exception e) {
				expandNavigationMenu(Section);
			}
			waitHelper.waitForElementClickable(driver, iconElment, 5);
			new ActionHelper(driver).moveToElementAndClick(iconElment);
		}
		
		else  {
			try {
				String fnlIconXpath = String.format(tmpMenuIconXpath , iconTooltip);
				WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
				click(waitHelper.waitForElementClickable(driver, iconElment, 5),"Clicking on menu of "+iconTooltip);
			}
			catch (Exception e) {
				WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, Section)));
				click(lnkGroupElement, "Clicking On SectionLink");
				WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,Section,iconTooltip)));
				click(waitHelper.waitForElementClickable(driver, iconElement, 20),"Clicking on icon element");
			}	
		}
		sleepFor(500);
		}
		
	
	/**
	 * Select from options in menu
	 * @param menuTitle - option value
	 */
	public void clickOnIconMenu(String menuTitle) {
		sleepFor(500);
		if(envType.equals("Lives")) {
			String fnlMenuXpath = String.format(tmpIconSubMenuXpath , menuTitle);
			WebElement menuElement=driver.findElement(By.xpath(fnlMenuXpath));
			waitHelper.waitForElementClickable(driver, menuElement, 10);
			new ActionHelper(driver).moveToElementAndClick(menuElement);
			//click(menuElement,"Clicking on icon "+menuElement);				//Commented because we are using move and click and again doing click thats why getting statle element
			//new JavaScriptHelper(driver).clickElement(menuElement);           //added by amit
		}else if(envType.equals("NECDM_Lives")) {
			try {						//Added try catch because tmpIconSubMenuXpath is not select other file system loc is not changing 
				String fnlMenuXpath = String.format(tmpIconSubMenuXpathAfter540 , menuTitle);
				WebElement menuElement=driver.findElement(By.xpath(fnlMenuXpath));
				waitHelper.waitForElementClickable(driver, menuElement, 10);
				new ActionHelper(driver).moveToElementAndClick(menuElement);
			} catch (Exception e) {
				String fnlMenuXpath = String.format(tmpIconSubMenuXpath , menuTitle);
				WebElement menuElement=driver.findElement(By.xpath(fnlMenuXpath));
				waitHelper.waitForElementClickable(driver, menuElement, 10);
				new ActionHelper(driver).moveToElementAndClick(menuElement);
			}
		}
		
		else  {
		try {
			String fnlMenuXpath = String.format(tmpIconSubMenuXpath , menuTitle);
			WebElement menuElement=driver.findElement(By.xpath(fnlMenuXpath));
			waitHelper.waitForElementClickable(driver, menuElement, 10);
			//new ActionHelper(driver).moveToElementAndClick(menuElement);
			click(menuElement,"Clicking on icon "+menuElement);	//Changed by amit
			}
			catch (Exception e) {
			log.info("Exception caught while icon menu click "+menuTitle+" Exception is "+e);
			String fnlMenuOldXpath= String.format(tmpIconSubMenuOldXpath, menuTitle);
			WebElement menuElement=driver.findElement(By.xpath(fnlMenuOldXpath));
			waitHelper.waitForElementClickable(driver, menuElement, 5);
			//new ActionHelper(driver).moveToElementAndClick(menuElement);
			click(menuElement,"Clicking on icon "+menuElement);	
			}
				
		}	
	}
	
	
	
	
	public boolean isIconEnabled(String iconTooltip,String Section) {
		if(envType.equals("Lives")) {
		try {
			expandNavigationMenu(Section);
			String fnlIconXpath = String.format(tmpHiddenIconXpath , iconTooltip);
			WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
			waitHelper.waitForElement(iconElment, 5);
			return new VerificationHelper(driver).isElementEnabled(iconElment);
			}
		catch (Exception e) {
			String fnlIconXpath = String.format(tmpIconXpath , iconTooltip);
			WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
			return new VerificationHelper(driver).isElementEnabled(iconElment);
			}
		}else if(envType.equals("NECDM_Lives")) {
			try {
				expandNavigationMenu(Section);
				String fnlIconXpath = String.format(tmpHiddenIconXpath , iconTooltip);
				WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
				waitHelper.waitForElement(iconElment, 5);
				return new VerificationHelper(driver).isElementEnabled(iconElment);
				}
			catch (Exception e) {
				String fnlIconXpath = String.format(tmpIconXpathAfter540 , iconTooltip);
				WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
				return new VerificationHelper(driver).isElementEnabled(iconElment);
				}
		}
		else {
			try {
				String fnlIconXpath = String.format(tmpIconXpath , iconTooltip);
				log.info("Final xpath in click On Icon"+fnlIconXpath);
				WebElement iconElement=driver.findElement(By.xpath(fnlIconXpath));
				return new VerificationHelper(driver).isElementEnabled(iconElement);
				}
			catch (Exception e) {
				log.info("Exception caught while checked icon enabled "+iconTooltip);
				WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, Section)));
				click(lnkGroupElement, "Clicking On SectionLink");
				WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,Section,iconTooltip)));
				return new VerificationHelper(driver).isElementEnabled(iconElement);
			}
		}
		
	}
	
	public boolean isIconsEnabled(List<String> iconTooltips) {
		boolean enabled=false;
		for (int i=0; i<iconTooltips.size();i++) {
		String fnlIconXpath = String.format(tmpIconXpath , iconTooltips.get(i));
		WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
		if(i==0) 
			enabled = new VerificationHelper(driver).isElementEnabled(iconElment);
		else
			enabled=enabled && new VerificationHelper(driver).isElementEnabled(iconElment);
		}
		
		return enabled;
	}
	
	/*	
     * Check if image is present for column
     * @param columnName
     * @return
     */
    public Boolean isImagePresentForColumn(String columnName) {
        String index="";
        try {
        WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
        if (!waitHelper.isElementDisplayedByEle(columnEle)){   //Previously is element present
            selectColumnVisiblityFromColumnConfig(columnName);
        }   
        index = columnEle.getAttribute("data-column-index");
        }
        catch(Exception e) {
            selectColumnVisiblityFromColumnConfig(columnName);
            WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
            index = columnEle.getAttribute("data-column-index");
        }
        int actualIndex = Integer.parseInt(index)+1;
        WebElement imgEle = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]/div/img"));
        return new VerificationHelper(driver).isElementDisplayedByEle(imgEle);
    }  
	/**
	 * Return current page url or active tab url
	 * @return
	 */
	public String getCurrentPageUrl(){
		return driver.getCurrentUrl();
	}
	
	
	public void expandNavigationMenu(String section){
		if(new VerificationHelper(driver).isElementPresent(By.xpath(String.format(expandIconBtnXpath, section)))) {
		driver.findElement((By.xpath(String.format(expandIconBtnXpath, section)))).click();	
		}
		sleepFor(2000);
	}

	public void collapseNavigationMenu(String section){
		sleepFor(1000);
		if(new VerificationHelper(driver).isElementDisplayed(By.xpath(String.format(collapseIconBtnXpath, section)))){
			driver.findElement((By.xpath(String.format(collapseIconBtnXpath, section)))).click();	
		}
		
//		if(new VerificationHelper(driver).isElementPresent(By.xpath(String.format(collapseIconBtnXpath, section)))) {
//		driver.findElement((By.xpath(String.format(collapseIconBtnXpath, section)))).click();	
//		}
		sleepFor(1000);
	}	
	
	
	public void expandAccordionIfCollapsed(String accordionSection) {
		WebElement accordEle = driver.findElement(By.xpath(String.format(tmpBtnAccordionSection, accordionSection)));
			if(!accordEle.getAttribute("class").contains("active")) {
				click(accordEle, "Clicking on index more button");
			}
	}
	
	public void clickOnAddIcon() {
		waitForAddIcon();
		click(addIconBtn,"Clicking on add icon");
	}
	
	/**
	 * Click on icon based on dataButton attribute value
	 * @param dataButtonValue
	 */
	public void clickOnIconUnderAction(String dataButtonValue) {
		WebElement buttonEle = driver.findElement(By.xpath(String.format(tmpBtnDataIconXpath, dataButtonValue)));
		click(waitHelper.waitForElementClickable(driver, buttonEle, 15),"Clicking on "+dataButtonValue+" icon");
	}
	
	
	public void clickOnEditIcon() {
		//click(editIconBtn,"Clicking on edit	 icon");
		clickOnIconMenu("Edit", "Actions");
	}
	
	
	public void clickOnCancelIcon() {
		click(btnCancelIcon,"Clicking on cancel icon");
	}
	
	public void clickOnSaveIcon() {
		//new WaitHelper(driver).waitForElementClickable(driver, saveIconBtn, 10);
		//click(saveIconBtn,"Clicking on save icon");
		new ActionHelper(driver).moveToElementAndClick(saveIconBtn);													  
		try {
			Thread.sleep(3000);
			new AlertHelper(driver).acceptAlertIfPresent();
		} catch (InterruptedException e) {
			log.info("Exception while clicking on save"+e);
		}
	}
	
//	public void waitForAddIcon() {
//		waitHelper.waitForElementVisible(addIconBtn, 30,1);
//	}
	
	public String getNavbarText() {
		return navBarTitleLbl.getText();
	}
	
	//Table related functions
	//Remove thread.sleep after this use
	public void searchInFilter(String filterText) {
		boolean filterTextBoxStatus=new VerificationHelper(driver).isElementDisplayedByEle(filterTxt);			//Added because getting blank page after selectio of column from column configuration
		if(filterTextBoxStatus) {
			waitForFilterTextBox();
			sendKeys(filterTxt, filterText, "Entering in filter"+filterText);
			sleepFor(2000);
		}else {
			refreshPage();
			waitForFilterTextBox();
			sendKeys(filterTxt, filterText, "Entering in filter"+filterText);
			sleepFor(2000);
		}
	}
		public void searchInDocumentActionsFilterInAuditActionsPopup(String filterText) {
		boolean filterTextBoxDocumentActionStatus=new VerificationHelper(driver).isElementDisplayedByEle(documentActionFilterSearch);			//Added because getting blank page after selectio of column from column configuration
		if(filterTextBoxDocumentActionStatus) {
			waitForFilterTextBox();
			sendKeys(documentActionFilterSearch, filterText, "Entering in Document Actions filter"+filterText);
			sleepFor(2000);
		}else {
			refreshPage();
			waitForFilterTextBox();
			sendKeys(documentActionFilterSearch, filterText, "Entering in Document Actions filter"+filterText);
			sleepFor(2000);
		}
	}	

	public void searchInIntrayActionsFilterInAuditActionsPopup(String filterText) {
		boolean filterTextBoxIntrayActionStatus=new VerificationHelper(driver).isElementDisplayedByEle(intrayActionFilterSearch);			//Added because getting blank page after selectio of column from column configuration
		if(filterTextBoxIntrayActionStatus) {
			waitForFilterTextBox();
			sendKeys(intrayActionFilterSearch, filterText, "Entering in Intray Actions filter"+filterText);
			sleepFor(2000);
		}else {
			refreshPage();
			waitForFilterTextBox();
			sendKeys(intrayActionFilterSearch, filterText, "Entering in Intray Actions filter"+filterText);
			sleepFor(2000);
		}
	}	
	
	public void searchInDocumentAuditFilterInLandingPage(String filterText) {
		boolean filterTextBoxIntrayActionStatus=new VerificationHelper(driver).isElementDisplayedByEle(documentAuditFilterSearch);			//Added because getting blank page after selectio of column from column configuration
		if(filterTextBoxIntrayActionStatus) {
			waitForFilterTextBoxOnLandingPageWithDocumentAudit();
			sendKeys(documentAuditFilterSearch, filterText, "Entering in Intray Actions filter"+filterText);
			sleepFor(2000);
		}else {
			refreshPage();
			waitForFilterTextBoxOnLandingPageWithDocumentAudit();
			sendKeys(documentAuditFilterSearch, filterText, "Entering in Intray Actions filter"+filterText);
			sleepFor(2000);
		}
	}
	
	public void searchInIntrayAuditFilterInLandingPage(String filterText) {
		boolean filterTextBoxIntrayActionStatus=new VerificationHelper(driver).isElementDisplayedByEle(intrayAuditFilterSearch);			//Added because getting blank page after selectio of column from column configuration
		if(filterTextBoxIntrayActionStatus) {
			waitForFilterTextBoxOnLandingPageWithIntrayAudit();
			sendKeys(intrayAuditFilterSearch, filterText, "Entering in Intray Actions filter"+filterText);
			sleepFor(2000);
		}else {
			refreshPage();
			waitForFilterTextBoxOnLandingPageWithIntrayAudit();
			sendKeys(intrayAuditFilterSearch, filterText, "Entering in Intray Actions filter"+filterText);
			sleepFor(2000);
		}
	}
	
	public void clickOnDeleteIcon() {
		click(btnDeleteIcon,"Clicking on delete icon");
		sleepFor(3000);
		clickOkOnPopup();
	}
	
	public void clickOkOnPopup() {
		waitHelper.waitForElementClickable(driver, dialogOkBtn, 10);
		click(dialogOkBtn,"Clicking on ok button");
	}

	public void waitForIcon(String iconTooltip) {
		try {
		  String fnlIconXpath = String.format(tmpIconXpath , iconTooltip);
		waitHelper.waitForElementVisible(By.xpath(fnlIconXpath), 30,1);
		}
		catch (Exception e) {
			//Failing in case of action items were collapsing
			waitForIcon(iconTooltip,"Actions");
		}
	}
		/**
		 * 
		 */
		public void waitForMenuIcon(String iconTooltip, String menuSection,String environment) {
			if(environment.equals("Enterprise_Sp1s") || environment.equals("Enterprise_R522") || environment.equals("Enterprise_R530") || environment.equals("Enterprise_R531") || environment.equals("Enterprise_R540")) {
				try {
					  String fnlMenuIconXpath = String.format(tmpMenuIconXpath , iconTooltip);
					waitHelper.waitForElementVisible(By.xpath(fnlMenuIconXpath), 30,1);
					}
					catch (Exception e) {
						WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, menuSection)));
							click(lnkGroupElement, "Clicking On SectionLink");
							WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,menuSection,iconTooltip)));
							waitHelper.waitForElementClickable(driver, iconElement, 20);
					}
			}else {
				try {
					  String fnlMenuIconXpath = String.format(tmpMenuIconXpathAfter540 , iconTooltip);
					waitHelper.waitForElementVisible(By.xpath(fnlMenuIconXpath), 30,1);
					}
					catch (Exception e) {
						WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, menuSection)));
							click(lnkGroupElement, "Clicking On SectionLink");
							WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,menuSection,iconTooltip)));
							waitHelper.waitForElementClickable(driver, iconElement, 20);
					}
			}
		
	}
	
	/**
	 * Wait For icon based on dataButton attribute value
	 * @param dataButtonValue
	 */
	public void WaitForOnIconUnderAction(String dataButtonValue) {
		WebElement buttonEle = driver.findElement(By.xpath(String.format(tmpBtnDataIconXpath, dataButtonValue)));
		click(waitHelper.waitForElementClickable(driver, buttonEle, 15),"Clicking on "+dataButtonValue+" icon");
	}
	
	/**
	 * Wait when icons are collapsed requires section
	 * @param waiticonTooltip
	 * @param waitSection
	 */
	public void waitForIcon(String waiticonTooltip, String waitSection) {
		if(envType.equals("Lives")) {
		try {
		  String fnlIconXpath = String.format(tmpIconXpath , waiticonTooltip);
		waitHelper.waitForElementVisible(By.xpath(fnlIconXpath), 20,1);
		}
		catch (Exception e) {
			WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, waitSection)));
				click(lnkGroupElement, "Clicking On SectionLink");
				WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,waitSection,waiticonTooltip)));
				waitHelper.waitForElementClickable(driver, iconElement, 20);
		}
		}else if(envType.equals("NECDM_Lives")) {
			try {
				  String fnlIconXpath = String.format(tmpIconXpathAfter540 , waiticonTooltip);
				waitHelper.waitForElementVisible(By.xpath(fnlIconXpath), 20,1);
				}
				catch (Exception e) {
					WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, waitSection)));
						click(lnkGroupElement, "Clicking On SectionLink");
						WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,waitSection,waiticonTooltip)));
						waitHelper.waitForElementClickable(driver, iconElement, 20);
				}
		}
		else {
			try {
			String fnlIconXpath = String.format(tmpIconXpath , waiticonTooltip);
			waitHelper.waitForElementVisible(By.xpath(fnlIconXpath), 20,1);
			}
			catch (Exception e) {
					WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, waitSection)));
				click(lnkGroupElement, "Clicking On SectionLink");
				WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,waitSection,waiticonTooltip)));
				waitHelper.waitForElementClickable(driver, iconElement, 20);
			}
		}
	}
	
	/**
	 * Wait when icons are collapsed requires section
	 * @param waiticonTooltip
	 * @param waitSection
	 */
	public void waitForIconWithDataButton(String dataButton, String waitSection) {
		try {
		  String fnlIconXpath = String.format(tmpBtnDataIconXpath , dataButton);
		waitHelper.waitForElementVisible(By.xpath(fnlIconXpath), 20,1);
		}
		catch (Exception e) {
			try {
				//WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath1, waitSection)));
				//click(lnkGroupElement, "Clicking On SectionLink");
				WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath1,waitSection,dataButton)));
				waitHelper.waitForElementClickable(driver, iconElement, 20);
			} catch (Exception e2) {
				WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, waitSection)));
				click(lnkGroupElement, "Clicking On SectionLink");
				WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,waitSection,dataButton)));
				waitHelper.waitForElementClickable(driver, iconElement, 20);
			}
		}
		
	}
	
	
	
	public String getAttributeIcon(String attrtooltip, String attrSection, String attrName,String environment) {
		try {
			//if(!environment.equals("Enterprise_R540")) {
			if(environment.equals("Enterprise_Sp1s") ||environment.equals("Enterprise_R522") || environment.equals("Enterprise_R530") || environment.equals("Enterprise_R531") ) {
				String fnlIconXpath = String.format(tmpIconXpath , attrtooltip);
				  WebElement fnlIconEle =  waitHelper.waitForElementVisible(By.xpath(fnlIconXpath), 20,1);
				  String attrValue =  fnlIconEle.getAttribute(attrName);
				  log.info("Attribute value is "+attrValue);
				  return attrValue;
			}else {
				String fnlIconXpath = String.format(tmpIconXpathAfter540 , attrtooltip);
				  WebElement fnlIconEle =  waitHelper.waitForElementVisible(By.xpath(fnlIconXpath), 20,1);
				  String attrValue =  fnlIconEle.getAttribute(attrName);
				  log.info("Attribute value is "+attrValue);
				  return attrValue;
			}
		}
		catch (Exception e) {
			  WebElement lnkGroupElement=driver.findElement(By.xpath(String.format(tmpLinkGroupHeaderSectionXpath, attrSection)));
				click(lnkGroupElement, "Clicking On SectionLink");
				WebElement iconElement = driver.findElement(By.xpath(String.format(tmpLinkGroupIconXpath,attrSection,attrtooltip)));
				return iconElement.getAttribute(attrName);
		}
	}

	
	
	/**
	 * This method returns element from filtered table 
	 * @param tableId
	 * @return Filtered element 
	 */
	public WebElement getfilteredRowElement(String tableId) {
		String fnlRowElement = String.format(tmpFilteredRowXpath , tableId);
		WebElement fnlrowElement=driver.findElement(By.xpath(fnlRowElement));	
		return fnlrowElement;
	}
	
	/**
	 * This method click on link of filtered row from table 
	 * @param tableId
	 * @return Filtered element 
	 */
	public void clickOnFilteredRowLink(String tableId) {
		String fnlRowLnkElementXpath = String.format(tmpFilteredRowLnkXpath , tableId);
		WebElement fnlrowLnkElement=driver.findElement(By.xpath(fnlRowLnkElementXpath));
		waitHelper.waitForElementClickable(driver, fnlrowLnkElement, 10);
		click(fnlrowLnkElement, "Clicking on filtered row link from table");
	}
	
	
	/**
	 * This method click on filtered row from table 
	 * @param tableId
	 * @return Filtered element 
	 */
	public void clickOnFilteredRow(String tableId) {
		String fnlRowElement = String.format(tmpFilteredRowXpath , tableId);
		WebElement fnlrowElement=driver.findElement(By.xpath(fnlRowElement));	
		click(fnlrowElement, "Clicking on filtered row from table");
	}

	public String getNoRecordsFoundMessage(String tableId) {
		String fnlRowElement = String.format(tmpNoRecordsRowXpath , tableId);
		WebElement fnlrowElement=driver.findElement(By.xpath(fnlRowElement));	
		return fnlrowElement.getText();
	}
	
	public ArrayList<String> getAllDisplayedColumnNames(String tableId){
		//table[@data-configuration-name='TemplateControlseDataTable'
		List<WebElement> eles = driver.findElements(By.xpath("//table[@data-configuration-name='"+tableId+"' and contains(@style,'px')]/thead/tr/th"));
		log.info("Column ele size "+String.valueOf(eles.size()));
		ArrayList<String> headers =  new ArrayList<String>();
		for(WebElement ele:eles) {
			headers.add(ele.getText());
			log.info("Adding text "+ele.getText());
		}
		log.info("Headers values are "+headers);
	return headers;
	}
	
	public ArrayList<String> getTextFromListElements(List<WebElement> eles){
		ArrayList<String> textEle =  new ArrayList<String>();
		for(WebElement ele:eles) {
			textEle.add(ele.getText());
			log.info("Adding text "+ele.getText());
		}
		return textEle;
	}
	
	public void changeToMultipleFromColumnConfig() {
		click(gearIconBtn,"Clicking on gear icon button");
		click(lnkChangeToMultiple,"clicking on change to multiple");
	}
	
	/**
	 * Select column from config of the table to make column visible on table
	 * @param columnName
	 * @return
	 */
	public boolean selectColumnVisiblityFromColumnConfig(String columnName) {
		sleepFor(1000);
		click(gearIconBtn,"Clicking on gear icon button");
		click(columnConfiglnk,"Clicking on column config link");
		waitHelper.waitForElement(columnConfigPopupTitle,10);
		WebElement columneEle = driver.findElement(By.xpath("//li[@title='"+columnName+"']/div[2]"));
		sleepFor(1000);
		new ActionHelper(driver).moveToElementAndClick(columneEle);
		sleepFor(1000);//Handling for patch env
		new WindowHelper(driver).clickOnModalFooterButton("Ok");
		waitHelper.waitForElement(navBarTitleLbl,10);
		return waitHelper.isElementDisplayedByEle(mailStatusColumn); //Previously is element present
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void waitForFilterTextBox() {
		sleepFor(1000);
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(filterTxt);
		sleepFor(1000);
		if(!status) {
			refreshPage();
		}
		waitHelper.waitForElement(filterTxt, 20);
	}

	public void waitForFilterTextBoxOnLandingPageWithDocumentAudit() {
		sleepFor(1000);
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(documentAuditFilterSearch);
		sleepFor(1000);
		if(!status) {
			refreshPage();
		}
		waitHelper.waitForElement(documentAuditFilterSearch, 35);
	}

	public void waitForFilterTextBoxOnLandingPageWithIntrayAudit() {
		sleepFor(1000);
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(intrayAuditFilterSearch);
		sleepFor(1000);
		if(!status) {
			refreshPage();
		}
		waitHelper.waitForElement(intrayAuditFilterSearch, 35);
	}
	
	public void waitForFilterTextBoxSearchResult() {
		boolean status = new VerificationHelper(driver).isElementDisplayedByEle(filterTxt);
		sleepFor(1000);
		if(!status) {
			refreshPage();
		}
        waitHelper.waitForElementVisible(filterTxt, 20,1);
    }
	
	public void waitForFilterTextBoxSubgrid() {
        waitHelper.waitForElement(txtFilterSubgrid, 20);
    }
    
	
	public void clickOnAccordion(String name) {
		WebElement btnEle = driver.findElement(By.xpath(String.format(tmpBtnAccordionXpath, name)));
		if(!btnEle.getAttribute("class").contains("active")) {
			btnEle.click();
		}
	}
	
	public void clickOnNavTab(String tabName) {
		String navTabXpath = String.format(tmpNavTabXpath, tabName);
		new JavaScriptHelper(driver).scrollToTop();
		WebElement lnkNavTabEle = waitHelper.waitForElementClickable(driver,driver.findElement(By.xpath(navTabXpath)),20);
		click(lnkNavTabEle, "Clicking on tab of "+tabName);
	}

	public void clickOnNavTabAtBottom(String tabName) {
		String navTabXpath = String.format(tmpNavTabXpath, tabName);
		sleepFor(1000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
		WebElement lnkNavTabEle = waitHelper.waitForElementClickable(driver,driver.findElement(By.xpath(navTabXpath)),50);
		click(lnkNavTabEle, "Clicking on tab of "+tabName);
		sleepFor(2000);
		new JavaScriptHelper(driver).scrollToBottom();
		sleepFor(1000);
	}
	
	public WebElement getColumnHeadEle(String tableName, String columnName) {
		WebElement headEle  = driver.findElement(By.xpath(String.format(tmpHeadingTableEle, tableName,columnName)));
		return headEle;
	}
	

	public void clickOnSpecificRow(String rowNum) {
		WebElement ele = driver.findElement(By.xpath(String.format(tmpRowElement, rowNum)));
		click(ele, "Clicking on table row index "+rowNum);
		sleepFor(500);
	}
	
	/*
	 * This method is used from Release 541 onwards
	 */
	public void clickOnSpecificRow(String environment,String rowNum) {
		if(environment.equals("Enterprise_Sp1s") || environment.equals("Enterprise_R522") || environment.equals("Enterprise_R530") || environment.equals("Enterprise_R531")) {
			WebElement ele = driver.findElement(By.xpath(String.format(tmpRowElement, rowNum)));
			click(ele, "Clicking on table row index "+rowNum);
			sleepFor(500);
		}else {
			WebElement ele = driver.findElement(By.xpath(String.format(tmpRowElementInR541, rowNum)));
			click(ele, "Clicking on table row index "+rowNum);
			sleepFor(500);
		}
	}
	
	/**
	 * Return the particular cell value based on params
	 * @param tableId TableId of data table
	 * @param row
	 * @param col
	 * @return
	 */
	public String getTableCellValue(String tableId,String row,String col) {
		return driver.findElement(By.xpath(String.format(tmpTableCellEle, tableId,row,col))).getText();
	}
	
	public void clickOnSelectAllUnderTableMenu() {
	      sleepFor(1000);
	      click(gearIconBtn,"Clicking on gear icon button");
	      new WaitHelper(driver).waitForElementClickable(driver, lnkSelectAll, 20);
	      new ActionHelper(driver).moveToElementAndClick(lnkSelectAll);
	      log.info("Clicked on select All link");
	    }
	
	public String getColumnValueFromTable(String columnName) {
		
		String status = "",index="";
		try {
			sleepFor(2000);
			WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
			boolean columnEleVisibility = new WaitHelper(driver).isElementDisplayedByEle(columnEle);
			log.info("======Column visibility====="+String.valueOf(columnEleVisibility));
		if (!columnEleVisibility){   //Previously is element present
			selectColumnVisiblityFromColumnConfig(columnName);
		}
		index = columnEle.getAttribute("data-column-index");
		}
		catch(Exception e) {
			if(!new VerificationHelper(driver).isElementDisplayedByEle(gearIconBtn)) {
				clickOnIconMenu("Re-run the same search to refresh the lists", "General");
				sleepFor(1000);
			}
			selectColumnVisiblityFromColumnConfig(columnName);
			WebElement columnElePost = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
			sleepFor(2000);
			//dragAndDropColumnToFirstPosition(columnElePost);
			index = columnElePost.getAttribute("data-column-index");
		}
		int actualIndex = Integer.parseInt(index)+1;
		
		status = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
		return status;
	}
	
		public String ColumnValue1(String columnName) {
		String status = "",index="";
		WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
		index = columnEle.getAttribute("data-column-index");
		int actualIndex = Integer.parseInt(index)+1;
		status = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
		return status;
	}
	
	/**
	 * 
	 * @param columnName
	 * @return
	 */
	public Boolean isGreenTickPresentForColumn(String columnName) {
		String index="";
		try {
		WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
		if (!waitHelper.isElementDisplayedByEle(columnEle)){   //Previously is element present
			selectColumnVisiblityFromColumnConfig(columnName);
		}	
		index = columnEle.getAttribute("data-column-index");
		}
		catch(Exception e) {
			sleepFor(1000);
			if(!new VerificationHelper(driver).isElementDisplayedByEle(filterTxtSearchResult)) {          //Added because getting blank page after selecting column value
				refreshPage();
			}
			selectColumnVisiblityFromColumnConfig(columnName);                                          
			WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
			index = columnEle.getAttribute("data-column-index");
		}
		int actualIndex = Integer.parseInt(index)+1;
		WebElement tickEle = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]/div/img[contains(@src,'Tick.png')]"));
		//return new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]/div/img[contains(@src,'Tick.png')]")));
		return new VerificationHelper(driver).isElementDisplayedByEle(tickEle);
	}
	
	/**
	 * 
	 * @param tableName
	 * @return
	 */
	public int getRowsCountForTable(String tableName) {
		String rowsXpath = "//table[@id='"+tableName+"']/tbody/tr";
		List<WebElement> rows = driver.findElements(By.xpath(rowsXpath));
		return rows.size();
	}
	
	/**
	 * 
	 * @param tableId
	 * @param index
	 * @return
	 */
	public List<String> getAllColumnValues(String tableId,int index){
		String columXpath = "//table[@id='"+tableId+"']/tbody/tr/td["+index+"]";
		List<WebElement> columnEle = driver.findElements(By.xpath(columXpath));
		return getTextFromListElements(columnEle);
		}
	
	/**
	 * Get value by index of the table
	 * @param tableId
	 * @param index Index starts from 1
	 * @return
	 */
	public String getColumnValueByIndex(String tableId,int index){
		String columXpath = "//table[@id='"+tableId+"']/tbody/tr/td["+index+"]";
		String columnVal = driver.findElement(By.xpath(columXpath)).getText();
		return columnVal;
		}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean isDownloadedFileExist(String fileName) {
		String home = System.getProperty("user.home");
		String downloadPath = home+"\\Downloads\\";
		log.info("Download directory is "+downloadPath);
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	 	log.info("dir_contents length are "+dir_contents.length);    
	    for (int i = 0; i < dir_contents.length; i++) {
	    	log.info("File name is "+dir_contents[i].getName());
	        if (dir_contents[i].getName().contains(fileName))
	            dir_contents[i].delete();
	        	return flag=true;
	            }
	    	return flag;		
		}
		
		/**
		 * 
		 * @param columnEle
		 * @return
		 */
		public boolean dragAndDropColumnToFirstPosition(WebElement columnEle) {
		new ActionHelper(driver).dragAndDropElement(columnEle, FirstColumnEle);
		log.info("Drag and drop is successfull for column ele");
		return true;
		}


		
		public void clickOnSaveIconForUser() {
			//new WaitHelper(driver).waitForElementClickable(driver, saveIconBtn, 10);
			click(saveIconBtn,"Clicking on save icon");
			clickOkOnPopup();				//Added by amit
			try {
				Thread.sleep(5000);
				new AlertHelper(driver).acceptAlertIfPresent();
			} catch (InterruptedException e) {
				log.info("Exception while clicking on save"+e);
			}
		}
		
		public void waitForAddIcon() {
			waitForIconWithDataButton("Add", "Actions");
		}
		
		/** In tray action items ****/
	    
	    public void clickOnFirstRow() {
	        waitHelper.waitForElementVisible(filterTxt, 20, 1);
	        //intratY
	        if(!intrayItemRow.getAttribute("class").contains("selected"))
	            click(intrayItemRowCell,"Clicking on first item of intray");
	      }
	    
	    public void WaitForNavigationBarTitle(String folderRef) {
	    	WebElement navBarTitle = driver.findElement(By.xpath(String.format(navigationBarTitleLabel, folderRef)));
	    	new WaitHelper(driver).waitForElement(navBarTitle, 10);
	    }
	    
	    public void SelectMultiCheckbox() {
	    	boolean status = new VerificationHelper(driver).isElementSelected(multiSelectCheckbox);
	    	if(!status) {
	    		click(multiSelectCheckbox, "Clicking on multi select checkbox");
	    	}
	    }
	    
	    public void UnSelectMultiCheckbox() {
	    	boolean status = new VerificationHelper(driver).isElementSelected(multiSelectCheckbox);
	    	if(status) {
	    		click(multiSelectCheckbox, "Clicking on multi select checkbox");
	    		sleepFor(2000);
	    	}
	    }
	    
	    public String  getColumnValue(String columnName) {
			String status = "",index="";
			try {
				sleepFor(2000);
				WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
				boolean columnEleVisibility = new WaitHelper(driver).isElementDisplayedByEle(columnEle);
				log.info("======Column visibility====="+String.valueOf(columnEleVisibility));
			
			index = columnEle.getAttribute("data-column-index");
			}
			catch(Exception e) {
				if(!new VerificationHelper(driver).isElementDisplayedByEle(gearIconBtn)) {
					clickOnIconMenu("Re-run the same search to refresh the lists", "General");
					sleepFor(1000);
				}
				//selectColumnVisiblityFromColumnConfig(columnName);
				WebElement columnElePost = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
				sleepFor(2000);
				//dragAndDropColumnToFirstPosition(columnElePost);
				index = columnElePost.getAttribute("data-column-index");
			}
			int actualIndex = Integer.parseInt(index)+1;
			
			status = driver.findElement(By.xpath("//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();
			return status;
	    }
//		created by sagar on 12.07.2023
		public String getTableCellValueByClassName(String tableClass,String row,String col) {
			return driver.findElement(By.xpath(String.format(tmpTableCellEleByClassName, tableClass,row,col))).getText();
		}
	
		
		public boolean waitForIconStatus(String iconTooltip, String Section) {
			try {
				String fnlIconXpath = String.format(tmpIconXpathAfter540 , iconTooltip);
				WebElement iconElment=driver.findElement(By.xpath(fnlIconXpath));
				try {
				waitHelper.waitForElementClickable(driver, iconElment, 5);
				}
				catch (Exception e) {
					expandNavigationMenu(Section);
				}
				waitHelper.waitForElement(iconElment, 10);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		public void waitForNavBarTitle(String navBar) {
		   	WebElement navBarTitle = driver.findElement(By.xpath(String.format(navigationBarTitle, navBar)));
	    	new WaitHelper(driver).waitForElement(navBarTitle, 10);
		}
		
		
		//		created by sagar on 05.10.2023
	    public String  getColumnValueFromLandingPageGridForIntrayAudit(String columnName) {
			String status = "",index="";
			try {
				sleepFor(2000);
				WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
				sleepFor(2000);
				boolean columnEleVisibility = new WaitHelper(driver).isElementDisplayedByEle(columnEle);
				log.info("======Column visibility====="+String.valueOf(columnEleVisibility));
			
			index = columnEle.getAttribute("data-column-index");
			}
			catch(Exception e) {
				if(!new VerificationHelper(driver).isElementDisplayedByEle(gearIconBtn)) {
					clickOnIconMenu("Re-run the same search to refresh the lists", "General");
					sleepFor(1000);
				}
				//selectColumnVisiblityFromColumnConfig(columnName);
				WebElement columnElePost = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
				sleepFor(2000);
				//dragAndDropColumnToFirstPosition(columnElePost);
				index = columnElePost.getAttribute("data-column-index");
			}
			int actualIndex = Integer.parseInt(index)+1;
			
//			status = driver.findElement(By.xpath("//div[@class='LandingPageGrid']//table[@id='auditSubgrid']/tbody/tr[1]/td["+String.valueOf(actualIndex)+"]")).getText();	//for document audit
			status = driver.findElement(By.xpath("//div[@class='LandingPageGrid']//table[@id='intrayAuditSubgrid']/tbody/tr[1]/td["+String.valueOf(actualIndex)+"]")).getText();	//for intray audit
			return status;
	    }
	    
//		created by sagar on 05.10.2023
	    public String  getColumnValueFromPopup(String columnName, String popUpName) {
			String status = "",index="";
			try {
				sleepFor(2000);
				WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
				boolean columnEleVisibility = new WaitHelper(driver).isElementDisplayedByEle(columnEle);
				log.info("======Column visibility====="+String.valueOf(columnEleVisibility));
			
			index = columnEle.getAttribute("data-column-index");
			}
			catch(Exception e) {
				if(!new VerificationHelper(driver).isElementDisplayedByEle(gearIconBtn)) {
					clickOnIconMenu("Re-run the same search to refresh the lists", "General");
					sleepFor(1000);
				}
				//selectColumnVisiblityFromColumnConfig(columnName);
				WebElement columnElePost = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
				sleepFor(2000);
				//dragAndDropColumnToFirstPosition(columnElePost);
				index = columnElePost.getAttribute("data-column-index");
			}
			int actualIndex = Integer.parseInt(index)+1;
			status = driver.findElement(By.xpath("//h4[text()='"+popUpName+"']/ancestor::div[2]//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();			
			return status;
	    }

//		created by sagar on 05.10.2023
	    public String  getColumnValueFromPopupFromIntrayAction(String columnName, String popUpName) {
			String status = "",index="";
			try {
				sleepFor(4000);
				WebElement columnEle = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
				sleepFor(4000);
				boolean columnEleVisibility = new WaitHelper(driver).isElementDisplayedByEle(columnEle);
				log.info("======Column visibility====="+String.valueOf(columnEleVisibility));
			
			index = columnEle.getAttribute("data-column-index");
			}
			catch(Exception e) {
				if(!new VerificationHelper(driver).isElementDisplayedByEle(gearIconBtn)) {
					clickOnIconMenu("Re-run the same search to refresh the lists", "General");
					sleepFor(1000);
				}
				//selectColumnVisiblityFromColumnConfig(columnName);
				WebElement columnElePost = driver.findElement(By.xpath(String.format(tmpTableColumnEle, columnName)));
				sleepFor(2000);
				//dragAndDropColumnToFirstPosition(columnElePost);
				index = columnElePost.getAttribute("data-column-index");
			}
			int actualIndex = Integer.parseInt(index)+1;
			status = driver.findElement(By.xpath("//h4[text()='"+popUpName+"']/ancestor::div[2]//table[@id='intrayAuditActionId']//tr[contains(@class,'odd')]/td["+String.valueOf(actualIndex)+"]")).getText();	
			return status;
	    }
		    
//		created by sagar on 12.07.2023
//		public String getTableCellValueByClassName(String tableClass,String row,String col) {
//			return driver.findElement(By.xpath(String.format(tmpTableCellEleByClassName, tableClass,row,col))).getText();
//		}
}
