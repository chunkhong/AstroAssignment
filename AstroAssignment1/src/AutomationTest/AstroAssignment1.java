package AutomationTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AstroAssignment1 {

	public static void main(String[] args) throws InterruptedException, FailingHttpStatusCodeException, MalformedURLException, IOException {
		// TODO Auto-generated method stub	
		long totalTime = 6000;
		
		// Create a new instance of the Firefox driver
		System.setProperty("webdriver.gecko.driver","E:\\Java Selenium\\geckoDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		try
		{
			long start = System.currentTimeMillis();
			
			//Launch Astro Website
			driver.get("http://www.astro.com.my");
			driver.findElement(By.id("footeryear")).isDisplayed();
			
			long finish = System.currentTimeMillis();
			totalTime = finish - start; 
			System.out.println("Total Time for page load - "+totalTime); 
			
			// Print a Log In message to the screen
	        System.out.println("Successfully loaded the website www.astro.com");
	        
	        Assert.assertEquals(100, totalTime);
	        
		}
		catch (AssertionError e)
		{
			System.out.println("Page load more than 0.1 second");
			System.out.println(e);
			
			try
			{
				Assert.assertTrue(totalTime < 5000);
				System.out.println("Page load less than 5 second");
				
				
				//Below code only will execute if page load less than 5 seconds
				List<WebElement> links = driver.findElements(By.tagName("a"));	
				System.out.println("Total links on the page >> " + links.size());
				
				for (int i = 0; i<links.size(); i=i+1)	 
				{ 
					String linkName = links.get(i).getText();
					String linkHref = links.get(i).getAttribute("href");
					int lnkSize = linkName.length();
					if (lnkSize >= 0)
					{
						System.out.println(i+") "+links.get(i).getText());
						System.out.println("href = "+linkHref);

						WebClient Client = new WebClient();
						HtmlPage astroPage = Client.getPage(linkHref);
					    try {
					    	int rCode = astroPage.getWebResponse().getStatusCode();
					    	long rTime = astroPage.getWebResponse().getLoadTime();
							System.out.println("Responese Code = "+rCode);	
							System.out.println("Responese Time = "+rTime);
							Assert.assertEquals(200,rCode);
							
						} catch (AssertionError e2) {
							System.out.println("Error happened");	
							continue;
						}
					} 
				}
								
				//Determine the sequence of the promotion
				Thread.sleep(5000);
				driver.findElement(By.cssSelector("#highlights > ol > li:nth-child(3) > a")).click(); //Click on 3rd banner
				String text = driver.findElement(By.cssSelector("#highlights > div > ul > li.flex-active-slide > a > div > div.textbox > h2")).getText();
		        
				try {
					Assert.assertEquals(text, "Watch Astro Without TV");
				}catch (AssertionError f) {
					System.out.println("Watch Astro Without TV not at 3rd banner !!!");
				}
				
				// Close the driver
		        driver.quit();
			}
			catch (AssertionError f)
			{
				System.out.println("Page load more than 5 second");
				
		        // Close the driver
		        driver.quit();
			}			
		}		
	}

}
