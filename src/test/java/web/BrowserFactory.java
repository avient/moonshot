package web;

import infrastructure.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

class BrowserFactory {

    public static WebDriver setUp(final Browser.Browsers type) {

        WebDriver driver = null;
        switch (type) {

            case CHROME:
                WebDriverManager.chromiumdriver().driverVersion(Configuration.getInstance().getProperty("chrome_driver_version_env")).setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--whitelisted-ips", "--disable-extensions", "--no-sandbox");
                driver = new ChromeDriver(options);
                break;

            case FIREFOX:
                // TODO

            default:
                break;
        }
        return driver;
    }
}
