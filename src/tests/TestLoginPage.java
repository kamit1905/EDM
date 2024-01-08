package tests;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.pageObject.LoginPage;

public class TestLoginPage extends TestBase {
	
	public LoginPage login;
	public ExcelReader xls;
	public ExtentTest test;
	
	@BeforeClass
	public void setupClass()  {
		login = new LoginPage(driver);
		xls = new ExcelReader();
	}
	
	@Test( enabled=true,priority=0, description = "This function tests the valid flow of login", groups = "smoke")
	public void TestLoginFunctionality() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		String username = ObjectReader.reader.getLoginId();
		String password = ObjectReader.reader.getPassword();
		login.loginWithCredentials(username,password);
		String welcomeActual = login.getWelcomeText();
		String usernameActual = login.getUsernameText();
		AssertionHelper.verifyText(welcomeActual, "Welcome");
		System.out.println("No record message is "+ObjectReader.reader.getNoRecordMessage());
		AssertionHelper.verifyText(usernameActual, username);
	}
	
	
	@DataProvider(name="userFormData")
	public Object[][] userDetails() throws Exception
	{
		Object[][] formData = xls.readExcelToMapWithTestCaseId(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","TestBased"
				+ "","TC1");
				
		return formData;
	}
	
	@Test( enabled=false,dataProvider = "userFormData")
	public void TestExcelReadingByTestCase1(Map<String, String> map) {
		String filepath =map.get("FilePath"),
				  accountRefValue = map.get("AccountRefValue"),
				  routingType=map.get("RoutingType")
				  ;
		System.out.println("I am in TestExcelReadingByTestCase1");
		/*
		 * System.out.println("File path is "+filepath);
		 * System.out.println("acc ref value is "+accountRefValue);
		 * System.out.println("routing type is "+routingType);
		 * System.out.println("doc ref is "+docRef);
		 */
	}
	
	@DataProvider(name="folderRefData")
	public Object[][] folderRefDetails() throws Exception
	{
		Object[][] formData = xls.readExcelWithTestCaseId(ResourceHelper.getResourcePath("src\\testdata\\"),"EDRM.xlsx","TestBased","TC-2");
				
		return formData;
	}
	
	@Test( enabled=false,dataProvider = "folderRefData")
	public void TestExcelReadingByTestCase2(String docType, String folder2ref, String docref ) {
		System.out.println("I am in TestExcelReadingByTestCase2");
		System.out.println("doctype is "+docType);
		System.out.println("folder2 ref is "+folder2ref);
		System.out.println("doc ref is "+docref);
	}
	
}
