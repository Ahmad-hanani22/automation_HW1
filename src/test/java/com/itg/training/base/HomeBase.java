package com.itg.training.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import com.itg.training.util.ConfigManager;
import com.itg.training.util.ReportManager;

public class HomeBase {

    protected WebDriver driver;
    protected String siteURL;
    protected String driverPath;

    @BeforeClass
    public void setup() {
        ReportManager.initReport();

        siteURL = ConfigManager.getProperty("siteURL");
        driverPath = ConfigManager.getProperty("driverPath");

        // ✅ إعدادات Chrome لتجنب Access Denied
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141 Safari/537.36");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get(siteURL);
        driver.navigate().refresh(); 
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ReportManager.flushReport();
    }
}
