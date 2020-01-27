package com.Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pageClasses.HomeLoanBorrowing;
import com.utility.BrowserFactory;
import com.utility.Reports;
import com.utility.ReusableMethods;

public class TestCases extends Reports{
	WebDriver driver;
	
	String strURL=ReusableMethods.getData("URL");
	ExtentHtmlReporter htmlReporter;
	public  static ExtentReports extent;
	public  static ExtentTest logger;
	
	@BeforeClass
	public void start()
	{
		htmlReporter = new ExtentHtmlReporter("./Reports/LoanCalculator.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		driver=BrowserFactory.startBrowser(driver, ReusableMethods.getData("Browser"));
	}
	
	@AfterClass
	public void tearDown()
	{
		extent.flush();
		BrowserFactory.quitBrowser(driver);
	}
	@Test
	public void verifyBorrowingEstimate() throws IOException, InterruptedException
	{
		driver.get(strURL);
		HomeLoanBorrowing hlb=PageFactory.initElements(driver, HomeLoanBorrowing.class);
		//System.out.println(strURL+" has launched");
		logger=extent.createTest("verifyBorrowingEstimate");
		logger.log(Status.PASS, driver.getTitle()+" page has displaye");
		logger.addScreenCaptureFromPath("./Reports/Capture.jpg");
		logger.log(Status.PASS,"Application type"+"\t'"+hlb.getApplicationType()+"'");
		logger.log(Status.PASS,"Application type"+"\t'"+hlb.getPropertyType()+"'");
		hlb.setYourIncome(ReusableMethods.getData("Income"));
		logger.log(Status.PASS, ReusableMethods.getData("Income")+" has enterd in 'Your income'");
		hlb.setOtherIncome(ReusableMethods.getData("OtherIncom"));
		logger.log(Status.PASS, ReusableMethods.getData("OtherIncom")+" has enterd in 'Your other income'");
		hlb.setLivingExpenses(ReusableMethods.getData("LivingExpenses"));
		logger.log(Status.PASS, ReusableMethods.getData("LivingExpenses")+" has enterd in 'Living Expenses'");
		hlb.setCurrentHomeLoanRepayments(ReusableMethods.getData("CurrentLoanRepayments"));
		logger.log(Status.PASS, ReusableMethods.getData("CurrentLoanRepayments")+" has enterd in 'Current home loan repayments'");
		hlb.setLoanRepayments(ReusableMethods.getData("OtherLoanRepayments"));
		logger.log(Status.PASS, ReusableMethods.getData("OtherLoanRepayments")+" has enterd in 'Other  loan repayments'");
		hlb.setOtherCommitments(ReusableMethods.getData("OtherCommitments"));
		logger.log(Status.PASS, ReusableMethods.getData("OtherCommitments")+" has enterd in 'Other commitments'");
		hlb.setTotalCreditCardLimits(ReusableMethods.getData("TotalCreditCardLimit"));
		logger.log(Status.PASS, ReusableMethods.getData("TotalCreditCardLimit")+" has enterd in 'Total credit card limit'");
		hlb.btWorkOutHowMuchICouldBorrow();
		logger.log(Status.PASS,hlb.getEstimateBorrow());
	}
	
	@Test
	public void verifyClearForm()
	{
		logger=extent.createTest("verifyClearForm");
		HomeLoanBorrowing hlb=PageFactory.initElements(driver, HomeLoanBorrowing.class);
		hlb.btStartOver();
		if(hlb.verifyClearForm()==true)
		{
			logger.log(Status.PASS, "'Home loan borrowing power calculator' form is cleared");
		}
		else {
			logger.log(Status.FAIL, "'Home loan borrowing power calculator' form is not cleared");
		}
		
	}
	@Test
	public void verifyUnableToGiveAnEstimateOfYourBorrowing() throws InterruptedException
	{
		logger=extent.createTest("verifyClearForm");
		HomeLoanBorrowing hlb=PageFactory.initElements(driver, HomeLoanBorrowing.class);
		hlb.setLivingExpenses("1");
		logger.log(Status.PASS, "'1' has enterd in 'Living Expenses'");
		hlb.btWorkOutHowMuchICouldBorrow();
		logger.log(Status.PASS, hlb.verifyUnableToBorrowMsg()+" message has displayed");
		
		
	}
	

}
