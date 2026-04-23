package base;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    public static WebDriver getDriver(String browserType) {
        WebDriver driver = null;
        String headless = System.getProperty("headless");
        boolean isHeadless = "true".equalsIgnoreCase(headless);

        switch (browserType.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) firefoxOptions.addArguments("-headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    // Critical flags for GitHub Actions stability
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox"); // Required for Linux CI
                    chromeOptions.addArguments("--disable-dev-shm-usage"); // Prevents memory crashes
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                // Retry logic for session creation to handle transient CI failures
                int maxRetries = 3;
                for (int i = 0; i < maxRetries; i++) {
                    try {
                        driver = new ChromeDriver(chromeOptions);
                        break;
                    } catch (SessionNotCreatedException e) {
                        if (i == maxRetries - 1) throw e;
                        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
                    }
                }
                break;
        }
        return driver;
    }
}