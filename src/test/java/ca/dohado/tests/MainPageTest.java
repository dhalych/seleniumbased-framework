package ca.dohado.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest extends BaseTest {
    @Value("${main_page.header}")
    private String expectedHeader;

    @Test
    public void givenMainPageUrl_whenOpen_thenExpectedHeaderDisplayed() {
        mainPage.openMainPage();

        assertThat(mainPage.getHeader()).isEqualTo(expectedHeader);
    }
}
