package com.itg.training.lancome.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.itg.training.selectors.LoginPageSelectors;
import com.itg.training.selectors.HomePageSelectors;

public class LoginPage {
	@SuppressWarnings("unused")
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = (JavascriptExecutor) driver;
    }

    public boolean isWelcomeMessageDisplayed() {
        WebElement welcomeMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageSelectors.WELCOME_MSG));
        return welcomeMsg.isDisplayed();
    }

    public void enterCredentials(String email, String password) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageSelectors.EMAIL_FIELD));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageSelectors.PASSWORD_FIELD));
        js.executeScript("arguments[0].value='" + email + "';", emailField);
        js.executeScript("arguments[0].value='" + password + "';", passwordField);
    }

    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(LoginPageSelectors.SIGNIN_BUTTON));
        loginBtn.click();
    }

    public boolean isUserLoggedIn() {
        WebElement accountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageSelectors.ACCOUNT_LINK));
        return accountLink.getText().trim().equals("My Account");
    }

    public boolean verifyAccountWelcomeMessageText() {
        WebElement welcomeText = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageSelectors.ACCOUNT_WELCOME_TEXT));
        return welcomeText.getText().trim().equals("Welcome to your account at Ballard Designs.");
    }
}
