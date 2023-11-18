package vtiger;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtils;
import CommonUtils.FileUtils1;
import CommonUtils.JavaUtils;
import CommonUtils.WebDriverUtils;

public class Oragnization {

	@Test
	public void Organizations() throws IOException, InterruptedException{	
		  final WebDriver driver;	
		   
		  FileUtils1 utils = new FileUtils1();
		  WebDriverUtils wUtils = new WebDriverUtils();
		  ExcelUtils eutils = new ExcelUtils();
		  JavaUtils jutils = new JavaUtils();
		  
		  //To read data From Properties file
		  
			String BROWSER = utils.GetDataFromPropertyFile("browser");
			String URL = utils.GetDataFromPropertyFile("url");
			String USERNAME = utils.GetDataFromPropertyFile("username");
			String PASSWORD= utils.GetDataFromPropertyFile("password");
			
		 //To read Data from Excel File
			
			String OrgName = eutils.getDataFromExcelFile("Sheet1", 1, 0);
          //String group = eutils.getDataFromExcelFile("Sheet1", 1, 1);
			String industry = eutils.getDataFromExcelFile("Sheet1", 1, 2);
			
			if(BROWSER.equalsIgnoreCase("Chrome")) {
				driver=new ChromeDriver();
			}else if(BROWSER.equalsIgnoreCase("Edge")) {
				driver=new EdgeDriver();
			}else {
			      driver=new FirefoxDriver();
			}
			
			wUtils.maximize(driver);
			wUtils.implicitwait(driver);
			
		     driver.get(URL);
			 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			 driver.findElement(By.id("submitButton")).click();
			 
			 driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
			 driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	           
	          driver.findElement(By.name("accountname")).sendKeys(OrgName+jutils.getRandomnumber());
	          driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
	          Thread.sleep(2000);
	          WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
	       // wUtils.handleDropDown(dropdown, group);
	          wUtils.handleDropDown(dropdown, 2);
	          
	         WebElement industryDropDown = driver.findElement(By.name("industry"));
	         wUtils.handleDropDown(industryDropDown, industry);
	         
	         WebElement box = driver.findElement(By.name("notify_owner"));
	         wUtils.action(driver, box);
             driver.findElement(By.xpath("//input[@name='button']")).click();
	         Thread.sleep(2000);

	         WebElement mouse = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	         wUtils.action(driver, mouse);
	         driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	         
	      
	}

}
