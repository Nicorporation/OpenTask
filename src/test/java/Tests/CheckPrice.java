package Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckPrice {

    @Test
    public void CheckPriceTest() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String stringExpectedPrice = "$16.51";
        String stringActualPrice;
        double doubleExpectedPrice = 16.51;
        double doubleActualPrice;
        int productsReturnedCount;

        try {
            driver.manage().window().maximize();
            driver.get("http://automationpractice.com/");
            driver.findElement(By.id("search_query_top")).sendKeys("Faded Short Sleeve T-shirts");
            driver.findElement(By.xpath("//button[@name='submit_search']")).click();
            Thread.sleep(5000);
            productsReturnedCount = driver.findElements(By.xpath("//div[@class='product" +
                    "-container" +
                    "']")).size();
            Assert.assertEquals(1, (int) productsReturnedCount);
            driver.findElement(By.xpath("//div[@class='product-container']")).click();
            Thread.sleep(5000);
            stringActualPrice =
                    driver.findElement(By.xpath("//span[@id='our_price_display']")).getText();
            //Assert stings
            Assert.assertEquals(stringExpectedPrice, stringActualPrice);
            //Assert doubles
            doubleActualPrice = Double.parseDouble(stringActualPrice.replace("$",""));
            Assert.assertEquals(doubleExpectedPrice, doubleActualPrice, 0.0);

        } finally {
            driver.quit();
        }
    }
}
