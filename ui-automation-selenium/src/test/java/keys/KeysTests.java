package keys;

import base.BaseTests;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/*
 * Test suite for verifying keyboard interaction and event handling.
 * Validates that the application correctly identifies and responds to
 * non-alphanumeric keyboard inputs (Special Keys).
 */
public class KeysTests extends BaseTests {

    /*
     * DataProvider supplying a suite of Selenium 'Keys' enums and their
     * corresponding expected string representations in the UI.
     */
    @DataProvider(name = "specialKeys")
    public Object[][] getKeys() {
        return new Object[][]{
                {Keys.BACK_SPACE, "BACK_SPACE"},
                {Keys.TAB, "TAB"},
                {Keys.ESCAPE, "ESCAPE"},
                {Keys.SPACE, "SPACE"},
                {Keys.ALT, "ALT"},
                {Keys.CONTROL, "CONTROL"},
                {Keys.SHIFT, "SHIFT"},
                {Keys.F1, "F1"},
                {Keys.NUMPAD5, "NUMPAD5"},
                {Keys.ARROW_UP, "UP"},
                {Keys.ARROW_DOWN, "DOWN"},
                {Keys.ARROW_LEFT, "LEFT"},
                {Keys.ARROW_RIGHT, "RIGHT"}
        };
    }

    /*
     * Simulates keyboard input and validates the UI's reaction message.
     * Demonstrates the ability to send composite keys (String + Keys Enum).
     */
    @Test(dataProvider = "specialKeys", description = "Verifies that the page correctly registers special key presses")
    public void testSpecialKeys(Keys keyToSend, String expectedText) {
        // Navigation via the centralized HomePage entry point
        var keyPage = homePage.clickKeyPresses();

        // Interaction: Combining standard characters with Enum-based keyboard triggers
        keyPage.enterText("A" + keyToSend);

        // State Verification: Asserting the DOM was updated dynamically by the key event
        String actualResult = keyPage.getResult();

        assertEquals(actualResult, "You entered: " + expectedText,
                "Assertion Failed: The UI result message does not match the key event sent.");
    }
}