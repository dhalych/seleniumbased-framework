package ca.dohado.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static ca.dohado.framework.utils.Utils.*;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MainPage extends AbstractPage {

    @FindBy(css = "h1.heading")
    WebElement header;
    @FindBy(css = "#content > ul > li")
    List<WebElement> pages;
    @FindBy(css = "")
    @Value("${main_page.url}")
    private String mainPageUrl;
    @Value("${main_page.links_names}")
    private List<String> pageNames;
    @Autowired
    private AddRemoveElementsPage addRemoveElementsPage;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        initElements();
    }

    public void openMainPage() {
        driver.get(mainPageUrl);
    }

    public String getHeader() {
        return header.getText();
    }

    public List<String> getPagesReferencesNames() {
        return getElementsStrings(pages);
    }

    public AddRemoveElementsPage openAddRemoveElementsPage() {
        openPage(1, addRemoveElementsPage);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("dsfd")));

        return addRemoveElementsPage;
    }

    private void openPage(int index, AbstractPage page) {
        String name = pageNames.get(index);
        getElementByName(pages, name).findElement(By.tagName("a")).click();
        page.initElements();
    }

}
