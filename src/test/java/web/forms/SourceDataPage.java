package web.forms;

import org.openqa.selenium.By;
import web.BaseForm;
import web.elements.Label;

public class SourceDataPage extends BaseForm {
    public SourceDataPage() {
        super(By.xpath("//span[text()='Coronavirus Source Data']"), "Source Data page");
    }

    private final Label csvSourceLabel = new Label(By.xpath("//a[text()='.csv']"), "Csv source");

    public void navigate() {
        super.navigate("https://ourworldindata.org/coronavirus-source-data");
    }

    public String getCsvLink() {
        return csvSourceLabel.getElementAttribute("href");
    }
}
