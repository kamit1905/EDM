package main.EDRM.hybridFramework.helper.select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import tests.TestIntrayTools;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


public class DropdownHelper {

	
	private WebDriver driver;
	WaitHelper waitHelper;
	private Logger log = LoggerHelper.getLogger(DropdownHelper.class);
	
	@FindAll({
		@FindBy(how=How.XPATH,using = "//div[contains(@class,'open')]/div/div/input"),
		@FindBy(how=How.XPATH,using = "//div[@class='bs-container dropdown bootstrap-select show']/div/div/input"),
		@FindBy(how=How.XPATH,using = "//div[contains(@class,'bootstrap-select')]/div[contains(@class,'show')]/div/input"),
	})
	public WebElement ddBootstrapInput;
		@FindAll({
		@FindBy(how=How.XPATH,using="//select[@name='searchResultIntrayDataTable_length']"),
			@FindBy(how=How.XPATH,using="//select[@aria-controls='searchResultIntrayDataTable']")
		})
	public WebElement showRowsDD;								//created by sagar on 10.10.2023 for show Rows Dropdown

		@FindBy(how=How.XPATH,using="//div[@id='searchResultIntrayDataTable_info']")
		public WebElement searchResultIntrayDataTable_info;			//created by sagar on 10.10.2023 for search Result Intray Data Table info

	
	public String tmpSelectsizeInput = "//input[@id='%s']/..//input[@type='text' and @autocomplete='off']",
			tmpSelectsizeDropdownValue = "//div[starts-with(@class,'selectize-dropdown multi %1$s')]//div[contains(@class,'option') and text()='%2$s']";
			
	public String tmpSelectsizeInputAfter550 = "//input[@id='%s']/..//input[@type='text' and @autocomplete='new-password']",
			tmpgetElementValue = "//input[@id='%s']/..//div[@class='item']",
			tmpSelectsizeDropdownValue550_1 = "//div[contains(@class,'%1$s')]//div[contains(@class,'item') and text()='%2$s']",
			tmpSelectsizeDropdownValue550 = "//div[contains(@class,'%1$s')]//div[contains(@class,'option') and text()='%2$s']";
			public String tmpshowRowsDD = "//select[@name='searchResultIntrayDataTable_length']//option[@value='%s']";
			//tmpSelectsizeDropdownValue550 = "//div[contains(@class,'selectize-dropdown multi %1$s')]//div[contains(@class,'option') and text()='%2$s']";	
	
	public DropdownHelper(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		log.debug("DropDown helper : " + this.driver.hashCode());
		waitHelper = new WaitHelper(driver);
	}
	
	/**
	 * Select from select tag dropdowns
	 * @param ele
	 * @param visibleText
	 */
	public void selectFromDropdown(WebElement ele, String visibleText) {
		Select s= new Select(ele);
		waitHelper.waitForElementClickable(driver, ele, 20);
		s.selectByVisibleText(visibleText);
	}
	
	/**
	 * This method helps to select from autocomplete dropdown in EDRM appln
	 * @param inputText : search input which you want to enter in dropdown
	 * @param : dropdownbtn dropdown button element 
	 */
	public void selectFromAutocompleDD(String inputText, WebElement buttonEle) {
		//WebElement buttonEle = driver.findElement(dropdownbtn);
		//buttonEle.click();
		new JavaScriptHelper(driver).clickElement(buttonEle);
		//new TestBase().click(buttonEle, "Clicking on dropdown");
		try {				
			//waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li[@class='selected active']/a")), 10);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.info("Exception in thread sleep "+e);
		}
		buttonEle.findElement(By.xpath("./following-sibling::div//input")).sendKeys(inputText);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			log.info("Exception in thread sleep "+e);
		}
		
		//buttonEle.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]/a")).click();
		WebElement ele = buttonEle.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]/a"));
		new JavaScriptHelper(driver).clickElement(ele);
	}
	
	public void selectFromAutocompleDDInPopup(String inputText, WebElement buttonEle) {
		//WebElement buttonEle = driver.findElement(dropdownbtn);
		buttonEle.click();
		waitHelper.waitForElement(driver.findElement(By.xpath("//li[@class='selected active']/a")), 1000);
		//driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select open']/div/div/input")).sendKeys(inputText);
		//driver.findElement(By.xpath("//div[@class='bs-container dropdown bootstrap-select show']/div/div/input")).sendKeys(inputText);			//Added for R531 release
		new TestBase().sleepFor(1000);
		new TestBase().sendKeys(ddBootstrapInput, inputText, "Entering text "+inputText);
		driver.findElement(By.xpath("//li[@class='active']/a")).click();
	}
	
	/**
	 * This method helps to select from autocomplete dropdown in EDRM appln
	 * @param inputText : search input which you want to enter in dropdown
	 * @param : dropdownbtn dropdown button element 
	 */
	public void selectFromAutocompleDDWithoutInput(String selectItem, WebElement buttonEle) {
		//buttonEle.click();
		new TestBase().click(buttonEle, "Clicking on drop down button");
		//new JavaScriptHelper(driver).clickElement(buttonEle);
		try {
		waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li[@class='selected active']/a")),10);
		}
		catch(Exception ex) {log.info("No Active selected element");	}
		WebElement DDlocatorEle = waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li/a/span[text()='"+selectItem+"']")),10);    
		new ActionHelper(driver).moveToElementAndClick(DDlocatorEle);
	}

		public String getDropdownSelectionValue(WebElement element) {
		return element.getAttribute("data-original-title");
	}
	
	/**
	 * This method requires provided param and it will return all dropdown options text values
	 * @param eleId Button Element data-id attribute 
	 * @return returns array list
	 */
	public List<String> getAllOptionsFromDropdown(String eleId,String getEnvVariable) {
		if(getEnvVariable.equals("Enterprise_R551")) {
			String lastDDEle = "//button[@data-id='"+eleId+"']/ancestor::section[contains(@class,'page container')]/following-sibling::div[contains(@class,'bs-container dropdown bootstrap-select')]//li[last()]";
			String locatorEle = "//button[@data-id='"+eleId+"']/ancestor::section[contains(@class,'page container')]/following-sibling::div[contains(@class,'bs-container dropdown bootstrap-select')]//ul//li/a/span[@class='text']";
			String buttonEle = "//button[@data-id='"+eleId+"']";
			WebElement buttonDDEle = driver.findElement(By.xpath(buttonEle));
			//driver.findElement(By.xpath(buttonEle)).click();
			new JavaScriptHelper(driver).clickElement(buttonDDEle);
			waitHelper.waitForElement(driver.findElement(By.xpath(lastDDEle)), 20, 1);
			List<WebElement> allOptions = driver.findElements(By.xpath(locatorEle));
			allOptions.remove(0);//Removing blank and null text elements
			Iterator<WebElement> itr = allOptions.iterator();
			ArrayList<String> optionsText = new ArrayList<String>();
			while(itr.hasNext()) {
				optionsText.add(itr.next().getText());
			}
			return optionsText;
		}else {
			String lastDDEle = "//button[@data-id='"+eleId+"']/..//li[last()]";
			String locatorEle = "//button[@data-id='"+eleId+"']/..//li/a/span[@class='text']";
			String buttonEle = "//button[@data-id='"+eleId+"']";
			WebElement buttonDDEle = driver.findElement(By.xpath(buttonEle));
			//driver.findElement(By.xpath(buttonEle)).click();
			new JavaScriptHelper(driver).clickElement(buttonDDEle);
			waitHelper.waitForElement(driver.findElement(By.xpath(lastDDEle)), 20, 1);
			List<WebElement> allOptions = driver.findElements(By.xpath(locatorEle));
			allOptions.remove(0);//Removing blank and null text elements
			Iterator<WebElement> itr = allOptions.iterator();
			ArrayList<String> optionsText = new ArrayList<String>();
			while(itr.hasNext()) {
				optionsText.add(itr.next().getText());
			}
			return optionsText;
		}
	}
		
	/**
	 * Select from selectsize class dropdown 
	 * @param inputId inputId of element with xpath: //input[@id='%s']/..//input[@type='text' and @autocomplete='off']
	 * @param selectValue Value which needs to be selected
	 */
		public void selectFromSelectsizeDropdown(String inputId, String selectValue) {
			WebElement inputEle = driver.findElement(By.xpath(String.format(tmpSelectsizeInput, inputId)));
			//inputEle.click();
			new JavaScriptHelper(driver).clickElement(inputEle);
			
			WebElement selectEle = driver.findElement(By.xpath(String.format(tmpSelectsizeDropdownValue,inputId,selectValue)));
			new TestBase().sleepFor(1000);
			waitHelper.waitForElementClickable(driver, selectEle, 30).click();
		}
		
		public void selectFromSelectsizeDropdown(String inputId, String selectValue,String inputId1) {
			WebElement inputEle = driver.findElement(By.xpath(String.format(tmpSelectsizeInputAfter550, inputId)));
			//inputEle.click();
			new JavaScriptHelper(driver).clickElement(inputEle);
			
			WebElement selectEle = driver.findElement(By.xpath(String.format(tmpSelectsizeDropdownValue,inputId1,selectValue)));
			new TestBase().sleepFor(1000);
			waitHelper.waitForElementClickable(driver, selectEle, 30).click();
		}
		
		public void selectFromSelectsizeDropdownFrom550Onwards(String inputId, String selectValue,String inputId1) {
			WebElement inputEle = driver.findElement(By.xpath(String.format(tmpSelectsizeInputAfter550, inputId)));
			//inputEle.click();
			new JavaScriptHelper(driver).clickElement(inputEle);
			
			WebElement selectEle = driver.findElement(By.xpath(String.format(tmpSelectsizeDropdownValue550,inputId1,selectValue)));
			new TestBase().sleepFor(1000);
			waitHelper.waitForElementClickable(driver, selectEle, 30).click();
		}
		
		public void selectFromSelectsizeDropdownFrom550Onwards_forItem(String inputId, String selectValue,String inputId1) {
			WebElement inputEle = driver.findElement(By.xpath(String.format(tmpSelectsizeInputAfter550, inputId)));
			//inputEle.click();
			new JavaScriptHelper(driver).clickElement(inputEle);
			
			WebElement selectEle = driver.findElement(By.xpath(String.format(tmpSelectsizeDropdownValue550_1,inputId1,selectValue)));
			new TestBase().sleepFor(1000);
			waitHelper.waitForElementClickable(driver, selectEle, 30).click();
		}
		
		public void selectFromAutocompleDDWithoutInputForUser(String selectItem, WebElement buttonEle) {
			buttonEle.click();
			//new JavaScriptHelper(driver).clickElement(buttonEle);
			try {
			waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li[@class='selected active']/a")),10);
			}
			catch(Exception ex) {log.info("No Active selected element");	}
			WebElement DDlocatorEle = waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li/a/span[text()='"+selectItem+"']")),10);
			new ActionHelper(driver).moveToElementAndClick(DDlocatorEle);
		}
		
		public void selectFromAutocompleDDWithoutInputForJobRole(String selectItem, WebElement buttonEle) {
			//buttonEle.click();
			new TestBase().click(buttonEle, "Clicking on drop down button");
			//new JavaScriptHelper(driver).clickElement(buttonEle);
			try {
				Thread.sleep(1000);
			waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li[@class='selected active']/a")),10);
			}
			catch(Exception ex) {log.info("No Active selected element");	
			WebElement DDlocatorEle = buttonEle.findElement(By.xpath("./..//li/a/span[text()='"+selectItem+"']"));
			new ActionHelper(driver).moveToElementAndClick(DDlocatorEle);
			}
			
		}

		public void presetTheShowRowsToTop(String configValue) throws InterruptedException {
			String[] configValueArray = configValue.split(", "); 
			Actions actions = new Actions(driver);

			waitHelper.waitForElementClickable(driver, showRowsDD, 35);
			new TestBase().click(showRowsDD, "Clicking on show rows drop down");
			Thread.sleep(200);
			for(int k=8; k>=0; k--) {
				actions.sendKeys(Keys.UP).build().perform();
				Thread.sleep(200);
			}
			actions.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(200);
		}
		
		public void selectFromAutocompleDDWithoutInputForShowRows(String configValue) throws InterruptedException {
			String[] configValueArray = configValue.split(", "); 
			Actions actions = new Actions(driver);
					
			String expSearchResultIntrayDataTableValue1 = configValueArray[0];					//"35";
			String tmpshowRowsDDXpath = String.format(tmpshowRowsDD, expSearchResultIntrayDataTableValue1);
			WebElement ShowRowsDD = driver.findElement(By.xpath(tmpshowRowsDDXpath));
					
			waitHelper.waitForElementClickable(driver, showRowsDD, 35);
			new TestBase().click(showRowsDD, "----> Clicking on show rows drop down");
			Thread.sleep(200);
	
			waitHelper.waitForElementVisible(ShowRowsDD, 35, 2);		//		Clickable(driver, ShowRowsDD, 35);
			actions.sendKeys(Keys.DOWN).build().perform();
			Thread.sleep(200);
			actions.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(200);
		}	
		

//		public void presetTheShowRowsToTop(String configValue) throws InterruptedException {
//			String[] configValueArray = configValue.split(", "); 
//			Actions actions = new Actions(driver);
//
////			Preset the show rows to top
//				waitHelper.waitForElementClickable(driver, showRowsDD, 35);
//				new TestBase().click(showRowsDD, "Clicking on show rows drop down");
//				Thread.sleep(200);
//				for(int k=8; k>=0; k--) {
//					actions.sendKeys(Keys.UP).build().perform();
//					Thread.sleep(200);
//				}
//				actions.sendKeys(Keys.ENTER).build().perform();
//				Thread.sleep(200);
//				
////				String expSearchResultIntrayDataTableValue = configValueArray[0];					//"35";
////				String actSearchResultIntrayDataTable = searchResultIntrayDataTable_info.getText();
////				String[] actSearchResultIntrayDataTableValue = actSearchResultIntrayDataTable.split(" ");  
//////				verification
////				boolean actSearchResultIntrayDataTableValueCompare = expSearchResultIntrayDataTableValue.equals(actSearchResultIntrayDataTableValue[2]);
////				if (actSearchResultIntrayDataTableValueCompare==false) 
////				{
////					AssertionHelper.verifyText(actSearchResultIntrayDataTableValue[2], actSearchResultIntrayDataTableValue[4]);
////				}
////				else 
////				{
////					AssertionHelper.verifyText(expSearchResultIntrayDataTableValue, actSearchResultIntrayDataTableValue[2]);
////				}
////				Thread.sleep(3000);	
//		}
//		
//		public void selectFromAutocompleDDWithoutInputForShowRows(String configValue) throws InterruptedException {
//				String[] configValueArray = configValue.split(", "); 
//				Actions actions = new Actions(driver);
//
//////				Preset the show rows to top
////					waitHelper.waitForElementClickable(driver, showRowsDD, 35);
////					new TestBase().click(showRowsDD, "Clicking on show rows drop down");
////					Thread.sleep(1000);
////					for(int k=8; k>=0; k--) {
////						actions.sendKeys(Keys.UP).build().perform();
////						Thread.sleep(1000);
////					}
////					actions.sendKeys(Keys.ENTER).build().perform();
////					Thread.sleep(1000);
////					
////					String expSearchResultIntrayDataTableValue = configValueArray[0];					//"35";
////					String actSearchResultIntrayDataTable = searchResultIntrayDataTable_info.getText();
////					String[] actSearchResultIntrayDataTableValue = actSearchResultIntrayDataTable.split(" ");  
//////					verification
////					boolean actSearchResultIntrayDataTableValueCompare = expSearchResultIntrayDataTableValue.equals(actSearchResultIntrayDataTableValue[2]);
////					if (actSearchResultIntrayDataTableValueCompare==false) 
////					{
////						AssertionHelper.verifyText(actSearchResultIntrayDataTableValue[2], actSearchResultIntrayDataTableValue[4]);
////					}
////					else 
////					{
////						AssertionHelper.verifyText(expSearchResultIntrayDataTableValue, actSearchResultIntrayDataTableValue[2]);
////					}
////					Thread.sleep(3000);
//					
////				for(int i = 1; i < configValueArray.length; i++){
//					String expSearchResultIntrayDataTableValue1 = configValueArray[0];					//"35";
//					String tmpshowRowsDDXpath = String.format(tmpshowRowsDD, expSearchResultIntrayDataTableValue1);
//					WebElement ShowRowsDD = driver.findElement(By.xpath(tmpshowRowsDDXpath));
//					
//					waitHelper.waitForElementClickable(driver, showRowsDD, 35);
//					new TestBase().click(showRowsDD, "----> Clicking on show rows drop down");
//					Thread.sleep(200);
//	//				WebElement DDlocatorEle = waitHelper.waitForElement(intrayTools.showRowsDD.findElement(By.xpath("//option[@value='"+configValueArray[i]+"']")), 35);
//	//				WebElement DDlocatorEle = waitHelper.waitForElement(intrayTools.showRowsDD.findElement(By.xpath("//option[@value='"+expSearchResultIntrayDataTableValue+"']")), 35);
//	//				WebElement DDlocatorEle = waitHelper.waitForElement(intrayTools.showRowsDD.findElement(By.xpath("//option[text()='"+expSearchResultIntrayDataTableValue+"']")), 35);
//	//				waitHelper.waitForElementClickable(driver, intrayTools.showRowsDD.findElement(By.xpath("//option[@value='"+expSearchResultIntrayDataTableValue+"']")), 35);
//	//				WebElement DDlocatorEle = waitHelper.waitForElementClickable(driver, intrayTools.showRowsDD.findElement(By.xpath("//option[@value='"+expSearchResultIntrayDataTableValue+"']")), 35);
//	
//					waitHelper.waitForElementVisible(ShowRowsDD, 35, 2);		//		Clickable(driver, ShowRowsDD, 35);
//	//				actions.keyDown(ShowRowsDD, tmpshowRowsDDXpath);
//	//				Thread.sleep(1000);
//	//				actions.moveToElement(ShowRowsDD);
//					actions.sendKeys(Keys.DOWN).build().perform();
//					Thread.sleep(200);
//					actions.sendKeys(Keys.ENTER).build().perform();
//					Thread.sleep(200);
//	
//					String actSearchResultIntrayDataTable1 = searchResultIntrayDataTable_info.getText();
//					String[] actSearchResultIntrayDataTableValue1 = actSearchResultIntrayDataTable1.split(" ");  
//					
//////					verification
////					boolean actSearchResultIntrayDataTableValueCompare1 = expSearchResultIntrayDataTableValue1.equals(actSearchResultIntrayDataTableValue1[2]);
////					if (actSearchResultIntrayDataTableValueCompare1==false) 
////					{
////						AssertionHelper.verifyText(actSearchResultIntrayDataTableValue1[2], actSearchResultIntrayDataTableValue1[4]);
////					}
////					else 
////					{
////						AssertionHelper.verifyText(expSearchResultIntrayDataTableValue1, actSearchResultIntrayDataTableValue1[2]);
////					}
////					Thread.sleep(3000);
////				}
//			
////			//buttonEle.click();
////			Thread.sleep(3000);
////			new TestBase().click(buttonEle, "Clicking on drop down button");
////			//new JavaScriptHelper(driver).clickElement(buttonEle);
////			Thread.sleep(2000);
////			try {
////				Thread.sleep(1000);
////				waitHelper.waitForElement(buttonEle.findElement(By.xpath("//option[@value='"+selectItem+"']")),35);
////			}
////			catch(Exception ex) {log.info("No Active selected element");	
////				Thread.sleep(1000);
////				
////				WebElement DDlocatorEle = driver.findElement(By.xpath("//select[@name='searchResultIntrayDataTable_length']//option[@value='"+selectItem+"']"));
//////				WebElement DDlocatorEle = buttonEle.findElement(By.xpath("//select[@name='searchResultIntrayDataTable_length']//option[text()='"+selectItem+"']"));
////				Thread.sleep(1000);
////				new ActionHelper(driver).moveToElementAndClick(DDlocatorEle);
////			}
//		}	
		
		
		
		
		
		
		
		
		
		
		public void selectFromAutocompleDDWithoutInputForShowRows1(String selectItem, WebElement buttonEle, String getEnvVariable) {

			if (getEnvVariable.equals("Enterprise_R551")) {
				new TestBase().click(buttonEle, "Clicking on drop down button");
				WebElement DDlocatorEle = waitHelper.waitForElement(buttonEle.findElement(By.xpath("//option[@value='"+selectItem+"']")), 35);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("InsideDDD*********************"+DDlocatorEle.toString());
				new ActionHelper(driver).moveToElementAndClick(DDlocatorEle);
			} else {
				new TestBase().click(buttonEle, "Clicking on drop down button");
				try {
					waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//option[@value='"+selectItem+"']")),	35);
				} catch (Exception ex) {
					log.info("No Active selected element");
				}
				WebElement DDlocatorEle = waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//option[@value='"+selectItem+"']")), 35);
				new ActionHelper(driver).moveToElementAndClick(DDlocatorEle);
			}
		}
		 
		/* This method helps to select from autocomplete dropdown in EDRM appln
		 * 
		 * @param inputText : search input which you want to enter in dropdown
		 * @param :         dropdownbtn dropdown button element  from R551 onwards
		 * @throws InterruptedException 
		 */
		public void selectFromAutocompleDDWithoutInput(String selectItem, WebElement buttonEle, String getEnvVariable) {

			if (getEnvVariable.equals("Enterprise_R551")) {
				//new TestBase().click(buttonEle, "Clicking on drop down button");
				WebElement DDlocatorEle = waitHelper.waitForElement(
						buttonEle.findElement(By.xpath("//li//a//span[text()='" + selectItem + "']")), 10);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("InsideDDD*********************"+DDlocatorEle.toString());
				new ActionHelper(driver).moveToElementAndClick(DDlocatorEle);

			} else {
				new TestBase().click(buttonEle, "Clicking on drop down button");
				try {
					waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li[@class='selected active']/a")),
							10);
				} catch (Exception ex) {
					log.info("No Active selected element");
				}
				WebElement DDlocatorEle = waitHelper.waitForElement(
						buttonEle.findElement(By.xpath("./..//li/a/span[text()='" + selectItem + "']")), 10);
				new ActionHelper(driver).moveToElementAndClick(DDlocatorEle);
			}

		}

		/**
		 * This method helps to select from autocomplete dropdown in EDRM appln
		 * 
		 * @param inputText : search input which you want to enter in dropdown
		 * @param :         dropdownbtn dropdown button element
		 * @throws InterruptedException
		 */
		public void selectFromAutocompleDD(String inputText, WebElement buttonEle, String getEnvVariable) {
			//new JavaScriptHelper(driver).clickElement(buttonEle);

			if (getEnvVariable.equals("Enterprise_R551")) {
				buttonEle.findElement(By.xpath("./following-sibling::div[contains(@class,'bs-container dropdown bootstrap-select')]//input"))
						.sendKeys(inputText);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				WebElement ele = buttonEle
						.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]//a"));
				new JavaScriptHelper(driver).clickElement(ele);
			} else {
				new JavaScriptHelper(driver).clickElement(buttonEle);
				try {
					// waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li[@class='selected
					// active']/a")), 10);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					log.info("Exception in thread sleep " + e);
				}
				buttonEle.findElement(By.xpath("./following-sibling::div//input")).sendKeys(inputText);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					log.info("Exception in thread sleep " + e);
				}

				// buttonEle.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]/a")).click();
				WebElement ele = buttonEle
						.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]/a"));
				new JavaScriptHelper(driver).clickElement(ele);

			}
		}
	public void selectFromAutocompleDD1(String inputText, WebElement buttonEle, String getEnvVariable) {
			//new JavaScriptHelper(driver).clickElement(buttonEle);

			if (getEnvVariable.equals("Enterprise_R551")) {
				buttonEle.findElement(By.xpath("./following-sibling::div[contains(@class,'bs-container dropdown bootstrap-select')]//input"))
						.sendKeys(inputText);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				WebElement ele = buttonEle
						.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]//a"));
				new JavaScriptHelper(driver).clickElement(ele);
			} else {
				new JavaScriptHelper(driver).clickElement(buttonEle);
				try {
					// waitHelper.waitForElement(buttonEle.findElement(By.xpath("./..//li[@class='selected
					// active']/a")), 10);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					log.info("Exception in thread sleep " + e);
				}
				buttonEle.sendKeys(inputText);														//findElement(By.xpath("./following-sibling::div//input")).
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					log.info("Exception in thread sleep " + e);
				}

				// buttonEle.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]/a")).click();
				WebElement ele = buttonEle
//						.findElement(By.xpath("./following-sibling::div//li[contains(@class,'active')]/a"));
				.findElement(By.xpath("./ancestor::div/following-sibling::div//li[contains(@class,'active')]/a"));
				new JavaScriptHelper(driver).clickElement(ele);
				WebElement clickOnPlusIcon = driver.findElement(By.xpath("//a[@data-bs-original-title='Add Available Team']"));
				new JavaScriptHelper(driver).clickElement(clickOnPlusIcon);
			}
		}

	
}
