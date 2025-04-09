package ca.dohado.tests;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTest extends BaseTest {

    @Test
    public void testLoginPageNews() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("News");
    }

    @Test
    public void testLoginPageVideos() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("Videos");
    }

    @Test
    public void testLoginPageImages() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("Images");
    }

    @Test
    public void testLoginPageShopping() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("Shopping");
    }

    @Test
    public void testLoginPageWeb() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("Web");
    }

    @Test
    public void testLoginPageFail1() {
        loginPage.sendSearchKeys("something to search");
        loginPage.openResultTypesTabByIndex(5);
        assertThat(loginPage.getResultTypesTabsNames()).contains("Fail1");
    }

    @Test
    public void testLoginPageFail2() {
        loginPage.sendSearchKeys("something to search");
        loginPage.openResultTypesTabByIndex(4);
        assertThat(loginPage.getResultTypesTabsNames()).contains("Fail2");
    }

}
