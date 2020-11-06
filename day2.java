package guru99Package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
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

public class day2 {
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
  public void loginTest() {

	  File file = new File("C:\\Users\\Eien\\Selenium-Eclipse\\eclipse-workspace\\guru99Project\\loginData.properties");
	  FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		driver.findElement(By.cssSelector(prop.getProperty("UserID"))).sendKeys("mngr293142");
		driver.findElement(By.cssSelector(prop.getProperty("Password"))).sendKeys("Egugyje");
		driver.findElement(By.cssSelector(prop.getProperty("Login"))).click();
	
	  WebElement titleSelector= driver.findElement(By.cssSelector("marquee[class='heading3']"));
	  wait.until(ExpectedConditions.visibilityOf(titleSelector));
	  String titleText=titleSelector.getText();
		
	  if(titleText.equals("Welcome To Manager's Page of Guru99 Bank")) {
		  System.out.print("Test Passed");
	  }
	  else {
		  System.out.print("Test Failed");
	  }
		

  }
  
  @AfterTest
  public void terminateBrowser() {
	  driver.close();
	  
  }
  
  
}
