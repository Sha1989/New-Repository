package Reports;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.*;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportsClass{
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	/*protected AndroidDriver<AndroidElement> driver;*/
	@BeforeSuite
	public void startReport(){
		String filename = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String extentReportFile = System.getProperty("user.dir")+"/results/SubwayExtentReport"+"_"+"TestHM"+".html";
		htmlReporter = new ExtentHtmlReporter(extentReportFile);
		//htmlReporter.loadXMLConfig("C:\\Shashank\\Project\\30_5\\SubwayAutomationMobileApplication\\extent-config.xml");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Host Name", "Subway Digital");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Shashank Bansal");
		
		htmlReporter.config().setDocumentTitle("SUBWAY - Automation Status");
		htmlReporter.config().setReportName("Automation Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		
	}
	
	public static void pass(String message) {
		DOMConfigurator.configure("log4j.xml");
        logger.log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }
	
	public static void fail(String message) {
		DOMConfigurator.configure("log4j.xml");
        logger.log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
    }
	
	public static void skip(String message) {
		DOMConfigurator.configure("log4j.xml");
		 logger.log(Status.SKIP, MarkupHelper.createLabel(message, ExtentColor.ORANGE));
	}
	
	
		
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE){
			//logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			//logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		}else if(result.getStatus() == ITestResult.SKIP){
			//logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));	
		}
	}
	
	
	@AfterSuite
	public void endReport(){
		
		extent.flush();
		//extent.close();
    }
}