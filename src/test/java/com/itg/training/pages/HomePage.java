package com.itg.training.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.itg.training.selectors.HomePageSelectors;
import com.itg.training.util.ReportUtil;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogoDisplayed() {
        try {
            return driver.findElement(HomePageSelectors.LOGO).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSignInOrRegister() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        try {
            WebElement accountLink = wait.until(ExpectedConditions.elementToBeClickable(HomePageSelectors.ACCOUNT_LINK));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accountLink);
            accountLink.click();
            ReportUtil.logInfo("✅ Clicked on Sign In/Register button");
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Failed to click Sign In/Register: " + e.getMessage());
        }
    }

    public boolean waitUntilMyAccountAppears() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        try {
            return wait.until(driver1 -> {
                try {
                    WebElement link = driver1.findElement(HomePageSelectors.ACCOUNT_LINK);
                    String text = link.getText().trim().toLowerCase();
                    return text.contains("my account") || text.contains("welcome") || text.contains("sign out");
                } catch (StaleElementReferenceException | NoSuchElementException e) {
                    return false;
                }
            });
        } catch (TimeoutException e) {
            System.out.println("⚠️ My Account link did not appear in time.");
            return false;
        }
    }

    public boolean isMyAccountVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageSelectors.ACCOUNT_LINK));
            return link.getText().trim().equalsIgnoreCase("My Account");
        } catch (Exception e) {
            return false;
        }
    }
}
