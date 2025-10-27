package com.itg.training.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import com.itg.training.selectors.LoginPageSelectors;
import com.itg.training.util.ConfigManager;
import com.itg.training.util.ReportUtil;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginFormVisible() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("signin"),
                    ExpectedConditions.visibilityOfElementLocated(LoginPageSelectors.EMAIL_FIELD)
            ));
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageSelectors.EMAIL_FIELD));
            WebElement passwordField = driver.findElement(LoginPageSelectors.PASSWORD_FIELD);
            return (emailField.isDisplayed() && passwordField.isDisplayed());
        } catch (Exception e) {
            System.out.println("⚠️ Login form not visible: " + e.getMessage());
            return false;
        }
    }

    public void enterCredentials(String email, String password) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageSelectors.EMAIL_FIELD));
            WebElement passwordField = driver.findElement(LoginPageSelectors.PASSWORD_FIELD);

            js.executeScript("arguments[0].scrollIntoView(true);", emailField);
            Thread.sleep(500);

            emailField.clear();
            emailField.sendKeys(email);
            passwordField.clear();
            passwordField.sendKeys(password);

            ReportUtil.logInfo("✅ Entered login credentials successfully");
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Failed to enter credentials: " + e.getMessage());
        }
    }

    public void clickLoginButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(LoginPageSelectors.SIGNIN_BUTTON));
            driver.get(ConfigManager.getProperty("siteURL"));
            Thread.sleep(2000);
            driver.navigate().refresh();

            js.executeScript("arguments[0].scrollIntoView(true);", loginButton);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", loginButton);

            ReportUtil.logInfo("✅ Login button clicked successfully");
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Failed to click login button: " + e.getMessage());
        }
    }

    public boolean isAccountWelcomeTextCorrect() {
        try {
            WebElement welcome = driver.findElement(LoginPageSelectors.ACCOUNT_WELCOME_TEXT);
            return welcome.getText().trim().equals("Welcome to your account at Ballard Designs.");
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeMessageText() {
        try {
            return driver.findElement(LoginPageSelectors.ACCOUNT_WELCOME_TEXT).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
