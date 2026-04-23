package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/*
 * DriverManager Class (Factory Design Pattern)
 * This class acts as a 'Factory' to manufacture WebDriver instances.
 */
public class DriverManager {

    /*
     * Manufactures a specific WebDriver based on the provided browser name.
     * Supports headless execution for CI/CD environments.
     * * @param browserType The browser to instantiate (chrome, firefox, edge)
     * @return A configured WebDriver instance
     */
    public static WebDriver getDriver(String browserType) {
        WebDriver driver;

        // Read the 'headless' property from the Maven command (-Dheadless=true)
        String headless = System.getProperty("headless");
        boolean isHeadless = "true".equalsIgnoreCase(headless);

        switch (browserType.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("-headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--disable-gpu");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                // Default to Chrome
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    // Optimized flags for stable execution in Ubuntu/GitHub Runners
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        return driver;
    }
}