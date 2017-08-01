package Astro;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;

public class SignIn {
	
	static AndroidDriver driver;
	static Dimension size;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		//File classpathRoot = new File(System.getProperty("user.dir"));
		//File appDir = new File(classpathRoot, "/Gauge/");
		//File app = new File(appDir, "Gauge1.0.11_112_QA_original.apk");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "Mi Phone");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("platformName", "Android");
		//capabilities.setCapability("app", app.getAbsolutePath());
		//capabilities.setCapability("autoGrantPermissions","true");
		capabilities.setCapability("appPackage", "com.astro.astroview");
		capabilities.setCapability("appActivity", "com.astro.astroview.activities.LauncherActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		
		//Get the size of screen.
		size = driver.manage().window().getSize();
		System.out.println(size);
		  
	    //Find swipe start and end point from screen's with and height.
		//Find startx point which is at right side of screen.
		int startx = (int) (size.width * 0.85);
		//Find endx point which is at left side of screen.
		int endx = (int) (size.width * 0.15);
		//Find vertical point where you wants to swipe. It is in middle of screen height.
		int starty = size.height / 2;
		System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);
		
		//Wait for app to load...
		Thread.sleep(15000);

		//Swipe to login page
		for (int i=1; i<5;i++)
		{
			//Swipe from Right to Left.
			driver.swipe(startx, starty, endx, starty, 3000);
			Thread.sleep(2000);
		}
		System.out.println("Swipe done...");
		
		driver.findElement(By.id("btn_ftu_login")).click();
		Thread.sleep(10000);
		//Swipe from Top to down.
		//driver.swipe(0, 800, 0, 100, 3000);
		Thread.sleep(2000);
		
		List<WebElement> textBox = driver.findElements(By.className("android.widget.EditText"));
		System.out.println(textBox.size());
		textBox.get(0).sendKeys("chunkhong@hotmail.com");
		textBox.get(1).sendKeys("Herbalife@2017");
		driver.hideKeyboard();
		//Swipe from Top to down.
		driver.swipe(0, 600, 0, 100, 3000);
		Thread.sleep(2000);
		List<WebElement> button = driver.findElements(By.className("android.widget.Button"));
		System.out.println(button.size());
		System.out.println(button.get(0).getText());
		button.get(0).click();
		Thread.sleep(8000);
		driver.findElement(By.id("btn_agree")).click();
		Thread.sleep(8000);
		//Swipe from Top to down.
		driver.swipe(0, 700, 0, 100, 3000);
		driver.swipe(0, 420, 0, 200, 3000);
		List<WebElement> movies = driver.findElements(By.className("android.widget.FrameLayout"));
		System.out.println("Number of movie > "+movies.size());
		System.out.println("Movie name > "+movies.get(1).getText());
		movies.get(0).click();
		Thread.sleep(8000);
		String desc = driver.findElement(By.id("tv_show_short_desc")).getText();
		System.out.println(desc);
		System.out.println("Total characters > "+desc.length());
		//Check synopsis text more than 50
		Assert.assertTrue(desc.length()>50);
		driver.findElement(By.id("fab_more")).click();
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.id("img_reminder_action")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("button1")).click();
			Thread.sleep(2000);
		}catch (Exception e) {
			
		}
		driver.findElement(By.id("img_favourite_action")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("img_close_action")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("android.widget.ImageButton")).click();
		Thread.sleep(15000);
		
		//Swipe to Favorite tab
		for (int i=1; i<3;i++)
		{
			//Swipe from Right to Left.
			driver.swipe(startx, starty, endx, starty, 3000);
			Thread.sleep(2000);
		}
		System.out.println("Swipe to favorite...");
		
		
		Thread.sleep(15000);
		driver.quit();
	}

}
