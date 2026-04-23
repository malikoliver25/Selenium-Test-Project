package com.malikoliver.qa.factory;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    public Page initBrowser(String browserName) {
        System.out.println("Launching browser: " + browserName);

        // 1. Initialize Playwright
        playwright = Playwright.create();

        // 2. Setup the browser type based on the name passed
        switch (browserName.toLowerCase()) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                System.out.println("Invalid browser name. Defaulting to Chromium.");
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
        }

        // 3. Create a clean context and page
        // This tells the browser: "I trust this site, keep going even if the certificate is old"
        browserContext = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));
        page = browserContext.newPage();

        return page;
    }
}