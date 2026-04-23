package com.malikoliver.qa.utils;

import base.BaseTests;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

/*
 * Custom listener that integrates TestNG execution with Allure reporting.
 * This class monitors test state and automatically triggers data collection
 * (like screenshots) upon specific lifecycle events like failures.
 */
public class AllureListener implements ITestListener {

    /*
     * Automatically invoked by TestNG whenever a test method fails.
     * Extracts the driver instance from the current test class to capture
     * the exact state of the UI at the moment of failure.
     * @param result The result object containing metadata about the failed test.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        // Retrieve the class instance where the failure occurred
        Object testClass = result.getInstance();

        // Cast the instance to BaseTests to access the thread-safe WebDriver
        WebDriver driver = ((BaseTests) testClass).getDriver();

        if (driver != null) {
            saveScreenshot(driver);
        }
    }

    /*
     * Captures a screenshot and attaches it directly to the Allure report.
     * The @Attachment annotation informs Allure to embed this byte array
     * as a PNG image in the final report dashboard.
     * @param driver The WebDriver instance from the failing thread.
     * @return The screenshot captured as a byte array.
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}