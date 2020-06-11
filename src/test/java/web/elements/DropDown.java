package web.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DropDown extends BaseElement {

    public DropDown(final By locator, final String name) {
        super(locator, name);
    }

    public void selectByText(String text) {
        new Select(browser.getDriver().findElement(locator)).selectByVisibleText(text);
    }
}
