package basePackage;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import listeners.Listener;

public class BaseClass {

	protected WebDriver webDriver;
	public static String test;

	public static String directorypath;
	public static String screenshotpath;
	public static ExtentReports extent;
	public static ExtentTest testReport;
	public static File directory = new File(".");

	public static Properties CONFIGOBJ = null;

	@BeforeSuite
	public void beforeSuite() throws IOException {
		System.out.println("Inside @BeforeSuite");
		String reportPath = directory.getCanonicalPath() + File.separator + "AutomationReport" + File.separator
				+ "AutomationReport.html";

		extent = new ExtentReports(reportPath, true);

		try {

			String extentConfigPath = directory.getCanonicalPath() + File.separator + "extent-config.xml";

			System.out.println(">>>>>>>>>>>" + extentConfigPath);
			extent.loadConfig(new File(extentConfigPath));
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Parameters({ "b_type" })
	@BeforeClass
	public void beforeClass(String b_type) throws Exception {
		System.out.println("Inside @BeforeClass");
		System.out.println("Class b_type is ----- " + b_type);
		WebDriverHandler handler = new WebDriverHandler(); //Object created
		webDriver = handler.setDriver(b_type); //Method called to set driver
	}

	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("Inside @BeforeTest");
		

	}

	@AfterTest
	public void afterTest() {
		// driver.close();
		webDriver.quit();
	}

	@AfterSuite
	public void afterSuite() {

	}

	public static void main(String[] args) throws IOException {
		BaseClass obj = new BaseClass();
		obj.beforeSuite();
	}

}
