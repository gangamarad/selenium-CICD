package pageFactor;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtendReporter {

	// ExtentReport and sparkExtentReport is responsible for making reports
	ExtentReports extent;

	
	public void config() {
		File path = new File(
				"C:\\Users\\Vijay\\Documents\\Ganga Selenium\\SeleniumFrameworkDesign\\src\\main\\java\\extentReports\\reports\\index.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Tests");
		reporter.config().setDocumentTitle("Tests");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@Test
	public void extentReporterDemo() {
		ExtentTest test = extent.createTest("extentReporterDemo");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		// driver.close();
		extent.flush();
	}
}
