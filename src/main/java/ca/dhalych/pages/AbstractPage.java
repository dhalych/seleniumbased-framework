package ca.dohado.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    @Value("${download.path}")
    private String downloadPath;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
}
