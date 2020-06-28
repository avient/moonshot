package web;

import infrastructure.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

class BrowserFactory {

    public static WebDriver setUp(final Browser.Browsers type) {

        WebDriver driver = null;
        switch (type) {

            case CHROME:
                WebDriverManager.chromiumdriver().driverVersion(Configuration.getInstance().getProperty("chrome_driver_version_env")).setup();
                String tempDataDirectory = Configuration.getInstance().getProperty("tempDataDirectory");
                String pathToDownload = System.getProperty("user.dir") +
                        File.separator +
                        tempDataDirectory.replaceAll("/", Matcher.quoteReplacement(File.separator)) +
                        File.separator;
                Map<String, Object> prefsMap = new HashMap<String, Object>();
                prefsMap.put("profile.default_content_settings.popups", 0);
                prefsMap.put("download.default_directory", pathToDownload);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefsMap);
                options.addArguments("--test-type");
                options.addArguments("--disable-extensions");
                options.addArguments("--window-size=1920,1200");
                options.addArguments("--whitelisted-ips");
                options.addArguments("--disable-extensions");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-gpu");
                options.addArguments("--headless");

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
