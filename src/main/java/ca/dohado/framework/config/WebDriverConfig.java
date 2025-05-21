package ca.dohado.framework.config;

import ca.dohado.framework.annotation.WebDriverBean;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Map;

@Configuration
public class WebDriverConfig {
    @Value("${wait.implicit}")
    private int implicitWait;
    @Value("${browser_headless:true}")
    private boolean isHeadless;
    @Value("${download.path}")
    private Path downloadPath;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @WebDriverBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver firefoxDriver() {
        logger.info("Instantiating FireFox Driver");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=2560", "--height=1440");
        options.addPreference("dom.webnotifications.enabled",false);
        options.addPreference("browser.download.folderList",2);
        options.addPreference("dom.push.enabled", false);
        options.addPreference("browser.download.dir", getAbsolutePath(downloadPath));
        options.addPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
        options.addPreference("browser.download.manager.showWhenStarting", false);
        if (isHeadless) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

    @WebDriverBean
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    public WebDriver edgeDriver() {
        logger.info("Instantiating Edge Driver");
        EdgeOptions options = new EdgeOptions();
        setOptionsForChromium(options);
        return new EdgeDriver(options);
    }

    @WebDriverBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public WebDriver chromeDriver() {
        logger.info("Instantiating Chrome Driver");
        ChromeOptions options = new ChromeOptions();
        setOptionsForChromium(options);
        return new ChromeDriver(options);
    }

    private void setOptionsForChromium(ChromiumOptions<?> options) {
        logger.debug("Absolute download path is {}", getAbsolutePath(downloadPath));
        options.addArguments("--window-size=2560,1440", "--no-sandbox", "--disable-dev-shm-usage");
        if (isHeadless) {
            options.addArguments("--headless=new");
        }
        options.setExperimentalOption("prefs",
                Map.of("profile.default_content_setting_values.notifications", 2, //1 Allow, 2 Block
                        "profile.default_content_settings.popups", 0,
                        "download.default_directory", getAbsolutePath(downloadPath),
                        "download.prompt_for_download", false)

        );
    }

    private String getAbsolutePath(Path path) {
        return path.toAbsolutePath().toString();
    }

}
