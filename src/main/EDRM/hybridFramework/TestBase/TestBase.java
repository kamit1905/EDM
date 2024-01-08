package main.EDRM.hybridFramework.TestBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.BrowserType;
import main.EDRM.hybridFramework.helper.browserConfigurations.ChromeBrowser;
import main.EDRM.hybridFramework.helper.browserConfigurations.FirefoxBrowser;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.PropertyReader;
import main.EDRM.hybridFramework.helper.excel.ExcelHelper;
import main.EDRM.hybridFramework.helper.javaScript.JavaScriptHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import utils.ExtentReports.ExtentManager;
import utils.ExtentReports.ExtentTestManager;

import org.apache.commons.io.IOUtils;


import java.util.Base64;
import java.io.FileInputStream;

public class TestBase {

	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectery;
	public static WaitHelper waitHelper;
	public static AlertHelper alertHelper;
	public static String baseUrl;
	public static HomePage home;
	
	@FindBy(how=How.XPATH,using="//*[@class='modal-dialog-busy']")
	public WebElement busyDialogIcon ;
	
	@FindBy(how=How.ID,using="dialogTitle")
		public WebElement dialogTitleId;
	
	@BeforeSuite
	public void beforeSuite() throws Exception{
		ObjectReader.reader = new PropertyReader();
		extent = ExtentManager.getInstance();
		PageFactory.initElements(driver, this);
		reportDirectery = new File(ResourceHelper.getResourcePath("src\\main\\EDRM\\resources\\screenShots"));
		setUpDriver(ObjectReader.reader.getBrowserType());
		waitHelper=new WaitHelper(driver);
		alertHelper = new AlertHelper(driver);
		test = extent.createTest(getClass().getSimpleName());
		home=new HomePage(driver);
		//getApplicationUrl1();				//Uncomment this line while running on https & comment below line
		getApplicationUrl();
	}
	
	
	
	@BeforeMethod
	public void beforeMethod(Method method){
		test = ExtentTestManager.startTest( method.getName());
		test.log(Status.INFO, method.getName()+"**************test started***************");
		log.info("**************"+method.getName()+"Started***************");
		if(baseUrl.contains("edm-app-2012")) {
			//Avoiding in case of third party app
			getApplicationUrl(true);
		}
		//alertHelper.acceptAlertIfPresent();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException{
		//alertHelper.acceptAlertIfPresent();
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(),driver);
			
			//test.addScreenCaptureFromPath(imagePath);
			test.addScreenCaptureFromBase64String(imagePath);
			test.fail(MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
			new WindowHelper(driver).clickOkOnPopupIfExist();					//For already exist popup								
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(Status.PASS, result.getName()+" is pass");
			//String imagePath = captureScreen(result.getName(),driver);
			//test.addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, result.getThrowable());
		}
		log.info("**************"+result.getName()+"Finished***************");
		//Checking whether any extra tabs are open and closing them
		Set<String> handles = driver.getWindowHandles();
		if(handles!=null && handles.size()>1) {
		new WindowHelper(driver).closeAllTabsAndSwitchToMainWindow();
		}
		alertHelper.acceptAlertIfPresent();
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		
	}
	
	@AfterSuite
	public void afterTest() throws Exception{
		if(driver!=null){
			//driver.quit();
		}	
	}
	
	public WebDriver getBrowserObject(BrowserType btype) throws Exception{
		
		try{
			switch(btype){
			case Chrome:
				// get object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(options);
			
				/**
			case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);
				**/
			default:
				throw new Exception("Driver not Found: "+btype.name());
			}
		}
		catch(Exception e){
			log.info(e.getMessage());
			throw e;
		}
	}
	
	public void setUpDriver(BrowserType btype) throws Exception{
		driver = getBrowserObject(btype);
		log.info("Initialize Web driver: "+driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		//wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.setPageLoadTimeout(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public String captureScreen(String fileName, WebDriver driver){
		byte [] imageBytes = null;
		if(driver == null){
			log.info("driver is null..");
			return null;
		}
		if(fileName==""){
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			destFile = new File(reportDirectery+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			//Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
			imageBytes = IOUtils.toByteArray(new FileInputStream(destFile));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//return destFile.toString();
		return Base64.getEncoder().encodeToString(imageBytes);
	}

	
	public void getNavigationScreen(WebDriver driver) {
		log.info("capturing ui navigation screen...");
		//new JavaScriptHelper(driver).zoomInBy60Percentage();
		 String screen = captureScreen("", driver);
		 //new JavaScriptHelper(driver).zoomInBy100Percentage();
		 try {
			test.addScreenCaptureFromPath(screen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logExtentReport(String s1){
		test.log(Status.INFO, s1);
	}
	
	public String getBaseURL() {
		return ObjectReader.reader.getUrl();
	}
	
	public void getApplicationUrl(){
		 baseUrl=ObjectReader.reader.getUrl();
		try {
		driver.get(baseUrl);
		alertHelper.acceptAlertIfPresent();
		}
		catch(Exception ex){
			log.info("In catch of getApplicationUrl");
			alertHelper.acceptAlertIfPresent();
		}
		logExtentReport("navigating to ..."+baseUrl);
	}
	
	public void getApplicationUrl(boolean isAlertPossible){
		try {
		driver.get(baseUrl);
		if(isAlertPossible) {
			alertHelper.acceptAlertIfPresent();
		}
		}
		catch(UnhandledAlertException ex){
			alertHelper.acceptAlertIfPresent();
		}
		logExtentReport("navigating to ..."+baseUrl);
	}
	
	public void getApplicationUrl1(){
		 baseUrl=ObjectReader.reader.getUrl1();
		try {
		driver.get(baseUrl);
		}
		catch(Exception ex){
			log.info("In catch of getApplicationUrl");
			alertHelper.acceptAlertIfPresent();
		}
		//logExtentReport("navigating to ..."+baseUrl);
	}
	
	public Object[][] getExcelData(String excelName, String sheetName){
		String excelLocation = ResourceHelper.getResourcePath("src/main/resources/configfile/")+excelName;
		log.info("excel location "+excelLocation);
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
	}
	/**
	 * Sendkeys common function
	 * @param txtElement Element in which text needs to be entered
	 * @param input input string in textbox
	 * @param logmsg logmsg for log file and extent report
	 */
	public void sendKeys( WebElement txtElement, String input, String logmsg) {
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		//txtElement.clear();
		new JavaScriptHelper(driver).clearText(txtElement);				//Added by amit because unable to clear user security level
		txtElement.sendKeys(input);
	}
	
	/**
	 * Sendkeys common function
	 * @param txtElement Element in which text needs to be entered
	 * @param input input string in textbox
	 * @param logmsg logmsg for log file and extent report
	 */
	public void sendKeysWithoutClear( WebElement txtElement, String input, String logmsg) {
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		txtElement.sendKeys(input);
	}
	
	/**
	 * 
	 * @param element Elment which needs to be clicked
	 * @param logmsg logmsg which will be logged in extent report and logger
	 */
	public void	click( WebElement element, String logmsg) {
		log.info(logmsg);
		ExtentTest test=ExtentTestManager.getTest();
		test.log(Status.INFO, logmsg);
		new WaitHelper(driver).waitForElementClickable(driver, element, 10);
		element.click();
	}
	
	/**
	 * Wait for invisibility of busy icon 
	 * @param timeout in seconds
	 */
	public void waitTillInvisiblityOfBusyIcon(int timeout) {
		try {
		waitHelper.waitForElementInvisible(driver,busyDialogIcon,timeout);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		}
	
	public void incrementCheveronValue(WebElement ele) {
		WebElement chevUp = ele.findElement(By.xpath("./following-sibling::span/span[contains(@class,'chevron-up')]"));
		click(chevUp, "Incrementing chevron value");
	}
	
	public void decrementCheveronValue(WebElement ele) {
		WebElement chevDown = ele.findElement(By.xpath("./following-sibling::span/span[contains(@class,'chevron-down')]"));
		click(chevDown, "decrementing chevron value");
	}
	public void refreshPage() {
		log.info("Refreshing page");
		driver.navigate().refresh();
		new AlertHelper(driver).acceptAlertIfPresent();
	}
	
	public void sleepFor(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void runExecutable(String executable) {
		try {
			Thread.sleep(2000);
		    Runtime.getRuntime().exec(executable);
		    Thread.sleep(2000);
		}
		catch (Exception e) {
		    log.info("Exception while running executable file "+executable+" Exception is " + e);
		}
	}
	
	/*
	 * It is used to generate 4 digit random number 
	 */
	public String generateRandomNumber() {
		
		Random rand = new Random();
	    log.info("Random numbers...");
	    int resRandom = rand.nextInt((9999 - 100) + 1);
	    log.info(resRandom);
	   return  String.valueOf(resRandom);
	}
	
	/*
	 * It is used to generate 2 digit random number 
	 */
	public String generateThreeDigitRandomNumber() {
		
		Random rand = new Random();
	    log.info("Random numbers...");
	    int resRandom = rand.nextInt((999 - 100) + 1);
	    log.info(resRandom);
	   return  String.valueOf(resRandom);
	}
	
	/*
	 * It is used to get date, subctract the days and format the date[used for nextMail]
	 */
	public String getDate(String days,String plusOrMinus) {
		String format ="";
		if(plusOrMinus.equals("Minus")) {
			java.time.LocalDate returnExpectedDate=subtractDaysAndReturnDate(days);
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/YYYY");
			format = returnExpectedDate.format(myFormatObj);
		}else {
			java.time.LocalDate returnExpectedDate=addDaysAndReturnDate(days);
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/YYYY");
			format = returnExpectedDate.format(myFormatObj);
		}
		
		return format;
	}
	
	
	public LocalDate subtractDaysAndReturnDate(String days) {
		java.time.LocalDate local = java.time.LocalDate.now();
		java.time.LocalDate returnDate = local.minusDays(Integer.parseInt(days));
		return returnDate;
	}
	
	public LocalDate addDaysAndReturnDate(String days) {
		java.time.LocalDate local = java.time.LocalDate.now();
		java.time.LocalDate returnDate = local.plusDays(Integer.parseInt(days));
		return returnDate;
	}
	
	public String removeLeadingZeroFromDate(String date) {
		String str=date;
		String regex = "^0+(?!$)";
		str = str.replaceAll(regex, "");
		return str;
	}
	
	  /**
	   * Find dynamic element on basis of tmpxapth and string value
	   * @param xpath temp xpath containgin %s
	   * @param variable String value which needs to be replaces
	   * @return
	   */
	 public WebElement findDynamicElement(String xpath, String variable) {
      log.info("Driver value is "+driver ); 
      return  driver.findElement(By.xpath(String.format(xpath, variable))); 
     } 
	 
	 
	 public boolean isWeekend(final LocalDate ld) {
	        DayOfWeek day = DayOfWeek.of(ld.get(ChronoField.DAY_OF_WEEK));
	        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
	 }
	 
	 /*
	  * convert from date string to actual date with given pattern
	  */
	 public String convertStringToDate(String pattern,String date) throws ParseException {
		 String expDate=date;
		 Date dateNew = new SimpleDateFormat("MM-dd-YYYY").parse(expDate);
		 return	new SimpleDateFormat(pattern).format(dateNew);
		 //return dateNew;
	 }
	 
	 public String convertDateToString(String pattern,Date date) {
		 Date dateVar = date;
		 DateFormat dateFormat = new SimpleDateFormat(pattern);
		 String strDate = dateFormat.format(dateVar);
		 return strDate;
	 }
	 
	 /*
	  * It is used to generate random number from 100 to 999 created by sagar
	  */
	 
		public String generateThreeDigitRandomNumberForRangeRouting() {
			
		    log.info("Random numbers...");
		    int resRandom = ((int)(Math.random() * 100000)) % 1000;
		    log.info(resRandom);
		   return  String.valueOf(resRandom);
		}
		
		/*
		 * It is used to generate random number from 1000 to 9999  created by sagar
		 */
		
		public String generateFourDigitRandomNumberForRangeRouting() { 
			Random rand = new Random();
		    log.info("Random numbers...");
		    int resRandom = rand.nextInt(9000) + 1000;
		    log.info(resRandom);
		   return  String.valueOf(resRandom);

		}

	 
}