package ca.dohado.tests;

import ca.dohado.pages.AddRemoveElementsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class AddRemoveElementsTest extends BaseTest {

    AddRemoveElementsPage addRemoveElementsPage;

    @BeforeEach
    public void beforeEach() {
        addRemoveElementsPage = mainPage.openAddRemoveElementsPage();
    }

    @Test
    public void givenMainPage_whenNavigatingToAddRemoveElements_thenNoAddedElementsPresent() {
        assertThat(addRemoveElementsPage.getAddedElementsNames()).hasSize(0);
    }


    @ParameterizedTest(name = "Adding element {0} times")
    @ValueSource(ints = {1, 5, 10})
    public void givenAddRemoveElementsPage_whenClickAddElement_thenElementAdded(int numberOfElementsToAdd) {
        addRemoveElementsPage.clickAdd(numberOfElementsToAdd);

        assertThat(addRemoveElementsPage.getAddedElementsNames()).hasSize(numberOfElementsToAdd);
    }

}
