package Tests;

import Factory.ObjectFactory;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BuyProduct {

    @Test
    @Parameters({"browser"})
    public void BuyProductTest(String browser) throws InterruptedException{

        ObjectFactory objectFactory = new ObjectFactory("src/main/resources/base.properties", browser);

        String email = objectFactory.properties.getProperty("email");
        String password = objectFactory.properties.getProperty("password");
        String quantity = "2";
        String size = "M";
        String color = "Blue";
        String mediateTotalPrice;
        String finalTotalPrice;

        try {
            objectFactory.home.enterSearchQuery("Faded Short Sleeve T-shirts");
            objectFactory.home.clickSearch();
            Assert.assertEquals(objectFactory.search.getResultsCount(), 1);
            objectFactory.search.clickFirstProduct();

            //Quantity 2
            objectFactory.product.setProductQuantity(quantity);

            //Size M
            objectFactory.product.selectSize(size);

            //Color Blue
            objectFactory.product.selectColor(color);

            //Add to cart
            objectFactory.product.addToCart();

            //Get Total price
            Thread.sleep(3000);
            mediateTotalPrice = objectFactory.product.getTotalPrice();
            System.out.println("MEDIATE PRICE: " + mediateTotalPrice);

            //Proceed
            objectFactory.product.clickProceed();
            objectFactory.product.clickProceedInSummary();

            //Sign in with user
            objectFactory.product.enterEmail(email);
            objectFactory.product.enterPassword(password);
            objectFactory.product.clickLoginButton();

            //Proceed
            objectFactory.product.clickProceedInAddress();

            //Agree TS
            objectFactory.product.agreeToTerms();

            //Proceed
            objectFactory.product.clickProceedInShipping();

            //Pay by bank wire
            objectFactory.product.clickBankWirePayment();

            //Confirm
            objectFactory.product.clickConfirmOrder();

            //Assert completion
            Assert.assertTrue(objectFactory.product.isOrderConfirmed());

            //Assert amount
            finalTotalPrice = objectFactory.product.getCompletedTotalPrice();
            Assert.assertEquals(mediateTotalPrice, finalTotalPrice);

        } finally {
            objectFactory.webDriver.driver.quit();
        }
    }
}
