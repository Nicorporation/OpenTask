package Tests;

import Pages.Home;
import Pages.Product;
import Pages.Search;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BuyProduct {

    Home home;
    Search search;
    Product product;

    @Test
    public void BuyProductTest() throws InterruptedException{

        System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();

        String email = "vase5555@gmail.com";
        String password = "Test@123";
        String quantity = "2";
        String size = "M";
        String color = "Blue";
        String mediateTotalPrice;
        String finalTotalPrice;

        home = new Home(driver);
        search = new Search(driver);
        product = new Product(driver);

        try {
            home.enterSearchQuery("Faded Short Sleeve T-shirts");
            home.clickSearch();
            Assert.assertEquals(search.getResultsCount(), 1);
            search.clickFirstProduct();

            //Quantity 2
            product.setProductQuantity(quantity);

            //Size M
            product.selectSize(size);

            //Color Blue
            product.selectColor(color);

            //Add to cart
            product.addToCart();

            //Get Total price
            Thread.sleep(2000);
            mediateTotalPrice = product.getTotalPrice();
            System.out.println("MEDIATE PRICE: " + mediateTotalPrice);

            //Proceed
            product.clickProceed();
            product.clickProceedInSummary();

            //Sign in with user
            product.enterEmail(email);
            product.enterPassword(password);
            product.clickLoginButton();

            //Proceed
            product.clickProceedInAddress();

            //Agree TS
            product.agreeToTerms();

            //Proceed
            product.clickProceedInShipping();

            //Pay by bank wire
            product.clickBankWirePayment();

            //Confirm
            product.clickConfirmOrder();

            //Assert completion
            Assert.assertTrue(product.isOrderConfirmed());

            //Assert amount
            finalTotalPrice = product.getCompletedTotalPrice();
            Assert.assertEquals(mediateTotalPrice, finalTotalPrice);

        } finally {
            driver.quit();
        }
    }
}
