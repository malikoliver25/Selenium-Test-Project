package hover;

import base.BaseTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/*
 * Test suite for verifying hover interactions and dynamic content reveal.
 * Implements Data-Driven Testing (DDT) to validate multiple UI elements
 * using a single test logic block.
 */
public class HoverTests extends BaseTests {

    /*
     * Provides a dataset for hover validation.
     * Maps the element index to its expected caption text.
     */
    @DataProvider(name = "hoverData")
    public Object[][] getHoverData() {
        // Repeated data points to ensure framework stability across multiple iterations
        return new Object[][]{
                {1, "name: user1"},
                {2, "name: user2"},
                {3, "name: user3"},
                {1, "name: user1"},
                {2, "name: user2"},
                {3, "name: user3"},
                {1, "name: user1"},
                {2, "name: user2"},
                {3, "name: user3"},
                {1, "name: user1"}
        };
    }

     // Validates that hovering over a profile figure correctly displays a caption
    @Test(dataProvider = "hoverData", description = "Verifies captions appear on mouse hover")
    public void testHoverUser(int index, String expectedFullName) {
        var hoverPage = homePage.clickHovers();
        var caption = hoverPage.hoverOverFigure(index);

        // Verification Points (Assertions)
        assertTrue(caption.isCaptionDisplayed(),
                "FAILED: Caption not visible for figure at index " + index);

        assertEquals(caption.getTitle(), expectedFullName,
                "FAILED: Username header mismatch for index " + index);

        assertTrue(caption.getLink().endsWith("/users/" + index),
                "FAILED: Profile link URL structure is incorrect for index " + index);
    }
}