package Factory;

import Pages.Home;
import Pages.Product;
import Pages.Search;

public class ObjectFactory {

    public SeleniumWebDriver webDriver;
    public Home home;
    public Search search;
    public Product product;

    public ObjectFactory(String driverConfig, String browser) {
        try {
            webDriver = new SeleniumWebDriver(driverConfig, browser);
            home = new Home(webDriver.driver);
            search = new Search(webDriver.driver);
            product = new Product(webDriver.driver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
