package AutomationTest;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {
	
	public static void main(String[] args) throws InterruptedException {
		// Create a new instance of the Firefox driver
		System.setProperty("webdriver.gecko.driver","E:\\Java Selenium\\geckoDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://www.astro.com.my");
		
		WebElement wElement = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("footeryear")));
		
		List<WebElement> banners = driver.findElements(By.cssSelector("#highlights > ol > li"));
		
		for (int i = 0; i<banners.size(); i=i+1)
		{
			banners.get(i).click();
			String header = driver.findElement(By.cssSelector("#highlights > div > ul > li.flex-active-slide > a > div > div > h2")).getText();
			System.out.println("Banner no "+(i+1) + " Banner tittle : "+header);
			
			if (i == 4) {
				
				//Check campaign not the last banner
				Assert.assertNotEquals(i+1, banners.size());
				
				driver.findElement(By.cssSelector("#highlights > div > ul > li.flex-active-slide")).click();
				
				WebElement register = (new WebDriverWait(driver, 10))
						  .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign Up")));
				register.click();
				
				//Fill in details
				driver.findElement(By.cssSelector("#divContentArea > div:nth-child(6) > div.container > div:nth-child(7) > div.row > div.col-sm-6.pdr5 > input")).sendKeys("ChunKhong");
				driver.findElement(By.cssSelector("#divContentArea > div:nth-child(6) > div.container > div:nth-child(7) > div.row > div.col-sm-6.pdl5 > input")).sendKeys("Cheong");
				driver.findElement(By.id("divEmail")).findElement(By.tagName("input")).sendKeys("chunkhong@hotmail.com");
				driver.findElement(By.cssSelector("#divEmail > input.form-control.txt.txtrepeatemail")).sendKeys("chunkhong@hotmail.com");
				driver.findElement(By.id("password")).sendKeys("chunkhong@1978");
				driver.findElement(By.id("btnSubmit")).click();
				Thread.sleep(3000);
				break;
			}
		}
		
		//Quit
		driver.quit();
	}
}
