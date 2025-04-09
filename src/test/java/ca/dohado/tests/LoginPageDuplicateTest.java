package ca.dohado.tests;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageDuplicateTest extends BaseTest {

    @Test
    public void testLoginPageNewsDuplicate() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("News");
    }

    @Test
    public void testLoginPageVideosDuplicate() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("Videos");
    }

    @Test
    public void testLoginPageImagesDuplicate() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("Images");
    }

    @Test
    public void testLoginPageShoppingDuplicate() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("Shopping");
    }

    @Test
    public void testLoginPageWebDuplicate() {
        loginPage.sendSearchKeys("something to search");
        assertThat(loginPage.getResultTypesTabsNames()).contains("Web");
    }

    @Test
    public void testLoginPageFail1Duplicate() {
        loginPage.sendSearchKeys("something to search");
        loginPage.openResultTypesTabByIndex(5);
        assertThat(loginPage.getResultTypesTabsNames()).contains("Fail1");
    }

    @Test
    public void testLoginPageFail2Duplicate() {
        loginPage.sendSearchKeys("something to search");
        loginPage.openResultTypesTabByIndex(4);
        assertThat(loginPage.getResultTypesTabsNames()).contains("Fail2");
    }

}
