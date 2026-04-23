package dropdown;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

// Test the dropdown functionality by inheriting the setup from BaseTests
public class DropdownTests extends BaseTests {

    @Test
    public void testSelectOption() {
        // Use the shared homePage to navigate to the Dropdown page
        var dropdownPage = homePage.clickDropDown();

        String option = "Option 1";
        dropdownPage.selectDropdownOption("Option 1");

        // Perform the action of selecting an option from the menu
        var selectedOptions = dropdownPage.getSelectedOptions();

        // Verify Requirement 1 — Ensure ONLY one item is selected
        // We check the size of the list returned by our Page Object
        assertEquals(selectedOptions.size(), 1, "Incorrect number of selected options");

        /*
         *  Verify Requirement 2 — Ensure the CORRECT item is selected.
         *  We use assertTrue with .contains() to see if "Option 1" is in our list.
         */
        assertTrue(selectedOptions.contains("Option 1"), "Option 1 should be selected");

    }

}
