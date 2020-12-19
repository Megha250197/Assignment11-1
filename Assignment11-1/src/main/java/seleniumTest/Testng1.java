package seleniumTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Testng1 
{
	
	WebDriver driver;
	ExtentReports extent;
	ExtentSparkReporter spark;
	@BeforeTest
	public void openWebsite()
	{
	WebDriverManager.chromedriver().setup(); //Without using system.setproperty command,
											//we can invoke any browser with WebDriver manager
	
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");// to disable notification pop up
	driver = new ChromeDriver();
	driver= new ChromeDriver(options);
	driver.manage().window().maximize();//to maximize window
	driver.get("https://www.cleartrip.com");
	driver.manage().deleteAllCookies();
	String atitle = driver.getTitle();
	System.out.println(atitle);
	Assert.assertEquals(atitle, "#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.");
	System.out.println("Before test is completed");
	  // to generate report
	extent=new ExtentReports();
	spark=new ExtentSparkReporter("/target/Spark.html");
	extent.attachReporter(spark);
	
	}
	
	
	@Test(priority=0)
    public void testmethod1() throws InterruptedException
    {
    	extent.createTest("Search Flights");
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
         WebElement flights = driver.findElement(By.xpath("//a[text()='Flights']"));
        Assert.assertTrue(flights.isDisplayed());
        flights.click();
        WebElement oneway = driver.findElement(By.xpath("//label[@title='One way']"));//radio button
        oneway.click();
        Thread.sleep(2000);
        //from tag
        WebElement fromTag = driver.findElement(By.xpath("//input[@id='FromTag']"));
      fromTag.sendKeys("bang",Keys.DOWN);
      Thread.sleep(4000);
       fromTag.sendKeys(Keys.ENTER);
       Thread.sleep(2000);
        //to tag
        WebElement totag = driver.findElement(By.id("ToTag"));
        totag.sendKeys("new" , Keys.DOWN);
        Thread.sleep(4000);
        totag.sendKeys(Keys.ENTER);
        Thread.sleep(2000);        
        //calendar 
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30);//add 30days to current date
        
        String newDate = dateFormat.format(cal.getTime());
        WebElement departDate = driver.findElement(By.xpath("//input[@placeholder='Pick a date']"));
        departDate.sendKeys(newDate);
       //adult
        WebElement adult = driver.findElement(By.name("adults"));
        adult.click();
        Select drpdwn1=new Select(adult);
        drpdwn1.selectByIndex(0);
        //child
        WebElement child = driver.findElement(By.name("childs"));
        child.click();
        Select drpdwn2=new Select(child);
        drpdwn2.selectByIndex(1);
        //infant
        WebElement infant = driver.findElement(By.name("infants"));
        infant.click();
        Select drpdwn3=new Select(infant);
        drpdwn3.selectByIndex(1);
        //click on search button
        driver.findElement(By.id("SearchBtn")).click();
        Thread.sleep(2000);
        String actualtitle = driver.getTitle();
        System.out.println(actualtitle);
        Assert.assertEquals(actualtitle , "Flight bookings, Cheap flights, Lowest Air tickets @Cleartrip");
        Thread.sleep(5000);
        
        System.out.println("test method 1 is executed");
        extent.flush();
    }
    @Test(priority=1)
    public void testmethod2() throws InterruptedException
    {
    	extent.createTest("Book flight");
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	Thread.sleep(4000);
    	driver.findElement(By.xpath("//p[text()='Non-stop']")).click();
    	Thread.sleep(3000);
    	 WebElement slider = driver.findElement(By.xpath("(//div[@role='slider'])[1]"));
    	 Thread.sleep(3000);
    
    	  int xpath = slider.getLocation().getX();
    	  int ypath = slider.getLocation().getY();
    	 System.out.println(xpath);
    	 System.out.println(ypath);
    	 
    	   Actions move = new Actions(driver);
    	   slider.click();
    	    move.dragAndDropBy(slider ,-136 ,0 ).build().perform();
    	    move.release();
      	  Thread.sleep(2000);

    	    WebElement chkbox = driver.findElement(By.xpath("//p[text()='Show multi-airline itineraries']"));
    	    Thread.sleep(3000);
    	    chkbox.click();
    	   
    	    Assert.assertFalse(chkbox.isSelected());
    	    Thread.sleep(4000);
    	    
       	  WebElement listOfFlights = driver.findElement(By.xpath("//button[text()='Book']"));
       	  listOfFlights.click();
   	        Thread.sleep(3000);
   	        System.out.println("Test method 2 is executed");
   	        extent.flush();
    }
   	        
  @Test(priority=2)
  public void testmethod3() throws InterruptedException
  {
	  extent.createTest("open Iternary window");
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().deleteAllCookies();
	  Set<String> pwh = driver.getWindowHandles();
	  System.out.println(pwh);
	 
	 
	  for (String w : pwh) 
	  {
		 driver.switchTo().window(w);
		 System.out.println(driver.getTitle());
		 System.out.println("================================================================");
		
	  }
	  Thread.sleep(3000);
	  String pgeTitle = driver.getTitle();
	  Assert.assertEquals(pgeTitle, "Cleartrip | Book your flight securely in simple steps");
	  Thread.sleep(3000);	     
   	       WebElement conBtn = driver.findElement(By.id("itineraryBtn"));
   	       conBtn.click();
   	      Thread.sleep(2000);
   	      
   	      driver.findElement(By.xpath("//input[@name='username']")).sendKeys("megha@gmail.com");
           driver.findElement(By.xpath("//input[@id='LoginContinueBtn_1']")).click();
           Thread.sleep(2000);
           System.out.println("Test method 3 is executed");
           extent.flush();
    	
    }
    

  @Test(priority=3)
  		public void testMethod4() throws InterruptedException 
  		{
	  extent.createTest("Adding traveler details");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  //Traveler details
	  //adult details
	  WebElement title1 = driver.findElement(By.name("AdultTitle1"));
      title1.click();
      Select drpdwn1=new Select(title1);
      drpdwn1.selectByValue("Mrs");
      
      driver.findElement(By.name("AdultFname1")).sendKeys("Megha");
      driver.findElement(By.name("AdultLname1")).sendKeys("J");
      Thread.sleep(1000);
      //child details
      WebElement title2 = driver.findElement(By.name("ChildTitle1"));
      title2.click();
      Select drpdwn2=new Select(title2);
      drpdwn2.selectByValue("Miss");
	   
      driver.findElement(By.name("ChildFname1")).sendKeys("ammu");
      driver.findElement(By.name("ChildLname1")).sendKeys("s");
      Thread.sleep(1000);
      
      WebElement dob1 = driver.findElement(By.name("ChildDobDay1"));
      dob1.click();
      Select drpdob1=new Select(dob1);
      drpdob1.selectByValue("25");
      
      WebElement dob2 = driver.findElement(By.name("ChildDobMonth1"));
      dob2.click();
      Select drpdob2=new Select(dob2);
      drpdob2.selectByValue("1");
      
      WebElement dob3 = driver.findElement(By.name("ChildDobYear1"));
      dob3.click();
      Select drpdob3=new Select(dob3);
      drpdob3.selectByValue("2010");
      //infant detail
      WebElement title3 = driver.findElement(By.name("InfantTitle1"));
      title3.click();
      Select drpdwn3=new Select(title3);
      drpdwn3.selectByValue("Mstr");
      
      driver.findElement(By.name("InfantFname1")).sendKeys("agastya");
      driver.findElement(By.name("InfantLname1")).sendKeys("s");
      Thread.sleep(2000);
      
      WebElement dob4 = driver.findElement(By.name("InfantDobDay1"));
      dob1.click();
      Select drpdob4=new Select(dob4);
      drpdob4.selectByValue("14");
      
      WebElement dob5 = driver.findElement(By.name("InfantDobMonth1"));
      dob2.click();
      Select drpdob5=new Select(dob5);
      drpdob5.selectByValue("6");
      
      WebElement dob6 = driver.findElement(By.name("InfantDobYear1"));
      dob3.click();
      Select drpdob6=new Select(dob6);
      drpdob6.selectByValue("2020");
      //mobile number
        driver.findElement(By.name("contact1")).sendKeys("9066509208");
	      Thread.sleep(2000);
	      //continue to payment
	    driver.findElement(By.id("travellerBtn")).click();
	    Thread.sleep(2000);
	    
	    System.out.println("Test method 4 is executed");
	    extent.flush();
  		}
  
  	@AfterTest()
  		public void aftertestmethod()
  		{
  		
  			driver.quit();
  			
  		}
}
