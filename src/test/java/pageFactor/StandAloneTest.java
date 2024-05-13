package pageFactor;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	WebDriver driver;

	@Test(dataProvider = "getData", groups = { "mainTest" })
	public void runTest(String userName, String Pass, String productName) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("gangasm@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Viji@2024");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		// stream
		List<WebElement> elements = driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement element = elements.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		element.findElement(By.cssSelector("div[class='card-body'] button:last-of-type")).click();

		Thread.sleep(3000);

		// for loop
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")) {
				elements.get(i).findElement(By.cssSelector("div[class='card-body'] button:last-of-type")).click();
			}
		}

		// validation to click on cart - should wait until success message come and
		// animation should go so
		// defining explicit wait

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// validating the cart with added items

		List<WebElement> cartElements = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		long count = cartElements.stream().filter(product -> product.getText().equalsIgnoreCase("ZARA COAT 3")
				|| product.getText().equalsIgnoreCase("IPHONE 13 PRO")).count();
		System.out.println(count);
		Assert.assertEquals(2, count);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> contries = driver.findElements(By.cssSelector("div .list-group-item"));
		contries.stream().filter(p -> p.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("button[routerlink*=myorders]")).click();
		List<WebElement> orderidsElements = driver.findElements(By.cssSelector("tbody th"));
		orderidsElements.stream().map(p -> p.getText()).forEach(p -> System.out.println(p));
	}

	@DataProvider()
	@Test
	public Object[][] getData() {
		return new Object[][] { { "gangasm@gmail.com", "Viji@2024", "ZARA COAT 3" },
				{ "gangasm@gmail.com", "Viji@2024", "IPHONE 13 PRO" } };
	}

	public String takeScreenshot(String testName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("C:\\Users\\Vijay\\Documents\\screenshot\\screenshot.png"));
		return "C:\\\\Users\\\\Vijay\\\\Documents\\\\screenshot\\\\screenshot.png";
	}
}
