package base;
import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openxmlformats.schemas.drawingml.x2006.chart.STSecondPieSize;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

public class BaseTest {
	public static WebDriver driver;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@BeforeTest
	public void startReport() {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator+"reports"+File.separator+"AutomationReport.html");
		//sparkReporter = new ExtentSparkReporter("AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		//add environments details
		extent.setSystemInfo("Machine", "Dell");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("User", "Anchal");

		//Configuration to change look and feel
		sparkReporter.config().setDocumentTitle("Automation Test Report");
		sparkReporter.config().setReportName("Anchal choudhary");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a'('zzz')'");
	}

	@BeforeMethod
	@Parameters("browser")
	public void launchbrowser(String browser, Method testMethod){
		logger = extent.createTest(testMethod.getName());
		setDriver(browser);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}


	@AfterMethod
	public void listner(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ "Test Case is Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable()+ "Test Case is Failed", ExtentColor.RED));

		}
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+ "Test Case is Passed", ExtentColor.GREEN));
		}
		if(result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ "Test Case is Skipped", ExtentColor.YELLOW));
		}
		driver.quit();
	}

	@AfterTest
	public void flushOutReport() {
		extent.flush();
	}

	public void setDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	}
	
	//Switch tab
	String originalWindow;
	public void switchWindowHandle()
	{
		// Step 3: Get all window handles
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();

		originalWindow = iterator.next();  // First window
		String newTab = iterator.next();  // Second window (new tab)
		// Step 4: Switch to the new tab
		driver.switchTo().window(newTab);
	}
	
	public void switchToParentWindow() {
		 // Step 6: Switch back to the original tab
        driver.switchTo().window(originalWindow);
	}
}
