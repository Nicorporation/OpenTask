package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Search {

    WebDriver driver;

    @FindBy(xpath = "//div[@class='product-container']")
    List<WebElement> searchResults;

    @FindBy(xpath = "//span[@id='our_price_display']")
    WebElement price;

    public Search (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFirstProduct() {
        searchResults.get(0).click();
    }

    public int getResultsCount() {
        return searchResults.size();
    }

    public String getPriceString() {
        return price.getText();
    }
}
