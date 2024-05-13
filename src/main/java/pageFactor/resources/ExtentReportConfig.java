package pageFactor.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportConfig {

	public static ExtentReports exportReport() {

		File path = new File(
				"C:\\Users\\Vijay\\Documents\\Ganga Selenium\\SeleniumFrameworkDesign\\src\\main\\java\\extentReports\\reports");
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation tests");
		reporter.config().setDocumentTitle("Tests");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ganga Marad");
		return extent;
	}
}
