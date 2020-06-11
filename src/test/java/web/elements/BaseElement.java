package web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.BaseEntity;
import web.Browser;

import java.util.List;

public abstract class BaseElement extends BaseEntity {
    WebElement element;
    String name;
    By locator;
    private static final int TIMEOUT_WAIT_5 = 5;

    BaseElement(final By loc, final String nameOf) {
        locator = loc;
        name = nameOf;
    }

    public void click() {
        waitForIsPresent();
        logger.info(String.format("%s :: Clicking", name));
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='1px solid red'", element);
        }
        element.click();
    }

    public boolean isPresent() {
        try {
            waitForIsPresent(TIMEOUT_WAIT_5);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForIsPresent() {
        waitForIsPresent(Integer.parseInt(browser.getTimeoutForCondition()));
    }

    public void waitForIsPresent(int timeout) {
        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), timeout);
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            List<WebElement> list = driver.findElements(locator);
            element = driver.findElement(locator);
            return list.size() != 0;
        });
    }

    public String getElementAttribute(String attribute) {
        waitForIsPresent();
        logger.info(String.format("%s :: Getting '%s' attribute", name, attribute));
        String attributeValue = element.getAttribute(attribute);
        logger.info(String.format("Attribute '%s' is %s", attribute, attributeValue));
        return attributeValue;
    }
}