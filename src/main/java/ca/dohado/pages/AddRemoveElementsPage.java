package ca.dohado.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static ca.dohado.framework.utils.Utils.getElementsStrings;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddRemoveElementsPage extends AbstractPage {

    @FindBy(css = "#content h3")
    private WebElement header;
    @FindBy(css = "button[onclick='addElement()']")
    private WebElement addButton;
    @FindBy(css = "#elements button")
    List<WebElement> addedElements;

    public AddRemoveElementsPage(WebDriver driver, Wait<?> wait) {
        super(driver, wait);
    }

    public String getHeader() {
        return header.getText();
    }

    public void clickAdd(int numbersOfElementsToAdd) {
        for (int i = 0; i < numbersOfElementsToAdd; i++) addButton.click();
    }

    public List<String> getAddedElementsNames() {
        return getElementsStrings(addedElements);
    }

}
