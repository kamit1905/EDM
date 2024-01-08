package tests;

import org.testng.annotations.Test;

import main.EDRM.hybridFramework.TestBase.TestBase;
import main.EDRM.hybridFramework.pageObject.CapturePageExamplePOC;

public class ATestDemo3 extends TestBase {
	
	@Test(enabled = true,priority = 1)
	public void CapturePageDetails() {
		CapturePageExamplePOC capture = new CapturePageExamplePOC(driver);
		capture.EnterDocRef("Value");
		capture.ClickOnFileSystemDD();
	}

}
