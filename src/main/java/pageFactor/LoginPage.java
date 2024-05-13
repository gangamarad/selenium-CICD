package pageFactor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userMail;

	@FindBy(id = "userPassword")
	WebElement userPass;

	@FindBy(id = "login")
	WebElement loginPage;

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public ProductCatalogue login(String email,String pass)

	{
		userMail.sendKeys(email);
		userPass.sendKeys(pass);
		loginPage.click();
		ProductCatalogue productscatlog = new ProductCatalogue(driver);
		return productscatlog;
	}

	
}
