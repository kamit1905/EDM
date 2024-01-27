package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import main.EDRM.hybridFramework.helper.browserConfigurations.config.ObjectReader;
import main.EDRM.hybridFramework.helper.browserConfigurations.config.PropertyReader;

public class ATestAutomationDemo {  //New
	//Add one line
	
	//Next megre practice
	
	public static void main(String[] args) throws InterruptedException {
		ObjectReader.reader = new PropertyReader();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		
//		driver.findElement(By.xpath("//input[@name='enter-name']")).sendKeys("NECDM");
//		
//		driver.findElement(By.xpath("//input[@id='alertbtn']")).click();
//		
//		String getText1 = driver.switchTo().alert().getText();
//		System.out.println("getText1 "+getText1);
//		Thread.sleep(2000);
//		driver.switchTo().alert().accept();
//		
//		String getOptionValue = driver.findElement(By.xpath("//input[@id='checkBoxOption1']/ancestor::label")).getText();
//		Assert.assertEquals(getOptionValue, "Option1");
//		
//		String getDCURL = ObjectReader.reader.getDocumentConnectUrl();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//driver.switchTo().frame("courses-iframe");
		driver.findElement(By.xpath("//a[text()='Mentorship']")).click();
	}

}
