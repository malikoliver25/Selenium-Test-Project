package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/*
 * Page Object for the Hovers page.
 * Demonstrates advanced mouse interactions and the handling of
 * dynamic elements that only appear in the DOM upon user action.
 */
public class HoverPage {

    private WebDriver driver;
    private By figureBox = By.className("figure");
    private By boxCaption = By.className("figcaption");

    public HoverPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Triggers a mouse hover action over a specific profile figure.
     * Uses the Selenium Actions class to simulate realistic user movement.
     * * @param index The 1-based index of the figure to hover over.
     * @return A FigureCaption object representing the revealed text and links.
     */
    public FigureCaption hoverOverFigure(int index) {
        WebElement figure = driver.findElements(figureBox).get(index - 1);

        Actions actions = new Actions(driver);
        actions.moveToElement(figure).perform();

        return new FigureCaption(figure.findElement(boxCaption));
    }

    /*
     * Inner class representing the caption that appears upon hovering.
     * This encapsulation follows the Page Object Model by treating
     * a specific component of the page as its own entity.
     */
    public class FigureCaption {

        private WebElement caption;
        private By header = By.tagName("h5");
        private By link = By.tagName("a");

        public FigureCaption(WebElement caption) {
            this.caption = caption;
        }

        /*
         * Verifies if the caption is visible to the user.
         * Implements an Explicit Wait to account for CSS transition delays
         * and hardware latency, ensuring test stability.
         * * @return true if visible, false if timeout occurs or not displayed.
         */
        public boolean isCaptionDisplayed() {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                wait.until(ExpectedConditions.visibilityOf(caption));
                return caption.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }

        /**
         * Extracts the header text (username) from the caption.
         */
        public String getTitle() {
            return caption.findElement(header).getText();
        }

         // Extracts the 'href' attribute from the profile link.
        public String getLink() {
            return caption.findElement(link).getAttribute("href");
        }

         // Extracts the visible text of the profile link.
        public String getLinkText() {
            return caption.findElement(link).getText();
        }
    }
}