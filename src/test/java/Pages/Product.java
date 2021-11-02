package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Product {

    WebDriver driver;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "passwd")
    WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    WebElement loginButton;

    @FindBy(id = "quantity_wanted")
    WebElement quantityField;

    @FindBy(id = "group_1")
    WebElement sizeDropdown;

    @FindBy(name = "group_1")
    WebElement sizeOptions;

    @FindBy(xpath = "//button[@name='Submit']")
    WebElement addToCartButton;

    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    WebElement totalPrice;

    @FindBy(xpath = "//span[@class='price']")
    WebElement completedTotalPrice;

    @FindBy(id = "cgv")
    WebElement termsCheckbox;

    @FindBy(xpath = "//a[@title='Pay by bank wire']")
    WebElement bankWirePayment;

    @FindBy(xpath = "//span[contains(text(), 'Proceed to checkout')]")
    WebElement proceedInCartButton;

    @FindBy(xpath = "//p[@class='cart_navigation clearfix']/a[@title='Proceed to checkout']")
    WebElement proceedInSummaryButton;

    @FindBy(xpath = "//button[@name='processAddress']")
    WebElement proceedInAddressButton;

    @FindBy(xpath = "//button[@name='processCarrier']")
    WebElement proceedInShippingButton;

    @FindBy(xpath = "//button[@type='submit']/span[contains(text(), 'I confirm my order')]")
    WebElement confirmOrderButton;

    @FindBy(xpath = "//h1[contains(text(), 'Order confirmation')]")
    WebElement orderConfirmationHeader;

    public Product (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void setProductQuantity(String quantity) {
        quantityField.clear();
        quantityField.sendKeys(quantity);
    }

    public void selectSize(String size) {
        sizeDropdown.click();
        Select sizes = new Select(sizeOptions);
        sizes.selectByVisibleText(size);
    }

    public void selectColor(String color) {
        driver.findElement(By.xpath("//a[@title='" + color + "']")).click();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public String getCompletedTotalPrice() {
        return completedTotalPrice.getText();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void agreeToTerms() {
        termsCheckbox.click();
    }

    public void clickBankWirePayment() {
        bankWirePayment.click();
    }

    public void clickProceed() {
        proceedInCartButton.click();
    }

    public void clickProceedInSummary() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  proceedInSummaryButton);
        proceedInSummaryButton.click();
    }

    public void clickProceedInAddress() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  proceedInAddressButton);
        proceedInAddressButton.click();
    }

    public void clickProceedInShipping() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  proceedInShippingButton);
        proceedInShippingButton.click();
    }

    public void clickConfirmOrder() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  confirmOrderButton);
        confirmOrderButton.click();
    }

    public boolean isOrderConfirmed() {
        return orderConfirmationHeader.isDisplayed();
    }

}
