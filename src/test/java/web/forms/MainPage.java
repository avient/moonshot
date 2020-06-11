package web.forms;

import org.openqa.selenium.By;
import web.BaseForm;

public class MainPage extends BaseForm {
    public MainPage() {
        super(By.xpath("//*[@class='site-logo']"), "Main page");
    }
}
