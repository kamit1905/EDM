package tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import main.EDRM.hybridFramework.ExcelReader.ExcelReader;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.AssertionHelper;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.DocumentToolsPage;
import main.EDRM.hybridFramework.pageObject.FolderFlagReference;
import main.EDRM.hybridFramework.pageObject.HomePage;
import main.EDRM.hybridFramework.pageObject.IntrayToolsPage;
import main.EDRM.hybridFramework.pageObject.LoginPage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import main.EDRM.hybridFramework.pageObject.Admin.AdminDocumentSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminUserSection;
import main.EDRM.hybridFramework.pageObject.Admin.AdminWorkflowSection;
import main.EDRM.hybridFramework.pageObject.Admin.Allocation;
import main.EDRM.hybridFramework.pageObject.Admin.Group;
import main.EDRM.hybridFramework.pageObject.Admin.Teams;
import main.EDRM.hybridFramework.pageObject.Admin.Role;

/*
 * Suite created by Sagar
 */

public class TestRangeRouting_UAT extends TestBase  
{
	public DocumentToolsPage docTools;
	public CapturePage capture;
	public HomePage home;
	public ExcelReader xls;
	public ExtentTest test;
	public static NavigationMenu navigationMenu;
	public AdminUserSection adminUser;
	public AdminUserSection adminUserSection;
	public AdminDocumentSection adminDoc;
	public AdminWorkflowSection workflow;
	
	public Group group;
	public Teams teams;
	public Role role; 
	public Allocation allocation;
	AlertHelper alertHelper;
	WindowHelper windowHelper;
	WaitHelper waitHelper;
	AdminDocumentSection adminDocument;
	IntrayToolsPage intrayTools;
	public LoginPage login;
	public FolderFlagReference folderPage;
	
	private String fileSystemAdminUserName;
	private String fileSystemAdminUserName1;
	
	String Role, RoleName,TeamId,TeamName,EditedTeamName,statusCode,statusDescription,
	statusDescriptionEdited,docType,docDescription,groupName,typeId,editedGroupName,folderName,
	workflowType, template, existingRuleName, ruleName, documentType, versionComment;
	
	String folder1Ref, folder2Ref, folder3Ref, fold3entityName, StreetValue,StreetValue1, StreetValue2, docRef, docRef1;
	String threeDigitRandomNo, fourDigitRandomNo;
	
	@BeforeClass
	public void setupClass() {
		docTools = new DocumentToolsPage(driver);
		capture = new CapturePage(driver);
		home = new HomePage(driver);
		navigationMenu = new NavigationMenu(driver);
		alertHelper = new AlertHelper(driver);
		windowHelper = new WindowHelper(driver);
		adminDocument = new AdminDocumentSection(driver);
		xls = new ExcelReader();
		intrayTools = new IntrayToolsPage(driver);
		waitHelper = new WaitHelper(driver);
		login = new LoginPage(driver);
		folderPage = new FolderFlagReference(driver);
		adminUser = new AdminUserSection(driver);
		adminDoc = new AdminDocumentSection(driver);
		group = new Group(driver);
		teams = new Teams(driver);
		role = new Role(driver);
		allocation = new Allocation(driver);
		workflow = new AdminWorkflowSection(driver); 
	}
	
	@DataProvider(name="folderRefData")
	public Object[][] folderReferenceData() throws Exception
	{
		Object[][] formData = xls.readExcelToMap(ResourceHelper.getResourcePath( "src\\testdata\\"),"EDRM.xlsx","FolderRefData");
		return formData;
	}
	
	@Test(priority = 1,enabled = true,dataProvider="folderRefData")
	public void TestCreateFolder3Ref(Map<String, String> map) throws InterruptedException {     
		threeDigitRandomNo = generateThreeDigitRandomNumberForRangeRouting();				//generateThreeDigitRandomNumber();
		fourDigitRandomNo = generateFourDigitRandomNumberForRangeRouting();				// generateRandomNumber();
		fold3entityName = ObjectReader.reader.getFolder3RefName();
				
		folder3Ref=map.get("AddFolder3Ref")+fourDigitRandomNo;
				
		StreetValue= "S0"+generateThreeDigitRandomNumberForRangeRouting();
		StreetValue1= "S"+generateFourDigitRandomNumberForRangeRouting();
		StreetValue2 = "S1"+generateFourDigitRandomNumberForRangeRouting();	
			
		folderPage.addFolder3Reference(fold3entityName, StreetValue);
		sleepFor(4000);
		folderPage.addFolder3Reference(fold3entityName, StreetValue1);
		sleepFor(4000);
		folderPage.addFolder3Reference(fold3entityName, StreetValue2);
		sleepFor(4000);
	}
	
	@Test(priority = 2, enabled = true,dataProvider="folderRefData")
	public void newAllocationRulesCanBeCreated(Map<String, String> map) throws InterruptedException {
		Role = "R_"+ fourDigitRandomNo;
		RoleName = Role; 		//"RN_"+ Role;
		TeamId = "TID_"+ fourDigitRandomNo;
		TeamName = TeamId; 		//"TN_"+ TeamId;
		groupName = "GN"+ fourDigitRandomNo;
		typeId = "Street";																									//"Str_"+ fourDigitRandomNo;		
		workflowType = "Predefined";
		template = "Street";
		fileSystemAdminUserName = "FSA_"+fourDigitRandomNo;
		fileSystemAdminUserName1 = fileSystemAdminUserName + "_2";
		ruleName = "Rule set "+ fourDigitRandomNo;
		documentType = "Street - Street";																					//groupName+" / "+typeId+" - "+typeId+"_description";
		versionComment = "Version comment for the rule name "+ ruleName;
		
		docRef = "RR_"+fourDigitRandomNo;
		docRef1 = docRef+"_1";

		boolean createRef = true;
		String docType = "Street - Street", mailOption = "Mail", userType = "Workflow", userName = "Street", teamName = "Street";


		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);

//		pre-requisite
		role.addNewRole(Role, RoleName);																					//		add Role
		teams.addTeam(TeamId, TeamName, RoleName, getEnvVariable);												//		add team
		group.AddDocumentTypeGroup(groupName);																				//		add Group
		
		adminDoc.addDocumentType(typeId, groupName, getEnvVariable);
		adminDoc.editDocumentType(typeId, groupName, Role, workflowType, template, getEnvVariable);							//		edit document type

		adminUser.AddStandardUser(fileSystemAdminUserName,getEnvVariable);
		adminUser.EditUserWithTeamAndRole(fileSystemAdminUserName, RoleName, TeamName);	//basic user or standard user use this

		adminUser.AddStandardUser(fileSystemAdminUserName1,getEnvVariable);
		adminUser.EditUserWithTeamAndRole(fileSystemAdminUserName1, RoleName, TeamName);

		allocation.addAllocation(ruleName, documentType, Role, TeamId, fileSystemAdminUserName, fileSystemAdminUserName1); 	//		add allocation
		workflow.editWorkflowTemplateForRangeRouting(template, Role, ruleName, versionComment, getEnvVariable);				//		set workflow template for allocated rule

//		capture document using indexing with property ref value
		getApplicationUrl();
        sleepFor(1000);
		capture.navigateAndCaptureDocWithParameters(null, null, docType, docRef, mailOption, "", userType, userName, teamName, createRef, StreetValue, getEnvVariable);

//		verification
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Property Ref",StreetValue);
		capture.clickOnIntrayListBtn();
        sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
        sleepFor(20000);
		String ActualUserID = navigationMenu.getColumnValueFromTable("User ID");			
		AssertionHelper.verifyText(fileSystemAdminUserName, ActualUserID);	
		
		getApplicationUrl();
        sleepFor(1000);
        capture.navigateAndCaptureDocWithParameters(null, null, docType, docRef1, mailOption, "", userType, userName, teamName, createRef, StreetValue1, getEnvVariable);

//		verification --> property ref is allocated to user which is mapped by rule
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Property Ref",StreetValue1);
		capture.clickOnIntrayListBtn();
        sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
        sleepFor(20000);
		String ActualUserID1 = navigationMenu.getColumnValueFromTable("User ID");			
		AssertionHelper.verifyText(fileSystemAdminUserName1, ActualUserID1);				
	}
	
	@Test(priority = 3, enabled = true)
	public void existingRulesAreVisibleInNECDM() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		existingRuleName = ruleName; 																	//"Rule set 4"; 	//ruleName; //"Rule set 1";

		getApplicationUrl();
		sleepFor(1000);
		navigationMenu.clickOnTab("Administration");
		navigationMenu.clickOnIconMenu("Mail Allocation rules maintenance", "Workflow");
		sleepFor(2000);
		
		navigationMenu.searchInFilter(existingRuleName);
		sleepFor(1000);
		navigationMenu.clickOnFilteredRow("mailRoutingRuleSetTable");
	}
	
	@Test(priority = 4, enabled = true)
	public void existingRulesCanBeEdited() throws InterruptedException {
		String getEnvVariable = System.getProperty("propertyName");
		System.out.println("getEnvVariable===================="+getEnvVariable);
		boolean createRef = true;
		String docType = "Street - Street", mailOption = "Mail", userType = "Workflow", userName = "Street", teamName = "Street";

		allocation.editAllocation(ruleName, documentType, Role, TeamId, fileSystemAdminUserName, fileSystemAdminUserName1, "S10000", "S99999");
		System.out.println(versionComment);
		workflow.editWorkflowTemplateForRangeRouting(template, Role, ruleName, versionComment+"_1", getEnvVariable);				//		set workflow template for allocated rule
		
		capture.navigateAndCaptureDocWithParameters(null, null, docType, docRef1+"_1", mailOption, "", userType, userName, teamName, createRef, StreetValue2, getEnvVariable);

//		verification --> property ref is allocated to user which is mapped by rule
		getApplicationUrl();
		capture.selectSearchTab(); 
		capture.searchWithCriteria("Property Ref",StreetValue2);
		capture.clickOnIntrayListBtn();
        sleepFor(2000);
		capture.clickOnFirstItemOfIntray();
        sleepFor(20000);
		String ActualUserID = navigationMenu.getColumnValueFromTable("User ID");			
		AssertionHelper.verifyText(fileSystemAdminUserName, ActualUserID);	
	}
	
	

}
