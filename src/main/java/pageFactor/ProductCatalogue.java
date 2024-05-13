package pageFactor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends AbstractElement {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".col-lg-4")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By element = By.cssSelector(".col-lg-4");
	By productLocator = By.cssSelector("b");
	By cartLocator = By.cssSelector("div[class='card-body'] button:last-of-type");
	By toastmessage = By.id("toast-container");

	// get the list of products
	// List<WebElement> elements = driver.findElements(By.cssSelector(".col-lg-4"));
	public List<WebElement> getProductsList() {
		waitforElementToAppear(element);
		return products;
	}
	
	//get product by its productName
	/*
	 * WebElement element = products.stream() .filter(product ->
	 * product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).
	 * findFirst() .orElse(null);
	 */
	public WebElement getProduct(String ProductName)
	{
		WebElement product = getProductsList().stream().filter(p->p.findElement(By.cssSelector("b")).getText()
				.equalsIgnoreCase(ProductName))
				.findFirst().orElse(null);
		return product;
	}

	//add product to cart
	//element.findElement(By.cssSelector("div[class='card-body'] button:last-of-type")).click();
	public Cart addProductToCart(String ProductName)
	{
		WebElement Product = getProduct(ProductName);
		Product.findElement(cartLocator).click();
		waitforElementToAppear(toastmessage);
		waitforElementToDisAppear(spinner);
		Cart cart = new Cart(driver);
		return cart;
	}
}
