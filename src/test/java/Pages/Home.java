package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {

    WebDriver driver;

    @FindBy(id = "search_query_top")
    WebElement searchField;

    @FindBy(xpath = "//button[@name='submit_search']")
    WebElement searchButton;

    public Home (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchQuery(String searchQuery) {
        searchField.sendKeys(searchQuery);
    }

    public void clickSearch() {
        searchButton.click();
    }

}
