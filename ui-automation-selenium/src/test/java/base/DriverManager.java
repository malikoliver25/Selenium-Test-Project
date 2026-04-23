package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

/*
 * DriverManager Class (Factory Design Pattern)
 * This class acts as a 'Factory' to manufacture WebDriver instances.
 */
public class DriverManager {

    //Manufactures a specific WebDriver based on the provided browser name.
    public static WebDriver getDriver(String browserType) {
        WebDriver driver;

        switch (browserType.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                // Fallback to Chrome as the default browser if input is invalid or empty
                driver = new ChromeDriver();
                break;
        }

        return driver;
    }
}