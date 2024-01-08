package main.EDRM.hybridFramework.pageObject.Admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.helper.action.ActionHelper;
import main.EDRM.hybridFramework.helper.alert.AlertHelper;
import main.EDRM.hybridFramework.helper.assertion.VerificationHelper;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.select.DropdownHelper;
import main.EDRM.hybridFramework.helper.wait.WaitHelper;
import main.EDRM.hybridFramework.helper.window.WindowHelper;
import main.EDRM.hybridFramework.pageObject.CapturePage;
import main.EDRM.hybridFramework.pageObject.NavigationMenu;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;

public class AdminColdReport extends TestBase{
        
    private WebDriver driver;
            WaitHelper waitHelper;
            AlertHelper alertHelper;
            NavigationMenu navigationMenu;
            DropdownHelper dropdownHelper;
            VerificationHelper verificationHelper;
            WindowHelper windowHelper;
            Logger log = LoggerHelper.getLogger(AdminColdReport.class);
    
            @FindBy(how=How.XPATH,using="//input[@id='ReportType']")
            private WebElement txtReportTypeName;
            
            @FindBy(how=How.XPATH,using="//input[@id='Description']")
            private WebElement txtReportTypeDescription;
            
            @FindBy(how=How.XPATH,using="//button[@data-id='DocumentMode']")
            private WebElement btnDocumentModeType;
            
            @FindBy(how=How.XPATH,using="//button[@data-id='CompressionType']")
            private WebElement ddBtnCompressionType;
            
            @FindBy(how=How.XPATH,using="//input[@id='FileName']")
            private WebElement txtColdFileName;
             
            @FindBy(how=How.XPATH,using="//input[@id='Description']")
            private WebElement txtColdFileDescription;
            
            @FindBy(how=How.XPATH,using="//button[@data-id='ReportType']")
            private WebElement ddBtnColdReportType;
            
            @FindBy(how=How.XPATH,using="//input[@id='Description']")
            private WebElement txtColdGroupName;
            
            @FindBy(how=How.XPATH,using="//button[@data-id='FileNames']")
            private WebElement ddBtnFileNamesInGroup;
            
            @FindBy(how=How.XPATH,using="//button[@data-id='FileName']")
            private WebElement ddBtnFileNameInReportSearch;
            
            @FindBy(how=How.XPATH,using="//a[@data-icon='Add']")
            private WebElement lnkAddIcon;
            
            @FindBy(how=How.XPATH,using="//button[@data-id='coldGroup']")
            private WebElement ddBtn;
            
            @FindBy(how=How.XPATH,using="//button[@data-id='coldFileName']")
            private WebElement ddBtnFileNameBatchImport;
            
            @FindBy(how=How.XPATH,using="//input[@id='ReportDate']")
            private WebElement txtDatePickBatchImport;
            
            @FindBy(how=How.XPATH,using="//input[@id='IsClearDownDirectory']")
            private WebElement chkClearDownDirsColdImport;
            
            @FindBy(how=How.XPATH,using="//input[@id='IsCreateDirectory']")
            private WebElement chkCreateDirsColdImport;
            
            @FindBy(how=How.XPATH,using="//input[@id='IsAutoIndex']")
            private WebElement chkAutoIndexColdImport;
            
            @FindBy(how=How.XPATH,using="//input[@id='IsBatchImport']")
            private WebElement chkBatchImportColdImport;
            
            @FindBy(how=How.XPATH,using="//input[@id='IsAutoImport']")
            private WebElement chkAutoImportColdImport;
            
            /*************** Edit reference ***********/
            
            @FindBy(how=How.XPATH,using="//div[@class='modal-body']//input[@id='Name']")
            private WebElement txtNameReferenceConfig;
            
            @FindBy(how=How.XPATH,using="//div[@class='modal-body']//input[@id='Length']")
            private WebElement txtLengthReferenceConfig;
            
            @FindBy(how=How.XPATH,using="//div[@class='modal-body']//button[@data-id='FolderLink']")
            private WebElement ddBtnFolderLinkReferenceConfig;
            
            @FindBy(how=How.XPATH,using="//div[@class='modal-body']//input[@id='PictureFormat']")
            private WebElement txtFormatReferenceConfig;
            
            @FindBy(how=How.XPATH,using="//div[@class='modal-body']//button[@id='RowColumn']")
            private WebElement btnRowAndColumnFieldConfig;
            
            @FindBy(how=How.XPATH,using="//select[@id='referenceTypes']/option")
            private WebElement ddSelectOptionReferenceTypes;
            
            
    public AdminColdReport(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
        navigationMenu = new NavigationMenu(driver);
        dropdownHelper = new DropdownHelper(driver);
        verificationHelper = new VerificationHelper(driver);
        windowHelper = new WindowHelper(driver);
    }
    

    
    /**
     * Navigate to active document types then active section
     */
    public void navigateToAdminColdReportType() {
        navigationMenu.clickOnTab("Administration");
        navigationMenu.clickOnIcon("COLD Report Type maintenance","COLD Report");
         navigationMenu.waitForAddIcon();
    }
    
     /**
     * Navigate to active document types then active section
     */
    public void navigateToAdminColdReportFile() {
        navigationMenu.clickOnTab("Administration");
          navigationMenu.clickOnIcon("COLD Report File maintenance","COLD Report");
         navigationMenu.waitForAddIcon();
    }
    
     /**
     * Navigate to active document types then active section
     */
    public void navigateToAdminColdReportGroup() {
        navigationMenu.clickOnTab("Administration");
       navigationMenu.clickOnIcon("COLD Report Group maintenance","COLD Report");
         navigationMenu.waitForAddIcon();
    }
    

  public void navigateToDocumnetEditSection() {
    navigationMenu.clickOnFilteredRow("documentTypesTable");
    navigationMenu.clickOnEditIcon();
    navigationMenu.waitForIconWithDataButton("Save", "Actions");
    
  }

  public void clickOnAddIcon() {
   navigationMenu.clickOnIconUnderAction("Add");
   navigationMenu.waitForIconWithDataButton("Save", "Actions");
  }
  
  /**
   * Fill General section cold report type
   * @param reportType
   * @param reportTypeDescription
   */
  public void fillGeneralSectionInTypeForm(String reportType,String reportTypeDescription) {
    sendKeys(txtReportTypeName, reportType, "Entering report type as "+reportType);
    sendKeys(txtReportTypeDescription, reportTypeDescription, "Entering report type as "+reportTypeDescription);
    new DropdownHelper(driver).selectFromAutocompleDDWithoutInput("Document", btnDocumentModeType);
    new DropdownHelper(driver).selectFromAutocompleDDWithoutInput("No Compression", ddBtnCompressionType);
  }
  
  /**
   * Click on save icon and wait for add icon
   */
  public void clickOnSaveIconWaitForAddIcon() {
   navigationMenu.clickOnIconUnderAction("Save");
   navigationMenu.waitForAddIcon();
  }

   /**
   * Click on save icon
   */
  public void clickOnSaveIcon() {
   navigationMenu.clickOnIconUnderAction("Save");
  }

  
  public String getColdReportTypeName() {
    String actReportType = navigationMenu.getColumnValueFromTable("Report Type");
    return actReportType;
  }



  public void fillColdFileDetails(String fileName, String fileDescription, String reportType) {
    sendKeys(txtColdFileName, fileName, "Entering cold file name as "+fileName);
    sendKeys(txtColdFileDescription, fileDescription, "Entering file description as "+fileDescription);
    new DropdownHelper(driver).selectFromAutocompleDD(reportType, ddBtnColdReportType);
  }



  public String getColdReportFileName() {
   String actFileName = navigationMenu.getColumnValueFromTable("File Name");
    return actFileName;
  }



  public void fillColdGroupForm(String groupName, String fileName) {
    sendKeys(txtColdGroupName, groupName, "Entering cold group name as "+groupName);
    navigationMenu.clickOnNavTab("File Names");
    dropdownHelper.selectFromAutocompleDDWithoutInput(fileName.toUpperCase(), ddBtnFileNamesInGroup);
    click(lnkAddIcon, "Clicking on file name add");
  }



  public String getColdGroupName() {
    String actGroupName = navigationMenu.getColumnValueFromTable("Group Name");
    return actGroupName;
  }



  public void navigateToColdBatchImport() {
    navigationMenu.clickOnTab("Cold");
    navigationMenu.clickOnIcon("COLD Batch setup", "Cold");
    navigationMenu.waitForIconWithDataButton("Cancel", "Actions");
  }



  public void fillBatchImportForm(String groupName, String filename) {
    String pattern = "dd/MM/yyyy";
    String todaysDate =new SimpleDateFormat(pattern).format(new Date());
    new DropdownHelper(driver).selectFromAutocompleDD(groupName, ddBtn);
    new DropdownHelper(driver).selectFromAutocompleDD(filename, ddBtnFileNameBatchImport);
    sendKeys(txtDatePickBatchImport, todaysDate, "Entering todays date");
    click(lnkAddIcon, "Clicking on add icon of batch");
  }



  public String getBatchImportPopupMessage() {
    WindowHelper windowHelper = new WindowHelper(driver);
    String actPopupMsg = windowHelper.getPopupMessageClickOk("COLD Batch");
    return actPopupMsg;
  }
  
  public String getColdImportPopupMessage() {
    WindowHelper windowHelper = new WindowHelper(driver);
    String actPopupMsg = windowHelper.getPopupMessageClickOk("COLD Import");
    return actPopupMsg;
  }

  public void navigateToColdImportReports() {
    navigationMenu.clickOnTab("Cold");
    navigationMenu.clickOnIcon("Import COLD reports", "COLD");
    navigationMenu.waitForIconWithDataButton("Cancel", "Actions");
  }

  /**
   * 
   * @param isAutoIndex
   * @param isBatchImport
   * @param isAutoImport
   */
  public void fillColdImportForm(boolean isAutoIndex, boolean isBatchImport, boolean isAutoImport) {
   click(chkClearDownDirsColdImport, "Unchecking clear down directory import");
   click(chkCreateDirsColdImport, "Unchecking create dirs down directory import");
   if(!isAutoIndex)
     click(chkAutoIndexColdImport, "Unchecking auto index directory import");
   if(!isBatchImport)
     click(chkBatchImportColdImport, "Unchecking batch import directory import");
   if(!isAutoImport)
     click(chkAutoImportColdImport, "Unchecking auto Import checkbox");
   navigationMenu.clickOnIcon("Launch import process", "Actions");
  }



  public void navigateToColdImportSearch() {
    navigationMenu.clickOnTab("Cold");
    navigationMenu.clickOnIcon("COLD reports search", "COLD");
    navigationMenu.waitForIconWithDataButton("Cancel", "Actions");
  }



  public void searchDocAndCreateReference(String reportGroupName, String fileName, String referenceName) {
        new DropdownHelper(driver).selectFromAutocompleDD(reportGroupName, ddBtnColdReportType);
        new DropdownHelper(driver).selectFromAutocompleDD(fileName, ddBtnFileNameInReportSearch);
        navigationMenu.clickOnIcon("COLD Search", "Search");
        navigationMenu.waitForFilterTextBox();
        navigationMenu.clickOnFirstRow();
        navigationMenu.clickOnIcon("Display", "Report");
        WindowHelper windowHelper= new WindowHelper(driver);
        windowHelper.switchToNewlyOpenedTab();// File viewer tab
        navigationMenu.waitForIcon("Edit  References", "Reference");
        navigationMenu.clickOnIcon("Edit  References", "Reference");
        windowHelper.waitForModalDialog("Field Configuration");
        windowHelper.clickOnModalFooterButton("New Reference");
        windowHelper.waitForModalDialog("Reference Configuration");
        sendKeys(txtNameReferenceConfig, referenceName, "Entering reference name");
        windowHelper.clickOnModalFooterButton("Get Length");
        new DropdownHelper(driver).selectFromAutocompleDD("Folder 1", ddBtnFolderLinkReferenceConfig);
        int refLength=referenceName.length();
        sendKeys(txtLengthReferenceConfig, String.valueOf(refLength), "Entering reference name");
        String inputString = generate(() -> "X").limit(refLength).collect(joining());
        sleepFor(1000);
        sendKeys(txtFormatReferenceConfig, inputString, "Entering format as "+inputString);
        //windowHelper.clickOnModalFormFooterButton("Save");
        windowHelper.clickOnModalFooterButton("Save");
        sleepFor(2000);
        click(btnRowAndColumnFieldConfig,"Click on row and column button in field config");
        windowHelper.clickOnModalFooterButton("Save Field");
        //navigationMenu.waitForIcon("Edit  References", "Reference");
        sleepFor(1000);
        windowHelper.clickOnModalFormFooterButton("Cancel");
        driver.close();
        sleepFor(1000);
        new WindowHelper(driver).switchToNewlyOpenedTab();
    }



  public void createReferenceInFileSystem(String referenceName) {
    
  }



  public void captureDocWithCreatedReference(String referenceName,String getEnvVariable) throws InterruptedException {
    new CapturePage(driver).navigateAndCaptureDocWithParameters(null, null, null,"ColdImportRef", referenceName,getEnvVariable);
  }



  public String getSelectedRefernceName() {
      String optionText= ddSelectOptionReferenceTypes.getText();
      return optionText;
  }


    
    
}
