package ca.dohado.tests;

import ca.dohado.pages.AddRemoveElementsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddRemoveElementsTest extends BaseTest {

    AddRemoveElementsPage addRemoveElementsPage;

    @BeforeEach
    public void beforeEach() {
        addRemoveElementsPage = mainPage.openAddRemoveElementsPage();
    }

    @Test
    public void givenAddRemoveElementsPage_whenClickAddElement_thenElementAdded() {
        final int numberOfElementsToAdd = 1;

        addRemoveElementsPage.clickAdd(numberOfElementsToAdd);

        assertThat(addRemoveElementsPage.getAddedElementsNames()).hasSize(numberOfElementsToAdd);
    }

}
