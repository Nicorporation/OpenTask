package Tests;

import Pages.Home;
import Pages.Search;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CheckPrice {

    Home home;
    Search search;

    @Test
    public void CheckPriceTest() {

        //BeforeTest - testng maybe
        System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();

        home = new Home(driver);
        search = new Search(driver);

        String stringExpectedPrice = "$16.51";
        double doubleExpectedPrice = 16.51;
        double doubleActualPrice;

        try {
            home.enterSearchQuery("Faded Short Sleeve T-shirts");
            home.clickSearch();
            Assert.assertEquals(search.getResultsCount(), 1);
            search.clickFirstProduct();

            //Assert as strings
            Assert.assertEquals(stringExpectedPrice, search.getPriceString());
            //Assert as doubles
            doubleActualPrice = Double.parseDouble(search.getPriceString().replace("$",""));
            Assert.assertEquals(doubleExpectedPrice, doubleActualPrice, 0.0);

        } finally {
            driver.quit();
        }
    }
}
