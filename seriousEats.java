package newpackage;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class seriousEats {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver,5);

		String baseUrl="https://www.seriouseats.com/";
		String expectedSearch="Chinese Herbal Silkie Chicken Soup Recipe";
		String actualSearch="";
		
		driver.get(baseUrl);
		driver.manage().window().maximize();
		
		WebElement searchIcon=driver.findElement(By.xpath("(//*[@class='icon icon-search-glass'])[2]"));
		WebElement searchInput=driver.findElement(By.id("search-box"));
		
		searchIcon.click();
		searchInput.sendKeys(expectedSearch);
		searchInput.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		WebElement searchResult=driver.findElement(By.xpath("//h4[contains(text(),'"+expectedSearch+"')]"));
		wait.until(ExpectedConditions.visibilityOf(searchResult));

		
		searchResult.click();
		actualSearch=driver.getTitle();
		
		if(actualSearch.contains(expectedSearch)) {
			System.out.print("Test Passed");
		}else {
			System.out.print("Test Failed");
		}
		Thread.sleep(10000);
		
		driver.close();
		
	}
}
