package com.utility;
import java.io.*;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {
	
	WebDriver driver;
	
	ReusableMethods(WebDriver idriver)
	{
		driver=idriver;
	}
	
	
	public static String getData(String para)
	{
		Properties p=new Properties();
		String strVl = null;
		try {
			InputStream input=new FileInputStream("./Config.properties");
			p.load(input);
			strVl=p.getProperty(para);
			//System.out.println("Parameter"+strVl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strVl;
		
	}
	
	

}
