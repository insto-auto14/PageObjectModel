package com.pageClasses;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.Reports;

public class HomeLoanBorrowing extends Reports{
	WebDriver driver;
	public HomeLoanBorrowing(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	
	@FindBy(xpath="//span[@id='q2q1i1']/following-sibling::input")
	WebElement yourIncome;
	By incom=By.xpath("//span[@id='q2q1i1']/following-sibling::input");
	@FindBy(xpath="//span[@id='q2q2i1']/following-sibling::input")
	WebElement otherIncom;
	@FindBy(xpath="//span[@id='q3q1i1']/following-sibling::input")
	WebElement livingExpenses;
	@FindBy(xpath="//span[@id='q3q2i1']/following-sibling::input")
	WebElement currentHomeLoanRepayments;
	@FindBy(xpath="//span[@id='q3q3i1']/following-sibling::input")
	WebElement otherLoanRepayments;
	@FindBy(xpath="//span[@id='q3q4i1']/following-sibling::input")
	WebElement otherCommitments;
	@FindBy(xpath="//span[@id='q3q5i1']/following-sibling::input")
	WebElement totalCreditCardLimit;
	@FindBy(xpath="//button[contains(text(),'Work out how much I could borrow')]")
	WebElement btnHowMuchICouldBorrow;
	@FindBy(xpath="//div[@class='borrow__result text--white clearfix']//button")
	WebElement startOver;
	@FindBy(xpath="//span[@class='borrow__result__text']")
	WebElement estimateBorrow;
	By estimateBor=By.xpath("//span[@class='borrow__result__text']");
	@FindBy(xpath="//ul[@class='borrow__question__answer clearfix']//li/label[contains(text(),'Single')]")
	WebElement applicationType;
	@FindBy(xpath="//ul[@class='borrow__question__answer clearfix']//li/label[contains(text(),'Home to live in')]")
	WebElement propertyWouldLikeBuy;
	@FindBy(xpath="//span[@class='borrow__error__text']")
	WebElement unableToBorrow;
	public void setYourIncome(String strYourIncome)
	{
		//waitForElement(incom);
		yourIncome.sendKeys(strYourIncome);
		//logger.pass("SUCCESS");
	}
	
	public String getEstimateBorrow()
	{
		waitForElement(estimateBor);
		String str=estimateBorrow.getText();
		return str;
	}
	
	public void setOtherIncome(String strIncome)
	{
		//waitForElement(incom);
		otherIncom.sendKeys(strIncome);
	}
	public void setLivingExpenses(String strLivingExpenses)
	{
		//waitForElement(incom);
		livingExpenses.sendKeys(strLivingExpenses);
	}
	
	public String getApplicationType()
	{
		String str=applicationType.getText();
		return str;
	}
	
	public String getPropertyType()
	{
		String str=propertyWouldLikeBuy.getText();
		return str;
	}
	
	public void setCurrentHomeLoanRepayments(String strCurrentHomeLoanRepayments)
	{
		//waitForElement(incom);
		currentHomeLoanRepayments.sendKeys(strCurrentHomeLoanRepayments);
	}
	
	public void setLoanRepayments(String strOtherLoanRepayments)
	{
		//waitForElement(incom);
		otherLoanRepayments.sendKeys(strOtherLoanRepayments);
	}
	
	public void setOtherCommitments(String strOtherCommitments)
	{
		//waitForElement(incom);
		otherCommitments.sendKeys(strOtherCommitments);
	}
	
	public void setTotalCreditCardLimits(String strTotalCreditCardLimit)
	{
		//waitForElement(incom);
		totalCreditCardLimit.sendKeys(strTotalCreditCardLimit);
	}
	
	public void btWorkOutHowMuchICouldBorrow() throws InterruptedException
	{
		//btnHowMuchICouldBorrow.click();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", btnHowMuchICouldBorrow);
		Thread.sleep(2000);
	}
	public void btStartOver()
	{
		startOver.click();
	}
	
	public boolean verifyClearForm()
	{
		boolean blnForm=false;
		try {
			System.out.println(yourIncome.getText());
			if(yourIncome.getAttribute("value").equals("0")&&otherIncom.getAttribute("value").equals("0")&&livingExpenses.getAttribute("value").equals("0")&&currentHomeLoanRepayments.getAttribute("value").equals("0")&&
					otherLoanRepayments.getAttribute("value").equals("0")&&otherCommitments.getAttribute("value").equals("0")&&totalCreditCardLimit.getAttribute("value").equals("0"))
			{
				blnForm=true;
			}
			else {
				blnForm=false;
			}
			
		}catch(Exception e)
		{
			
		}
		return blnForm;
	}
	
	public String verifyUnableToBorrowMsg()
	{
		
		String strMsg=unableToBorrow.getText();
		return strMsg;
	}
	public void waitForElement(By strIncome)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(strIncome));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	 public  String capture(String screenShotName) throws IOException
	    {
	        TakesScreenshot ts = (TakesScreenshot)driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String dest = System.getProperty("user.dir") +"\\Reports\\"+screenShotName+".png";
	        File destination = new File(dest);
	        FileUtils.copyFile(source, destination);        
	                     
	        return dest;
	    }
	public  String captureImg(String strName) throws IOException
	{
		 //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File("./Reports/"+strName+".PNG");

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);
				return strName;
	}

}
