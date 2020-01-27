package com.utility;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports {
	
	ExtentHtmlReporter htmlReporter;
	public  static ExtentReports extent;
	public  static ExtentTest logger;
	//@BeforeSuite
	public void reportConfig(String strName)
	{
		htmlReporter = new ExtentHtmlReporter("./Reports/LoanCalculator.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		//logger = extent.createTest(strName);
	}
	//@AfterMethod
	public void getResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.fail(MarkupHelper.createLabel(result.getName()+"Test case failed", ExtentColor.RED));
			logger.fail(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.pass(MarkupHelper.createLabel(result.getName()+"Test case passed", ExtentColor.GREEN));
			
		}
		else {
			logger.pass(MarkupHelper.createLabel(result.getName()+"Test case passed", ExtentColor.YELLOW));	
		}
	}
	
	//@AfterSuite
	public void closeReport()
	{
		extent.close();
	}

}
