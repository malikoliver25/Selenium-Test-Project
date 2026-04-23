package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.HomePage;
import utils.WindowManager;

/*
 * BaseTests Class
 * Acts as the centralized hub for test initialization.
 * Demonstrates the Page Object Model and Factory Design Pattern.
 */
public class BaseTests {

    protected WebDriver driver;
    protected HomePage homePage;
    private WindowManager windowManager;

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        // Initialize the browser via our Factory
        driver = DriverManager.getDriver(browser);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void goHome() {
        // Ensure every test starts at the base URL
        driver.get("https://the-internet.herokuapp.com/");

        // Initialize the Page Objects and Utilities that tests depend on
        homePage = new HomePage(driver);
        windowManager = new WindowManager(driver);
    }

     // Getter for WindowManager utility used in Navigation tests.
    public WindowManager getWindowManager() {
        return windowManager;
    }

    // Getter for the driver used by the AllureListener.
    public WebDriver getDriver() {
        return this.driver;
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}