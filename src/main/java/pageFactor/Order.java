package pageFactor;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Order {

	//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
	
	WebDriver driver;
	public Order(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = "div .list-group-item")
	List<WebElement> contries;
	
	@FindBy(css = ".action__submit")
	WebElement submitButton;
	
	@FindBy(css = "tbody th")
	List<WebElement> orderidsElements;
	
	@FindBy(css = "button[routerlink*=myorders]")
	WebElement viewOrder;
	
	public void placeOrder()
	{
		country.sendKeys("ind");
		contries.stream().filter(p -> p.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		//driver.findElement(By.cssSelector(".action__submit")).click();
		submitButton.click();
		
	}
	
	/*
	 * driver.findElement(By.cssSelector("button[routerlink*=myorders]")).click();
	 * List<WebElement> orderidsElements =
	 * driver.findElements(By.cssSelector("tbody th"));
	 * orderidsElements.stream().map(p -> p.getText()).forEach(p ->
	 * System.out.println(p));
	 */
	public List<String> getOrderIds()
	{
		viewOrder.click();
		List<String> orderIds = orderidsElements.stream().map(p -> p.getText()).collect(Collectors.toList());
		return orderIds;
	}
	
}
