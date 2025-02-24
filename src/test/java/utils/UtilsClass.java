package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class UtilsClass {
	
	public static WebDriver driver;
	public String sheetName;
//	public ExtentSparkReporter reporter;
//	public ExtentReports extent;
//	public ExtentTest test;
	public String testName, testDescription, testAuthor, testCategory;
	
	public void browserLaunch(String browser, String url) {
		
		if(browser.contentEquals("Chrome")) {
			driver = new ChromeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void closeBrowser() {
		
		driver.close();
	}
	
	public static String[][] readExcel (String sheetname) throws IOException {
		
		XSSFWorkbook book = new XSSFWorkbook("C:\\Users\\srija\\eclipse-workspace\\MiniProjectNew01\\src\\test\\resources\\TestData.xlsx");
		
		XSSFSheet sheet = book.getSheet(sheetname);
		
		// get the no.of rows
		int rowCount = sheet.getLastRowNum(); 
		System.out.println("Row count: "+rowCount);
		
		// get the no. of columns
		int columnCount = sheet.getRow(0).getLastCellNum(); 
		System.out.println("Column count: "+columnCount);
		
		// create 2D array 
		String[][] data = new String[rowCount] [columnCount];
		for(int i=1; i<= rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
			
			// get into the columns 
			for(int j=0; j<columnCount; j++) {
				XSSFCell cell = row.getCell(j);
				// to store in array 
				data[i-1][j] = cell.getStringCellValue();
			}
		}
		
		book.close();
		return data;
		
	}
	
	public static String screenshot(String name) throws IOException {
		
		String path = "C:\\Users\\srija\\eclipse-workspace\\MiniProjectNew01\\snap\\"+name+".png";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
		return path;
	}

}
