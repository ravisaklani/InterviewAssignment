package sanityTests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import basePackage.BaseClass;

@Listeners(listeners.Listener.class)			


public class login extends BaseClass{

	@Test(testName="ravi")
	public void firstTest() {
		Assert.assertEquals(2, 2);
		webDriver.get("http://www.javatpoint.com/");
		// driver.navigate().to("http://www.javatpoint.com/");
	}

}
