package sanityTests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import basePackage.BaseClass;
import pages.Amazon;

public class InterviewAssignment extends BaseClass{
  @Test
  public void amazonTests() throws InterruptedException {
	  Thread.sleep(1000);
	  Amazon obj= new Amazon(webDriver);
	  obj.searchSomething("Selenium Books");
	  Thread.sleep(2000);
	  obj.clickNext();
	  obj.sortItems();
	  Thread.sleep(2000);
	  obj.selectFormat();
	  Thread.sleep(2000);
	  obj.selectFromListOfItems();
	  Thread.sleep(2000);
	  obj.clickAddToCart();
	  Thread.sleep(5000);
		/*
		 * if(assertCondition) { testReport.log(Pass/fail, ur message); }
		 */
  }
  @BeforeMethod
  public void beforeMethod(Method method) {
	  testReport = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName());
	  webDriver.get("https://www.amazon.com");
  }

  @AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result, Method method) throws IOException {

		System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>afterMethod");
		if (result.getStatus() == ITestResult.FAILURE) {
			File src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

			String targetDestination = "AutomationReport" + File.separator + "Images"
					+ File.separator + method.getName() + ".png";

			System.out.println("PATH=" + targetDestination);
			FileUtils.copyFile(src, new File(targetDestination));

			testReport.log(LogStatus.INFO, "Snapshot below: " + testReport.addScreenCapture(targetDestination));
			testReport.log(LogStatus.FAIL, result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP)

			testReport.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		else

			testReport.log(LogStatus.PASS, "Test passed");

		extent.endTest(testReport);
		extent.flush();
	}

}
