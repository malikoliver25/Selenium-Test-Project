package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.HomePage;
import utils.WindowManager;
import com.malikoliver.qa.utils.AllureListener;

/*
 * The BaseTests class serves as the foundation for the entire automation suite.
 * It handles the WebDriver lifecycle, environment configuration, and ensures
 * thread-safety for parallel test execution.
 */
@Listeners({AllureListener.class})
public class BaseTests {

    /*
     * ThreadLocal provides an isolated WebDriver instance for each execution thread.
     * This is critical for preventing cross-talk between tests when running in parallel,
     * as each thread maintains its own driver state and session.
     */
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected HomePage homePage;

    /*
     * Thread-safe getter for the current thread's WebDriver instance.
     * @return The WebDriver instance assigned to the calling thread.
     */
    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /*
     * Initializes the WebDriver and prepares the application state before every test.
     * Uses WebDriverManager for automated driver binary management.
     */
    @BeforeMethod
    public void setUp() {
        // Automatically manages the correct version of ChromeDriver for the host system
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Assign the driver instance to the current thread's storage
        driverThreadLocal.set(driver);

        // Navigate to the base URL and initialize the entry-point Page Object
        getDriver().get("https://the-internet.herokuapp.com/");
        homePage = new HomePage(getDriver());
    }

    /*
     * Ensures clean exit and resource management after every test.
     * Quits the driver and removes the ThreadLocal reference to prevent memory leaks.
     */
    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        // Clears the thread's storage to ensure a fresh state for future tests
        driverThreadLocal.remove();
    }

    /*
     * Utility method to provide tests with access to window management controls.
     * @return A new instance of WindowManager for the current session.
     */
    public WindowManager getWindowManager() {
        return new WindowManager(getDriver());
    }
}