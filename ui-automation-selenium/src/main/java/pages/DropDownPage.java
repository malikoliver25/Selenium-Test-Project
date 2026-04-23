package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

// Represent the Dropdown page and provide specialized methods for menu interaction
public class DropDownPage {

    private WebDriver driver;

    // Locate the <select> element by its ID
    private By dropdown = By.id("dropdown");

    public DropDownPage(WebDriver driver) {
        this.driver = driver;
    }

    // Use the Select wrapper to click an option based on the text the user sees
    public void selectDropdownOption(String option) {
        findDropdownElement().selectByVisibleText(option);
    }

    /*
     * Objective: Retrieve all currently selected options.
     * We return a List<String> instead of List<WebElement> to keep Selenium
     * dependencies out of our test classes (Encapsulation).
     */
    public List<String> getSelectedOptions() {
       List<WebElement> selectedElements = findDropdownElement().getAllSelectedOptions();

        // Use Java Streams to transform the list of WebElements into a list of plain Strings
       return selectedElements.stream()
               .map(e->e.getText()) // Extract text from each element
               .collect(Collectors.toList()); // Gather them back into a list
    }

    /*
     * A private helper method to "wrap" the standard WebElement in a Select object.
     * This avoids repeating the 'new Select()' logic in every method that uses the dropdown.
     */
    private Select findDropdownElement() {
        return new Select(driver.findElement(dropdown));
    }
}
