package ca.dohado.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MainPage extends AbstractPage {

    @FindBy(css = "h1.heading")
    WebElement header;
    @FindBy(css = "#content > ul > li")
    List<WebElement> pages;
    @Value("${main_page.url}")
    private String mainPageUrl;

    public MainPage(WebDriver driver, Wait<?> wait) {
        super(driver, wait);
    }

    public void openMainPage() {
        driver.get(mainPageUrl);
    }

    public String getHeader() {
        return header.getText();
    }

}
