package web.elements;

import org.openqa.selenium.By;

public class TextBox extends BaseElement {
    public TextBox(final By locator, final String name) {
        super(locator, name);
    }

    public void clearSetText(String string) {
        waitForIsPresent();
        logger.info(String.format("%s :: Setting text '%s'", name, string));
        element.clear();
        element.sendKeys(string);
    }
}
