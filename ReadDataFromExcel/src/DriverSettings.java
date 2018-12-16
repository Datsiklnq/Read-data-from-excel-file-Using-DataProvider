

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSettings {
	private static final String APP_URL = "https://www.facebook.com";
	public static InternetExplorerDriver driver;

	public static void driver() throws InterruptedException {

		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();

		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		caps.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
		caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, false);

		// Create driver object for IE browser
		// Invoke exe. file
		System.setProperty("webdriver.ie.driver", "C:\\Users\\TBAG\\Desktop\\IEDriverServer.exe");
		driver = new InternetExplorerDriver(caps);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(APP_URL);
		Thread.sleep(3000);
		System.out.print("Loading ");
		timer();
		System.out.println();

	}

	public static void timer() throws InterruptedException {

		for (int i = 5; i >= 0; i--) {
			Thread.sleep(1000);
			System.out.print(".");

		}
	}
}