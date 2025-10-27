package com.itg.training.lancome.util;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class ReportUtil {

    // ✅ تسجيل نجاح الخطوة
    public static void logPass(String message) {
        ReportManager.getTest().log(Status.PASS, message);
    }

    // ✅ تسجيل معلومات
    public static void logInfo(String message) {
        ReportManager.getTest().log(Status.INFO, message);
    }

    // ✅ تسجيل فشل مع صورة Screenshot
    public static void logFail(WebDriver driver, String message) {
        ReportManager.getTest().log(Status.FAIL, message);
        try {
            String screenshotPath = takeScreenshot(driver);
            ReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            ReportManager.getTest().log(Status.WARNING, "⚠️ Failed to attach screenshot: " + e.getMessage());
        }
    }

    // ✅ أخذ Screenshot وتخزينه داخل test-output/screenshots
    public static String takeScreenshot(WebDriver driver) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "test-output/screenshots/" + System.currentTimeMillis() + ".png";
        FileUtils.copyFile(src, new File(path));
        return path;
    }
}
