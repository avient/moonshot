package web;

import infrastructure.Configuration;
import org.openqa.selenium.WebDriver;

public class Browser {
    private static Configuration props = Configuration.getInstance();
    private static Browser instance;
    private static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void goTo(String string) {
        driver.get(string);
    }

    private Browser() {
    }

    public static synchronized Browser getInstance() {
        if (instance == null) {
            String browser = props.getProperty("browser");
            driver = BrowserFactory.setUp(Browsers.valueOf(browser.toUpperCase()));
            instance = new Browser();
        }
        return instance;
    }

    enum Browsers {
        FIREFOX("firefox"),
        CHROME("chrome");

        public String value;

        Browsers(final String values) {
            value = values;
        }

        public String toString() {
            return value;
        }
    }

    public String getTimeoutForCondition() {
        return props.getProperty("defaultConditionTimeout");
    }
}

