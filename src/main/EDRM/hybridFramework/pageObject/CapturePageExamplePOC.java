package main.EDRM.hybridFramework.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import main.EDRM.hybridFramework.TestBase.TestBase;

public class CapturePageExamplePOC extends TestBase {
	
	private WebDriver driver;
	
	@FindBy(how = How.XPATH,using = "//input[@name='DocumentRef']")
	public WebElement docRef;
	
	@FindBy(how = How.XPATH,using = "//button[@data-id='FileSystemId']")
	public WebElement fileSystemDD;

	
	public CapturePageExamplePOC(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//driver.findElement(By.xpath("//input[@name='DocumentRef']"))		//doc ref xpath
	
	public void ClickOnFileSystemDD() {
		fileSystemDD.click();
	}
	
	public void EnterDocRef(String docRefValue) {
		docRef.sendKeys(docRefValue);
	}

}
