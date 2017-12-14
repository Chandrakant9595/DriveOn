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

public class adminActivity {
  
	@Test
  public void testScripts() throws InterruptedException, AWTException {
	  
		adminLogin();
		
		//Ouick Quote----------------------------------------------------------
		//adminQuickQuoteForConsumerForm();
		//adminQuickQuoteForCommericalForm();
		
		
		//Applications ------------------------------------------------------- view all -
		addPersonalLoanApplication();
		//editPersonalLoanApplicationWithANZ();
		//addPersonalLoanApplication();
		//editPersonalLoanApplicationWithLiberty();
		//addPersonalLoanApplication();
	    //editPersonalLoanApplicationWithDSriveOn();
		
	    
		//addBusinessLoanApplication();
		//editBusinessLoanApplication();
		//addBusinessLoanApplication();
		//editBusinessLoanApplicationWithDSriveOn();
		//adminLogout();
  }
  
  public static WebDriver driver;
  public static JavascriptExecutor jse;
  public static Robot robot;
  
  @BeforeClass
 	public void testSetup() {
 		
	  
		 // To run scripts on chrome browser
		  System.setProperty("webdriver.chrome.driver" ,
		  "/home/nadsoft/AutomationSoftware/ChromrDriver/chromedriver"); 
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
 	
 	public void adminLogin() throws InterruptedException, AWTException{
 		
 		System.out.println("====================================================================");
		
		System.out.println("Admin Login");
		            
		driver.get("https://www.drive-on.com.au/login/");
		Thread.sleep(2000);
		
		//Enter user name
		driver.findElement(By.id("user_login")).clear();
		driver.findElement(By.id("user_login")).sendKeys("admin");
		Thread.sleep(1000);
		
		//Enter password
		driver.findElement(By.id("user_pass")).clear();
		driver.findElement(By.id("user_pass")).sendKeys("Wedriveon16!");
		Thread.sleep(2000);
		
		//Login button
		//driver.findElement(By.id("wp-submit")).click();
		driver.findElement(By.name("wp-submit")).click();
		Thread.sleep(2000);
		
		//Direct press enter key 
		/*Actions action = new Actions(driver); 
		action.sendKeys(Keys.ENTER).build().perform();*/
		
		/*String actual = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[1]/div/h3")).getText();
		System.out.println(actual);
		String expected = "Hi , welcome back";
		
		Assert.assertEquals(expected, actual);*/
		
		System.out.println("Admin login successfull");
		
		System.out.println("====================================================================");
 	}
 	
 	public void adminLogout() throws InterruptedException{
 		
 		System.out.println("Admin Logout");
		
		//Click logout button
		driver.findElement(By.xpath(".//*[@id='wrapper']/nav/ul/li/a")).click();
		Thread.sleep(1000);
		
		//logout
		driver.findElement(By.xpath(".//*[@id='wrapper']/nav/ul/li/ul/li[5]/a")).click();
		Thread.sleep(2000);
		
		String actual = driver.getCurrentUrl();
		System.out.println(actual);
		String expected = "http://www.godrivefinance.com.au/login/?logged_out=true";
		
		Assert.assertEquals(actual, expected);
		
		System.out.println("Admin logout successfull");
		
		System.out.println("====================================================================");
 	}
 	
 	public void adminQuickQuoteForConsumerForm() throws InterruptedException, AWTException{
 		
 		System.out.println("Admin add quick quote for consumer form");
 		
 		//Quick quote link
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[12]/a")).click();
 		Thread.sleep(3000);
 		
 		//Finance for
 		WebElement financeOption = driver.findElement(By.id("finance_type"));
		Select option = new Select(financeOption);
		option.selectByVisibleText("Consumer");
		Thread.sleep(5000);
 		
		//Proceed to application
		driver.findElement(By.xpath(".//*[@id='proceedToApp']")).click();
		Thread.sleep(2000);
		
		//How many Applicants are there? 
		driver.findElement(By.name("data[before-we-start][applicant-are-there]")).clear();
		driver.findElement(By.name("data[before-we-start][applicant-are-there]")).sendKeys("1");
		Thread.sleep(1000);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(2000);
		
		//I acknowledge that the information provided by me is true and correct*
		driver.findElement(By.xpath(".//*[@id='custom-privacy-declaration']/div[3]/div/label/div/ins")).click();
		Thread.sleep(1000);          
		
		//Agree
		driver.findElement(By.xpath(".//*[@id='agree']")).click();
		Thread.sleep(3000);
		
		String actual = driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[1]/div/div/div/div/div[1]/div[1]/h4")).getText();
		System.out.println(actual);
		String expected = "Personal Details";
		Assert.assertEquals(actual, expected);
		
		//scroll down
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,950)", "");
				Thread.sleep(3000);
				
				//next button for validation check
				driver.findElement(By.xpath(".//*[@id='next1']")).click();
				Thread.sleep(3000);          
				
				//scroll up
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-850)", "");
				Thread.sleep(5000);
		
				//Personal Details -
				
				//capture the validation msg
				String vMsg1 = driver.findElement(By.xpath(".//*[@id='data[individual_application][borrow_type1][]-error']")).getText();
				String vMsg2 = "This field is required.";   
				Assert.assertEquals(vMsg1, vMsg2);
				//Borrower Type - 	Primary Buyer 
				List <WebElement> radios = driver.findElements(By.name("data[individual_application][borrow_type1][]"));
				int o = radios.size();                                        
				System.out.println(o);
				radios.get(0).click();
				Thread.sleep(200);
				
				
				//First name
				//capture the validation msg
				String vMsg3 = driver.findElement(By.xpath(".//*[@id='data[personal_details][fname][]-error']")).getText();
				String vMsg4 = "This field is required.";
				Assert.assertEquals(vMsg3, vMsg4);
				driver.findElement(By.name("data[personal_details][fname][]")).clear();
				driver.findElement(By.name("data[personal_details][fname][]")).sendKeys("Test");
				Thread.sleep(200);

				
				//Middle name
				//capture the validation msg
				String vMsg5 = driver.findElement(By.xpath(".//*[@id='data[personal_details][mname][]-error']")).getText();
				String vMsg6 = "This field is required.";
				Assert.assertEquals(vMsg5, vMsg6);
				driver.findElement(By.name("data[personal_details][mname][]")).clear();
				driver.findElement(By.name("data[personal_details][mname][]")).sendKeys("Demo");
				Thread.sleep(200);
				
				
				//Surname
				//capture the validation msg
				String vMsg7 = driver.findElement(By.xpath(".//*[@id='data[personal_details][surname][]-error']")).getText();
				String vMsg8 = "This field is required.";
				Assert.assertEquals(vMsg7, vMsg8);
				driver.findElement(By.name("data[personal_details][surname][]")).clear();
				driver.findElement(By.name("data[personal_details][surname][]")).sendKeys("ConsumerQuickQuote");
				Thread.sleep(200);
				
				
				//Gender
				//capture the validation msg
				String vMsg9 = driver.findElement(By.xpath(".//*[@id='data[personal_details][gender1][]-error']")).getText();
				String vMsg10 = "This field is required.";
				Assert.assertEquals(vMsg9, vMsg10);
				List <WebElement> genders = driver.findElements(By.name("data[personal_details][gender1][]"));
				int gender = genders.size();       
				System.out.println(gender);
				genders.get(0).click();
				Thread.sleep(200);
			
				
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
				
				
				//Marital Status
				//capture the validation msg
				String vMsg13 = driver.findElement(By.xpath(".//*[@id='data[personal_details][marital_status][]-error']")).getText();
				String vMsg14 = "This field is required.";
				Assert.assertEquals(vMsg13, vMsg14);
				WebElement gender1 = driver.findElement(By.name("data[personal_details][marital_status][]"));
				Select g = new Select(gender1);
				g.selectByVisibleText("Single");
				Thread.sleep(500);
				
				
				//Drivers License No
				//capture the validation msg
				String vMsg15 = driver.findElement(By.xpath(".//*[@id='data[personal_details][driver_license_no][]-error']")).getText();
				String vMsg16 = "This field is required.";
				Assert.assertEquals(vMsg15, vMsg16);
				driver.findElement(By.name("data[personal_details][driver_license_no][]")).clear();
				driver.findElement(By.name("data[personal_details][driver_license_no][]")).sendKeys("8451245");
				Thread.sleep(200);
				
				
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

				
				//No.of Departments
				//capture the validation msg
				String vMsg19 = driver.findElement(By.xpath(".//*[@id='no_of_dependent-error']")).getText();
				String vMsg20 = "This field is required.";
				Assert.assertEquals(vMsg19, vMsg20);
				driver.findElement(By.name("data[personal_details][no_of_department][]")).clear();
				driver.findElement(By.name("data[personal_details][no_of_department][]")).sendKeys("2");
				Thread.sleep(200);

				
				//Age of Department
				driver.findElement(By.name("data[personal_details][age_of_depart][]")).clear();
				driver.findElement(By.name("data[personal_details][age_of_depart][]")).sendKeys("1");
				Thread.sleep(200);
				
				
				//Residency Status
				//capture the validation msg
				String vMsg21 = driver.findElement(By.xpath(".//*[@id='data[personal_details][residential_status1][]-error']")).getText();
				String vMsg22 = "This field is required.";
				Assert.assertEquals(vMsg21, vMsg22);
				List <WebElement> residency = driver.findElements(By.name("data[personal_details][residential_status1][]"));
				int ststus = residency.size();       
				System.out.println(ststus);
				residency.get(1).click();
				Thread.sleep(200);
				
				
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
				//capture the validation msg
				String vMsg27 = driver.findElement(By.xpath(".//*[@id='data[personal_details][home_phone][]-error']")).getText();
				String vMsg28 = "This field is required.";
				Assert.assertEquals(vMsg27, vMsg28);
				driver.findElement(By.name("data[personal_details][home_phone][]")).clear();
				driver.findElement(By.name("data[personal_details][home_phone][]")).sendKeys("02356784512");
				Thread.sleep(200);
			
				
				//Direct Debit Bank account Details  - Account Name
				//capture the validation msg
				String vMsg29 = driver.findElement(By.xpath(".//*[@id='data[personal_details][account_name][]-error']")).getText();
				String vMsg30 = "This field is required.";
				Assert.assertEquals(vMsg29, vMsg30);
				driver.findElement(By.name("data[personal_details][account_name][]")).clear();
				driver.findElement(By.name("data[personal_details][account_name][]")).sendKeys("Yes bank");
				Thread.sleep(200);

				
				//BSB
				//capture the validation msg
				String vMsg31 = driver.findElement(By.xpath(".//*[@id='data[personal_details][bsb][]-error']")).getText();
				String vMsg32 = "This field is required.";
				Assert.assertEquals(vMsg31, vMsg32);
				driver.findElement(By.name("data[personal_details][bsb][]")).clear();
				driver.findElement(By.name("data[personal_details][bsb][]")).sendKeys("78452");
				Thread.sleep(200);
			
				
				//AccountNo
				//capture the validation msg
				String vMsg33 = driver.findElement(By.xpath(".//*[@id='data[personal_details][account_no][]-error']")).getText();
				String vMsg34 = "This field is required.";   
				Assert.assertEquals(vMsg33, vMsg34);
				driver.findElement(By.name("data[personal_details][account_no][]")).clear();
				driver.findElement(By.name("data[personal_details][account_no][]")).sendKeys("89451245");
				Thread.sleep(200);          
				
				
				//next
				driver.findElement(By.xpath(".//*[@id='next1']")).click();
				Thread.sleep(2000);
				
				//scroll down
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,950)", "");
				Thread.sleep(3000);
				
				//next button for validation check
				driver.findElement(By.xpath(".//*[@id='next2']")).click();
				Thread.sleep(3000);   
				
				//scroll up
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-850)", "");
				Thread.sleep(5000);
				
				//Current Address Details - Current Address
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
		    	Thread.sleep(2000);
				
				//Postal Code
		    	//capture the validation msg
				String vMsg41 = driver.findElement(By.xpath(".//*[@id='data[address_details][postal_code][]-error']")).getText();
				String vMsg42 = "This field is required.";
				Assert.assertEquals(vMsg41, vMsg42);
				driver.findElement(By.name("data[address_details][postal_code][]")).clear();
				driver.findElement(By.name("data[address_details][postal_code][]")).sendKeys("45121");
				Thread.sleep(200);
				
				//Time At Address
				WebElement years1 = driver.findElement(By.name("data[address_details][time_at_address_yrs][]"));
		    	Select year1 = new Select (years1);
		    	year1.selectByVisibleText("1 Years");
		    	Thread.sleep(1000);
		    	
		    	WebElement months1 = driver.findElement(By.name("data[address_details][time_at_address_months][]"));
		    	Select month1 = new Select (months1);
		    	month1.selectByVisibleText("4 Months");
		    	Thread.sleep(1000);
				
				//Current Resident Type
		    	//capture the validation msg
				String vMsg43 = driver.findElement(By.xpath(".//*[@id='data[address_details][residential_type1][]-error']")).getText();
				String vMsg44 = "This field is required.";
				Assert.assertEquals(vMsg43, vMsg44);
				List <WebElement> cResidency = driver.findElements(By.name("data[address_details][residential_type1][]"));
				int type = cResidency.size();                                                         
				System.out.println(type);
				cResidency.get(0).click();
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
				
				//Previous Address Details - Previous Address
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
				    	Thread.sleep(2000);
				
				//Postal Code
				    	String vMsg51 = driver.findElement(By.xpath(".//*[@id='data[address_details][previous_postal_code][]-error']")).getText();
						String vMsg52 = "This field is required.";
						Assert.assertEquals(vMsg51, vMsg52);
				driver.findElement(By.name("data[address_details][previous_postal_code][]")).clear();
				driver.findElement(By.name("data[address_details][previous_postal_code][]")).sendKeys("45254");
				Thread.sleep(200);
				
				//next
				driver.findElement(By.xpath(".//*[@id='next2']")).click();
				Thread.sleep(2000);
				
				//scroll down
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,950)", "");
				Thread.sleep(3000);
				
				//next button for validation check
				driver.findElement(By.xpath(".//*[@id='next3']")).click();
				Thread.sleep(3000);   
				
				//scroll up
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-850)", "");
				Thread.sleep(5000);
				
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
				
				//Net Income $
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
				List <WebElement> radios2 = driver.findElements(By.name("data[employment_details][period1][]"));
				int o2 = radios2.size();                                        
				System.out.println(o2);
				radios2.get(2).click();
				Thread.sleep(200);
				
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
				
				//next
				driver.findElement(By.xpath(".//*[@id='next3']")).click();
				Thread.sleep(2000);
				
				//scroll down
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,950)", "");
				Thread.sleep(3000);
				
				//next button for validation check
				driver.findElement(By.xpath(".//*[@id='next4']")).click();
				Thread.sleep(3000);   
				
				//scroll up
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-850)", "");
				Thread.sleep(5000);
				
				//Vehicle Details - Vehicle For
				String vMsg73 = driver.findElement(By.xpath(".//*[@id='data[personal_details][vehicle_for1][]-error']")).getText();
				String vMsg74 = "This field is required.";
				Assert.assertEquals(vMsg73, vMsg74);
				List <WebElement> vehicle = driver.findElements(By.name("data[personal_details][vehicle_for1][]"));
				int v = vehicle.size();       
				System.out.println(v);
				vehicle.get(0).click();
				Thread.sleep(200);
				
				//Make
				String vMsg75 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][make][]-error']")).getText();
				String vMsg76 = "This field is required.";
				Assert.assertEquals(vMsg75, vMsg76);
				driver.findElement(By.name("data[new_vehicle_detail][make][]")).clear();
				driver.findElement(By.name("data[new_vehicle_detail][make][]")).sendKeys("Maruti");
				Thread.sleep(200);
				
				//Model
				String vMsg77 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][model][]-error']")).getText();
				String vMsg78 = "This field is required.";
				Assert.assertEquals(vMsg77, vMsg78);
				driver.findElement(By.name("data[new_vehicle_detail][model][]")).clear();
				driver.findElement(By.name("data[new_vehicle_detail][model][]")).sendKeys("2000");
				Thread.sleep(200);
				
				//Year
				WebElement months51 = driver.findElement(By.name("data[new_vehicle_detail][year][]"));
		    	Select month51 = new Select (months51);
		    	month51.selectByVisibleText("2015");
		    	Thread.sleep(1000);
				
				//KMs
		    	String vMsg79 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][kms][]-error']")).getText();
				String vMsg80 = "This field is required.";
				Assert.assertEquals(vMsg79, vMsg80);
				driver.findElement(By.name("data[new_vehicle_detail][kms][]")).clear();
				driver.findElement(By.name("data[new_vehicle_detail][kms][]")).sendKeys("200");
				Thread.sleep(200);
				
				//Accessories
				driver.findElement(By.name("data[new_vehicle_detail][accessories][]")).clear();
				driver.findElement(By.name("data[new_vehicle_detail][accessories][]")).sendKeys("Yes");
				Thread.sleep(200);
				
				//Amount to Finance ($)
				String vMsg81 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][amount_to_finance][]-error']")).getText();
				String vMsg82 = "This field is required.";
				Assert.assertEquals(vMsg81, vMsg82);
				driver.findElement(By.name("data[new_vehicle_detail][amount_to_finance][]")).clear();
				driver.findElement(By.name("data[new_vehicle_detail][amount_to_finance][]")).sendKeys("10000");
				Thread.sleep(200);
				
				//Term
				String vMsg83 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][term][]-error']")).getText();
				String vMsg84 = "This field is required.";
				Assert.assertEquals(vMsg83, vMsg84);
				WebElement years5 = driver.findElement(By.name("data[new_vehicle_detail][term][]"));
		    	Select year5 = new Select (years5);             
		    	year5.selectByVisibleText("1");
		    	Thread.sleep(1000);
				
				//Baloon
		    	String vMsg85 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][baloon][]-error']")).getText();
				String vMsg86 = "This field is required.";
				Assert.assertEquals(vMsg85, vMsg86);
				driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).clear();
				driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).sendKeys("60");
				Thread.sleep(200);
				
				//Assets and Libialities - Home
				driver.findElement(By.name("data[assets_libility][home][]")).clear();
				driver.findElement(By.name("data[assets_libility][home][]")).sendKeys("5000");
				Thread.sleep(200);
				
				//Investment Property
				driver.findElement(By.name("data[assets_libility][investment_property][]")).clear();
				driver.findElement(By.name("data[assets_libility][investment_property][]")).sendKeys("1200");
				Thread.sleep(200);
				
				//Cash Back
				driver.findElement(By.name("data[assets_libility][cash_back][]")).clear();
				driver.findElement(By.name("data[assets_libility][cash_back][]")).sendKeys("1000");
				Thread.sleep(200);
				
				//Vehicles
				driver.findElement(By.name("data[assets_libility][vehicles][]")).clear();
				driver.findElement(By.name("data[assets_libility][vehicles][]")).sendKeys("410");
				Thread.sleep(200);
				
				//Household Effects
				driver.findElement(By.name("data[assets_libility][household_effect][]")).clear();
				driver.findElement(By.name("data[assets_libility][household_effect][]")).sendKeys("200");
				Thread.sleep(200);
				
				//Shares
				driver.findElement(By.name("data[assets_libility][shares][]")).clear();
				driver.findElement(By.name("data[assets_libility][shares][]")).sendKeys("100");
				Thread.sleep(200);
				
				//Mortgage/Rent
				driver.findElement(By.name("data[assets_libility][mortgage_rent][monthly_payment][]")).clear();
				driver.findElement(By.name("data[assets_libility][mortgage_rent][monthly_payment][]")).sendKeys("100");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][mortgage_rent][balance_owing][]")).clear();
				driver.findElement(By.name("data[assets_libility][mortgage_rent][balance_owing][]")).sendKeys("200");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][mortgage_rent][bank_institute][]")).clear();
				driver.findElement(By.name("data[assets_libility][mortgage_rent][bank_institute][]")).sendKeys("300");
				Thread.sleep(200);
				
				//Investment Mortgage
				driver.findElement(By.name("data[assets_libility][investment_mortgage][monthly_payment][]")).clear();
				driver.findElement(By.name("data[assets_libility][investment_mortgage][monthly_payment][]")).sendKeys("300");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][investment_mortgage][balance_owing][]")).clear();
				driver.findElement(By.name("data[assets_libility][investment_mortgage][balance_owing][]")).sendKeys("200");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][investment_mortgage][bank_institute][]")).clear();
				driver.findElement(By.name("data[assets_libility][investment_mortgage][bank_institute][]")).sendKeys("100");
				Thread.sleep(200);
				
				//Personal Loans
				driver.findElement(By.name("data[assets_libility][personal_loan][monthly_payment][]")).clear();
				driver.findElement(By.name("data[assets_libility][personal_loan][monthly_payment][]")).sendKeys("500");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][personal_loan][balance_owing][]")).clear();
				driver.findElement(By.name("data[assets_libility][personal_loan][balance_owing][]")).sendKeys("600");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][personal_loan][bank_institute][]")).clear();
				driver.findElement(By.name("data[assets_libility][personal_loan][bank_institute][]")).sendKeys("700");
				Thread.sleep(200);
				
				//General Living Expenses
				driver.findElement(By.name("data[assets_libility][general_living][monthly_payment][]")).clear();
				driver.findElement(By.name("data[assets_libility][general_living][monthly_payment][]")).sendKeys("110");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][general_living][balance_owing][]")).clear();
				driver.findElement(By.name("data[assets_libility][general_living][balance_owing][]")).sendKeys("201");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][general_living][bank_institute][]")).clear();
				driver.findElement(By.name("data[assets_libility][general_living][bank_institute][]")).sendKeys("101");
				Thread.sleep(200);
				
				//Child Support
				driver.findElement(By.name("data[assets_libility][child_support][monthly_payment][]")).clear();
				driver.findElement(By.name("data[assets_libility][child_support][monthly_payment][]")).sendKeys("411");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][child_support][balance_owing][]")).clear();
				driver.findElement(By.name("data[assets_libility][child_support][balance_owing][]")).sendKeys("121");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][child_support][bank_institute][]")).clear();
				driver.findElement(By.name("data[assets_libility][child_support][bank_institute][]")).sendKeys("111");
				Thread.sleep(200);
				
				//Car Loans
				driver.findElement(By.name("data[assets_libility][car_loans][monthly_payment][]")).clear();
				driver.findElement(By.name("data[assets_libility][car_loans][monthly_payment][]")).sendKeys("110");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][car_loans][balance_owing][]")).clear();
				driver.findElement(By.name("data[assets_libility][car_loans][balance_owing][]")).sendKeys("211");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][car_loans][bank_institute][]")).clear();
				driver.findElement(By.name("data[assets_libility][car_loans][bank_institute][]")).sendKeys("121");
				Thread.sleep(200);
				
				//Credit Cards
				driver.findElement(By.name("data[assets_libility][credit_card][monthly_payment][]")).clear();
				driver.findElement(By.name("data[assets_libility][credit_card][monthly_payment][]")).sendKeys("100");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][credit_card][balance_owing][]")).clear();
				driver.findElement(By.name("data[assets_libility][credit_card][balance_owing][]")).sendKeys("111");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][credit_card][bank_institute][]")).clear();
				driver.findElement(By.name("data[assets_libility][credit_card][bank_institute][]")).sendKeys("111");
				Thread.sleep(200);
				
				//Other
				driver.findElement(By.name("data[assets_libility][other][monthly_payment][]")).clear();
				driver.findElement(By.name("data[assets_libility][other][monthly_payment][]")).sendKeys("101");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][other][balance_owing][]")).clear();
				driver.findElement(By.name("data[assets_libility][other][balance_owing][]")).sendKeys("841");
				Thread.sleep(200);
				driver.findElement(By.name("data[assets_libility][other][bank_institute][]")).clear();
				driver.findElement(By.name("data[assets_libility][other][bank_institute][]")).sendKeys("151");
				Thread.sleep(200);
				
				//next
				driver.findElement(By.xpath(".//*[@id='next4']")).click();
				Thread.sleep(3000);
				
				//scroll down
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,950)", "");
				Thread.sleep(3000);
				
				//next button for validation check
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(3000);             
				
				//scroll up
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-850)", "");
				Thread.sleep(5000);
				
				//References One - First name
				String vMsg87 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_1][]-error']")).getText();
				String vMsg88 = "This field is required.";
				Assert.assertEquals(vMsg87, vMsg88);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][fname_1][]")).clear();
				driver.findElement(By.name("data[personal_reference][fname_1][]")).sendKeys("Salman");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);
				
				//Surname
				String vMsg89 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][surname_1][]-error']")).getText();
				String vMsg90 = "This field is required.";
				Assert.assertEquals(vMsg89, vMsg90);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][surname_1][]")).clear();
				driver.findElement(By.name("data[personal_reference][surname_1][]")).sendKeys("Khan");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);
				
				//CurrentAddress
				String vMsg91 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][current_address_1][]-error']")).getText();
				String vMsg92 = "This field is required.";
				Assert.assertEquals(vMsg91, vMsg92);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][current_address_1][]")).clear();
				driver.findElement(By.name("data[personal_reference][current_address_1][]")).sendKeys("Pune");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);
				
				//Phone No
				String vMsg93 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_1][]-error']")).getText();
				String vMsg94 = "This field is required.";
				Assert.assertEquals(vMsg93, vMsg94);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][phone_1][]")).clear();
				driver.findElement(By.name("data[personal_reference][phone_1][]")).sendKeys("9856451245");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);
				
				//Relationship
				String vMsg95 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][relationship_1][]-error']")).getText();
				String vMsg96 = "This field is required.";
				Assert.assertEquals(vMsg95, vMsg96);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][relationship_1][]")).clear();
				driver.findElement(By.name("data[personal_reference][relationship_1][]")).sendKeys("Friend");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);
				
				//References Two - First name
				String vMsg97 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_2][]-error']")).getText();
				String vMsg98 = "This field is required.";
				Assert.assertEquals(vMsg97, vMsg98);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][fname_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][fname_2][]")).sendKeys("Amir");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);	
				
				//Surname
				String vMsg99 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][surname_2][]-error']")).getText();
				String vMsg100 = "This field is required.";
				Assert.assertEquals(vMsg99, vMsg100);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][surname_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][surname_2][]")).sendKeys("Khan");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);
				
				//CurrentAddress
				String vMsg101 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][current_address_2][]-error']")).getText();
				String vMsg102 = "This field is required.";
				Assert.assertEquals(vMsg101, vMsg102);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][current_address_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][current_address_2][]")).sendKeys("Pune");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);
				
				//Phone No
				String vMsg103 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_2][]-error']")).getText();
				String vMsg104 = "This field is required.";
				Assert.assertEquals(vMsg103, vMsg104);
				Thread.sleep(200);
				driver.findElement(By.name("data[personal_reference][phone_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][phone_2][]")).sendKeys("9856451245");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);
				
				//Relationship
				String vMsg105 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][relationship_2][]-error']")).getText();
				String vMsg106 = "This field is required.";
				Assert.assertEquals(vMsg105, vMsg106);
				Thread.sleep(2000);
				driver.findElement(By.name("data[personal_reference][relationship_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][relationship_2][]")).sendKeys("Friend");
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
				Thread.sleep(200);
				
				//Payslip 1
				String vMsg107 = driver.findElement(By.xpath(".//*[@id='docDriverLic-error']")).getText();
				String vMsg108 = "This field is required.";
				Assert.assertEquals(vMsg107, vMsg108);
				Thread.sleep(2000);
				driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[3]/div/div[1]/div/div/div/span/label/span")).click();
		    	driver.switchTo()
			            .activeElement()
			            .sendKeys(
			                    "/home/nadsoft/Desktop/DriveOnData/PayslipOne.PDF");
			    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			    Thread.sleep(5000);
			    Robot r3 = new Robot();
			    r3.keyPress(KeyEvent.VK_ESCAPE);
			    r3.keyRelease(KeyEvent.VK_ESCAPE);
			    driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
			    Thread.sleep(2000);
			    
			  //Payslip 2
			    String vMsg109 = driver.findElement(By.xpath(".//*[@id='docPayslip1-error']")).getText();
				String vMsg110 = "This field is required.";
				Assert.assertEquals(vMsg109, vMsg110);
				Thread.sleep(2000);
			  		driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[3]/div/div[2]/div/div/div/span/label/span")).click();
			      	driver.switchTo()            
			  	            .activeElement()
			  	            .sendKeys(
			  	                    "/home/nadsoft/Desktop/DriveOnData/PayslipTwo.PDF");
			  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  	    Thread.sleep(5000);
			  	    r3.keyPress(KeyEvent.VK_ESCAPE);
			  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
			  	  driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
			  	    Thread.sleep(2000);
			  	    
			  //Copy of Driver's Licenses	  
			  	  String vMsg111 = driver.findElement(By.xpath(".//*[@id='docPayslip2-error']")).getText();
					String vMsg112 = "This field is required.";
					Assert.assertEquals(vMsg111, vMsg112);
					Thread.sleep(2000);
			  	  driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[3]/div/div[3]/div/div/div/span/label/span")).click();
			      	driver.switchTo()
			  	            .activeElement()
			  	            .sendKeys(
			  	                    "/home/nadsoft/Desktop/DriveOnData/DriverLicenses.PDF");
			  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  	    Thread.sleep(5000);
			  	    r3.keyPress(KeyEvent.VK_ESCAPE);
			  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
			  	 // driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
			  	    Thread.sleep(2000);
			  	    
			  //Rates Notices for Home Owner (If applicable)
			  	  driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[3]/div/div[4]/div/div/div/span/label/span")).click();
			      	driver.switchTo()
			  	            .activeElement()
			  	            .sendKeys(
			  	                    "/home/nadsoft/Desktop/DriveOnData/RatesNoticesForHomeOwner.PDF");
			  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  	    Thread.sleep(5000);
			  	    r3.keyPress(KeyEvent.VK_ESCAPE);
			  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
			  	    Thread.sleep(2000);
			  	    
			  	//Other
				  	  driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[3]/div/div[5]/div/div/div/span/label/span")).click();
				      	driver.switchTo()
				  	            .activeElement()
				  	            .sendKeys(
				  	                    "/home/nadsoft/Desktop/DriveOnData/RatesNoticesForHomeOwner.PDF");
				  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				  	    Thread.sleep(5000);
				  	    r3.keyPress(KeyEvent.VK_ESCAPE);
				  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
				  	    Thread.sleep(2000); 
				
				//submit
				/*driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[5]/div/button")).click();
				Thread.sleep(15000);*/
		
		/*String actual1 = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
		System.out.println(actual1);                  
		String expected1 = "Test ConsumerQuickQuote";
		
		Assert.assertEquals(actual1, expected1);
		
		System.out.println("Admin add successfull with quick quote consumer form");
		
		System.out.println("====================================================================");*/
 	}
 	
 	public void adminQuickQuoteForCommericalForm() throws InterruptedException, AWTException{
 		
 		System.out.println("Admin add quick quote for commerical form");
 		
 		//Quick quote link
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[4]/a")).click();
 		Thread.sleep(3000);
		
		//Proceed to application
		driver.findElement(By.xpath(".//*[@id='proceedToApp']")).click();
		Thread.sleep(2000);
		
		//How many Applicants are there? 
		driver.findElement(By.name("data[before-we-start][applicant-are-there]")).clear();
		driver.findElement(By.name("data[before-we-start][applicant-are-there]")).sendKeys("1");
		Thread.sleep(1000);
				
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(2000);
				
		//I acknowledge that the information provided by me is true and correct
		driver.findElement(By.xpath(".//*[@id='custom-privacy-declaration']/div[3]/div/label/div/ins")).click();
		Thread.sleep(1000);
		
		//agree
		driver.findElement(By.xpath(".//*[@id='agree']")).click();
		Thread.sleep(2000);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,950)", "");
		Thread.sleep(3000);
		
		//next button for validation check
		driver.findElement(By.xpath(".//*[@id='next7']")).click();
		Thread.sleep(3000);   
		
		//scroll up
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-850)", "");
		Thread.sleep(5000);
		
		
//Company details
		//Borrower Type - Primary Buyer
		String vMsg107 = driver.findElement(By.xpath(".//*[@id='data[company_application][borrow_type1][]-error']")).getText();
		String vMsg108 = "This field is required.";
		Assert.assertEquals(vMsg107, vMsg108);
		List <WebElement> radios1 = driver.findElements(By.name("data[company_application][borrow_type1][]"));
		radios1.get(0).click();
		Thread.sleep(200);
		
		//Company details - Trading Name
		String vMsg109 = driver.findElement(By.xpath(".//*[@id='data[company_details][tradingname][]-error']")).getText();
		String vMsg110 = "This field is required.";
		Assert.assertEquals(vMsg109, vMsg110);
		driver.findElement(By.name("data[company_details][tradingname][]")).clear();
		driver.findElement(By.name("data[company_details][tradingname][]")).sendKeys("Test Micro trading");
		Thread.sleep(200);
		
		//Legal Name
		String vMsg111 = driver.findElement(By.xpath(".//*[@id='data[company_details][legalname][]-error']")).getText();
		String vMsg112 = "This field is required.";
		Assert.assertEquals(vMsg111, vMsg112);
		driver.findElement(By.name("data[company_details][legalname][]")).clear();
		driver.findElement(By.name("data[company_details][legalname][]")).sendKeys("Micro");
		Thread.sleep(200);
		
		//Type of Business
		String vMsg113 = driver.findElement(By.xpath(".//*[@id='data[company_details][type_of_business][]-error']")).getText();
		String vMsg114 = "This field is required.";
		Assert.assertEquals(vMsg113, vMsg114);
		driver.findElement(By.name("data[company_details][type_of_business][]")).clear();
		driver.findElement(By.name("data[company_details][type_of_business][]")).sendKeys("Electric");
		Thread.sleep(200);
		
		//ACN/ABN
		String vMsg115 = driver.findElement(By.xpath(".//*[@id='data[company_details][acn_abn][]-error']")).getText();
		String vMsg116 = "This field is required.";
		Assert.assertEquals(vMsg115, vMsg116);
		driver.findElement(By.name("data[company_details][acn_abn][]")).clear();
		driver.findElement(By.name("data[company_details][acn_abn][]")).sendKeys("455645");
		Thread.sleep(200);
		
		//Date Established
		String vMsg117 = driver.findElement(By.xpath(".//*[@id='data[company_details][date_esta][]-error']")).getText();
		String vMsg118 = "This field is required.";
		Assert.assertEquals(vMsg117, vMsg118);
		WebElement established = driver.findElement(By.name("data[company_details][date_esta][]"));
		established.clear();
		established.sendKeys("05/05/2001");
		Thread.sleep(200);
		established.sendKeys(Keys.TAB);
		
		//Current Address
		String vMsg119 = driver.findElement(By.xpath(".//*[@id='data[company_details][current_address][]-error']")).getText();
		String vMsg120 = "This field is required.";
		Assert.assertEquals(vMsg119, vMsg120);
		driver.findElement(By.name("data[company_details][current_address][]")).clear();
		driver.findElement(By.name("data[company_details][current_address][]")).sendKeys("Pune");
		Thread.sleep(200);
		
		//Address Type : RENTING
		String vMsg121 = driver.findElement(By.xpath(".//*[@id='data[company_details][address_type1][]-error']")).getText();
		String vMsg122 = "This field is required.";
		Assert.assertEquals(vMsg121, vMsg122);
		List <WebElement> address = driver.findElements(By.name("data[company_details][address_type1][]"));
		int addres = address.size();       
		System.out.println(addres);
		address.get(2).click();
		Thread.sleep(200);
		
		//Phone Number
		String vMsg123 = driver.findElement(By.xpath(".//*[@id='data[company_details][phone_number][]-error']")).getText();
		String vMsg124 = "This field is required.";
		Assert.assertEquals(vMsg123, vMsg124);
		driver.findElement(By.name("data[company_details][phone_number][]")).clear();
		driver.findElement(By.name("data[company_details][phone_number][]")).sendKeys("8945561245");
		Thread.sleep(200);
		
		//Email Address
		String vMsg125 = driver.findElement(By.xpath(".//*[@id='data[company_details][email][]-error']")).getText();
		String vMsg126 = "This field is required.";
		Assert.assertEquals(vMsg125, vMsg126);
		driver.findElement(By.name("data[company_details][email][]")).clear();
		driver.findElement(By.name("data[company_details][email][]")).sendKeys("micro@nadsoft.com");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next7']")).click();
		Thread.sleep(2000);	
		
//Directorship details		
		//next button for validation check
		driver.findElement(By.xpath(".//*[@id='next8']")).click();
		Thread.sleep(3000);   
		
		//Number of Directors
		String vMsg127 = driver.findElement(By.xpath(".//*[@id='data[directorship_details][director_number][]-error']")).getText();
		String vMsg128 = "This field is required.";
		Assert.assertEquals(vMsg127, vMsg128);
		driver.findElement(By.name("data[directorship_details][director_number][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_number][]")).sendKeys("3");
		Thread.sleep(200);
		
		//Name of Director 1
		String vMsg129 = driver.findElement(By.xpath(".//*[@id='data[directorship_details][director_1_name][]-error']")).getText();
		String vMsg130 = "This field is required.";
		Assert.assertEquals(vMsg129, vMsg130);
		driver.findElement(By.name("data[directorship_details][director_1_name][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_1_name][]")).sendKeys("Pravin Jagdale");
		Thread.sleep(200);
		
		//Date Appointed
		String vMsg131 = driver.findElement(By.xpath(".//*[@id='data[directorship_details][director_1_doa][]-error']")).getText();
		String vMsg132 = "This field is required.";
		Assert.assertEquals(vMsg131, vMsg132);
		WebElement appointmentD1 = driver.findElement(By.name("data[directorship_details][director_1_doa][]"));
		appointmentD1.clear();
		appointmentD1.sendKeys("01/05/1995");
		Thread.sleep(200);
		appointmentD1.sendKeys(Keys.TAB);
		
		//Date of Birth
		String vMsg133 = driver.findElement(By.xpath(".//*[@id='data[directorship_details][director_1_dob][]-error']")).getText();
		String vMsg134 = "This field is required.";
		Assert.assertEquals(vMsg133, vMsg134);
		WebElement birthD1 = driver.findElement(By.name("data[directorship_details][director_1_dob][]"));
		birthD1.clear();
		birthD1.sendKeys("01/05/1980");
		Thread.sleep(200);
		birthD1.sendKeys(Keys.TAB);
		
		//Name of Director 2
		driver.findElement(By.name("data[directorship_details][director_2_name][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_2_name][]")).sendKeys("Mohsin Jamadar");
		Thread.sleep(200);
				
		//Date Appointed
		WebElement appointmentD2 = driver.findElement(By.name("data[directorship_details][director_2_doa][]"));
		appointmentD2.clear();
		appointmentD2.sendKeys("01/08/2001");
		Thread.sleep(200);
		appointmentD2.sendKeys(Keys.TAB);
				
		//Date of Birth
		WebElement birthD2 = driver.findElement(By.name("data[directorship_details][director_2_dob][]"));
		birthD2.clear();
		birthD2.sendKeys("01/05/1990");
		Thread.sleep(200);
		birthD2.sendKeys(Keys.TAB);
		
		//Name of Director 3
		driver.findElement(By.name("data[directorship_details][director_3_name][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_3_name][]")).sendKeys("Rakesh Deshpande");
		Thread.sleep(200);
						
		//Date Appointed
		WebElement appointmentD3 = driver.findElement(By.name("data[directorship_details][director_3_doa][]"));
		appointmentD3.clear();
		appointmentD3.sendKeys("04/07/2009");
		Thread.sleep(200);
		appointmentD3.sendKeys(Keys.TAB);
						
		//Date of Birth
		WebElement birthD3 = driver.findElement(By.name("data[directorship_details][director_3_dob][]"));
		birthD3.clear();
		birthD3.sendKeys("01/05/1993");
		Thread.sleep(200);
		birthD3.sendKeys(Keys.TAB);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next8']")).click();
		Thread.sleep(2000);	
		
//Financial details		
		//Assets and Libialities - Home
		driver.findElement(By.name("data[assets_libility][home][]")).clear();
		driver.findElement(By.name("data[assets_libility][home][]")).sendKeys("600");
		Thread.sleep(200);          
		
		//Investment Property
		driver.findElement(By.name("data[assets_libility][investment_property][]")).clear();
		driver.findElement(By.name("data[assets_libility][investment_property][]")).sendKeys("200");
		Thread.sleep(200);
		
		//Cash Back
		driver.findElement(By.name("data[assets_libility][cash_back][]")).clear();
		driver.findElement(By.name("data[assets_libility][cash_back][]")).sendKeys("1100");
		Thread.sleep(200);
		
		//Vehicles
		driver.findElement(By.name("data[assets_libility][vehicles][]")).clear();
		driver.findElement(By.name("data[assets_libility][vehicles][]")).sendKeys("120");
		Thread.sleep(200);
		
		//Household Effects
		driver.findElement(By.name("data[assets_libility][household_effect][]")).clear();
		driver.findElement(By.name("data[assets_libility][household_effect][]")).sendKeys("300");
		Thread.sleep(200);
		
		//Shares
		driver.findElement(By.name("data[assets_libility][shares][]")).clear();
		driver.findElement(By.name("data[assets_libility][shares][]")).sendKeys("200");
		Thread.sleep(200);
		
		//Mortgage/Rent
		driver.findElement(By.name("data[assets_libility][mortgage_rent][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][mortgage_rent][monthly_payment][]")).sendKeys("110");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][mortgage_rent][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][mortgage_rent][balance_owing][]")).sendKeys("210");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][mortgage_rent][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][mortgage_rent][bank_institute][]")).sendKeys("310");
		Thread.sleep(200);
		
		//Investment Mortgage
		driver.findElement(By.name("data[assets_libility][investment_mortgage][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][investment_mortgage][monthly_payment][]")).sendKeys("310");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][investment_mortgage][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][investment_mortgage][balance_owing][]")).sendKeys("210");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][investment_mortgage][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][investment_mortgage][bank_institute][]")).sendKeys("110");
		Thread.sleep(200);
		
		//Personal Loans
		driver.findElement(By.name("data[assets_libility][personal_loan][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][personal_loan][monthly_payment][]")).sendKeys("510");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][personal_loan][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][personal_loan][balance_owing][]")).sendKeys("610");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][personal_loan][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][personal_loan][bank_institute][]")).sendKeys("710");
		Thread.sleep(200);
		
		//General Living Expenses
		driver.findElement(By.name("data[assets_libility][general_living][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][general_living][monthly_payment][]")).sendKeys("110");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][general_living][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][general_living][balance_owing][]")).sendKeys("211");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][general_living][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][general_living][bank_institute][]")).sendKeys("111");
		Thread.sleep(200);
		
		//Child Support
		driver.findElement(By.name("data[assets_libility][child_support][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][child_support][monthly_payment][]")).sendKeys("111");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][child_support][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][child_support][balance_owing][]")).sendKeys("121");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][child_support][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][child_support][bank_institute][]")).sendKeys("151");
		Thread.sleep(200);
		
		//Car Loans
		driver.findElement(By.name("data[assets_libility][car_loans][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][car_loans][monthly_payment][]")).sendKeys("111");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][car_loans][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][car_loans][balance_owing][]")).sendKeys("411");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][car_loans][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][car_loans][bank_institute][]")).sendKeys("141");
		Thread.sleep(200);
		
		//Credit Cards
		driver.findElement(By.name("data[assets_libility][credit_card][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][credit_card][monthly_payment][]")).sendKeys("100");
		Thread.sleep(200);          
		
		//click on next button
		driver.findElement(By.xpath(".//*[@id='next4']")).click();
		Thread.sleep(1000);            
		
		String msg1 = driver.findElement(By.xpath(".//*[@id='credit_card_val_1-error']")).getText();
		String msg2 = "This field is required.";   
        Assert.assertEquals(msg1, msg2);
        Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][credit_card][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][credit_card][balance_owing][]")).sendKeys("111");
		Thread.sleep(200);          
		String msg3 = driver.findElement(By.xpath(".//*[@id='credit_card_val_2-error']")).getText();
		String msg4 = "This field is required.";   
        Assert.assertEquals(msg3, msg4);
        Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][credit_card][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][credit_card][bank_institute][]")).sendKeys("111");
		Thread.sleep(200);          
		
		//Other
		driver.findElement(By.name("data[assets_libility][other][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][monthly_payment][]")).sendKeys("101");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][balance_owing][]")).sendKeys("841");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][bank_institute][]")).sendKeys("151");
		Thread.sleep(200);
		
		//Other (please specify)
		driver.findElement(By.name("data[assets_libility][other][monthly_payment_1][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][monthly_payment_1][]")).sendKeys("101");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][balance_owing_1][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][balance_owing_1][]")).sendKeys("841");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][bank_institute_1][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][bank_institute_1][]")).sendKeys("151");
		Thread.sleep(200);
		
		//Other (please specify)
		driver.findElement(By.name("data[assets_libility][other][monthly_payment_2][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][monthly_payment_2][]")).sendKeys("101");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][balance_owing_2][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][balance_owing_2][]")).sendKeys("841");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][bank_institute_2][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][bank_institute_2][]")).sendKeys("151");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next4']")).click();
		Thread.sleep(2000);	
		
//Accountant Details		
		//next button for validation check
		driver.findElement(By.xpath(".//*[@id='next9']")).click();
		Thread.sleep(3000);   
		
		
		//Accountant
		String vMsg135 = driver.findElement(By.xpath(".//*[@id='data[accountant_details][accountant][]-error']")).getText();
		String vMsg136 = "This field is required.";
		Assert.assertEquals(vMsg135, vMsg136);
		driver.findElement(By.name("data[accountant_details][accountant][]")).clear();
		driver.findElement(By.name("data[accountant_details][accountant][]")).sendKeys("HDFC");
		Thread.sleep(200);
		
		//Contact Name
		String vMsg137 = driver.findElement(By.xpath(".//*[@id='data[accountant_details][contact_name][]-error']")).getText();
		String vMsg138 = "This field is required.";
		Assert.assertEquals(vMsg137, vMsg138);
		driver.findElement(By.name("data[accountant_details][contact_name][]")).clear();
		driver.findElement(By.name("data[accountant_details][contact_name][]")).sendKeys("Rehan Khan");
		Thread.sleep(200);
		
		//Phone Number
		String vMsg139 = driver.findElement(By.xpath(".//*[@id='data[accountant_details][phone_number][]-error']")).getText();
		String vMsg140 = "This field is required.";
		Assert.assertEquals(vMsg139, vMsg140);
		driver.findElement(By.name("data[accountant_details][phone_number][]")).clear();
		driver.findElement(By.name("data[accountant_details][phone_number][]")).sendKeys("9719734682");
		Thread.sleep(200);
		
		
		//next
		driver.findElement(By.xpath(".//*[@id='next9']")).click();
		Thread.sleep(2000);
			

//Personal Details -
		
		driver.findElement(By.xpath(".//*[@id='next1']")).click();
		Thread.sleep(2000);
		
		//capture the validation msg
		String vMsg1 = driver.findElement(By.xpath(".//*[@id='data[individual_application][borrow_type1][]-error']")).getText();
		String vMsg2 = "This field is required.";   
		Assert.assertEquals(vMsg1, vMsg2);
		//Borrower Type - 	Primary Buyer 
		List <WebElement> radios = driver.findElements(By.name("data[individual_application][borrow_type1][]"));
		int o = radios.size();       
		System.out.println(o);
		radios.get(0).click();
		Thread.sleep(200);
		
		//First name
		//capture the validation msg
		String vMsg3 = driver.findElement(By.xpath(".//*[@id='data[personal_details][fname][]-error']")).getText();
		String vMsg4 = "This field is required.";
		Assert.assertEquals(vMsg3, vMsg4);
		driver.findElement(By.name("data[personal_details][fname][]")).clear();
		driver.findElement(By.name("data[personal_details][fname][]")).sendKeys("Test");
		Thread.sleep(200);

		
		//Middle name
		//capture the validation msg
		/*String vMsg5 = driver.findElement(By.xpath(".//*[@id='data[personal_details][mname][]-error']")).getText();
		String vMsg6 = "This field is required.";
		Assert.assertEquals(vMsg5, vMsg6);*/
		driver.findElement(By.name("data[personal_details][mname][]")).clear();
		driver.findElement(By.name("data[personal_details][mname][]")).sendKeys("Demo");
		Thread.sleep(200);
		
		
		//Surname
		//capture the validation msg
		String vMsg7 = driver.findElement(By.xpath(".//*[@id='data[personal_details][surname][]-error']")).getText();
		String vMsg8 = "This field is required.";
		Assert.assertEquals(vMsg7, vMsg8);
		driver.findElement(By.name("data[personal_details][surname][]")).clear();
		driver.findElement(By.name("data[personal_details][surname][]")).sendKeys("CompanyLoan");
		Thread.sleep(200);
		
		
		//Gender
		//capture the validation msg
		String vMsg9 = driver.findElement(By.xpath(".//*[@id='data[personal_details][gender1][]-error']")).getText();
		String vMsg10 = "This field is required.";
		Assert.assertEquals(vMsg9, vMsg10);
		List <WebElement> genders = driver.findElements(By.name("data[personal_details][gender1][]"));
		int gender = genders.size();       
		System.out.println(gender);
		genders.get(0).click();
		Thread.sleep(200);
	
		
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
		
		
		//Marital Status
		//capture the validation msg
		String vMsg13 = driver.findElement(By.xpath(".//*[@id='data[personal_details][marital_status][]-error']")).getText();
		String vMsg14 = "This field is required.";
		Assert.assertEquals(vMsg13, vMsg14);
		WebElement gender1 = driver.findElement(By.name("data[personal_details][marital_status][]"));
		Select g = new Select(gender1);
		g.selectByVisibleText("Single");
		Thread.sleep(500);
		
		
		//Drivers License No
		//capture the validation msg
		String vMsg15 = driver.findElement(By.xpath(".//*[@id='data[personal_details][driver_license_no][]-error']")).getText();
		String vMsg16 = "This field is required.";
		Assert.assertEquals(vMsg15, vMsg16);
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).clear();
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).sendKeys("8451245");
		Thread.sleep(200);
		
		
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

		
		//No.of Departments
		//capture the validation msg
		String vMsg19 = driver.findElement(By.xpath(".//*[@id='no_of_dependent-error']")).getText();
		String vMsg20 = "This field is required.";
		Assert.assertEquals(vMsg19, vMsg20);
		driver.findElement(By.name("data[personal_details][no_of_department][]")).clear();
		driver.findElement(By.name("data[personal_details][no_of_department][]")).sendKeys("2");
		Thread.sleep(200);

		
		//Age of Department
		driver.findElement(By.name("data[personal_details][age_of_depart][]")).clear();
		driver.findElement(By.name("data[personal_details][age_of_depart][]")).sendKeys("1");
		Thread.sleep(200);
		
		
		//Residency Status
		//capture the validation msg
		String vMsg21 = driver.findElement(By.xpath(".//*[@id='data[personal_details][residential_status1][]-error']")).getText();
		String vMsg22 = "This field is required.";
		Assert.assertEquals(vMsg21, vMsg22);
		List <WebElement> residency = driver.findElements(By.name("data[personal_details][residential_status1][]"));
		int ststus = residency.size();       
		System.out.println(ststus);
		residency.get(1).click();
		Thread.sleep(200);
		
		
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
		//capture the validation msg
		/*String vMsg27 = driver.findElement(By.xpath(".//*[@id='data[personal_details][home_phone][]-error']")).getText();
		String vMsg28 = "This field is required.";
		Assert.assertEquals(vMsg27, vMsg28);*/
		driver.findElement(By.name("data[personal_details][home_phone][]")).clear();
		driver.findElement(By.name("data[personal_details][home_phone][]")).sendKeys("02356784512");
		Thread.sleep(200);
	
		
		//Direct Debit Bank account Details  - Account Name
		//capture the validation msg
		/*String vMsg29 = driver.findElement(By.xpath(".//*[@id='data[personal_details][account_name][]-error']")).getText();
		String vMsg30 = "This field is required.";
		Assert.assertEquals(vMsg29, vMsg30);*/
		driver.findElement(By.name("data[personal_details][account_name][]")).clear();
		driver.findElement(By.name("data[personal_details][account_name][]")).sendKeys("Yes bank");
		Thread.sleep(200);

		
		//BSB
		//capture the validation msg
		/*String vMsg31 = driver.findElement(By.xpath(".//*[@id='data[personal_details][bsb][]-error']")).getText();
		String vMsg32 = "This field is required.";
		Assert.assertEquals(vMsg31, vMsg32);*/
		driver.findElement(By.name("data[personal_details][bsb][]")).clear();
		driver.findElement(By.name("data[personal_details][bsb][]")).sendKeys("78452");
		Thread.sleep(200);
	
		
		//AccountNo
		//capture the validation msg
		/*String vMsg33 = driver.findElement(By.xpath(".//*[@id='data[personal_details][account_no][]-error']")).getText();
		String vMsg34 = "This field is required.";
		Assert.assertEquals(vMsg33, vMsg34);*/
		driver.findElement(By.name("data[personal_details][account_no][]")).clear();
		driver.findElement(By.name("data[personal_details][account_no][]")).sendKeys("89451245");
		Thread.sleep(200);
		
		//References One - First name
		String vMsg87 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_1][]-error']")).getText();
		String vMsg88 = "This field is required.";
		Assert.assertEquals(vMsg87, vMsg88);
		driver.findElement(By.name("data[personal_reference][fname_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][fname_1][]")).sendKeys("Salman");
		Thread.sleep(200);
		
		//Surname
		String vMsg89 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][surname_1][]-error']")).getText();
		String vMsg90 = "This field is required.";
		Assert.assertEquals(vMsg89, vMsg90);
		driver.findElement(By.name("data[personal_reference][surname_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][surname_1][]")).sendKeys("Khan");
		Thread.sleep(200);
		
		//CurrentAddress
		/*String vMsg91 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][current_address_1][]-error']")).getText();
		String vMsg92 = "This field is required.";
		Assert.assertEquals(vMsg91, vMsg92);*/
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).sendKeys("Pune");
		Thread.sleep(200);
		
		//Phone No
		String vMsg93 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_1][]-error']")).getText();
		String vMsg94 = "This field is required.";
		Assert.assertEquals(vMsg93, vMsg94);
		driver.findElement(By.name("data[personal_reference][phone_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][phone_1][]")).sendKeys("9856451245");
		Thread.sleep(200);
		
		//Relationship
		String vMsg95 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][relationship_1][]-error']")).getText();
		String vMsg96 = "This field is required.";
		Assert.assertEquals(vMsg95, vMsg96);
		driver.findElement(By.name("data[personal_reference][relationship_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][relationship_1][]")).sendKeys("Friend");
		Thread.sleep(200);
		
		//References Two - First name
		String vMsg97 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_2][]-error']")).getText();
		String vMsg98 = "This field is required.";
		Assert.assertEquals(vMsg97, vMsg98);
		driver.findElement(By.name("data[personal_reference][fname_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][fname_2][]")).sendKeys("Amir");
		Thread.sleep(200);
				
		//Surname
		String vMsg99 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][surname_2][]-error']")).getText();
		String vMsg100 = "This field is required.";
		Assert.assertEquals(vMsg99, vMsg100);
		driver.findElement(By.name("data[personal_reference][surname_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][surname_2][]")).sendKeys("Khan");
		Thread.sleep(200);
				
		//CurrentAddress
		/*String vMsg101 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][current_address_2][]-error']")).getText();
		String vMsg102 = "This field is required.";
		Assert.assertEquals(vMsg101, vMsg102);*/
		driver.findElement(By.name("data[personal_reference][current_address_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][current_address_2][]")).sendKeys("Pune");
		Thread.sleep(200);
		
		//Phone No
		String vMsg103 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_2][]-error']")).getText();
		String vMsg104 = "This field is required.";
		Assert.assertEquals(vMsg103, vMsg104);
		driver.findElement(By.name("data[personal_reference][phone_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][phone_2][]")).sendKeys("9856451245");
		Thread.sleep(200);
				
		//Relationship
		String vMsg105 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][relationship_2][]-error']")).getText();
		String vMsg106 = "This field is required.";
		Assert.assertEquals(vMsg105, vMsg106);
		driver.findElement(By.name("data[personal_reference][relationship_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][relationship_2][]")).sendKeys("Friend");
		Thread.sleep(200);
		
		
		//next
		driver.findElement(By.xpath(".//*[@id='next1']")).click();
		Thread.sleep(2000);          
				
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,950)", "");
		Thread.sleep(3000);
		
		//next button for validation check
		driver.findElement(By.xpath(".//*[@id='next2']")).click();
		Thread.sleep(3000);          
		
		//scroll up
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-850)", "");
		Thread.sleep(5000);
		
		//Time At Address
		WebElement years1 = driver.findElement(By.name("data[address_details][time_at_address_yrs][]"));
    	Select year1 = new Select (years1);             
    	year1.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months1 = driver.findElement(By.name("data[address_details][time_at_address_months][]"));
    	Select month1 = new Select (months1);
    	month1.selectByVisibleText("4 Months");
    	Thread.sleep(1000);
		
		//Current Address Details - Current Address
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
    	Thread.sleep(2000);
		
		//Postal Code
    	//capture the validation msg
		String vMsg41 = driver.findElement(By.xpath(".//*[@id='data[address_details][postal_code][]-error']")).getText();
		String vMsg42 = "This field is required.";
		Assert.assertEquals(vMsg41, vMsg42);
		driver.findElement(By.name("data[address_details][postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][postal_code][]")).sendKeys("45121");
		Thread.sleep(200);
		
		//Current Resident Type
    	//capture the validation msg
		String vMsg43 = driver.findElement(By.xpath(".//*[@id='data[address_details][residential_type1][]-error']")).getText();
		String vMsg44 = "This field is required.";
		Assert.assertEquals(vMsg43, vMsg44);
		List <WebElement> cResidency = driver.findElements(By.name("data[address_details][residential_type1][]"));
		int type = cResidency.size();                                                         
		System.out.println(type);
		cResidency.get(0).click();
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
		
		//Previous Address Details - Previous Address
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
		    	Thread.sleep(2000);
		
		//Postal Code
		    	String vMsg51 = driver.findElement(By.xpath(".//*[@id='data[address_details][previous_postal_code][]-error']")).getText();
				String vMsg52 = "This field is required.";
				Assert.assertEquals(vMsg51, vMsg52);
		driver.findElement(By.name("data[address_details][previous_postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][previous_postal_code][]")).sendKeys("45254");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next2']")).click();
		Thread.sleep(2000);          
		
		/*//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,950)", "");
		Thread.sleep(3000);
		
		//next button for validation check
		driver.findElement(By.xpath(".//*[@id='next3']")).click();
		Thread.sleep(3000);          
		
		//scroll up
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-850)", "");
		Thread.sleep(5000);
		
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
		statusemp.selectByVisibleText("Full Time");
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
		
		//Net Income $
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
		List <WebElement> radios2 = driver.findElements(By.name("data[employment_details][period1][]"));
		int o2 = radios2.size();                                        
		System.out.println(o2);
		radios2.get(2).click();
		Thread.sleep(200);
		
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
    	
    	//Additional Monthly Income
    	//Additional Monthly Income
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
    	Thread.sleep(500);
    	
		
		//next
		driver.findElement(By.xpath(".//*[@id='next3']")).click();
		Thread.sleep(2000);   */       
		
		          
		
		/*//Vehicle Details - Vehicle For
		List <WebElement> vehicle1 = driver.findElements(By.name("data[personal_details][vehicle_for1][]"));
		int v1 = vehicle1.size();       
		System.out.println(v1);
		vehicle1.get(0).click();
		Thread.sleep(200);
		
		//Make
		driver.findElement(By.name("data[new_vehicle_detail][make][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][make][]")).sendKeys("Honda");
		Thread.sleep(200);
		
		//Model
		driver.findElement(By.name("data[new_vehicle_detail][model][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][model][]")).sendKeys("Accord");
		Thread.sleep(200);
		
		//Year
		WebElement years = driver.findElement(By.xpath(".//*[@id='financial']/div/div/div[1]/div/div[5]/div/select"));
    	Select year = new Select (years);               
    	year.selectByVisibleText("2011");
    	Thread.sleep(2000);
		
		//KMs
		driver.findElement(By.name("data[new_vehicle_detail][kms][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][kms][]")).sendKeys("300");
		Thread.sleep(200);
		
		//Accessories
		driver.findElement(By.name("data[new_vehicle_detail][accessories][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][accessories][]")).sendKeys("Yes");
		Thread.sleep(200);
		
		//Amount to Finance ($)
		driver.findElement(By.name("data[new_vehicle_detail][amount_to_finance][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][amount_to_finance][]")).sendKeys("80000");
		Thread.sleep(200);
		
		//Term
		WebElement months8 = driver.findElement(By.name("data[new_vehicle_detail][term][]"));
    	Select month8 = new Select (months8);
    	month8.selectByVisibleText("4");
    	Thread.sleep(1000);
		
		//Baloon
		driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).sendKeys("50");
		Thread.sleep(200);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(2000);*/
		
		//Vehicle Details   
	  	//Make
		/*String vMsg141 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][make][]-error']")).getText();
		String vMsg142 = "This field is required.";   
		Assert.assertEquals(vMsg141, vMsg142);
		Thread.sleep(2000);    */         
			driver.findElement(By.name("data[commercial_new_vehicle_detail][make][]")).clear();
			driver.findElement(By.name("data[commercial_new_vehicle_detail][make][]")).sendKeys("Yamaha");
			/*driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[4]/div/button")).click();
		    Thread.sleep(1000);*/
			
			//Model
			/*String vMsg143 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][model][]-error']")).getText();
			String vMsg144 = "This field is required.";
			Assert.assertEquals(vMsg143, vMsg144);*/
			driver.findElement(By.name("data[commercial_new_vehicle_detail][model][]")).clear();
			driver.findElement(By.name("data[commercial_new_vehicle_detail][model][]")).sendKeys("FZ");
			/*driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[4]/div/button")).click();
		    Thread.sleep(1000);*/
			
			//Year
			WebElement years13 = driver.findElement(By.name("data[commercial_new_vehicle_detail][year][]"));
	    	Select year13 = new Select (years13);
	    	year13.selectByVisibleText("2014");
	    	Thread.sleep(1000);
			
			//KMs
	    	/*String vMsg145 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][kms][]-error']")).getText();
			String vMsg146 = "This field is required.";
			Assert.assertEquals(vMsg145, vMsg146);*/
			driver.findElement(By.name("data[commercial_new_vehicle_detail][kms][]")).clear();
			driver.findElement(By.name("data[commercial_new_vehicle_detail][kms][]")).sendKeys("200");
			/*driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[4]/div/button")).click();
		    Thread.sleep(1000);*/
			
			//Accessories
			driver.findElement(By.name("data[commercial_new_vehicle_detail][accessories][]")).clear();
			driver.findElement(By.name("data[commercial_new_vehicle_detail][accessories][]")).sendKeys("Yes");
			Thread.sleep(200);
			
			//term
			WebElement years15 = driver.findElement(By.name("data[new_vehicle_detail][term][]"));
	    	Select year15 = new Select (years15);
	    	year15.selectByVisibleText("5");
	    	Thread.sleep(1000);
			
			
			//Amount to Finance
			/*String vMsg147 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][amount_finance][]-error']")).getText();
			String vMsg148 = "This field is required.";
			Assert.assertEquals(vMsg147, vMsg148);*/
			driver.findElement(By.name("data[commercial_new_vehicle_detail][amount_finance][]")).clear();
			driver.findElement(By.name("data[commercial_new_vehicle_detail][amount_finance][]")).sendKeys("50000");
			/*driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[4]/div/button")).click();
		    Thread.sleep(1000);*/
			
			//Dealership
			/*String vMsg149 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][dealership][]-error']")).getText();
			String vMsg150 = "This field is required.";
			Assert.assertEquals(vMsg149, vMsg150);*/
			driver.findElement(By.name("data[commercial_new_vehicle_detail][dealership][]")).clear();
			driver.findElement(By.name("data[commercial_new_vehicle_detail][dealership][]")).sendKeys("Yamaha shop");
			/*driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[4]/div/button")).click();
		    Thread.sleep(1000);*/
		    
		    //Balloon/Residual ($ or %)*
		    /*String vMsg200 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][baloon][]-error']")).getText();
			String vMsg201 = "This field is required.";
			Assert.assertEquals(vMsg200, vMsg201);*/
			driver.findElement(By.name("data[commercial_new_vehicle_detail][baloon][]")).clear();
			driver.findElement(By.name("data[commercial_new_vehicle_detail][baloon][]")).sendKeys("44");
			/*driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[4]/div/button")).click();
		    Thread.sleep(1000);*/
		    
			//Phone Number
			/*String vMsg151 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][phone_number][]-error']")).getText();
			String vMsg152 = "This field is required.";
			Assert.assertEquals(vMsg151, vMsg152);*/
			driver.findElement(By.name("data[commercial_new_vehicle_detail][phone_number][]")).clear();
			driver.findElement(By.name("data[commercial_new_vehicle_detail][phone_number][]")).sendKeys("7856154689");
			/*driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[4]/div/button")).click();
		    Thread.sleep(1000);*/    
			
		  //next
			driver.findElement(By.xpath(".//*[@id='next10']")).click();
			Thread.sleep(2000);
			
			//scroll down
			jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,950)", "");
			Thread.sleep(3000);
			
			//next button for validation check
			driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[4]/div/button")).click();
			Thread.sleep(3000);            
			
			//scroll up
			jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-850)", "");
			Thread.sleep(5000);
		    
			/*Robot rs = new Robot();
			rs.keyPress(KeyEvent.VK_TAB);
			rs.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);*/
			
			//Payslip 1
			String vMsg155 = driver.findElement(By.xpath(".//*[@id='docDriverLic1-error']")).getText();
			String vMsg156 = "This field is required.";      
			Assert.assertEquals(vMsg155, vMsg156);
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[2]/div/div/div/span/label/span")).click();
	    	driver.switchTo()            
		            .activeElement()
		            .sendKeys(
		                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-1. paper.pdf");
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    Thread.sleep(5000);
		    Robot rr = new Robot();
		    rr.keyPress(KeyEvent.VK_ESCAPE);
		    rr.keyRelease(KeyEvent.VK_ESCAPE);
		    /*driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[3]/div/button")).click();
			Thread.sleep(2000);*/
		    
		  //Payslip 2
		    driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[3]/div/div/div/span/label/span")).click();
	      	driver.switchTo()
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-2. paper-2.pdf");
	  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	    Thread.sleep(5000);
	  	    rr.keyPress(KeyEvent.VK_ESCAPE);
	  	    rr.keyRelease(KeyEvent.VK_ESCAPE);
	  	    Thread.sleep(2000); 
		  	    
		  //Copy of Driver's Licenses*	
	  	  driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[4]/div/div/div/span/label/span")).click();
	      	driver.switchTo()
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-3.pdf");
	  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	    Thread.sleep(5000);
	  	    rr.keyPress(KeyEvent.VK_ESCAPE);
	  	    rr.keyRelease(KeyEvent.VK_ESCAPE);
	  	    Thread.sleep(2000); 
		  	    
		  //Rates Notices for Home Owner (If applicable)	
	  	  driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[5]/div/div/div/span/label/span")).click();
	      	driver.switchTo()
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-4.pdf");
	  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	    Thread.sleep(5000);
	  	    rr.keyPress(KeyEvent.VK_ESCAPE);
	  	    rr.keyRelease(KeyEvent.VK_ESCAPE);
	  	    Thread.sleep(2000); 
		  	    
		  /*//Latest tax return or notice of assessment	
		  	  WebElement upload5 = driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[9]/div/div/div/div/div[2]/div[6]/div/div/div/span/label/span"));
		    	Actions action5 = new Actions(driver);
		    	action5.moveToElement(upload5).perform();
		    	Thread.sleep(2000);
		  	  driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[9]/div/div/div/div/div[2]/div[6]/div/div/div/span/label/span")).click();
		      	driver.switchTo()
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/DriveOnData/RatesNoticesForHomeOwner.PDF");
		  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  	    Thread.sleep(5000);
		  	    rr.keyPress(KeyEvent.VK_ESCAPE);
		  	    rr.keyRelease(KeyEvent.VK_ESCAPE);
		  	    Thread.sleep(2000);
		  	    
		  //Copy of Drivers Licenses*	    
		  	  driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[9]/div/div/div/div/div[2]/div[7]/div/div/div/span/label/span")).click();
		      	driver.switchTo()
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/DriveOnData/RatesNoticesForHomeOwner.PDF");
		  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  	    Thread.sleep(5000);
		  	    rr.keyPress(KeyEvent.VK_ESCAPE);
		  	    rr.keyRelease(KeyEvent.VK_ESCAPE);
		  	    Thread.sleep(2000);
		  	    
		  //Rates Notices for Home Owners (if applicable)	    
		  	  driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[9]/div/div/div/div/div[2]/div[8]/div/div/div/span/label/span")).click();
		      	driver.switchTo()
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/DriveOnData/RatesNoticesForHomeOwner.PDF");
		  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  	    Thread.sleep(5000);
		  	    rr.keyPress(KeyEvent.VK_ESCAPE);
		  	    rr.keyRelease(KeyEvent.VK_ESCAPE);
		  	    Thread.sleep(2000); */
			
		/*driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[4]/div/button")).click();
		Thread.sleep(2000);*/
		
	}
 	
 	//Application--------------------------------------------------------
 	
 	public void addPersonalLoanApplication() throws InterruptedException, AWTException{
 		
 		System.out.println("Add personal loan form");
 		
 		//click dashboard
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
 		Thread.sleep(2000);          
 		
 		//click applications
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/a")).click();
 		Thread.sleep(2000);          
 		
 		//click on view all 
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/ul/li[1]/a")).click();
 		Thread.sleep(4000);                
 		
 		//Add new button
 		driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div/div/button")).click();
 		Thread.sleep(3000);              
 		
 		//Personal loan application 
 		driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div/div/ul/li[1]/a")).click();
 		Thread.sleep(4000);              
 		
 		//scroll down
 				jse = (JavascriptExecutor) driver;
 				jse.executeScript("window.scrollBy(0,950)", "");
 				Thread.sleep(3000);
 				
 				//next button for validation check
 				driver.findElement(By.xpath(".//*[@id='next1']")).click();
 				Thread.sleep(3000);   
 				
 				//scroll up
 				jse = (JavascriptExecutor) driver;
 				jse.executeScript("window.scrollBy(0,-850)", "");
 				Thread.sleep(5000);
 				
 				//Personal Details -
 				
 				//Phone No
					/*String vMsg93 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_1][]-error']")).getText();
					String vMsg94 = "This field is required.";
					Assert.assertEquals(vMsg93, vMsg94);
					Thread.sleep(1000);*/
					//driver.findElement(By.name("data[personal_reference][phone_1][]")).sendKeys("7777777777");
					//driver.findElement(By.name("data[personal_reference][phone_1][]")).sendKeys("9856451245");
 				
 						//capture the validation msg
 						String vMsg1 = driver.findElement(By.xpath(".//*[@id='data[individual_application][borrow_type1][]-error']")).getText();
 						String vMsg2 = "This field is required.";    
 						Assert.assertEquals(vMsg1, vMsg2);
 						//Borrower Type - 	Primary Buyer 
 						List <WebElement> radios = driver.findElements(By.name("data[individual_application][borrow_type1][]"));
 						int o = radios.size();                                                                      
 						System.out.println(o);
 						radios.get(0).click();
 						Thread.sleep(200);
 						
 						//Select tittle
 						String vMsg13a = driver.findElement(By.xpath(".//*[@id='data[personal_details][title][]-error']")).getText();
 						String vMsg14a = "This field is required.";
 						Assert.assertEquals(vMsg13a, vMsg14a);
 						WebElement gender1a = driver.findElement(By.name("data[personal_details][title][]"));
 						Select ga = new Select(gender1a);
 						ga.selectByVisibleText("Mr");
 						Thread.sleep(500);
 						
 						
 						//First name
 						//capture the validation msg
 						String vMsg3 = driver.findElement(By.xpath(".//*[@id='data[personal_details][fname][]-error']")).getText();
 						String vMsg4 = "This field is required.";
 						Assert.assertEquals(vMsg3, vMsg4);
 						driver.findElement(By.name("data[personal_details][fname][]")).clear();
 						driver.findElement(By.name("data[personal_details][fname][]")).sendKeys("Test");
 						Thread.sleep(200);

 						
 						//Middle name
 						//capture the validation msg
 						/*String vMsg5 = driver.findElement(By.xpath(".//*[@id='data[personal_details][mname][]-error']")).getText();
 						String vMsg6 = "This field is required.";
 						Assert.assertEquals(vMsg5, vMsg6);*/
 						driver.findElement(By.name("data[personal_details][mname][]")).clear();
 						driver.findElement(By.name("data[personal_details][mname][]")).sendKeys("Demo");
 						Thread.sleep(200);
 						
 						
 						//Surname
 						//capture the validation msg
 						String vMsg7 = driver.findElement(By.xpath(".//*[@id='data[personal_details][surname][]-error']")).getText();
 						String vMsg8 = "This field is required.";
 						Assert.assertEquals(vMsg7, vMsg8);
 						driver.findElement(By.name("data[personal_details][surname][]")).clear();
 						driver.findElement(By.name("data[personal_details][surname][]")).sendKeys("ApplicationsPersonalLoan");
 						Thread.sleep(200);
 						
 						
 						//Gender
 						//capture the validation msg
 						String vMsg9 = driver.findElement(By.xpath(".//*[@id='data[personal_details][gender1][]-error']")).getText();
 						String vMsg10 = "This field is required.";
 						Assert.assertEquals(vMsg9, vMsg10);
 						List <WebElement> genders = driver.findElements(By.name("data[personal_details][gender1][]"));
 						int gender = genders.size();       
 						System.out.println(gender);
 						genders.get(0).click();
 						Thread.sleep(200);
 					
 						
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
 						
 						
 						//Marital Status
 						//capture the validation msg
 						String vMsg13 = driver.findElement(By.xpath(".//*[@id='data[personal_details][marital_status][]-error']")).getText();
 						String vMsg14 = "This field is required.";
 						Assert.assertEquals(vMsg13, vMsg14);
 						WebElement gender1 = driver.findElement(By.name("data[personal_details][marital_status][]"));
 						Select g = new Select(gender1);
 						g.selectByVisibleText("Single");
 						Thread.sleep(500);
 						
 						//Licence Type
 						String vMsg13ba = driver.findElement(By.xpath(".//*[@id='data[personal_details][licence_type][]-error']")).getText();
 						String vMsg14ba = "This field is required.";
 						Assert.assertEquals(vMsg13ba, vMsg14ba);
 						WebElement gender1ba = driver.findElement(By.name("data[personal_details][licence_type][]"));
 						Select gab = new Select(gender1ba);
 						gab.selectByVisibleText("Provisional");
 						Thread.sleep(500);
 						
 						//Licence State
 						String vMsg13bc = driver.findElement(By.xpath(".//*[@id='data[personal_details][licence_state][]-error']")).getText();
 						String vMsg14bc = "This field is required.";
 						Assert.assertEquals(vMsg13bc, vMsg14bc);
 						WebElement gender1bc = driver.findElement(By.name("data[personal_details][licence_state][]"));
 						Select gac = new Select(gender1bc);
 						gac.selectByVisibleText("NSW");
 						Thread.sleep(500);
 						
 						
 						//Drivers License No
 						//capture the validation msg
 						String vMsg15 = driver.findElement(By.xpath(".//*[@id='data[personal_details][driver_license_no][]-error']")).getText();
 						String vMsg16 = "This field is required.";
 						Assert.assertEquals(vMsg15, vMsg16);
 						driver.findElement(By.name("data[personal_details][driver_license_no][]")).clear();
 						driver.findElement(By.name("data[personal_details][driver_license_no][]")).sendKeys("8451245");
 						Thread.sleep(200);
 						
 						
 						//Expiry 
 						//capture the validation msg
 						/*String vMsg17 = driver.findElement(By.xpath(".//*[@id='data[personal_details][expiry][]-error']")).getText();
 						String vMsg18 = "This field is required.";
 						Assert.assertEquals(vMsg17, vMsg18);*/
 						WebElement expiry = driver.findElement(By.name("data[personal_details][expiry][]"));
 						expiry.clear();                                 
 						expiry.sendKeys("05/05/2020");
 						Thread.sleep(200);
 						expiry.sendKeys(Keys.TAB);
 						
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
 						//capture the validation msg
 						/*String vMsg27 = driver.findElement(By.xpath(".//*[@id='data[personal_details][home_phone][]-error']")).getText();
 						String vMsg28 = "This field is required.";
 						Assert.assertEquals(vMsg27, vMsg28);*/
 						driver.findElement(By.name("data[personal_details][home_phone][]")).clear();
 						driver.findElement(By.name("data[personal_details][home_phone][]")).sendKeys("02356784512");
 						Thread.sleep(200);
 						
 						
 						/*//No.of Departments
 						//capture the validation msg
 						String vMsg19 = driver.findElement(By.xpath(".//*[@id='no_of_dependent-error']")).getText();
 						String vMsg20 = "This field is required.";
 						Assert.assertEquals(vMsg19, vMsg20);
 						driver.findElement(By.name("data[personal_details][no_of_department][]")).clear();
 						driver.findElement(By.name("data[personal_details][no_of_department][]")).sendKeys("2");
 						Thread.sleep(200);*/          
					
 						
 						//Residency Status
 						//capture the validation msg
 						String vMsg21 = driver.findElement(By.xpath(".//*[@id='data[personal_details][residential_status1][]-error']")).getText();
 						String vMsg22 = "This field is required.";   
 						Assert.assertEquals(vMsg21, vMsg22);
 						List <WebElement> residency = driver.findElements(By.name("data[personal_details][residential_status1][]"));
 						int ststus = residency.size();                                
 						System.out.println(ststus);
 						residency.get(1).click();
 						Thread.sleep(200);
 						
 						
 						//Creadit history
 						String vMsg211 = driver.findElement(By.xpath(".//*[@id='data[personal_details][credit_history][]-error']")).getText();
 						String vMsg221 = "This field is required.";    
 						Assert.assertEquals(vMsg211, vMsg221);
 						Select drops = new Select(driver.findElement(By.name("data[personal_details][credit_history][]"))); 
 						drops.selectByVisibleText("Good History");
 						Thread.sleep(200);
 						
 						/*//Direct Debit Bank account Details  - Account Name
 						//capture the validation msg
 						String vMsg29 = driver.findElement(By.xpath(".//*[@id='data[personal_details][account_name][]-error']")).getText();
 						String vMsg30 = "This field is required.";
 						Assert.assertEquals(vMsg29, vMsg30);
 						driver.findElement(By.name("data[personal_details][account_name][]")).clear();
 						driver.findElement(By.name("data[personal_details][account_name][]")).sendKeys("Yes bank");
 						Thread.sleep(200);

 						
 						//BSB
 						//capture the validation msg
 						String vMsg31 = driver.findElement(By.xpath(".//*[@id='data[personal_details][bsb][]-error']")).getText();
 						String vMsg32 = "This field is required.";
 						Assert.assertEquals(vMsg31, vMsg32);
 						driver.findElement(By.name("data[personal_details][bsb][]")).clear();
 						driver.findElement(By.name("data[personal_details][bsb][]")).sendKeys("78452");
 						Thread.sleep(200);
 					
 						
 						//AccountNo
 						//capture the validation msg
 						String vMsg33 = driver.findElement(By.xpath(".//*[@id='data[personal_details][account_no][]-error']")).getText();
 						String vMsg34 = "This field is required.";   
 						Assert.assertEquals(vMsg33, vMsg34);
 						driver.findElement(By.name("data[personal_details][account_no][]")).clear();
 						driver.findElement(By.name("data[personal_details][account_no][]")).sendKeys("89451245");
 						Thread.sleep(200);*/
 						
 						/*//References One - First name
 						String vMsg87 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_1][]-error']")).getText();
 						String vMsg88 = "This field is required.";   
 						Assert.assertEquals(vMsg87, vMsg88);
 						driver.findElement(By.name("data[personal_reference][fname_1][]")).clear();
 						driver.findElement(By.name("data[personal_reference][fname_1][]")).sendKeys("Salman");
 						
 						//Surname
 						String vMsg89 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][surname_1][]-error']")).getText();
 						String vMsg90 = "This field is required.";
 						Assert.assertEquals(vMsg89, vMsg90);
 						Thread.sleep(1000);
 						driver.findElement(By.name("data[personal_reference][surname_1][]")).clear();
 						driver.findElement(By.name("data[personal_reference][surname_1][]")).sendKeys("Khan");
 						Thread.sleep(200); 
 						
 						//CurrentAddress
 						String vMsg91 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][current_address_1][]-error']")).getText();
 						String vMsg92 = "This field is required.";
 						Assert.assertEquals(vMsg91, vMsg92);
 						Thread.sleep(2000);
 						driver.findElement(By.name("data[personal_reference][current_address_1][]")).clear();
 						driver.findElement(By.name("data[personal_reference][current_address_1][]")).sendKeys("Pune");
 						Thread.sleep(200); 
 	
 						
 						//Relationship
 						String vMsg1051 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][relationship_1][]-error']")).getText();
 						String vMsg1061 = "This field is required.";
 						Assert.assertEquals(vMsg1051, vMsg1061);
 						Thread.sleep(2000); 
 						driver.findElement(By.name("data[personal_reference][relationship_1][]")).clear();
 						driver.findElement(By.name("data[personal_reference][relationship_1][]")).sendKeys("Friend");
 						Thread.sleep(200);
 						
 						
 						
 					
                        //References Two - First name
 						String vMsg97 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_2][]-error']")).getText();
 						String vMsg98 = "This field is required.";
 						Assert.assertEquals(vMsg97, vMsg98);
 						Thread.sleep(2000); 
 						driver.findElement(By.name("data[personal_reference][fname_2][]")).clear();
 						driver.findElement(By.name("data[personal_reference][fname_2][]")).sendKeys("Amir");
 						Thread.sleep(200);
 						driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
 						Thread.sleep(2000); 
 						
 						//Surname
 						String vMsg99 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][surname_2][]-error']")).getText();
 						String vMsg100 = "This field is required.";
 						Assert.assertEquals(vMsg99, vMsg100);
 						Thread.sleep(2000); 
 						driver.findElement(By.name("data[personal_reference][surname_2][]")).clear();
 						driver.findElement(By.name("data[personal_reference][surname_2][]")).sendKeys("Khan");
 						Thread.sleep(200);
 						driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
 						Thread.sleep(2000); 
 								
 						//CurrentAddress
 						String vMsg101 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][current_address_2][]-error']")).getText();
 						String vMsg102 = "This field is required.";
 						Assert.assertEquals(vMsg101, vMsg102);
 						Thread.sleep(2000); 
 						driver.findElement(By.name("data[personal_reference][current_address_2][]")).clear();
 						driver.findElement(By.name("data[personal_reference][current_address_2][]")).sendKeys("Pune");
 						Thread.sleep(200);
 						driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
 						Thread.sleep(2000); 
 						
 						//Phone No
 						String vMsg103 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_2][]-error']")).getText();
 						String vMsg104 = "This field is required.";
 						Assert.assertEquals(vMsg103, vMsg104);
 						Thread.sleep(2000); 
 						driver.findElement(By.name("data[personal_reference][phone_2][]")).clear();
 						driver.findElement(By.name("data[personal_reference][phone_2][]")).sendKeys("9856451245");
 						Thread.sleep(200);
 						driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
 						Thread.sleep(2000); 		
 						
 						//Relationship
 						String vMsg105 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][relationship_2][]-error']")).getText();
 						String vMsg106 = "This field is required.";
 						Assert.assertEquals(vMsg105, vMsg106);
 						Thread.sleep(2000); 
 						driver.findElement(By.name("data[personal_reference][relationship_2][]")).clear();
 						driver.findElement(By.name("data[personal_reference][relationship_2][]")).sendKeys("Friend");
 						Thread.sleep(200);
 						driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
 						Thread.sleep(2000);			*/
 						
 						//next
 						driver.findElement(By.xpath(".//*[@id='next1']")).click();
 						Thread.sleep(2000);          
 						
 						//scroll down
 						jse = (JavascriptExecutor) driver;
 						jse.executeScript("window.scrollBy(0,950)", "");
 						Thread.sleep(3000);
 						
 						//next button for validation check
 						driver.findElement(By.xpath(".//*[@id='next2']")).click();
 						Thread.sleep(3000);   
 						
 						//scroll up
 						jse = (JavascriptExecutor) driver;
 						jse.executeScript("window.scrollBy(0,-850)", "");
 						Thread.sleep(5000);
 						
 						//Current Address Details - Current Address
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
 				    	Thread.sleep(2000);
 						
 						//Postal Code
 				    	//capture the validation msg
 						String vMsg41 = driver.findElement(By.xpath(".//*[@id='data[address_details][postal_code][]-error']")).getText();
 						String vMsg42 = "This field is required.";
 						Assert.assertEquals(vMsg41, vMsg42);
 						driver.findElement(By.name("data[address_details][postal_code][]")).clear();
 						driver.findElement(By.name("data[address_details][postal_code][]")).sendKeys("45121");
 						Thread.sleep(200);
 						
 						//Time At Address
 						WebElement years1 = driver.findElement(By.name("data[address_details][time_at_address_yrs][]"));
 				    	Select year1 = new Select (years1);
 				    	year1.selectByVisibleText("1 Years");
 				    	Thread.sleep(1000);
 				    	
 				    	WebElement months1 = driver.findElement(By.name("data[address_details][time_at_address_months][]"));
 				    	Select month1 = new Select (months1);
 				    	month1.selectByVisibleText("4 Months");
 				    	Thread.sleep(1000);
 						
 						//Current Resident Type
 				    	//capture the validation msg
 						String vMsg43 = driver.findElement(By.xpath(".//*[@id='data[address_details][residential_type1][]-error']")).getText();
 						String vMsg44 = "This field is required.";
 						Assert.assertEquals(vMsg43, vMsg44);
 						List <WebElement> cResidency = driver.findElements(By.name("data[address_details][residential_type1][]"));
 						int type = cResidency.size();                                                         
 						System.out.println(type);
 						cResidency.get(0).click();
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
 						
 						//Previous Address Details - Previous Address
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
 						    	Thread.sleep(2000);
 						
 						//Postal Code
 						    	String vMsg51 = driver.findElement(By.xpath(".//*[@id='data[address_details][previous_postal_code][]-error']")).getText();
 								String vMsg52 = "This field is required.";
 								Assert.assertEquals(vMsg51, vMsg52);
 						driver.findElement(By.name("data[address_details][previous_postal_code][]")).clear();
 						driver.findElement(By.name("data[address_details][previous_postal_code][]")).sendKeys("45254");
 						Thread.sleep(200);
 						
 						//next
 						driver.findElement(By.xpath(".//*[@id='next2']")).click();
 						Thread.sleep(2000);
 						
 						//scroll down
 						jse = (JavascriptExecutor) driver;
 						jse.executeScript("window.scrollBy(0,950)", "");
 						Thread.sleep(3000);
 						
 						//next button for validation check
 						driver.findElement(By.xpath(".//*[@id='next3']")).click();
 						Thread.sleep(3000);   
 						
 						//scroll up
 						jse = (JavascriptExecutor) driver;
 						jse.executeScript("window.scrollBy(0,-850)", "");
 						Thread.sleep(5000);
 						
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
 						
 						/*
 						//Net Income $
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
 						List <WebElement> radios2 = driver.findElements(By.name("data[employment_details][period1][]"));
 						int o2 = radios2.size();                                        
 						System.out.println(o2);
 						radios2.get(2).click();
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
 				    	
 				    	//Income Details
 				    	//Net Income Verification
 				    	driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[3]/div/div/div/div/div[3]/div[1]/div/div/label[1]/input")).click();
 						
 						/*//next
 						driver.findElement(By.xpath(".//*[@id='next3']")).click();
 						Thread.sleep(2000);
 						Thread.sleep(500);*/
 						
 						//Monthly Government Benifits Income
 						/*String err1 = driver.findElement(By.xpath(".//*[@id='data[employment_details][government_benefits][]-error']")).getText();
 						String err2 = "This field is required.";
 						Assert.assertEquals(err1, err2);*/
 						driver.findElement(By.name("data[employment_details][government_benefits][]")).click();
 						Thread.sleep(200);
 						driver.findElement(By.name("data[employment_details][government_benefits][]")).sendKeys("100");
 						Thread.sleep(200);
 						
 						//Monthly Investment Income
 						/*String err3 = driver.findElement(By.xpath(".//*[@id='data[employment_details][investment_income][]-error']")).getText();
 						String err4 = "This field is required.";
 						Assert.assertEquals(err3, err4);*/
 						driver.findElement(By.name("data[employment_details][investment_income][]")).click();
 						Thread.sleep(200);
 						driver.findElement(By.name("data[employment_details][investment_income][]")).sendKeys("100");
 						Thread.sleep(200);
 						
 						//Monthly Net Income
 						/*String err5 = driver.findElement(By.xpath(".//*[@id='data[employment_details][monthly_net_income][]-error']")).getText();
 						String err6 = "This field is required.";
 						Assert.assertEquals(err5, err6);*/
 						driver.findElement(By.name("data[employment_details][monthly_net_income][]")).click();
 						Thread.sleep(200);
 						driver.findElement(By.name("data[employment_details][monthly_net_income][]")).sendKeys("100");
 						Thread.sleep(200);
 						
 						//Monthly Investment Property Income
 						/*String err7 = driver.findElement(By.xpath(".//*[@id='data[employment_details][investment_property][]-error']")).getText();
 						String err8 = "This field is required.";
 						Assert.assertEquals(err7, err8);*/
 						driver.findElement(By.name("data[employment_details][investment_property][]")).click();
 						Thread.sleep(200);
 						driver.findElement(By.name("data[employment_details][investment_property][]")).sendKeys("100");
 						Thread.sleep(200);
 						
 						//Monthly Other Income
 						/*String err9 = driver.findElement(By.xpath(".//*[@id='data[employment_details][other_income][]-error']")).getText();
 						String err10 = "This field is required.";
 						Assert.assertEquals(err9, err10);*/
 						driver.findElement(By.name("data[employment_details][other_income][]")).click();
 						Thread.sleep(200);
 						driver.findElement(By.name("data[employment_details][other_income][]")).sendKeys("100");
 						Thread.sleep(200);
 						
 						
 						//ABN number
 						driver.findElement(By.id("abn_number")).clear();
 						Thread.sleep(500);
 						driver.findElement(By.id("abn_number")).sendKeys("12345");
 						Thread.sleep(2000);       
 						
 						//next
 						driver.findElement(By.xpath(".//*[@id='next3']")).click();
 						Thread.sleep(2000);
 		
 						
 						//Assets
 						//yes option check and delete field
 						WebElement assects = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
 				    	Select homeProperty = new Select (assects);           
 				    	homeProperty.selectByVisibleText("Home Property");
 				    	Thread.sleep(1000);
 						//add button
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
 				    	Thread.sleep(1000);
 				    	//Yes select
 				    	WebElement assects2 = driver.findElement(By.name("data[assets_libility_assets][asset_home][own][]"));
 				    	Select ownedOutright = new Select (assects2);           
 				    	ownedOutright.selectByVisibleText("Yes");
 				    	Thread.sleep(1000);
 				    	//delete
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[2]/table/tbody/tr[2]/td[4]/a/i")).click();
 				    	Thread.sleep(1000);
 				    	//no option and delete from liabilties 
 				    	//add button
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
 				    	Thread.sleep(1000);
 				    	//no option
 				    	WebElement assects3 = driver.findElement(By.name("data[assets_libility_assets][asset_home][own][]"));
 				    	Select ownedOutrightNo = new Select (assects3);           
 				    	ownedOutrightNo.selectByVisibleText("No");
 				    	Thread.sleep(1000);
 				    	//del from liab
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[7]/a/i")).click();
 				    	Thread.sleep(1000);
 						
 				    	//add all assets and select Yes
 				    	//Home Property
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
 				    	Thread.sleep(1000);          
 				    	//delete
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[7]/a/i")).click();
 				    	Thread.sleep(1000);
 				    	
 				    	
 				    	//check the all liability property fields and his error messages
 				    	//Home Property Loan
 				    	//add
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
 				    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
 				    	Thread.sleep(1000);          
 				    	//Balance
 				    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][balance_owing][]")).sendKeys("100");
 				    	Thread.sleep(1000);
 						
 						//next
 						driver.findElement(By.xpath(".//*[@id='next4']")).click();
 						Thread.sleep(2000);
 						
 						
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
 						driver.findElement(By.xpath(".//*[@id='next4']")).click();
 						Thread.sleep(2000);
 						
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
 				    	//Other Expenses
 				    	//Monthly Rental/Board Payment 
 				    	String err12 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][monthly_rent_board][]-error']")).getText();
 				    	String err13 = "This field is required.";
 				    	Assert.assertEquals(err12, err13);
 				    	driver.findElement(By.name("data[assets_libility][monthly_rent_board][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[assets_libility][monthly_rent_board][]")).sendKeys("100");
 				    	Thread.sleep(500);
 				
 				    	//General Living Expenses 
 				    	String err14 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][general_living][]-error']")).getText();
 				    	String err15 = "This field is required.";
 				    	Assert.assertEquals(err14, err15);
 				    	driver.findElement(By.name("data[assets_libility][general_living][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[assets_libility][general_living][]")).sendKeys("100");
 				    	Thread.sleep(500);
 				    	
 				    	//Motor Vehicle Running Costs
 				    	String err16 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][vehicle_running_cost][]-error']")).getText();
 				    	String err17 = "This field is required.";
 				    	Assert.assertEquals(err16, err17);
 				    	driver.findElement(By.name("data[assets_libility][vehicle_running_cost][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[assets_libility][vehicle_running_cost][]")).sendKeys("100");
 				    	Thread.sleep(500);
 				    	
 				    	//Private Education & Childcare 
 				    	String err18 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][private_education_childcare][]-error']")).getText();
 				    	String err19 = "This field is required.";
 				    	Assert.assertEquals(err18, err19);
 				    	driver.findElement(By.name("data[assets_libility][private_education_childcare][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[assets_libility][private_education_childcare][]")).sendKeys("100");
 				    	Thread.sleep(500);
 				    	
 				    	//Other Expenses 
 				    	String err20 = driver.findElement(By.xpath(".//*[@id='data[assets_libility][other_expenses][]-error']")).getText();
 				    	String err21 = "This field is required.";
 				    	Assert.assertEquals(err20, err21);
 				    	driver.findElement(By.name("data[assets_libility][other_expenses][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[assets_libility][other_expenses][]")).sendKeys("100");
 				    	Thread.sleep(500);

 				    	//Foreseeable Changes
 				    	driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[4]/div/div/div/div/div[4]/div/div/label[1]/input")).click();
 				    	Thread.sleep(500);
 						
 				    	//next
 						driver.findElement(By.xpath(".//*[@id='next4']")).click();
 						Thread.sleep(2000);
 						
 						//Please provide an explanation on the foreseeable changes*
 				    	String er11w = driver.findElement(By.xpath(".//*[@id='data[assets_libility][foreseeable_details][]-error']")).getText();
 				    	String er12w = "This field is required.";
 				    	Assert.assertEquals(er11w, er12w);
 				    	driver.findElement(By.name("data[assets_libility][foreseeable_details][]")).sendKeys("TestExplation");
 				    	Thread.sleep(500);
 						
 				    	//next
 						driver.findElement(By.xpath(".//*[@id='next4']")).click();
 						Thread.sleep(2000);
 						
 						//Vehicle Details - Vehicle For
 						
 						//next
 						driver.findElement(By.xpath(".//*[@id='next5']")).click();
 						Thread.sleep(2000);
 						
 						//Make
 						String err22 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][make][]-error']")).getText();
 				    	String err23 = "This field is required.";
 				    	Assert.assertEquals(err22, err23);
 				    	driver.findElement(By.name("data[new_vehicle_detail][make][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[new_vehicle_detail][make][]")).sendKeys("Honda");
 				    	Thread.sleep(500);
 						
 						//Model
 				    	String err24 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][model][]-error']")).getText();
 				    	String err25 = "This field is required.";
 				    	Assert.assertEquals(err24, err25);
 				    	driver.findElement(By.name("data[new_vehicle_detail][model][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[new_vehicle_detail][model][]")).sendKeys("ModelTest");
 				    	Thread.sleep(500);
 						
 						//Build Date
 				    	WebElement web13 = driver.findElement(By.name("data[new_vehicle_detail][year][]"));
 				    	Select webb13 = new Select(web13);            
 				    	webb13.selectByVisibleText("2005");
 				    	Thread.sleep(1000);
 				    	
 				    	//KMs
 				    	String err26 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][kms][]-error']")).getText();
 				    	String err27 = "This field is required.";
 				    	Assert.assertEquals(err26, err27);
 				    	driver.findElement(By.name("data[new_vehicle_detail][kms][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[new_vehicle_detail][kms][]")).sendKeys("100");
 				    	Thread.sleep(500);
 				    	
 				    	//Amount to Finance
 				    	String err28 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][amount_to_finance][]-error']")).getText();
 				    	String err29 = "This field is required.";
 				    	Assert.assertEquals(err28, err29);
 				    	driver.findElement(By.name("data[new_vehicle_detail][amount_to_finance][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[new_vehicle_detail][amount_to_finance][]")).sendKeys("100");
 				    	Thread.sleep(500);
 				    	
 				    	//Term (Yrs)
 				    	WebElement web14 = driver.findElement(By.name("data[new_vehicle_detail][term][]"));
 				    	Select webb14 = new Select(web14);            
 				    	webb14.selectByVisibleText("2");
 				    	Thread.sleep(1000);
 				    	
 				    	//Balloon/Residual ($ or %)
 				    	String err30 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][baloon][]-error']")).getText();
 				    	String err31 = "This field is required.";
 				    	Assert.assertEquals(err30, err31);
 				    	driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).sendKeys("10");
 				    	Thread.sleep(500);
 				    	
 				    	//Dealership/Vendor
 				    	String err32 = driver.findElement(By.xpath(".//*[@id='data[new_vehicle_detail][dealership][]-error']")).getText();
 				    	String err33 = "This field is required.";
 				    	Assert.assertEquals(err32, err33);
 				    	driver.findElement(By.name("data[new_vehicle_detail][dealership][]")).clear();
 				    	Thread.sleep(500);
 				    	driver.findElement(By.name("data[new_vehicle_detail][dealership][]")).sendKeys("TestDel");
 				    	Thread.sleep(500);
 						
 						//next
 						driver.findElement(By.xpath(".//*[@id='next5']")).click();
 						Thread.sleep(3000);
 						
 						//scroll down
 						jse = (JavascriptExecutor) driver;
 						jse.executeScript("window.scrollBy(0,950)", "");
 						Thread.sleep(3000);
 						
 						//next button for validation check
 						driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button")).click();
 						Thread.sleep(3000);            
 						
 						//scroll up
 						jse = (JavascriptExecutor) driver;
 						jse.executeScript("window.scrollBy(0,-850)", "");
 						Thread.sleep(5000);
 						
 						//Payslip 1
 						String vMsg107 = driver.findElement(By.xpath(".//*[@id='docDriverLic-error']")).getText();
 						String vMsg108 = "This field is required.";   
 						Assert.assertEquals(vMsg107, vMsg108);
 						Thread.sleep(2000); 
 						driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[1]/div/div/div/span/label/span")).click();
 				    	driver.switchTo()            
 					            .activeElement()
 					            .sendKeys(
 					                    "/home/nadsoft/Desktop/Backup/ISTQB/20MB.pdf");
 					    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 					    Thread.sleep(3000);
 					    Robot r3 = new Robot();
 					    r3.keyPress(KeyEvent.VK_ESCAPE);
 					    r3.keyRelease(KeyEvent.VK_ESCAPE);
 					    
 					    //scroll down
 						jse = (JavascriptExecutor) driver;
 						jse.executeScript("window.scrollBy(0,500)", "");
 						Thread.sleep(1000);
 					    
 						
 						try {
 							WebElement e = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
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
 							WebElement e1 = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
 						    boolean actualValue1 = e1.isEnabled();
 						    if (actualValue1){
 						           System.out.println("Button is enabled");
 						           driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button")).click();
 						    }else                               
 						           System.out.println("Button is disabled");
 							
 						} catch (Exception e) {
 							e.printStackTrace();
 						}
 					    
 					  
 					  //Payslip 2
 					    String vMsg109 = driver.findElement(By.xpath(".//*[@id='docPayslip1-error']")).getText();
 						String vMsg110 = "This field is required.";
 						Assert.assertEquals(vMsg109, vMsg110);
 						Thread.sleep(2000); 
 					  		driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[2]/div/div/div/span/label/span")).click();
 					      	driver.switchTo()            
 					  	            .activeElement()
 					  	            .sendKeys(
 					  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-2. paper-2.pdf");
 					  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 					  	    Thread.sleep(1000);
 					  	    r3.keyPress(KeyEvent.VK_ESCAPE);
 					  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
 					  	    
 					  	    //scroll down
 							jse = (JavascriptExecutor) driver;
 							jse.executeScript("window.scrollBy(0,500)", "");
 							Thread.sleep(500);
 					  	    
 					  	    
 					  	  try {
 								WebElement e = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
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
 								WebElement e1 = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
 							    boolean actualValue1 = e1.isEnabled();
 							    if (actualValue1){
 							           System.out.println("Button is enabled");
 							           driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button")).click();
 							    }else
 							           System.out.println("Button is disabled");
 								
 							} catch (Exception e) {
 								e.printStackTrace();
 							}
 					  	    
 					  	    
 					  //Copy of Driver's Licenses	  
 					  	  String vMsg111 = driver.findElement(By.xpath(".//*[@id='docPayslip2-error']")).getText();
 							String vMsg112 = "This field is required.";
 							Assert.assertEquals(vMsg111, vMsg112);
 							Thread.sleep(2000); 
 					  	  driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[3]/div/div/div/span/label/span")).click();
 					      	driver.switchTo()
 					  	            .activeElement()
 					  	            .sendKeys(
 					  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-3.pdf");
 					  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 					  	    Thread.sleep(1000);
 					  	    r3.keyPress(KeyEvent.VK_ESCAPE);
 					  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
 					  	    
 					  	    //scroll down
 							jse = (JavascriptExecutor) driver;
 							jse.executeScript("window.scrollBy(0,500)", "");
 							Thread.sleep(500);
 					  	    
 					  	    
 					  	  try {
 								WebElement e = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
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
 								WebElement e1 = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
 							    boolean actualValue1 = e1.isEnabled();
 							    if (actualValue1){
 							           System.out.println("Button is enabled");
 							    }else
 							           System.out.println("Button is disabled");
 								
 							} catch (Exception e) {
 								e.printStackTrace();
 							}
 					  	    
 							System.out.println("Pass");
 					  	 
 					  	
 					  //Rates Notices for Home Owner (If applicable)
 					  	  driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[4]/div/div/div/span/label/span")).click();
 					      	driver.switchTo()
 					  	            .activeElement()
 					  	            .sendKeys(
 					  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-4.pdf");
 					  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 					  	    Thread.sleep(5000);
 					  	    r3.keyPress(KeyEvent.VK_ESCAPE);
 					  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
 					  	    Thread.sleep(2000);
 					  	    
 					  	//Other
 						  	  driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[5]/div/div/div/span/label/span")).click();
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
 						driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
 						Thread.sleep(15000);
		
		String actual2 = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
		System.out.println(actual2);                       
		String expected2 = "Test ApplicationsPersonalLoan";
		Assert.assertEquals(actual2, expected2);
			
		System.out.println("Add successfull personal application");
		
		System.out.println("====================================================================");
		
 	}
 	
 	public void editPersonalLoanApplicationWithANZ() throws InterruptedException{
 		
 		System.out.println("Edit functionality of consumer application");
 		
 		//click dashboard
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
 		Thread.sleep(2000);          
 		
 		//click applications
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/a")).click();
 		Thread.sleep(2000);          
 		
 		//click on view all 
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/ul/li[1]/a")).click();
 		Thread.sleep(10000); 
 		
 		
 		//edit
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[13]/div/button")).click();
 		Thread.sleep(4000);                                       
 		
 		//ANZ
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[4]/div/div/div/div/div/a[1]")).click();
 		Thread.sleep(20000);                 
 		
 		//Privacy Consent Form (check box)
 		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div[1]/div/div[2]/div/div/div[1]/div/div/label[1]/input")).click();
 		Thread.sleep(200);
 		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div[1]/div/div[2]/div/div/div[1]/div/div/label[2]/input")).click();
 		Thread.sleep(200);
 		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div[1]/div/div[2]/div/div/div[1]/div/div/label[3]/input")).click();
 		Thread.sleep(200);
 		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div[1]/div/div[2]/div/div/div[1]/div/div/label[4]/input")).click();
 		Thread.sleep(200);
 		
 		//Does customer have adverse credit file? (yes)
 		List <WebElement> g = driver.findElements(By.name("data[applicant_details][app_type]"));
		int ig = g.size();       
		System.out.println(ig);
		g.get(0).click();
		Thread.sleep(200);
 		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[1]/div[1]/div[1]/div/div[2]/div/div/div[2]/div/label[2]/input")).click();
 		Thread.sleep(200);
 		
 		//Your ANZ Relationship Manager is: - Name
 		driver.findElement(By.name("data[anz_consumer_relationship][name]")).clear();
		driver.findElement(By.name("data[anz_consumer_relationship][name]")).sendKeys("Vasim");
		Thread.sleep(200);
		
		//Phone
		driver.findElement(By.name("data[anz_consumer_relationship][phone]")).clear();
		driver.findElement(By.name("data[anz_consumer_relationship][phone]")).sendKeys("8956457845");
		Thread.sleep(200);
		
		//Email
		driver.findElement(By.name("data[anz_consumer_relationship][email]")).clear();
		driver.findElement(By.name("data[anz_consumer_relationship][email]")).sendKeys("vasimtest@nadsoft.com");
		Thread.sleep(200);
		
		//Fax
		driver.findElement(By.name("data[anz_consumer_relationship][fax]")).clear();
		driver.findElement(By.name("data[anz_consumer_relationship][fax]")).sendKeys("5645788945");
		Thread.sleep(200);
		
		//BROKER DETAILS - Broker Firm *
		driver.findElement(By.name("data[anz_consumer_broker_details][firm]")).clear();
		driver.findElement(By.name("data[anz_consumer_broker_details][firm]")).sendKeys("Wood Firm");
		Thread.sleep(200);
		
		//Source of Business Number (SOB) *
		driver.findElement(By.name("data[anz_consumer_broker_details][sob]")).clear();
		driver.findElement(By.name("data[anz_consumer_broker_details][sob]")).sendKeys("784556");
		Thread.sleep(200);
		
		//Phone Number *
		driver.findElement(By.name("data[anz_consumer_broker_details][phone]")).clear();
		driver.findElement(By.name("data[anz_consumer_broker_details][phone]")).sendKeys("7856451245");
		Thread.sleep(200);
		
		//Fax Number
		driver.findElement(By.name("data[banz_consumer_roker_details][fax]")).clear();
		driver.findElement(By.name("data[banz_consumer_roker_details][fax]")).sendKeys("4556788945");
		Thread.sleep(200);
		
		//Broker Name
		driver.findElement(By.name("data[anz_consumer_broker_details][name]")).clear();
		driver.findElement(By.name("data[anz_consumer_broker_details][name]")).sendKeys("Mahesh");
		Thread.sleep(200);
		
		//Date
		WebElement bDate = driver.findElement(By.name("data[anz_consumer_broker_details][date]"));
		bDate.clear();
		bDate.sendKeys("01/05/1981");
		Thread.sleep(200);
		
		//Mobile
		driver.findElement(By.name("data[anz_consumer_broker_details][mobile]")).clear();
		driver.findElement(By.name("data[anz_consumer_broker_details][mobile]")).sendKeys("8945561245");
		Thread.sleep(200);
		
		//Email
		driver.findElement(By.name("data[anz_consumer_broker_details][email]")).clear();
		driver.findElement(By.name("data[anz_consumer_broker_details][email]")).sendKeys("woodtest@nadsoft.com");
		Thread.sleep(200);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000)", "");
		Thread.sleep(2000);
		
		//Tick as applicable - LTD
		List <WebElement> e13 = driver.findElements(By.name("data[applicant_details][app_type]"));
		int i13 = e13.size();                                
		System.out.println(i13);
		e13.get(0).click();
		Thread.sleep(200);	
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[1]/div[1]/div[4]/div/div[2]/div/div[1]/div/label[1]/input")).click();
		Thread.sleep(200);
		
		//Number of Guarantors
		driver.findElement(By.name("data[broker_details][email]")).clear();
		driver.findElement(By.name("data[broker_details][email]")).sendKeys("3"); 
		Thread.sleep(200);
		
		//Personal Details - First name
		driver.findElement(By.name("data[personal_details1][fname]")).clear();
		driver.findElement(By.name("data[personal_details1][fname]")).sendKeys("TestApplication"); 
		Thread.sleep(200);
		
		//Middle name
		driver.findElement(By.name("data[personal_details1][mname]")).clear();
		driver.findElement(By.name("data[personal_details1][mname]")).sendKeys("Mahesh");
		Thread.sleep(200);
		
		//Surname
		driver.findElement(By.name("data[personal_details1][surname]")).clear();
		driver.findElement(By.name("data[personal_details1][surname]")).sendKeys("ANZ");
		Thread.sleep(200);
		
		//Gender - Male
		List <WebElement> e11 = driver.findElements(By.name("data[personal_details][gender]"));
		int i11 = e11.size();       
		System.out.println(i11);
		e11.get(0).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[1]/div[1]/div[5]/div/div[2]/div[2]/div[1]/div/div/label[1]/input")).click();
		Thread.sleep(200);
		
		//Privacy Consent - yes
		List <WebElement> e12 = driver.findElements(By.name("data[personal_details][privacy_con]"));
		int i12 = e12.size();       
		System.out.println(i12);
		e12.get(0).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[1]/div[1]/div[5]/div/div[2]/div[2]/div[2]/div/div/label[1]/input")).click();
		Thread.sleep(200);
		
		//Date of Birth
		WebElement bPDate = driver.findElement(By.name("data[personal_details][dob]"));
		bPDate.clear();
		bPDate.sendKeys("01/05/1995");
		Thread.sleep(200);
		
		//Country of Citizenship
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[1]/div[1]/div[5]/div/div[2]/div[3]/div[1]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[1]/div[1]/div[5]/div/div[2]/div[3]/div[1]/div/input")).sendKeys("India");
		Thread.sleep(200);
		
		//Other Country of Citizenship (if applicable)
		driver.findElement(By.name("data[personal_details1][other_counrty_of_citizen]")).clear();
		driver.findElement(By.name("data[personal_details1][other_counrty_of_citizen]")).sendKeys("No");
		Thread.sleep(200);
		
		//Marital Status
		driver.findElement(By.name("data[personal_details1][marital_status]")).clear();
		driver.findElement(By.name("data[personal_details1][marital_status]")).sendKeys("Single");
		Thread.sleep(200);
		
		//Number of Dependents
		driver.findElement(By.name("data[personal_details1][no_of_dependents]")).clear();
		driver.findElement(By.name("data[personal_details1][no_of_dependents]")).sendKeys("2");
		Thread.sleep(200);
		
		//Drivers License Number
		driver.findElement(By.name("data[personal_details1][driver_lic_no]")).clear();
		driver.findElement(By.name("data[personal_details1][driver_lic_no]")).sendKeys("455612");
		Thread.sleep(200);
		
		//Existing Esanda Customer? - yes
		List <WebElement> e14 = driver.findElements(By.name("data[personal_details][esanda_customer]"));
		int i14 = e14.size();       
		System.out.println(i14);
		e14.get(0).click();
		Thread.sleep(200);	
		
		//If Yes, Contract No.
		driver.findElement(By.name("data[personal_details1][yes_esanda_customer_no]")).clear();
		driver.findElement(By.name("data[personal_details1][yes_esanda_customer_no]")).sendKeys("89565521");
		Thread.sleep(200);
		
		//scroll up
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
		Thread.sleep(2000);
		
		//Address Details - Address
		driver.findElement(By.name("data[address_details][address]")).clear();
		driver.findElement(By.name("data[address_details][address]")).sendKeys("Kharadi, Pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[address_details][suburb]")).clear();
		driver.findElement(By.name("data[address_details][suburb]")).sendKeys("Kharadi");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.name("data[address_details][state_territory]")).clear();
		driver.findElement(By.name("data[address_details][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);          
		
		//Postcode
		driver.findElement(By.name("data[address_details][postcode]")).clear();
		driver.findElement(By.name("data[address_details][postcode]")).sendKeys("89457821");
		Thread.sleep(200);
		
		//Phone (home)
		driver.findElement(By.name("data[address_details][phone_home]")).clear();
		driver.findElement(By.name("data[address_details][phone_home]")).sendKeys("7589134679");
		Thread.sleep(200);
		
		//Phone (business)
		driver.findElement(By.name("data[address_details][phone_business]")).clear();
		driver.findElement(By.name("data[address_details][phone_business]")).sendKeys("2356794612");
		Thread.sleep(200);
		
		//Mobile
		driver.findElement(By.name("data[address_details][phone_mobile]")).clear();
		driver.findElement(By.name("data[address_details][phone_mobile]")).sendKeys("8946131346");
		Thread.sleep(200);
		
		//Residential Status
		List <WebElement> e15 = driver.findElements(By.name("data[address_details][residential_status]"));
		int i15 = e15.size();       
		System.out.println(i15);
		e15.get(0).click();
		Thread.sleep(200);	
		
		//Duration at Address
		driver.findElement(By.name("data[address_details][duration_at_previous_address][year]")).clear();
		driver.findElement(By.name("data[address_details][duration_at_previous_address][year]")).sendKeys("2");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[address_details][duration_at__previous_address][month]")).clear();
		driver.findElement(By.name("data[address_details][duration_at__previous_address][month]")).sendKeys("2");
		Thread.sleep(200);
		
		//Previous Address (if less than 3 years in current)
		driver.findElement(By.name("data[pre_address_details][acn]")).clear();
		driver.findElement(By.name("data[pre_address_details][acn]")).sendKeys("Camp, Pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[pre_address_details][suburb]")).clear();
		driver.findElement(By.name("data[pre_address_details][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.name("data[pre_address_details][state_territory]")).clear();
		driver.findElement(By.name("data[pre_address_details][state_territory]")).sendKeys("Maharshtra");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.name("data[pre_address_details][postcode]")).clear();
		driver.findElement(By.name("data[pre_address_details][postcode]")).sendKeys("794631");
		Thread.sleep(200);
		
		//Duration at Address
		driver.findElement(By.name("data[pre_address_details][duration_at_previous_address][year]")).clear();
		driver.findElement(By.name("data[pre_address_details][duration_at_previous_address][year]")).sendKeys("2");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[pre_address_details][duration_at__previous_address][month]")).clear();
		driver.findElement(By.name("data[pre_address_details][duration_at__previous_address][month]")).sendKeys("2");
		Thread.sleep(1000);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(2000);
		
		//next
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[1]/div[2]/div/button")).click();
		Thread.sleep(2000);          
		
		
		
		
		
		
		//Employment Details - Full Time
		List <WebElement> e16 = driver.findElements(By.name("data[employment_details][period]"));
		int i16 = e16.size();       
		System.out.println(i16);
		e16.get(0).click();
		Thread.sleep(200);	
		
		//Occupation
		driver.findElement(By.name("data[employment_details][occupation]")).clear();
		driver.findElement(By.name("data[employment_details][occupation]")).sendKeys("Job");
		Thread.sleep(200);
		
		//Name of Employer
		driver.findElement(By.name("data[employment_details][name_employer]")).clear();
		driver.findElement(By.name("data[employment_details][name_employer]")).sendKeys("Pradeep");
		Thread.sleep(200);
		
		//Address
		driver.findElement(By.name("data[employment_details][address]")).clear();
		driver.findElement(By.name("data[employment_details][address]")).sendKeys("Kothrord, pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[employment_details][suburb]")).clear();
		driver.findElement(By.name("data[employment_details][suburb]")).sendKeys("Kothrud");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.name("data[employment_details][state_territory]")).clear();
		driver.findElement(By.name("data[employment_details][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);          
		
		//Postcode
		driver.findElement(By.name("data[employment_details][postcode]")).clear();
		driver.findElement(By.name("data[employment_details][postcode]")).sendKeys("5623");
		Thread.sleep(200);
		
		//Phone
		driver.findElement(By.name("data[employment_details][phone]")).clear();
		driver.findElement(By.name("data[employment_details][phone]")).sendKeys("9865458982");
		Thread.sleep(200);
		
		//Contact Name
		driver.findElement(By.name("data[employment_details][contact_name]")).clear();
		driver.findElement(By.name("data[employment_details][contact_name]")).sendKeys("Deepak");
		Thread.sleep(200);
		
		//Position
		driver.findElement(By.name("data[employment_details][position]")).clear();
		driver.findElement(By.name("data[employment_details][position]")).sendKeys("MD");
		Thread.sleep(200);
		
		//Duration of Employment
		driver.findElement(By.name("data[employment_details][duration_at_employment][year]")).clear();
		driver.findElement(By.name("data[employment_details][duration_at_employment][year]")).sendKeys("1");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[employment_details][duration_at_employment][month]")).clear();
		driver.findElement(By.name("data[employment_details][duration_at_employment][month]")).sendKeys("5");
		Thread.sleep(200);
		
		//Spouse Details - First Name
		driver.findElement(By.name("data[spouse_details][firstname]")).clear();
		driver.findElement(By.name("data[spouse_details][firstname]")).sendKeys("Karim");
		Thread.sleep(200);
		
		//Second Name
		driver.findElement(By.name("data[spouse_details][secondname]")).clear();
		driver.findElement(By.name("data[spouse_details][secondname]")).sendKeys("Sohil");
		Thread.sleep(200);
		
		//Surname
		driver.findElement(By.name("data[spouse_details][surname]")).clear();
		driver.findElement(By.name("data[spouse_details][surname]")).sendKeys("Khan");
		Thread.sleep(200);
		
		//Date of Birth
		WebElement spdate = driver.findElement(By.name("data[spouse_details][dob]"));
		spdate.clear();
		spdate.sendKeys("01/05/1992");
		Thread.sleep(200);
		
		//Drivers Licence Number
		driver.findElement(By.name("data[driver_lic_num][firstname]")).clear();
		driver.findElement(By.name("data[driver_lic_num][firstname]")).sendKeys("895621");
		Thread.sleep(200);
		
		//Occupation
		driver.findElement(By.name("data[spouse_details][occupation]")).clear();
		driver.findElement(By.name("data[spouse_details][occupation]")).sendKeys("Job");
		Thread.sleep(200);
		
		//Employer
		driver.findElement(By.name("data[spouse_details][employer]")).clear();
		driver.findElement(By.name("data[spouse_details][employer]")).sendKeys("D Company");
		Thread.sleep(200);
		
		List <WebElement> e17 = driver.findElements(By.name("data[spouse_details][period]"));
		int i17 = e17.size();                                   
		System.out.println(i17);
		e17.get(0).click();
		Thread.sleep(200);
		
		//Duration of Employment
		driver.findElement(By.name("data[spouse_details][duration_at_employment][year]")).clear();
		driver.findElement(By.name("data[spouse_details][duration_at_employment][year]")).sendKeys("1");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[spouse_details][duration_at_employment][month]")).clear();
		driver.findElement(By.name("data[spouse_details][duration_at_employment][month]")).sendKeys("2");
		Thread.sleep(200);
		
		//Net Monthly Income
		driver.findElement(By.name("data[spouse_details][net_monthly_income]")).clear();
		driver.findElement(By.name("data[spouse_details][net_monthly_income]")).sendKeys("2000");
		Thread.sleep(200);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,900)", "");
		Thread.sleep(2000);
		
		//Previous Employment (if less than 3 years in current) - Occupation
		driver.findElement(By.name("data[employment_details][pre_address_details][occupation]")).clear();
		driver.findElement(By.name("data[employment_details][pre_address_details][occupation]")).sendKeys("Job");
		Thread.sleep(200);
		
		//Employer
		driver.findElement(By.name("data[employment_details][pre_address_details][employer]")).clear();
		driver.findElement(By.name("data[employment_details][pre_address_details][employer]")).sendKeys("DDD Soft");
		Thread.sleep(200);
		
		//Duration of Employment    
		driver.findElement(By.name("data[employment_details][pre_emp_details][year]")).clear();
		driver.findElement(By.name("data[employment_details][pre_emp_details][year]")).sendKeys("2");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[employment_details][pre_emp_details][month]")).clear();
		driver.findElement(By.name("data[employment_details][pre_emp_details][month]")).sendKeys("3");
		Thread.sleep(200);
		
		//Self Employed Details (if applicable) - ABN
		driver.findElement(By.name("data[self_employment_details][abn]")).clear();
		driver.findElement(By.name("data[self_employment_details][abn]")).sendKeys("784512");
		Thread.sleep(200);
		
		//Trading Name
		driver.findElement(By.name("data[self_employment_details][trading_name]")).clear();
		driver.findElement(By.name("data[self_employment_details][trading_name]")).sendKeys("MI");
		Thread.sleep(200);
		
		//Trust Name
		driver.findElement(By.name("data[self_employment_details][trust_name]")).clear();
		driver.findElement(By.name("data[self_employment_details][trust_name]")).sendKeys("Maya");
		Thread.sleep(200);
		
		//Type of Trust (ie. Family Trust, Discretionary Trust etc)
		driver.findElement(By.name("data[self_employment_details][type_trust]")).clear();
		driver.findElement(By.name("data[self_employment_details][type_trust]")).sendKeys("Family");
		Thread.sleep(200);          
		
		//Full Name of the Settlor of the Trust
		driver.findElement(By.name("data[self_employment_details][fullname_setter]")).clear();
		driver.findElement(By.name("data[self_employment_details][fullname_setter]")).sendKeys("KL Trust");
		Thread.sleep(200);
		
		//Nature of Business
		driver.findElement(By.name("data[self_employment_details][name_business]")).clear();
		driver.findElement(By.name("data[self_employment_details][name_business]")).sendKeys("Food");
		Thread.sleep(200);
		
		//Address
		driver.findElement(By.name("data[self_employment_details][address]")).clear();
		driver.findElement(By.name("data[self_employment_details][address]")).sendKeys("Pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[self_employment_details][suburb]")).clear();
		driver.findElement(By.name("data[self_employment_details][suburb]")).sendKeys("Station");
		Thread.sleep(200);
		
		//State / Territory
				driver.findElement(By.name("data[self_employment_details][state_territory]")).clear();
				driver.findElement(By.name("data[self_employment_details][state_territory]")).sendKeys("Maharashtra");
				Thread.sleep(200);
				
				//Postcode
				driver.findElement(By.name("data[self_employment_details][postcode]")).clear();
				driver.findElement(By.name("data[self_employment_details][postcode]")).sendKeys("894521");
				Thread.sleep(200);
		
		//Phone Number
		driver.findElement(By.name("data[self_employment_details][phone_number]")).clear();
		driver.findElement(By.name("data[self_employment_details][phone_number]")).sendKeys("9975455645");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-2']/div[3]/div/button[2]")).click();
		Thread.sleep(2000);
		
		
		
		
		
		//FINANCIAL DETAILS - Monthly Income Applicant - Monthly Income (after tax)
		driver.findElement(By.name("data[monthly_income_applicant][monthly_income]")).clear();
		driver.findElement(By.name("data[monthly_income_applicant][monthly_income]")).sendKeys("1000");
		Thread.sleep(200);
		
		//OR Net Profit (before tax) yearly
		driver.findElement(By.name("data[monthly_income_applicant][net_profit]")).clear();
		driver.findElement(By.name("data[monthly_income_applicant][net_profit]")).sendKeys("500");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[monthly_income_applicant][month_to]")).clear();
		driver.findElement(By.name("data[monthly_income_applicant][month_to]")).sendKeys("1");
		Thread.sleep(200);
		
		//date
		WebElement ndate = driver.findElement(By.name("data[monthly_income_applicant][date]"));
		ndate.clear();
		ndate.sendKeys("01/01/2016");
		Thread.sleep(200);
		
		//Depreciation
		driver.findElement(By.name("data[monthly_income_applicant][depreciation]")).clear();
		driver.findElement(By.name("data[monthly_income_applicant][depreciation]")).sendKeys("2");
		Thread.sleep(200);
		
		//Overtime
		driver.findElement(By.name("data[monthly_income_applicant][overtime]")).clear();
		driver.findElement(By.name("data[monthly_income_applicant][overtime]")).sendKeys("100");
		Thread.sleep(200);
		
		//Other Income (Rental/Commission)*
		driver.findElement(By.name("data[monthly_income_applicant][other_income]")).clear();
		driver.findElement(By.name("data[monthly_income_applicant][other_income]")).sendKeys("100");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[monthly_income_applicant][detail_other_income]")).clear();
		driver.findElement(By.name("data[monthly_income_applicant][detail_other_income]")).sendKeys("data");
		Thread.sleep(200);
		
		//Monthly Expenditure (combined) - Mortgage/Rent/Board
		driver.findElement(By.name("data[monthly_expenditure][mortagage]")).clear();
		driver.findElement(By.name("data[monthly_expenditure][mortagage]")).sendKeys("100");
		Thread.sleep(200);
		
		//Other Mortgage Payments
		driver.findElement(By.name("data[monthly_expenditure][other_mortage]")).clear();
		driver.findElement(By.name("data[monthly_expenditure][other_mortage]")).sendKeys("100");
		Thread.sleep(200);
		
		//Credit Cards
		driver.findElement(By.name("data[monthly_expenditure][credit_card]")).clear();
		driver.findElement(By.name("data[monthly_expenditure][credit_card]")).sendKeys("200");
		Thread.sleep(200);
		
		//Vehicle Expenses
		driver.findElement(By.name("data[monthly_expenditure][vehicle_expenses]")).clear();
		driver.findElement(By.name("data[monthly_expenditure][vehicle_expenses]")).sendKeys("100");
		Thread.sleep(200);
		
		//Home Expenses (utilities)
		driver.findElement(By.name("data[monthly_expenditure][home_expenes]")).clear();
		driver.findElement(By.name("data[monthly_expenditure][home_expenes]")).sendKeys("100");
		Thread.sleep(200);
		
		//Other Expenditure*
		driver.findElement(By.name("data[monthly_expenditure][other_expenes]")).clear();
		driver.findElement(By.name("data[monthly_expenditure][other_expenes]")).sendKeys("100");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[monthly_expenditure][detail_other_expenes]")).clear();
		driver.findElement(By.name("data[monthly_expenditure][detail_other_expenes]")).sendKeys("Data");
		Thread.sleep(200);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,600)", "");
		Thread.sleep(2000);
		
		//Personal Assets (combined) - Cash at Bank (excl. deposit)
		driver.findElement(By.name("data[personal_assets][cash_at_bank]")).clear();
		driver.findElement(By.name("data[personal_assets][cash_at_bank]")).sendKeys("200");
		Thread.sleep(200);
		
		//Home Value
		driver.findElement(By.name("data[personal_assets][home_value]")).clear();
		driver.findElement(By.name("data[personal_assets][home_value]")).sendKeys("100");
		Thread.sleep(200);
		
		//Other Property Value
		driver.findElement(By.name("data[personal_assets][other_property_value]")).clear();
		driver.findElement(By.name("data[personal_assets][other_property_value]")).sendKeys("100");
		Thread.sleep(200);
		
		//Motor Vehicle(s)
		driver.findElement(By.name("data[personal_assets][motor_vehicle]")).clear();
		driver.findElement(By.name("data[personal_assets][motor_vehicle]")).sendKeys("100");
		Thread.sleep(200);
		
		//Plant & Equipment
		driver.findElement(By.name("data[personal_assets][plant_equipment]")).clear();
		driver.findElement(By.name("data[personal_assets][plant_equipment]")).sendKeys("150");
		Thread.sleep(200);
		
		//Household Effects
		driver.findElement(By.name("data[personal_assets][household_effect]")).clear();
		driver.findElement(By.name("data[personal_assets][household_effect]")).sendKeys("200");
		Thread.sleep(200);
		
		//Business Assets
		driver.findElement(By.name("data[personal_assets][business_assets]")).clear();
		driver.findElement(By.name("data[personal_assets][business_assets]")).sendKeys("100");
		Thread.sleep(200);
		
		//Debentures
		driver.findElement(By.name("data[personal_assets][debenturnes]")).clear();
		driver.findElement(By.name("data[personal_assets][debenturnes]")).sendKeys("100");
		Thread.sleep(200);
		
		//Term Deposit
		driver.findElement(By.name("data[personal_assets][term_deposit]")).clear();
		driver.findElement(By.name("data[personal_assets][term_deposit]")).sendKeys("100");
		Thread.sleep(200);
		
		//Debtors
		driver.findElement(By.name("data[personal_assets][debtors]")).clear();
		driver.findElement(By.name("data[personal_assets][debtors]")).sendKeys("100");
		Thread.sleep(200);
		
		//Other Assets *
		driver.findElement(By.name("data[personal_assets][other_assets]")).clear();
		driver.findElement(By.name("data[personal_assets][other_assets]")).sendKeys("100");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[personal_assets][details_other_assets]")).clear();
		driver.findElement(By.name("data[personal_assets][details_other_assets]")).sendKeys("data");
		Thread.sleep(200);
		
		//Personal Liabilities (combined) - Home Mortgage
		driver.findElement(By.name("data[personal_liabilities][home_mortage]")).clear();
		driver.findElement(By.name("data[personal_liabilities][home_mortage]")).sendKeys("100");
		Thread.sleep(200);
		
		//Other Property Mortgage
		driver.findElement(By.name("data[personal_liabilities][property_mortage]")).clear();
		driver.findElement(By.name("data[personal_liabilities][property_mortage]")).sendKeys("100");
		Thread.sleep(200);
		
		//Creditors
		driver.findElement(By.name("data[personal_liabilities][creditors]")).clear();
		driver.findElement(By.name("data[personal_liabilities][creditors]")).sendKeys("100");
		Thread.sleep(200);
		
		//Credit Card Limit
		driver.findElement(By.name("data[personal_liabilities][credit_card_limit]")).clear();
		driver.findElement(By.name("data[personal_liabilities][credit_card_limit]")).sendKeys("100");
		Thread.sleep(200);
		
		//Overdraft Limit
		driver.findElement(By.name("data[personal_liabilities][overdraft_limit]")).clear();
		driver.findElement(By.name("data[personal_liabilities][overdraft_limit]")).sendKeys("100");
		Thread.sleep(200);
		
		//Loans Outstanding
		driver.findElement(By.name("data[personal_liabilities][loans_outstanding]")).clear();
		driver.findElement(By.name("data[personal_liabilities][loans_outstanding]")).sendKeys("200");
		Thread.sleep(200);
		
		//Other Liabilities
		driver.findElement(By.name("data[personal_liabilities][other_liabilities]")).clear();
		driver.findElement(By.name("data[personal_liabilities][other_liabilities]")).sendKeys("100");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[personal_liabilities][detail_other_liabilities]")).clear();
		driver.findElement(By.name("data[personal_liabilities][detail_other_liabilities]")).sendKeys("data");
		Thread.sleep(200);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(2000);
		
		//Details of Loans Outstanding - Financier
		driver.findElement(By.name("data[detail_loan][financier][val1]")).clear(); 
		driver.findElement(By.name("data[detail_loan][financier][val1]")).sendKeys("50");//-------------1
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][financier][val2]")).clear();
		driver.findElement(By.name("data[detail_loan][financier][val2]")).sendKeys("50");//-------------2
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][financier][val3]")).clear();
		driver.findElement(By.name("data[detail_loan][financier][val3]")).sendKeys("50");//-------------3
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][financier][val4]")).clear(); 
		driver.findElement(By.name("data[detail_loan][financier][val4]")).sendKeys("50");//-------------4
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][financier][val5]")).clear();
		driver.findElement(By.name("data[detail_loan][financier][val5]")).sendKeys("50");//-------------5
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][financier][val6]")).clear();
		driver.findElement(By.name("data[detail_loan][financier][val6]")).sendKeys("50");//-------------6
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][financier][val7]")).clear(); 
		driver.findElement(By.name("data[detail_loan][financier][val7]")).sendKeys("50");//-------------7
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][financier][val8]")).clear();
		driver.findElement(By.name("data[detail_loan][financier][val8]")).sendKeys("50");//-------------8
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][financier][val9]")).clear();
		driver.findElement(By.name("data[detail_loan][financier][val9]")).sendKeys("50");//-------------9
		Thread.sleep(200);
		
		//Instalment $
		driver.findElement(By.name("data[detail_loan][instalment][val1]")).clear(); 
		driver.findElement(By.name("data[detail_loan][instalment][val1]")).sendKeys("50");//-------------1
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][instalment][val2]")).clear();
		driver.findElement(By.name("data[detail_loan][instalment][val2]")).sendKeys("50");//-------------2
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][instalment][val3]")).clear();
		driver.findElement(By.name("data[detail_loan][instalment][val3]")).sendKeys("50");//-------------3
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][instalment][val4]")).clear(); 
		driver.findElement(By.name("data[detail_loan][instalment][val4]")).sendKeys("50");//-------------4
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][instalment][val5]")).clear();
		driver.findElement(By.name("data[detail_loan][instalment][val5]")).sendKeys("50");//-------------5
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][instalment][val6]")).clear();
		driver.findElement(By.name("data[detail_loan][instalment][val6]")).sendKeys("50");//-------------6
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][instalment][val7]")).clear(); 
		driver.findElement(By.name("data[detail_loan][instalment][val7]")).sendKeys("50");//-------------7
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][instalment][val8]")).clear();
		driver.findElement(By.name("data[detail_loan][instalment][val8]")).sendKeys("50");//-------------8
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][instalment][val9]")).clear();
		driver.findElement(By.name("data[detail_loan][instalment][val9]")).sendKeys("50");//-------------9
		Thread.sleep(200);
		
		//Commencement Date data[detail_loan][commencement_date][val1] 
		WebElement date1 = driver.findElement(By.name("data[detail_loan][commencement_date][val1]"));//--------1
		date1.clear();
		date1.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date2 = driver.findElement(By.name("data[detail_loan][commencement_date][val2]"));//--------2
		date2.clear();
		date2.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date3 = driver.findElement(By.name("data[detail_loan][commencement_date][val3]"));//--------3
		date3.clear();
		date3.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date4 = driver.findElement(By.name("data[detail_loan][commencement_date][val4]"));//--------4
		date4.clear();
		date4.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date5 = driver.findElement(By.name("data[detail_loan][commencement_date][val5]"));//--------5
		date5.clear();
		date5.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date6 = driver.findElement(By.name("data[detail_loan][commencement_date][val6]"));//--------6
		date6.clear();
		date6.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date7 = driver.findElement(By.name("data[detail_loan][commencement_date][val7]"));//--------7
		date7.clear();
		date7.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date8 = driver.findElement(By.name("data[detail_loan][commencement_date][val8]"));//--------8
		date8.clear();
		date8.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date9 = driver.findElement(By.name("data[detail_loan][commencement_date][val9]"));//--------9
		date9.clear();
		date9.sendKeys("01/01/2016");
		Thread.sleep(200);
		
		//Term (months)
		driver.findElement(By.name("data[detail_loan][term][val1]")).clear(); 
		driver.findElement(By.name("data[detail_loan][term][val1]")).sendKeys("50");//-------------1
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][term][val2]")).clear();
		driver.findElement(By.name("data[detail_loan][term][val2]")).sendKeys("50");//-------------2
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][term][val3]")).clear();
		driver.findElement(By.name("data[detail_loan][term][val3]")).sendKeys("50");//-------------3
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][term][val4]")).clear(); 
		driver.findElement(By.name("data[detail_loan][term][val4]")).sendKeys("50");//-------------4
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][term][val5]")).clear();
		driver.findElement(By.name("data[detail_loan][term][val5]")).sendKeys("50");//-------------5
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][term][val6]")).clear();
		driver.findElement(By.name("data[detail_loan][term][val6]")).sendKeys("50");//-------------6
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan]term][val7]")).clear(); 
		driver.findElement(By.name("data[detail_loan]term][val7]")).sendKeys("50");//-------------7
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][term][val8]")).clear();
		driver.findElement(By.name("data[detail_loan][term][val8]")).sendKeys("50");//-------------8
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][term][val9]")).clear();
		driver.findElement(By.name("data[detail_loan][term][val9]")).sendKeys("50");//-------------9
		Thread.sleep(200);
		
		//To be paid out (Y/N)
		driver.findElement(By.name("data[detail_loan][to_be_paid][val1]")).clear(); 
		driver.findElement(By.name("data[detail_loan][to_be_paid][val1]")).sendKeys("Y");//-------------1
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][to_be_paid][val2]")).clear();
		driver.findElement(By.name("data[detail_loan][to_be_paid][val2]")).sendKeys("N");//-------------2
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][to_be_paid][val3]")).clear();
		driver.findElement(By.name("data[detail_loan][to_be_paid][val3]")).sendKeys("Y");//-------------3
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][to_be_paid][val4]")).clear(); 
		driver.findElement(By.name("data[detail_loan][to_be_paid][val4]")).sendKeys("N");//-------------4
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][to_be_paid][val5]")).clear();
		driver.findElement(By.name("data[detail_loan][to_be_paid][val5]")).sendKeys("Y");//-------------5
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][to_be_paid][val6]")).clear();
		driver.findElement(By.name("data[detail_loan][to_be_paid][val6]")).sendKeys("N");//-------------6
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][to_be_paid][val7]")).clear(); 
		driver.findElement(By.name("data[detail_loan][to_be_paid][val7]")).sendKeys("Y");//-------------7
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][to_be_paid][val8]")).clear();
		driver.findElement(By.name("data[detail_loan][to_be_paid][val8]")).sendKeys("N");//-------------8
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][to_be_paid][val9]")).clear();
		driver.findElement(By.name("data[detail_loan][to_be_paid][val9]")).sendKeys("N");//-------------9
		Thread.sleep(200);
		
		//Contract No. (if Esanda)
		driver.findElement(By.name("data[detail_loan][contract_no]val1]")).clear(); 
		driver.findElement(By.name("data[detail_loan][contract_no]val1]")).sendKeys("40");//-------------1
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][contract_no]val2]")).clear();
		driver.findElement(By.name("data[detail_loan][contract_no]val2]")).sendKeys("04");//-------------2
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][contract_no]val3]")).clear();
		driver.findElement(By.name("data[detail_loan][contract_no]val3]")).sendKeys("025");//-------------3
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][contract_no][val4]")).clear(); 
		driver.findElement(By.name("data[detail_loan][contract_no][val4]")).sendKeys("01");//-------------4
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][contract_no]val5]")).clear();
		driver.findElement(By.name("data[detail_loan][contract_no]val5]")).sendKeys("20");//-------------5
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][contract_no]val6]")).clear();
		driver.findElement(By.name("data[detail_loan][contract_no]val6]")).sendKeys("01");//-------------6
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][contract_no]val7]")).clear(); 
		driver.findElement(By.name("data[detail_loan][contract_no]val7]")).sendKeys("04");//-------------7
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][contract_no]val8]")).clear();
		driver.findElement(By.name("data[detail_loan][contract_no]val8]")).sendKeys("05");//-------------8
		Thread.sleep(200);
		driver.findElement(By.name("data[detail_loan][contract_no]val9]")).clear();
		driver.findElement(By.name("data[detail_loan][contract_no]val9]")).sendKeys("04");//-------------9
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-3']/div[5]/div/button[2]")).click();
		Thread.sleep(2000);          
		
		
		
		
		//Supplier Details - Supplier Name
		driver.findElement(By.name("data[supplier_details][name]")).clear();
		driver.findElement(By.name("data[supplier_details][name]")).sendKeys("Kailas");
		Thread.sleep(200);
		
		//Address
		driver.findElement(By.name("data[supplier_details][address]")).clear();
		driver.findElement(By.name("data[supplier_details][address]")).sendKeys("Camp, Pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[supplier_details][suburb]")).clear();
		driver.findElement(By.name("data[supplier_details][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.name("data[supplier_details][state_territory]")).clear();
		driver.findElement(By.name("data[supplier_details][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.name("data[supplier_details][postcode]")).clear();
		driver.findElement(By.name("data[supplier_details][postcode]")).sendKeys("894512");
		Thread.sleep(200);
		
		//Supplier Type - Sale & Buy Back
		List <WebElement> e20 = driver.findElements(By.name("data[supplier_type]"));
		int i20 = e20.size();       
		System.out.println(i20);
		e20.get(0).click();
		Thread.sleep(200);	
		
		//Goods Category
		driver.findElement(By.name("data[vehicle_chattel_detail][goods_cate]")).clear();
		driver.findElement(By.name("data[vehicle_chattel_detail][goods_cate]")).sendKeys("Good");
		Thread.sleep(200);          
		
		//New / Used / Demo
		driver.findElement(By.name("data[vehicle_chattel_detail][new_used_demo]")).clear();
		driver.findElement(By.name("data[vehicle_chattel_detail][new_used_demo]")).sendKeys("New");
		Thread.sleep(200);
		
		//Year of Manufacture
		driver.findElement(By.name("data[vehicle_chattel_detail][year_manufature]")).clear();
		driver.findElement(By.name("data[vehicle_chattel_detail][year_manufature]")).sendKeys("2015");
		Thread.sleep(200);
		
		//Make
		driver.findElement(By.name("data[vehicle_chattel_detail][make]")).clear();
		driver.findElement(By.name("data[vehicle_chattel_detail][make]")).sendKeys("Demo");
		Thread.sleep(200);
		
		//Model
		driver.findElement(By.name("data[vehicle_chattel_detail][model]")).clear();
		driver.findElement(By.name("data[vehicle_chattel_detail][model]")).sendKeys("GT451");
		Thread.sleep(200);
		
		//Type
		List <WebElement> e21 = driver.findElements(By.name("data[vehicle_chattel_detail][type]"));
		int i21 = e21.size();       
		System.out.println(i21);
		e21.get(0).click();
		Thread.sleep(200);
		
		//Other – please specify
		driver.findElement(By.name("data[vehicle_chattel_detail][other]")).clear();
		driver.findElement(By.name("data[vehicle_chattel_detail][other]")).sendKeys("Data");
		Thread.sleep(200);
		
		//Description of Goods
				driver.findElement(By.name("data[vehicle_chattel_detail][desciption_good]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][desciption_good]")).sendKeys("Nice");
				Thread.sleep(200);          
				
				//Options/Accessories
				driver.findElement(By.name("data[vehicle_chattel_detail][options_accessories]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][options_accessories]")).sendKeys("Yes");
				Thread.sleep(200);
				
				//Manual/Automatic
				driver.findElement(By.name("data[vehicle_chattel_detail][manual_auto]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][manual_auto]")).sendKeys("Automation");
				Thread.sleep(200);
				
				//Kilometers
				driver.findElement(By.name("data[vehicle_chattel_detail][marital_status]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][marital_status]")).sendKeys("10");
				Thread.sleep(200);
				
				//Hours (if applicable)
				driver.findElement(By.name("data[vehicle_chattel_detail][number_of_dependents]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][number_of_dependents]")).sendKeys("3");
				Thread.sleep(200);
				
				//Engine Type - 4cyl
				List <WebElement> e26 = driver.findElements(By.name("data[vehicle_chattel_detail][engine_type]"));
				int i26 = e26.size();       
				System.out.println(i26);
				e26.get(0).click();
				Thread.sleep(200);
				
				//Other
				driver.findElement(By.name("data[vehicle_chattel_detail][engine_type][other]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][engine_type][other]")).sendKeys("Data");
				Thread.sleep(200);
				
				//Fuel - Petrol 
				List <WebElement> e27 = driver.findElements(By.name("data[vehicle_chattel_detail][fuel]"));
				int i27 = e27.size();       
				System.out.println(i27);
				e27.get(0).click();
				Thread.sleep(200);
				
				//Other
				driver.findElement(By.name("data[vehicle_chattel_detail][fuel][other]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][fuel][other]")).sendKeys("Data");
				Thread.sleep(200);
				
				//Trade-in Details:  -  Year
				driver.findElement(By.name("data[vehicle_chattel_detail][trade_detail][year]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][trade_detail][year]")).sendKeys("2015");
				Thread.sleep(200);          
				
				//Make 
				driver.findElement(By.name("data[vehicle_chattel_detail][trade_detail][make]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][trade_detail][make]")).sendKeys("Me");
				Thread.sleep(200);
				
				//Model
				driver.findElement(By.xpath(".//*[@id='step-4']/div[2]/div[1]/div/div[2]/div/div[9]/div[1]/div[3]/input")).click();
				driver.findElement(By.xpath(".//*[@id='step-4']/div[2]/div[1]/div/div[2]/div/div[9]/div[1]/div[3]/input")).sendKeys("GH45");
				Thread.sleep(200);
				
				//Type
				driver.findElement(By.xpath(".//*[@id='step-4']/div[2]/div[1]/div/div[2]/div/div[9]/div[2]/div[1]/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-4']/div[2]/div[1]/div/div[2]/div/div[9]/div[2]/div[1]/input")).sendKeys("One");
				Thread.sleep(200);  
				
				//Registration Number:
				driver.findElement(By.name("data[vehicle_chattel_detail][register_name]")).clear();
				driver.findElement(By.name("data[vehicle_chattel_detail][register_name]")).sendKeys("621212");
				Thread.sleep(200);
		
		
		//Facility - Lease
				List <WebElement> e22 = driver.findElements(By.name("data[finance_details][facility]"));
				int i22 = e22.size();       
				System.out.println(i22);
				e22.get(0).click();
				Thread.sleep(200);
		
		//Cash Price (Incl GST)
		driver.findElement(By.name("data[finance_details][cash_price]")).clear();
		driver.findElement(By.name("data[finance_details][cash_price]")).sendKeys("1000");
		Thread.sleep(200);
		
		//GST Amount (as per invoice)
		driver.findElement(By.name("data[finance_details][gst_amount]")).clear();
		driver.findElement(By.name("data[finance_details][gst_amount]")).sendKeys("200");
		Thread.sleep(200);
		
		//Less Deposit
		driver.findElement(By.name("data[finance_details][less_deposit]")).clear();
		driver.findElement(By.name("data[finance_details][less_deposit]")).sendKeys("50");
		Thread.sleep(200);
		
		//Less Trade-in Value
		driver.findElement(By.name("data[finance_details][less_trade]")).clear();
		driver.findElement(By.name("data[finance_details][less_trade]")).sendKeys("10");
		Thread.sleep(200);
		
		//Plus Origination Fee (M/V only) 
		driver.findElement(By.name("data[finance_details][plus_orgination_fee]")).clear();
		driver.findElement(By.name("data[finance_details][plus_orgination_fee]")).sendKeys("V");
		Thread.sleep(200);
		
		//Payout Finance Company - Finance Company Name
		driver.findElement(By.name("data[payout_finance][finance_co_name]")).clear();
		driver.findElement(By.name("data[payout_finance][finance_co_name]")).sendKeys("PTM");
		Thread.sleep(200);
		
		//Plus Amount Owing	
		driver.findElement(By.name("data[payout_finance][plus_amount]")).clear();
		driver.findElement(By.name("data[payout_finance][plus_amount]")).sendKeys("60");
		Thread.sleep(200);
		
		//Total Amount Financed
		driver.findElement(By.name("data[payout_finance][total_amout]")).clear();
		driver.findElement(By.name("data[payout_finance][total_amout]")).sendKeys("20");
		Thread.sleep(200);
		
		//Commission/Brokerage (incl GST)
		driver.findElement(By.name("data[payout_finance][commission]")).clear();
		driver.findElement(By.name("data[payout_finance][commission]")).sendKeys("20");
		Thread.sleep(200);
		
		//Base Rate
		driver.findElement(By.name("data[payout_finance][base_rate]")).clear();
		driver.findElement(By.name("data[payout_finance][base_rate]")).sendKeys("100");
		Thread.sleep(200);
		
		//Writing/Customer Rate
		driver.findElement(By.name("data[payout_finance][writing_rate]")).clear();
		driver.findElement(By.name("data[payout_finance][writing_rate]")).sendKeys("50");
		Thread.sleep(200);
		
		//Term (months)
		driver.findElement(By.name("data[payout_finance][term]")).clear();
		driver.findElement(By.name("data[payout_finance][term]")).sendKeys("8");
		Thread.sleep(200);
		
		//Balloon/Residual ($ or %)
		driver.findElement(By.name("data[payout_finance][balloon]")).clear();
		driver.findElement(By.name("data[payout_finance][balloon]")).sendKeys("10");
		Thread.sleep(200);
		
		//Repayment Pattern
		driver.findElement(By.name("data[payout_finance][repayment_pattern]")).clear();
		driver.findElement(By.name("data[payout_finance][repayment_pattern]")).sendKeys("1");
		Thread.sleep(200);
		
		//If irregular, provide details
		driver.findElement(By.name("data[payout_finance][irregular_details]")).clear();
		driver.findElement(By.name("data[payout_finance][irregular_details]")).sendKeys("Data");
		Thread.sleep(200);
		
		//Payments - Advance
		List <WebElement> e23 = driver.findElements(By.name("data[payout_finance][payment]"));
		int i23 = e23.size();       
		System.out.println(i23);
		e23.get(0).click();
		Thread.sleep(200);
		
		//Fees -  Direct Debit
		List <WebElement> e24 = driver.findElements(By.name("data[payout_finance][fee]"));
		int i24 = e24.size();       
		System.out.println(i24);
		e24.get(0).click();
		Thread.sleep(200);
		
		//State/Territory where goods will be predominantly used
		driver.findElement(By.name("data[payout_finance][state_territory]")).clear();
		driver.findElement(By.name("data[payout_finance][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		
		//Payment Method: - Direct Debit
		List <WebElement> e25 = driver.findElements(By.name("data[payout_finance][payment_method]"));
		int i25 = e25.size();       
		System.out.println(i25);
		e25.get(0).click();
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-4']/div[3]/div/button[2]")).click();
		Thread.sleep(2000);          
		
		
		
		
		
		//Full Name of the Settlor of the Trust
		driver.findElement(By.xpath(".//*[@id='step-5']/div[3]/div/div/div/div/div[1]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-5']/div[3]/div/div/div/div/div[1]/div/input")).sendKeys("Trust me");
		Thread.sleep(200);           
		
		//Full Name-1
		driver.findElement(By.xpath(".//*[@id='step-5']/div[3]/div/div/div/div/div[2]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-5']/div[3]/div/div/div/div/div[2]/div[1]/input")).sendKeys("Gurukant");
		Thread.sleep(200);
		
		//Street Address
		driver.findElement(By.name("data[additional_trustees_details][set1][street_address]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set1][street_address]")).sendKeys("Wadia, Pune");
		Thread.sleep(200);          
		
		//Suburb
		driver.findElement(By.name("data[additional_trustees_details][set1][suburb]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set1][suburb]")).sendKeys("Wedia");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.name("data[additional_trustees_details][set1][state_territory]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set1][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.name("data[additional_trustees_details][set1][postcode]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set1][postcode]")).sendKeys("56122");
		Thread.sleep(200);
		
		List <WebElement> e30 = driver.findElements(By.name("data[additional_trustees_details][set1][type]"));
		int i30 = e30.size();       
		System.out.println(i30);
		e30.get(0).click();
		Thread.sleep(200);
		
		driver.findElement(By.name("data[additional_trustees_details][set1][class_beneficiary]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set1][class_beneficiary]")).sendKeys("Data1");
		Thread.sleep(200);          
		
		//Full Name-2
		driver.findElement(By.name("data[additional_trustees_details][set2][first_name]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set2][first_name]")).sendKeys("HAT");
		Thread.sleep(200);          
				
		//Street Address
		driver.findElement(By.name("data[additional_trustees_details][set2][street_address]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set2][street_address]")).sendKeys("Wakad, Pune");
		Thread.sleep(200);
				
		//Suburb
		driver.findElement(By.name("data[additional_trustees_details][set2][suburb]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set2][suburb]")).sendKeys("Wakad");
		Thread.sleep(200);
				
		//State / Territory
		driver.findElement(By.name("data[additional_trustees_details][set2][state_territory]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set2][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
				
		//Postcode
		driver.findElement(By.name("data[additional_trustees_details][set2][postcode]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set2][postcode]")).sendKeys("85412");
		Thread.sleep(200);
		
		List <WebElement> e31 = driver.findElements(By.name("data[additional_trustees_details][set2][type]"));
		int i31 = e31.size();       
		System.out.println(i31);
		e31.get(0).click();
		Thread.sleep(200);
		
		driver.findElement(By.name("data[additional_trustees_details][set2][class_beneficiary]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set2][class_beneficiary]")).sendKeys("Data2");
		Thread.sleep(200);
		
		//Full Name-3
		driver.findElement(By.name("data[additional_trustees_details][set3][first_name]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set3][first_name]")).sendKeys("Lava");
		Thread.sleep(200);
						
		//Street Address
		driver.findElement(By.name("data[additional_trustees_details][set3][street_address]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set3][street_address]")).sendKeys("KK market, Pune");
		Thread.sleep(200);
						
		//Suburb
		driver.findElement(By.name("data[additional_trustees_details][set3][suburb]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set3][suburb]")).sendKeys("KK market");
		Thread.sleep(200);
						
		//State / Territory
		driver.findElement(By.name("data[additional_trustees_details][set3][state_territory]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set3][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
						
		//Postcode
		driver.findElement(By.name("data[additional_trustees_details][set3][postcode]")).clear();
		driver.findElement(By.name("data[additional_trustees_details][set3][postcode]")).sendKeys("455612");
		Thread.sleep(200);
		
		List <WebElement> e32 = driver.findElements(By.name("data[additional_trustees_details][set3][type]"));
		int i32 = e32.size();       
		System.out.println(i32);
		e32.get(0).click();
		Thread.sleep(200);
		
		driver.findElement(By.name("data[additional_directories][set3][class_beneficiary]")).clear();
		driver.findElement(By.name("data[additional_directories][set3][class_beneficiary]")).sendKeys("Data3");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-5']/div[6]/button[2]")).click();
		Thread.sleep(2000);   
		
		
		
		
		//Bank Reference - Bank Name
		driver.findElement(By.name("data[bank_reference][bank_name]")).clear();
		driver.findElement(By.name("data[bank_reference][bank_name]")).sendKeys("ICICI");
		Thread.sleep(200);          
		
		//Branch
		driver.findElement(By.name("data[bank_reference][branch]")).clear();
		driver.findElement(By.name("data[bank_reference][branch]")).sendKeys("Station,Pune");
		Thread.sleep(200);
		
		//Account Type
		driver.findElement(By.name("data[bank_reference][account_type]")).clear();
		driver.findElement(By.name("data[bank_reference][account_type]")).sendKeys("Personal");
		Thread.sleep(200);
		
		//Personal Reference - Name
		driver.findElement(By.name("data[personal_reference][name]")).clear();
		driver.findElement(By.name("data[personal_reference][name]")).sendKeys("Dinesh");
		Thread.sleep(200);
		
		//Address
		driver.findElement(By.name("data[personal_reference][address]")).clear();
		driver.findElement(By.name("data[personal_reference][address]")).sendKeys("Wakad, Pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[personal_reference][suburb]")).clear();
		driver.findElement(By.name("data[personal_reference][suburb]")).sendKeys("Wakad");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.name("data[personal_reference][state_territory]")).clear();
		driver.findElement(By.name("data[personal_reference][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.name("data[personal_reference][postcode]")).clear();
		driver.findElement(By.name("data[personal_reference][postcode]")).sendKeys("452345");
		Thread.sleep(200);
		
		//Phone Number
		driver.findElement(By.name("data[personal_reference][phone_number]")).clear();
		driver.findElement(By.name("data[personal_reference][phone_number]")).sendKeys("7889455612");
		Thread.sleep(200);
		
		//Business/Trade References (sole trader only) must provide at least 2 - Contact Person
				driver.findElement(By.name("data[business_ref][contact_person]")).clear();
				driver.findElement(By.name("data[business_ref][contact_person]")).sendKeys("Amit");
				Thread.sleep(200);
				
				//Company name
				driver.findElement(By.name("data[business_ref][company_name]")).clear();
				driver.findElement(By.name("data[business_ref][company_name]")).sendKeys("Amit trade");
				Thread.sleep(200);
				
				//Phone Number
				driver.findElement(By.name("data[business_ref][phone_number]")).clear();
				driver.findElement(By.name("data[business_ref][phone_number]")).sendKeys("7889897878");
				Thread.sleep(200);
		
				//Accountant Details (sole trader only) - Contact Person
				driver.findElement(By.name("data[account_detail_sole][contact_person]")).clear();
				driver.findElement(By.name("data[account_detail_sole][contact_person]")).sendKeys("IDBI");
				Thread.sleep(200);
				
				//Name of Accounting Firm
				driver.findElement(By.name("data[account_detail_sole][name_account_firm]")).clear();
				driver.findElement(By.name("data[account_detail_sole][name_account_firm]")).sendKeys("Ac IDBI");
				Thread.sleep(200);
				
				//Phone Number
				driver.findElement(By.name("data[account_detail_sole][phone_number]")).clear();
				driver.findElement(By.name("data[account_detail_sole][phone_number]")).sendKeys("9000000001");
				Thread.sleep(200);
		
		//Nearest Living relative (not living with applicant) - Name
		driver.findElement(By.name("data[near_living_relative][company_name]")).clear();
		driver.findElement(By.name("data[near_living_relative][company_name]")).sendKeys("Sameer");
		Thread.sleep(200);
	
		//Address
		driver.findElement(By.name("data[near_living_relative][address]")).clear();
		driver.findElement(By.name("data[near_living_relative][address]")).sendKeys("Rastapeth ,Pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[near_living_relative][suburb]")).clear();
		driver.findElement(By.name("data[near_living_relative][suburb]")).sendKeys("Rastapeth");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.name("data[near_living_relative][state_territory]")).clear();
		driver.findElement(By.name("data[near_living_relative][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.name("data[near_living_relative][postcode]")).clear();
		driver.findElement(By.name("data[near_living_relative][postcode]")).sendKeys("784512");
		Thread.sleep(200);
		
		//Phone Number
		driver.findElement(By.name("data[near_living_relative][phone_number]")).clear();
		driver.findElement(By.name("data[near_living_relative][phone_number]")).sendKeys("9878465212");
		Thread.sleep(200);
		
		//Landlord/Agent/Mortgagee - Company
		driver.findElement(By.name("data[landloard][company_name]")).clear();
		driver.findElement(By.name("data[landloard][company_name]")).sendKeys("K agent");
		Thread.sleep(200);
		
		//Contact Name
		driver.findElement(By.name("data[landloard][contact_name]")).clear();
		driver.findElement(By.name("data[landloard][contact_name]")).sendKeys("Sanjay");
		Thread.sleep(200);
		
		//Branch
		driver.findElement(By.name("data[landloard][branch]")).clear();
		driver.findElement(By.name("data[landloard][branch]")).sendKeys("Mega center");
		Thread.sleep(200);
		
		//Phone Number
		driver.findElement(By.name("data[landloard][phone_number]")).clear();
		driver.findElement(By.name("data[landloard][phone_number]")).sendKeys("7755484845");
		Thread.sleep(200);
		
		//Contact Person
		driver.findElement(By.name("data[business_ref][contact_person_2]")).clear();
		driver.findElement(By.name("data[business_ref][contact_person_2]")).sendKeys("Akshay");
		Thread.sleep(200);
		
		//Company Name
		driver.findElement(By.name("data[business_ref][company_name_2]")).clear();
		driver.findElement(By.name("data[business_ref][company_name_2]")).sendKeys("Akshay trade");
		Thread.sleep(200);
		
		//Phone Number
		driver.findElement(By.name("data[business_ref][phone_number_2]")).clear();
		driver.findElement(By.name("data[business_ref][phone_number_2]")).sendKeys("9638527412");
		Thread.sleep(200);
		
		//ADDITIONAL COMMENTS
		driver.findElement(By.name("data[account_detail_sole][additional_comment]")).clear();
		driver.findElement(By.name("data[account_detail_sole][additional_comment]")).sendKeys("data");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-6']/div[3]/div/button[2]")).click();
		Thread.sleep(2000); 
		
		
		

		
		//Guarantor 1 – Personal Details - First Name
		driver.findElement(By.name("data[guarantor_detail][personal_1][firstname]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][firstname]")).sendKeys("Moshin");
		Thread.sleep(200);
		
		//Second Name 
		driver.findElement(By.name("data[guarantor_detail][personal_1][middlename]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][middlename]")).sendKeys("salim");
		Thread.sleep(200);
		
		//Surname   
		driver.findElement(By.name("data[guarantor_detail][personal_1][surname]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][surname]")).sendKeys("Jamadar");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[guarantor_detail][personal_1][suburb]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][suburb]")).sendKeys("Wadia");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.name("data[guarantor_detail][personal_1][state_territory]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.name("data[guarantor_detail][personal_1][postcode]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][postcode]")).sendKeys("5551");
		Thread.sleep(200);
		
		//Duration at Address
		driver.findElement(By.name("data[guarantor_detail][personal_1][duration_address][year]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][duration_address][year]")).sendKeys("2");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[guarantor_detail][personal_1][duration_address][month]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][duration_address][month]")).sendKeys("2");
		Thread.sleep(200);
		
		//Gender
		List <WebElement> e33 = driver.findElements(By.name("data[guarantor_detail][personal_1][gender]"));
		int i33 = e33.size();       
		System.out.println(i33);
		e33.get(0).click();
		Thread.sleep(200);
		
		//Privacy Consent
		List <WebElement> e34 = driver.findElements(By.name("data[guarantor_detail][personal_1]['privacy_con']"));
		int i34 = e34.size();       
		System.out.println(i34);
		e34.get(0).click();
		Thread.sleep(200);
		
		//dob
		WebElement dob5 = driver.findElement(By.name("data[guarantor_detail][personal_1][dob]"));
		dob5.clear();
		dob5.sendKeys("05/05/1990");
		Thread.sleep(200);
		dob5.sendKeys(Keys.TAB);
		
		//Country of Citizenship
		driver.findElement(By.name("data[guarantor_detail][personal_1][country_citizen]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][country_citizen]")).sendKeys("Indian");
		Thread.sleep(200);          
		
		//Other Country of Citizenship (if applicable)
		driver.findElement(By.name("data[guarantor_detail][personal_1][other_country_citizen]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][other_country_citizen]")).sendKeys("No");
		Thread.sleep(200);
		
		//Marital Status
		driver.findElement(By.name("datadata[guarantor_detail][personal_1][marital_status]")).clear();
		driver.findElement(By.name("datadata[guarantor_detail][personal_1][marital_status]")).sendKeys("Single");
		Thread.sleep(200);
		
		//Number of Dependents
		driver.findElement(By.name("data[guarantor_detail][personal_1][number_dependents]")).clear();
		driver.findElement(By.name("data[guarantor_detail][personal_1][number_dependents]")).sendKeys("5");
		Thread.sleep(200);
		
		//Drivers Licence Number
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[7]/div[2]/div/div/div[2]/div[12]/div/input")).click();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[7]/div[2]/div/div/div[2]/div[12]/div/input")).sendKeys("87455612");
		Thread.sleep(200); 
		
						
				//Address
				driver.findElement(By.name("data[guarantor_detail][address_details_1][address]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][address]")).sendKeys("Daccan, Pune");
				Thread.sleep(200);
				
				//Suburb
				driver.findElement(By.name("data[guarantor_detail][address_details_1][suburb]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][suburb]")).sendKeys("Daccan");
				Thread.sleep(200);        
				
				//State / Territory
				driver.findElement(By.name("data[guarantor_detail][address_details_1][state_territory]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][state_territory]")).sendKeys("Maharashtra");
				Thread.sleep(200);          
				
				//Postcode
				driver.findElement(By.name("data[guarantor_detail][address_details_1][postcode]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][postcode]")).sendKeys("54621");
				Thread.sleep(200);          
				
				//Phone (home)
				driver.findElement(By.name("data[guarantor_detail][address_details_1][phone_home]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][phone_home]")).sendKeys("8956451245");
				Thread.sleep(200);          
				
				//Phone (business)
				driver.findElement(By.name("data[guarantor_detail][address_details_1][phone_business]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][phone_business]")).sendKeys("9865451245");
				Thread.sleep(200);          
				
				//Mobile
				driver.findElement(By.name("data[guarantor_detail][address_details_1][mobile]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][mobile]")).sendKeys("7989655421");
				Thread.sleep(200);
				
				//Residential Status
				List <WebElement> re = driver.findElements(By.name("data[guarantor_detail][address_details_1][residental_status]"));
				int i = re.size();       
				System.out.println(i);
				re.get(0).click();
				Thread.sleep(200);
				
				//Duration at Address
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_address][year]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_address][year]")).sendKeys("4");
				Thread.sleep(200);
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_address][month]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_address][month]")).sendKeys("2");
				Thread.sleep(200);
				
				//Previous Address (if less than 3 years in current) - Address
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][address]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][address]")).sendKeys("2");
				Thread.sleep(200);
				
				//Suburb
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][suburb]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][suburb]")).sendKeys("Deccan");
				Thread.sleep(200);

				//State / Territory
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][state_territory]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][state_territory]")).sendKeys("Maharashtra");
				Thread.sleep(200);
				
				//Postcode
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][postcode]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][postcode]")).sendKeys("46136");
				Thread.sleep(200);
				
				//Duration at Address
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][year]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][year]")).sendKeys("2");
				Thread.sleep(200);
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][month]")).clear();
				driver.findElement(By.name("data[guarantor_detail][address_details_1][duration_at_pre_address][month]")).sendKeys("2");
				Thread.sleep(200);
				
		
		
		
		
		
		//Guarantor 2 – Personal Details - First Name 
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[1]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[1]/div/input")).sendKeys("Shankar");
		Thread.sleep(200);
		
		//Second Name 
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[2]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[2]/div/input")).sendKeys("Pandu");
		Thread.sleep(200);
		
		//Surname
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[3]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[3]/div/input")).sendKeys("Patil");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[4]/div/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[4]/div/div[1]/input")).sendKeys("Aundh");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[4]/div/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[4]/div/div[2]/input")).sendKeys("Maharashtra");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[4]/div/div[3]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[4]/div/div[3]/input")).sendKeys("457821");
		Thread.sleep(200);
		
		//Duration at Address
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[5]/div[2]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[5]/div[2]/div[1]/input")).sendKeys("5");
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[5]/div[2]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[5]/div[2]/div[2]/input")).sendKeys("2");
		Thread.sleep(200);
		
		//Gender - male 
		//driver.findElement(By.xpath("")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[7]/div[3]/div/div/div[2]/div[6]/div[1]/div/div[2]/label[1]/input")).click();
		Thread.sleep(200);
		
		//Privacy Consent - 
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[7]/div[3]/div/div/div[2]/div[6]/div[2]/div/div[2]/label[1]/input")).click();
		//driver.findElement(By.xpath("")).sendKeys("");
		Thread.sleep(200);
		
		//dob
		WebElement dob6 = driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[7]/div[3]/div/div/div[2]/div[7]/div/div/div/input"));
		dob6.clear();
		dob6.sendKeys("08/01/1992");
		Thread.sleep(200);
		dob6.sendKeys(Keys.TAB);
		
		//Country of Citizenship
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[8]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[8]/div/input")).sendKeys("Indian");
		Thread.sleep(200);
		
		//Other Country of Citizenship (if applicable
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[9]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[9]/div/input")).sendKeys("No");
		Thread.sleep(200);
		
		//Marital Status
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[10]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[10]/div/input")).sendKeys("Single");
		Thread.sleep(200);
		
		//Number of Dependents
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[11]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[11]/div/input")).sendKeys("3");
		Thread.sleep(200);
		
		////Drivers Licence Number
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[12]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[12]/div/input")).sendKeys("3");
		Thread.sleep(200);           
		
		//Address
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[13]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[13]/div[1]/input")).sendKeys("Aundh, Pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[13]/div[2]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[13]/div[2]/div[1]/input")).sendKeys("Aundh");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[13]/div[2]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[13]/div[2]/div[2]/input")).sendKeys("ADP trading");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[13]/div[2]/div[3]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[13]/div[2]/div[3]/input")).sendKeys("32156");
		Thread.sleep(200);
		
		//Phone
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[14]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[14]/div/input")).sendKeys("8945561245");
		Thread.sleep(200);
		
		//Phone (business)
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[15]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[15]/div/input")).sendKeys("7889455612");
		Thread.sleep(200);
		
		//Mobile
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[16]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[16]/div/input")).sendKeys("MD");
		Thread.sleep(200);
		
		//Residential Status
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[7]/div[3]/div/div/div[2]/div[17]/div[1]/div[2]/label[1]/input")).click();
		Thread.sleep(200);
		
		//Duration of Address
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[17]/div[3]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[17]/div[3]/div[1]/input")).sendKeys("1");
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[17]/div[3]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[17]/div[3]/div[2]/input")).sendKeys("2");
		Thread.sleep(200);
		
		//Address
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[1]/input")).sendKeys("Kharadi, Pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[2]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[2]/div[1]/input")).sendKeys("Kharadi");
		Thread.sleep(200);
		
		//State / Territor
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[2]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[2]/div[2]/input")).sendKeys("Maharashtra");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[2]/div[3]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[2]/div[3]/input")).sendKeys("215462");
		Thread.sleep(200);
		
		//Duration at Address
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[4]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[4]/div[1]/input")).sendKeys("2");
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[4]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[2]/div[18]/div[4]/div[2]/input")).sendKeys("4");
		Thread.sleep(200);
		
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-7']/div[4]/div/button[2]")).click();
		Thread.sleep(2000);
		
		
		
		
		//Guarantor 1 – Employment Details
		List <WebElement> e41 = driver.findElements(By.name("data[guarantor_emp_detail_1][period]"));
		int i41 = e41.size();       
		System.out.println(i41);
		e41.get(0).click();
		Thread.sleep(200);	
		
		//Occupation
		driver.findElement(By.name("data[guarantor_emp_detail_1][occupation]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][occupation]")).sendKeys("Job");
		Thread.sleep(200);
		//Name of Employer
		driver.findElement(By.name("data[guarantor_emp_detail_1][name_employer]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][name_employer]")).sendKeys("ABV");
		Thread.sleep(200);
		//Address
		driver.findElement(By.name("data[guarantor_emp_detail_1][addess]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][addess]")).sendKeys("Camp, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[guarantor_emp_detail_1][suburb]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[guarantor_emp_detail_1][state_territory]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[guarantor_emp_detail_1][postcode]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][postcode]")).sendKeys("55123");
		Thread.sleep(200);
		//Phone
		driver.findElement(By.name("data[guarantor_emp_detail_1][phone]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][phone]")).sendKeys("7894561232");
		Thread.sleep(200);
		//Contact Name
		driver.findElement(By.name("data[guarantor_emp_detail_1][contact_name]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][contact_name]")).sendKeys("Sameer");
		Thread.sleep(200);
		//Position
		driver.findElement(By.name("data[guarantor_emp_detail_1][position]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][position]")).sendKeys("MD");
		Thread.sleep(200);
		//Duration of Employment
		driver.findElement(By.name("data[guarantor_emp_detail_1][duration_at_employment][year]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][duration_at_employment][year]")).sendKeys("2");
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_emp_detail_1][duration_at_employment][month]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][duration_at_employment][month]")).sendKeys("5");
		Thread.sleep(200);
		//Occupation
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[1]/div/div[2]/div[5]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[1]/div/div[2]/div[5]/div[1]/input")).sendKeys("Job");
		Thread.sleep(200);
		//Employer
		driver.findElement(By.name("data[guarantor_emp_detail_1][employer]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][employer]")).sendKeys("ASD");
		Thread.sleep(200);          
		//Duration of Employment
		driver.findElement(By.name("data[guarantor_emp_detail_1][duration_at_previous_address][year]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][duration_at_previous_address][year]")).sendKeys("2");
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_emp_detail_1][duration_at_previous_address][month]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_1][duration_at_previous_address][month]")).sendKeys("5");
		Thread.sleep(200);
		//ABN
		driver.findElement(By.name("data[self_employed_1][abn]")).clear();
		driver.findElement(By.name("data[self_employed_1][abn]")).sendKeys("452");
		Thread.sleep(200);
		//Trading Name
		driver.findElement(By.name("data[self_employed_1][trading_name]")).clear();
		driver.findElement(By.name("data[self_employed_1][trading_name]")).sendKeys("KTRUST");
		Thread.sleep(200);
		//Trust Name
		driver.findElement(By.name("data[self_employed_1][trust_name]")).clear();
		driver.findElement(By.name("data[self_employed_1][trust_name]")).sendKeys("K");
		Thread.sleep(200);
		//Type of Trust (ie. Family Trust, Discretionary Trust etc)
		driver.findElement(By.name("data[self_employed_1][type_trust]")).clear();
		driver.findElement(By.name("data[self_employed_1][type_trust]")).sendKeys("Family");
		Thread.sleep(200);
		//Full Name of the Settlor of the Trust
		driver.findElement(By.name("data[self_employed_1][full_name_settler]")).clear();
		driver.findElement(By.name("data[self_employed_1][full_name_settler]")).sendKeys("KK");
		Thread.sleep(200);
		//Nature of Business
		driver.findElement(By.name("data[self_employed_1][nature_business]")).clear();
		driver.findElement(By.name("data[self_employed_1][nature_business]")).sendKeys("Trade");
		Thread.sleep(200);
		//Address
		driver.findElement(By.name("data[self_employed_1][address]")).clear();
		driver.findElement(By.name("data[self_employed_1][address]")).sendKeys("DP Road");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[self_employed_1][suburb]")).clear();
		driver.findElement(By.name("data[self_employed_1][suburb]")).sendKeys("DP");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[self_employed_1][state_territor]")).clear();
		driver.findElement(By.name("data[self_employed_1][state_territor]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[self_employed_1][postcode]")).clear();
		driver.findElement(By.name("data[self_employed_1][postcode]")).sendKeys("564512");
		Thread.sleep(200);
		//Phone Number
		driver.findElement(By.name("data[self_employed_1][phone_number]")).clear();
		driver.findElement(By.name("data[self_employed_1][phone_number]")).sendKeys("8512369451");
		Thread.sleep(200);
		
		
		//Guarantor 2 – Employment Details - Occupation
		List <WebElement> e42 = driver.findElements(By.name("data[guarantor_emp_detail_2][period]"));
		int i42 = e42.size();       
		System.out.println(i42);
		e42.get(0).click();
		Thread.sleep(200);	
		
		//Occupation
		driver.findElement(By.name("data[guarantor_emp_detail_2][occupation]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_2][occupation]")).sendKeys("Job");
		Thread.sleep(200);
		//Name of Employer
		driver.findElement(By.name("data[guarantor_emp_detail_2][name_employer]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_2][name_employer]")).sendKeys("ABV");
		Thread.sleep(200);
		//Address
		driver.findElement(By.name("data[guarantor_emp_detail_2][addess]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_2][addess]")).sendKeys("Camp, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[guarantor_emp_detail_2][suburb]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_2][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[guarantor_emp_detail_2][state_territory]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_2][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[guarantor_emp_detail_2][postcode]")).clear();
		driver.findElement(By.name("data[guarantor_emp_detail_2][postcode]")).sendKeys("55123");
		Thread.sleep(200);
		//Phone
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[2]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[2]/div/input")).sendKeys("7894561232");
		Thread.sleep(200);
		//Contact Name
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[3]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[3]/div/input")).sendKeys("Sameer");
		Thread.sleep(200);
		//Position
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[4]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[4]/div[1]/input")).sendKeys("MD");
		Thread.sleep(200);
		//Duration of Employment
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[4]/div[3]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[4]/div[3]/div[1]/input")).sendKeys("2");
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[4]/div[3]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[4]/div[3]/div[2]/input")).sendKeys("5");
		Thread.sleep(200);
		//Occupation
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[5]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[5]/div[1]/input")).sendKeys("Job");
		Thread.sleep(200);
		//Employer
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[5]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[5]/div[2]/input")).sendKeys("ASD");
		Thread.sleep(200);          
		//Duration of Employment
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[5]/div[4]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[5]/div[4]/div[1]/input")).sendKeys("2");
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[5]/div[4]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[5]/div[4]/div[2]/input")).sendKeys("5");
		Thread.sleep(200);
		//ABN
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[6]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[6]/div/input")).sendKeys("452");
		Thread.sleep(200);
		//Trading Name
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[7]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[7]/div/input")).sendKeys("KTRUST");
		Thread.sleep(200);
		//Trust Name
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[8]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[8]/div/input")).sendKeys("K");
		Thread.sleep(200);
		//Type of Trust (ie. Family Trust, Discretionary Trust etc)
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[1]/input")).sendKeys("Family");
		Thread.sleep(200);
		//Full Name of the Settlor of the Trust
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[2]/input")).sendKeys("Trade");
		Thread.sleep(200);
		////Nature of Business
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[3]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[3]/input")).sendKeys("KK");
		Thread.sleep(200);
		//Address
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[4]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[4]/input")).sendKeys("DP Road");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[5]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[5]/div[1]/input")).sendKeys("DP");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[5]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[5]/div[2]/input")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[5]/div[3]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[5]/div[3]/input")).sendKeys("564512");
		Thread.sleep(200);
		//Phone Number
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[6]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-8']/div[1]/div[2]/div/div[2]/div[9]/div[6]/input")).sendKeys("8512369451");
		Thread.sleep(200);
		
		//next 
		driver.findElement(By.xpath(".//*[@id='step-8']/div[2]/div/button[2]")).click();
		Thread.sleep(2000);
		
		
		
		
		
		
		
		
		//Monthly Income Guarantor 1 - Monthly Income (after tax)================================================================================== 1
		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income]")).clear();
		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income]")).sendKeys("2000");
		Thread.sleep(200);
		
		//OR Net Profit (before tax) yearly
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[1]/div/div[2]/div[2]/div[2]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[1]/div/div[2]/div[2]/div[2]/div/input")).sendKeys("120");
		Thread.sleep(200);           

		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income][month_to]")).clear();
		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income][month_to]")).sendKeys("21");
		Thread.sleep(200);
		
		//Date 
		WebElement toDateBox= driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income][date]"));
		toDateBox.clear();
		toDateBox.sendKeys("01/01/1990");
		Thread.sleep(200);
		toDateBox.sendKeys(Keys.TAB);
		
		//Depreciation
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[1]/div/div[2]/div[4]/div[2]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[1]/div/div[2]/div[4]/div[2]/div/input")).sendKeys("10");
		Thread.sleep(200);

		//Overtime
		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income][overtime]")).clear();
		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income][overtime]")).sendKeys("10");
		Thread.sleep(200);
		

		//Other Income (Rental/Commission
		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income][other_income]")).clear();
		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income][other_income]")).sendKeys("10");
		Thread.sleep(200);          
		
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[1]/div/div[2]/div[7]/div/textarea")).clear();
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[1]/div/div[2]/div[7]/div/textarea")).sendKeys("45");
		Thread.sleep(200);
		
		//Monthly Income Guarantor 2 - Monthly Income (after tax)================================================================================== 1
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/input")).sendKeys("2000");
				Thread.sleep(200);
				
				//OR Net Profit (before tax) yearly
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[2]/div[2]/div/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[2]/div[2]/div/input")).sendKeys("120");
				Thread.sleep(200);           

				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[3]/div[1]/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[3]/div[1]/input")).sendKeys("21");
				Thread.sleep(200);
				
				//Date 
				WebElement toDateBox1= driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[3]/div[3]/input"));
				toDateBox1.clear();
				toDateBox1.sendKeys("01/01/1990");
				Thread.sleep(200);
				toDateBox1.sendKeys(Keys.TAB);
				
				//Depreciation
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[4]/div[2]/div/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[4]/div[2]/div/input")).sendKeys("10");
				Thread.sleep(200);

				//Overtime
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[5]/div[2]/div/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[5]/div[2]/div/input")).sendKeys("10");
				Thread.sleep(200);
				

				//Other Income (Rental/Commission
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[6]/div[2]/div/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[6]/div[2]/div/input")).sendKeys("10");
				Thread.sleep(200);          
				
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[7]/div/textarea")).clear();
				driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[2]/div/div[2]/div[7]/div/textarea")).sendKeys("45");
				Thread.sleep(200);
		
		//Monthly Expenditure (combined) - Mortgage/Rent/Board====================================================================4
				driver.findElement(By.name("data[guarantor_monthly_expenditure][mortagage]")).clear();
				driver.findElement(By.name("data[guarantor_monthly_expenditure][mortagage]")).sendKeys("1000");
				Thread.sleep(200);
				
				//Other Mortgage Payments
				driver.findElement(By.name("data[guarantor_monthly_expenditure][other_mortage]")).clear();
				driver.findElement(By.name("data[guarantor_monthly_expenditure][other_mortage]")).sendKeys("20");
				Thread.sleep(200);
				
				//Credit Cards
				driver.findElement(By.name("data[guarantor_monthly_expenditure][credit_card]")).clear();
				driver.findElement(By.name("data[guarantor_monthly_expenditure][credit_card]")).sendKeys("10");
				Thread.sleep(200);
				
				//Vehicle Expenses
				driver.findElement(By.name("data[guarantor_monthly_expenditure][vehicle_expenses]")).clear();
				driver.findElement(By.name("data[guarantor_monthly_expenditure][vehicle_expenses]")).sendKeys("10");
				Thread.sleep(200);
				
				//Home Expenses (utilities)
				driver.findElement(By.name("data[guarantor_monthly_expenditure][home_expenes]")).clear();
				driver.findElement(By.name("data[guarantor_monthly_expenditure][home_expenes]")).sendKeys("20");
				Thread.sleep(200);
				
				//Other Expenditure
				driver.findElement(By.name("data[guarantor_monthly_expenditure][[other_expenes]")).clear();
				driver.findElement(By.name("data[guarantor_monthly_expenditure][[other_expenes]")).sendKeys("10");
				Thread.sleep(200);
				
				driver.findElement(By.name("data[guarantor_monthly_expenditure][detail_other_expenes]")).clear();
				driver.findElement(By.name("data[guarantor_monthly_expenditure][detail_other_expenes]")).sendKeys("20");
				Thread.sleep(200);
		
				
		
		//Personal Assets (combined) - Cash at Bank (excl. deposit)=========================================================================2
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[4]/div/div[2]/div[1]/div[2]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[4]/div/div[2]/div[1]/div[2]/div/input")).sendKeys("45");
		Thread.sleep(200);
		
		//Home Value
		driver.findElement(By.name("data[guarantor_personal_assets][home_value]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][home_value]")).sendKeys("20");
		Thread.sleep(200);
		
		//Other Property Value
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[4]/div/div[2]/div[3]/div[2]/div/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-9']/div[2]/div[4]/div/div[2]/div[3]/div[2]/div/input")).sendKeys("10");
		Thread.sleep(200);
		
		//Motor Vehicle(s)
		driver.findElement(By.name("data[guarantor_personal_assets][motor_vehicle]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][motor_vehicle]")).sendKeys("20");
		Thread.sleep(200);
		
		//Plant & Equipment
		driver.findElement(By.name("data[guarantor_personal_assets][plant_equipment]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][plant_equipment]")).sendKeys("210");
		Thread.sleep(200);
		
		//Household Effects
		driver.findElement(By.name("data[guarantor_personal_assets][household_effect]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][household_effect]")).sendKeys("20");
		Thread.sleep(200);
		
		//Business Assets
		driver.findElement(By.name("data[guarantor_personal_assets][business_assets]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][business_assets]")).sendKeys("10");
		Thread.sleep(200);
		
		//Debentures
		driver.findElement(By.name("data[guarantor_personal_assets][debentures]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][debentures]")).sendKeys("40");
		Thread.sleep(200);
		
		//Term Deposit
		driver.findElement(By.name("data[guarantor_personal_assets][term_deposit]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][term_deposit]")).sendKeys("50");
		Thread.sleep(200);
		
		//Debtors
		driver.findElement(By.name("data[guarantor_personal_assets][debtors]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][debtors]")).sendKeys("10");
		Thread.sleep(200);
		
		//Other Assets 
		driver.findElement(By.name("data[guarantor_personal_assets][other_assets]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][other_assets]")).sendKeys("20");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[guarantor_personal_assets][details_other_assets]")).clear();
		driver.findElement(By.name("data[guarantor_personal_assets][details_other_assets]")).sendKeys("5");
		Thread.sleep(200);
		
		
	
		//Personal Liabilities (combined) - Home Mortgage=============================================================5
				driver.findElement(By.name("data[guarantor_personal_liabilities][home_mortage]")).clear();
				driver.findElement(By.name("data[guarantor_personal_liabilities][home_mortage]")).sendKeys("10");
				Thread.sleep(200);
				
				//Other Property Mortgage
				driver.findElement(By.name("data[guarantor_personal_liabilities][property_mortage]")).clear();
				driver.findElement(By.name("data[guarantor_personal_liabilities][property_mortage]")).sendKeys("10");
				Thread.sleep(200);
				
				//Creditors
				driver.findElement(By.name("data[guarantor_personal_liabilities][creditors]")).clear();
				driver.findElement(By.name("data[guarantor_personal_liabilities][creditors]")).sendKeys("10");
				Thread.sleep(200);
				
				//Credit Card Limit
				driver.findElement(By.name("data[guarantor_personal_liabilities][credit_card_limit]]")).clear();
				driver.findElement(By.name("data[guarantor_personal_liabilities][credit_card_limit]]")).sendKeys("10");
				Thread.sleep(200);
				
				//Overdraft Limit
				driver.findElement(By.name("data[guarantor_personal_liabilities][overdraft_limit]")).clear();
				driver.findElement(By.name("data[guarantor_personal_liabilities][overdraft_limit]")).sendKeys("10");
				Thread.sleep(200);
				
				//Loans Outstanding
				driver.findElement(By.name("data[guarantor_personal_liabilities][loans_outstanding]")).clear();
				driver.findElement(By.name("data[guarantor_personal_liabilities][loans_outstanding]")).sendKeys("10");
				Thread.sleep(200);
				
				//Other Liabilities
				driver.findElement(By.name("data[guarantor_personal_liabilities][other_liabilities]")).clear();
				driver.findElement(By.name("data[guarantor_personal_liabilities][other_liabilities]")).sendKeys("10");
				Thread.sleep(200);
				
				driver.findElement(By.name("data[guarantor_personal_liabilities][detail_other_liabilities]")).clear();
				driver.findElement(By.name("data[guarantor_personal_liabilities][detail_other_liabilities]")).sendKeys("data");
				Thread.sleep(200);
				
				
		
		
		/*//Monthly Income Guarantor 1 - Monthly Income (after tax) =============================================================================================3
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[10]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[10]/div[2]/div/input")).sendKeys("55");
		Thread.sleep(200);
		
		//OR Net Profit (before tax) yearly
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[11]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[11]/div[2]/div/input")).sendKeys("50");
		Thread.sleep(200);
		
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[12]/div[1]/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[12]/div[1]/input")).sendKeys("50");
		Thread.sleep(200);
		
		//Date Established
				WebElement ox= driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[12]/div[3]/input"));
				ox.clear();
				ox.sendKeys("01/01/2016");
				Thread.sleep(200);
				ox.sendKeys(Keys.TAB);
		
				//Depreciation
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[13]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[13]/div[2]/div/input")).sendKeys("20");
		Thread.sleep(200);
		
		//Overtime
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[14]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[14]/div[2]/div/input")).sendKeys("65");
		Thread.sleep(200);
		
		//Other Income (Rental/Commission)*
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[15]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[15]/div[2]/div/input")).sendKeys("20");
		Thread.sleep(200);
		
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[16]/div/textarea")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[16]/div/textarea")).sendKeys("40");
		Thread.sleep(200);
		
		
		
		//Monthly Income Guarantor 1 - Monthly Income (after tax)
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[10]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[10]/div[2]/div/input")).sendKeys("10");
		Thread.sleep(200);
		
		//OR Net Profit (before tax) yearly
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[11]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[11]/div[2]/div/input")).sendKeys("10");
		Thread.sleep(200);
		
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[12]/div[1]/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[12]/div[1]/input")).sendKeys("20");
		Thread.sleep(200);
		
		//Date Established
		WebElement dDateBox= driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[12]/div[3]/input"));
		dDateBox.clear();
		dDateBox.sendKeys("01/01/2016");
		Thread.sleep(200);
		dDateBox.sendKeys(Keys.TAB);
		
		//Depreciation
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[13]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[13]/div[2]/div/input")).sendKeys("20");
		Thread.sleep(200);
		
		//Overtime
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[14]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[14]/div[2]/div/input")).sendKeys("20");
		Thread.sleep(200);
		
		//Other Income (Rental/Commission)* 	
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[15]/div[2]/div/input")).clear();
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/div/form/div[9]/div[2]/div[1]/div[15]/div[2]/div/input")).sendKeys("20");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income][other_income_detail]")).clear();
		driver.findElement(By.name("data[guarantor_finance_details][montly_inco_g1][montly_income][other_income_detail]")).sendKeys("10");
		Thread.sleep(200);
		
		
		
		
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,900)", "");
		Thread.sleep(2000);*/
		
		//Financier
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val1]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val1]")).sendKeys("10");//-------1
		Thread.sleep(200);               
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val2]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val2]")).sendKeys("20");//-------2
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val3]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val3]")).sendKeys("30");//-------3
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val4]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val4]")).sendKeys("40");//-------4
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val5]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val5]")).sendKeys("50");//-------5
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val6]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val6]")).sendKeys("60");//-------6
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val7]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val7]")).sendKeys("70");//-------7
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val8]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val8]")).sendKeys("80");//-------8
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val9]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][financier][val9]")).sendKeys("90");//-------9
		Thread.sleep(200);
		
		//Instalment $
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val1]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val1]")).sendKeys("10");//-------1
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val2]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val2]")).sendKeys("20");//-------2
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val3]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val3]")).sendKeys("30");//-------3
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val4]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val4]")).sendKeys("40");//-------4
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val5]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val5]")).sendKeys("50");//-------5
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val6]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val6]")).sendKeys("60");//-------6
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val7]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val7]")).sendKeys("70");//-------7
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val8]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val8]")).sendKeys("80");//-------8
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val9]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][instalment][val9]")).sendKeys("90");//-------9
		Thread.sleep(200);
		
		//Commencement Date
		WebElement date11 = driver.findElement(By.name("data[guarantor_detail_loan][commencement_date][val1]"));//--------1
		date11.clear();
		date11.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date22 = driver.findElement(By.name("data[guarantor_detail_loan][commencement_date][val2]"));//--------2
		date22.clear();
		date22.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date33 = driver.findElement(By.name("data[guarantor_detail_loan][commencement_date][val3]"));//--------3
		date33.clear();
		date33.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date44 = driver.findElement(By.name("data[guarantor_detail_loan][commencement_date][val4]"));//--------4
		date44.clear();
		date44.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date55 = driver.findElement(By.name("data[guarantor_detail_loan][commencement_date][val5]"));//--------5
		date55.clear();
		date55.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date66 = driver.findElement(By.name("data[guarantor_detail_loan][commencement_date][val6]"));//--------6
		date66.clear();
		date66.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date77 = driver.findElement(By.name("data[guarantor_detail_loan][commencement_date][val7]"));//--------7
		date77.clear();
		date77.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date88 = driver.findElement(By.name("data[guarantor_detail_loan][commencement_date][val8]"));//--------8
		date88.clear();
		date88.sendKeys("01/01/2016");
		Thread.sleep(200);
		WebElement date99 = driver.findElement(By.name("data[guarantor_detail_loan][commencement_date][val9]"));//--------9
		date99.clear();
		date99.sendKeys("01/01/2016");
		Thread.sleep(200);
		
		//Term (months)
		driver.findElement(By.name("data[guarantor_detail_loan][term][val1]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][term][val1]")).sendKeys("10");//-------1
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][term][val2]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][term][val2]")).sendKeys("20");//-------2
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][term][val3]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][term][val3]")).sendKeys("30");//-------3
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][term][val4]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][term][val4]")).sendKeys("40");//-------4
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][term][val5]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][term][val5]")).sendKeys("50");//-------5
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][term][val6]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][term][val6]")).sendKeys("60");//-------6
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan]term][val7]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan]term][val7]")).sendKeys("70");//-------7
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][term][val8]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][term][val8]")).sendKeys("80");//-------8
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][term][val9]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][term][val9]")).sendKeys("90");//-------9
		Thread.sleep(200);
		
		//To be paid out (Y/N)
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val1]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val1]")).sendKeys("Y");//-------1
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val2]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val2]")).sendKeys("Y");//-------2
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val3]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val3]")).sendKeys("Y");//-------3
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val4]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val4]")).sendKeys("Y");//-------4
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val5]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val5]")).sendKeys("Y");//-------5
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val6]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val6]")).sendKeys("Y");//-------6
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val7]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val7]")).sendKeys("Y");//-------7
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val8]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val8]")).sendKeys("Y");//-------8
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val9]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][to_be_paid][val9]")).sendKeys("Y");//-------9
		Thread.sleep(200);
		
		//Contract No. (if Esanda)
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val1]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val1]")).sendKeys("10");//-------1
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val2]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val2]")).sendKeys("20");//-------2
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val3]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val3]")).sendKeys("30");//-------3
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val4]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val4]")).sendKeys("40");//-------4
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val5]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val5]")).sendKeys("50");//-------5
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val6]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val6]")).sendKeys("60");//-------6
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val7]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val7]")).sendKeys("70");//-------7
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val8]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val8]")).sendKeys("80");//-------8
		Thread.sleep(200);
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val9]")).clear();
		driver.findElement(By.name("data[guarantor_detail_loan][contract_no][val9]")).sendKeys("90");//-------9
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-9']/div[4]/div/button[2]")).click();
		Thread.sleep(2000);          
		
		//COMPANY GUARANTOR DETAILS (IF APPLICABLE) - Company Name
		driver.findElement(By.name("data[company_guarentor_details][company_name]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][company_name]")).sendKeys("Tom Trader");
		Thread.sleep(200);
		
		//ABN
		driver.findElement(By.name("data[company_guarentor_details][abn]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][abn]")).sendKeys("04512");
		Thread.sleep(200);
		
		//Trading Name
		driver.findElement(By.name("data[company_guarentor_details][trading_name]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][trading_name]")).sendKeys("Tom");
		Thread.sleep(200);
		
		//Nature of Business
		driver.findElement(By.name("data[company_guarentor_details][nature_business]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][nature_business]")).sendKeys("Food");
		Thread.sleep(200);
		
		//Address
		driver.findElement(By.name("data[company_guarentor_details][address]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][address]")).sendKeys("ABC");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[company_guarentor_details][suburb]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][suburb]")).sendKeys("Pune");
		Thread.sleep(200);
		
		//Phone Number
		driver.findElement(By.name("data[company_guarentor_details][phone_number]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][phone_number]")).sendKeys("8956452312");
		Thread.sleep(200);
		
		//Company Type
		driver.findElement(By.name("data[company_guarentor_details][company_type]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][company_type]")).sendKeys("Private");
		Thread.sleep(200);
		
		//ACN
		driver.findElement(By.name("data[company_guarentor_details][acn]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][acn]")).sendKeys("8512");
		Thread.sleep(200);
		
		//Trust Name
		driver.findElement(By.name("data[company_guarentor_details][trust_name]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][trust_name]")).sendKeys("Tom");
		Thread.sleep(200);
		
		//Type of Trust (ie. Family Trust, Discretionary Trust etc.)
		driver.findElement(By .name("data[company_guarentor_details][type_trust]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][type_trust]")).sendKeys("Family");
		Thread.sleep(200);
		
		//Full Name of the Settlor of the Trust
		driver.findElement(By.name("data[company_guarentor_details][fullname_settlor]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][fullname_settlor]")).sendKeys("Maya");
		Thread.sleep(200);
		
		//State / Territory
		driver.findElement(By.name("data[company_guarentor_details][state_territory]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		
		//Postcode
		driver.findElement(By.name("data[company_guarentor_details][postcode]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][postcode]")).sendKeys("875421");
		Thread.sleep(200);
		
		//Fax Number
		driver.findElement(By.name("data[company_guarentor_details][fax_number]")).clear();
		driver.findElement(By.name("data[company_guarentor_details][fax_number]")).sendKeys("5612458945");
		Thread.sleep(200);
		
		//update
		driver.findElement(By.xpath(".//*[@id='anzUpdatebtn']")).click();
		Thread.sleep(60000);         
		
		String actual = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
		System.out.println(actual);                           
		String expected = "TestApplication ANZ";
		
		Assert.assertEquals(actual, expected);
		
		System.out.println("Consumer form edit successfull form ANZ option");
		
		System.out.println("====================================================================");
 	}
 	
 	public void editPersonalLoanApplicationWithLiberty() throws InterruptedException{
 		
 		System.out.println("Edit functionality of Personal loan application with Liberty option");
 		
 		//click dashboard
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
 		Thread.sleep(2000);
 		
 		//click applications
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[6]/a")).click();
 		Thread.sleep(3000);
 		
 		//click on view all 
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[6]/ul/li[3]/a")).click();
 		Thread.sleep(3000);          
 		
 		//edit
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[9]/button[2]")).click();
 		Thread.sleep(2000);
 		
 		//Liberty
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[4]/div/div/div/div/div/a[2]")).click();
 		Thread.sleep(2000);
 		
 		//Tab-1
 		//CONTACT DETAILS - To - Name
 		driver.findElement(By.name("data[contact_details][to][name]")).clear();
		driver.findElement(By.name("data[contact_details][to][name]")).sendKeys("Gururaj");
		Thread.sleep(200);
		
		//FAX
		driver.findElement(By.name("data[contact_details][to][fax]")).clear();
		driver.findElement(By.name("data[contact_details][to][fax]")).sendKeys("8945784512");
		Thread.sleep(200);
	
		//From - Name
		driver.findElement(By.name("data[contact_details][from][name]")).clear();
		driver.findElement(By.name("data[contact_details][from][name]")).sendKeys("Abhi");
		Thread.sleep(200);
		
		//Company
		driver.findElement(By.name("data[contact_details][from][company]")).clear();
		driver.findElement(By.name("data[contact_details][from][company]")).sendKeys("KKSOFT");
		Thread.sleep(200);
		
		//Phone
		driver.findElement(By.name("data[contact_details][from][phone]")).clear();
		driver.findElement(By.name("data[contact_details][from][phone]")).sendKeys("4556788945");
		Thread.sleep(200);
		
		//Email
		driver.findElement(By.name("data[contact_details][from][email]")).clear();
		driver.findElement(By.name("data[contact_details][from][email]")).sendKeys("gurutest@nadsoft.com");
		Thread.sleep(200);
		
		//Date
		WebElement toDateBox= driver.findElement(By.name("data[contact_details][from][date]"));
		toDateBox.clear();
		toDateBox.sendKeys("01/01/2016");
		Thread.sleep(200);
		toDateBox.sendKeys(Keys.TAB);
		
		//Pages
		driver.findElement(By.name("data[contact_details][from][pages]")).clear();
		driver.findElement(By.name("data[contact_details][from][pages]")).sendKeys("125");
		Thread.sleep(200);
		
		//APPLICATION CHECKLIST & NOTES
		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div[2]/div/div[2]/div/div[1]/div/div[1]/label/div/ins")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div[2]/div/div[2]/div/div[1]/div/div[2]/label/div/ins")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div[2]/div/div[2]/div/div[3]/div/div/label/div/ins")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div[2]/div/div[2]/div/div[2]/div/div[1]/label/div/ins")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div[2]/div/div[2]/div/div[2]/div/div[2]/label/div/ins")).click();
		Thread.sleep(200);
		
		//scroll down
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,900)", "");
				Thread.sleep(2000);
				
		//FINANCING REQUIREMENT - Cash Price
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Cash-Price]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Cash-Price]")).sendKeys("100");
		Thread.sleep(200);
		
		//Cash deposit
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Cash-deposit]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Cash-deposit]")).sendKeys("100");
		Thread.sleep(200);
		
		//Net trade-in
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Net-trade-in]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Net-trade-in]")).sendKeys("100");
		Thread.sleep(200);
 	
		//Comprehensive
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Comprehensive]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Comprehensive]")).sendKeys("100");
		Thread.sleep(200);
		
		//CCI
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][CCI]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][CCI]")).sendKeys("100");
		Thread.sleep(200);
 	
		//Ext. Warranty
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Warranty]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Warranty]")).sendKeys("100");
		Thread.sleep(200);
		
		//ISP + Gap
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][ISP-Gap]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][ISP-Gap]")).sendKeys("100");
		Thread.sleep(200);
 	
		//Application fee
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Application-fee]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Application-fee]")).sendKeys("100");
		Thread.sleep(200);
		
		//Other
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Other]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Other]")).sendKeys("100");
		Thread.sleep(200);
 	
		//= Loan amount
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Loan-amount]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][Loan-amount]")).sendKeys("100");
		Thread.sleep(200);
		
		//GST on purchase
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][on-purchase]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][finacing_requirement][on-purchase]")).sendKeys("100");
		Thread.sleep(200);
 	
		//TRADE-IN DETAILS (IF APPLICABLE)
		//Year
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Year]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Year]")).sendKeys("2016");
		Thread.sleep(200);
		
		//Make
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][make]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][make]")).sendKeys("ME");
		Thread.sleep(200);
 	
		//Model
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][model]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][model]")).sendKeys("GT50");
		Thread.sleep(200);
		
		//Trade in value
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Trade-in-value]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Trade-in-value]")).sendKeys("100");
		Thread.sleep(200);
 	
		//Amount owing
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Amount-owing]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Amount-owing]")).sendKeys("100");
		Thread.sleep(200);
		
		//Net trade-in
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Net-trade-in]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Net-trade-in]")).sendKeys("100");
		Thread.sleep(200);
 	
		//Lender
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Lender]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Lender]")).sendKeys("2");
		Thread.sleep(200);
		
		//Payment
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Payment]")).clear();
		driver.findElement(By.name("data[finacing_requirement_trade_in_details][trade-in-details][Payment]")).sendKeys("1000");
		Thread.sleep(200);
		
		//scroll down
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,800)", "");
				Thread.sleep(2000);
 	
		//PRODUCT SELECTION
		//Term		
		driver.findElement(By.name("data[product-selection][term]")).clear();
		driver.findElement(By.name("data[product-selection][term]")).sendKeys("5");
		Thread.sleep(200);
		
		//Vehicle use
		List <WebElement> e11 = driver.findElements(By.name("data[product-selection][Vehicle-use]"));
		int i11 = e11.size();       
		System.out.println(i11);
		e11.get(0).click();
		Thread.sleep(200);	
		
		//Balloon/residual
		driver.findElement(By.name("data[product-selection][balloon-residual]")).clear();
		driver.findElement(By.name("data[product-selection][balloon-residual]")).sendKeys("1000");
		Thread.sleep(200);
 	
		//Interest rate
		driver.findElement(By.name("data[product-selection][interest-rate]")).clear();
		driver.findElement(By.name("data[product-selection][interest-rate]")).sendKeys("50");
		Thread.sleep(200);
		
		//Verification
		List <WebElement> e12 = driver.findElements(By.name("data[product-selection][verification]"));
		int i12 = e12.size();       
		System.out.println(i12);
		e12.get(0).click();
		Thread.sleep(200);	
		
		//Vendor
		List <WebElement> e13 = driver.findElements(By.name("data[product-selection][vendor]"));
		int i13 = e13.size();       
		System.out.println(i13);
		e13.get(0).click();
		Thread.sleep(200);
		
		//Repayments
		List <WebElement> e14 = driver.findElements(By.name("data[product-selection][repayments]"));
		int i14 = e14.size();       
		System.out.println(i14);
		e14.get(0).click();
		Thread.sleep(200);
		
		//Next
		driver.findElement(By.xpath(".//*[@id='step-1']/div[6]/div/button")).click();
		Thread.sleep(2000);          
		
		//Tab-2 PERSONAL DETAILS - Title
		List <WebElement> e1 = driver.findElements(By.name("data[applicannt-information][applicant1][title]"));
		int i1 = e1.size();                                    
		System.out.println(i1);
		e1.get(0).click();
		Thread.sleep(200);
		
		//First/Middle name
		driver.findElement(By.name("data[applicannt-information][applicant1][firstname]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][firstname]")).sendKeys("Amit Pravin");
		Thread.sleep(200);
		
		//Surname
		driver.findElement(By.name("data[applicannt-information][applicant1][lastname]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][lastname]")).sendKeys("Shinde");
		Thread.sleep(200);
		
		//Date of birth
		WebElement toDateBox1= driver.findElement(By.name("data[applicannt-information][applicant1][dateofbirth]"));
		toDateBox1.clear();
		toDateBox1.sendKeys("01/01/1990");
		Thread.sleep(200);
		toDateBox1.sendKeys(Keys.TAB);
		
		//Marital status
		List <WebElement> e2 = driver.findElements(By.name("data[applicannt-information][applicant1][maritalstatus][]"));
		int i2 = e2.size();       
		System.out.println(i2);
		e2.get(0).click();
		Thread.sleep(200);
		
		//Driver’s license - State
		driver.findElement(By.name("data[applicannt-information][applicant1][driverslicense][State]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][driverslicense][State]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//No
		driver.findElement(By.name("data[applicannt-information][applicant1][driverslicense][no]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][driverslicense][no]")).sendKeys("7845124578");
		Thread.sleep(200);
		
		//Dependants - of
		driver.findElement(By.name("data[applicannt-information][applicant1][dependants][of]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][dependants][of]")).sendKeys("2");
		Thread.sleep(200);
		//Ages
		driver.findElement(By.name("data[applicannt-information][applicant1][dependants][Ages]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][dependants][Ages]")).sendKeys("1");
		Thread.sleep(200);
		
		//Contact information (2 minimum) - Home
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][home]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][home]")).sendKeys("9085748585");
		Thread.sleep(200);
		//Work
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][work]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][work]")).sendKeys("5645788945");
		Thread.sleep(200);
		//Mobile
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][mobile]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][mobile]")).sendKeys("7889457889");
		Thread.sleep(200);
		//Fax
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][fax]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][fax]")).sendKeys("4578894578");
		Thread.sleep(200);
		//Email
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][email]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][Contactin-formation][email]")).sendKeys("chandrakant@nadsoftdev.com");
		Thread.sleep(200);
		
		//Current home address - Street
		driver.findElement(By.name("data[applicannt-information][applicant1][current-home-address][Street]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][current-home-address][Street]")).sendKeys("MG Road");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[applicannt-information][applicant1][current-home-address][suburb]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][current-home-address][suburb]")).sendKeys("Kharadi");
		Thread.sleep(200);
		//Pcode
		driver.findElement(By.name("data[applicannt-information][applicant1][current-home-address][pcode]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][current-home-address][pcode]")).sendKeys("78452154");
		Thread.sleep(200);
		
		//Current residential status
		List <WebElement> e3 = driver.findElements(By.name("data[applicannt-information][applicant1][currentresidential][]"));
		int i3 = e3.size();       
		System.out.println(i3);
		e3.get(0).click();
		Thread.sleep(200);
		
		//Here since
		driver.findElement(By.name("data[applicannt-information][applicant1][current-home-address][here-since]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][current-home-address][here-since]")).sendKeys("2");
		Thread.sleep(200);
		
		//Applicant residency
		List <WebElement> e4 = driver.findElements(By.name("data[applicannt-information][applicant1][applicantresidential]"));
		int i4 = e4.size();       
		System.out.println(i4);
		e4.get(0).click();
		Thread.sleep(200);
		
		//Mortgagee/Real estate Agent/Landlord info  - Name
		driver.findElement(By.name("data[applicannt-information][applicant1][mortgagee][Name]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][mortgagee][Name]")).sendKeys("Vikas");
		Thread.sleep(200);
		//Address
		driver.findElement(By.name("data[applicannt-information][applicant1][mortgagee][Address]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][mortgagee][Address]")).sendKeys("Camp, Pune");
		Thread.sleep(200);
		//Phone
		driver.findElement(By.name("data[applicannt-information][applicant1][mortgagee][Phone]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][mortgagee][Phone]")).sendKeys("7889457845");
		Thread.sleep(200);
		//Previous residential Details (if less than 2 years at current residential address) - Street
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][street]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][street]")).sendKeys("Camp, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][suburb]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		//Pcode
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][pcode]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][pcode]")).sendKeys("124512");
		Thread.sleep(200);
		//Country
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][country]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][country]")).sendKeys("India");
		Thread.sleep(200);
		//From
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][from]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][previous-residential-details][from]")).sendKeys("02/2016");
		Thread.sleep(200);
		
		//Personal reference - Name
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative][Name]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative][Name]")).sendKeys("Sandip");
		Thread.sleep(200);
		//Address
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative][Address]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative][Address]")).sendKeys("WadgaunShri, Pune");
		Thread.sleep(200);
		//Phone
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative][Phone]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative][Phone]")).sendKeys("7889457845");
		Thread.sleep(200);
		//Relationship
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative][relationship]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative][relationship]")).sendKeys("Friend");
		Thread.sleep(200);
		
		//Nearest relative not living with applicant - Name
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative1][Name]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative1][Name]")).sendKeys("Sanajy");
		Thread.sleep(200);
		//Address
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative1][Address]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative1][Address]")).sendKeys("Katraj, Pune");
		Thread.sleep(200);
		//Phone
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative1][Phone]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative1][Phone]")).sendKeys("4589457812");
		Thread.sleep(200);
		//Relationship
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative1][relationship]")).clear();
		driver.findElement(By.name("data[applicannt-information][applicant1][nearest-relative1][relationship]")).sendKeys("Friend");
		Thread.sleep(200);
		
		//scroll down
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(2000);
		
		//VEHICLE INFORMATION - Vehicle details - Make 	
		driver.findElement(By.name("data[vehicle-info][make]")).clear();
		driver.findElement(By.name("data[vehicle-info][make]")).sendKeys("TATA");
		Thread.sleep(200);
		//Model
		driver.findElement(By.name("data[vehicle-info][model]")).clear();
		driver.findElement(By.name("data[vehicle-info][model]")).sendKeys("Indica");
		Thread.sleep(200);
		//Registration
		driver.findElement(By.name("data[vehicle-info][registration]")).clear();
		driver.findElement(By.name("data[vehicle-info][registration]")).sendKeys("2015");
		Thread.sleep(200);
		//VIN/Chassis #:
		driver.findElement(By.name("data[vehicle-info][Chassis]")).clear();
		driver.findElement(By.name("data[vehicle-info][Chassis]")).sendKeys("12");
		Thread.sleep(200);
		//Engine No
		driver.findElement(By.name("data[vehicle-info][engineno]")).clear();
		driver.findElement(By.name("data[vehicle-info][engineno]")).sendKeys("420");
		Thread.sleep(200);
		//NVIC (if known):
		driver.findElement(By.name("data[vehicle-info][NVIC]")).clear();
		driver.findElement(By.name("data[vehicle-info][NVIC]")).sendKeys("IC145");
		Thread.sleep(200);
		
		//Body
		List <WebElement> e5 = driver.findElements(By.name("data[vehicle-info][body]"));
		int i5 = e5.size();       
		System.out.println(i5);
		e5.get(0).click();
		Thread.sleep(200);
		
		//Year
		List <WebElement> e6 = driver.findElements(By.name("data[vehicle-info][condition]"));
		int i6 = e6.size();       
		System.out.println(i6);
		e6.get(0).click();
		Thread.sleep(200);

		//Transmission
		List <WebElement> e7 = driver.findElements(By.name("data[vehicle-info][transmission]"));
		int i7 = e7.size();       
		System.out.println(i7);
		e7.get(0).click();
		Thread.sleep(200);
		
		//Odometer
		driver.findElement(By.name("data[vehicle-info][odometer]")).clear();
		driver.findElement(By.name("data[vehicle-info][odometer]")).sendKeys("Data");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-2']/div/div/div[7]/div/button")).click();
		Thread.sleep(2000);
		
		//Tab-3
		//Company/Trust
		driver.findElement(By.name("data[applicant3][Company-Trust]")).clear();
		driver.findElement(By.name("data[applicant3][Company-Trust]")).sendKeys("Trust ME");
		Thread.sleep(200);
		
		//Trust Type
		List <WebElement> e8 = driver.findElements(By.name("data[applicant3][trust-type]"));
		int i8 = e8.size();       
		System.out.println(i8);
		e8.get(0).click();
		Thread.sleep(200);
		
		//ABN
		driver.findElement(By.name("data[applicant3][abn]")).clear();
		driver.findElement(By.name("data[applicant3][abn]")).sendKeys("785412");
		Thread.sleep(200);
		//Commence/Foundation date (DD/MM/YYYY):
		WebElement toDateBox3= driver.findElement(By.name("data[applicant3][start-date]"));
		toDateBox3.clear();
		toDateBox3.sendKeys("01/01/1990");
		Thread.sleep(200);
		toDateBox3.sendKeys(Keys.TAB);
		//Address - street
		driver.findElement(By.name("data[applicant3][address][street]")).clear();
		driver.findElement(By.name("data[applicant3][address][street]")).sendKeys("ABC");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[applicant3][address][suburb]")).clear();
		driver.findElement(By.name("data[applicant3][address][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		//State
		driver.findElement(By.name("data[applicant3][address][state]")).clear();
		driver.findElement(By.name("data[applicant3][address][state]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Pcode
		driver.findElement(By.name("data[applicant3][address][pcode]")).clear();
		driver.findElement(By.name("data[applicant3][address][pcode]")).sendKeys("785621");
		Thread.sleep(200);
		
		//Registered office - street
		driver.findElement(By.name("data[applicant3][office-address][street]")).clear();
		driver.findElement(By.name("data[applicant3][office-address][street]")).sendKeys("MG road");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[applicant3][office-address][suburb]")).clear();
		driver.findElement(By.name("data[applicant3][office-address][suburb]")).sendKeys("Wakad");
		Thread.sleep(200);
		//State
		driver.findElement(By.name("data[applicant3][office-address][state]")).clear();
		driver.findElement(By.name("data[applicant3][office-address][state]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Pcode
		driver.findElement(By.name("data[applicant3][office-address][pcode]")).clear();
		driver.findElement(By.name("data[applicant3][office-address][pcode]")).sendKeys("85412");
		Thread.sleep(200);
		
		//Applicant capacity
		driver.findElement(By.name("data[applicant3][applicant-capacity]")).clear();
		driver.findElement(By.name("data[applicant3][applicant-capacity]")).sendKeys("Data");
		Thread.sleep(200);
		//Director names (all directors must be applicants
		driver.findElement(By.name("data[applicant3][director-names]")).clear();
		driver.findElement(By.name("data[applicant3][director-names]")).sendKeys("Sanjay");
		Thread.sleep(200);
		//Shareholders
		driver.findElement(By.name("data[applicant3][shareholders]")).clear();
		driver.findElement(By.name("data[applicant3][shareholders]")).sendKeys("Sharma");
		Thread.sleep(200);
		
		//Trustee
		driver.findElement(By.name("data[applicant3][trustee]")).clear();
		driver.findElement(By.name("data[applicant3][trustee]")).sendKeys("Data");
		Thread.sleep(200);
		//Adult beneficiaries
		driver.findElement(By.name("data[applicant3][beneficiaries]")).clear();
		driver.findElement(By.name("data[applicant3][beneficiaries]")).sendKeys("Data");
		Thread.sleep(200);
		//Unit holders
		driver.findElement(By.name("data[applicant3][unit-holders]")).clear();
		driver.findElement(By.name("data[applicant3][unit-holders]")).sendKeys("Data");
		Thread.sleep(200);
		
		//APPLICANT 4 - Company/Trust
		driver.findElement(By.name("data[applicant4][Company-Trust]")).clear();
		driver.findElement(By.name("data[applicant4][Company-Trust]")).sendKeys("Trust");
		Thread.sleep(200);
		
		//Trust Type
		List <WebElement> e9 = driver.findElements(By.name("data[applicant4][trust-type]"));
		int i9 = e9.size();       
		System.out.println(i9);
		e9.get(0).click();
		Thread.sleep(200);
		
		//ABN & start date
		driver.findElement(By.name("data[applicant4][abn]")).clear();
		driver.findElement(By.name("data[applicant4][abn]")).sendKeys("52121");
		Thread.sleep(200);
		//Commence/Foundation date (DD/MM/YYYY):
		WebElement toDateBox4= driver.findElement(By.name("data[applicant4][start-date]"));
		toDateBox4.clear();
		toDateBox4.sendKeys("01/01/1990");
		Thread.sleep(200);
		toDateBox4.sendKeys(Keys.TAB);
		//Address - street
		driver.findElement(By.name("data[applicant4][address][street]")).clear();
		driver.findElement(By.name("data[applicant4][address][street]")).sendKeys("KK road");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[applicant4][address][suburb]")).clear();
		driver.findElement(By.name("data[applicant4][address][suburb]")).sendKeys("Kharadi");
		Thread.sleep(200);
		//State
		driver.findElement(By.name("data[applicant4][address][state]")).clear();
		driver.findElement(By.name("data[applicant4][address][state]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Pcode
		driver.findElement(By.name("data[applicant4][address][pcode]")).clear();
		driver.findElement(By.name("data[applicant4][address][pcode]")).sendKeys("457821");
		Thread.sleep(200);
		
		//Registered office - street
		driver.findElement(By.name("data[applicant4][office-address][street]")).clear();
		driver.findElement(By.name("data[applicant4][office-address][street]")).sendKeys("Wakad bridge");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[applicant4][office-address][suburb]")).clear();
		driver.findElement(By.name("data[applicant4][office-address][suburb]")).sendKeys("Wakad");
		Thread.sleep(200);
		//State
		driver.findElement(By.name("data[applicant4][office-address][state]")).clear();
		driver.findElement(By.name("data[applicant4][office-address][state]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Pcode
		driver.findElement(By.name("data[applicant4][office-address][pcode]")).clear();
		driver.findElement(By.name("data[applicant4][office-address][pcode]")).sendKeys("455621");
		Thread.sleep(200);
		//Applicant capacity
		driver.findElement(By.name("data[applicant4][applicant-capacity]")).clear();
		driver.findElement(By.name("data[applicant4][applicant-capacity]")).sendKeys("Data");
		Thread.sleep(200);
		//Director names (all directors must be applicants)
		driver.findElement(By.name("data[applicant4][director-names]")).clear();
		driver.findElement(By.name("data[applicant4][director-names]")).sendKeys("Mahesh");
		Thread.sleep(200);
		//Shareholders
		driver.findElement(By.name("data[applicant4][shareholders]")).clear();
		driver.findElement(By.name("data[applicant4][shareholders]")).sendKeys("sawant");
		Thread.sleep(200);
		//Trustee
		driver.findElement(By.name("data[applicant4][trustee]")).clear();
		driver.findElement(By.name("data[applicant4][trustee]")).sendKeys("You");
		Thread.sleep(200);
		//Adult beneficiaries 
		driver.findElement(By.name("data[applicant4][beneficiaries]")).clear();
		driver.findElement(By.name("data[applicant4][beneficiaries]")).sendKeys("Data");
		Thread.sleep(200);
		//Unit holders
		driver.findElement(By.name("data[applicant4][unit-holders]")).clear();
		driver.findElement(By.name("data[applicant4][unit-holders]")).sendKeys("Data");
		Thread.sleep(200);
		
				
		//CURRENT PAYG - APPLICANT 1		
		//Employer name		
		driver.findElement(By.name("data[employment-income][applicant1][employer-name]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][employer-name]")).sendKeys("Prakash");
		Thread.sleep(200);
		//Employer address
		driver.findElement(By.name("data[employment-income][applicant1][employer-address]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][employer-address]")).sendKeys("Kharadi, Pune");
		Thread.sleep(200);
		//Employer contact name
		driver.findElement(By.name("data[employment-income][applicant1][employer-contact]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][employer-contact]")).sendKeys("Sachin");
		Thread.sleep(200);
		//Employer phone
		driver.findElement(By.name("data[employment-income][applicant1][employer-phone]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][employer-phone]")).sendKeys("8945784512");
		Thread.sleep(200);
		
		
		//Employment status
		List <WebElement> e10 = driver.findElements(By.name("data[employment-income][applicant1][employer-status]"));
		int i10 = e10.size();       
		System.out.println(i10);
		e10.get(0).click();
		Thread.sleep(200);
		
		//ABN
		driver.findElement(By.name("data[employment-income][applicant1][ABN]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][ABN]")).sendKeys("785421");
		Thread.sleep(200);
		//Industry
		driver.findElement(By.name("data[employment-income][applicant1][industry]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][industry]")).sendKeys("IT");
		Thread.sleep(200);
		//Occupation
		driver.findElement(By.name("data[employment-income][applicant1][occupation]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][occupation]")).sendKeys("Job");
		Thread.sleep(200);
		//Date
		WebElement toDateBox5= driver.findElement(By.name("data[employment-income][applicant1][Date]"));
		toDateBox5.clear();
		toDateBox5.sendKeys("01/01/1990");
		Thread.sleep(200);
		toDateBox5.sendKeys(Keys.TAB);
		
		//PREVIOUS PAYG EMPLOYMENT (REQUIRED IF LESS THAN 2 YEARS IN CURRENT EMPLOYMENT)
		//Employer name
		driver.findElement(By.name("data[employment-income][applicant1][prev-employer-name]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][prev-employer-name]")).sendKeys("Zensar");
		Thread.sleep(200);
		//Employer address
		driver.findElement(By.name("data[employment-income][applicant1][prev-employer-address]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][prev-employer-address]")).sendKeys("Kharadi, Pune");
		Thread.sleep(200);
		//Employer contact name
		driver.findElement(By.name("data[employment-income][applicant1][prev-employer-contact-name]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][prev-employer-contact-name]")).sendKeys("Sameer Khana");
		Thread.sleep(200);
		//Employer phone
		driver.findElement(By.name("data[employment-income][applicant1][prev-employer-contact-phone]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][prev-employer-contact-phone]")).sendKeys("7894561230");
		Thread.sleep(200);
		
		
		//Employment status
		List <WebElement> e15 = driver.findElements(By.name("data[employment-income][applicant1][prev-employer-status]"));
		int i15 = e15.size();       
		System.out.println(i15);
		e15.get(0).click();
		Thread.sleep(200);

		
		//ABN
		driver.findElement(By.name("data[employment-income][applicant1][prev-ABN]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][prev-ABN]")).sendKeys("458945");
		Thread.sleep(200);
		//Industry
		driver.findElement(By.name("data[employment-income][applicant1][prev-industry]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][prev-industry]")).sendKeys("IT");
		Thread.sleep(200);
		//Occupation
		driver.findElement(By.name("data[employment-income][applicant1][prev-occupation]")).clear();
		driver.findElement(By.name("data[employment-income][applicant1][prev-occupation]")).sendKeys("Job");
		Thread.sleep(200);
		//Date
		WebElement toDateBox7= driver.findElement(By.name("data[employment-income][applicant1][prev-Date]"));
		toDateBox7.clear();
		toDateBox7.sendKeys("01/01/1990");
		Thread.sleep(200);
		toDateBox7.sendKeys(Keys.TAB);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-3']/div/div/div[5]/div/button")).click();
		Thread.sleep(2000);
		
		//Tab- 4
		//MONTHLY PAYG GROSS INCOME - 
		//Gross salary (inc Super)
		driver.findElement(By.name("data[previous-payg-emplyoment][gross-salary][text]")).clear();
		driver.findElement(By.name("data[previous-payg-emplyoment][gross-salary][text]")).sendKeys("5000");
		Thread.sleep(200);
		driver.findElement(By.name("data[previous-payg-emplyoment][gross-salary][val]")).clear();
		driver.findElement(By.name("data[previous-payg-emplyoment][gross-salary][val]")).sendKeys("4000");
		Thread.sleep(200);
		//Overtime/Allowances
		driver.findElement(By.name("data[previous-payg-emplyoment][overtime][text]")).clear();
		driver.findElement(By.name("data[previous-payg-emplyoment][overtime][text]")).sendKeys("300");
		Thread.sleep(200);
		driver.findElement(By.name("data[previous-payg-emplyoment][overtime][val]")).clear();
		driver.findElement(By.name("data[previous-payg-emplyoment][overtime][val]")).sendKeys("200");
		Thread.sleep(200);
		//Other (e.g. Commissions)
		driver.findElement(By.name("data[previous-payg-emplyoment][other][text]")).clear();
		driver.findElement(By.name("data[previous-payg-emplyoment][other][text]")).sendKeys("400");
		Thread.sleep(200);
		driver.findElement(By.name("data[previous-payg-emplyoment][other][val]")).clear();
		driver.findElement(By.name("data[previous-payg-emplyoment][other][val]")).sendKeys("300");
		Thread.sleep(200);
		
		//ANNUAL INCOME SELF-EMPLOYMENT/COMPANY INCOME (LAST 2 FULL FINANCIAL YEARS ENDING JUNE 30)
		//Pre-tax profit
		driver.findElement(By.name("data[annual][company][pre-tax-profit][text]")).clear();
		driver.findElement(By.name("data[annual][company][pre-tax-profit][text]")).sendKeys("300");
		Thread.sleep(200);
		driver.findElement(By.name("data[annual][company][pre-tax-profit][val]")).clear();
		driver.findElement(By.name("data[annual][company][pre-tax-profit][val]")).sendKeys("100");
		Thread.sleep(200);
		//Depreciation
		driver.findElement(By.name("data[annual][company][depreciation][text]")).clear();
		driver.findElement(By.name("data[annual][company][depreciation][text]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[annual][company][depreciation][val]")).clear();
		driver.findElement(By.name("data[annual][company][depreciation][val]")).sendKeys("50");
		Thread.sleep(200);
		//Other add backs
		driver.findElement(By.name("data[annual][company][other][text]")).clear();
		driver.findElement(By.name("data[annual][company][other][text]")).sendKeys("500");
		Thread.sleep(200);
		driver.findElement(By.name("data[annual][company][other][val]")).clear();
		driver.findElement(By.name("data[annual][company][other][val]")).sendKeys("300");
		Thread.sleep(200);
		//Accountant info (if any)
		driver.findElement(By.name("data[annual][company][accont-info1]")).clear();
		driver.findElement(By.name("data[annual][company][accont-info1]")).sendKeys("Ramesh Patil");
		Thread.sleep(200);
		driver.findElement(By.name("data[annual][company][accont-info2]")).clear();
		driver.findElement(By.name("data[annual][company][accont-info2]")).sendKeys("9517538520");
		Thread.sleep(200);
		
		//ANNUAL INCOME /TRUST INCOME (LAST 2 FULL FINANCIAL YEARS ENDING JUNE 30)
		//Pre-tax profit
				driver.findElement(By.name("data[annual][trust][pre-tax-profit][text]")).clear();
				driver.findElement(By.name("data[annual][trust][pre-tax-profit][text]")).sendKeys("300");
				Thread.sleep(200);
				driver.findElement(By.name("data[annual][trust][pre-tax-profit][val]")).clear();
				driver.findElement(By.name("data[annual][trust][pre-tax-profit][val]")).sendKeys("100");
				Thread.sleep(200);
				//Depreciation
				driver.findElement(By.name("data[annual][trust][depreciation][text]")).clear();
				driver.findElement(By.name("data[annual][trust][depreciation][text]")).sendKeys("100");
				Thread.sleep(200);
				driver.findElement(By.name("data[annual][trust][depreciation][val]")).clear();
				driver.findElement(By.name("data[annual][trust][depreciation][val]")).sendKeys("50");
				Thread.sleep(200);
				//Other add backs
				driver.findElement(By.name("data[annual][trust][other][text]")).clear();
				driver.findElement(By.name("data[annual][trust][other][text]")).sendKeys("500");
				Thread.sleep(200);
				driver.findElement(By.name("data[annual][trust][other][val]")).clear();
				driver.findElement(By.name("data[annual][trust][other][val]")).sendKeys("300");
				Thread.sleep(200);
				//Accountant info (if any)
				driver.findElement(By.name("data[annual][trust][accont-info1]")).clear();
				driver.findElement(By.name("data[annual][trust][accont-info1]")).sendKeys("Ramesh Patil");
				Thread.sleep(200);
				driver.findElement(By.name("data[annual][trust][accont-info2]")).clear();
				driver.findElement(By.name("data[annual][trust][accont-info2]")).sendKeys("9517538520");
				Thread.sleep(200);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(2000);		
		
		//OTHER INCOME (RENTAL, PENSION, GOVERNMENT BENEFITS, WORKCOVER, CSA ETC)
		//Source
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][0]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][0]")).sendKeys("10");//----------1
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][1]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][1]")).sendKeys("10");//----------2
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][2]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][2]")).sendKeys("10");//----------3
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][3]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][3]")).sendKeys("10");//----------4
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][4]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][4]")).sendKeys("10");//----------5
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][5]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Source][5]")).sendKeys("10");//----------6
		Thread.sleep(200);
		
		//Type/Description
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][0]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][0]")).sendKeys("Test");//----------1
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][1]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][1]")).sendKeys("Test");//----------2
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][2]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][2]")).sendKeys("Test");//----------3
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][3]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][3]")).sendKeys("Test");//----------4
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][4]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][4]")).sendKeys("Test");//----------5
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][5]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Type][5]")).sendKeys("Test");//----------6
		Thread.sleep(200);
		
		//Applicant
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][0]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][0]")).sendKeys("10");//----------1
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][1]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][1]")).sendKeys("10");//----------2
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][2]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][2]")).sendKeys("10");//----------3
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][3]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][3]")).sendKeys("10");//----------4
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][4]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][4]")).sendKeys("10");//----------5
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][5]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Applicant][5]")).sendKeys("10");//----------6
		Thread.sleep(200);
		
		//Payment
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][0]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][0]")).sendKeys("Y");//----------1
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][1]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][1]")).sendKeys("N");//----------2
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][2]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][2]")).sendKeys("Y");//----------3
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][3]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][3]")).sendKeys("N");//----------4
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][4]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][4]")).sendKeys("Y");//----------5
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][5]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][Payment][5]")).sendKeys("N");//----------6
		Thread.sleep(200);
		
		//Total for Last Year
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][0]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][0]")).sendKeys("10");//----------1
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][1]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][1]")).sendKeys("10");//----------2
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][2]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][2]")).sendKeys("10");//----------3
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][3]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][3]")).sendKeys("10");//----------4
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][4]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][4]")).sendKeys("10");//----------5
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][5]")).clear();
		driver.findElement(By.name("data[seccion-page-7][CONTACT-DETAILS][TotalforLastYear][5]")).sendKeys("10");//----------6
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-4']/div/div/div[5]/div/button")).click();
		Thread.sleep(2000);
		
		//Tab-5
		//ASSETS - Real Estate
		driver.findElement(By.name("data[seccion-page-5][real-estate][Address]")).clear();
		driver.findElement(By.name("data[seccion-page-5][real-estate][Address]")).sendKeys("Goa");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][real-estate][Pcode]")).clear();
		driver.findElement(By.name("data[seccion-page-5][real-estate][Pcode]")).sendKeys("74152");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][real-estate][val]")).clear();
		driver.findElement(By.name("data[seccion-page-5][real-estate][val]")).sendKeys("7000");
		Thread.sleep(200);
		
		//Other Real Estate
		driver.findElement(By.name("data[seccion-page-5][other-real-estate][Address]")).clear();
		driver.findElement(By.name("data[seccion-page-5][other-real-estate][Address]")).sendKeys("Goa");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][other-real-estate][Pcode]")).clear();
		driver.findElement(By.name("data[seccion-page-5][other-real-estate][Pcode]")).sendKeys("74152");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][other-real-estate][val]")).clear();
		driver.findElement(By.name("data[seccion-page-5][other-real-estate][val]")).sendKeys("5121");
		Thread.sleep(200);
		
		//Vehicle #1 
		driver.findElement(By.name("data[seccion-page-5][vehicle1][Make]")).clear();
		driver.findElement(By.name("data[seccion-page-5][vehicle1][Make]")).sendKeys("Honda");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][vehicle1][Model]")).clear();
		driver.findElement(By.name("data[seccion-page-5][vehicle1][Model]")).sendKeys("City");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][vehicle1][year]")).clear();
		driver.findElement(By.name("data[seccion-page-5][vehicle1][year]")).sendKeys("2016");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][vehicle1][val]")).clear();
		driver.findElement(By.name("data[seccion-page-5][vehicle1][val]")).sendKeys("200000");
		Thread.sleep(200);
		
		//Vehicle #2
		driver.findElement(By.name("data[seccion-page-5][vehicle2][Make]")).clear();
		driver.findElement(By.name("data[seccion-page-5][vehicle2][Make]")).sendKeys("Honda");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][vehicle2][Model]")).clear();
		driver.findElement(By.name("data[seccion-page-5][vehicle2][Model]")).sendKeys("City");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][vehicle2][year]")).clear();
		driver.findElement(By.name("data[seccion-page-5][vehicle2][year]")).sendKeys("2016");
		Thread.sleep(200);
		driver.findElement(By.name("data[seccion-page-5][vehicle2][val]")).clear();
		driver.findElement(By.name("data[seccion-page-5][vehicle2][val]")).sendKeys("200000");
		Thread.sleep(200);
		
		//Bank balances
		driver.findElement(By.name("data[seccion-page-5][bank-balance]")).clear();
		driver.findElement(By.name("data[seccion-page-5][bank-balance]")).sendKeys("50000");
		Thread.sleep(200);
		
		//Plant & Equipment
		driver.findElement(By.name("data[seccion-page-5][Plant-Equipment]")).clear();
		driver.findElement(By.name("data[seccion-page-5][Plant-Equipment]")).sendKeys("4000");
		Thread.sleep(200);
		
		//Household items
		driver.findElement(By.name("data[seccion-page-5][Household-items]")).clear();
		driver.findElement(By.name("data[seccion-page-5][Household-items]")).sendKeys("5000");
		Thread.sleep(200);
		
		//Investments
		driver.findElement(By.name("data[seccion-page-5][Investments]")).clear();
		driver.findElement(By.name("data[seccion-page-5][Investments]")).sendKeys("100");
		Thread.sleep(200);
		
		//Other
		driver.findElement(By.name("data[seccion-page-5][Other]")).clear();
		driver.findElement(By.name("data[seccion-page-5][Other]")).sendKeys("500");
		Thread.sleep(200);
		
		//BY ASSETS - Mortgage #1
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-1][lender]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-1][lender]")).sendKeys("Amir");
		Thread.sleep(200);
		//Mortgage #2
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-2][lender]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-2][lender]")).sendKeys("Salman");
		Thread.sleep(200);
		//Car loan #1
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-1][lender]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-1][lender]")).sendKeys("Shahrukh");
		Thread.sleep(200);
		//Car loan #2
				driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-2][lender]")).clear();
				driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-2][lender]")).sendKeys("Saif");
				Thread.sleep(200);
		//Other Secured Loan
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][other-loan-no]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][other-loan-no]")).sendKeys("3");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][other-loan-lender]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][other-loan-lender]")).sendKeys("400");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][other-loan-monthly-payment]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][other-loan-monthly-payment]")).sendKeys("200");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][other-loan-balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][other-loan-balance]")).sendKeys("100");
		Thread.sleep(200);
		
		//Worst current status
		List <WebElement> e20 = driver.findElements(By.name("data[LIABILITIES-1PAYMENTS][secured][worst-current-status]"));
		int i20 = e20.size();       
		System.out.println(i20);
		e20.get(0).click();
		Thread.sleep(200);	
				
				//Worst past status
		List <WebElement> e21 = driver.findElements(By.name("data[LIABILITIES-1PAYMENTS][secured][worst-past-status]"));
		int i21 = e21.size();       
		System.out.println(i21);
		e21.get(0).click();
		Thread.sleep(200);
		
		//MONTHLY PAYMENT
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-1][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-1][monthly]")).sendKeys("200");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-2][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-2][monthly]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-1][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-1][monthly]")).sendKeys("400");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-2][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-2][monthly]")).sendKeys("100");
		Thread.sleep(200);
		
		//BALANCE or LIMIT
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-1][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-1][balance]")).sendKeys("200");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-2][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Mortgage-2][balance]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-1][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-1][balance]")).sendKeys("400");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-2][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][carloan-2][balance]")).sendKeys("100");
		Thread.sleep(200);
		
		
		
		//LIABILITIES NOT SECURED BY ASSETS
		//All store cards
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-store-cards][no]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-store-cards][no]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-store-cards][lender]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-store-cards][lender]")).sendKeys("Kedar");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-store-cards][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-store-cards][monthly]")).sendKeys("500");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-store-cards][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-store-cards][balance]")).sendKeys("400");
		Thread.sleep(200);
		
		//All credit cards
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-credit-cards][no]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-credit-cards][no]")).sendKeys("5");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-credit-cards][lender]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-credit-cards][lender]")).sendKeys("Kedar");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-credit-cards][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-credit-cards][monthly]")).sendKeys("500");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-credit-cards][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-credit-cards][balance]")).sendKeys("400");
		Thread.sleep(200);
		
		//All personal loans
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-personal-loans][no]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-personal-loans][no]")).sendKeys("3");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-personal-loans][lender]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-personal-loans][lender]")).sendKeys("Kedar");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-personal-loans][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-personal-loans][monthly]")).sendKeys("500");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-personal-loans][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-personal-loans][balance]")).sendKeys("400");
		Thread.sleep(200);
		
		//All other loans
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-other-loans][no]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-other-loans][no]")).sendKeys("4");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-other-loans][lender]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-other-loans][lender]")).sendKeys("Kedar");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-other-loans][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-other-loans][monthly]")).sendKeys("500");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-other-loans][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][All-other-loans][balance]")).sendKeys("400");
		Thread.sleep(200);
		
		//Worst current status
		List <WebElement> e41 = driver.findElements(By.name("data[LIABILITIES-1PAYMENTS][notsecured][worst-current-status]"));
		int i41 = e41.size();       
		System.out.println(i41);
		e41.get(0).click();
		Thread.sleep(200);	
		
		//Worst past status
		List <WebElement> e42 = driver.findElements(By.name("data[LIABILITIES-1PAYMENTS][notsecured][worst-past-status]"));
		int i42 = e42.size();       
		System.out.println(i42);
		e42.get(0).click();
		Thread.sleep(200);	
		
		//OTHER LIABILITIES
		//Rates/Water
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][rates][crediator]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][rates][crediator]")).sendKeys("data");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][rates][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][rates][monthly]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][rates][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][rates][balance]")).sendKeys("100");
		Thread.sleep(200);
		
		//Rent/Board
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Rent][creditor]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Rent][creditor]")).sendKeys("data");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Rent][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Rent][monthly]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Rent][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Rent][balance]")).sendKeys("100");
		Thread.sleep(200);
		
		//Tax/Utilities
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Tax][crediator]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Tax][crediator]")).sendKeys("data");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Tax][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Tax][monthly]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Tax][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Tax][balance]")).sendKeys("100");
		Thread.sleep(200);
		
		//Insurance
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Insurance][crediator]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Insurance][crediator]")).sendKeys("data");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Insurance][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Insurance][monthly]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Insurance][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Insurance][balance]")).sendKeys("100");
		Thread.sleep(200);
		
		//Other
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Other][crediator]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Other][crediator]")).sendKeys("data");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Other][monthly]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Other][monthly]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Other][balance]")).clear();
		driver.findElement(By.name("data[LIABILITIES-1PAYMENTS][Other][balance]")).sendKeys("100");
		Thread.sleep(200);
		
		//Worst current status
				List <WebElement> e43 = driver.findElements(By.name("data[LIABILITIES-1PAYMENTS][other][worst-current-status]"));
				int i43 = e43.size();       
				System.out.println(i43);
				e43.get(0).click();
				Thread.sleep(200);	
				
				//Worst past status
				List <WebElement> e44 = driver.findElements(By.name("data[LIABILITIES-1PAYMENTS][other][worst-past-status]"));
				int i44 = e44.size();       
				System.out.println(i44);
				e44.get(0).click();
				Thread.sleep(200);	
				
		//ARREARS EXPLANATIONS (PLEASE PROVIDE FOLLOWING IF ANY INFRINGEMENTS INDICATED ABOVE)
		//Arrears reason (tick one)		
				List <WebElement> e45 = driver.findElements(By.name("data[arrears-explanations][arrears-reason]"));
				int i45 = e45.size();       
				System.out.println(i45);
				e45.get(0).click();
				Thread.sleep(200);	
		
		//Arrears explanation 
		WebElement toDateBox8= driver.findElement(By.name("data[arrears-explanations][arrears-explanation]"));
		toDateBox8.clear();
		toDateBox8.sendKeys("01/01/2015");
		Thread.sleep(200);
		toDateBox8.sendKeys(Keys.TAB);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-5']/div/div/div[6]/div/button")).click();
		Thread.sleep(2000);
		
		//Tab-6
		//Annual Stated NPBT ($)
		driver.findElement(By.name("data[consumer][other][npbt]")).clear();
		driver.findElement(By.name("data[consumer][other][npbt]")).sendKeys("Data");
		Thread.sleep(200);
		
		//AUTHORITY TO DELIVER LOAN DOCUMENTS
		driver.findElement(By.name("data[consumer][other][authority-email]")).clear();
		driver.findElement(By.name("data[consumer][other][authority-email]")).sendKeys("chandrakant@nadsoftdev.com");
		Thread.sleep(200);
		driver.findElement(By.name("data[consumer][other][authority-postal]")).clear();
		driver.findElement(By.name("data[consumer][other][authority-postal]")).sendKeys("Kharadi, Pune");
		Thread.sleep(2000);
		
		List <WebElement> e46 = driver.findElements(By.name("data[consumer][other][declaration-check1]"));
		int i46 = e46.size();       
		System.out.println(i46);
		e46.get(0).click();
		Thread.sleep(200);
		
		List <WebElement> e47 = driver.findElements(By.name("data[consumer][other][declaration-check2]"));
		int i47 = e47.size();       
		System.out.println(i47);
		e47.get(0).click();
		Thread.sleep(200);	
		
		List <WebElement> e48 = driver.findElements(By.name("data[consumer][other][declaration-check3]"));
		int i48 = e48.size();       
		System.out.println(i48);
		e48.get(0).click();
		Thread.sleep(200);	
		
		List <WebElement> e49 = driver.findElements(By.name("data[applicannt-information][last][title]"));
		int i49 = e49.size();       
		System.out.println(i49);
		e49.get(0).click();
		Thread.sleep(200);	
		
		
		
		driver.findElement(By.name("data[introducer-declaration][applicant1][firstname]")).clear();
		driver.findElement(By.name("data[introducer-declaration][applicant1][firstname]")).sendKeys("Amit");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[introducer-declaration][applicant1][lastname]")).clear();
		driver.findElement(By.name("data[introducer-declaration][applicant1][lastname]")).sendKeys("Shinde");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[consumer][other][drivers-licence-no]")).clear();
		driver.findElement(By.name("data[consumer][other][drivers-licence-no]")).sendKeys("878564");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[consumer][other][int1-travel-doc-no]")).clear();
		driver.findElement(By.name("data[consumer][other][int1-travel-doc-no]")).sendKeys("10");
		Thread.sleep(200);
		
					
		//Submit
				driver.findElement(By.xpath(".//*[@id='library-form-submit']")).click();
				Thread.sleep(20000);
				
				//add code of verification
				
				//click dashboard
		 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
		 		Thread.sleep(2000);
		 		
		 		//click applications
		 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[6]/a")).click();
		 		Thread.sleep(2000);
		 		
		 		//click on view all 
		 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[6]/ul/li[3]/a")).click();
		 		Thread.sleep(2000);		
		 		
		 		System.out.println("Liberty form updated");
		 		
		 		String actual = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
		 		System.out.println(actual);
		 		String expected = "Amit Pravin Shinde";
		 		
		 		Assert.assertEquals(actual, expected);
				
				System.out.println("====================================================================");		
		
 	}
 	
 	public void addBusinessLoanApplication() throws InterruptedException, AWTException{
 		
 		System.out.println("Add business application form");
 		
 		//click dashboard
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
 		Thread.sleep(2000);          
 		
 		//click applications
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/a")).click();
 		Thread.sleep(2000);
 		
 		//click on view all 
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/ul/li[1]/a")).click();
 		Thread.sleep(5000);
 		
 		//Add new button
 		driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div/div/button")).click();
 		Thread.sleep(3000);
 		
 		//Business application 
 		driver.findElement(By.xpath(".//*[@id='page-wrapper']/div[1]/div/div/div/ul/li[2]/a")).click();
 		Thread.sleep(3000);  
 		
 		
 		//Company details
		
		//next button for validation check
		driver.findElement(By.xpath(".//*[@id='next7']")).click();
		Thread.sleep(3000);   
		
		/*//phone no ref -1 
				driver.findElement(By.name("data[personal_reference][phone_1][]")).sendKeys("9856451245");*/
		
		/*//Borrower Type - Primary Buyer
		String vMsg107 = driver.findElement(By.xpath(".//*[@id='data[company_application][borrow_type1][]-error']")).getText();
		String vMsg108 = "This field is required.";
		Assert.assertEquals(vMsg107, vMsg108);
		List <WebElement> radios1 = driver.findElements(By.name("data[company_application][borrow_type1][]"));
		radios1.get(0).click();
		Thread.sleep(200);*/
		
		//Is Company Proprietary?
		String err1 = driver.findElement(By.xpath(".//*[@id='data[company_details][is_proprietary_company][]-error']")).getText();
		String err2 = "This field is required.";
		Assert.assertEquals(err1, err2);
		driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[4]/div/div/div/div/div[1]/div[1]/div/div/label[1]/input")).click();
		Thread.sleep(200);
		
		//Company details - Trading Name
		String vMsg109 = driver.findElement(By.xpath(".//*[@id='data[company_details][tradingname][]-error']")).getText();
		String vMsg110 = "This field is required.";
		Assert.assertEquals(vMsg109, vMsg110);
		driver.findElement(By.name("data[company_details][tradingname][]")).clear();
		driver.findElement(By.name("data[company_details][tradingname][]")).sendKeys("Test Micro trading");
		Thread.sleep(200);
		
		//Legal Name
		String vMsg111 = driver.findElement(By.xpath(".//*[@id='data[company_details][legalname][]-error']")).getText();
		String vMsg112 = "This field is required.";
		Assert.assertEquals(vMsg111, vMsg112);
		driver.findElement(By.name("data[company_details][legalname][]")).clear();
		driver.findElement(By.name("data[company_details][legalname][]")).sendKeys("Micro");
		Thread.sleep(200);
		
		//Type of Business
		String vMsg113 = driver.findElement(By.xpath(".//*[@id='data[company_details][type_of_business][]-error']")).getText();
		String vMsg114 = "This field is required.";
		Assert.assertEquals(vMsg113, vMsg114);
		driver.findElement(By.name("data[company_details][type_of_business][]")).clear();
		driver.findElement(By.name("data[company_details][type_of_business][]")).sendKeys("Electric");
		Thread.sleep(200);
		
		//ACN/ABN
		String vMsg115 = driver.findElement(By.xpath(".//*[@id='data[company_details][acn_abn][]-error']")).getText();
		String vMsg116 = "This field is required.";
		Assert.assertEquals(vMsg115, vMsg116);
		driver.findElement(By.name("data[company_details][acn_abn][]")).clear();
		driver.findElement(By.name("data[company_details][acn_abn][]")).sendKeys("455645");
		Thread.sleep(200);
		
		//Date Established
		String vMsg117 = driver.findElement(By.xpath(".//*[@id='data[company_details][date_esta][]-error']")).getText();
		String vMsg118 = "This field is required.";
		Assert.assertEquals(vMsg117, vMsg118);
		WebElement established = driver.findElement(By.name("data[company_details][date_esta][]"));
		established.clear();
		established.sendKeys("05/05/2001");
		Thread.sleep(200);
		established.sendKeys(Keys.TAB);
		
		//creadit history
		String vMsg1171 = driver.findElement(By.xpath(".//*[@id='credit_history-error']")).getText();
		String vMsg1181 = "This field is required.";
		Assert.assertEquals(vMsg1171, vMsg1181);
		Select drop = new Select(driver.findElement(By.name("data[company_details][credit_history][]"))); 
		drop.selectByVisibleText("No Credit History");
		Thread.sleep(200);
		
		//Address Type : Buying
		String vMsg121 = driver.findElement(By.xpath(".//*[@id='data[company_details][address_type1][]-error']")).getText();
		String vMsg122 = "This field is required.";
		Assert.assertEquals(vMsg121, vMsg122);
		List <WebElement> address = driver.findElements(By.name("data[company_details][address_type1][]"));
		int addres = address.size();       
		System.out.println(addres);
		address.get(1).click();
		Thread.sleep(200);
		
		//Previous Address Details
		//Time At Address
		WebElement years1 = driver.findElement(By.name("data[company_details][prev_time_at_address_yrs][]"));
		Select year1 = new Select (years1);             
		year1.selectByVisibleText("1 Years");
		Thread.sleep(1000);
		WebElement months1 = driver.findElement(By.name("data[company_details][prev_time_at_address_months][]"));
		Select month1 = new Select (months1);            
		month1.selectByVisibleText("4 Months");
		Thread.sleep(1000);
				
		//capture the validation msg
		String vMsg35 = driver.findElement(By.xpath(".//*[@id='data[company_details][previous_address][]-error']")).getText();
		String vMsg36 = "This field is required.";
		Assert.assertEquals(vMsg35, vMsg36);
		driver.findElement(By.name("data[company_details][previous_address][]")).clear();
		driver.findElement(By.name("data[company_details][previous_address][]")).sendKeys("Aundh, Pune");
		Thread.sleep(200);
		
		//suburb
		//capture the validation msg
		String vMsg37 = driver.findElement(By.xpath(".//*[@id='data[company_details][previous_suburb][]-error']")).getText();
		String vMsg38 = "This field is required.";
		Assert.assertEquals(vMsg37, vMsg38);
		driver.findElement(By.name("data[company_details][previous_suburb][]")).clear();
		driver.findElement(By.name("data[company_details][previous_suburb][]")).sendKeys("Aundh");
		Thread.sleep(200);          
				
		//State
		//capture the validation msg
		String vMsg39 = driver.findElement(By.xpath(".//*[@id='data[company_details][prev_state][]-error']")).getText();
		String vMsg40 = "This field is required.";
		Assert.assertEquals(vMsg39, vMsg40);
		WebElement state = driver.findElement(By.name("data[company_details][prev_state][]"));
		Select states = new Select (state);            
		states.selectByVisibleText("NSW");
		Thread.sleep(1000);
				
		//Postal Code
		//capture the validation msg
		String vMsg41 = driver.findElement(By.xpath(".//*[@id='data[company_details][previous_postal_code][]-error']")).getText();
		String vMsg42 = "This field is required.";
		Assert.assertEquals(vMsg41, vMsg42);
		driver.findElement(By.name("data[company_details][previous_postal_code][]")).clear();
		driver.findElement(By.name("data[company_details][previous_postal_code][]")).sendKeys("45121");
		Thread.sleep(200);
		
		//Prev Time At Address
		WebElement years2 = driver.findElement(By.name("data[company_details][time_at_address_yrs][]"));
    	Select year2 = new Select (years2);                     
    	year2.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	WebElement months2 = driver.findElement(By.name("data[company_details][time_at_address_months][]"));
    	Select month2 = new Select (months2);
    	month2.selectByVisibleText("4 Months");
    	Thread.sleep(1000);
		
		//Previous Address
    	//capture the validation msg
		String vMsg45 = driver.findElement(By.xpath(".//*[@id='data[company_details][current_address][]-error']")).getText();
		String vMsg46 = "This field is required.";
		Assert.assertEquals(vMsg45, vMsg46);
		driver.findElement(By.name("data[company_details][current_address][]")).clear();
		driver.findElement(By.name("data[company_details][current_address][]")).sendKeys("Station, Pune");
		Thread.sleep(200);
		
		//suburb
		//capture the validation msg
		String vMsg47 = driver.findElement(By.xpath(".//*[@id='data[company_details][suburb][]-error']")).getText();
		String vMsg48 = "This field is required.";
		Assert.assertEquals(vMsg47, vMsg48);
		driver.findElement(By.name("data[company_details][suburb][]")).clear();
		driver.findElement(By.name("data[company_details][suburb][]")).sendKeys("Station");
		Thread.sleep(200);
		
		//State
		//capture the validation msg
		String vMsg49 = driver.findElement(By.xpath(".//*[@id='data[company_details][state][]-error']")).getText();
		String vMsg50 = "This field is required.";
		Assert.assertEquals(vMsg49, vMsg50);
		WebElement state1 = driver.findElement(By.name("data[company_details][state][]"));
		Select states1 = new Select (state1);
		states1.selectByVisibleText("NSW");
		Thread.sleep(1000);
		
		//Postal Code
		String vMsg51 = driver.findElement(By.xpath(".//*[@id='data[company_details][postal_code][]-error']")).getText();
		String vMsg52 = "This field is required.";
		Assert.assertEquals(vMsg51, vMsg52);
		driver.findElement(By.name("data[company_details][postal_code][]")).clear();
		driver.findElement(By.name("data[company_details][postal_code][]")).sendKeys("45254");
		Thread.sleep(200);
		
		//Company Annual Profit
		String vMsg511 = driver.findElement(By.xpath(".//*[@id='data[company_details][annual_profit][]-error']")).getText();
		String vMsg521 = "This field is required.";
		Assert.assertEquals(vMsg511, vMsg521);
		driver.findElement(By.name("data[company_details][annual_profit][]")).clear();
		driver.findElement(By.name("data[company_details][annual_profit][]")).sendKeys("976211");
		Thread.sleep(200);
		
		//Trade Reference One 
		//Business Name
		String vMsg1291 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_1][]-error']")).getText();
		String vMsg1301 = "This field is required.";
		Assert.assertEquals(vMsg1291, vMsg1301);
		driver.findElement(By.name("data[personal_reference][fname_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][fname_1][]")).sendKeys("RefOne");
		Thread.sleep(200);
		
		//Contact
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).sendKeys("TestOne");
		Thread.sleep(200);
		
		//Phone No*
		String vMsg12911 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_1][]-error']")).getText();
		String vMsg13011 = "This field is required.";
		Assert.assertEquals(vMsg12911, vMsg13011);
		driver.findElement(By.name("data[personal_reference][phone_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][phone_1][]")).sendKeys("1234567890");
		Thread.sleep(200);
		
		//Trade Reference One
		//Business Name
				String vMsg129111 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][fname_2][]-error']")).getText();
				String vMsg130111 = "This field is required.";
				Assert.assertEquals(vMsg129111, vMsg130111);
				driver.findElement(By.name("data[personal_reference][fname_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][fname_2][]")).sendKeys("RefTwo");
				Thread.sleep(200);
				
				//Contact
				driver.findElement(By.name("data[personal_reference][current_address_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][current_address_2][]")).sendKeys("TestTwo");
				Thread.sleep(200);
				
				//Phone No*
				String vMsg1291111 = driver.findElement(By.xpath(".//*[@id='data[personal_reference][phone_2][]-error']")).getText();
				String vMsg1301111 = "This field is required.";
				Assert.assertEquals(vMsg1291111, vMsg1301111);
				driver.findElement(By.name("data[personal_reference][phone_2][]")).clear();
				driver.findElement(By.name("data[personal_reference][phone_2][]")).sendKeys("1234567890");
				Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next7']")).click();
		Thread.sleep(2000);
		
	
		
		//Number of Directors
		Select drops1 = new Select(driver.findElement(By.name("data[directorship_details][director_number][]")));
		drops1.selectByVisibleText("3");
		Thread.sleep(200);
		
		//Directorship details
		driver.findElement(By.xpath(".//*[@id='next8']")).click();
		Thread.sleep(2000);
		
		//Name of Director 1
		String vMsg129 = driver.findElement(By.xpath(".//*[@id='director_1_name-error']")).getText();
		String vMsg130 = "This field is required.";
		Assert.assertEquals(vMsg129, vMsg130);
		driver.findElement(By.name("data[directorship_details][director_1_name][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_1_name][]")).sendKeys("Pravin Jagdale");
		Thread.sleep(200);
		
		//Date Appointed
		String vMsg131 = driver.findElement(By.xpath(".//*[@id='director_1_doa-error']")).getText();
		String vMsg132 = "This field is required.";
		Assert.assertEquals(vMsg131, vMsg132);
		WebElement appointmentD1 = driver.findElement(By.name("data[directorship_details][director_1_doa][]"));
		appointmentD1.clear();
		appointmentD1.sendKeys("01/05/1995");
		Thread.sleep(200);
		appointmentD1.sendKeys(Keys.TAB);
		
		//Date of Birth
		String vMsg133 = driver.findElement(By.xpath(".//*[@id='director_1_dob-error']")).getText();
		String vMsg134 = "This field is required.";
		Assert.assertEquals(vMsg133, vMsg134);
		WebElement birthD1 = driver.findElement(By.name("data[directorship_details][director_1_dob][]"));
		birthD1.clear();
		birthD1.sendKeys("01/05/1980");
		Thread.sleep(200);
		birthD1.sendKeys(Keys.TAB);
		
		//Name of Director 2
		String vMsg1311 = driver.findElement(By.xpath(".//*[@id='director_2_name-error']")).getText();
		String vMsg1321 = "This field is required.";
		Assert.assertEquals(vMsg1311, vMsg1321);
		driver.findElement(By.name("data[directorship_details][director_2_name][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_2_name][]")).sendKeys("Mohsin Jamadar");
		Thread.sleep(200);
				
		//Date Appointed
		String vMsg1312 = driver.findElement(By.xpath(".//*[@id='director_2_doa-error']")).getText();
		String vMsg1322 = "This field is required.";
		Assert.assertEquals(vMsg1312, vMsg1322);
		WebElement appointmentD2 = driver.findElement(By.name("data[directorship_details][director_2_doa][]"));
		appointmentD2.clear();
		appointmentD2.sendKeys("01/08/2001");
		Thread.sleep(200);
		appointmentD2.sendKeys(Keys.TAB);
				
		//Date of Birth
		WebElement birthD2 = driver.findElement(By.name("data[directorship_details][director_2_dob][]"));
		birthD2.clear();
		birthD2.sendKeys("01/05/1990");
		Thread.sleep(200);
		birthD2.sendKeys(Keys.TAB);
		
		//Name of Director 3
		driver.findElement(By.name("data[directorship_details][director_3_name][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_3_name][]")).sendKeys("Rakesh Deshpande");
		Thread.sleep(200);
						
		//Date Appointed
		WebElement appointmentD3 = driver.findElement(By.name("data[directorship_details][director_3_doa][]"));
		appointmentD3.clear();
		appointmentD3.sendKeys("04/07/2009");
		Thread.sleep(200);
		appointmentD3.sendKeys(Keys.TAB);
						
		//Date of Birth
		WebElement birthD3 = driver.findElement(By.name("data[directorship_details][director_3_dob][]"));
		birthD3.clear();
		birthD3.sendKeys("01/05/1993");
		Thread.sleep(200);
		birthD3.sendKeys(Keys.TAB);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next8']")).click();
		Thread.sleep(2000);
		
		//Financial details
		//Assets
			//yes option check and delete field
			WebElement assects = driver.findElement(By.name("data[assets_libility][type_of_asset][]"));
	    	Select homeProperty = new Select (assects);               
	    	homeProperty.selectByVisibleText("Home Property");
	    	Thread.sleep(1000);
			//add button
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
	    	Thread.sleep(1000);          
	    	//Yes select
	    	WebElement assects2 = driver.findElement(By.name("data[assets_libility_assets][asset_home][own][]"));
	    	Select ownedOutright = new Select (assects2);             
	    	ownedOutright.selectByVisibleText("Yes");
	    	Thread.sleep(1000);
	    	//delete
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[2]/table/tbody/tr[2]/td[4]/a/i")).click();
	    	Thread.sleep(1000);           
	    	//no option and delete from liabilties 
	    	//add button
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
	    	Thread.sleep(1000);          
	    	//no option
	    	WebElement assects3 = driver.findElement(By.name("data[assets_libility_assets][asset_home][own][]"));
	    	Select ownedOutrightNo = new Select (assects3);           
	    	ownedOutrightNo.selectByVisibleText("No");
	    	Thread.sleep(1000);
	    	//del from liab
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[7]/a/i")).click();
	    	Thread.sleep(1000);          
			
	    	//add all assets and select Yes
	    	//Home Property
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
	    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[1]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
		    	Thread.sleep(1000);          
		    	//delete
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[2]/table/tbody/tr[2]/td[7]/a/i")).click();
		    	Thread.sleep(1000);
		    	
		    	
		    	//check the all liability property fields and his error messages
		    	//Home Property Loan
		    	//add
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
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
		    	driver.findElement(By.xpath(".//*[@id='financial']/div/div/div/div/div[2]/div[1]/a/i")).click();
		    	Thread.sleep(1000);          
		    	//Balance
		    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][balance_owing][]")).sendKeys("100");
		    	Thread.sleep(1000);
				
				//next
				driver.findElement(By.xpath(".//*[@id='next4']")).click();
				Thread.sleep(2000);
		
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
					driver.findElement(By.xpath(".//*[@id='next4']")).click();
					Thread.sleep(2000);
					
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
			    	driver.findElement(By.name("data[assets_libility][monthly_rent_board][]")).sendKeys("10");
			    	Thread.sleep(1000);
			    	
			    	//Monthly Rental/Board Payment 
			    	driver.findElement(By.name("data[assets_libility][general_living][]")).sendKeys("10");
			    	Thread.sleep(1000);
			    	
			    	//General Living Expenses
			    	driver.findElement(By.name("data[assets_libility][vehicle_running_cost][]")).sendKeys("10");
			    	Thread.sleep(1000);
			    	
			    	//Motor Vehicle Running Costs
			    	driver.findElement(By.name("data[assets_libility][private_education_childcare][]")).sendKeys("10");
			    	Thread.sleep(1000);
			    	
			    	//Private Education & Childcare 
			    	driver.findElement(By.name("data[assets_libility][other_expenses][]")).sendKeys("10");
			    	Thread.sleep(1000);
			    	
        //next
		driver.findElement(By.xpath(".//*[@id='next4']")).click();
		Thread.sleep(2000);
		
		//Account details
		//next button for validation check
		driver.findElement(By.xpath(".//*[@id='next9']")).click();
		Thread.sleep(3000);   
		
		//Accountant
		String vMsg135 = driver.findElement(By.xpath(".//*[@id='data[accountant_details][accountant][]-error']")).getText();
		String vMsg136 = "This field is required.";
		Assert.assertEquals(vMsg135, vMsg136);
		driver.findElement(By.name("data[accountant_details][accountant][]")).clear();
		driver.findElement(By.name("data[accountant_details][accountant][]")).sendKeys("HDFC");
		Thread.sleep(200);
		
		//Contact Name
		String vMsg137 = driver.findElement(By.xpath(".//*[@id='data[accountant_details][contact_name][]-error']")).getText();
		String vMsg138 = "This field is required.";
		Assert.assertEquals(vMsg137, vMsg138);
		driver.findElement(By.name("data[accountant_details][contact_name][]")).clear();
		driver.findElement(By.name("data[accountant_details][contact_name][]")).sendKeys("Rehan Khan");
		Thread.sleep(200);
		
		//Phone Number
		String vMsg139 = driver.findElement(By.xpath(".//*[@id='data[accountant_details][phone_number][]-error']")).getText();
		String vMsg140 = "This field is required.";
		Assert.assertEquals(vMsg139, vMsg140);
		driver.findElement(By.name("data[accountant_details][phone_number][]")).clear();
		driver.findElement(By.name("data[accountant_details][phone_number][]")).sendKeys("9719734682");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next9']")).click();
		Thread.sleep(2000);
		
		
		//Personal details 
		driver.findElement(By.xpath(".//*[@id='next1']")).click();
		Thread.sleep(2000);

        //Personal Details -
		//capture the validation msg
		String vMsg1 = driver.findElement(By.xpath(".//*[@id='data[individual_application][borrow_type1][]-error']")).getText();
		String vMsg2 = "This field is required.";   
		Assert.assertEquals(vMsg1, vMsg2);
		//Borrower Type - 	Primary Buyer 
		List <WebElement> radios = driver.findElements(By.name("data[individual_application][borrow_type1][]"));
		int o = radios.size();       
		System.out.println(o);
		radios.get(0).click();
		Thread.sleep(200);
		
		//Select Title
		//capture the validation msg
		String vMsg13a = driver.findElement(By.xpath(".//*[@id='data[personal_details][title][]-error']")).getText();
		String vMsg14a = "This field is required.";    
		Assert.assertEquals(vMsg13a, vMsg14a);
		WebElement gender1a = driver.findElement(By.name("data[personal_details][title][]"));
		Select ga = new Select(gender1a);                 
		ga.selectByVisibleText("Mr");
		Thread.sleep(500);
		
		//First name
		//capture the validation msg
		String vMsg3 = driver.findElement(By.xpath(".//*[@id='data[personal_details][fname][]-error']")).getText();
		String vMsg4 = "This field is required.";
		Assert.assertEquals(vMsg3, vMsg4);
		driver.findElement(By.name("data[personal_details][fname][]")).clear();
		driver.findElement(By.name("data[personal_details][fname][]")).sendKeys("Test");
		Thread.sleep(200);

		
		//Middle name
		//capture the validation msg
		/*String vMsg5 = driver.findElement(By.xpath(".//*[@id='data[personal_details][mname][]-error']")).getText();
		String vMsg6 = "This field is required.";
		Assert.assertEquals(vMsg5, vMsg6);*/
		driver.findElement(By.name("data[personal_details][mname][]")).clear();
		driver.findElement(By.name("data[personal_details][mname][]")).sendKeys("Demo");
		Thread.sleep(200);
		
		
		//Surname
		//capture the validation msg
		String vMsg7 = driver.findElement(By.xpath(".//*[@id='data[personal_details][surname][]-error']")).getText();
		String vMsg8 = "This field is required.";
		Assert.assertEquals(vMsg7, vMsg8);
		driver.findElement(By.name("data[personal_details][surname][]")).clear();
		driver.findElement(By.name("data[personal_details][surname][]")).sendKeys("ApplicationsCompanyLoan");
		Thread.sleep(200);
		
		
		//Gender
		//capture the validation msg
		String vMsg9 = driver.findElement(By.xpath(".//*[@id='data[personal_details][gender1][]-error']")).getText();
		String vMsg10 = "This field is required.";
		Assert.assertEquals(vMsg9, vMsg10);
		List <WebElement> genders = driver.findElements(By.name("data[personal_details][gender1][]"));
		int gender = genders.size();       
		System.out.println(gender);
		genders.get(0).click();
		Thread.sleep(200);
	
		
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
		
		
		//Marital Status
		//capture the validation msg
		String vMsg13 = driver.findElement(By.xpath(".//*[@id='data[personal_details][marital_status][]-error']")).getText();
		String vMsg14 = "This field is required.";
		Assert.assertEquals(vMsg13, vMsg14);
		WebElement gender1 = driver.findElement(By.name("data[personal_details][marital_status][]"));
		Select g = new Select(gender1);
		g.selectByVisibleText("Single");
		Thread.sleep(500);
		
		//Licence Type 
				//capture the validation msg
				String vMsg13b = driver.findElement(By.xpath(".//*[@id='data[personal_details][licence_type][]-error']")).getText();
				String vMsg14b = "This field is required.";   
				Assert.assertEquals(vMsg13b, vMsg14b);
				WebElement gender1b = driver.findElement(By.name("data[personal_details][licence_type][]"));
				Select gb = new Select(gender1b);                 
				gb.selectByVisibleText("Full");
				Thread.sleep(500);
				
				//Licence State
				String vMsg15c = driver.findElement(By.xpath(".//*[@id='data[personal_details][licence_state][]-error']")).getText();
				String vMsg16c = "This field is required.";
				Assert.assertEquals(vMsg15c, vMsg16c);
				WebElement gender1c = driver.findElement(By.name("data[personal_details][licence_state][]"));
				Select gc = new Select(gender1c);
				gc.selectByVisibleText("NSW");
				Thread.sleep(500);
		
		//Drivers License No
		//capture the validation msg
		String vMsg15 = driver.findElement(By.xpath(".//*[@id='data[personal_details][driver_license_no][]-error']")).getText();
		String vMsg16 = "This field is required.";
		Assert.assertEquals(vMsg15, vMsg16);
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).clear();
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).sendKeys("8451245");
		Thread.sleep(200);
		
		
		//Expiry 
		//capture the validation msg
		/*String vMsg17 = driver.findElement(By.xpath(".//*[@id='data[personal_details][expiry][]-error']")).getText();
		String vMsg18 = "This field is required.";
		Assert.assertEquals(vMsg17, vMsg18);*/
		WebElement expiry = driver.findElement(By.name("data[personal_details][expiry][]"));
		expiry.clear();                                 
		expiry.sendKeys("05/05/2020");
		Thread.sleep(200);
		expiry.sendKeys(Keys.TAB);
		
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
				//capture the validation msg
				/*String vMsg27 = driver.findElement(By.xpath(".//*[@id='data[personal_details][home_phone][]-error']")).getText();
				String vMsg28 = "This field is required.";
				Assert.assertEquals(vMsg27, vMsg28);*/
				driver.findElement(By.name("data[personal_details][home_phone][]")).clear();
				driver.findElement(By.name("data[personal_details][home_phone][]")).sendKeys("02356784512");
				Thread.sleep(200);
		

		
		//Age of Department
		Select drop5 = new Select(driver.findElement(By.name("data[personal_details][no_of_department][]")));
		drop5.selectByVisibleText("2");
		Thread.sleep(200);
		
		
		//Residency Status
		//capture the validation msg
		String vMsg21 = driver.findElement(By.xpath(".//*[@id='data[personal_details][residential_status1][]-error']")).getText();
		String vMsg22 = "This field is required.";
		Assert.assertEquals(vMsg21, vMsg22);
		List <WebElement> residency = driver.findElements(By.name("data[personal_details][residential_status1][]"));
		int ststus = residency.size();       
		System.out.println(ststus);
		residency.get(1).click();
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next1']")).click();
		Thread.sleep(2000);
				
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,950)", "");
		Thread.sleep(3000);
		
		//next button for validation check
		driver.findElement(By.xpath(".//*[@id='next2']")).click();
		Thread.sleep(3000);   
		
		//scroll up
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-850)", "");
		Thread.sleep(5000);
		
		//Current Address Details - Current Address
		//capture the validation msg
		String vMsg351 = driver.findElement(By.xpath(".//*[@id='data[address_details][current_address][]-error']")).getText();
		String vMsg361 = "This field is required.";
		Assert.assertEquals(vMsg351, vMsg361);
		driver.findElement(By.name("data[address_details][current_address][]")).clear();
		driver.findElement(By.name("data[address_details][current_address][]")).sendKeys("Aundh, Pune");
		Thread.sleep(200);
		
		//suburb
		//capture the validation msg
		String vMsg371 = driver.findElement(By.xpath(".//*[@id='data[address_details][suburb][]-error']")).getText();
		String vMsg381 = "This field is required.";
		Assert.assertEquals(vMsg371, vMsg381);
		driver.findElement(By.name("data[address_details][suburb][]")).clear();
		driver.findElement(By.name("data[address_details][suburb][]")).sendKeys("Aundh");
		Thread.sleep(200);
		
		//State
		//capture the validation msg
		String vMsg391 = driver.findElement(By.xpath(".//*[@id='data[address_details][state][]-error']")).getText();
		String vMsg401 = "This field is required.";
		Assert.assertEquals(vMsg391, vMsg401);
		WebElement state11 = driver.findElement(By.name("data[address_details][state][]"));
    	Select states11 = new Select (state11);
    	states11.selectByVisibleText("NSW");
    	Thread.sleep(2000);
		
		//Postal Code
    	//capture the validation msg
		String vMsg411 = driver.findElement(By.xpath(".//*[@id='data[address_details][postal_code][]-error']")).getText();
		String vMsg421 = "This field is required.";
		Assert.assertEquals(vMsg411, vMsg421);
		driver.findElement(By.name("data[address_details][postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][postal_code][]")).sendKeys("45121");
		Thread.sleep(200);          
		
		//Time At Address
		WebElement years11 = driver.findElement(By.name("data[address_details][time_at_address_yrs][]"));
    	Select year11 = new Select (years11);
    	year11.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months11 = driver.findElement(By.name("data[address_details][time_at_address_months][]"));
    	Select month11 = new Select (months11);
    	month11.selectByVisibleText("4 Months");
    	Thread.sleep(1000);
		
		//Current Resident Type
    	//capture the validation msg
		String vMsg43 = driver.findElement(By.xpath(".//*[@id='data[address_details][residential_type1][]-error']")).getText();
		String vMsg44 = "This field is required.";
		Assert.assertEquals(vMsg43, vMsg44);
		List <WebElement> cResidency = driver.findElements(By.name("data[address_details][residential_type1][]"));
		int type = cResidency.size();                                                         
		System.out.println(type);
		cResidency.get(0).click();
		Thread.sleep(200);
		
		//Prev Time At Address
		WebElement years21 = driver.findElement(By.name("data[address_details][prev_time_at_address_yrs][]"));
    	Select year21 = new Select (years21);             
    	year21.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months21 = driver.findElement(By.name("data[address_details][prev_time_at_address_months][]"));
    	Select month21 = new Select (months21);
    	month21.selectByVisibleText("4 Months");
    	Thread.sleep(1000);
		
		//Previous Address Details - Previous Address
    	//capture the validation msg
		String vMsg451 = driver.findElement(By.xpath(".//*[@id='data[address_details][previous_address][]-error']")).getText();
		String vMsg461 = "This field is required.";
		Assert.assertEquals(vMsg451, vMsg461);
		driver.findElement(By.name("data[address_details][previous_address][]")).clear();
		driver.findElement(By.name("data[address_details][previous_address][]")).sendKeys("Station, Pune");
		Thread.sleep(200);
		
		//suburb
		//capture the validation msg
		String vMsg471 = driver.findElement(By.xpath(".//*[@id='data[address_details][previous_suburb][]-error']")).getText();
		String vMsg481 = "This field is required.";
		Assert.assertEquals(vMsg471, vMsg481);
		driver.findElement(By.name("data[address_details][previous_suburb][]")).clear();
		driver.findElement(By.name("data[address_details][previous_suburb][]")).sendKeys("Station");
		Thread.sleep(200);
		
		//State
		//capture the validation msg
		String vMsg491 = driver.findElement(By.xpath(".//*[@id='data[address_details][prev_state][]-error']")).getText();
		String vMsg501 = "This field is required.";
		Assert.assertEquals(vMsg491, vMsg501);
				WebElement state111 = driver.findElement(By.name("data[address_details][prev_state][]"));
		    	Select states111 = new Select (state111);
		    	states111.selectByVisibleText("NSW");
		    	Thread.sleep(2000);
		
		//Postal Code
		    	String vMsg5111 = driver.findElement(By.xpath(".//*[@id='data[address_details][previous_postal_code][]-error']")).getText();
				String vMsg5211 = "This field is required.";
				Assert.assertEquals(vMsg5111, vMsg5211);
		driver.findElement(By.name("data[address_details][previous_postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][previous_postal_code][]")).sendKeys("45254");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next2']")).click();
		Thread.sleep(2000);
		
		
		//Vehicle Details
		//next
		driver.findElement(By.xpath(".//*[@id='next10']")).click();
		Thread.sleep(2000);
		
		//Make
			String err22 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][make][]-error']")).getText();
	    	String err23 = "This field is required.";
	    	Assert.assertEquals(err22, err23);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][make][]")).clear();
	    	Thread.sleep(500);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][make][]")).sendKeys("Honda");
	    	Thread.sleep(500);
			
			//Model
	    	String err24 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][model][]-error']")).getText();
	    	String err25 = "This field is required.";
	    	Assert.assertEquals(err24, err25);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][model][]")).clear();
	    	Thread.sleep(500);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][model][]")).sendKeys("ModelTest");
	    	Thread.sleep(500);
			
			//Build Date
	    	WebElement web13 = driver.findElement(By.name("data[commercial_new_vehicle_detail][year][]"));
	    	Select webb13 = new Select(web13);            
	    	webb13.selectByVisibleText("2005");
	    	Thread.sleep(1000);
	    	
	    	//KMs
	    	String err26 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][kms][]-error']")).getText();
	    	String err27 = "This field is required.";
	    	Assert.assertEquals(err26, err27);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][kms][]")).clear();
	    	Thread.sleep(500);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][kms][]")).sendKeys("100");
	    	Thread.sleep(500);
	    	
	    	//Amount to Finance
	    	String err28 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][amount_finance][]-error']")).getText();
	    	String err29 = "This field is required.";
	    	Assert.assertEquals(err28, err29);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][amount_finance][]")).clear();
	    	Thread.sleep(500);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][amount_finance][]")).sendKeys("100");
	    	Thread.sleep(500);
	    	
	    	//Term (Yrs)
	    	WebElement web14 = driver.findElement(By.name("data[new_vehicle_detail][term][]"));
	    	Select webb14 = new Select(web14);            
	    	webb14.selectByVisibleText("2");
	    	Thread.sleep(1000);
	    	
	    	//Balloon/Residual ($ or %)
	    	String err30 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][baloon][]-error']")).getText();
	    	String err31 = "This field is required.";
	    	Assert.assertEquals(err30, err31);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][baloon][]")).clear();
	    	Thread.sleep(500);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][baloon][]")).sendKeys("10");
	    	Thread.sleep(500);
	    	
	    	//Dealership/Vendor
	    	String err32 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][dealership][]-error']")).getText();
	    	String err33 = "This field is required.";   
	    	Assert.assertEquals(err32, err33);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][dealership][]")).clear();
	    	Thread.sleep(500);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][dealership][]")).sendKeys("TestDel");
	    	Thread.sleep(500);
	    	
	    	//Phone no
	    	String err34 = driver.findElement(By.xpath(".//*[@id='data[commercial_new_vehicle_detail][phone_number][]-error']")).getText();
	    	String err35 = "This field is required.";   
	    	Assert.assertEquals(err34, err35);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][phone_number][]")).clear();
	    	Thread.sleep(500);
	    	driver.findElement(By.name("data[commercial_new_vehicle_detail][phone_number][]")).sendKeys("9762115212");
	    	Thread.sleep(500);
		
		
	    	//next
			driver.findElement(By.xpath(".//*[@id='next10']")).click();
			Thread.sleep(3000);
		
		
		
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,950)", "");
		Thread.sleep(3000);
		
		//next button for validation check
		driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[4]/div/button")).click();
		Thread.sleep(3000);           
		
		//scroll up
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-850)", "");
		Thread.sleep(5000);
	  	      
			
			Robot rs = new Robot();
			rs.keyPress(KeyEvent.VK_TAB);
			rs.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			
			//Payslip 1
			String vMsg10711 = driver.findElement(By.xpath(".//*[@id='docDriverLic1-error']")).getText();
			String vMsg10811 = "This field is required.";   
			Assert.assertEquals(vMsg10711, vMsg10811);
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[2]/div/div/div/span/label/span")).click();
	    	driver.switchTo()                              
		            .activeElement()
		            .sendKeys(
		                    "/home/nadsoft/Desktop/Backup/ISTQB/20MB.pdf");
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    Thread.sleep(1000);
		    Robot r3 = new Robot();
		    r3.keyPress(KeyEvent.VK_ESCAPE);
		    r3.keyRelease(KeyEvent.VK_ESCAPE);
		    
		    //scroll down
			jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)", "");
			Thread.sleep(1000);
		    
			
			try {
				WebElement e = driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[4]/div/button"));
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
				WebElement e1 = driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[4]/div/button"));
			    boolean actualValue1 = e1.isEnabled();
			    if (actualValue1){
			           System.out.println("Button is enabled");
			    }else
			           System.out.println("Button is disabled");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
			System.out.println("Pass");
			
		  //Payslip 2
		    driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[3]/div/div/div/span/label/span")).click();
	      	driver.switchTo()            
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-2. paper-2.pdf");
	  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	    Thread.sleep(5000);
	  	    r3.keyPress(KeyEvent.VK_ESCAPE);
	  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
	  	    Thread.sleep(2000); 
		    
		  //Payslip 2
		    driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[4]/div/div/div/span/label/span")).click();
	      	driver.switchTo()
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-2. paper-2.pdf");
	  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	    Thread.sleep(5000);
	  	    r3.keyPress(KeyEvent.VK_ESCAPE);
	  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
	  	    Thread.sleep(2000);
		  	    
		  //Copy of Driver's Licenses*	
	  	  driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[5]/div/div/div/span/label/span")).click();
	      	driver.switchTo()
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-3.pdf");
	  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	    Thread.sleep(5000);
	  	    r3.keyPress(KeyEvent.VK_ESCAPE);
	  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
	  	    Thread.sleep(2000);
		  	    
		  /*//Rates Notices for Home Owner (If applicable)	
	  	  driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[5]/div/div/div/span/label/span")).click();
	      	driver.switchTo()
	  	            .activeElement()
	  	            .sendKeys(
	  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-4.pdf");
	  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	    Thread.sleep(5000);
	  	    r3.keyPress(KeyEvent.VK_ESCAPE);
	  	    r3.keyRelease(KeyEvent.VK_ESCAPE);
	  	    Thread.sleep(2000);*/
		  	    
		  //admin note
	  	    driver.findElement(By.name("data[personal_details][anote][]")).clear();
	  	  driver.findElement(By.name("data[personal_details][anote][]")).sendKeys("Test note");
	  	  
		/*driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[6]/div/button")).click();
		Thread.sleep(20000);
		
		//Dashboard
		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
		Thread.sleep(2000);
		
		//Applications 
		driver.findElement(By.xpath(".//*[@id='side-menu']/li[7]/a")).click();
		Thread.sleep(2000);
		//View All  
		driver.findElement(By.xpath(".//*[@id='side-menu']/li[7]/ul/li[3]/a")).click();
		Thread.sleep(2000);
		
		String actual1 = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
		System.out.println(actual1);
		String expectd1 = "NDA";
		Assert.assertEquals(actual1, expectd1);
		Thread.sleep(2000);
		
		System.out.println("Business form add successfull");
		
		System.out.println("====================================================================");*/
 	}
 	
 	public void editBusinessLoanApplication() throws InterruptedException{
 		
 		System.out.println("Edit business application form");
 		
 		//Dashboard
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
 		Thread.sleep(2000);
 				
 		//Applications 
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/a")).click();
 		Thread.sleep(2000);
 		//View All  
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/ul/li[1]/a")).click();
 		Thread.sleep(20000);
 		
 		//edit
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[12]/div/button[3]")).click();
 		Thread.sleep(2000);                            
 		
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div/div/div/div/div/a[1]")).click();
 		Thread.sleep(8000);

 		
 		//Tab-1  checkbox
 		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div/div/div[2]/div/div/div[1]/div/div/label[1]/input")).click();
 		Thread.sleep(200);                         
 		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div/div/div[2]/div/div/div[1]/div/div/label[2]/input")).click();
 		Thread.sleep(200);
 		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div/div/div[2]/div/div/div[1]/div/div/label[3]/input")).click();
 		Thread.sleep(200);
 		driver.findElement(By.xpath(".//*[@id='step-1']/div[1]/div/div/div[2]/div/div/div[1]/div/div/label[4]/input")).click();
 		Thread.sleep(200);
 		
 		//Name
 		driver.findElement(By.name("data[anz_relationship][name]")).clear();
		driver.findElement(By.name("data[anz_relationship][name]")).sendKeys("Amol Patil");
		Thread.sleep(200);          
		//Phone
		driver.findElement(By.name("data[anz_relationship][phone]")).clear();
		driver.findElement(By.name("data[anz_relationship][phone]")).sendKeys("8956124578");
		Thread.sleep(200);
		//Email
		driver.findElement(By.name("data[anz_relationship][email]")).clear();
		driver.findElement(By.name("data[anz_relationship][email]")).sendKeys("amoltest@test.com");
		Thread.sleep(200);
		//Fax
		driver.findElement(By.name("data[anz_relationship][fax]")).clear();
		driver.findElement(By.name("data[anz_relationship][fax]")).sendKeys("5623124578");
		Thread.sleep(200);
		
		//BROKER DETAILS - Broker Firm
		driver.findElement(By.name("data[broker_details][firm]")).clear();
		driver.findElement(By.name("data[broker_details][firm]")).sendKeys("JKLDoft");
		Thread.sleep(200);
		//Source of Business Number (SOB) 
		driver.findElement(By.name("data[broker_details][sob]")).clear();
		driver.findElement(By.name("data[broker_details][sob]")).sendKeys("451256");
		Thread.sleep(200);
		//Phone Number
		driver.findElement(By.name("data[broker_details][phone]")).clear();
		driver.findElement(By.name("data[broker_details][phone]")).sendKeys("8945785612");
		Thread.sleep(200);
		//Fax Number
		driver.findElement(By.name("data[broker_details][fax]")).clear();
		driver.findElement(By.name("data[broker_details][fax]")).sendKeys("5612457812");
		Thread.sleep(200);
		//Broker Name
		driver.findElement(By.name("data[broker_details][name]")).clear();
		driver.findElement(By.name("data[broker_details][name]")).sendKeys("Pankaj Desai");
		Thread.sleep(200);
		//Date
		WebElement birthDate1 = driver.findElement(By.name("data[broker_details][date]"));
		birthDate1.clear();
		birthDate1.sendKeys("01/05/1981");
		Thread.sleep(200);
		//Mobile
		driver.findElement(By.name("data[broker_details][mobile]")).clear();
		driver.findElement(By.name("data[broker_details][mobile]")).sendKeys("9632587412");
		Thread.sleep(200);
		//Email
		driver.findElement(By.name("data[broker_details][email]")).clear();
		driver.findElement(By.name("data[broker_details][email]")).sendKeys("pankajtest@test.com");
		Thread.sleep(200);
		
		//APPLICANT DETAILS - Applicant Type
		List <WebElement> e1 = driver.findElements(By.name("data[applicant_details][app_type]"));
		int i1 = e1.size();       
		System.out.println(i1);
		e1.get(2).click();
		Thread.sleep(200);
		//ABN
		driver.findElement(By.name("data[applicant_details][abn]")).clear();
		driver.findElement(By.name("data[applicant_details][abn]")).sendKeys("451225");
		Thread.sleep(200);
		//ACN
		driver.findElement(By.name("data[applicant_details][acn]")).clear();
		driver.findElement(By.name("data[applicant_details][acn]")).sendKeys("451212");
		Thread.sleep(200);
		//Trading Name
		driver.findElement(By.name("data[applicant_details][trading_name]")).clear();
		driver.findElement(By.name("data[applicant_details][trading_name]")).sendKeys("Test Business Trade");
		Thread.sleep(200);
		//Trust Name
		driver.findElement(By.name("data[applicant_details][trust_name]")).clear();
		driver.findElement(By.name("data[applicant_details][trust_name]")).sendKeys("NDA Trust");
		Thread.sleep(200);
		//Type of Trust (ie. Family Trust, Discretionary Trust etc)
		driver.findElement(By.name("data[applicant_details][type_of_trust]")).clear();
		driver.findElement(By.name("data[applicant_details][type_of_trust]")).sendKeys("Family");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[applicant_details][suburb]")).clear();
		driver.findElement(By.name("data[applicant_details][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		//State / Territory
		/*WebElement state = driver.findElement(By.name("data[applicant_details][state_territory]"));
    	Select states = new Select (state);
    	states.selectByVisibleText("NSW");
    	Thread.sleep(2000);*/
		driver.findElement(By.name("data[applicant_details][state_territory]")).clear();
		driver.findElement(By.name("data[applicant_details][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);          
		//Postcode
		driver.findElement(By.name("data[applicant_details][postcode]")).clear();
		driver.findElement(By.name("data[applicant_details][postcode]")).sendKeys("455612");
		Thread.sleep(200);
		//Total Number of Directors/Partners
		driver.findElement(By.name("data[applicant_details][total_number_of_directors_partners]")).clear();
		driver.findElement(By.name("data[applicant_details][total_number_of_directors_partners]")).sendKeys("1");
		Thread.sleep(200);
		//Fax Number
		driver.findElement(By.name("data[applicant_details][fax_number]")).clear();
		driver.findElement(By.name("data[applicant_details][fax_number]")).sendKeys("7856124545");
		Thread.sleep(200);
		//Country of Establishment
		driver.findElement(By.xpath(".//*[@id='step-1']/div[4]/div/div/div[2]/div[3]/div[12]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-1']/div[4]/div/div/div[2]/div[3]/div[12]/input")).sendKeys("India");
		Thread.sleep(200);
		//Nature of Business
		driver.findElement(By.name("data[applicant_details][nature_of_business]")).clear();
		driver.findElement(By.name("data[applicant_details][nature_of_business]")).sendKeys("Trading");
		Thread.sleep(200);
		//Time in Operation
		driver.findElement(By.name("data[applicant_details][time_in_operation][year]")).clear();
		driver.findElement(By.name("data[applicant_details][time_in_operation][year]")).sendKeys("2");
		Thread.sleep(200);
		driver.findElement(By.name("data[applicant_details][time_in_operation][months]")).clear();
		driver.findElement(By.name("data[applicant_details][time_in_operation][months]")).sendKeys("3");
		Thread.sleep(200);
		//Existing Esanda Customer?
		driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/div/div/form/div[1]/div[4]/div/div/div[2]/div[3]/div[14]/label[2]/input")).click();
		Thread.sleep(200);
		//If Yes, please provide Contract No. 
		driver.findElement(By.xpath(".//*[@id='step-1']/div[4]/div/div/div[2]/div[3]/div[15]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-1']/div[4]/div/div/div[2]/div[3]/div[15]/input")).sendKeys("8956451245");
		Thread.sleep(200);
		//Registration No.
		driver.findElement(By.xpath(".//*[@id='step-1']/div[4]/div/div/div[2]/div[3]/div[16]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-1']/div[4]/div/div/div[2]/div[3]/div[16]/input")).sendKeys("4536113");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-1']/div[4]/div/div/div[3]/div/button")).click();
		Thread.sleep(2000);          
		
		//Tab-2
		//1st Director / Proprietor – Details
		List <WebElement> e2 = driver.findElements(By.name("data[1st_director_proprietor_details][1st_director_proprietor_details_option]"));
		int i2 = e2.size();       
		System.out.println(i2);
		e2.get(1).click();
		Thread.sleep(200);	
		//First Name
		driver.findElement(By.name("data[1st_director_proprietor_details][firstname]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][firstname]")).sendKeys("Sachin");
		Thread.sleep(200);
		//Second Name
		driver.findElement(By.name("data[1st_director_proprietor_details][second_name]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][second_name]")).sendKeys("Prakash");
		Thread.sleep(200);
		//Surname
		driver.findElement(By.name("data[1st_director_proprietor_details][surname]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][surname]")).sendKeys("Sawant");
		Thread.sleep(200);
		//Date of Birth 
		WebElement dobDate1 = driver.findElement(By.name("data[1st_director_proprietor_details][dob]"));
		dobDate1.clear();
		dobDate1.sendKeys("01/02/1990");
		Thread.sleep(200);
		dobDate1.sendKeys(Keys.TAB);
		//Privacy Consent
		List <WebElement> e3 = driver.findElements(By.name("data[1st_director_proprietor_details][privacy_consent]"));
		int i3 = e3.size();       
		System.out.println(i3);
		e3.get(1).click();
		Thread.sleep(200);
		//Address
		driver.findElement(By.name("data[1st_director_proprietor_details][address]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][address]")).sendKeys("RK nagar, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[1st_director_proprietor_details][suburb_1]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][suburb_1]")).sendKeys("RK nagar");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[1st_director_proprietor_details][state_territory_1]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][state_territory_1]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[1st_director_proprietor_details][postcode_1]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][postcode_1]")).sendKeys("4589521");
		Thread.sleep(200);
		//Duration at Address
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_at_address][year]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_at_address][year]")).sendKeys("2");
		Thread.sleep(200);
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_at_address][month]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_at_address][month]")).sendKeys("2");
		Thread.sleep(200);
		//Gender
		List <WebElement> e4 = driver.findElements(By.name("data[1st_director_proprietor_details][gender]"));
		int i4 = e4.size();       
		System.out.println(i4);
		e4.get(0).click();
		Thread.sleep(200);
		//Previous Address (if less than 3 years in current) 
		driver.findElement(By.name("data[1st_director_proprietor_details][previous_address]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][previous_address]")).sendKeys("Aundh, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[1st_director_proprietor_details][suburb_2]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][suburb_2]")).sendKeys("Aundh");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[1st_director_proprietor_details][state_territory_2]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][state_territory_2]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[1st_director_proprietor_details][postcode_2]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][postcode_2]")).sendKeys("89563");
		Thread.sleep(200);
		//Duration at Address
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_at_previous_address][year]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_at_previous_address][year]")).sendKeys("3");
		Thread.sleep(200);
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_at__previous_address][month]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_at__previous_address][month]")).sendKeys("2");
		Thread.sleep(200);
		//Marital Status
		driver.findElement(By.name("data[1st_director_proprietor_details][marital_status]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][marital_status]")).sendKeys("Single");
		Thread.sleep(200);
		//Number of Dependents
		driver.findElement(By.name("data[1st_director_proprietor_details][number_of_dependents]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][number_of_dependents]")).sendKeys("2");
		Thread.sleep(200);
		//Mobile
		driver.findElement(By.name("data[1st_director_proprietor_details][mobile]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][mobile]")).sendKeys("7845561245");
		Thread.sleep(200);
		//Time as Director/Proprietor of Company
		driver.findElement(By.name("data[1st_director_proprietor_details][time_as_director_Proprietor_company][year]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][time_as_director_Proprietor_company][year]")).sendKeys("1");
		Thread.sleep(200);
		driver.findElement(By.name("data[1st_director_proprietor_details][time_as_director_Proprietor_company][month]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][time_as_director_Proprietor_company][month]")).sendKeys("3");
		Thread.sleep(200);
		//Telephone
		driver.findElement(By.name("data[1st_director_proprietor_details][telephone]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][telephone]")).sendKeys("8945781245");
		Thread.sleep(200);
		//Drivers License Number
		driver.findElement(By.name("data[1st_director_proprietor_details][drivers_licence_number]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][drivers_licence_number]")).sendKeys("645213");
		Thread.sleep(200);
		//Residential Status
		List <WebElement> addres = driver.findElements(By.name("data[1st_director_proprietor_details][residential_status]"));
		int a = addres.size();
		System.out.println(a);
		addres.get(2).click();
		Thread.sleep(200);
		//Previous Employment (if less than 3 years in current) Occupation
		driver.findElement(By.name("data[1st_director_proprietor_details][previous_employment ]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][previous_employment ]")).sendKeys("2");
		Thread.sleep(200);
		//Employer
		driver.findElement(By.name("data[1st_director_proprietor_details][Employer]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][Employer]")).sendKeys("SWASoft");
		Thread.sleep(200);
		//Duration of Employment
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_of_employment][year]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_of_employment][year]")).sendKeys("2");
		Thread.sleep(200);
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_of_employment][month]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][duration_of_employment][month]")).sendKeys("5");
		Thread.sleep(200);
		//1st Director / Proprietor - Asset Position - Personal Assets
		//Cash at Bank
		driver.findElement(By.name("data[1st_director_proprietor_details][cash_at_bank]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][cash_at_bank]")).sendKeys("1000");
		Thread.sleep(200);
		//Home Value
		driver.findElement(By.name("data[1st_director_proprietor_details][home_value]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][home_value]")).sendKeys("1000");
		Thread.sleep(200);
		//Other Property Value
		driver.findElement(By.name("data[1st_director_proprietor_details][other_property_value]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][other_property_value]")).sendKeys("1000");
		Thread.sleep(200);
		//Motor Vehicle/s
		driver.findElement(By.name("data[1st_director_proprietor_details][motor_vehicle]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][motor_vehicle]")).sendKeys("1000");
		Thread.sleep(200);
		//Plant & Equipment
		driver.findElement(By.name("data[1st_director_proprietor_details][plant_equipment]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][plant_equipment]")).sendKeys("1000");
		Thread.sleep(200);
		//Household Effects
		driver.findElement(By.name("data[1st_director_proprietor_details][household_effects]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][household_effects]")).sendKeys("1000");
		Thread.sleep(200);
		//Business Assets
		driver.findElement(By.name("data[1st_director_proprietor_details][business_assets]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][business_assets]")).sendKeys("1000");
		Thread.sleep(200);
		//Term Deposits
		driver.findElement(By.name("data[1st_director_proprietor_details][term_deposits]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][term_deposits]")).sendKeys("1000");
		Thread.sleep(200);
		//Debentures
		driver.findElement(By.name("data[1st_director_proprietor_details][debentures]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][debentures]")).sendKeys("1000");
		Thread.sleep(200);
		//Debtors
		driver.findElement(By.name("data[1st_director_proprietor_details][debtors]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][debtors]")).sendKeys("1000");
		Thread.sleep(200);
		//Other Assets (please list)
		driver.findElement(By.name("data[1st_director_proprietor_details][other_assets_please_list]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][other_assets_please_list]")).sendKeys("1000");
		Thread.sleep(200);
		//Personal Liabilities - Home Mortgage
		driver.findElement(By.name("data[1st_director_proprietor_details][home_mortgage]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][home_mortgage]")).sendKeys("1000");
		Thread.sleep(200);
		//Other Mortgages
		driver.findElement(By.name("data[1st_director_proprietor_details][other_mortgages]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][other_mortgages]")).sendKeys("1000");
		Thread.sleep(200);
		//Creditors
		driver.findElement(By.name("data[1st_director_proprietor_details][creditors]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][creditors]")).sendKeys("1000");
		Thread.sleep(200);
		//Credit Card (limit)
		driver.findElement(By.name("data[1st_director_proprietor_details][credit_card_limit)]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][credit_card_limit)]")).sendKeys("1000");
		Thread.sleep(200);
		//Overdraft (limit)
		driver.findElement(By.name("data[1st_director_proprietor_details][overdraft_limit]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][overdraft_limit]")).sendKeys("1000");
		Thread.sleep(200);
		//Loans Outstanding
		driver.findElement(By.name("data[1st_director_proprietor_details][loans_outstanding]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][loans_outstanding]")).sendKeys("1000");
		Thread.sleep(200);
		//Other Liabilities (please list)
		driver.findElement(By.name("data[1st_director_proprietor_details][other_liabilities_list]")).clear();
		driver.findElement(By.name("data[1st_director_proprietor_details][other_liabilities_list]")).sendKeys("1000");
		Thread.sleep(200);
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(1000);
		//next
		driver.findElement(By.xpath(".//*[@id='step-2']/div[2]/div/div/div[2]/div/button[2]")).click();
		Thread.sleep(1000);
		
		//Tab-3
		//1st Director / Proprietor – Details
		List <WebElement> e5 = driver.findElements(By.name("data[2nd_director_proprietor_details][2nd_director_proprietor_details_option]"));
		int i5 = e5.size();                                     
		System.out.println(i5);
		e5.get(0).click();
		Thread.sleep(200);	
				//First Name
				driver.findElement(By.name("data[2nd_director_proprietor_details][firstname]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][firstname]")).sendKeys("Virat");
				Thread.sleep(200);
				//Second Name
				driver.findElement(By.name("data[2nd_director_proprietor_details][second_name]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][second_name]")).sendKeys("Ramesh");
				Thread.sleep(200);
				//Surname
				driver.findElement(By.name("data[2nd_director_proprietor_details][surname]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][surname]")).sendKeys("Joshi");
				Thread.sleep(200);
				//Date of Birth 
				WebElement dobDate2 = driver.findElement(By.name("data[2nd_director_proprietor_details][dob]"));
				dobDate2.clear();
				dobDate2.sendKeys("01/02/1990");
				Thread.sleep(200);
				dobDate2.sendKeys(Keys.TAB);
				//Privacy Consent
				List <WebElement> e8 = driver.findElements(By.name("data[2nd_director_proprietor_details][privacy_consent]"));
				int i8 = e8.size();       
				System.out.println(i8);
				e8.get(1).click();
				Thread.sleep(200);
				//Address
				driver.findElement(By.name("data[2nd_director_proprietor_details][address]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][address]")).sendKeys("JK files, Pune");
				Thread.sleep(200);          
				//Suburb
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[7]/div[1]/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[7]/div[1]/input")).sendKeys("JK files");
				Thread.sleep(200);          
				//State / Territory
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[7]/div[2]/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[7]/div[2]/input")).sendKeys("Maharashtra");
				Thread.sleep(200);
				//Postcode
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[7]/div[3]/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[7]/div[3]/input")).sendKeys("4589521");
				Thread.sleep(200);
				//Duration at Address
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_at_address][year]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_at_address][year]")).sendKeys("2");
				Thread.sleep(200);
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_at_address][month]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_at_address][month]")).sendKeys("2");
				Thread.sleep(200);
				//Gender
				List <WebElement> e9 = driver.findElements(By.name("data[2nd_director_proprietor_details][gender]"));
				int i9 = e9.size();       
				System.out.println("gender" +i9);
				e9.get(0).click();
				Thread.sleep(200);
				//Previous Address (if less than 3 years in current) 
				driver.findElement(By.name("data[2nd_director_proprietor_details][previous_address]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][previous_address]")).sendKeys("Aundh, Pune");
				Thread.sleep(200);
				//Suburb
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[8]/div[1]/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[8]/div[1]/input")).sendKeys("Aundh");
				Thread.sleep(200);
				//State / Territory
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[8]/div[2]/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[8]/div[2]/input")).sendKeys("Maharashtra");
				Thread.sleep(200);
				//Postcode
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[8]/div[3]/input")).clear();
				driver.findElement(By.xpath(".//*[@id='step-3']/div[1]/div/div/div/div[2]/div[8]/div[3]/input")).sendKeys("89563");
				Thread.sleep(200);
				//Duration at Address
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_at_previous_address][year]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_at_previous_address][year]")).sendKeys("3");
				Thread.sleep(200);
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_at__previous_address][month]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_at__previous_address][month]")).sendKeys("2");
				Thread.sleep(200);
				//Marital Status
				driver.findElement(By.name("data[2nd_director_proprietor_details][marital_status]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][marital_status]")).sendKeys("Single");
				Thread.sleep(200);
				//Number of Dependents
				driver.findElement(By.name("data[2nd_director_proprietor_details][number_of_dependents]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][number_of_dependents]")).sendKeys("2");
				Thread.sleep(200);
				//Mobile
				driver.findElement(By.name("data[2nd_director_proprietor_details][mobile]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][mobile]")).sendKeys("7445561245");
				Thread.sleep(200);
				//Time as Director/Proprietor of Company
				driver.findElement(By.name("data[2nd_director_proprietor_details][time_as_director_Proprietor_company][year]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][time_as_director_Proprietor_company][year]")).sendKeys("1");
				Thread.sleep(200);
				driver.findElement(By.name("data[2nd_director_proprietor_details][time_as_director_Proprietor_company][month]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][time_as_director_Proprietor_company][month]")).sendKeys("3");
				Thread.sleep(200);
				//Telephone
				driver.findElement(By.name("data[2nd_director_proprietor_details][telephone]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][telephone]")).sendKeys("8945781245");
				Thread.sleep(200);
				//Drivers License Number
				driver.findElement(By.name("data[2nd_director_proprietor_details][drivers_licence_number]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][drivers_licence_number]")).sendKeys("855213");
				Thread.sleep(200);
				//Residential Status
				List <WebElement> addres1 = driver.findElements(By.name("data[2nd_director_proprietor_details][residential_status]"));
				int a1 = addres.size();
				System.out.println(a1);
				addres1.get(2).click();
				Thread.sleep(200);
				//Previous Employment (if less than 3 years in current) Occupation
				driver.findElement(By.name("data[2nd_director_proprietor_details][previous_employment ]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][previous_employment ]")).sendKeys("2");
				Thread.sleep(200);
				//Employer
				driver.findElement(By.name("data[2nd_director_proprietor_details][Employer]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][Employer]")).sendKeys("SWDSoft");
				Thread.sleep(200);
				//Duration of Employment
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_of_employment][year]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_of_employment][year]")).sendKeys("2");
				Thread.sleep(200);
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_of_employment][month]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][duration_of_employment][month]")).sendKeys("5");
				Thread.sleep(200);
				//1st Director / Proprietor - Asset Position - Personal Assets
				//Cash at Bank
				driver.findElement(By.name("data[2nd_director_proprietor_details][cash_at_bank]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][cash_at_bank]")).sendKeys("1000");
				Thread.sleep(200);
				//Home Value
				driver.findElement(By.name("data[2nd_director_proprietor_details][home_value]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][home_value]")).sendKeys("1000");
				Thread.sleep(200);
				//Other Property Value
				driver.findElement(By.name("data[2nd_director_proprietor_details][other_property_value]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][other_property_value]")).sendKeys("1000");
				Thread.sleep(200);
				//Motor Vehicle/s
				driver.findElement(By.name("data[2nd_director_proprietor_details][motor_vehicle]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][motor_vehicle]")).sendKeys("1000");
				Thread.sleep(200);
				//Plant & Equipment
				driver.findElement(By.name("data[2nd_director_proprietor_details][plant_equipment]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][plant_equipment]")).sendKeys("1000");
				Thread.sleep(200);
				//Household Effects
				driver.findElement(By.name("data[2nd_director_proprietor_details][household_effects]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][household_effects]")).sendKeys("1000");
				Thread.sleep(200);
				//Business Assets
				driver.findElement(By.name("data[2nd_director_proprietor_details][business_assets]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][business_assets]")).sendKeys("1000");
				Thread.sleep(200);
				//Term Deposits
				driver.findElement(By.name("data[2nd_director_proprietor_details][term_deposits]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][term_deposits]")).sendKeys("1000");
				Thread.sleep(200);
				//Debentures
				driver.findElement(By.name("data[2nd_director_proprietor_details][debentures]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][debentures]")).sendKeys("1000");
				Thread.sleep(200);          
				//Debtors
				driver.findElement(By.name("data[2nd_director_proprietor_details][debtors]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][debtors]")).sendKeys("1000");
				Thread.sleep(200);
				//Other Assets (please list)
				driver.findElement(By.name("data[2nd_director_proprietor_details][other_assets_please_list]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][other_assets_please_list]")).sendKeys("1000");
				Thread.sleep(200);
				//Personal Liabilities - Home Mortgage
				driver.findElement(By.name("data[2nd_director_proprietor_details][home_mortgage]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][home_mortgage]")).sendKeys("1000");
				Thread.sleep(200);
				//Other Mortgages
				driver.findElement(By.name("data[2nd_director_proprietor_details][other_mortgages]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][other_mortgages]")).sendKeys("1000");
				Thread.sleep(200);
				//Creditors
				driver.findElement(By.name("data[2nd_director_proprietor_details][creditors]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][creditors]")).sendKeys("1000");
				Thread.sleep(200);
				//Credit Card (limit)
				driver.findElement(By.name("data[2nd_director_proprietor_details][credit_card_limit)]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][credit_card_limit)]")).sendKeys("1000");
				Thread.sleep(200);
				//Overdraft (limit)
				driver.findElement(By.name("data[2nd_director_proprietor_details][overdraft_limit]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][overdraft_limit]")).sendKeys("1000");
				Thread.sleep(200);
				//Loans Outstanding
				driver.findElement(By.name("data[2nd_director_proprietor_details][loans_outstanding]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][loans_outstanding]")).sendKeys("1000");
				Thread.sleep(200);
				//Other Liabilities (please list)
				driver.findElement(By.name("data[2nd_director_proprietor_details][other_liabilities_list]")).clear();
				driver.findElement(By.name("data[2nd_director_proprietor_details][other_liabilities_list]")).sendKeys("1000");
				Thread.sleep(200);
				//scroll down
				jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(1000);
				//next
				driver.findElement(By.xpath(".//*[@id='step-3']/div[2]/div/div/div[2]/div/button[2]")).click();
				Thread.sleep(2000);          
		
		//Tab-4 First Name
		driver.findElement(By.name("data[additional_directories][set1][first_name]")).clear();
		driver.findElement(By.name("data[additional_directories][set1][first_name]")).sendKeys("Amar");
		Thread.sleep(200);          
		//Second Name
		driver.findElement(By.name("data[additional_directories][set1][second_name]")).clear();
		driver.findElement(By.name("data[additional_directories][set1][second_name]")).sendKeys("Suryakant");
		Thread.sleep(200);
		//Surname
		driver.findElement(By.name("data[additional_directories][set1][surname]")).clear();
		driver.findElement(By.name("data[additional_directories][set1][surname]")).sendKeys("Jadhav");
		Thread.sleep(200);
		List <WebElement> e10 = driver.findElements(By.name("data[additional_directories][set1][select]"));
		int i10 = e10.size();       
		System.out.println(i10);
		e10.get(0).click();
		Thread.sleep(200);	
		//Street Address
		driver.findElement(By.name("data[additional_directories][set1][street_address]")).clear();
		driver.findElement(By.name("data[additional_directories][set1][street_address]")).sendKeys("Wadia, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[additional_directories][set1][suburb]")).clear();
		driver.findElement(By.name("data[additional_directories][set1][suburb]")).sendKeys("Wadia");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[additional_directories][set1][state_territory]")).clear();
		driver.findElement(By.name("data[additional_directories][set1][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[additional_directories][set1][postcode]")).clear();
		driver.findElement(By.name("data[additional_directories][set1][postcode]")).sendKeys("4563221");
		Thread.sleep(200);
		
		//First Name
		driver.findElement(By.name("data[additional_directories][set2][first_name]")).clear();
		driver.findElement(By.name("data[additional_directories][set2][first_name]")).sendKeys("Sameer");
		Thread.sleep(200);
		//Second Name
		driver.findElement(By.name("data[additional_directories][set2][second_name]")).clear();
		driver.findElement(By.name("data[additional_directories][set2][second_name]")).sendKeys("Guru");
		Thread.sleep(200);
		//Surname
		driver.findElement(By.name("data[additional_directories][set2][surname]")).clear();
		driver.findElement(By.name("data[additional_directories][set2][surname]")).sendKeys("Sawant");
		Thread.sleep(200);
		List <WebElement> e11 = driver.findElements(By.name("data[additional_directories][set2][select]"));
		int i11 = e11.size();                                 
		System.out.println(i11);
		e11.get(0).click();
		Thread.sleep(200);	
		//Street Address
		driver.findElement(By.name("data[additional_directories][set2][street_address]")).clear();
		driver.findElement(By.name("data[additional_directories][set2][street_address]")).sendKeys("Camp, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[additional_directories][set2][suburb]")).clear();
		driver.findElement(By.name("data[additional_directories][set2][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[additional_directories][set2][state_territory]")).clear();
		driver.findElement(By.name("data[additional_directories][set2][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[additional_directories][set2][postcode]")).clear();
		driver.findElement(By.name("data[additional_directories][set2][postcode]")).sendKeys("4563225");
		Thread.sleep(200);
		
		//First Name
				driver.findElement(By.name("data[additional_directories][set3][first_name]")).clear();
				driver.findElement(By.name("data[additional_directories][set3][first_name]")).sendKeys("Sameer");
				Thread.sleep(200);
				//Second Name
				driver.findElement(By.name("data[additional_directories][set3][second_name]")).clear();
				driver.findElement(By.name("data[additional_directories][set3][second_name]")).sendKeys("Guru");
				Thread.sleep(200);
				//Surname
				driver.findElement(By.name("data[additional_directories][set3][surname]")).clear();
				driver.findElement(By.name("data[additional_directories][set3][surname]")).sendKeys("Desai");
				Thread.sleep(200);
				List <WebElement> e12 = driver.findElements(By.name("data[additional_directories][set3][select]"));
				int i12 = e12.size();       
				System.out.println(i12);
				e12.get(0).click();
				Thread.sleep(200);	
				//Street Address
				driver.findElement(By.name("data[additional_directories][set3][street_address]")).clear();
				driver.findElement(By.name("data[additional_directories][set3][street_address]")).sendKeys("Camp, Pune");
				Thread.sleep(200);
				//Suburb
				driver.findElement(By.name("data[additional_directories][set3][suburb]")).clear();
				driver.findElement(By.name("data[additional_directories][set3][suburb]")).sendKeys("Camp");
				Thread.sleep(200);
				//State / Territory
				driver.findElement(By.name("data[additional_directories][set3][state_territory]")).clear();
				driver.findElement(By.name("data[additional_directories][set3][state_territory]")).sendKeys("Maharashtra");
				Thread.sleep(200);
				//Postcode
				driver.findElement(By.name("data[additional_directories][set3][postcode]")).clear();
				driver.findElement(By.name("data[additional_directories][set3][postcode]")).sendKeys("4563225");
				Thread.sleep(200);
				
		//next
		driver.findElement(By.xpath(".//*[@id='step-4']/div[4]/div/div/div[2]/div/button[2]")).click();
		Thread.sleep(2000);
		
		//Tab-5
		//First Name
		driver.findElement(By.name("data[additional_trustees][set1][first_name]")).clear();
		driver.findElement(By.name("data[additional_trustees][set1][first_name]")).sendKeys("Sandip");
		Thread.sleep(200);
		//Second Name
		driver.findElement(By.name("data[additional_trustees][set1][second_name]")).clear();
		driver.findElement(By.name("data[additional_trustees][set1][second_name]")).sendKeys("Suresh");
		Thread.sleep(200);
		//Surname
		driver.findElement(By.name("data[additional_trustees][set1][surname]")).clear();
		driver.findElement(By.name("data[additional_trustees][set1][surname]")).sendKeys("Mane");
		Thread.sleep(200);
		List <WebElement> e13 = driver.findElements(By.name("data[additional_trustees][set1][select]"));
		int i13 = e13.size();       
		System.out.println(i13);
		e13.get(0).click();
		Thread.sleep(200);	
		//Street Address
		driver.findElement(By.name("data[additional_trustees][set1][street_address]")).clear();
		driver.findElement(By.name("data[additional_trustees][set1][street_address]")).sendKeys("Station, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[additional_trustees][set1][suburb]")).clear();
		driver.findElement(By.name("data[additional_trustees][set1][suburb]")).sendKeys("Station");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[additional_trustees][set1][state_territory]")).clear();
		driver.findElement(By.name("data[additional_trustees][set1][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[additional_trustees][set1][postcode]")).clear();
		driver.findElement(By.name("data[additional_trustees][set1][postcode]")).sendKeys("4563221");
		Thread.sleep(200);
		
		//First Name
		driver.findElement(By.name("data[additional_trustees][set2][first_name]")).clear();
		driver.findElement(By.name("data[additional_trustees][set2][first_name]")).sendKeys("Suraj");
		Thread.sleep(200);
		//Second Name
		driver.findElement(By.name("data[additional_trustees][set2][second_name]")).clear();
		driver.findElement(By.name("data[additional_trustees][set2][second_name]")).sendKeys("Amit");
		Thread.sleep(200);
		//Surname
		driver.findElement(By.name("data[additional_trustees][set2][surname]")).clear();
		driver.findElement(By.name("data[additional_trustees][set2][surname]")).sendKeys("Deshpande");
		Thread.sleep(200);
		List <WebElement> e14 = driver.findElements(By.name("data[additional_trustees][set2][select]"));
		int i14 = e14.size();       
		System.out.println(i14);
		e14.get(0).click();
		Thread.sleep(200);	
		//Street Address
		driver.findElement(By.name("data[additional_trustees][set2][street_address]")).clear();
		driver.findElement(By.name("data[additional_trustees][set2][street_address]")).sendKeys("Camp, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[additional_trustees][set2][suburb]")).clear();
		driver.findElement(By.name("data[additional_trustees][set2][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[additional_trustees][set2][state_territory]")).clear();
		driver.findElement(By.name("data[additional_trustees][set2][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[additional_trustees][set2][postcode]")).clear();
		driver.findElement(By.name("data[additional_trustees][set2][postcode]")).sendKeys("4563225");
		Thread.sleep(200);
		
		//First Name
				driver.findElement(By.name("data[additional_trustees][set3][first_name]")).clear();
				driver.findElement(By.name("data[additional_trustees][set3][first_name]")).sendKeys("Vijay");
				Thread.sleep(200);
				//Second Name
				driver.findElement(By.name("data[additional_trustees][set3][second_name]")).clear();
				driver.findElement(By.name("data[additional_trustees][set3][second_name]")).sendKeys("Dinesh");
				Thread.sleep(200);
				//Surname
				driver.findElement(By.name("data[additional_trustees][set3][surname]")).clear();
				driver.findElement(By.name("data[additional_trustees][set3][surname]")).sendKeys("Desai");
				Thread.sleep(200);
				List <WebElement> e15 = driver.findElements(By.name("data[additional_trustees][set3][select]"));
				int i15 = e15.size();       
				System.out.println(i15);
				e15.get(0).click();
				Thread.sleep(200);	
				//Street Address
				driver.findElement(By.name("data[additional_trustees][set3][street_address]")).clear();
				driver.findElement(By.name("data[additional_trustees][set3][street_address]")).sendKeys("Camp, Pune");
				Thread.sleep(200);
				//Suburb
				driver.findElement(By.name("data[additional_trustees][set3][suburb]")).clear();
				driver.findElement(By.name("data[additional_trustees][set3][suburb]")).sendKeys("Camp");
				Thread.sleep(200);
				//State / Territory
				driver.findElement(By.name("data[additional_trustees][set3][state_territory]")).clear();
				driver.findElement(By.name("data[additional_trustees][set3][state_territory]")).sendKeys("Maharashtra");
				Thread.sleep(200);
				//Postcode
				driver.findElement(By.name("data[additional_trustees][set3][postcode]")).clear();
				driver.findElement(By.name("data[additional_trustees][set3][postcode]")).sendKeys("4563225");
				Thread.sleep(200);
				
		//next
		driver.findElement(By.xpath(".//*[@id='step-5']/div[4]/div/div/div[2]/div/button[2]")).click();
		Thread.sleep(2000);
		
		//Tab-6 - APPLICANT BANKING DETAILS
		//Name of Bank
		driver.findElement(By.name("data[applicant_banking_details][name_of_bank]")).clear();
		driver.findElement(By.name("data[applicant_banking_details][name_of_bank]")).sendKeys("SBI");
		Thread.sleep(200);
		//Branch
		driver.findElement(By.name("data[applicant_banking_details][branch]")).clear();
		driver.findElement(By.name("data[applicant_banking_details][branch]")).sendKeys("Aundh");
		Thread.sleep(200);
		//Account Type
		driver.findElement(By.name("data[applicant_banking_details][account_type]")).clear();
		driver.findElement(By.name("data[applicant_banking_details][account_type]")).sendKeys("Business");
		Thread.sleep(200);
		
		//ACCOUNTANT DETAILS
		//Contact Person
		driver.findElement(By.name("data[applicant_banking_details][contact_person]")).clear();
		driver.findElement(By.name("data[applicant_banking_details][contact_person]")).sendKeys("Ramesh Patil");
		Thread.sleep(200);
		//Phone Number
		driver.findElement(By.name("data[applicant_banking_details][phone_number]")).clear();
		driver.findElement(By.name("data[applicant_banking_details][phone_number]")).sendKeys("8527411475");
		Thread.sleep(200);
		//Name of Accounting Firm
		driver.findElement(By.name("data[applicant_banking_details][name_of_accounting_firm]")).clear();
		driver.findElement(By.name("data[applicant_banking_details][name_of_accounting_firm]")).sendKeys("HDFC");
		Thread.sleep(200);
		//BUSINESS/TRADE REFERENCES (MUST PROVIDE AT LEAST TWO)
		//Contact Person
		driver.findElement(By.name("data[applicant_business_reference][contact_person][set1]")).clear();
		driver.findElement(By.name("data[applicant_business_reference][contact_person][set1]")).sendKeys("Friend");
		Thread.sleep(200);
		driver.findElement(By.name("data[applicant_business_reference][contact_person][set2]")).clear();
		driver.findElement(By.name("data[applicant_business_reference][contact_person][set2]")).sendKeys("Friend");
		Thread.sleep(200);
		//Contact Name
		driver.findElement(By.xpath(".//*[@id='step-6']/div[3]/div/div/div[2]/div/div[2]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-6']/div[3]/div/div/div[2]/div/div[2]/div[1]/input")).sendKeys("Karim");
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-6']/div[3]/div/div/div[2]/div/div[2]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-6']/div[3]/div/div/div[2]/div/div[2]/div[2]/input")).sendKeys("Prashant");
		Thread.sleep(200);
		//Phone Number
		driver.findElement(By.xpath(".//*[@id='step-6']/div[3]/div/div/div[2]/div/div[3]/div[1]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-6']/div[3]/div/div/div[2]/div/div[3]/div[1]/input ")).sendKeys("8888555511");
		Thread.sleep(200);
		driver.findElement(By.xpath(".//*[@id='step-6']/div[3]/div/div/div[2]/div/div[3]/div[2]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='step-6']/div[3]/div/div/div[2]/div/div[3]/div[2]/input")).sendKeys("8521234565");
		Thread.sleep(200);
		
		//GOODS TO BE FINANCED -Year
		driver.findElement(By.name("data[good_finance][Year]")).clear();
		driver.findElement(By.name("data[good_finance][Year]")).sendKeys("2014");
		Thread.sleep(200);
		//Make
		driver.findElement(By.name("data[good_finance][make]")).clear();
		driver.findElement(By.name("data[good_finance][make]")).sendKeys("TATA");
		Thread.sleep(200);
		//Model
		driver.findElement(By.name("data[good_finance][model]")).clear();
		driver.findElement(By.name("data[good_finance][model]")).sendKeys("Swift");
		Thread.sleep(200);
		//New / Used / Demo
		driver.findElement(By.name("data[good_finance][new_used_demo]")).clear();
		driver.findElement(By.name("data[good_finance][new_used_demo]")).sendKeys("Demo");
		Thread.sleep(200);
		//Goods Category
		driver.findElement(By.name("data[good_finance][goods_category]")).clear();
		driver.findElement(By.name("data[good_finance][goods_category]")).sendKeys("Trade");
		Thread.sleep(200);
		//Kilometers
		driver.findElement(By.name("data[good_finance][kilometers]")).clear();
		driver.findElement(By.name("data[good_finance][kilometers]")).sendKeys("100");
		Thread.sleep(200);
		//Hours (if applicable)
		driver.findElement(By.name("data[good_finance][hours]")).clear();
		driver.findElement(By.name("data[good_finance][hours]")).sendKeys("2");
		Thread.sleep(200);
		//Description of Goods
		driver.findElement(By.name("data[good_finance][description_of_goods]")).clear();
		driver.findElement(By.name("data[good_finance][description_of_goods]")).sendKeys("Data");
		Thread.sleep(200);
		
		//For Goods with Wheels Only
		//Fuel
		List <WebElement> e20 = driver.findElements(By.name("data[goods_with_wheel][fuel]"));
		int i20 = e20.size();                                      
		System.out.println(i20);
		e20.get(0).click();
		Thread.sleep(200);
		driver.findElement(By.name("data[goods_with_wheel][fuel][other]")).clear();
		driver.findElement(By.name("data[goods_with_wheel][fuel][other]")).sendKeys("Data");
		Thread.sleep(200);
		//Type
		List <WebElement> e21 = driver.findElements(By.name("data[goods_with_wheel][Type]"));
		int i21 = e21.size();       
		System.out.println(i21);
		e21.get(0).click();
		Thread.sleep(200);
		driver.findElement(By.name("data[goods_with_wheel][type][other]")).clear();
		driver.findElement(By.name("data[goods_with_wheel][type][other]")).sendKeys("Data");
		Thread.sleep(200);
		//Engine
		List <WebElement> e22 = driver.findElements(By.name("data[goods_with_wheel][engine]"));
		int i22 = e22.size();       
		System.out.println(i22);
		e22.get(0).click();
		Thread.sleep(200);
		driver.findElement(By.name("data[goods_with_wheel][engine][other]")).clear();
		driver.findElement(By.name("data[goods_with_wheel][engine][other]")).sendKeys("Data");
		Thread.sleep(200);
		//Transmission
		List <WebElement> e23 = driver.findElements(By.name("data[goods_with_wheel][transmission]"));
		int i23 = e23.size();       
		System.out.println(i23);
		e23.get(0).click();
		Thread.sleep(200);
		//State / Territory where goods will be predominantly used
		driver.findElement(By.name("data[good_finance][state_territory]")).clear();
		driver.findElement(By.name("data[good_finance][state_territory]")).sendKeys("Data");
		Thread.sleep(200);
		
		//TRADE-IN DETAILS (IF APPLICABLE) - Year
		driver.findElement(By.name("data[trade_in_details][Year]")).clear();
		driver.findElement(By.name("data[trade_in_details][Year]")).sendKeys("2014");
		Thread.sleep(200);
		//Make
		driver.findElement(By.name("data[trade_in_details][make]")).clear();
		driver.findElement(By.name("data[trade_in_details][make]")).sendKeys("Honda");
		Thread.sleep(200);
		//Model
		driver.findElement(By.name("data[trade_in_details][model]")).clear();
		driver.findElement(By.name("data[trade_in_details][model]")).sendKeys("Vista");
		Thread.sleep(200);
		//New / Used / Demo
		driver.findElement(By.name("data[trade_in_details][new_used_demo]")).clear();
		driver.findElement(By.name("data[trade_in_details][new_used_demo]")).sendKeys("New");
		Thread.sleep(200);
		//Registration Number
		driver.findElement(By.name("data[trade_in_details][registration_number]")).clear();
		driver.findElement(By.name("data[trade_in_details][registration_number]")).sendKeys("465123");
		Thread.sleep(200);
		//Trade Amount:
		driver.findElement(By.name("data[trade_in_details][trade_amount:]")).clear();
		driver.findElement(By.name("data[trade_in_details][trade_amount:]")).sendKeys("50000");
		Thread.sleep(200);
		
		//SUPPLIER DETAILS
		//Supplier Name
		driver.findElement(By.name("data[supplier_details][supplier_name]")).clear();
		driver.findElement(By.name("data[supplier_details][supplier_name]")).sendKeys("Tajas");
		Thread.sleep(200);
		//Address 	
		driver.findElement(By.name("data[supplier_details][address]")).clear();
		driver.findElement(By.name("data[supplier_details][address]")).sendKeys("Camp, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[supplier_details][suburb]")).clear();
		driver.findElement(By.name("data[supplier_details][suburb]")).sendKeys("Camp");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[supplier_details][state_territory]")).clear();
		driver.findElement(By.name("data[supplier_details][state_territory]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[supplier_details][postcode]")).clear();
		driver.findElement(By.name("data[supplier_details][postcode]")).sendKeys("65322");
		Thread.sleep(200);
		//Supplier Type
		List <WebElement> e24 = driver.findElements(By.name("data[supplier_type]"));
		int i24 = e24.size();       
		System.out.println(i24);
		e24.get(0).click();
		Thread.sleep(200);	
		
		//next
		driver.findElement(By.xpath(".//*[@id='step-6']/div[7]/div/div/div[3]/div/button[2]")).click();
		Thread.sleep(2000);	
		
		//Tab - 7
		//INANCE DETAILS - Facility
		List <WebElement> e25 = driver.findElements(By.name("data[finanace_details][facility]"));
		int i25 = e25.size();                                      
		System.out.println(i25);
		e25.get(0).click();
		Thread.sleep(200);
		//Cash Price (incl GST)
		driver.findElement(By.name("data[finanace_details][cash_price]")).clear();
		driver.findElement(By.name("data[finanace_details][cash_price]")).sendKeys("100");
		Thread.sleep(200);
		//Commission/Brokerage (Incl. GST)
		driver.findElement(By.name("data[finanace_details][commission_brokerage]")).clear();
		driver.findElement(By.name("data[finanace_details][commission_brokerage]")).sendKeys("100");
		Thread.sleep(200);
		//GST Amount (as per Invoice)
		driver.findElement(By.name("data[finanace_details][gst_amout]")).clear();
		driver.findElement(By.name("data[finanace_details][gst_amout]")).sendKeys("100");
		Thread.sleep(200);
		//Base Rate
		driver.findElement(By.name("data[finanace_details][base_rate]")).clear();
		driver.findElement(By.name("data[finanace_details][base_rate]")).sendKeys("100");
		Thread.sleep(200);
		//Less Deposit
		driver.findElement(By.name("data[finanace_details][less_deposite]")).clear();
		driver.findElement(By.name("data[finanace_details][less_deposite]")).sendKeys("100");
		Thread.sleep(200);
		//Writing/Customer Rate
		driver.findElement(By.name("data[finanace_details][writing_customer_rate]")).clear();
		driver.findElement(By.name("data[finanace_details][writing_customer_rate]")).sendKeys("100");
		Thread.sleep(200);
		//Less Trade-in Value/s
		driver.findElement(By.name("data[finanace_details][less_trade_in_value]")).clear();
		driver.findElement(By.name("data[finanace_details][less_trade_in_value]")).sendKeys("100");
		Thread.sleep(200);
		//Term (months)
		driver.findElement(By.name("data[finanace_details][term]")).clear();
		driver.findElement(By.name("data[finanace_details][term]")).sendKeys("100");
		Thread.sleep(200);
		//Plus Origination Fee (M/V Only) Incl GST
		driver.findElement(By.name("data[finanace_details][plus_origination]")).clear();
		driver.findElement(By.name("data[finanace_details][plus_origination]")).sendKeys("100");
		Thread.sleep(200);
		//Balloon/Residual ($ or %)
		driver.findElement(By.name("data[finanace_details][balloon]")).clear();
		driver.findElement(By.name("data[finanace_details][balloon]")).sendKeys("10");
		Thread.sleep(200);
		//Finance Company Name
		driver.findElement(By.name("data[finanace_details][finance_company_name]")).clear();
		driver.findElement(By.name("data[finanace_details][finance_company_name]")).sendKeys("Global finance");
		Thread.sleep(200);
		//Payments (advance/arrears)
		driver.findElement(By.name("data[finanace_details][payments]")).clear();
		driver.findElement(By.name("data[finanace_details][payments]")).sendKeys("100");
		Thread.sleep(200);
		//Plus Amount Owing
		driver.findElement(By.name("data[finanace_details][plus_amount_owing]")).clear();
		driver.findElement(By.name("data[finanace_details][plus_amount_owing]")).sendKeys("100");
		Thread.sleep(200);
		//Repayment Pattern
		driver.findElement(By.name("data[finanace_details][repayment_pattern]")).clear();
		driver.findElement(By.name("data[finanace_details][repayment_pattern]")).sendKeys("100");
		Thread.sleep(200);
		//Total Amount Financed (please note fees & charges may apply
		driver.findElement(By.name("data[finanace_details][total_amount_finance]")).clear();
		driver.findElement(By.name("data[finanace_details][total_amount_finance]")).sendKeys("1000");
		Thread.sleep(200);
		//If Irregular – Provide Details
		driver.findElement(By.name("data[finanace_details][If_Irregular]")).clear();
		driver.findElement(By.name("data[finanace_details][If_Irregular]")).sendKeys("Data");
		Thread.sleep(200);
		//Payment Method
		List <WebElement> e26 = driver.findElements(By.name("data[finanace_details][payment_method]"));
		int i26 = e26.size();                                  
		System.out.println(i26);
		e26.get(0).click();
		Thread.sleep(200);	
		//Fees
		List <WebElement> e27 = driver.findElements(By.name("data[finanace_details][Fees]"));
		int i27 = e27.size();       
		System.out.println(i27);
		e27.get(0).click();
		Thread.sleep(200);
		
		//FINANCIAL COMMITMENTS (IF APPLICABLE)
		//Lender/Financier
		driver.findElement(By.name("data[finanace_commitment][lender_financier][1]")).clear();
		driver.findElement(By.name("data[finanace_commitment][lender_financier][1]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][lender_financier][2]")).clear();
		driver.findElement(By.name("data[finanace_commitment][lender_financier][2]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][lender_financier][3]")).clear();
		driver.findElement(By.name("data[finanace_commitment][lender_financier][3]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][lender_financier][4]")).clear();
		driver.findElement(By.name("data[finanace_commitment][lender_financier][4]")).sendKeys("10");
		Thread.sleep(200);
		//Date Commenced
		WebElement toDateBox1= driver.findElement(By.name("data[finanace_commitment][date_commenced][1]"));
		toDateBox1.clear();
		toDateBox1.sendKeys("01/01/2015");
		Thread.sleep(200);
		toDateBox1.sendKeys(Keys.TAB);
		WebElement toDateBox2= driver.findElement(By.name("data[finanace_commitment][date_commenced][2]"));
		toDateBox2.clear();
		toDateBox2.sendKeys("01/01/2015");
		Thread.sleep(200);
		toDateBox2.sendKeys(Keys.TAB);
		WebElement toDateBox3= driver.findElement(By.name("data[finanace_commitment][date_commenced][3]"));
		toDateBox3.clear();
		toDateBox3.sendKeys("01/01/2015");
		Thread.sleep(200);
		toDateBox3.sendKeys(Keys.TAB);
		WebElement toDateBox4= driver.findElement(By.name("data[finanace_commitment][date_commenced][4]"));
		toDateBox4.clear();
		toDateBox4.sendKeys("01/01/2015");
		Thread.sleep(200);
		toDateBox4.sendKeys(Keys.TAB);
		//Amount Financed
		driver.findElement(By.name("data[finanace_commitment][amount_finance][1]")).clear();
		driver.findElement(By.name("data[finanace_commitment][amount_finance][1]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][amount_finance][2]")).clear();
		driver.findElement(By.name("data[finanace_commitment][amount_finance][2]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][amount_finance][3]")).clear();
		driver.findElement(By.name("data[finanace_commitment][amount_finance][3]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][amount_finance][4]")).clear();
		driver.findElement(By.name("data[finanace_commitment][amount_finance][4]")).sendKeys("10");
		Thread.sleep(200);
		//Goods
		driver.findElement(By.name("data[finanace_commitment][goods][1]")).clear();
		driver.findElement(By.name("data[finanace_commitment][goods][1]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][goods][2]")).clear();
		driver.findElement(By.name("data[finanace_commitment][goods][2]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][goods][3]")).clear();
		driver.findElement(By.name("data[finanace_commitment][goods][3]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][goods][4]")).clear();
		driver.findElement(By.name("data[finanace_commitment][goods][4]")).sendKeys("10");
		Thread.sleep(200);
		//Monthly Repayment
		driver.findElement(By.name("data[finanace_commitment][monthly_repayment][1]")).clear();
		driver.findElement(By.name("data[finanace_commitment][monthly_repayment][1]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][monthly_repayment][2]")).clear();
		driver.findElement(By.name("data[finanace_commitment][monthly_repayment][2]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][monthly_repayment][3]")).clear();
		driver.findElement(By.name("data[finanace_commitment][monthly_repayment][3]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][monthly_repayment][4]")).clear();
		driver.findElement(By.name("data[finanace_commitment][monthly_repayment][4]")).sendKeys("10");
		Thread.sleep(200);
		//Term
		driver.findElement(By.name("data[finanace_commitment][term][1]")).clear();
		driver.findElement(By.name("data[finanace_commitment][term][1]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][term][2]")).clear();
		driver.findElement(By.name("data[finanace_commitment][term][2]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][term][3]")).clear();
		driver.findElement(By.name("data[finanace_commitment][term][3]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][term][4]")).clear();
		driver.findElement(By.name("data[finanace_commitment][term][4]")).sendKeys("10");
		Thread.sleep(200);
		//Balloon/Residual
		driver.findElement(By.name("data[finanace_commitment][balloon_residual][1]")).clear();
		driver.findElement(By.name("data[finanace_commitment][balloon_residual][1]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][balloon_residual][2]")).clear();
		driver.findElement(By.name("data[finanace_commitment][balloon_residual][2]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][balloon_residual][3]")).clear();
		driver.findElement(By.name("data[finanace_commitment][balloon_residual][3]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][balloon_residual][4]")).clear();
		driver.findElement(By.name("data[finanace_commitment][balloon_residual][4]")).sendKeys("10");
		Thread.sleep(200);
		//Current Balance
		driver.findElement(By.name("data[finanace_commitment][current_balance][1]")).clear();
		driver.findElement(By.name("data[finanace_commitment][current_balance][1]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][current_balance][2]")).clear();
		driver.findElement(By.name("data[finanace_commitment][current_balance][2]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][current_balance][3]")).clear();
		driver.findElement(By.name("data[finanace_commitment][current_balance][3]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][current_balance][4]")).clear();
		driver.findElement(By.name("data[finanace_commitment][current_balance][4]")).sendKeys("10");
		Thread.sleep(200);
		//Contract No. (Esanda only)
		driver.findElement(By.name("data[finanace_commitment][contract_no][1]")).clear();
		driver.findElement(By.name("data[finanace_commitment][contract_no][1]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][contract_no][2]")).clear();
		driver.findElement(By.name("data[finanace_commitment][contract_no][2]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][contract_no][3]")).clear();
		driver.findElement(By.name("data[finanace_commitment][contract_no][3]")).sendKeys("10");
		Thread.sleep(200);
		driver.findElement(By.name("data[finanace_commitment][contract_no][4]")).clear();
		driver.findElement(By.name("data[finanace_commitment][contract_no][4]")).sendKeys("10");
		Thread.sleep(200);
		//To be paid out
		//1
		List <WebElement> e30 = driver.findElements(By.name("data[finanace_commitment][to_be_paid][1]"));
		int i30 = e30.size();       
		System.out.println(i30);
		e30.get(0).click();
		Thread.sleep(200);	
		//2
				List <WebElement> e31 = driver.findElements(By.name("data[finanace_commitment][to_be_paid][2]"));
				int i31 = e31.size();       
				System.out.println(i31);
				e31.get(0).click();
				Thread.sleep(200);
		//3
				List <WebElement> e32 = driver.findElements(By.name("data[finanace_commitment][to_be_paid][3]"));
				int i32 = e32.size();       
				System.out.println(i32);
				e32.get(0).click();
				Thread.sleep(200);
				
		//4
				List <WebElement> e34 = driver.findElements(By.name("data[finanace_commitment][to_be_paid][4]"));
				int i34 = e34.size();       
				System.out.println(i34);
				e34.get(0).click();
				Thread.sleep(200);	
		
		//COMPANY GUARANTOR DETAILS (IF APPLICABLE)
		driver.findElement(By.name("data[company_guarantor_detail][company_name]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][company_name]")).sendKeys("CNN");
		Thread.sleep(200);
		//Company Type
		driver.findElement(By.name("data[company_guarantor_detail][company_type]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][company_type]")).sendKeys("Cost");
		Thread.sleep(200);
		//ABN
		driver.findElement(By.name("data[company_guarantor_detail][abn]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][abn]")).sendKeys("45123");
		Thread.sleep(200);
		//ACN
		driver.findElement(By.name("data[company_guarantor_detail][acn]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][acn]")).sendKeys("8795");
		Thread.sleep(200);
		//Trading Name
		driver.findElement(By.name("data[company_guarantor_detail][trading_name]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][trading_name]")).sendKeys("CostTrade");
		Thread.sleep(200);
		//Trust Name
		driver.findElement(By.name("data[company_guarantor_detail][trust_name]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][trust_name]")).sendKeys("Cost");
		Thread.sleep(200);
		//Nature of Business
		driver.findElement(By.name("data[company_guarantor_detail][nature_of_business]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][nature_of_business]")).sendKeys("Costing");
		Thread.sleep(200);
		//Type of Trust (ie. Family Trust, Discretionary Trust etc.)
		driver.findElement(By.name("data[company_guarantor_detail][type_of_trust]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][type_of_trust]")).sendKeys("Family");
		Thread.sleep(200);
		//Address
		driver.findElement(By.name("data[company_guarantor_detail][address]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][address]")).sendKeys("JK Files, Pune");
		Thread.sleep(200);
		//Suburb
		driver.findElement(By.name("data[company_guarantor_detail][suburb_1]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][suburb_1]")).sendKeys("JK Files");
		Thread.sleep(200);
		//State / Territory
		driver.findElement(By.name("data[company_guarantor_detail][state_territory_1]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][state_territory_1]")).sendKeys("Maharashtra");
		Thread.sleep(200);
		//Postcode
		driver.findElement(By.name("data[company_guarantor_detail][postcode_1]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][postcode_1]")).sendKeys("5422121");
		Thread.sleep(200);
		//Phone Number
		driver.findElement(By.name("data[company_guarantor_detail][phone_number]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][phone_number]")).sendKeys("7456321456");
		Thread.sleep(200);
		//Fax Number
		driver.findElement(By.name("data[company_guarantor_detail][fax_number]")).clear();
		driver.findElement(By.name("data[company_guarantor_detail][fax_number]")).sendKeys("1234567895");
		Thread.sleep(200);
		//next
		driver.findElement(By.xpath(".//*[@id='step-7']/div[3]/div/div/div[3]/div/button[2]")).click();
		Thread.sleep(3000);
		
		//Tab - 8 
		driver.findElement(By.xpath(".//*[@id='step-8']/div/div/div/div[3]/div/button[2]")).click();
		Thread.sleep(2000);
		
		//Tab - 9
		driver.findElement(By.name("data[ability_to_service][fy][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][fy][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][fy][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][fy][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][fy][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][fy][3]")).sendKeys("100");
		Thread.sleep(200);
		//Financial Period
		driver.findElement(By.name("data[ability_to_service][finance_period][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][finance_period][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][finance_period][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][finance_period][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][finance_period][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][finance_period][3]")).sendKeys("100");
		Thread.sleep(200);
		//Sales (turnover)
		driver.findElement(By.name("data[ability_to_service][sales][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][sales][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][sales][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][sales][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][sales][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][sales][3]")).sendKeys("100");
		Thread.sleep(200);
		//Gross Profit
		driver.findElement(By.name("data[ability_to_service][gross_profit][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][gross_profit][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][gross_profit][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][gross_profit][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][gross_profit][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][gross_profit][3]")).sendKeys("100");
		Thread.sleep(200);
		//Net Profit
		driver.findElement(By.name("data[ability_to_service][net_profit][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][net_profit][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][net_profit][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][net_profit][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][net_profit][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][net_profit][3]")).sendKeys("100");
		Thread.sleep(200);
		//Depreciation
		driver.findElement(By.name("data[ability_to_service][depreciation][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][depreciation][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][depreciation][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][depreciation][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][depreciation][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][depreciation][3]")).sendKeys("100");
		Thread.sleep(200);
		//Lease Rentals
		driver.findElement(By.name("data[ability_to_service][lease_rentals][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][lease_rentals][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][lease_rentals][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][lease_rentals][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][lease_rentals][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][lease_rentals][3]")).sendKeys("100");
		Thread.sleep(200);
		//Lease Interest
		driver.findElement(By.name("data[ability_to_service][lease_interest][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][lease_interest][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][lease_interest][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][lease_interest][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][lease_interest][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][lease_interest][3]")).sendKeys("100");
		Thread.sleep(200);
		//Lease Amortisation
		driver.findElement(By.name("data[ability_to_service][lease_amortisation][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][lease_amortisation][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][lease_amortisation][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][lease_amortisation][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][lease_amortisation][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][lease_amortisation][3]")).sendKeys("100");
		Thread.sleep(200);
		//Hire Purchase
		driver.findElement(By.name("data[ability_to_service][hire_purchase][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][hire_purchase][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][hire_purchase][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][hire_purchase][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][hire_purchase][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][hire_purchase][3]")).sendKeys("100");
		Thread.sleep(200);
		//Interest
		driver.findElement(By.name("data[ability_to_service][interest][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][interest][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][interest][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][interest][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][interest][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][interest][3]")).sendKeys("100");
		Thread.sleep(200);
		//Loss on Sale of fixed assets
		driver.findElement(By.name("data[ability_to_service][loss_on_sale_of_fixed_aseets][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][loss_on_sale_of_fixed_aseets][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][loss_on_sale_of_fixed_aseets][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][loss_on_sale_of_fixed_aseets][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][loss_on_sale_of_fixed_aseets][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][loss_on_sale_of_fixed_aseets][3]")).sendKeys("100");
		Thread.sleep(200);
		//(Profit on asset)
		driver.findElement(By.name("data[ability_to_service][profit_on_asset][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][profit_on_asset][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][profit_on_asset][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][profit_on_asset][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][profit_on_asset][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][profit_on_asset][3]")).sendKeys("100");
		Thread.sleep(200);
		//Cash Flow
		driver.findElement(By.name("data[ability_to_service][cash_flow][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][cash_flow][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][cash_flow][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][cash_flow][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][cash_flow][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][cash_flow][3]")).sendKeys("100");
		Thread.sleep(200);
		//Overdraft
		driver.findElement(By.name("data[ability_to_service][Overdraft][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][Overdraft][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][Overdraft][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][Overdraft][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][Overdraft][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][Overdraft][3]")).sendKeys("100");
		Thread.sleep(200);
		//Bank
		driver.findElement(By.name("data[ability_to_service][bank][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][bank][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][bank][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][bank][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][bank][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][bank][3]")).sendKeys("100");
		Thread.sleep(200);
		//Current Equipment
		driver.findElement(By.name("data[ability_to_service][current_equipment][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][current_equipment][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][current_equipment][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][current_equipment][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][current_equipment][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][current_equipment][3]")).sendKeys("100");
		Thread.sleep(200);
		//This request
		driver.findElement(By.name("data[ability_to_service][the_request][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][the_request][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][the_request][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][the_request][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][the_request][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][the_request][3]")).sendKeys("100");
		Thread.sleep(200);
		//Surplus
		driver.findElement(By.name("data[ability_to_service][surplus][1]")).clear();
		driver.findElement(By.name("data[ability_to_service][surplus][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][surplus][2]")).clear();
		driver.findElement(By.name("data[ability_to_service][surplus][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service][surplus][3]")).clear();
		driver.findElement(By.name("data[ability_to_service][surplus][3]")).sendKeys("100");
		Thread.sleep(200);
		//APPENDIX – ABILITY TO SERVICE - PERSONAL - 
		driver.findElement(By.name("data[ability_to_service_personal][fy][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][fy][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][fy][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][fy][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][fy][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][fy][3]")).sendKeys("100");
		Thread.sleep(200);
		//Taxable Income
		driver.findElement(By.name("data[ability_to_service_personal][taxable_income][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][taxable_income][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][taxable_income][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][taxable_income][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][taxable_income][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][taxable_income][3]")).sendKeys("100");
		Thread.sleep(200);
		//Monthly After Tax
		driver.findElement(By.name("data[ability_to_service_personal][monthly_after_tax][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][monthly_after_tax][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][monthly_after_tax][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][monthly_after_tax][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][monthly_after_tax][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][monthly_after_tax][3]")).sendKeys("100");
		Thread.sleep(200);
		//Net Profit Other Income
		driver.findElement(By.name("data[ability_to_service_personal][net_profit_other_income][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][net_profit_other_income][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][net_profit_other_income][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][net_profit_other_income][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][net_profit_other_income][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][net_profit_other_income][3]")).sendKeys("100");
		Thread.sleep(200);
		//Total Income
		driver.findElement(By.name("data[ability_to_service_personal][total_income][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][total_income][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][total_income][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][total_income][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][total_income][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][total_income][3]")).sendKeys("100");
		Thread.sleep(200);
		//Mortgage / Rent
		driver.findElement(By.name("data[ability_to_service_personal][mortgage][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][mortgage][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][mortgage][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][mortgage][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][mortgage][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][mortgage][3]")).sendKeys("100");
		Thread.sleep(200);
		//Credit Cards
		driver.findElement(By.name("data[ability_to_service_personal][credit_cards][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][credit_cards][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][credit_cards][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][credit_cards][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][credit_cards][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][credit_cards][3]")).sendKeys("100");
		Thread.sleep(200);
		//Lease / Other Repayments
		driver.findElement(By.name("data[ability_to_service_personal][lease_other_repayments][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][lease_other_repayments][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][lease_other_repayments][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][lease_other_repayments][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][lease_other_repayments][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][lease_other_repayments][3]")).sendKeys("100");
		Thread.sleep(200);
		//Vehicles
		driver.findElement(By.name("data[ability_to_service_personal][vehicles][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][vehicles][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][vehicles][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][vehicles][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][vehicles][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][vehicles][3]")).sendKeys("100");
		Thread.sleep(200);
		//Other Expenses
		driver.findElement(By.name("data[ability_to_service_personal][other_expenses][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][other_expenses][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][other_expenses][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][other_expenses][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][other_expenses][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][other_expenses][3]")).sendKeys("100");
		Thread.sleep(200);
		//This Request
		driver.findElement(By.name("data[ability_to_service_personal][this_request][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][this_request][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][this_request][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][this_request][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][this_request][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][this_request][3]")).sendKeys("100");
		Thread.sleep(200);
		//Surplus
		driver.findElement(By.name("data[ability_to_service_personal][surplus][1]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][surplus][1]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][surplus][2]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][surplus][2]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[ability_to_service_personal][surplus][3]")).clear();
		driver.findElement(By.name("data[ability_to_service_personal][surplus][3]")).sendKeys("100");
		Thread.sleep(200);
		
		//save
		driver.findElement(By.xpath(".//*[@id='anzUpdatebtn']")).click();
		Thread.sleep(15000);
		
		//Dashboard
		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
		Thread.sleep(2000);
				
		//Applications 
		driver.findElement(By.xpath(".//*[@id='side-menu']/li[6]/a")).click();
		Thread.sleep(2000);
		//View All  
		driver.findElement(By.xpath(".//*[@id='side-menu']/li[6]/ul/li[3]/a")).click();
		Thread.sleep(2000);
				
		String actual1 = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[3]/div/div/div/div[2]/div/table/tbody/tr[1]/td[3]")).getText();
		System.out.println(actual1);                                  
		String expectd1 = "Business Trade";
		Assert.assertEquals(actual1, expectd1);
		Thread.sleep(2000);
		
		System.out.println("Business form eidt successfull");
		
		System.out.println("====================================================================");
 	}
 	
 	public void editPersonalLoanApplicationWithDSriveOn() throws InterruptedException, AWTException{
 		
 		//Dashboard
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
 		Thread.sleep(2000);
 				
 		//Applications 
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/a")).click();
 		Thread.sleep(2000);
 		//View All  
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/ul/li[1]/a")).click();
 		Thread.sleep(7000);
 		
 		//action button
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[14]/div/button")).click();
 		Thread.sleep(2000);          
 		
 		//edit
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[14]/div/div/div/a[2]")).click();
 		Thread.sleep(2000);                                        
 		
 		/*//drive on option
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[4]/div/div/div/div/div/a[2]")).click();
 		Thread.sleep(2000);          */
 		
 		//tab-1 Borrower Type
 		List <WebElement> radios = driver.findElements(By.name("data[individual_application][borrow_type1][]"));
		int o = radios.size();                                    
		System.out.println(o);
		radios.get(1).click();
		Thread.sleep(200);
		
		//tittle
		WebElement gender1c = driver.findElement(By.name("data[personal_details][title][]"));
		Select gc = new Select(gender1c);
		gc.selectByVisibleText("Mrs");
		Thread.sleep(500);
		
		//First name
		driver.findElement(By.name("data[personal_details][fname][]")).clear();
		driver.findElement(By.name("data[personal_details][fname][]")).sendKeys("TestDriveOn");
		Thread.sleep(200);
		
		//Middle name
		driver.findElement(By.name("data[personal_details][mname][]")).clear();
		driver.findElement(By.name("data[personal_details][mname][]")).sendKeys("Demo");
		Thread.sleep(200);
		
		//Surname
		driver.findElement(By.name("data[personal_details][surname][]")).clear();
		driver.findElement(By.name("data[personal_details][surname][]")).sendKeys("Personal");
		Thread.sleep(200);
		
		//Gender
		List <WebElement> radios1 = driver.findElements(By.name("data[personal_details][gender1][]"));
		int o1 = radios1.size();       
		System.out.println(o1);
		radios1.get(1).click();
		Thread.sleep(200);
		
		//Date of Birth
		driver.findElement(By.name("data[personal_details][dob][]")).clear();
		driver.findElement(By.name("data[personal_details][dob][]")).sendKeys("01/01/1990");
		Thread.sleep(200);
		
		//Marital Status
		WebElement gender1 = driver.findElement(By.name("data[personal_details][marital_status][]"));
		Select g = new Select(gender1);
		g.selectByVisibleText("Single");
		Thread.sleep(500);
		
		//licance type
		WebElement gender2 = driver.findElement(By.name("data[personal_details][licence_type][]"));
		Select g2 = new Select(gender2);
		g2.selectByVisibleText("Learner");
		Thread.sleep(500);
		
		//Licence State
		WebElement gender3 = driver.findElement(By.name("data[personal_details][licence_state][]"));
		Select g3 = new Select(gender3);
		g3.selectByVisibleText("VIC");
		Thread.sleep(500);
		
		//Drivers License No
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).clear();
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).sendKeys("8541212");
		Thread.sleep(200);
		
		//Expiry
		WebElement dob = driver.findElement(By.name("data[personal_details][expiry][]"));
		dob.clear();                                 
		dob.sendKeys("05/05/2022");
		Thread.sleep(200);
		dob.sendKeys(Keys.TAB);
		
		//Email Address
				driver.findElement(By.name("data[personal_details][email][]")).clear();
				driver.findElement(By.name("data[personal_details][email][]")).sendKeys("testq@test.com");
				Thread.sleep(200);
				
				//Mobile Phone No
				driver.findElement(By.name("data[personal_details][mobile_phone][]")).clear();
				driver.findElement(By.name("data[personal_details][mobile_phone][]")).sendKeys("7894561230");
				Thread.sleep(200);
				
				//Home Phone No
				driver.findElement(By.name("data[personal_details][home_phone][]")).clear();
				driver.findElement(By.name("data[personal_details][home_phone][]")).sendKeys("7894561231");
				Thread.sleep(200);		
		
		//No. of Dependants 
		Select noDept = new Select(driver.findElement(By.name("data[personal_details][no_of_department][]")));
		noDept.selectByVisibleText("3");
		Thread.sleep(200);		
		
		//Residency Status
		List <WebElement> radios2 = driver.findElements(By.name("data[personal_details][residential_status1][]"));
		int o2 = radios2.size();       
		System.out.println(o2);
		radios2.get(2).click();
		Thread.sleep(200);
		
		//Creadit history
		Select noDept1 = new Select(driver.findElement(By.name("data[personal_details][credit_history][]")));
		noDept1.selectByVisibleText("No Credit History");
		Thread.sleep(200);
		
		/*//References One - First name
		driver.findElement(By.name("data[personal_reference][fname_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][fname_1][]")).sendKeys("Karim");
		Thread.sleep(200);
		
		//Surname
		driver.findElement(By.name("data[personal_reference][surname_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][surname_1][]")).sendKeys("Shaikh");
		Thread.sleep(200); 
		
		//CurrentAddress
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).sendKeys("Pune");
		Thread.sleep(200); 
		
		//Phone No
		driver.findElement(By.name("data[personal_reference][phone_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][phone_1][]")).sendKeys("9856451245");
		Thread.sleep(200); 
		
		//Relationship
		driver.findElement(By.name("data[personal_reference][relationship_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][relationship_1][]")).sendKeys("Friend");
		Thread.sleep(200);
		
		//References Two - First name
		driver.findElement(By.name("data[personal_reference][fname_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][fname_2][]")).sendKeys("Amir");
		Thread.sleep(200);
		
		//Surname
		driver.findElement(By.name("data[personal_reference][surname_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][surname_2][]")).sendKeys("Khan");
		Thread.sleep(200);
		
		//CurrentAddress
		driver.findElement(By.name("data[personal_reference][current_address_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][current_address_2][]")).sendKeys("Pune");
		Thread.sleep(200);
		
		//Phone No
		driver.findElement(By.name("data[personal_reference][phone_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][phone_2][]")).sendKeys("9856451245");
		Thread.sleep(200);
	
		//Relationship
		driver.findElement(By.name("data[personal_reference][relationship_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][relationship_2][]")).sendKeys("Friend");
		Thread.sleep(200);*/
 	
		//next
		driver.findElement(By.xpath(".//*[@id='next1']")).click();
		Thread.sleep(2000);
		
		//Tab-2
		//Current Address
		driver.findElement(By.name("data[address_details][current_address][]")).clear();
		driver.findElement(By.name("data[address_details][current_address][]")).sendKeys("TRP Stop");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[address_details][suburb][]")).clear();
		driver.findElement(By.name("data[address_details][suburb][]")).sendKeys("Camp");
		Thread.sleep(200);
		
		//State
		WebElement state = driver.findElement(By.name("data[address_details][state][]"));
    	Select states = new Select (state);
    	states.selectByVisibleText("NSW");
    	Thread.sleep(200);
    	
    	//Postal Code
    	driver.findElement(By.name("data[address_details][postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][postal_code][]")).sendKeys("54612");
		Thread.sleep(200);
		
		//Time At Address
		WebElement years1 = driver.findElement(By.name("data[address_details][time_at_address_yrs][]"));
    	Select year1 = new Select (years1);
    	year1.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months1 = driver.findElement(By.name("data[address_details][time_at_address_months][]"));
    	Select month1 = new Select (months1);
    	month1.selectByVisibleText("2 Months");
    	Thread.sleep(1000);
    	
    	//Current Resident Type*
    	List <WebElement> radios3 = driver.findElements(By.name("data[address_details][residential_type1][]"));
		int o3 = radios3.size();       
		System.out.println(o3);
		radios3.get(1).click();
		
		//Prev Time At Address
		WebElement years2 = driver.findElement(By.name("data[address_details][prev_time_at_address_yrs][]"));
    	Select year2 = new Select (years2);
    	year2.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months2 = driver.findElement(By.name("data[address_details][prev_time_at_address_months][]"));
    	Select month2 = new Select (months2);
    	month2.selectByVisibleText("2 Months");
    	Thread.sleep(1000);
    	
    	//Previous Address*
    	driver.findElement(By.name("data[address_details][previous_address][]")).clear();
		driver.findElement(By.name("data[address_details][previous_address][]")).sendKeys("Chandan Nagar, Pune");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[address_details][previous_suburb][]")).clear();
		driver.findElement(By.name("data[address_details][previous_suburb][]")).sendKeys("Chandan Nagar");
		Thread.sleep(200);
		
		//State
		WebElement state1 = driver.findElement(By.name("data[address_details][prev_state][]"));
    	Select states1 = new Select (state1);
    	states1.selectByVisibleText("NSW");
    	Thread.sleep(200);
    	
    	//Postal Code
    	driver.findElement(By.name("data[address_details][previous_postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][previous_postal_code][]")).sendKeys("78452");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next2']")).click();
		Thread.sleep(2000);
		
		//Current Employer*
		driver.findElement(By.name("data[employment_details][current_employer][]")).clear();
		driver.findElement(By.name("data[employment_details][current_employer][]")).sendKeys("TATA");
		Thread.sleep(200);
		
		//Employer Address
		driver.findElement(By.name("data[employment_details][employer_address][]")).clear();
		driver.findElement(By.name("data[employment_details][employer_address][]")).sendKeys("Camp. Pune");
		Thread.sleep(200);
		
		//Phone No 
		driver.findElement(By.name("data[employment_details][employer_phone_no][]")).clear();
		driver.findElement(By.name("data[employment_details][employer_phone_no][]")).sendKeys("7894523145");
		Thread.sleep(200);
		
		//Occupation
		driver.findElement(By.name("data[employment_details][occupation][]")).clear();
		driver.findElement(By.name("data[employment_details][occupation][]")).sendKeys("Job");
		Thread.sleep(200);
		
		//Employment Status
		WebElement empStatus = driver.findElement(By.name("data[employment_details][employment_status][]"));
		Select statusemp = new Select(empStatus);
		statusemp.selectByVisibleText("Full Time");
		Thread.sleep(1000);
		
		//ABN number
		driver.findElement(By.id("abn_number")).clear();
		Thread.sleep(500);
		driver.findElement(By.id("abn_number")).sendKeys("54321");
		Thread.sleep(2000);
		
		//ime Employed (2 years history required)*
		WebElement years3 = driver.findElement(By.name("data[employment_details][time_employed_yrs][]"));
    	Select year3 = new Select (years3);             
    	year3.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months3 = driver.findElement(By.name("data[employment_details][time_employed_months][]"));
    	Select month3 = new Select (months3);
    	month3.selectByVisibleText("2 Months");
    	Thread.sleep(1000);
    	
    	//Contact Name*
    	driver.findElement(By.name("data[employment_details][prev_contact_name][]")).clear();
		driver.findElement(By.name("data[employment_details][prev_contact_name][]")).sendKeys("Ashok kumar");
		Thread.sleep(200);
		
		/*//Net Income
		driver.findElement(By.name("data[employment_details][net_income][]")).clear();
		driver.findElement(By.name("data[employment_details][net_income][]")).sendKeys("1000");
		Thread.sleep(200);
		
		//Frequency
		List <WebElement> radios4 = driver.findElements(By.name("data[employment_details][period1][]"));
		int o4 = radios4.size();       
		System.out.println(o4);
		radios4.get(1).click();*/
		
		//Previous employer
		driver.findElement(By.name("data[employment_details][previous_employer][]")).clear();
		driver.findElement(By.name("data[employment_details][previous_employer][]")).sendKeys("AMD");
		Thread.sleep(200);          
		
		//Phone No
		driver.findElement(By.name("data[employment_details][prev_phone_no][]")).clear();
		driver.findElement(By.name("data[employment_details][prev_phone_no][]")).sendKeys("7412563254");
		Thread.sleep(200);
		
		//Previous Occupation
		driver.findElement(By.name("data[employment_details][prev_occupation][]")).clear();
		driver.findElement(By.name("data[employment_details][prev_occupation][]")).sendKeys("Job");
		Thread.sleep(200);
		
		//Time Employed
		WebElement years4 = driver.findElement(By.name("data[employment_details][prev_time_employed_yrs][]"));
    	Select year4 = new Select (years4);
    	year4.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months4 = driver.findElement(By.name("data[employment_details][prev_time_employed_months][]"));
    	Select month4 = new Select (months4);
    	month4.selectByVisibleText("2 Months");
    	Thread.sleep(1000);
    	
    	//Income Details
    	//Net Income Verification*
    	driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[3]/div/div/div/div/div[3]/div[1]/div/div/label[2]/input")).click();
    	Thread.sleep(1000);
    	
    	//Monthly Government Benifits Income
    	driver.findElement(By.name("data[employment_details][government_benefits][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[employment_details][government_benefits][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Monthly Investment Income
    	driver.findElement(By.name("data[employment_details][investment_income][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[employment_details][investment_income][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Monthly Net Income
    	driver.findElement(By.name("data[employment_details][monthly_net_income][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[employment_details][monthly_net_income][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Monthly Investment Property Income
    	driver.findElement(By.name("data[employment_details][investment_property][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[employment_details][investment_property][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Monthly Other Income*
    	driver.findElement(By.name("data[employment_details][other_income][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[employment_details][other_income][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//next
    	driver.findElement(By.xpath(".//*[@id='next3']")).click();
    	Thread.sleep(2000);
    	
    	//Tab-4 
		//Home Property
    	//value
    	driver.findElement(By.name("data[assets_libility_assets][asset_home][value][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_assets][asset_home][value][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Owned Outright NO
    	WebElement opt = driver.findElement(By.name("data[assets_libility_assets][asset_home][own][]"));
    	Select opts = new Select (opt);
    	opts.selectByVisibleText("No");
    	Thread.sleep(1000);
    	
    	//Investment Property
    	driver.findElement(By.name("data[assets_libility_assets][asset_investment][value][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_assets][asset_investment][value][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Owned Outright NO
    	WebElement opt1 = driver.findElement(By.name("data[assets_libility_assets][asset_investment][own][]"));
    	Select opts1 = new Select (opt1);
    	opts1.selectByVisibleText("No");
    	Thread.sleep(1000);
    	
    	//Cash in Bank
    	driver.findElement(By.name("data[assets_libility_assets][asset_cash][value][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_assets][asset_cash][value][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Shares/Trusts/Managed Funds
    	driver.findElement(By.name("data[assets_libility_assets][asset_shares][value][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_assets][asset_shares][value][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Owned Outright NO
    	WebElement opt2 = driver.findElement(By.name("data[assets_libility_assets][asset_shares][own][]"));
    	Select opts2 = new Select (opt2);
    	opts2.selectByVisibleText("No");
    	Thread.sleep(1000);
    	
    	//Motor Vehicle
    	driver.findElement(By.name("data[assets_libility_assets][asset_vehicle][value][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_assets][asset_vehicle][value][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Owned Outright NO
    	WebElement opt3 = driver.findElement(By.name("data[assets_libility_assets][asset_vehicle][own][]"));
    	Select opts3 = new Select (opt3);
    	opts3.selectByVisibleText("No");
    	Thread.sleep(1000);
    	
    	//Plant and Equipment
    	driver.findElement(By.name("data[assets_libility_assets][asset_plant][value][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_assets][asset_plant][value][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Owned Outright NO
    	WebElement opt4 = driver.findElement(By.name("data[assets_libility_assets][asset_plant][own][]"));
    	Select opts4 = new Select (opt4);
    	opts4.selectByVisibleText("No");
    	Thread.sleep(1000);
    	
    	//Superannuation
    	driver.findElement(By.name("data[assets_libility_assets][asset_super][value][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_assets][asset_super][value][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Recreational Assets (Boats/RVs/Jet Skis etc.)
    	driver.findElement(By.name("data[assets_libility_assets][asset_recreate][value][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_assets][asset_recreate][value][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Owned Outright NO
    	WebElement opt5 = driver.findElement(By.name("data[assets_libility_assets][asset_recreate][own][]"));
    	Select opts5 = new Select (opt5);
    	opts5.selectByVisibleText("No");
    	Thread.sleep(1000);
    	
    	//Home Contents
    	driver.findElement(By.name("data[assets_libility_assets][asset_homecontent][value][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_assets][asset_homecontent][value][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Liabilities
    	//Home Property Loan
    	//Balance
    	driver.findElement(By.name("data[assets_libility_liabs][lib_home][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_home][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_home][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_home][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_home][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_home][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	
    	//Investment Property Loan
    	driver.findElement(By.name("data[assets_libility_liabs][lib_investment][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_investment][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_investment][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_investment][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_investment][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_investment][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	
    	//Margin Loan
    	driver.findElement(By.name("data[assets_libility_liabs][lib_margin][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_margin][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_margin][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_margin][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_margin][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_margin][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	
    	//Motor Vehicle Loan
    	driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	//payment
    	WebElement gender1c1 = driver.findElement(By.name("data[assets_libility_liabs][lib_vehicle][payout][]"));
		Select gc1 = new Select(gender1c1);
		gc1.selectByVisibleText("No");
		Thread.sleep(500);
    	
    	//Recreational Assets Loan
		driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	//payment
    	WebElement gender1c2 = driver.findElement(By.name("data[assets_libility_liabs][lib_recreate][payout][]"));
		Select gc2 = new Select(gender1c2);
		gc2.selectByVisibleText("No");
		Thread.sleep(500);
		
		//Plant and Equipment Loan
		driver.findElement(By.name("data[assets_libility_liabs][lib_plant][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_plant][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_plant][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_plant][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_plant][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_plant][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	//payment
    	WebElement gen1 = driver.findElement(By.name("data[assets_libility_liabs][lib_plant][payout][]"));
		Select genn1 = new Select(gen1);
		genn1.selectByVisibleText("No");
		Thread.sleep(500);
		
		//Loan for Investments
		driver.findElement(By.name("data[assets_libility_liabs][lib_shares][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_shares][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_shares][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_shares][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_shares][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_shares][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
		
    	//Personal Loan
    	driver.findElement(By.name("data[assets_libility_liabs][lib_personal][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_personal][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_personal][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_personal][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_personal][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_personal][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	
		//Credit Cards
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	//Limit
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][limit][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_credit][limit][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	//Overdraft
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	//Limit
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][limit][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_overdraft][limit][]")).sendKeys("100");
    	Thread.sleep(500);
    	
    	//Debt Agreement
    	driver.findElement(By.name("data[assets_libility_liabs][lib_debtagreement][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_debtagreement][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_debtagreement][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_debtagreement][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_debtagreement][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_debtagreement][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	
    	//Tax Debt
    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][balance_owing][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][balance_owing][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Monthly Payment
    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][monthly_payment][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][monthly_payment][]")).sendKeys("500");
    	Thread.sleep(500);
    	//Financier
    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][bank_institute][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility_liabs][lib_taxdebt][bank_institute][]")).sendKeys("HSBC");
    	Thread.sleep(500);
    	
    	//Other Expenses
    	//Monthly Rental/Board Payment
    	driver.findElement(By.name("data[assets_libility][monthly_rent_board][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility][monthly_rent_board][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Motor Vehicle Running Costs 
    	driver.findElement(By.name("data[assets_libility][vehicle_running_cost][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility][vehicle_running_cost][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Other Expenses
    	driver.findElement(By.name("data[assets_libility][other_expenses][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility][other_expenses][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//General Living Expenses *
    	driver.findElement(By.name("data[assets_libility][general_living][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility][general_living][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Private Education & Childcare
    	driver.findElement(By.name("data[assets_libility][private_education_childcare][]")).clear();
    	Thread.sleep(500);
    	driver.findElement(By.name("data[assets_libility][private_education_childcare][]")).sendKeys("500");
    	Thread.sleep(500);
    	
    	//Foreseeable Changes
    	driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[4]/div/div/div/div/div[4]/div/div/label[2]/input")).click();
    	Thread.sleep(500);
    	
		//next
		driver.findElement(By.xpath(".//*[@id='next4']")).click();
		Thread.sleep(2000);
		
		//tab-5 
		/*List <WebElement> radios5 = driver.findElements(By.name("data[personal_details][vehicle_for1][]"));
		int o5 = radios5.size();                                      
		System.out.println(o5);
		radios5.get(1).click();
		Thread.sleep(200);*/
		
		//Make
		driver.findElement(By.name("data[new_vehicle_detail][make][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][make][]")).sendKeys("Honda");
		Thread.sleep(200);
		
		//Model
		driver.findElement(By.name("data[new_vehicle_detail][model][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][model][]")).sendKeys("45ss11");
		Thread.sleep(200);          
		
		//Year
		WebElement years5 = driver.findElement(By.name("data[new_vehicle_detail][year][]"));
    	Select year5 = new Select (years5);             
    	year5.selectByVisibleText("2016");
    	Thread.sleep(1000);
    	
    	//KMs
    	driver.findElement(By.name("data[new_vehicle_detail][kms][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][kms][]")).sendKeys("11");
		Thread.sleep(200);
		
		driver.findElement(By.name("data[new_vehicle_detail][amount_to_finance][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][amount_to_finance][]")).sendKeys("1000");
		Thread.sleep(200);
		
		//Term
		WebElement years6 = driver.findElement(By.name("data[new_vehicle_detail][term][]"));
    	Select year6 = new Select (years6);             
    	year6.selectByVisibleText("5");
    	Thread.sleep(1000);
    	
    	//Balloon
    	driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][baloon][]")).sendKeys("11");
		Thread.sleep(200);
		

        //Dealership/Vendor
		driver.findElement(By.name("data[new_vehicle_detail][dealership][]")).clear();
		driver.findElement(By.name("data[new_vehicle_detail][dealership][]")).sendKeys("EnterDealer");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next5']")).click();
		Thread.sleep(2000);
		Robot r3 = new Robot();
		
		//Copy of Drivers Licence (clear colour copy) 
		driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[1]/div/div/div/span/label/span")).click();
		driver.switchTo()             
	            .activeElement()
	            	.sendKeys(
	                    "/home/nadsoft/Desktop/Backup/ISTQB/20MB.pdf");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
		r3.keyPress(KeyEvent.VK_ESCAPE);
		r3.keyRelease(KeyEvent.VK_ESCAPE);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(1000);
		
		try {
			WebElement e = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
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
			WebElement e1 = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
		    boolean actualValue1 = e1.isEnabled();
		    if (actualValue1){
		           System.out.println("Button is enabled");
		    }else
		           System.out.println("Button is disabled");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Most Recent Payslip-1 (must indicate YTD figures)
		driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[2]/div/div/div/span/label/span")).click();
		driver.switchTo()
	            .activeElement()
	            	.sendKeys(
	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-2. paper-2.pdf");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		r3.keyPress(KeyEvent.VK_ESCAPE);
		r3.keyRelease(KeyEvent.VK_ESCAPE);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(500);
  	    
  	    
  	  try {
			WebElement e = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
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
			WebElement e1 = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
		    boolean actualValue1 = e1.isEnabled();
		    if (actualValue1){
		           System.out.println("Button is enabled");
		    }else
		           System.out.println("Button is disabled");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Most Recent Payslip-2 (must indicate YTD figures)
		driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[3]/div/div/div/span/label/span")).click();
		driver.switchTo()
	            .activeElement()
	            	.sendKeys(
	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-3.pdf");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		r3.keyPress(KeyEvent.VK_ESCAPE);
		r3.keyRelease(KeyEvent.VK_ESCAPE);
		
		//scroll down
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(500);
  	    
  	  try {
			WebElement e = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
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
			WebElement e1 = driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[4]/div/button"));
		    boolean actualValue1 = e1.isEnabled();
		    if (actualValue1){
		           System.out.println("Button is enabled");
		    }else
		           System.out.println("Button is disabled");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	    
		System.out.println("Pass");
		
		//Rates Notices for Home Owner (If applicable)
		driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[4]/div/div/div/span/label/span")).click();
		driver.switchTo()
	            .activeElement()
	            	.sendKeys(
	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-4.pdf");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		r3.keyPress(KeyEvent.VK_ESCAPE);
		r3.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(2000);
		
		//Other Documents
				driver.findElement(By.xpath(".//*[@id='upload']/div/div/div/div/div[1]/div[1]/div[5]/div/div/div/span/label/span")).click();
				driver.switchTo()
			            .activeElement()
			            	.sendKeys(
			                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-5.pdf");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(5000);
				r3.keyPress(KeyEvent.VK_ESCAPE);
				r3.keyRelease(KeyEvent.VK_ESCAPE);
				Thread.sleep(2000);
		
		//Admin Notes
		driver.findElement(By.name("data[personal_details][anote][]")).clear();
		driver.findElement(By.name("data[personal_details][anote][]")).sendKeys("Admin note");
		Thread.sleep(2000);
		
		/*//submit
		driver.findElement(By.xpath(".//*[@id='references']/div/div/div/div/div[6]/div/button")).click();
		Thread.sleep(2000);*/
 	}
 	
 	public void  editBusinessLoanApplicationWithDSriveOn() throws InterruptedException, AWTException{
 		
 		//Dashboard
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
 		Thread.sleep(2000);
 				
 		//Applications 
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/a")).click();
 		Thread.sleep(2000);
 		//View All  
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[3]/ul/li[1]/a")).click();
 		Thread.sleep(10000);
 		
 		//action
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[14]/div/button")).click();
 		Thread.sleep(2000);
 		
 		//edit
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[14]/div/div/div/a[2]")).click();
 		Thread.sleep(2000);               
 		
 		/*//drive on option
 		driver.findElement(By.xpath("html/body/div[1]/div[1]/div[5]/div/div/div/div/div/a[2]")).click();
 		Thread.sleep(2000);*/
 		
 		//Company details
 		//Borrower Type - Primary Buyer
		/*List <WebElement> radios1 = driver.findElements(By.name("data[company_application][borrow_type1][]"));
		radios1.get(0).click();
		Thread.sleep(200);*/
		
		//Company details - Trading Name
		driver.findElement(By.name("data[company_details][tradingname][]")).clear();
		driver.findElement(By.name("data[company_details][tradingname][]")).sendKeys("Drive Test Micro trading");
		Thread.sleep(200);
		
		//Legal Name
		driver.findElement(By.name("data[company_details][legalname][]")).clear();
		driver.findElement(By.name("data[company_details][legalname][]")).sendKeys("Micro");
		Thread.sleep(200);
		
		//Type of Business
		driver.findElement(By.name("data[company_details][type_of_business][]")).clear();
		driver.findElement(By.name("data[company_details][type_of_business][]")).sendKeys("Electric");
		Thread.sleep(200);
		
		//ACN/ABN
		driver.findElement(By.name("data[company_details][acn_abn][]")).clear();
		driver.findElement(By.name("data[company_details][acn_abn][]")).sendKeys("455645");
		Thread.sleep(200);
		
		//Date Established
		WebElement established = driver.findElement(By.name("data[company_details][date_esta][]"));
		established.clear();                                 
		established.sendKeys("05/05/2001");
		Thread.sleep(200);
		established.sendKeys(Keys.TAB);
		
		//Current Address
		driver.findElement(By.name("data[company_details][current_address][]")).clear();
		driver.findElement(By.name("data[company_details][current_address][]")).sendKeys("Pune");
		Thread.sleep(200);
		
		//Address Type : RENTING
		List <WebElement> address = driver.findElements(By.name("data[company_details][address_type1][]"));
		int addres = address.size();       
		System.out.println(addres);
		address.get(1).click();
		Thread.sleep(200);
		
		//Current Address Details
		//year
		Select yers = new Select(driver.findElement(By.name("data[company_details][time_at_address_yrs][]")));
		yers.selectByVisibleText("1 Years");
		Thread.sleep(200);
		//months
		Select months = new Select(driver.findElement(By.name("data[company_details][time_at_address_months][]")));
		months.selectByVisibleText("3 Months");                
		Thread.sleep(200);
		
		//Current Address
		driver.findElement(By.name("data[company_details][current_address][]")).clear();
		driver.findElement(By.name("data[company_details][current_address][]")).sendKeys("Chor bazar");
		Thread.sleep(200);
		
		//Suburb
		driver.findElement(By.name("data[company_details][suburb][]")).clear();
		driver.findElement(By.name("data[company_details][suburb][]")).sendKeys("JK market");
		Thread.sleep(200);
		
		//State
		Select states = new Select(driver.findElement(By.name("data[company_details][state][]")));
		states.selectByVisibleText("VIC");
		Thread.sleep(200);
		
		//Postal Code
		driver.findElement(By.name("data[company_details][postal_code][]")).clear();
		driver.findElement(By.name("data[company_details][postal_code][]")).sendKeys("4612");
		Thread.sleep(200);
		
		//Previous Address Details
		//year
				Select yers1 = new Select(driver.findElement(By.name("data[company_details][prev_time_at_address_yrs][]")));
				yers1.selectByVisibleText("3 Years");
				Thread.sleep(200);
				//months
				Select months1 = new Select(driver.findElement(By.name("data[company_details][prev_time_at_address_months][]")));
				months1.selectByVisibleText("3 Months");
				Thread.sleep(200);
				
				//Current Address
				driver.findElement(By.name("data[company_details][previous_address][]")).clear();
				driver.findElement(By.name("data[company_details][previous_address][]")).sendKeys("KLU bazar");
				Thread.sleep(200);
				
				//Suburb
				driver.findElement(By.name("data[company_details][previous_suburb][]")).clear();
				driver.findElement(By.name("data[company_details][previous_suburb][]")).sendKeys("MLM market");
				Thread.sleep(200);
				
				//State
				Select states1 = new Select(driver.findElement(By.name("data[company_details][prev_state][]")));
				states.selectByVisibleText("VIC");
				Thread.sleep(200);
				
				//Postal Code
				driver.findElement(By.name("data[company_details][previous_postal_code][]")).clear();
				driver.findElement(By.name("data[company_details][previous_postal_code][]")).sendKeys("4612");
				Thread.sleep(200);
				
		//Company Annual Profit
		driver.findElement(By.name("data[company_details][annual_profit][]")).clear();
		driver.findElement(By.name("data[company_details][annual_profit][]")).sendKeys("461542");
		Thread.sleep(200);	
		

		//References One - Business Name
		driver.findElement(By.name("data[personal_reference][fname_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][fname_1][]")).sendKeys("Akshay");
		Thread.sleep(200);          
				
		//Contact
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][current_address_1][]")).sendKeys("Pune");
		Thread.sleep(200);
				
		//Phone No
		driver.findElement(By.name("data[personal_reference][phone_1][]")).clear();
		driver.findElement(By.name("data[personal_reference][phone_1][]")).sendKeys("9856125678");
				Thread.sleep(200);
				
		//References Two - Business Name
		driver.findElement(By.name("data[personal_reference][fname_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][fname_2][]")).sendKeys("Amit");
		Thread.sleep(200);
						
		//CurrentAddress
		driver.findElement(By.name("data[personal_reference][current_address_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][current_address_2][]")).sendKeys("Pune");
		Thread.sleep(200);
						
		//Phone No
		driver.findElement(By.name("data[personal_reference][phone_2][]")).clear();
		driver.findElement(By.name("data[personal_reference][phone_2][]")).sendKeys("9975323408");
		Thread.sleep(200);
		
		

		//next
		driver.findElement(By.xpath(".//*[@id='next7']")).click();
		Thread.sleep(2000);
		
		//Driectorship details
		//Number of Directors
		/*driver.findElement(By.name("data[directorship_details][director_number][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_number][]")).sendKeys("3");
		Thread.sleep(200);*/
		
		//Name of Director 1
		driver.findElement(By.name("data[directorship_details][director_1_name][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_1_name][]")).sendKeys("Pravin Jagdale");
		Thread.sleep(200);
		
		//Date Appointed
		WebElement appointmentD1 = driver.findElement(By.name("data[directorship_details][director_1_doa][]"));
		appointmentD1.clear();
		appointmentD1.sendKeys("01/05/1995");
		Thread.sleep(200);
		appointmentD1.sendKeys(Keys.TAB);
		
		//Date of Birth
		WebElement birthD1 = driver.findElement(By.name("data[directorship_details][director_1_dob][]"));
		birthD1.clear();
		birthD1.sendKeys("01/05/1980");
		Thread.sleep(200);
		birthD1.sendKeys(Keys.TAB);
		
		//Name of Director 2
		driver.findElement(By.name("data[directorship_details][director_2_name][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_2_name][]")).sendKeys("Mohsin Jamadar");
		Thread.sleep(200);
				
		//Date Appointed
		WebElement appointmentD2 = driver.findElement(By.name("data[directorship_details][director_2_doa][]"));
		appointmentD2.clear();
		appointmentD2.sendKeys("01/08/2001");
		Thread.sleep(200);
		appointmentD2.sendKeys(Keys.TAB);
				
		//Date of Birth
		WebElement birthD2 = driver.findElement(By.name("data[directorship_details][director_2_dob][]"));
		birthD2.clear();
		birthD2.sendKeys("01/05/1990");
		Thread.sleep(200);
		birthD2.sendKeys(Keys.TAB);
		
		//Name of Director 3
		driver.findElement(By.name("data[directorship_details][director_3_name][]")).clear();
		driver.findElement(By.name("data[directorship_details][director_3_name][]")).sendKeys("Rakesh Deshpande");
		Thread.sleep(200);
						
		//Date Appointed
		WebElement appointmentD3 = driver.findElement(By.name("data[directorship_details][director_3_doa][]"));
		appointmentD3.clear();
		appointmentD3.sendKeys("04/07/2009");
		Thread.sleep(200);
		appointmentD3.sendKeys(Keys.TAB);
						
		//Date of Birth
		WebElement birthD3 = driver.findElement(By.name("data[directorship_details][director_3_dob][]"));
		birthD3.clear();
		birthD3.sendKeys("01/05/1993");
		Thread.sleep(200);
		birthD3.sendKeys(Keys.TAB);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next8']")).click();
		Thread.sleep(2000);	
		
		//Financial details
		//Assets and Libialities - Home
		driver.findElement(By.name("data[assets_libility][home][]")).clear();
		driver.findElement(By.name("data[assets_libility][home][]")).sendKeys("100");
		Thread.sleep(200);          
		
		//Investment Property
		driver.findElement(By.name("data[assets_libility][investment_property][]")).clear();
		driver.findElement(By.name("data[assets_libility][investment_property][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Cash Back
		driver.findElement(By.name("data[assets_libility][cash_back][]")).clear();
		driver.findElement(By.name("data[assets_libility][cash_back][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Vehicles
		driver.findElement(By.name("data[assets_libility][vehicles][]")).clear();
		driver.findElement(By.name("data[assets_libility][vehicles][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Household Effects
		driver.findElement(By.name("data[assets_libility][household_effect][]")).clear();
		driver.findElement(By.name("data[assets_libility][household_effect][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Shares
		driver.findElement(By.name("data[assets_libility][shares][]")).clear();
		driver.findElement(By.name("data[assets_libility][shares][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Mortgage/Rent
		driver.findElement(By.name("data[assets_libility][mortgage_rent][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][mortgage_rent][monthly_payment][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][mortgage_rent][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][mortgage_rent][balance_owing][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][mortgage_rent][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][mortgage_rent][bank_institute][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Investment Mortgage
		driver.findElement(By.name("data[assets_libility][investment_mortgage][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][investment_mortgage][monthly_payment][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][investment_mortgage][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][investment_mortgage][balance_owing][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][investment_mortgage][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][investment_mortgage][bank_institute][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Personal Loans
		driver.findElement(By.name("data[assets_libility][personal_loan][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][personal_loan][monthly_payment][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][personal_loan][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][personal_loan][balance_owing][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][personal_loan][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][personal_loan][bank_institute][]")).sendKeys("100");
		Thread.sleep(200);
		
		//General Living Expenses
		driver.findElement(By.name("data[assets_libility][general_living][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][general_living][monthly_payment][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][general_living][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][general_living][balance_owing][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][general_living][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][general_living][bank_institute][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Child Support
		driver.findElement(By.name("data[assets_libility][child_support][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][child_support][monthly_payment][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][child_support][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][child_support][balance_owing][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][child_support][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][child_support][bank_institute][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Car Loans
		driver.findElement(By.name("data[assets_libility][car_loans][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][car_loans][monthly_payment][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][car_loans][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][car_loans][balance_owing][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][car_loans][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][car_loans][bank_institute][]")).sendKeys("100");
		Thread.sleep(200);
		
		//Credit Cards
		driver.findElement(By.name("data[assets_libility][credit_card][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][credit_card][monthly_payment][]")).sendKeys("100");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][credit_card][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][credit_card][balance_owing][]")).sendKeys("111");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][credit_card][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][credit_card][bank_institute][]")).sendKeys("111");
		Thread.sleep(200);
		
		//Other
		driver.findElement(By.name("data[assets_libility][other][monthly_payment][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][monthly_payment][]")).sendKeys("101");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][balance_owing][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][balance_owing][]")).sendKeys("841");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][bank_institute][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][bank_institute][]")).sendKeys("151");
		Thread.sleep(200);
		
		//Other (please specify)
		driver.findElement(By.name("data[assets_libility][other][monthly_payment_1][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][monthly_payment_1][]")).sendKeys("101");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][balance_owing_1][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][balance_owing_1][]")).sendKeys("841");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][bank_institute_1][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][bank_institute_1][]")).sendKeys("151");
		Thread.sleep(200);
		
		//Other (please specify)
		driver.findElement(By.name("data[assets_libility][other][monthly_payment_2][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][monthly_payment_2][]")).sendKeys("101");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][balance_owing_2][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][balance_owing_2][]")).sendKeys("841");
		Thread.sleep(200);
		driver.findElement(By.name("data[assets_libility][other][bank_institute_2][]")).clear();
		driver.findElement(By.name("data[assets_libility][other][bank_institute_2][]")).sendKeys("151");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next4']")).click();
		Thread.sleep(2000);
		
		//Account details
		//Accountant
		driver.findElement(By.name("data[accountant_details][accountant][]")).clear();
		driver.findElement(By.name("data[accountant_details][accountant][]")).sendKeys("HDFC");
		Thread.sleep(200);
		
		//Contact Name
		driver.findElement(By.name("data[accountant_details][contact_name][]")).clear();
		driver.findElement(By.name("data[accountant_details][contact_name][]")).sendKeys("Rehan Khan");
		Thread.sleep(200);
		
		//Phone Number
		driver.findElement(By.name("data[accountant_details][phone_number][]")).clear();
		driver.findElement(By.name("data[accountant_details][phone_number][]")).sendKeys("9719734682");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next9']")).click();
		Thread.sleep(2000); 	
		
		//Personal Details -
		//Borrower Type - 	Primary Buyer 
		List <WebElement> radios = driver.findElements(By.name("data[individual_application][borrow_type1][]"));
		int o = radios.size();                                        
		System.out.println(o);
		radios.get(2).click();
		Thread.sleep(200);
		
		//First name
		driver.findElement(By.name("data[personal_details][fname][]")).clear();
		driver.findElement(By.name("data[personal_details][fname][]")).sendKeys("Drive");
		Thread.sleep(200);          
				
		//Middle name
		driver.findElement(By.name("data[personal_details][mname][]")).clear();
		driver.findElement(By.name("data[personal_details][mname][]")).sendKeys("On");
		Thread.sleep(200);
				
		//Surname
		driver.findElement(By.name("data[personal_details][surname][]")).clear();
		driver.findElement(By.name("data[personal_details][surname][]")).sendKeys("Business");
		Thread.sleep(200);
				
		//Gender
		List <WebElement> genders = driver.findElements(By.name("data[personal_details][gender1][]"));
		int gender = genders.size();       
		System.out.println(gender);
		genders.get(0).click();
		Thread.sleep(200);
				
		//Date of Birth
		WebElement dob = driver.findElement(By.name("data[personal_details][dob][]"));
		dob.clear();
		dob.sendKeys("03/08/1992");
		Thread.sleep(200);
		dob.sendKeys(Keys.TAB);
				
		//Marital Status
		WebElement gender1 = driver.findElement(By.name("data[personal_details][marital_status][]"));
		Select g = new Select(gender1);
		g.selectByVisibleText("Single");
		Thread.sleep(500);
				
		//Drivers License No 
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).clear();
		driver.findElement(By.name("data[personal_details][driver_license_no][]")).sendKeys("869512");
		Thread.sleep(200);
				
		//Expiry
		WebElement expiry1 = driver.findElement(By.name("data[personal_details][expiry][]"));
		expiry1.clear();
		expiry1.sendKeys("05/05/2020");
		Thread.sleep(200);
		expiry1.sendKeys(Keys.TAB);
		
		//Email Address
				driver.findElement(By.name("data[personal_details][email][]")).clear();
				driver.findElement(By.name("data[personal_details][email][]")).sendKeys("testt@t.com");
				Thread.sleep(200);
						
				//Mobile Phone No 
				driver.findElement(By.name("data[personal_details][mobile_phone][]")).clear();
				driver.findElement(By.name("data[personal_details][mobile_phone][]")).sendKeys("8945561278");
				Thread.sleep(200);
						
				//Home Phone No
				driver.findElement(By.name("data[personal_details][home_phone][]")).clear();
				driver.findElement(By.name("data[personal_details][home_phone][]")).sendKeys("02356784545");
				Thread.sleep(200);
				
		//No. of Dependants
		Select noDep = new Select(driver.findElement(By.name("data[personal_details][no_of_department][]")));
		noDep.selectByVisibleText("4");
		Thread.sleep(200);
		
				
		/*//No.of Departments
		driver.findElement(By.name("data[personal_details][no_of_department][]")).clear();
		driver.findElement(By.name("data[personal_details][no_of_department][]")).sendKeys("1");
		Thread.sleep(200);
				
		//Age of Department
		driver.findElement(By.name("data[personal_details][age_of_depart][]")).clear();
		driver.findElement(By.name("data[personal_details][age_of_depart][]")).sendKeys("1");
		Thread.sleep(200);*/
				
		//Residency Status
		List <WebElement> residency = driver.findElements(By.name("data[personal_details][residential_status1][]"));
		int ststus = residency.size();       
		System.out.println(ststus);
		residency.get(1).click();
		Thread.sleep(200);
				
		
		/*		
		//Direct Debit Bank account Details  - Account Name
		driver.findElement(By.name("data[personal_details][account_name][]")).clear();
		driver.findElement(By.name("data[personal_details][account_name][]")).sendKeys("Panjab national bank");
		Thread.sleep(200);
				
		//BSB
		driver.findElement(By.name("data[personal_details][bsb][]")).clear();
		driver.findElement(By.name("data[personal_details][bsb][]")).sendKeys("4532645");
		Thread.sleep(200);
				
		//AccountNo
		driver.findElement(By.name("data[personal_details][account_no][]")).clear();
		driver.findElement(By.name("data[personal_details][account_no][]")).sendKeys("8945126587");
		Thread.sleep(200);*/
		
		
		
		//next
		driver.findElement(By.xpath(".//*[@id='next1']")).click();
		Thread.sleep(2000);
				
		//Current Address Details - Current Address
		driver.findElement(By.name("data[address_details][current_address][]")).clear();
		driver.findElement(By.name("data[address_details][current_address][]")).sendKeys("Viman Nagar, Pune");
		Thread.sleep(200);
				
		//suburb
		driver.findElement(By.name("data[address_details][suburb][]")).clear();
		driver.findElement(By.name("data[address_details][suburb][]")).sendKeys("Viman Nagar");
		Thread.sleep(200);
		
		//state
		WebElement state11 = driver.findElement(By.name("data[address_details][state][]"));
    	Select states11 = new Select (state11);
    	states11.selectByVisibleText("NSW");
    	Thread.sleep(1000);
				
		//Postal Code
		driver.findElement(By.name("data[address_details][postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][postal_code][]")).sendKeys("451124");
		Thread.sleep(200);          
				
		//Time At Address
		WebElement years1 = driver.findElement(By.name("data[address_details][time_at_address_yrs][]"));
    	Select year1 = new Select (years1);
    	year1.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months111 = driver.findElement(By.name("data[address_details][time_at_address_months][]"));
    	Select month111 = new Select (months111);
    	month111.selectByVisibleText("1 Months");
    	Thread.sleep(1000);          
				
		//Current Resident Type
		List <WebElement> cResidency = driver.findElements(By.name("data[address_details][residential_type1][]"));
		int type = cResidency.size();                                                                                           
		System.out.println(type);
		cResidency.get(0).click();
		Thread.sleep(200);
				
		//Previous Address Details - Prev Time At Address
		WebElement years2 = driver.findElement(By.name("data[address_details][prev_time_at_address_yrs][]"));
    	Select year2 = new Select (years2);
    	year2.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months2 = driver.findElement(By.name("data[address_details][prev_time_at_address_months][]"));
    	Select month2 = new Select (months2);
    	month2.selectByVisibleText("1 Months");
    	Thread.sleep(1000);
		
		//Previous Address
		driver.findElement(By.name("data[address_details][previous_address][]")).clear();
		driver.findElement(By.name("data[address_details][previous_address][]")).sendKeys("Yerwada, Pune");
		Thread.sleep(200);
				
		//suburb
		driver.findElement(By.name("data[address_details][previous_suburb][]")).clear();
		driver.findElement(By.name("data[address_details][previous_suburb][]")).sendKeys("Yerwada");
		Thread.sleep(200);
		
		//state
				WebElement state12 = driver.findElement(By.name("data[address_details][prev_state][]"));
		    	Select states12 = new Select (state12);
		    	states12.selectByVisibleText("NSW");
		    	Thread.sleep(2000);
				
		//Postal Code
		driver.findElement(By.name("data[address_details][previous_postal_code][]")).clear();
		driver.findElement(By.name("data[address_details][previous_postal_code][]")).sendKeys("45254");
		Thread.sleep(200);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next2']")).click();
		Thread.sleep(2000);
		
		/*//Current Employment Details - Current Employer
		driver.findElement(By.name("data[employment_details][current_employer][]")).clear();
		driver.findElement(By.name("data[employment_details][current_employer][]")).sendKeys("MSoft");
		Thread.sleep(200);
				
		//Employer Address
		driver.findElement(By.name("data[employment_details][employer_address][]")).clear();
		driver.findElement(By.name("data[employment_details][employer_address][]")).sendKeys("Yerwada, Pune");
		Thread.sleep(200);
				
		//Phone No
		driver.findElement(By.name("data[employment_details][employer_phone_no][]")).clear();
		driver.findElement(By.name("data[employment_details][employer_phone_no][]")).sendKeys("8945124587");
		Thread.sleep(200);
				
		//Occupation
		driver.findElement(By.name("data[employment_details][occupation][]")).clear();
		driver.findElement(By.name("data[employment_details][occupation][]")).sendKeys("Job");
		Thread.sleep(200);
				
		//Time Employed
		WebElement years11 = driver.findElement(By.name("data[employment_details][time_employed_yrs][]"));
    	Select year11 = new Select (years11);
    	year11.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months11 = driver.findElement(By.name("data[employment_details][time_employed_months][]"));
    	Select month11 = new Select (months11);
    	month11.selectByVisibleText("1 Months");
    	Thread.sleep(1000);
		
		//Contact name
		driver.findElement(By.name("data[employment_details][prev_contact_name][]")).clear();
		driver.findElement(By.name("data[employment_details][prev_contact_name][]")).sendKeys("500000");
		Thread.sleep(200);
				
		//Net Income $
		driver.findElement(By.name("data[employment_details][net_income][]")).clear();
		driver.findElement(By.name("data[employment_details][net_income][]")).sendKeys("50000");
		Thread.sleep(200);          
				
		//Frequency
		List <WebElement> e1 = driver.findElements(By.name("data[employment_details][period1][]"));
		int i1 = e1.size();                                                                 
		System.out.println(i1);
		e1.get(0).click();
		Thread.sleep(200);
				
		//Previous Employment Details - Previous employer 
		driver.findElement(By.name("data[employment_details][previous_employer][]")).clear();
		driver.findElement(By.name("data[employment_details][previous_employer][]")).sendKeys("OrangeSoft");
		Thread.sleep(200);
				
		//Phone No 
		driver.findElement(By.name("data[employment_details][prev_phone_no][]")).clear();
		driver.findElement(By.name("data[employment_details][prev_phone_no][]")).sendKeys("7852147851");
		Thread.sleep(200);
				
		//Previous Occupation
		driver.findElement(By.name("data[employment_details][prev_occupation][]")).clear();
		driver.findElement(By.name("data[employment_details][prev_occupation][]")).sendKeys("Job");
		Thread.sleep(200);
				
		//Time Employed
		WebElement years6 = driver.findElement(By.name("data[employment_details][prev_time_employed_yrs][]"));
    	Select year6 = new Select (years6);
    	year6.selectByVisibleText("1 Years");
    	Thread.sleep(1000);
    	
    	WebElement months7 = driver.findElement(By.name("data[employment_details][prev_time_employed_months][]"));
    	Select month7 = new Select (months7);
    	month7.selectByVisibleText("1 Months");
    	Thread.sleep(1000);
    	
    	//Additional Monthly Income
    	driver.findElement(By.name("data[additional_monthly_income][type1][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type1][]")).sendKeys("Test Property");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value1][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value1][]")).sendKeys("1000");
    	Thread.sleep(500);
    	
    	driver.findElement(By.name("data[additional_monthly_income][type2][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type2][]")).sendKeys("Test Investment");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value2][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value2][]")).sendKeys("1000");
    	Thread.sleep(500);
    	
    	driver.findElement(By.name("data[additional_monthly_income][type3][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type3][]")).sendKeys("Test Chile Care");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value3][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value3][]")).sendKeys("1000");
    	Thread.sleep(500);
    	
    	driver.findElement(By.name("data[additional_monthly_income][type4][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type4][]")).sendKeys("Test One");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value4][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value4][]")).sendKeys("1000");
    	Thread.sleep(500);
    	
    	driver.findElement(By.name("data[additional_monthly_income][type5][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][type5][]")).sendKeys("Test Two");
    	Thread.sleep(500);
    	driver.findElement(By.name("data[additional_monthly_income][value5][]")).clear();
    	driver.findElement(By.name("data[additional_monthly_income][value5][]")).sendKeys("1000");
    	Thread.sleep(500);
		
		//next
		driver.findElement(By.xpath(".//*[@id='next3']")).click();
		Thread.sleep(2000);*/
		
		
				
		
		//Vehicle Details - Vehicle For
		/*List <WebElement> vehicle1 = driver.findElements(By.name("data[personal_details][vehicle_for1][]"));
		int v1 = vehicle1.size();       
		System.out.println(v1);
		vehicle1.get(0).click();
		Thread.sleep(200);*/
		
		//Make
		driver.findElement(By.name("data[commercial_new_vehicle_detail][make][]")).clear();
		driver.findElement(By.name("data[commercial_new_vehicle_detail][make][]")).sendKeys("Honda");
		Thread.sleep(200);
		
		//Model
		driver.findElement(By.name("data[commercial_new_vehicle_detail][model][]")).clear();
		driver.findElement(By.name("data[commercial_new_vehicle_detail][model][]")).sendKeys("Accord");
		Thread.sleep(200);
		
		//Year
		WebElement years13 = driver.findElement(By.name("data[commercial_new_vehicle_detail][year][]"));
    	Select year13 = new Select (years13);            
    	year13.selectByVisibleText("2014");
    	Thread.sleep(1000);
		
		//KMs
		driver.findElement(By.name("data[commercial_new_vehicle_detail][kms][]")).clear();
		driver.findElement(By.name("data[commercial_new_vehicle_detail][kms][]")).sendKeys("200");
		Thread.sleep(200);
		
		//Accessories
		driver.findElement(By.name("data[commercial_new_vehicle_detail][amount_finance][]")).clear();
		driver.findElement(By.name("data[commercial_new_vehicle_detail][amount_finance][]")).sendKeys("Yes");
		Thread.sleep(200);
		
		//Amount to Finance ($)
		driver.findElement(By.name("data[commercial_new_vehicle_detail][amount_finance][]")).clear();
		driver.findElement(By.name("data[commercial_new_vehicle_detail][amount_finance][]")).sendKeys("10000");
		Thread.sleep(200);
		
		//Term
		WebElement months8 = driver.findElement(By.name("data[new_vehicle_detail][term][]"));
    	Select month8 = new Select (months8);
    	month8.selectByVisibleText("5");
    	Thread.sleep(1000);
		
		//Baloon
		driver.findElement(By.name("data[commercial_new_vehicle_detail][baloon][]")).clear();
		driver.findElement(By.name("data[commercial_new_vehicle_detail][baloon][]")).sendKeys("50");
		Thread.sleep(200);
	
	    //Dealership 	
		driver.findElement(By.name("data[commercial_new_vehicle_detail][dealership][]")).clear();
		driver.findElement(By.name("data[commercial_new_vehicle_detail][dealership][]")).sendKeys("DealershipTest");
		Thread.sleep(200);
		
		//Phone Number
		driver.findElement(By.name("data[commercial_new_vehicle_detail][phone_number][]")).clear();
		driver.findElement(By.name("data[commercial_new_vehicle_detail][phone_number][]")).sendKeys("7889455641");
		Thread.sleep(200);
		
		
		//next
		driver.findElement(By.xpath(".//*[@id='next10']")).click();
		Thread.sleep(2000);
		
		 
			//admin note
			driver.findElement(By.name("data[personal_details][anote][]")).clear();
			driver.findElement(By.name("data[personal_details][anote][]")).sendKeys("Admin note");
			Thread.sleep(200);          
			
			//Payslip 1
			WebElement upload1 = driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[2]/div/div/div/span/label/span"));
	    	Actions action1 = new Actions(driver);            
	    	action1.moveToElement(upload1).perform();
	    	Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[2]/div/div/div/span/label/span")).click();
	    	driver.switchTo()            
		            .activeElement()
		            .sendKeys(
		                    "/home/nadsoft/Desktop/Backup/ISTQB/20MB.pdf");
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    Thread.sleep(3000);
		    Robot rrr = new Robot();
		    rrr.keyPress(KeyEvent.VK_ESCAPE);
		    rrr.keyRelease(KeyEvent.VK_ESCAPE);
		    
		  //scroll down
			jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,500)", "");
			Thread.sleep(1000);
		    
			
			try {
				WebElement e = driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[4]/div/button"));
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
				WebElement e1 = driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[4]/div/button"));
			    boolean actualValue1 = e1.isEnabled();
			    if (actualValue1){
			           System.out.println("Button is enabled");
			    }else
			           System.out.println("Button is disabled");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
			System.out.println("Pass");
		    
		  //Payslip 2
		    WebElement upload2 = driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[3]/div/div/div/span/label/span"));
	    	Actions action2 = new Actions(driver);
	    	action2.moveToElement(upload2).perform();
	    	Thread.sleep(2000);
		  		driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[3]/div/div/div/span/label/span")).click();
		      	driver.switchTo()            
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-2. paper-2.pdf");
		  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  	    Thread.sleep(5000);
		  	    rrr.keyPress(KeyEvent.VK_ESCAPE);
		  	    rrr.keyRelease(KeyEvent.VK_ESCAPE);
		  	    Thread.sleep(2000);
		  	    
		  //Copy of Driver's Licenses*	
		  	  WebElement upload3 = driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[4]/div/div/div/span/label/span"));
		    	Actions action3 = new Actions(driver);
		    	action3.moveToElement(upload3).perform();
		    	Thread.sleep(2000);
		  	  driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[4]/div/div/div/span/label/span")).click();
		      	driver.switchTo()
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-3.pdf");
		  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  	    Thread.sleep(5000);
		  	    rrr.keyPress(KeyEvent.VK_ESCAPE);
		  	    rrr.keyRelease(KeyEvent.VK_ESCAPE);
		  	    Thread.sleep(2000);
		  	    
		  //Rates Notices for Home Owner (If applicable)	
		  	  WebElement upload4 = driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[5]/div/div/div/span/label/span"));
		    	Actions action4 = new Actions(driver);
		    	action4.moveToElement(upload4).perform();
		    	Thread.sleep(2000);
		  	  driver.findElement(By.xpath(".//*[@id='udoc']/div/div/div/div/div[1]/div[1]/div[5]/div/div/div/span/label/span")).click();
		      	driver.switchTo()
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/Backup/ISTQB/Paper-4.pdf");
		  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  	    Thread.sleep(5000);
		  	    rrr.keyPress(KeyEvent.VK_ESCAPE);
		  	    rrr.keyRelease(KeyEvent.VK_ESCAPE);
		  	    Thread.sleep(2000);
		  	    
		  /*//Latest tax return or notice of assessment	
		  	  WebElement upload5 = driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[9]/div/div/div/div/div[2]/div[6]/div/div/div/span/label/span"));
		    	Actions action5 = new Actions(driver);
		    	action5.moveToElement(upload5).perform();
		    	Thread.sleep(2000);
		  	  driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[9]/div/div/div/div/div[2]/div[6]/div/div/div/span/label/span")).click();
		      	driver.switchTo()
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/DriveOnData/RatesNoticesForHomeOwner.PDF");
		  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  	    Thread.sleep(5000);
		  	    rrr.keyPress(KeyEvent.VK_ESCAPE);
		  	    rrr.keyRelease(KeyEvent.VK_ESCAPE);
		  	    Thread.sleep(2000);
		  	    
		  //Copy of Drivers Licenses*	    
		  	  driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[9]/div/div/div/div/div[2]/div[7]/div/div/div/span/label/span")).click();
		      	driver.switchTo()
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/DriveOnData/RatesNoticesForHomeOwner.PDF");
		  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  	    Thread.sleep(5000);
		  	    rrr.keyPress(KeyEvent.VK_ESCAPE);
		  	    rrr.keyRelease(KeyEvent.VK_ESCAPE);
		  	    Thread.sleep(2000);
		  	    
		  //Rates Notices for Home Owners (if applicable)	    
		  	  driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div/form/div/div[9]/div/div/div/div/div[2]/div[8]/div/div/div/span/label/span")).click();
		      	driver.switchTo()
		  	            .activeElement()
		  	            .sendKeys(
		  	                    "/home/nadsoft/Desktop/DriveOnData/RatesNoticesForHomeOwner.PDF");
		  	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  	    Thread.sleep(5000);
		  	    rrr.keyPress(KeyEvent.VK_ESCAPE);
		  	    rrr.keyRelease(KeyEvent.VK_ESCAPE);
		  	    Thread.sleep(2000); 
			
			
			
			//submit
			driver.findElement(By.xpath(".//*[@id='vehicle']/div/div/div/div/div[5]/div/button")).click();
			Thread.sleep(2000);*/
 	}
 	
 	public void testcsript() throws InterruptedException{
 		
 		//click dashboard
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[1]/a")).click();
 		Thread.sleep(2000);          
 		
 		//click applications
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[2]/a")).click();
 		Thread.sleep(3000);          
 		
 		//click on view all 
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[2]/ul/li[3]/a")).click();
 		Thread.sleep(2000); 
 		
 		driver.findElement(By.xpath("html/body/div[1]/nav/div[2]/div[1]/a/i")).click();
 		Thread.sleep(2000);
 	
 		driver.findElement(By.xpath(".//*[@id='side-menu']/li[4]/a")).click();
 		Thread.sleep(2000); 
 	}
}
