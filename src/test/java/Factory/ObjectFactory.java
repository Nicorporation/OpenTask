package Factory;

import Pages.Home;
import Pages.Product;
import Pages.Search;

import java.io.FileInputStream;
import java.util.Properties;

public class ObjectFactory {

    public SeleniumWebDriver webDriver;
    public Home home;
    public Search search;
    public Product product;
    public Properties properties;

    public ObjectFactory(String driverConfig, String browser) {
        try {
            webDriver = new SeleniumWebDriver(driverConfig, browser);
            home = new Home(webDriver.driver);
            search = new Search(webDriver.driver);
            product = new Product(webDriver.driver);
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/base.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
