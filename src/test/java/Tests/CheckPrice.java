package Tests;

import Factory.ObjectFactory;
import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckPrice {

    @Test
    @Parameters({"browser"})
    public void CheckPriceTest(String browser) {

        ObjectFactory objectFactory = new ObjectFactory("src/main/resources/base.properties", browser);

        String stringExpectedPrice = "$16.51";
        double doubleExpectedPrice = 16.51;
        double doubleActualPrice;

        try {
            objectFactory.home.enterSearchQuery("Faded Short Sleeve T-shirts");
            objectFactory.home.clickSearch();
            Assert.assertEquals(objectFactory.search.getResultsCount(), 1);
            objectFactory.search.clickFirstProduct();

            //Assert as strings
            Assert.assertEquals(stringExpectedPrice, objectFactory.search.getPriceString());
            //Assert as doubles
            doubleActualPrice = Double.parseDouble(objectFactory.search.getPriceString().replace("$",""));
            Assert.assertEquals(doubleExpectedPrice, doubleActualPrice, 0.0);

        } finally {
            objectFactory.webDriver.driver.quit();
        }
    }
}
