package com.cleartrip.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basics
{
	public WebDriver driver;
	protected ExtentReports extent;
	public ExtentSparkReporter spark;
	
	@BeforeTest
	public void startBrowser()
	{
		WebDriverManager.chromedriver().setup(); //Without using system.setproperty command,
		//we can invoke any browser with WebDriver manager

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");// to disable notification pop up
		driver = new ChromeDriver();
		driver= new ChromeDriver(options);
		driver.manage().window().maximize();//to maximize window
		
		extent=new ExtentReports();
		spark=new ExtentSparkReporter("/target/Spark.html");
		extent.attachReporter(spark);
	}
	@AfterTest()
		public void aftertestmethod()
		{
		
			driver.quit();
			
		}
	

}
