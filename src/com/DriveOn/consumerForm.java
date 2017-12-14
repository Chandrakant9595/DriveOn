package com.DriveOn;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class consumerForm {
  
  @Test
  public void testScripts() throws InterruptedException, AWTException {
	  
	  addConsumerDetailsWithTwoApplication();
	  /*login();
	  verifyPersonalForm();
	  logout();*/
  }
  
  //Commit code line
  //Two
  
  public static WebDriver driver;
  public static JavascriptExecutor jse;
  public static Robot r;	
	
  	@BeforeClass
	public void testSetup() {
		
  		
		  // To run scripts on chrome browser
		  System.setProperty("webdriver.chrome.driver","/home/nadsoft/AutomationSoftware/ChromrDriver/chromedriver"); 
		   driver = new ChromeDriver();
		 

		// To run scripts on firefox browser
		// WebDriver driver = new FirefoxDriver();
		/*System.setProperty("webdriver.gecko.driver",
				"/home/nadsoft/AutomationSoftware/firefox/geckodriver");
		driver = new FirefoxDriver();*/

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown() {
		//driver.quit();
	} 
	
	public void addConsumerDetailsWithTwoApplication() throws InterruptedException, AWTException{
		
		System.out.println("================================================================"); 
		
		//get link
		driver.get("https://www.drive-on.com.au/");
		         // http://dev.drive-on.com.au/
		//driver.get("http://www.drive-on.com.au/application-form/?app_id=1&applicant=1");
		Thread.sleep(2000);
		
		//Apply now link
		driver.findElement(By.xpath("html/body/header/div/div[1]/nav/div/div[2]/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		
		//Personal loan link
		driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/div[1]/div/div[1]/a/img")).click();
		Thread.sleep(2000);
		
		//car link
		driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/div[3]/div[1]/a/img")).click();
		Thread.sleep(2000);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,2000)", "");
		Thread.sleep(2000);
		
		//next button
		driver.findElement(By.xpath(".//*[@id='personal']/div/div[2]/a[2]")).click();
		Thread.sleep(2000); 
		
		//scroll up
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-300)", "");
				Thread.sleep(2000);
		
		//Innestional 
				//capture the validation msg
				String vMsg13a = driver.findElement(By.xpath(".//*[@id='data[personal_details][title][]-error']")).getText();
				String vMsg14a = "This field is required.";    
				Assert.assertEquals(vMsg13a, vMsg14a);
				WebElement gender1a = driver.findElement(By.name("data[personal_details][title][]"));
				Select ga = new Select(gender1a);                 
				ga.selectByVisibleText("Mr");
				Thread.sleep(500);	
		
		//Personal Details -
		//capture the validation msg
		String vMsg1 = driver.findElement(By.xpath(".//*[@id='data[individual_application][borrow_type1][]-error']")).getText();
		System.out.println(vMsg1);
		String vMsg2 = "This field is required.";          
		Assert.assertEquals(vMsg1, vMsg2);
		System.out.println("Success");
		/*//Borrower Type - 	Primary Buyer 
		List <WebElement> radios = driver.findElements(By.name("data[individual_application][borrow_type1][]"));
		int o = radios.size();                                         
		System.out.println(o);
		radios.get(0).click();
		Thread.sleep(200); */
		driver.findElement(By.xpath(".//*[@id='personal']/div/div[1]/div[2]/div/label[1]")).click();
		Thread.sleep(500);
		
		
				
		//First name
		//capture the validation msg
		String vMsg3 = driver.findElement(By.xpath(".//*[@id='first_name-error']")).getText();
		String vMsg4 = "This field is required.";   
		Assert.assertEquals(vMsg3, vMsg4);
		driver.findElement(By.name("data[personal_details][fname][]")).clear();
		driver.findElement(By.name("data[personal_details][fname][]")).sendKeys("Test");
		Thread.sleep(200);
		
		//Middle name
		driver.findElement(By.name("data[personal_details][mname][]")).clear();
		driver.findElement(By.name("data[personal_details][mname][]")).sendKeys("Demo");
		Thread.sleep(200);
					
		//Surname
		//capture the validation msg
		String vMsg7 = driver.findElement(By.xpath(".//*[@id='Surname-error']")).getText();
		String vMsg8 = "This field is required.";
		Assert.assertEquals(vMsg7, vMsg8);
		driver.findElement(By.name("data[personal_details][surname][]")).clear();
		driver.findElement(By.name("data[personal_details][surname][]")).sendKeys("PersonalLoan");
		Thread.sleep(200);
		
		//Marital Status
		//capture the validation msg
		String vMsg13 = driver.findElement(By.xpath(".//*[@id='data[personal_details][marital_status][]-error']")).getText();
		String vMsg14 = "This field is required.";
		Assert.assertEquals(vMsg13, vMsg14);
		WebElement gender1 = driver.findElement(By.name("data[personal_details][marital_status][]"));
		Select g = new Select(gender1);
		g.selectByVisibleText("Single");
		Thread.sleep(500);
				
				
		//Gender
		//capture the validation msg
		String vMsg9 = driver.findElement(By.xpath(".//*[@id='data[personal_details][gender1][]-error']")).getText();
		String vMsg10 = "This field is required.";
		Assert.assertEquals(vMsg9, vMsg10);
		driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[1]/div[2]/div/div/div[1]/div[2]/div/div[1]/div[8]/div/label[1]")).click();			
		Thread.sleep(500);                     
		
		//Date of Birth
		//capture the validation msg
		String vMsg11 = driver.findElement(By.xpath(".//*[@id='data[personal_details][dob][]-error']")).getText();
		String vMsg12 = "This field is required.";
		Assert.assertEquals(vMsg11, vMsg12);
		WebElement dob = driver.findElement(By.name("data[personal_details][dob][]"));
		dob.clear();
		dob.sendKeys("05/05/1990");
		Thread.sleep(200);
		dob.sendKeys(Keys.TAB);
		
		//Licence Type 
		//capture the validation msg
		String vMsg13b = driver.findElement(By.xpath(".//*[@id='data[personal_details][licence_type][]-error']")).getText();
		String vMsg14b = "This field is required.";   
		Assert.assertEquals(vMsg13b, vMsg14b);
		WebElement gender1b = driver.findElement(By.name("data[personal_details][licence_type][]"));
		Select gb = new Select(gender1b);
		gb.selectByVisibleText("Full");
		Thread.sleep(500);
		
		//Drivers License No
		//capture the validation msg
		String vMsg15 = driver.findElement(By.xpath(".//*[@id='data[personal_details][driver_license_no][]-error']")).getText();
		String vMsg16 = "This field is required.";
		Assert.assertEquals(vMsg15, vMsg16);
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).clear();
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).sendKeys("8451245");
		Thread.sleep(200);
		
		//Licence State
		String vMsg15c = driver.findElement(By.xpath(".//*[@id='data[personal_details][licence_state][]-error']")).getText();
		String vMsg16c = "This field is required.";
		Assert.assertEquals(vMsg15c, vMsg16c);
		WebElement gender1c = driver.findElement(By.name("data[personal_details][licence_state][]"));
		Select gc = new Select(gender1c);
		gc.selectByVisibleText("NSW");
		Thread.sleep(500);
		
		//Expiry 
		//capture the validation msg
		String vMsg17 = driver.findElement(By.xpath(".//*[@id='data[personal_details][expiry][]-error']")).getText();
		String vMsg18 = "This field is required.";
		Assert.assertEquals(vMsg17, vMsg18);
		WebElement expiry = driver.findElement(By.name("data[personal_details][expiry][]"));
		expiry.clear();
		expiry.sendKeys("05/05/2020");
		Thread.sleep(200);
		expiry.sendKeys(Keys.TAB);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,550)", "");
		Thread.sleep(2000);
		
		//Email Address
		//capture the validation msg
		String vMsg23 = driver.findElement(By.xpath(".//*[@id='data[personal_details][email][]-error']")).getText();
		String vMsg24 = "This field is required.";
		Assert.assertEquals(vMsg23, vMsg24);
		driver.findElement(By.name("data[personal_details][email][]")).clear();
		driver.findElement(By.name("data[personal_details][email][]")).sendKeys("chandrakant@nadsoftdev.com");
		Thread.sleep(200);

		
		//Mobile Phone No 
		//capture the validation msg
		String vMsg25 = driver.findElement(By.xpath(".//*[@id='data[personal_details][mobile_phone][]-error']")).getText();
		String vMsg26 = "This field is required.";
		Assert.assertEquals(vMsg25, vMsg26);
		driver.findElement(By.name("data[personal_details][mobile_phone][]")).clear();
		driver.findElement(By.name("data[personal_details][mobile_phone][]")).sendKeys("8945561245");
		Thread.sleep(200);
	
		
		//Home Phone No
		driver.findElement(By.name("data[personal_details][home_phone][]")).clear();
		driver.findElement(By.name("data[personal_details][home_phone][]")).sendKeys("02356784512");
		Thread.sleep(200);
				
		//No.of Departments
		WebElement noOfDept = driver.findElement(By.name("data[personal_details][no_of_department][]"));
		Select dept = new Select(noOfDept);
		dept.selectByVisibleText("4");
		Thread.sleep(500); 
		
		//Residency Status
		//capture the validation msg
		String vMsg21 = driver.findElement(By.xpath(".//*[@id='data[personal_details][residential_status1][]-error']")).getText();
		String vMsg22 = "This field is required.";
		Assert.assertEquals(vMsg21, vMsg22);
		driver.findElement(By.xpath(".//*[@id='personal']/div/div[1]/div[18]/div/label[1]")).click();
		Thread.sleep(500); 
		
		//Credit History
		//capture the validation msg
		String vMsg1990 = driver.findElement(By.xpath(".//*[@id='data[personal_details][credit_history][]-error']")).getText();
		String vMsg1991 = "This field is required.";   
		Assert.assertEquals(vMsg1990, vMsg1991);
		
		//paid default select
		WebElement paidOpt = driver.findElement(By.name("data[personal_details][credit_history][]"));
		Select paid = new Select(paidOpt);               
		paid.selectByVisibleText("Paid Defaults");
		Thread.sleep(500);
		//next button to generate messages
		driver.findElement(By.xpath(".//*[@id='personal']/div/div[2]/a[2]")).click();
		Thread.sleep(4000);          
		//paid default validation check
		//$
				String vMsg1998 = driver.findElement(By.xpath(".//*[@id='data[personal_details][paid_defaults_amount][]-error']")).getText();
				String vMsg1999 = "This field is required.";    
				Assert.assertEquals(vMsg1998, vMsg1999);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_details][paid_defaults_amount][]")).clear();
				driver.findElement(By.name("data[personal_details][paid_defaults_amount][]")).sendKeys("111");
				Thread.sleep(200);          
				//Date paid
				String vMsg1981 = driver.findElement(By.xpath(".//*[@id='data[personal_details][paid_defaults_date_paid][]-error']")).getText();
				String vMsg1982 = "This field is required.";
				Assert.assertEquals(vMsg1981, vMsg1982);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_details][paid_defaults_date_paid][]")).clear();
				driver.findElement(By.name("data[personal_details][paid_defaults_date_paid][]")).sendKeys("30102018");
				Thread.sleep(200);
				//Issuing Party
				String vMsg1983 = driver.findElement(By.xpath(".//*[@id='data[personal_details][paid_defaults_issuing_party][]-error']")).getText();
				String vMsg1984 = "This field is required.";
				Assert.assertEquals(vMsg1983, vMsg1984);
				Thread.sleep(2000);
				driver.findElement(By.name("data[personal_details][paid_defaults_issuing_party][]")).clear();
				driver.findElement(By.name("data[personal_details][paid_defaults_issuing_party][]")).sendKeys("TestData");
				Thread.sleep(2000);
		
		//Un-paid default select
		WebElement paidOpt1 = driver.findElement(By.name("data[personal_details][credit_history][]"));
		Select paid1 = new Select(paidOpt1);
		paid1.selectByVisibleText("Un-Paid Defaults");
		Thread.sleep(500);
		//next button to generate messages
		driver.findElement(By.xpath(".//*[@id='personal']/div/div[2]/a[2]")).click();
		Thread.sleep(4000);
		//Un-paid default validation check
		//$
		String vMsg19981 = driver.findElement(By.xpath(".//*[@id='data[personal_details][un_paid_defaults_amount][]-error']")).getText();
		String vMsg19991 = "This field is required.";   
		Assert.assertEquals(vMsg19981, vMsg19991);
		Thread.sleep(200);
		driver.findElement(By.name("data[personal_details][un_paid_defaults_amount][]")).clear();
		driver.findElement(By.name("data[personal_details][un_paid_defaults_amount][]")).sendKeys("111");
		Thread.sleep(200);          
		//Date paid
		String vMsg19811 = driver.findElement(By.xpath(".//*[@id='data[personal_details][un_paid_defaults_date_issued][]-error']")).getText();
		String vMsg19821 = "This field is required.";
		Assert.assertEquals(vMsg19811, vMsg19821);
		Thread.sleep(200);
		driver.findElement(By.name("data[personal_details][un_paid_defaults_date_issued][]")).clear();
		driver.findElement(By.name("data[personal_details][un_paid_defaults_date_issued][]")).sendKeys("30102018");
		Thread.sleep(200);
		//Issuing Party
		String vMsg19831 = driver.findElement(By.xpath(".//*[@id='data[personal_details][un_paid_defaults_issuing_party][]-error']")).getText();
		String vMsg19841 = "This field is required.";
		Assert.assertEquals(vMsg19831, vMsg19841);
		Thread.sleep(2000);
		driver.findElement(By.name("data[personal_details][un_paid_defaults_issuing_party][]")).clear();
		driver.findElement(By.name("data[personal_details][un_paid_defaults_issuing_party][]")).sendKeys("TestData");
		Thread.sleep(2000);
		
		/*//Personal Reference One
		//Fname
		String fName1 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_1][]-error']")).getText();
		String fName2 = "This field is required.";
		Assert.assertEquals(fName1, fName2);
		Thread.sleep(200);
		driver.findElement(By.name("data[personal_reference][fname_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][fname_1][]")).sendKeys("Ref-1");
		Thread.sleep(200);
		
		//Sname
		String sName1 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][surname_1][]-error']")).getText();
		String sName2 = "This field is required.";
		Assert.assertEquals(sName1, sName2);
		Thread.sleep(200);
		driver.findElement(By.name("data[personal_reference][surname_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][surname_1][]")).sendKeys("Sur-1");
		Thread.sleep(200);
		
		//Addess
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).sendKeys("Add-1");
		Thread.sleep(200);
		
		//phone no
		String pNo1 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_1][]-error']")).getText();
		String pNo2 = "This field is required.";
		Assert.assertEquals(pNo1, pNo2);
		Thread.sleep(200);
		driver.findElement(By.name("data[personal_reference][phone_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][phone_1][]")).sendKeys("7894561230");
		Thread.sleep(200);
		
		//relation
		String rel1 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][relationship_1][]-error']")).getText();
		String rel2 = "This field is required.";
		Assert.assertEquals(rel1, rel2);
		Thread.sleep(200);
		driver.findElement(By.name("data[personal_reference][relationship_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][relationship_1][]")).sendKeys("Friend-1");
		Thread.sleep(200);
		
		//Personal Reference Two
				//Fname
				String fName11 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_2][]-error']")).getText();
				String fName21 = "This field is required.";
				Assert.assertEquals(fName11, fName21);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][fname_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][fname_2][]")).sendKeys("Ref-2");
				Thread.sleep(200);
				
				//Sname
				String sName11 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][surname_2][]-error']")).getText();
				String sName21 = "This field is required.";
				Assert.assertEquals(sName11, sName21);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][surname_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][surname_2][]")).sendKeys("Sur-2");
				Thread.sleep(200);
				
				//Addess
				driver.findElement(By.name("data[personal_reference][current_address_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][current_address_2][]")).sendKeys("Add-2");
				Thread.sleep(200);
				
				//phone no
				String pNo11 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_2][]-error']")).getText();
				String pNo21 = "This field is required.";
				Assert.assertEquals(pNo11, pNo21);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][phone_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][phone_2][]")).sendKeys("9638527410");
				Thread.sleep(200);
				
				//relation
				String rel11 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][relationship_2][]-error']")).getText();
				String rel21 = "This field is required.";
				Assert.assertEquals(rel11, rel21);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][relationship_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][relationship_2][]")).sendKeys("Friend-2");
				Thread.sleep(200);*/
		
		//next
		driver.findElement(By.xpath(".//*[@id='personal']/div/div[2]/a[2]")).click();
		Thread.sleep(2000);
		
		
		//Residential Details
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1500)", "");
		Thread.sleep(2000);
		
		//next
		driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/a[2]")).click();
		Thread.sleep(2000);          
		
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-500)", "");
		Thread.sleep(2000);
		
		//Current Resident Type
    	//capture the validation msg
		String vMsg43 = driver.findElement(By.xpath(".//*[@id='data[address_details][residential_type1][]-error']")).getText();
		String vMsg44 = "This field is required.";
		Assert.assertEquals(vMsg43, vMsg44);
		//select renting and check field validation
		driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div/label[3]")).click();
		Thread.sleep(200);           
		//next
		driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/a[2]")).click();
		Thread.sleep(2000);
		//check the validation
		String rent1 = driver.findElement(By.xpath(".//*[@id='data[address_details][company_name][]-error']")).getText();
		String rent2 = "This field is required.";
		Assert.assertEquals(rent1, rent2);
		Thread.sleep(200);
		String rent3 = driver.findElement(By.xpath(".//*[@id='data[address_details][contact_name][]-error']")).getText();
		String rent4 = "This field is required.";
		Assert.assertEquals(rent3, rent4);
		Thread.sleep(200);
		String rent5 = driver.findElement(By.xpath(".//*[@id='data[address_details][phone_number][]-error']")).getText();
		String rent6 = "This field is required.";
		Assert.assertEquals(rent5, rent6);
		Thread.sleep(200);
		
		//Scroll up
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-100)", "");
		Thread.sleep(2000);
		
		//select other option
		driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div/label[6]")).click();
		Thread.sleep(200);
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1500)", "");
		Thread.sleep(2000);
		//next
		driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/a[2]")).click();
		Thread.sleep(2000);
		String otherValue1 = driver.findElement(By.xpath(".//*[@id='data[address_details][residential_type_other][]-error']")).getText(); 
		String otherValue2 = "This field is required.";
		Assert.assertEquals(otherValue1, otherValue2);
		Thread.sleep(200);
		//Scroll up
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-100)", "");
		Thread.sleep(2000);
		//select Owing option
		driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div/label[1]")).click();
		Thread.sleep(2000);
		
		//Current Address Details - Current Address
		//Time At Address
		WebElement years1 = driver.findElement(By.name("data[address_details][time_at_address_yrs][]"));
    	Select year1 = new Select (years1);             
    	year1.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	WebElement months1 = driver.findElement(By.name("data[address_details][time_at_address_months][]"));
    	Select month1 = new Select (months1);            
    	month1.selectByVisibleText("4 Months");
    	Thread.sleep(1000);
		
		//capture the validation msg
		String vMsg35 = driver.findElement(By.xpath(".//*[@id='data[address_details][current_address][]-error']")).getText();
		String vMsg36 = "This field is required.";
		Assert.assertEquals(vMsg35, vMsg36);
		driver.findElement(By.name("data[address_details][current_address][]")).clear();
		driver.findElement(By.name("data[address_details][current_address][]")).sendKeys("Aundh, Pune");
		Thread.sleep(200);
		
		//suburb
		//capture the validation msg
		String vMsg37 = driver.findElement(By.xpath(".//*[@id='data[address_details][suburb][]-error']")).getText();
		String vMsg38 = "This field is required.";
		Assert.assertEquals(vMsg37, vMsg38);
		driver.findElement(By.name("data[address_details][suburb][]")).clear();
		driver.findElement(By.name("data[address_details][suburb][]")).sendKeys("Aundh");
		Thread.sleep(200);          
		
		//State
		//capture the validation msg
		String vMsg39 = driver.findElement(By.xpath(".//*[@id='data[address_details][state][]-error']")).getText();
		String vMsg40 = "This field is required.";
		Assert.assertEquals(vMsg39, vMsg40);
		WebElement state = driver.findElement(By.name("data[address_details][state][]"));
    	Select states = new Select (state);            
    	states.selectByVisibleText("NSW");
    	Thread.sleep(1000);
		
		//Postal Code
    	//capture the validation msg
		String vMsg41 = driver.findElement(By.xpath(".//*[@id='data[address_details][postal_code][]-error']")).getText();
		String vMsg42 = "This field is required.";
		Assert.assertEquals(vMsg41, vMsg42);
		driver.findElement(By.name("data[address_details][postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][postal_code][]")).sendKeys("45121");
		Thread.sleep(200);
		
		//Prev Time At Address
		WebElement years2 = driver.findElement(By.name("data[address_details][prev_time_at_address_yrs][]"));
    	Select year2 = new Select (years2);                     
    	year2.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	WebElement months2 = driver.findElement(By.name("data[address_details][prev_time_at_address_months][]"));
    	Select month2 = new Select (months2);
    	month2.selectByVisibleText("4 Months");
    	Thread.sleep(1000);
		
		//Previous Address
    	//capture the validation msg
		String vMsg45 = driver.findElement(By.xpath(".//*[@id='data[address_details][previous_address][]-error']")).getText();
		String vMsg46 = "This field is required.";
		Assert.assertEquals(vMsg45, vMsg46);
		driver.findElement(By.name("data[address_details][previous_address][]")).clear();
		driver.findElement(By.name("data[address_details][previous_address][]")).sendKeys("Station, Pune");
		Thread.sleep(200);
		
		//suburb
		//capture the validation msg
		String vMsg47 = driver.findElement(By.xpath(".//*[@id='data[address_details][previous_suburb][]-error']")).getText();
		String vMsg48 = "This field is required.";
		Assert.assertEquals(vMsg47, vMsg48);
		driver.findElement(By.name("data[address_details][previous_suburb][]")).clear();
		driver.findElement(By.name("data[address_details][previous_suburb][]")).sendKeys("Station");
		Thread.sleep(200);
		
		//State
		//capture the validation msg
		String vMsg49 = driver.findElement(By.xpath(".//*[@id='data[address_details][prev_state][]-error']")).getText();
		String vMsg50 = "This field is required.";
		Assert.assertEquals(vMsg49, vMsg50);
		WebElement state1 = driver.findElement(By.name("data[address_details][prev_state][]"));
		Select states1 = new Select (state1);
		states1.selectByVisibleText("NSW");
		Thread.sleep(1000);
		
		//Postal Code
		String vMsg51 = driver.findElement(By.xpath(".//*[@id='data[address_details][previous_postal_code][]-error']")).getText();
		String vMsg52 = "This field is required.";
		Assert.assertEquals(vMsg51, vMsg52);
		driver.findElement(By.name("data[address_details][previous_postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][previous_postal_code][]")).sendKeys("45254");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='residential']/div/div[2]/a[2]")).click();
		Thread.sleep(2000);
		
		//next for check validation
		driver.findElement(By.xpath(".//*[@id='employment']/div/div[2]/a[2]")).click();
		Thread.sleep(2000);
		
		//Current Employment Details - Current Employer
		String vMsg53 = driver.findElement(By.xpath(".//*[@id='data[employment_details][current_employer][]-error']")).getText();
		String vMsg54 = "This field is required.";
		Assert.assertEquals(vMsg53, vMsg54);
		driver.findElement(By.name("data[employment_details][current_employer][]")).clear();
		driver.findElement(By.name("data[employment_details][current_employer][]")).sendKeys("JKLSoft");
		Thread.sleep(200);          
		
		//Employer Address
		String vMsg55 = driver.findElement(By.xpath(".//*[@id='data[employment_details][employer_address][]-error']")).getText();
		String vMsg56 = "This field is required.";
		Assert.assertEquals(vMsg55, vMsg56);
		driver.findElement(By.name("data[employment_details][employer_address][]")).clear();
		driver.findElement(By.name("data[employment_details][employer_address][]")).sendKeys("Aundh, Pune");
		Thread.sleep(200);          
		
		//Phone No
		String vMsg57 = driver.findElement(By.xpath(".//*[@id='data[employment_details][employer_phone_no][]-error']")).getText();
		String vMsg58 = "This field is required.";
		Assert.assertEquals(vMsg57, vMsg58);
		driver.findElement(By.name("data[employment_details][employer_phone_no][]")).clear();
		driver.findElement(By.name("data[employment_details][employer_phone_no][]")).sendKeys("9687455612");
		Thread.sleep(200);          
		
		//Occupation
		String vMsg59 = driver.findElement(By.xpath(".//*[@id='data[employment_details][occupation][]-error']")).getText();
		String vMsg60 = "This field is required.";
		Assert.assertEquals(vMsg59, vMsg60);
		driver.findElement(By.name("data[employment_details][occupation][]")).clear();
		driver.findElement(By.name("data[employment_details][occupation][]")).sendKeys("Job");
		Thread.sleep(200);          
		
		//Employment Status
		WebElement empStatus = driver.findElement(By.name("data[employment_details][employment_status][]"));
		Select statusemp = new Select(empStatus);
		statusemp.selectByVisibleText("Self Employed");
		Thread.sleep(1000);
		
		//Time Employed
		WebElement years3 = driver.findElement(By.name("data[employment_details][time_employed_yrs][]"));
    	Select year3 = new Select (years3);             
    	year3.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	WebElement months3 = driver.findElement(By.name("data[employment_details][time_employed_months][]"));
    	Select month3 = new Select (months3);
    	month3.selectByVisibleText("4 Months");
    	Thread.sleep(1000);
		
		//Contact name
    	String vMsg61 = driver.findElement(By.xpath(".//*[@id='data[employment_details][prev_contact_name][]-error']")).getText();
		String vMsg62 = "This field is required.";   
		Assert.assertEquals(vMsg61, vMsg62);
		driver.findElement(By.name("data[employment_details][prev_contact_name][]")).clear();
		driver.findElement(By.name("data[employment_details][prev_contact_name][]")).sendKeys("Mahesh Kasar");
		Thread.sleep(200);          
		
		/*//Net Income $
		String vMsg63 = driver.findElement(By.xpath(".//*[@id='data[employment_details][net_income][]-error']")).getText();
		String vMsg64 = "This field is required.";
		Assert.assertEquals(vMsg63, vMsg64);
		driver.findElement(By.name("data[employment_details][net_income][]")).clear();
		driver.findElement(By.name("data[employment_details][net_income][]")).sendKeys("700000");
		Thread.sleep(200);          
		
		//Frequency
		String vMsg65 = driver.findElement(By.xpath(".//*[@id='data[employment_details][period1][]-error']")).getText();
		String vMsg66 = "This field is required.";
		Assert.assertEquals(vMsg65, vMsg66);
		driver.findElement(By.xpath("html/body/section[2]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[1]/div[2]/div/div[1]/div[3]/div[1]/div[11]/div/label[1]")).click();
		Thread.sleep(200);*/
		
		//Previous Employment Details - Previous employer
		String vMsg67 = driver.findElement(By.xpath(".//*[@id='data[employment_details][previous_employer][]-error']")).getText();
		String vMsg68 = "This field is required.";
		Assert.assertEquals(vMsg67, vMsg68);
		driver.findElement(By.name("data[employment_details][previous_employer][]")).clear();
		driver.findElement(By.name("data[employment_details][previous_employer][]")).sendKeys("KKSoft");
		Thread.sleep(200);          
		
		//Phone No 
		String vMsg69 = driver.findElement(By.xpath(".//*[@id='data[employment_details][prev_phone_no][]-error']")).getText();
		String vMsg70 = "This field is required.";   
		Assert.assertEquals(vMsg69, vMsg70);
		driver.findElement(By.name("data[employment_details][prev_phone_no][]")).clear();
		driver.findElement(By.name("data[employment_details][prev_phone_no][]")).sendKeys("7894561211");
		Thread.sleep(200);          
		
		//Previous Occupation
		String vMsg71 = driver.findElement(By.xpath(".//*[@id='data[employment_details][prev_occupation][]-error']")).getText();
		String vMsg72 = "This field is required.";   
		Assert.assertEquals(vMsg71, vMsg72);
		driver.findElement(By.name("data[employment_details][prev_occupation][]")).clear();
		driver.findElement(By.name("data[employment_details][prev_occupation][]")).sendKeys("Job");
		Thread.sleep(200);
		
		//Time Employed
		WebElement years4 = driver.findElement(By.name("data[employment_details][prev_time_employed_yrs][]"));
    	Select year4 = new Select (years4);
    	year4.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months5 = driver.findElement(By.name("data[employment_details][prev_time_employed_months][]"));
    	Select month5 = new Select (months5);            
    	month5.selectByVisibleText("4 Months");
    	Thread.sleep(1000);
    	
    	/*//Additional Monthly Income
    	driver.findElement(By.name("data[additional_monthly_income][type1][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type1][]")).sendKeys("Test Property");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value1][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value1][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	driver.findElement(By.name("data[additional_monthly_income][type2][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type2][]")).sendKeys("Test Investment");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value2][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value2][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	driver.findElement(By.name("data[additional_monthly_income][type3][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type3][]")).sendKeys("Test Chile Care");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value3][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value3][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	driver.findElement(By.name("data[additional_monthly_income][type4][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type4][]")).sendKeys("Test One");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value4][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value4][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	driver.findElement(By.name("data[additional_monthly_income][type5][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type5][]")).sendKeys("Test Two");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value5][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value5][]")).sendKeys("100");
    	Thread.sleep(500);*/
    	
    	//Income Details
    	//Net Income Verification
    	String vMsg71a = driver.findElement(By.xpath(".//*[@id='data[employment_details][net_income_verification][]-error']")).getText();
		String vMsg72a = "This field is required.";   
		Assert.assertEquals(vMsg71a, vMsg72a);
		driver.findElement(By.xpath(".//*[@id='employment']/div/div[1]/div[5]/div[1]/div/label[1]")).click();
		Thread.sleep(200);
    	
		//clear all text boxes
		//Monthly Net Income
		driver.findElement(By.name("data[employment_details][monthly_net_income][]")).clear();
		Thread.sleep(200);
		//Monthly Government Benifits Income
		driver.findElement(By.name("data[employment_details][government_benefits][]")).clear();
		Thread.sleep(200);
		//Monthly Investment Property Income
		driver.findElement(By.name("data[employment_details][investment_property][]")).clear();
		Thread.sleep(200);
		//Monthly Investment Income
		driver.findElement(By.name("data[employment_details][investment_income][]")).clear();
		Thread.sleep(200);
		//Monthly Other Income
		driver.findElement(By.name("data[employment_details][other_income][]")).clear();
		Thread.sleep(200);
		//Monthly Superannuation Income
		driver.findElement(By.name("data[employment_details][superannuation_income][]")).clear();
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='employment']/div/div[2]/a[2]")).click();
		Thread.sleep(2000);
		
		//ABN number
		String actualABN = driver.findElement(By.xpath(".//*[@id='abn_number-error']")).getText();
		String expectedABN = "This field is required.";
		Assert.assertEquals(actualABN, expectedABN);
		driver.findElement(By.id("abn_number")).clear();
		Thread.sleep(100);
		driver.findElement(By.id("abn_number")).sendKeys("12345");
		Thread.sleep(200);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1500)", "");
		Thread.sleep(2000);
		
		//Monthly Net Income
		driver.findElement(By.name("data[employment_details][monthly_net_income][]")).sendKeys("100");
		Thread.sleep(200);
		//Monthly Government Benifits Income
		driver.findElement(By.name("data[employment_details][government_benefits][]")).sendKeys("100");
		Thread.sleep(200);
		//Monthly Investment Property Income
		driver.findElement(By.name("data[employment_details][investment_property][]")).sendKeys("100");
		Thread.sleep(200);
		//Monthly Investment Income
		driver.findElement(By.name("data[employment_details][investment_income][]")).sendKeys("100");
		Thread.sleep(200);
		//Monthly Other Income
		driver.findElement(By.name("data[employment_details][other_income][]")).sendKeys("100");
		Thread.sleep(200);
		//Monthly Superannuation Income
		driver.findElement(By.name("data[employment_details][superannuation_income][]")).sendKeys("100");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='employment']/div/div[2]/a[2]")).click();
		Thread.sleep(2000);
		
		//Assets 
		//yes option check and delete field
		WebElement assects = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
    	Select homeProperty = new Select (assects);           
    	homeProperty.selectByVisibleText("Home Property");
    	Thread.sleep(1000);
		//add button
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//Yes select
    	WebElement assects2 = driver.findElement(By.name("data[assets_libility_assets][asset_home][own][]"));
    	Select ownedOutright = new Select (assects2);           
    	ownedOutright.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	//delete
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[2]/table/tbody/tr/td[4]/a/i")).click();
    	Thread.sleep(1000);
    	//no option and delete from liabilties 
    	//add button
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//no option
    	WebElement assects3 = driver.findElement(By.name("data[assets_libility_assets][asset_home][own][]"));
    	Select ownedOutrightNo = new Select (assects3);           
    	ownedOutrightNo.selectByVisibleText("No");
    	Thread.sleep(1000);
    	//del from liab
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[4]/table/tbody/tr/td[7]/a/i")).click();
    	Thread.sleep(1000);
    	
    	//add all assets and select Yes
    	//Home Property
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//Yes select
    	WebElement assects4 = driver.findElement(By.name("data[assets_libility_assets][asset_home][own][]"));
    	Select ownedOutright4 = new Select (assects4);           
    	ownedOutright4.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_home][value][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Investment Property
    	WebElement assects1 = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
    	Select investmentProperty = new Select (assects1);           
    	investmentProperty.selectByVisibleText("Investment Property");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//Yes select
    	WebElement assects5 = driver.findElement(By.name("data[assets_libility_assets][asset_investment][own][]"));
    	Select ownedOutright5 = new Select (assects5);           
    	ownedOutright5.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_investment][value][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Cash in Bank
    	WebElement assects6 = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
    	Select cashinBank = new Select (assects6);           
    	cashinBank.selectByVisibleText("Cash in Bank");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_cash][value][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Shares/Trusts/Managed Funds
    	WebElement assects7 = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
    	Select trusts = new Select (assects7);           
    	trusts.selectByVisibleText("Shares/Trusts/Managed Funds");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//Yes select
    	WebElement assects8 = driver.findElement(By.name("data[assets_libility_assets][asset_shares][own][]"));
    	Select ownedOutright8 = new Select (assects8);           
    	ownedOutright8.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_shares][value][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Motor Vehicle
    	WebElement assects9 = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
    	Select motor = new Select (assects9);           
    	motor.selectByVisibleText("Motor Vehicle");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//Yes select
    	WebElement assects10 = driver.findElement(By.name("data[assets_libility_assets][asset_vehicle][own][]"));
    	Select ownedOutright10 = new Select (assects10);           
    	ownedOutright10.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_vehicle][value][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Plant and Equipment
    	WebElement assects11 = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
    	Select plant = new Select (assects11);           
    	plant.selectByVisibleText("Plant and Equipment");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//Yes select
    	WebElement assects12 = driver.findElement(By.name("data[assets_libility_assets][asset_plant][own][]"));
    	Select ownedOutright11 = new Select (assects12);           
    	ownedOutright11.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_plant][value][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Superannuation
    	WebElement assects13 = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
    	Select superannuation = new Select (assects13);           
    	superannuation.selectByVisibleText("Superannuation");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_super][value][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Recreational Assets
    	WebElement assects14 = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
    	Select recreational = new Select (assects14);           
    	recreational.selectByVisibleText("Recreational Assets");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//Yes select
    	WebElement assects15 = driver.findElement(By.name("data[assets_libility_assets][asset_recreate][own][]"));
    	Select ownedOutright12 = new Select (assects15);           
    	ownedOutright12.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_recreate][value][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Home Contents
    	WebElement assects16 = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
    	Select home = new Select (assects16);           
    	home.selectByVisibleText("Home Contents");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath(".//*[@id='financial']/div/div[1]/div[1]/div[1]/a/i")).click();
    	Thread.sleep(1000);
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_homecontent][value][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//scroll down
    	jse = (JavascriptExecutor) driver;
   		jse.executeScript("window.scrollBy(0,800)", "");
   		Thread.sleep(2000);
   		
   	    	
    	//Liabilities
    	//delete Home property field
    	WebElement liadelHomePro = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select homePro = new Select(liadelHomePro);            
    	homePro.selectByVisibleText("Home Property Loan");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);                         
    	//delete
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[4]/table/tbody/tr/td[7]/a/i")).click();
    	Thread.sleep(1000);                 
    	
    	//check the all liability property fields and his error messages
    	//Home Property Loan
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_home][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000); 
    	
    	//Investment Property Loan
    	WebElement web2 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb2 = new Select(web2);            
    	webb2.selectByVisibleText("Investment Property Loan");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_investment][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Margin Loan
    	WebElement web3 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb3 = new Select(web3);            
    	webb3.selectByVisibleText("Margin Loan");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_margin][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Motor Vehicle Loan
    	WebElement web4 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb4 = new Select(web4);            
    	webb4.selectByVisibleText("Motor Vehicle Loan");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Recreational Assets Loan
    	WebElement web5 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb5 = new Select(web5);            
    	webb5.selectByVisibleText("Recreational Assets Loan");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Plant and Equipment Loan
    	WebElement web6 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb6 = new Select(web6);            
    	webb6.selectByVisibleText("Plant and Equipment Loan");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_plant][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Loan for Investments
    	WebElement web7 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb7 = new Select(web7);            
    	webb7.selectByVisibleText("Loan for Investments");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_shares][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Personal Loan
    	WebElement web8 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb8 = new Select(web8);            
    	webb8.selectByVisibleText("Personal Loan");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_personal][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Credit Cards
    	WebElement web9 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb9 = new Select(web9);            
    	webb9.selectByVisibleText("Credit Cards");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Overdraft
    	WebElement web10 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb10 = new Select(web10);            
    	webb10.selectByVisibleText("Overdraft");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Debt Agreement
    	WebElement web11 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb11 = new Select(web11);            
    	webb11.selectByVisibleText("Debt Agreement");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_debtagreement][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//Tax Debt
    	WebElement web12 = driver.findElement(By.name("data[assets_libility][type_of_liability][]"));
    	Select webb12 = new Select(web12);            
    	webb12.selectByVisibleText("Tax Debt");
    	Thread.sleep(1000);
    	//add
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]/div[3]/a/i")).click();
    	Thread.sleep(1000);          //*[@id="financial"]/div/div[1]/div[1]/div[3]/a/i         
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][balance_owing][]")).sendKeys("100");
    	Thread.sleep(1000);
    	
    	//next
    	driver.findElement(By.xpath(".//*[@id='finnext']")).click();
    	Thread.sleep(1000);
    	
    	
    	
    	//get error messages verify it and and value in text boxes
    	//Home Property Loan
    	//Monthly Payment
    	String errlib1 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_home1-error']")).getText();
    	String errlib2 = "This field is required.";
    	Assert.assertEquals(errlib1, errlib2);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_home][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib3 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_home1-error']")).getText();
    	String errlib4 = "This field is required.";
    	Assert.assertEquals(errlib3, errlib4);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_home][bank_institute][]")).sendKeys("American Express");
    	Thread.sleep(1000);
    	
    	//Investment Property Loan
    	//Monthly Payment
    	String errlib5 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_investment1-error']")).getText();
    	String errlib6 = "This field is required.";
    	Assert.assertEquals(errlib5, errlib6);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_investment][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib7 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_investment1-error']")).getText();
    	String errlib8 = "This field is required.";
    	Assert.assertEquals(errlib7, errlib8);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_investment][bank_institute][]")).sendKeys("ANZ Bank");
    	Thread.sleep(1000);
    	
    	//Margin Loan
    	String errlib9 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_margin1-error']")).getText();
    	String errlib10 = "This field is required.";
    	Assert.assertEquals(errlib9, errlib10);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_margin][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib11 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_margin1-error']")).getText();
    	String errlib12 = "This field is required.";
    	Assert.assertEquals(errlib11, errlib12);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_margin][bank_institute][]")).sendKeys("BMW Australia Finance");
    	Thread.sleep(1000);
    	
    	//Motor Vehicle Loan
    	//Monthly Payment
    	String errlib13 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_vehicle1-error']")).getText();
    	String errlib14 = "This field is required.";
    	Assert.assertEquals(errlib13, errlib14);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib15 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_vehicle1-error']")).getText();
    	String errlib16 = "This field is required.";
    	Assert.assertEquals(errlib15, errlib16);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][bank_institute][]")).sendKeys("Capfin Direct");
    	Thread.sleep(1000);
    	//Payout
    	WebElement drop1 = driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][payout][]"));
    	Select dropp1 = new Select(drop1);            
    	dropp1.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	
    	//Recreational Assets Loan
    	//Monthly Payment
    	String errlib17 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_recreate1-error']")).getText();
    	String errlib18 = "This field is required.";
    	Assert.assertEquals(errlib17, errlib18);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib19 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_recreate1-error']")).getText();
    	String errlib20 = "This field is required.";
    	Assert.assertEquals(errlib19, errlib20);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][bank_institute][]")).sendKeys("Defence Bank Ltd");
    	Thread.sleep(1000);
    	//Payout
    	WebElement drop2 = driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][payout][]"));
    	Select dropp2 = new Select(drop2);            
    	dropp2.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	
    	
    	//Plant and Equipment Loan
    	//Monthly Payment
    	String errlib21 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_plant1-error']")).getText();
    	String errlib22 = "This field is required.";
    	Assert.assertEquals(errlib21, errlib22);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_plant][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib23 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_plant1-error']")).getText();
    	String errlib24 = "This field is required.";
    	Assert.assertEquals(errlib23, errlib24);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_plant][bank_institute][]")).sendKeys("Flexirent");
    	Thread.sleep(1000);
    	//Payout
    	WebElement drop3 = driver.findElement(By.name("data[assets_libility_liabs][lib_plant][payout][]"));
    	Select dropp3 = new Select(drop3);            
    	dropp3.selectByVisibleText("Yes");
    	Thread.sleep(1000);
    	
    	//Loan for Investments
    	String errlib25 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_shares1-error']")).getText();
    	String errlib26 = "This field is required.";
    	Assert.assertEquals(errlib25, errlib26);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_shares][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib27 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_shares1-error']")).getText();
    	String errlib28 = "This field is required.";
    	Assert.assertEquals(errlib27, errlib28);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_shares][bank_institute][]")).sendKeys("GE Financial Services");
    	Thread.sleep(1000);
    	
    	//Personal Loan
    	String errlib29 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_personal1-error']")).getText();
    	String errlib30 = "This field is required.";
    	Assert.assertEquals(errlib29, errlib30);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_personal][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib31 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_personal1-error']")).getText();
    	String errlib32 = "This field is required.";
    	Assert.assertEquals(errlib31, errlib32);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_personal][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(1000);
    	
    	//Credit Cards
    	String errlib33 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_credit1-error']")).getText();
    	String errlib34 = "This field is required.";
    	Assert.assertEquals(errlib33, errlib34);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib35 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_credit1-error']")).getText();
    	String errlib36 = "This field is required.";
    	Assert.assertEquals(errlib35, errlib36);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][bank_institute][]")).sendKeys("IMB Lending Services");
    	Thread.sleep(1000);
    	//Limit
    	String errlib37 = driver.findElement(By.xpath(".//*[@id='limitlib_credit1-error']")).getText();
    	String errlib38 = "This field is required.";
    	Assert.assertEquals(errlib37, errlib38);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][limit][]")).sendKeys("100");
    	
    	//Overdraft
    	String errlib39 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_overdraft1-error']")).getText();
    	String errlib40 = "This field is required.";
    	Assert.assertEquals(errlib39, errlib40);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib41 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_overdraft1-error']")).getText();
    	String errlib42 = "This field is required.";
    	Assert.assertEquals(errlib41, errlib42);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][bank_institute][]")).sendKeys("Kwik Finance P/L");
    	Thread.sleep(1000);
    	//Limit
    	String errlib43 = driver.findElement(By.xpath(".//*[@id='limitlib_overdraft1-error']")).getText();
    	String errlib44 = "This field is required.";
    	Assert.assertEquals(errlib43, errlib44);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][limit][]")).sendKeys("100");
    	
    	//Debt Agreement
    	String errlib45 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_debtagreement1-error']")).getText();
    	String errlib46 = "This field is required.";
    	Assert.assertEquals(errlib45, errlib46);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_debtagreement][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib47 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_debtagreement1-error']")).getText();
    	String errlib48 = "This field is required.";
    	Assert.assertEquals(errlib47, errlib48);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_debtagreement][bank_institute][]")).sendKeys("Latitude Finance");
    	Thread.sleep(1000);
    	
    	//next
    	driver.findElement(By.xpath(".//*[@id='finnext']")).click();
    	Thread.sleep(1000);
    	
    	//Tax Debt
    	String errlib49 = driver.findElement(By.xpath(".//*[@id='monthly_paymentlib_taxdebt1-error']")).getText();
    	String errlib50 = "This field is required.";
    	Assert.assertEquals(errlib49, errlib50);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][monthly_payment][]")).sendKeys("100");
    	Thread.sleep(500);
    	//Financier
    	String errlib51 = driver.findElement(By.xpath(".//*[@id='bank_institutelib_taxdebt1-error']")).getText();
    	String errlib52 = "This field is required.";
    	Assert.assertEquals(errlib51, errlib52);
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][bank_institute][]")).sendKeys("ME Bank");
    	Thread.sleep(1000);
    	
    	//Other Expenses
    	//General Living Expenses empty condtion
    	driver.findElement(By.name("data[assets_libility][general_living][]")).clear();
    	driver.findElement(By.xpath(".//*[@id='finnext']")).click();
    	Thread.sleep(500);
    	String error1 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][general_living][]-error']")).getText();
    	String error2 = "This field is required.";
    	Assert.assertEquals(error1, error2);
    	Thread.sleep(500);
    	
    	//General Living Expenses 0 condition
    	driver.findElement(By.name("data[assets_libility][general_living][]")).sendKeys("0");
    	Thread.sleep(500);
    	String er3 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][general_living][]-error']")).getText();
    	String er4 = "General Living Expenses must be greater than $0";
    	Assert.assertEquals(er3, er4);
    	Thread.sleep(500);
    	
    	//Monthly Rental/Board Payment 
    	driver.findElement(By.name("data[assets_libility][monthly_rent_board][]")).clear();
    	Thread.sleep(500);
    	
    	//Motor Vehicle Running Costs
    	driver.findElement(By.name("data[assets_libility][vehicle_running_cost][]")).clear();
    	Thread.sleep(500);
    	
    	//Private Education & Childcare 
    	driver.findElement(By.name("data[assets_libility][private_education_childcare][]")).clear();
    	Thread.sleep(500);
    	
    	//Other Expenses
    	driver.findElement(By.name("data[assets_libility][other_expenses][]")).clear();
    	Thread.sleep(500);
    	
    	//next
    	driver.findElement(By.xpath(".//*[@id='finnext']")).click();
    	Thread.sleep(500);
    	
    	//get error message and enter value
    	//Monthly Rental/Board Payment
    	String er5 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][monthly_rent_board][]-error']")).getText();
    	String er6 = "This field is required.";
    	Assert.assertEquals(er5, er6);
    	driver.findElement(By.name("data[assets_libility][monthly_rent_board][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	//General Living Expenses
    	driver.findElement(By.name("data[assets_libility][general_living][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	//Motor Vehicle Running Costs 
    	String er7 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][vehicle_running_cost][]-error']")).getText();
    	String er8 = "This field is required.";
    	Assert.assertEquals(er7, er8);
    	driver.findElement(By.name("data[assets_libility][vehicle_running_cost][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	//Private Education & Childcare 
    	String er9 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][private_education_childcare][]-error']")).getText();
    	String er10 = "This field is required.";
    	Assert.assertEquals(er9, er10);
    	driver.findElement(By.name("data[assets_libility][private_education_childcare][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	//Other Expenses
    	String er11 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][other_expenses][]-error']")).getText();
    	String er12 = "This field is required.";
    	Assert.assertEquals(er11, er12);
    	driver.findElement(By.name("data[assets_libility][other_expenses][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	//Do you see any foreseeable changes to your financia
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[7]/div/label[1]")).click();
    	Thread.sleep(500);
    	
    	//next
    	driver.findElement(By.xpath("html/body/section[1]/div/div/div[2]/div/form/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div[2]/a[2]")).click();
    	Thread.sleep(500);
    	
    	//Please provide an explanation on the foreseeable changes*
    	String er11w = driver.findElement(By.xpath(".//*[@id='data[assets_libility][foreseeable_details][]-error']")).getText();
    	String er12w = "This field is required.";
    	Assert.assertEquals(er11w, er12w);
    	driver.findElement(By.name("data[assets_libility][foreseeable_details][]")).sendKeys("TestExplation");
    	Thread.sleep(500);
    	
    	//next
    	driver.findElement(By.xpath(".//*[@id='finnext']")).click();
    	Thread.sleep(500);           
    	
    	//Vehicle Details
    	//next
     	
    	//Make
    	/*String er13 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][make][]-error']")).getText();
    	String er14 = "This field is required.";   
    	Assert.assertEquals(er13, er14);*/
    	driver.findElement(By.name("data[new_vehicle_detail][make][]")).sendKeys("Test Make");
    	Thread.sleep(500);
    	
    	//next
    	driver.findElement(By.xpath(".//*[@id='vehicle']/div/div[2]/a[2]")).click();
		Thread.sleep(2000);
    	
    	//Model
    	String er15 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][model][]-error']")).getText();
    	String er16 = "This field is required.";
    	Assert.assertEquals(er15, er16);
    	driver.findElement(By.name("data[new_vehicle_detail][model][]")).sendKeys("Test Model");
    	Thread.sleep(500);
    	
    	//Build Date
    	WebElement web13 = driver.findElement(By.name("data[new_vehicle_detail][year][]"));
    	Select webb13 = new Select(web13);            
    	webb13.selectByVisibleText("2005");
    	Thread.sleep(1000);
    	
    	//KMs
    	String er17 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][kms][]-error']")).getText();
    	String er18 = "This field is required.";
    	Assert.assertEquals(er17, er18);
    	driver.findElement(By.name("data[new_vehicle_detail][kms][]")).sendKeys("12");
    	Thread.sleep(500);
    	
    	//amount
    	String er19 = driver.findElement(By.xpath(".//*[@id='amount-finance-error']")).getText();
    	String er20 = "This field is required.";
    	Assert.assertEquals(er19, er20);
    	driver.findElement(By.name("data[new_vehicle_detail][amount_to_finance][]")).sendKeys("1210");
    	Thread.sleep(500);
    	
    	//term
    	String er21 = driver.findElement(By.xpath(".//*[@id='term-years-error']")).getText();
    	String er22 = "This field is required.";
    	Assert.assertEquals(er21, er22);
    	WebElement years5 = driver.findElement(By.name("data[new_vehicle_detail][term][]"));
    	Select year5 = new Select (years5);             
    	year5.selectByVisibleText("1");
    	Thread.sleep(1000);
		
		//Baloon
    	String er23 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][baloon][]-error']")).getText();
    	String er24 = "This field is required.";
    	Assert.assertEquals(er23, er24);
		driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).sendKeys("60");
		Thread.sleep(200);
		
		//Dealership/Vendor
		String er25 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][dealership][]-error']")).getText();
    	String er26 = "This field is required.";
    	Assert.assertEquals(er25, er26);
		driver.findElement(By.name("data[new_vehicle_detail][dealership][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][dealership][]")).sendKeys("TestDealer");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='vehicle']/div/div[2]/a[2]")).click();
		Thread.sleep(2000);
		
		//next for check validatoin 
		driver.findElement(By.xpath("html/body/section[2]/div/div/div[2]/div/form/div/div[4]/div[2]/div/div/div/div[2]/div/div[2]/button[1]")).click();
		Thread.sleep(2000);
		
		//Copy of Drivers Licence (clear colour copy)*
		String vMsg107 = driver.findElement(By.xpath(".//*[@id='docDriverLic-error']")).getText();
		String vMsg108 = "This field is required.";   
		Assert.assertEquals(vMsg107, vMsg108);
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='upload']/div/div[1]/div[1]/div/span/label/span")).click();
    	driver.switchTo()            
	            .activeElement()
	            .sendKeys(
	                    "/home/nadsoft/Desktop/Backup/ISTQB/20MB.pdf");
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    Thread.sleep(3000);
	    Robot r3 = new Robot();
	    r3.keyPress(KeyEvent.VK_ESCAPE);
	    r3.keyRelease(KeyEvent.VK_ESCAPE);
	    
		try {
			WebElement e = driver.findElement(By.xpath(".//*[@id='upload']/div/div[2]/button[1]"));
		    boolean actualValue = e.isEnabled();
		    if (actualValue)
		           System.out.println("Button is enabled");
		    else
		           System.out.println("Button is disabled");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Thread.sleep(35000);
		
		try {
			WebElement e1 = driver.findElement(By.xpath(".//*[@id='upload']/div/div[2]/button[1]"));
		    boolean actualValue1 = e1.isEnabled();
		    if (actualValue1){
		           System.out.println("Button is enabled");
		           driver.findElement(By.xpath(".//*[@id='upload']/div/div[2]/button[1]")).click();
		    }else
		           System.out.println("Button is disabled");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	  //Most Recent Payslip 1 (must indicate YTD figures)*
	    String vMsg109 = driver.findElement(By.xpath(".//*[@id='docPayslip1-error']")).getText();
		String vMsg110 = "This field is required.";
		Assert.assertEquals(vMsg109, vMsg110);
		Thread.sleep(2000);
	    driver.findElement(By.xpath(".//*[@id='upload']/div/div[1]/div[2]/div/span/label/span")).click();
	      	driver.switchTo()            
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-1. paper.pdf");
	  	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	Thread.sleep(1000);
	  	r3.keyPress(KeyEvent.VK_ESCAPE);
	  	r3.keyRelease(KeyEvent.VK_ESCAPE);
	  	
	  	try {
	  			WebElement e = driver.findElement(By.xpath(".//*[@id='upload']/div/div[2]/button[1]"));
			    boolean actualValue = e.isEnabled();
			    if (actualValue)
			           System.out.println("Button is enabled");
			    else
			           System.out.println("Button is disabled");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		Thread.sleep(5000);
			
		try {
				WebElement e1 = driver.findElement(By.xpath(".//*[@id='upload']/div/div[2]/button[1]"));
			    boolean actualValue1 = e1.isEnabled();
			    if (actualValue1){
			           System.out.println("Button is enabled");
			           driver.findElement(By.xpath(".//*[@id='upload']/div/div[2]/button[1]")).click();
			    }else
			           System.out.println("Button is disabled");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
	  //Most Recent Payslip 2 (must indicate YTD figures)	  
	  String vMsg111 = driver.findElement(By.xpath(".//*[@id='docPayslip2-error']")).getText();
	  String vMsg112 = "This field is required.";
	  Assert.assertEquals(vMsg111, vMsg112);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath(".//*[@id='upload']/div/div[1]/div[3]/div/span/label/span")).click();
	      	driver.switchTo()
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-2. paper-2.pdf");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  Thread.sleep(1000);
	  r3.keyPress(KeyEvent.VK_ESCAPE);
	  r3.keyRelease(KeyEvent.VK_ESCAPE);    
	  	   
	  try {
				WebElement e = driver.findElement(By.xpath(".//*[@id='upload']/div/div[1]/div[3]/div/span/label/span"));
			    boolean actualValue = e.isEnabled();
			    if (actualValue)
			           System.out.println("Button is enabled");
			    else
			           System.out.println("Button is disabled");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		Thread.sleep(5000);
			
		try {
				WebElement e1 = driver.findElement(By.xpath(".//*[@id='upload']/div/div[1]/div[3]/div/span/label/span"));
			    boolean actualValue1 = e1.isEnabled();
			    if (actualValue1){
			           System.out.println("Button is enabled");
			    }else
			           System.out.println("Button is disabled");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	  //Rates Notices for Home Owner (If applicable)
	  driver.findElement(By.xpath(".//*[@id='upload']/div/div[1]/div[4]/div/span/label/span")).click();
	  driver.switchTo()
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Ian & Tanya Jones Contract To Sign.pdf");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  Thread.sleep(5000);
	  r3.keyPress(KeyEvent.VK_ESCAPE);
	  r3.keyRelease(KeyEvent.VK_ESCAPE);
	  Thread.sleep(2000);
	  	    
	  //Other
	  driver.findElement(By.xpath(".//*[@id='upload']/div/div[1]/div[5]/div/span/label/span")).click();
	  driver.switchTo()
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-5.pdf");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  Thread.sleep(5000);
	  r3.keyPress(KeyEvent.VK_ESCAPE);
	  r3.keyRelease(KeyEvent.VK_ESCAPE);
	  Thread.sleep(2000); 
	
	  //submit
	  driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[5]/div/button")).click();
	  Thread.sleep(15000);		
				
				
	}
	
	public void login() throws InterruptedException{
		
		//partner login link
		driver.findElement(By.xpath(".//*[@id='menu-item-1481']/a")).click();
		Thread.sleep(2000);          
		

		//Enter user name
		driver.findElement(By.id("user_login")).clear();
		driver.findElement(By.id("user_login")).sendKeys("admin");
		Thread.sleep(1000);
		
		//Enter password
		driver.findElement(By.id("user_pass")).clear();
		driver.findElement(By.id("user_pass")).sendKeys("DriveON!23");
		Thread.sleep(2000);
		
		//Login button
		//driver.findElement(By.id("wp-submit")).click();
		driver.findElement(By.name("wp-submit")).click();
		Thread.sleep(2000);
		
		String actual = driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/h3")).getText();
		System.out.println(actual);
		String expected = "Hi Pit Crew, welcome back";
		
		Assert.assertEquals(expected, actual);
		
		System.out.println("Admin login successfull");
	}
	
	public void verifyPersonalForm() throws InterruptedException{
		
		///Applications link
		driver.findElement(By.xpath(".//*[@id='side-menu']/li[2]/a")).click();
		Thread.sleep(2000);          
		
		//view all
		driver.findElement(By.xpath(".//*[@id='side-menu']/li[2]/ul/li[3]/a")).click();
		Thread.sleep(4000);          
		
		String actual = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
		System.out.println(actual);                                                            

		String expected = "Test PersonalLoan";

		
		Assert.assertEquals(actual, expected);
		
		System.out.println("Personal loan form added successfull");
	}
	
	public void logout() throws InterruptedException{
		//Click logout button
		driver.findElement(By.xpath(".//*[@id='wrapper']/nav/div[1]/div[3]/ul/li/a")).click();
		Thread.sleep(1000);
				
		//logout
		driver.findElement(By.xpath(".//*[@id='wrapper']/nav/div[1]/div[3]/ul/li/ul/li[5]/a")).click();
		Thread.sleep(2000);
				
		String actual = driver.getCurrentUrl();
		System.out.println(actual);
		String expected = "http://www.drive-on.com.au/login/?logged_out=true";
		                   
		Assert.assertEquals(actual, expected);
				
		System.out.println("Admin logout successfull");
				
		System.out.println("====================================================================");
	}
}