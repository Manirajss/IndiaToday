

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment {

	public static void main(String[] args) throws InterruptedException, IOException {
		//1.Open the website https://thesportstak.com/ & select the language 
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://thesportstak.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//span[text()='Hindi']")).click();
		driver.findElement(By.xpath("//span[text()='Eng']")).click();
		driver.findElement(By.xpath("//span[@class='MuiTab-wrapper']")).click();
		driver.findElement(By.xpath("//button[@iconposition='end']//span")).click();
		
		//2. Click on each thumbnail and verify that links are not broken on Home-->All. 
		
		List<WebElement> links=driver.findElements(By.tagName("a"));
		System.out.println("Total Links on the Web Page: "+ links.size());
		for (int i=0;i<links.size();i++)
		{
			WebElement element=links.get(i);
			String url=element.getAttribute("href");
			URL link=new URL(url);
			HttpURLConnection httpconn=(HttpURLConnection) link.openConnection();
			Thread.sleep(2000);
			httpconn.connect();
			int rescode=httpconn.getResponseCode();
			if(rescode>=400)
			{
				System.out.println(url +"-"+ "is broken link");
			}
			else
			{
				System.out.println(url +"-"+ "is valid link");
			}
		}
		
		
			
			//3. Click on "Cricket" and scroll to the 3rd page and click on the links and verify the links are not broken. 
			
			driver.findElement(By.xpath("(//span[@class='MuiTab-wrapper'])[2]")).click();
			 JavascriptExecutor js = (JavascriptExecutor) driver;
		        //Scroll down till the bottom of the page
		        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			
			/*	WebElement videoTitleBookmark = driver.findElement(By.xpath("//div[@id='__next']/div[2]/main[1]/div[1]/div[3]/div[28]"));
			Actions builder=new Actions(driver);
			builder.scrollToElement(videoTitleBookmark).perform();
			System.out.println("The Title of the link is: "+ videoTitleBookmark.getText());
			System.out.println(videoTitleBookmark.getLocation()+ "is valid link");*/
		 
		    //4.  Change the theme from "Light" to "Dark" or vice versa. 
		    
		    WebElement theme1=driver.findElement(By.xpath("//span[text()='Dark']"));
		    theme1.click();
		    System.out.println("Theme colour is changed to"+ theme1.getText());
		    WebElement theme2=driver.findElement(By.xpath("//span[text()='Light']"));
		    theme2.click();
		    System.out.println("Theme colour is changed to"+ theme2.getText());
		    
		    //5. Click on any Web Stories displayed.

		    driver.findElement(By.xpath("//a[@href='/webstories']")).click();
		    List<WebElement> webStory=driver.findElements(By.xpath("//div[@class='card card-webstories-home']"));
		    
		    
		    //6. Capture the text on each Web Stories and each page of webstories. 
		    
		    System.out.println("The Text on each Web Stories are:");
		   for (int j=0;j<webStory.size();j++)
		    {
		    WebElement web= webStory.get(j);
		    System.out.println(web.getText());
	}
		   
		   //7. Then click on the next webstory and return to the homepage.
		   
		   driver.findElement(By.xpath("(//div[@class='card__face card__face--front'])[1]")).click();
		   Thread.sleep(2000);
		   Set<String> windowHandles = driver.getWindowHandles();
		   System.out.println("How many window open : "+windowHandles.size());
		   List<String>lstWindow=new ArrayList<String>(windowHandles);
		   driver.switchTo().window(lstWindow.get(1));
		   System.out.println(driver.getTitle());
		   driver.switchTo().window(lstWindow.get(0));
}
}

