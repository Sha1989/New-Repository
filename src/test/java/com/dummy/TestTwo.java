package com.dummy;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Reports.ExtentReportsClass;





public class TestTwo extends ExtentReportsClass{
	
	@Test
	public void verifysum(){
		
		logger=extent.createTest("Verify - Favroite Items List");
		System.out.println("Start Test");
	
		int a=50,b=80,c;
		c=a+b;
		Assert.assertEquals(c, 130);
		
		//Pass the test in report
		
		}

}
