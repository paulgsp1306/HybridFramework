package commonFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil{
//method for Login
	public static boolean verifyLogin(String username, String password)
	{
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("ObjLoginbtn"))).click();
		String expected = "adminflow";
		String actual = driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login Success::"+expected+"    "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login fail::"+expected+"    "+actual,true);
			return false;
		}
	}
	//method for click branches
	public static void clickBranches() throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjClickBrances"))).click();
		Thread.sleep(2000);
	}
	//method for branch creation
	public static boolean verifyBranchCreation(String branchName, String Address1, String Address2, String Address3, 
			String Area, String zipcode, String Country, String state, String city) throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjNewBranch"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys(branchName);
		driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(Address1);
		driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(Address2);
		driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Address3);
		driver.findElement(By.xpath(config.getProperty("ObjArea1"))).sendKeys(Area);
		driver.findElement(By.xpath(config.getProperty("Objzipcode"))).sendKeys(zipcode);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(Country);
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(config.getProperty("Objstate")))).selectByVisibleText(state);
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(config.getProperty("Objcity")))).selectByVisibleText(city);
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getProperty("ObjSubmitbtn"))).click();
		Thread.sleep(2000);
		//capture alert message
		String branchAlert = driver.switchTo().alert().getText();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		String actualAlert = "New Branch";
		if(branchAlert.toLowerCase().contains(actualAlert.toLowerCase()))
		{
			Reporter.log(branchAlert,true);
			return true;
		}
		else
		{
			Reporter.log("Branch fail to create",true);
			return false;
		}
		
		
	}
	//method for branch updation
	public static boolean verifyBranchUpdation(String branchname, String Address, String area, String zipcode) throws Throwable
	
	{
		driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath(config.getProperty("ObjBranch")));
		element1.clear();
		element1.sendKeys(branchname);
		WebElement element2 = driver.findElement(By.xpath(config.getProperty("ObjAddress")));
		element2.clear();
		element2.sendKeys(Address);
		WebElement element3 = driver.findElement(By.xpath(config.getProperty("ObjArea")));
		element3.clear();
		element3.sendKeys(area);
		WebElement element4 = driver.findElement(By.xpath(config.getProperty("Objzip")));
		element4.clear();
		element4.sendKeys(zipcode);
		driver.findElement(By.xpath(config.getProperty("ObjUpdateBtn"))).click();
		String expectedalert = driver.switchTo().alert().getText();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		String actualalert= "branch updated";
		if(expectedalert.toLowerCase().contains(actualalert.toLowerCase()))
		{
			Reporter.log(expectedalert,true);
			return true;
		}else
		{
			Reporter.log("Branch Updated",true);
			return false;
		}
		
	}
	//method for logout
	public static boolean verifyLogout() throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath(config.getProperty("ObjLoginbtn"))).isDisplayed())
		{
			Reporter.log("Logout success",true);
			return true;
		}else
		{
			Reporter.log("Logout fail",true);
			return false;
		}
	}
}

















