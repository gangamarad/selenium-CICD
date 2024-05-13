package pageFactor;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart extends AbstractElement {

	// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

	public Cart(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;

	@FindBy(xpath = "//*[@class='cartSection']/h3")
	List<WebElement> cartElements;
	
	@FindBy(css = ".totalRow button")
	WebElement checkOut;

	// view cart
	// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	public void viewCart() {
		cart.click();
	}

	// List<WebElement> cartElements =
	// driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
	public List<WebElement> getCartElements() {
		return cartElements;
	}

   //check the cart contains the product
	public boolean matchfound(String productName) {
		return getCartElements().stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
	}
	
	//checkout the cart
	public Order checkOut()
	{
		scrollWindow();
		checkOut.click();
		Order order = new Order(driver);
		return order;
	}
}
