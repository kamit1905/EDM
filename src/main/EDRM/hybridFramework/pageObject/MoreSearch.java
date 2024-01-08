package main.EDRM.hybridFramework.pageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.calender.CalenderHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;

public class MoreSearch extends TestBase {
	
	private WebDriver driver;
	WaitHelper waitHelper;
	private NavigationMenu navigationMenu;
	private Logger log = LoggerHelper.getLogger(MoreSearch.class);
	
	
	@FindBy(how = How.XPATH,using = "//input[@id='FromDate']")
	public WebElement fromDateField;
	
	@FindBy(how = How.XPATH,using = "//input[@id='ToDate']")
	public WebElement toDateField;
	
	@FindBy(how = How.XPATH,using = "//button[@data-bs-original-title='Save']")
	public WebElement saveBtnOnMoreSearch;
	
	@FindBy(how = How.XPATH,using = "//button[@data-bs-original-title='Delete']")
	public WebElement deleteBtnOnMoreSearch;

	@FindBy(how = How.XPATH,using = "//input[@name='SearchName']")
	public WebElement searchNameInput;
	
	@FindBy(how = How.XPATH,using = "//input[@id='SaveSearchHomepageTile']")
	public WebElement homePageTileCheckbox;
	
	@FindBy(how = How.XPATH,using = "//button[@id='btn-ParameterisedSearchMore']")
	public WebElement searchParameterSection;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='ScopeType']")
	public WebElement ddScopeType;

	@FindBy(how = How.XPATH,using = "//input[@id='SaveMailStatuses']")
	public WebElement mailStatusSaveBtn;
	
	@FindBy(how = How.XPATH,using = "//input[@id='Overdue']")
	public WebElement overDueCheckbox;
	
	@FindBy(how = How.XPATH,using = "//input[@id='AlmostDue']")
	public WebElement almostDueCheckbox;
	
	@FindBy(how = How.XPATH,using = "//input[@id='input-mailStatuses']")
	public WebElement mailStatusInput;
	@FindBy(how = How.XPATH,using = "//input[@id='SelectedFlexibleTagSearchOptionAll']")
	public WebElement SelectedFlexibleTagSearchOptionAll;									//added by sagar on 22.11.2023 to select 'all' radio button on tags.
	@FindBy(how = How.XPATH,using = "//input[@id='SelectedFlexibleTagSearchOptionAny']")
	public WebElement SelectedFlexibleTagSearchOptionAny;	

 	private String tmpTileXpath = "//div[text()='%s']";
	private String tmpTileCount = "//div[text()='%s']/following-sibling::div";																	   
	private String tmpScopeLoc = "//button[@data-id='Scope%s']";
	
	public String tmpMailStatus = "//div[@data-value='%s']//a[@title='Remove']";
	public String tmpTeam = "//div[contains(@class,'input-teams')]//div[contains(@class,'option') and text()='%s']";

	
	
	public MoreSearch(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		navigationMenu= new NavigationMenu(driver);
		log.info("Home page object created");
	}
	
	  public void EnterFromDate(String monthYearFrom,String monthFrom,String dayFrom) {
		  fromDateField.click();
		  new CalenderHelper(driver).SelectMonthFromDatePicker(monthYearFrom, monthFrom);
		  new CalenderHelper(driver).SelectMonthFromCalender(monthFrom);
		  new CalenderHelper(driver).SelectDayFromCalender(dayFrom);
	  }
	  
	  public void EnterToDate(String monthYearTo,String monthTo,String dayTo) {
		  toDateField.click();
		  new CalenderHelper(driver).SelectMonthFromDatePicker(monthYearTo, monthTo);
		  new CalenderHelper(driver).SelectMonthFromCalender(monthTo);
		  new CalenderHelper(driver).SelectDayFromCalender(dayTo);
	  }
	  
	  public void InputSearchName(String searchName) {
		  sendKeys(searchNameInput, searchName, "Search using "+searchName);
	  }
	  
	  public void ClickOnSaveBtnOnMoreSearchPage() {
		  click(saveBtnOnMoreSearch, "Clicking on save btn on more search page");
	  }
	  
	  public void ClickOnDeleteBtnOnMoreSearchPage() {
		  click(deleteBtnOnMoreSearch, "Clicking on delete btn on more search page");
		  sleepFor(2000);
		  String getMsg = new WindowHelper(driver).getPopupMessageClickOk("Delete Search");
		  sleepFor(2000);
		  AssertionHelper.verifyTextContains(getMsg, "Are you sure you want to delete");
	  }
	  
	  public void searchWithSavedSearch(String label) {
		navigationMenu.clickOnTab("Search"); // Change this if it causes prblm
		click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
		sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), label,
					"Enetering in search =" + label);
		click(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDMatch, 10), "Clicking on dropdown matching value");
	  }
	  
	  public void CreateHomePageTile(String tileName,String scopeType,boolean tileCheckbox,List<String> parameterizedSearchValue,String scope) {
			new MoreSearch(driver).ClickOnSaveBtnOnMoreSearchPage();
			new WindowHelper(driver).waitForModalDialog("Save Search");
			new MoreSearch(driver).InputSearchName(tileName);	
			
			if(tileCheckbox) {				 
			click(homePageTileCheckbox, "Clicking on home page tile checkbox");
			sleepFor(1000);
			}
			
			if(scopeType.equals("Team") || scopeType.equals("Role")) {
				SelectScopeTypeOnMoreSearch(scopeType);
				SelectScopeOnMoreSearch(scope,scopeType);
			}else if(scopeType.equals("File System")) {
				SelectScopeTypeOnMoreSearch(scopeType);
			}
			
			if(parameterizedSearchValue.size()>0) {
				click(searchParameterSection, "Clicking on search parameter section");
				sleepFor(2000);
				new DocumentToolsPage(driver).selectFromInputUnderMoreSearch("Account Ref","input-searchParameterisedFields","input-ParameterisedTags");
			}
			
			new WindowHelper(driver).clickOnModalFooterButton("Ok");
			String getMsg = new WindowHelper(driver).getPopupMessageClickOk("Save Search");		//
			
			AssertionHelper.verifyText(getMsg, "Search saved successfully.");
			sleepFor(1000);
	  }
		
	public void selectTagRadioButtonOnMySearch(String tagRadioBtn) {
			if(tagRadioBtn== "All") {
				click(SelectedFlexibleTagSearchOptionAll, "Clicking on search parameter section");
			}
			else if(tagRadioBtn== "Any") {
				click(SelectedFlexibleTagSearchOptionAny, "Clicking on search parameter section");
			}
	  }
	  public boolean CheckTileIsPresentOnHomePage(String tileName) {
		  String tile = String.format(tmpTileXpath, tileName);
		  boolean tileFlag = new VerificationHelper(driver).isElementDisplayed(By.xpath(tile));
		  return tileFlag;
	  }
	  public void selectTileOnHomePage (String tileName) {
		  String tile = String.format(tmpTileXpath, tileName);
		  WebElement tileNameXpath = driver.findElement(By.xpath(tile));
		  new VerificationHelper(driver).isElementDisplayed(By.xpath(tile));
		  waitHelper.waitForElementClickable(driver, tileNameXpath, 30).click();
 	  }
	  
	  public String countTheNosOnTilePresentOnHomePage(String tileName) {
//		  new MoreSearch(driver).CheckTileIsPresentOnHomePage(tileName);
		  CheckTileIsPresentOnHomePage(tileName);
		  String tilecount = String.format(tmpTileCount, tileName);
		  WebElement countOnTileXpath = driver.findElement(By.xpath(tilecount));
		  waitHelper.waitForElementVisible(countOnTileXpath, 90, 1);
		  String countOnTile = countOnTileXpath.getText();
		  return countOnTile;
	  }
	  
	  public void SelectScopeTypeOnMoreSearch(String scopeType) {
		  new DropdownHelper(driver).selectFromAutocompleDD(scopeType, ddScopeType);
	  }
	  
	  public void SelectScopeOnMoreSearch(String scope,String scopeType) {
		  String ddScopeLocator = String.format(tmpScopeLoc, scopeType);
		  WebElement ddScopeLoc = driver.findElement(By.xpath(ddScopeLocator));
		  click(ddScopeLoc, "Clicking on scope drop down");
		  
			try {				
				//waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li[@class='selected active']/a")), 10);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.info("Exception in thread sleep "+e);
			}
			ddScopeLoc.findElement(By.xpath("./following-sibling::div//input")).sendKeys(scope);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				log.info("Exception in thread sleep "+e);
			}
			
			//buttonEle.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]/a")).click();
			WebElement ele = ddScopeLoc.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]/a"));
			new JavaScriptHelper(driver).clickElement(ele);
	  }
			public void createMoreSearchWithAndWithoutHomePageTile (String documentType, String Team, String TeamName, String User, List<String> paramsList, String mailStatus, String Tag, String Role, String RoleName, String savedSearchInputName, String tagRadioButton, String scopeType, boolean tileCheckbox, String getEnvVariable) throws InterruptedException {
				log.info("----------> Creating HomePage Tile as => "+savedSearchInputName);
//				List<String> paramsList = new ArrayList<>();
				
//				create more search and save it
				getApplicationUrl();
				new DocumentToolsPage(driver).clickOnMoreSearch();
				test.log(Status.INFO,"===Environment value is ===="+getEnvVariable+"==========");
				if(getEnvVariable.equals("Enterprise_Sp1s")) {
				  test.log(Status.INFO,"====In if condition");
				  new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
				}
				else {
				  test.log(Status.INFO,"====In else condition");
				  new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
				}
				
				new JavaScriptHelper(driver).scrollToTop();
//				new JavaScriptHelper(driver).scrollByPixel();
				sleepFor(2000);
				if(documentType!=null) {
					new DocumentToolsPage(driver).enterValueUnderMoreSearch("IndexMore", "input-docTypes", documentType);		
					sleepFor(2000);
					}
				if(TeamName!=null) {
					new DocumentToolsPage(driver).enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Teams", TeamName);		
					sleepFor(2000);
					}				
				if(User!=null) {
					new DocumentToolsPage(driver).enterValueUnderMoreSearch("IntrayCriteriaMore", "input-Users", User);		
					sleepFor(2000);
					}
				if(mailStatus!=null) {
					new DocumentToolsPage(driver).enterValueUnderMoreSearch("IntrayCriteriaMore", "input-mailStatuses", mailStatus);		
					sleepFor(2000);
					}
				if(Tag!=null) {
					new DocumentToolsPage(driver).enterValueUnderMoreSearch("OtherCriteriaMore", "input-docTags", Tag);		
					sleepFor(2000);
					}			
				new JavaScriptHelper(driver).scrollToBottom();
				sleepFor(2000);
				new MoreSearch(driver).selectTagRadioButtonOnMySearch(tagRadioButton);
				sleepFor(1000);
//				create Home page title
				if(scopeType =="Team") {
					new MoreSearch(driver).CreateHomePageTile(savedSearchInputName, scopeType, tileCheckbox, paramsList, TeamName);
					sleepFor(2000);
					}
				if(scopeType =="Role") {
					new MoreSearch(driver).CreateHomePageTile(savedSearchInputName, scopeType, tileCheckbox, paramsList, RoleName);
					sleepFor(2000);
					}
		}

		public void verifyMoreSearchPresentInSearchList(String savedSearchInputName, String user)
		{
			getApplicationUrl();
			sleepFor(1000);
			navigationMenu.clickOnTab("Search"); 									// Change this if it causes prblm
			click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
			sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName, "Enetering in search =" + savedSearchInputName);
				try {
					boolean elementStatus = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='optgroup-3 active']")));
					if(elementStatus == true) {
						WebElement element = driver.findElement(By.xpath("//li[@class='optgroup-3 active']"));
						String getMsg2 = element.getText();
						boolean status2 = getMsg2.contains(savedSearchInputName);
						AssertionHelper.verifyTrue(status2, "--------->> The " + savedSearchInputName + " is present for user " + user);
						} 
				}
				catch (Exception e) {
					boolean elementStatus1 = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='no-results']")));
					if(elementStatus1 == true) {
						WebElement element1 = driver.findElement(By.xpath("//li[@class='no-results']"));
						String getMsg2 = element1.getText();
						boolean status2 = getMsg2.contains("No results matched");
						AssertionHelper.verifyTextContains(getMsg2, "No results matched");	
						}
					}
//				try {
//					boolean elementStatus = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='optgroup-3 active']")));
//					if(elementStatus == true) {
//						WebElement element = driver.findElement(By.xpath("//li[@class='optgroup-3 active']"));
//						String getMsg2 = element.getText();
//						boolean status2 = getMsg2.contains(savedSearchInputName);
//						AssertionHelper.verifyFalse(status2, "--------->> The " + savedSearchInputName + " is not present for user " + user);
//						} 
//				}
//				catch (Exception e) {
//					System.out.println("---------->> The try block for " + savedSearchInputName + " not present fails");
//						}
		}
		
		public void deleteMoreSearch(String savedSearchInputName) {
			getApplicationUrl();
			searchWithSavedSearch(savedSearchInputName);
			sleepFor(2000);
			new JavaScriptHelper(driver).scrollToBottom();
			sleepFor(2000);
			ClickOnDeleteBtnOnMoreSearchPage();
			
			getApplicationUrl();
			sleepFor(1000);
			navigationMenu.clickOnTab("Search"); 								
			click(new CapturePage(driver).searchDD, "Clicking on search dropdown");
			sendKeys(waitHelper.waitForElementClickable(driver, new CapturePage(driver).searchDDInput, 10), savedSearchInputName, "Enetering in search =" + savedSearchInputName);
			
			try {
				boolean elementStatus1 = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='no-results']")));
				if(elementStatus1 == true) {
					WebElement element1 = driver.findElement(By.xpath("//li[@class='no-results']"));
					String getMsg2 = element1.getText();
					AssertionHelper.verifyTextContains(getMsg2, "No results matched");	
					System.out.println("---------->> My search is deleted and no results founds");
				}
			} catch (Exception e) {
				boolean elementStatus = new VerificationHelper(driver).isElementDisplayedByEle(driver.findElement(By.xpath("//li[@class='optgroup-3 active']")));
				if(elementStatus == true) {
					WebElement element = driver.findElement(By.xpath("//li[@class='optgroup-3 active']"));
					String getMsg1 = element.getText();
					boolean status = getMsg1.contains(savedSearchInputName);
					AssertionHelper.verifyFalse(status, "----->> My search is deleted");
				}		
			}
		}
		
		public boolean verifyGivenButtonIsDisable(String BtnName) {
			
			boolean element = new VerificationHelper(driver).isElementEnabled(driver.findElement(By.xpath("//button[@data-bs-original-title='"+BtnName+"']")));
//			new VerificationHelper(driver).isElementEnabled(driver.findElement(By.xpath("//button[@class='btn btn-primary']/following-sibling::button[@disabled='']")));
			return element;
		}
		
		public void EditHomePageTile(String folder1Ref,String getEnvVariable,String teamName) {
			  if(!folder1Ref.isEmpty()) {
				  sendKeys(new CapturePage(driver).folder1Reftxt, folder1Ref, "Entering folder1Ref as "+folder1Ref);
				  new ActionHelper(driver).pressTab();
			  }
			  
				if (!teamName.isEmpty()) {
					if (getEnvVariable.equals("Enterprise_Sp1s")) {
						new DocumentToolsPage(driver).expandMoreSectionIfHidden("OtherCriteria");
					} else {
						new DocumentToolsPage(driver).expandMoreSectionIfHidden("Intray");
					}
					if (getEnvVariable.equals("Enterprise_R550") || getEnvVariable.equals("Enterprise_R551")) {
						new JavaScriptHelper(driver).scrollToBottom();
						sleepFor(2000);
						new DocumentToolsPage(driver).selectFromInputUnderMoreSearch(teamName, "input-Teams",
								"input-teams"); // Added by amit to
						// select user
					} else {
						new DocumentToolsPage(driver).selectFromTeamUnderIntray(teamName);
					}

				}
			}	
		
	  public void RemoveMailStatus(String mailStatusName) {
		  String tmpMailStatusString = String.format(tmpMailStatus, mailStatusName);
		  WebElement mailStatus = driver.findElement(By.xpath(tmpMailStatusString));
		  new JavaScriptHelper(driver).clickElement(mailStatus);
		  sleepFor(2000);
	  }
	  
	  public boolean VerifyTeamOnMoreSearchPage(String teamName) {
		  WebElement inputEle = driver.findElement(By.xpath(String.format(new DropdownHelper(driver).tmpSelectsizeInputAfter550, "input-Teams")));
		  new JavaScriptHelper(driver).clickElement(inputEle);
		  
		  String team = String.format(tmpTeam, teamName);
		 //WebElement teamNameEle = driver.findElement(By.xpath(String.format(tmpTeam, teamName)));
		  boolean status = new VerificationHelper(driver).isElementDisplayed(By.xpath(team));
		  return status;
	  }


}
