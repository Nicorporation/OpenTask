package Tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyProduct {

    @Test
    public void BuyProductTest() throws InterruptedException {
        String email = "vase5555@gmail.com";
        String password = "Test@123";
        String quantity = "2";
        String size = "M";
        String mediateTotalPrice;
        String finalTotalPrice;
        int productsReturnedCount;
        System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

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

            //Quantity 2
            WebElement quantityField = driver.findElement(By.id("quantity_wanted"));
            quantityField.clear();
            quantityField.sendKeys(quantity);

            //Size M
            driver.findElement(By.id("group_1")).click();
            Select sizes = new Select(driver.findElement(By.name("group_1")));
            sizes.selectByVisibleText(size);

            //Color Blue
            driver.findElement(By.xpath("//a[@title='Blue']")).click();
            Thread.sleep(5000);

            //Add to cart
            driver.findElement(By.xpath("//button[@name='Submit']")).click();
            WebDriverWait wait = new WebDriverWait(driver,3);
            wait.until(ExpectedConditions.elementToBeClickable (By.xpath("//span[contains(text(), 'Proceed to checkout')]")));

            //Get Total price
            mediateTotalPrice = driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']")).getText();
            System.out.println("MEDIATE PRICE IS: " + mediateTotalPrice);

            //Proceed
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[contains(text(), 'Proceed to checkout')]")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//p[@class='cart_navigation clearfix']/a[@title='Proceed" +
                    " to checkout']")).click();

            //Sign in with user
            WebElement emailField = driver.findElement(By.id("email"));
            emailField.clear();
            emailField.sendKeys(email);

            WebElement passwordField = driver.findElement(By.id("passwd"));
            passwordField.clear();
            passwordField.sendKeys(password);

            driver.findElement(By.id("SubmitLogin")).click();
            Thread.sleep(3000);

            //Proceed
            driver.findElement(By.xpath("//button[@name='processAddress']")).click();
            Thread.sleep(3000);

            //Agree TS
            driver.findElement(By.id("cgv")).click();

            //Proceed
            driver.findElement(By.xpath("//button[@name='processCarrier']")).click();


            //Pay by bank wire
            driver.findElement(By.xpath("//a[@class='bankwire']")).click();

            //Confirm
            driver.findElement(By.xpath("//button[@type='submit']/span[contains(text(), 'I confirm my order')]")).click();

            //Assert completed
            Assert.assertTrue(driver.findElement(By.xpath("//strong[contains(text(), 'Your order on My Store is complete.')]")).isDisplayed());

            //Assert amount
            finalTotalPrice =  driver.findElement(By.xpath("//span[@class='price']")).getText();
            Assert.assertEquals(mediateTotalPrice, finalTotalPrice);

        } finally {
            driver.quit();
        }
    }
}
