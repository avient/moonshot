package web;

import infrastructure.Logger;
import org.openqa.selenium.By;
import web.elements.Label;

public class BaseForm extends BaseEntity {
    private final By titleLocator;
    private final String title;

    protected BaseForm(final By locator, final String formTitle) {
        titleLocator = locator;
        title = formTitle;
    }

    public boolean isOpened() {
        return new Label(titleLocator, title).isPresent();
    }

    public void navigate(String url) {
        Logger.getInstance().info(String.format("Opening URL: %s", url));
        browser.goTo(url);
    }
}
