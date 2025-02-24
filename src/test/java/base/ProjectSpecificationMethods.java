package base;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import utils.UtilsClass;

public class ProjectSpecificationMethods extends UtilsClass {
	
//	@BeforeSuite
//	public void report() {
//		
//		String path = System.getProperty("C:\\Users\\srija\\eclipse-workspace\\MiniProjectNew01\\reports\\MiniProject01Report.html");
//		reporter = new ExtentSparkReporter(path);
//		reporter.config().setReportName("MiniProject01TestResults");
//		reporter.config().setDocumentTitle("");
//		
//		extent = new ExtentReports();
//		extent.attachReporter(reporter);
//		extent.setSystemInfo("Tester", "Maniish");
//	}
//	
//	@BeforeClass
//	public void testDetails() {
//		
//		test = extent.createTest(testName, testDescription);
//		test.assignCategory(testCategory);
//		test.assignAuthor(testAuthor);
//		
//	}
	
	@Parameters({"browser","url"})
	@BeforeMethod
	public void launchBrowser(String browser, String url) {
		
		browserLaunch(browser, url);
	}
	
	
	@DataProvider
	public String[][] excelRead() throws IOException {
		
		String[][] data = readExcel(sheetName);
		return data;
	}
	
	@AfterMethod
	public void close() {
		
		closeBrowser();
	}

}
