package ca.dohado.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest extends BaseTest {
    @Value("${main_page.header}")
    private String expectedHeader;
    @Value("${main_page.links_names}")
    private List<String> expectedLinksNames;

    @Test
    public void givenMainPageUrl_whenOpen_thenExpectedHeaderDisplayed() {
        assertThat(mainPage.getHeader()).isEqualTo(expectedHeader);
    }

    @Test
    public void givenMainPageUrl_whenOpen_thenExpectedLinksNamesToSubpagesDisplayed() {
        var linksNames = mainPage.getPagesReferencesNames();

        assertThat(linksNames).containsExactlyInAnyOrderElementsOf(expectedLinksNames);
    }
}
