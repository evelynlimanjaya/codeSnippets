package test;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

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

import utility.constants;

public class day4Test {
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
		
		driver.get(constants.URL);
		driver.manage().window().maximize();
	
	
	}
	
//	@Test
//	public void compareTest() throws InterruptedException {
//    	WebElement mobileTab=driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
//		mobileTab.click();
//		WebElement xperiaCompare=driver.findElement(By.xpath("(//*[@class='link-compare'])[1]"));
//		xperiaCompare.click();
//		WebElement iPhoneCompare=driver.findElement(By.xpath("(//*[@class='link-compare'])[2]"));
//		iPhoneCompare.click();
//		WebElement galaxyCompare=driver.findElement(By.xpath("(//*[@class='link-compare'])[3]"));
//		galaxyCompare.click();
//		WebElement compareBtn=driver.findElement(By.cssSelector("button[title='Compare']"));
//		compareBtn.click();
//		for (String handle : driver.getWindowHandles()) {
//			driver.switchTo().window(handle);
//		   	}
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='top']/body/div[1]/div[1]/h1")));
//		WebElement header=driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div[1]/h1"));
//		String strHead = ("COMPARE PRODUCTS");
//	    String compHead = header.getText();	
//	    System.out.println("compHead = "+compHead);
//	    String popupMobile1 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();
//	    String popupMobile2 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText(); 
//	    String popupMobile3=driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).getText(); 
//	    System.out.println("popupMobile1 = "+popupMobile1);
//	    System.out.println("popupMobile2 = "+popupMobile2);
//	    System.out.println("popupMobile3 = "+popupMobile3);
//	    try {	    	
//	    	assertEquals(strHead, compHead);
//		    } catch (Exception e) {
//		    	e.printStackTrace();	    	
//		    }	
//	  
//	    try {	    	
//	    	assertEquals("SONY XPERIA", popupMobile1);
//		    } catch (Exception e) {
//		    	e.printStackTrace();	    	
//		    }	
//	
//	    try {	    	
//	    	assertEquals("IPHONE", popupMobile2);
//		    } catch (Exception e) {
//		    	e.printStackTrace();	    	
//		    }	
//	    
//	    try {	    	
//	    	assertEquals("SAMSUNG GALAXY", popupMobile3);
//		    } catch (Exception e) {
//		    	e.printStackTrace();	    	
//		    }
//	    driver.findElement(By.xpath("//button[@title='Close Window']")).click();
//	    for (String handle : driver.getWindowHandles()) {
//		    driver.switchTo().window(handle);
//		    }	 
//	    
//	}
	
	@Test
	public void compareTest2() {
		WebElement mobileTab=driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
		mobileTab.click();
		WebElement xperiaCompare=driver.findElement(By.xpath("(//*[@class='link-compare'])[1]"));
		xperiaCompare.click();
		WebElement iPhoneCompare=driver.findElement(By.xpath("(//*[@class='link-compare'])[2]"));
		iPhoneCompare.click();
		WebElement galaxyCompare=driver.findElement(By.xpath("(//*[@class='link-compare'])[3]"));
		galaxyCompare.click();
		WebElement compareBtn=driver.findElement(By.cssSelector("button[title='Compare']"));
		compareBtn.click();
		
		String MainWindow=driver.getWindowHandle();		
		
        // To handle all new opened window.				
            Set<String> s1=driver.getWindowHandles();		
        Iterator<String> i1=s1.iterator();		
        		
        while(i1.hasNext()) {
        	String ChildWindow=i1.next();		
    		
            if(!MainWindow.equalsIgnoreCase(ChildWindow)) {
            	 // Switching to Child window
                driver.switchTo().window(ChildWindow);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='top']/body/div[1]/div[1]/h1")));
        		WebElement header=driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div[1]/h1"));
        		String strHead = ("COMPARE PRODUCTS");
        	    String compHead = header.getText();	
        	    System.out.println("compHead = "+compHead);
        	    String popupMobile1 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();
        	    String popupMobile2 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText(); 
        	    String popupMobile3=driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).getText(); 
        	    System.out.println("popupMobile1 = "+popupMobile1);
        	    System.out.println("popupMobile2 = "+popupMobile2);
        	    System.out.println("popupMobile3 = "+popupMobile3);
        	    try {	    	
        	    	assertEquals(strHead, compHead);
        		    } catch (Exception e) {
        		    	e.printStackTrace();	    	
        		    }	
        	  
        	    try {	    	
        	    	assertEquals("SONY XPERIA", popupMobile1);
        		    } catch (Exception e) {
        		    	e.printStackTrace();	    	
        		    }	
        	
        	    try {	    	
        	    	assertEquals("IPHONE", popupMobile2);
        		    } catch (Exception e) {
        		    	e.printStackTrace();	    	
        		    }	
        	    
        	    try {	    	
        	    	assertEquals("SAMSUNG GALAXY", popupMobile3);
        		    } catch (Exception e) {
        		    	e.printStackTrace();	    	
        		    }
        	    driver.findElement(By.xpath("//button[@title='Close Window']")).click();
            	
            }
        }
     // Switching to Parent window i.e Main Window.
        driver.switchTo().window(MainWindow);
		
	}
	
    @AfterTest
	public void terminateBrowser() {
		driver.quit();
	}
}
