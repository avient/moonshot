package web;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest extends BaseEntity {

    @BeforeTest
    public void beforeTest() {
        WebDriver driver = browser.getDriver();
        driver.manage().window().maximize();
//        driver.get(browser.getBaseUrl());
    }

    @AfterTest
    public void afterTest() {
        browser.getDriver().close();
    }
}
