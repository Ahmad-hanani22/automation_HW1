package com.itg.training.util;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class ReportUtil {

    public static void logPass(String message) {
        ReportManager.getTest().log(Status.PASS, message);
    }

    public static void logInfo(String message) {
        ReportManager.getTest().log(Status.INFO, message);
    }

    public static void logFail(WebDriver driver, String message) {
        ReportManager.getTest().log(Status.FAIL, message);
        try {
            String screenshotPath = takeScreenshot(driver);
            ReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            ReportManager.getTest().log(Status.WARNING, "❗ Failed to attach screenshot: " + e.getMessage());
        }
    }

    public static String takeScreenshot(WebDriver driver) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "test-output/screenshots/" + System.currentTimeMillis() + ".png";
        FileUtils.copyFile(src, new File(path));
        return path;
    }
}
