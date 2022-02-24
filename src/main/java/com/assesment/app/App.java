package com.assesment.app;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	System.setProperty("webdriver.chrome.driver", ".//driver//chromedriver.exe");
    	WebDriver driver=new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.get("https://www.indiabookstore.net/");
    	String defaultHandle=driver.getWindowHandle();
    	driver.findElement(By.id("searchBox")).sendKeys("Selenium");
    	driver.findElement(By.id("searchButtonInline")).click();
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	driver.findElement(By.xpath("//img[@alt='Selenium testing tools Cookbook']")).click();
    	Thread.sleep(10000L);
    	String ISBN=driver.findElement(By.xpath("//div[@class=' col-xs-9 col-smx-10 col-sm-9 col-md-9 modal-book-content']//div[@class='isbnContainer']")).getText();
    	System.out.println(ISBN);
    	driver.findElement(By.xpath("//div[@class='ratings clearfix row']//div[@class='col-xs-4 col-smx-2 col-sm-1 col-md-1 text-center no-padding-util']//a[contains(@href,'www.amazon.in')]")).click();
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	Set<String> handles=driver.getWindowHandles();
    	for(String h:handles) {
    		if(!h.equalsIgnoreCase(defaultHandle)) {
    			driver.switchTo().window(h);
    			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    			List<WebElement> li=driver.findElements(By.xpath("//div[@data-cel-widget='detailBulletsWrapper_feature_div']//div[@id='detailBullets_feature_div']//ul//li//span//span"));
    			for(int i=0;i<li.size();i++) {
    				
    				if(li.get(i).getText().contains(ISBN.substring(8))) {
    					System.out.println("ISBN matched");
    					break;
    				}
    			}
    			String availablity=driver.findElement(By.xpath("//div[@id='availability']")).getText();
    			if(availablity.contains("in stock")) {
    				System.out.println("Book in stock");
    			}
    			String Price=driver.findElement(By.xpath("//span[@id='price']")).getText();
    			System.out.println("Price -"+Price);
    			
    		}
    	}
    	driver.switchTo().defaultContent();
    	driver.quit();
			
    	
}
}
