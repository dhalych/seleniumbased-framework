package ca.dohado.pages;

import ca.dohado.framework.utils.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginPage extends AbstractPage {
    @Value("${login.url}")
    private String loginUrl;
    @FindBy(css = "textarea[aria-owns='Alh6id']")
    private WebElement searchInput;
    @FindBy(css = ".crJ18e div[role=list] > div[role=listitem]:not([jscontroller]) .YmvwI")
    private List<WebElement> resultTypesTabsWebElements;


    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void openLoginPage() {
        driver.get(loginUrl);
    }

    public void sendSearchKeys(String username) {
        searchInput.sendKeys(username);
        searchInput.sendKeys(Keys.ENTER);
    }

    public List<String> getResultTypesTabsNames() {
        return Utils.getElementsStrings(resultTypesTabsWebElements);
    }

    public void openResultTypesTabByIndex(int index) {
        resultTypesTabsWebElements.get(index).click();
    }

}
