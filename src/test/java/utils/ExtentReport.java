package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	public static ExtentReports getReport() {
		
		String path = "C:\\Users\\srija\\eclipse-workspace\\MiniProjectNew01\\reports\\MiniProject01Report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("MiniProject01TestResults");
//		reporter.config().setDocumentTitle("");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
//		extent.setSystemInfo("Tester", "Maniish");
	}

}
