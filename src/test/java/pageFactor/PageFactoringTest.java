package pageFactor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PageFactoringTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void ecommerceTest(HashMap<String, String> map) throws InterruptedException, IOException {

		LoginPage loginPage = launchApplication();

		// GetProductList and add to cart
		ProductCatalogue productscatlog = loginPage.login(map.get("email"), map.get("password"));
		Cart cart = productscatlog.addProductToCart(map.get("productName"));
		Thread.sleep(3000);

		cart.viewCart();
		Assert.assertTrue(cart.matchfound(map.get("productName")));
		Order order = cart.checkOut();
		order.placeOrder();

		Thread.sleep(3000);
		List<String> orderIds = order.getOrderIds();
		orderIds.stream().forEach(p -> System.out.println(p));

	}

	@DataProvider
	@Test
	public Object[][] getData() throws IOException {
		// returning data with haspmap
		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "gangasm@gmail.com"); map.put("password", "Viji@2024");
		 * map.put("productName", "IPHONE 13 PRO");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "gangasm@gmail.com"); map1.put("password", "Viji@2024");
		 * map1.put("productName", "IPHONE 13 PRO"); return new Object[][] { { map }, {
		 * map1 } };
		 */
		// returning data using json file
		List<HashMap<String, String>> map = jsonData();
		return new Object[][] { { map.get(0) }, { map.get(1) } };

	}

}
