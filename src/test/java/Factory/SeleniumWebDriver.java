package Factory;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class SeleniumWebDriver {

    public RemoteWebDriver driver;
    private String urlHost;

    public SeleniumWebDriver(String appConfig, String browser) {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream(appConfig));
            this.urlHost = config.getProperty("urlHost");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.driver = getWebDriver(browser);
    }

    public RemoteWebDriver getWebDriver(String browser) {
        RemoteWebDriver driver = null;
        try {
            URL url = new URL(this.urlHost);
            if (browser.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver");
                driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
                driver.get("http://automationpractice.com/");
                driver.manage().window().maximize();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return driver;
    }
}
