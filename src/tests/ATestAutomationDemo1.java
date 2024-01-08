package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ATestAutomationDemo1 {
	
	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		JavascriptExecutor js  = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//js.executeScript("arguments[0].click();", "")
		
		//driver.switchTo().frame("courses-iframe");
		
		//driver.findElement(By.xpath("//a[text()='Mentorship']")).click();
		
//		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("NECDM");
//		driver.findElement(By.xpath("//input[@id='confirmbtn']")).click();
//		
//		String getAlertTxt =  driver.switchTo().alert().getText();
//		System.out.println("getAlterText "+getAlertTxt);
//		Thread.sleep(2000);
//		
//		//driver.switchTo().alert().accept();		//Ok
//		driver.switchTo().alert().dismiss();		//Cancel
		
//		String getCheckboxValue = driver.findElement(By.xpath("//input[@id='checkBoxOption1']/ancestor::label[1]")).getText();
//		System.out.println("getCheckboxValue "+getCheckboxValue);
//		Assert.assertEquals(getCheckboxValue, "Option1sa");

//		String getHeaderValue = driver.findElement(By.xpath("//h1")).getText();
//		System.out.println("getHeaderValue "+getHeaderValue);
//		Assert.assertTrue(true);
	
		boolean flag  = driver.findElement(By.xpath("//input[@id='checkBoxOption1']")).isSelected();
		System.out.println("flag "+flag);
		//Assert.assertTrue(flag);
		Assert.assertFalse(flag);
	}

}
