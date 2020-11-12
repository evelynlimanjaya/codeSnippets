package guru99Package;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import readExcel.readGuru99Excel;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class day6TestNG {
	public String baseUrl = "http://www.demo.guru99.com/V4/";
	public WebDriver driver;
	public WebDriverWait wait;
	
	
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.gecko.driver","C:\\WebDriver\\geckodriver\\geckodriver.exe");
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myProfile = profile.getProfile("testProfile");
		FirefoxOptions opt = new FirefoxOptions();
		opt.setProfile(myProfile);
		driver =  new FirefoxDriver(opt);
		wait=new WebDriverWait(driver,5);
		

		
		driver.get(baseUrl);
		driver.manage().window().maximize();

	}
	

	
	@Test
	public void loginTest() throws Exception {		
		String actualBoxTitle;
		String welcomeText;
		
		readGuru99Excel file=new readGuru99Excel();
		
		Sheet guru99Sheet = file.readExcel(System.getProperty("user.dir"),"guru99Excel.xlsx" , "Sheet1");
//		int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();
		for(int i=1;i<5;i++) {

			WebElement UserID=driver.findElement(By.cssSelector("[name='uid']"));
			WebElement Password=driver.findElement(By.cssSelector("[name='password']"));
			WebElement Login=driver.findElement(By.cssSelector("[name='btnLogin']"));
			Row row = guru99Sheet.getRow(i);

			UserID.sendKeys(row.getCell(0).getStringCellValue());
			Password.sendKeys(row.getCell(1).getStringCellValue());
			Login.click();
			
			try{ 
			    
		       	Alert alt = driver.switchTo().alert();
				actualBoxTitle = alt.getText(); // get content of the Alert Message
				alt.accept();
//				if (actualBoxTitle.contains("User or Password is not valid")) { // Compare Error Text with Expected Error Value
//				    System.out.println("Test case: Passed"); 
//				} 
//				else {
//				    System.out.println("Test case: Failed");
//				}
				assertEquals(actualBoxTitle,"User or Password is not valid");
			}
			    
		    catch (NoAlertPresentException Ex){ 
		    	WebElement welcomeMsg=driver.findElement(By.cssSelector("tr[class='heading3']"));
				wait.until(ExpectedConditions.visibilityOf(welcomeMsg));
				welcomeText=welcomeMsg.getText();
				String[] textPart=welcomeText.split(":");
				String usernameText=textPart[1];

				// On Successful login compare Actual Welcome Message with Expected Welcome Message		
//				if(usernameText.equals(" mngr293142")) {
//					System.out.println("Test Case: Passed");
//				}
//				else {
//					System.out.println("Test Case: Failed");
//				}
				assertTrue(usernameText.equals(" mngr293142"));
				// Code to take Screenshot
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// Code to save screenshot at desired location
				FileUtils.copyFile(scrFile, new File("C:\\Users\\Eien\\Documents\\Selenium Screenshot\\screenshot.png"));
		    }
			driver.navigate().back();
		}
		

		
		

	  
  }
	@AfterTest
	public void terminateBrowser() {
		driver.quit();
	}
}
