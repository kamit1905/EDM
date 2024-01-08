package tests;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Toolkit.ToolkitCaseManagement;
import utils.ExtentReports.ExtentTestManager;

public class TestDeleteDocument extends TestBase {
	
	public IntrayToolsPage intrayTools ;
	private Logger log = LoggerHelper.getLogger(TestIntrayTools.class);
	public CapturePage capture;
	public LoginPage login;
	public ExcelReader xls;
	public DocumentToolsPage docTools ;
	public static NavigationMenu navigationMenu;
	
	
	@BeforeClass
	public void setupClass()  {
		intrayTools = new IntrayToolsPage(driver);
		capture = new CapturePage(driver);
		login = new LoginPage(driver);
		xls = new ExcelReader();
		docTools = new DocumentToolsPage(driver);
		navigationMenu = new NavigationMenu(driver);
	}
	
	
	/*@Test( enabled=true,priority=0, description = "This function tests the valid flow of login", groups = "smoke")
	public void TestLoginFunctionality() throws InterruptedException {
		String username = ObjectReader.reader.getLoginId();
		String password = ObjectReader.reader.getPassword();
		login.loginWithCredentials(username,password);
		String welcomeActual = login.getWelcomeText();
		String usernameActual = login.getUsernameText();
		AssertionHelper.verifyText(welcomeActual, "Welcome");
		System.out.println("No record message is "+ObjectReader.reader.getNoRecordMessage());
		AssertionHelper.verifyText(usernameActual, username);
	}*/
	
	
	@DataProvider(name="DeleteDocumentData")
	public Object[][] DeleteDocumentData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","DeleteDoc");
		return formData;
	}
	
	
	@Test(priority=1,enabled = true,dataProvider = "DeleteDocumentData")			//true
	public void TestIntrayDocumentDelete(Map<String, String> map) throws InterruptedException {
			test = ExtentTestManager.getTest();
			String expMsg = "Document(s) deleted successfully",
					delDocRef=map.get("DocRef");
		
			getApplicationUrl();
			capture.selectSearchTab();
			capture.searchWithCriteria("Doc Ref",delDocRef );
			capture.clickOnDocumentListBtn();
			capture.clickOnFirstItemOfIntray();
			String actMsg = intrayTools.clickOnDocumentDelete();
			//System.out.println("actMsg "+actMsg);
			AssertionHelper.verifyTextContains(actMsg, expMsg);	
	}
	
}
