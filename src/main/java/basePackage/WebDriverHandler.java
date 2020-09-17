package basePackage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverHandler {

	static File directory = new File(".");
	WebDriver driver;
	String os;
	String driverPath;

	DesiredCapabilities capabilities;
	DesiredCapabilities capability;

	public WebDriver setDriver(String browser) throws Exception {

		switch (browser) {
		case "chrome":

			setDriverPathInSystemProperties(browser);
			driver = new ChromeDriver();
			return maximizeDriverWindowAndReturn();

		case "firefox":

			setDriverPathInSystemProperties(browser);
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
			return maximizeDriverWindowAndReturn();

		case "ie":

			setDriverPathInSystemProperties(browser);
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			driver = new InternetExplorerDriver(capabilities);
			return maximizeDriverWindowAndReturn();

		default:
			driver = new FirefoxDriver();
			return maximizeDriverWindowAndReturn();
		}
	}

	public void setDriverPathInSystemProperties(String driverType) throws Exception {
		String archType, osType;
		archType = osDataModel();
		osType = osType();

		if (driverType.equals("chrome")) {

			System.out.println("Selected browser -- " + driverType);
			System.out.println("Os type = = = = " + osType);
			if (osType.contains("window") && archType.equals("32")) {
				driverPath = directory.getCanonicalPath() + File.separator + "resources" + File.separator + "windows"
						+ File.separator + "32Bit" + File.separator + "chromedriver.exe";
			} else if (osType.contains("linux") && archType.equals("64")) {
				driverPath = System.getProperty("user.dir") + File.separator + "lib" + File.separator + "chromeDriver"
						+ "chromedriver_linux64" + File.separator + "chromedriver";
			} else if (osType.contains("mac") && archType.equals("64")) {
				driverPath = directory.getCanonicalPath() + File.separator + "resources" + File.separator + "mac"
						+ File.separator + "chromedriver";
			}
			System.out.println(driverPath);
			System.setProperty("webdriver.chrome.driver", driverPath);

		} else if (driverType.equals("ie")) {

			System.out.println("Selected browser -- " + driverType);
			if (osType.contains("window") && archType.equals("32")) {
				driverPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator + "windows"
						+ File.separator + "msedgedriver.exe";
			} else if (osType.contains("linux") && archType.equals("64")) {
				throw new Exception();
			} else if (osType.contains("mac") && archType.equals("64")) {
				throw new Exception();
			}
			System.out.println(driverPath);
			System.setProperty("webdriver.ie.driver", driverPath);

		} else if (driverType.equals("firefox")) {

			System.out.println("Selected browser -- " + driverType);
			System.out.println("Os type = = = = " + osType);
			if (osType.contains("window") && archType.equals("32")) {
				driverPath = directory.getCanonicalPath() + File.separator + "resources" + File.separator + "windows"
						+ File.separator + "geckodriver.exe";
			} else if (osType.contains("linux") && archType.equals("64")) {
				driverPath = System.getProperty("user.dir") + File.separator + "lib" + File.separator + "chromeDriver"
						+ "chromedriver_linux64" + File.separator + "chromedriver";
			} else if (osType.contains("mac") && archType.equals("64")) {
				driverPath = directory.getCanonicalPath() + File.separator + "resources" + File.separator + "mac"
						+ File.separator + "geckodriver";
			}
			System.out.println(driverPath);
			System.setProperty("webdriver.gecko.driver", driverPath);

		}
	}

	public String osDataModel() {
		String realArch = System.getProperty("sun.arch.data.model");
		System.out.println("OS arch -- " + realArch);
		return realArch;
	}

	public String osType() {
		String osName = System.getProperty("os.name").toLowerCase();
		System.out.println("OS type -- " + osName);
		return osName;
	}

	private WebDriver maximizeDriverWindowAndReturn() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
