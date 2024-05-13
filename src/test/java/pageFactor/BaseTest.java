package pageFactor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;

	@Test
	public WebDriver initialDriver() throws IOException {

		Properties prop = new Properties();
//when reading the global properties from maven
		FileInputStream stream = new FileInputStream(
				"C:\\Users\\Vijay\\Documents\\Ganga Selenium\\SeleniumFrameworkDesign\\src\\main\\java\\pageFactor\\resources\\pagefactory.properties");
		prop.load(stream);
		String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		System.out.println(browsername);

		if (browsername.contains("chrome")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if (browsername.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().setSize(new Dimension(1440, 900));

		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// return driver;
		} else {
			// return edge driver
		}
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver = initialDriver();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}

	public List<HashMap<String, String>> jsonData() throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(
				"C:\\Users\\Vijay\\Documents\\Ganga Selenium\\SeleniumFrameworkDesign\\src\\main\\java\\pageFactor\\resources\\Pagefactor.json"));

		// converting string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String takeScreenshot(String testName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("C:\\Users\\Vijay\\Documents\\screenshot\\screenshot.png"));
		return "C:\\\\Users\\\\Vijay\\\\Documents\\\\screenshot\\\\screenshot.png";
	}

	// take a screenshot on failed test and attached to report
	@Test(retryAnalyzer = RetryListener.class)
	public void testFail() {
		System.out.println("Failed test");
		Assert.assertTrue(true);
	}
}
