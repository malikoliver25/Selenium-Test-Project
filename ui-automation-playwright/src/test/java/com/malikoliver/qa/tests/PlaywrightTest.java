package com.malikoliver.qa.tests;

import com.malikoliver.qa.factory.PlaywrightFactory;
import com.microsoft.playwright.Page; // 1. The Import
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaywrightTest {
    PlaywrightFactory pf;
    Page page; // 2. The Class Variable

    @BeforeMethod
    public void setup() {
        pf = new PlaywrightFactory();
        page = pf.initBrowser("chromium");
    }

    @Test
    public void testSuccessfulLogin() {
        page.navigate("https://the-internet.herokuapp.com/login");
        page.fill("#username", "tomsmith");
        page.fill("#password", "SuperSecretPassword!");
        page.click("button[type='submit']");

        String message = page.locator("#flash").innerText();
        System.out.println("Result: " + message);
        Assert.assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test
    public void testInvalidLogin() {
        page.navigate("https://the-internet.herokuapp.com/login");

        // 1. Enter INCORRECT credentials
        page.fill("#username", "wrongUser");
        page.fill("#password", "wrongPassword");
        page.click("button[type='submit']");

        // 2. Capture the error message
        String message = page.locator("#flash").innerText();
        System.out.println("Error Message: " + message);

        // 3. Assert that it contains the "invalid" warning
        Assert.assertTrue(message.contains("Your username is invalid!"));
    }

    @AfterMethod
    public void tearDown() {
        if (page != null) {
            page.context().browser().close();
        }
    }
}