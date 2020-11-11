package guru99Package;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class day5TestNG {
	public String baseUrl = "http://www.demo.guru99.com/V4/";
	public WebDriver driver;
	public WebDriverWait wait;
	
	@DataProvider (name="loginProvider")
	public Object[][]getDataFromDataProvider(){
		Object[][]data=new Object[4][2];
		
		//1st row
		data[0][0]="mngr293142";
		data[0][1]="Egugyje";
		//2nd row
		data[1][0]="invalidUser";
		data[1][1]="Egugyje";
		//3rd row
		data[2][0]="mngr293142";
		data [2][1]="invalidPassword";
		//4th row
		data[3][0]="invalidUser";
		data[3][1]="invalidPassword";
		return data;
		
	}
	
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
	

	
	@Test(dataProvider="loginProvider")
	public void loginTest(String username, String password) throws Exception {		
		String actualBoxTitle;
		String welcomeText;

			WebElement UserID=driver.findElement(By.cssSelector("[name='uid']"));
			WebElement Password=driver.findElement(By.cssSelector("[name='password']"));
			WebElement Login=driver.findElement(By.cssSelector("[name='btnLogin']"));

			UserID.sendKeys(username);
			Password.sendKeys(password);
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
				assertTrue(usernameText.equals("mngr293142"));
		    }
			driver.navigate().back();

		
		

	  
  }
	@AfterTest
	public void terminateBrowser() {
		driver.quit();
	}
}
