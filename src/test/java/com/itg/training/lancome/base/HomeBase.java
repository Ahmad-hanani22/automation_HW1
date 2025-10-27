package com.itg.training.lancome.base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.itg.training.lancome.config.ConfigManager;
import com.itg.training.lancome.validation.HomeValidation;
import com.itg.training.lancome.pages.HomePage;
import com.itg.training.lancome.util.*;

public class HomeBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected HomeValidation homeValidation;
    protected HomePage homePage;

    @BeforeClass
    public void setUp() {
        try {
            ReportManager.initReport();

            System.setProperty("webdriver.chrome.driver", ConfigManager.getDriverPath());
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
            driver.get(ConfigManager.getSiteURL());

            wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            homeValidation = new HomeValidation(driver, wait);
            homePage = new HomePage(driver, wait);

            // ✅ استبدل ReportUtil.logInfo بـ System.out.println مؤقتًا هنا
            System.out.println("✅ Browser launched and navigated to site successfully!");

        } catch (Exception e) {
            System.out.println("❌ Setup failed: " + e.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("✅ Browser closed successfully.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error during teardown: " + e.getMessage());
        } finally {
            ReportManager.flushReport();
            System.out.println("📄 Report generated at: test-output/AutomationReport.html");
        }
    }
}
