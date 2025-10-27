package com.itg.training.lancome.validation;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.itg.training.lancome.pages.*;
import com.itg.training.lancome.util.*;

public class HomeValidation {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomeValidation(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ✅ Test 1: Verify homepage logo
    public void verifyHomePageLogoIsDisplayed() {
        try {
            HomePage home = new HomePage(driver, wait);
            Assert.assertTrue(home.verifyHomePageLogo(), "❌ Logo is not visible!");
            ReportUtil.logPass("✅ Home page logo verified successfully!");
        } catch (AssertionError e) {
            ReportUtil.logFail(driver, e.getMessage());
            throw e;
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Unexpected error while verifying logo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // ✅ Test 3: Verify login page welcome message
    public void verifyLoginPageWelcomeMessageDisplayed() {
        try {
            LoginPage login = new LoginPage(driver, wait);
            Assert.assertTrue(login.isWelcomeMessageDisplayed(), "❌ Welcome message not visible!");
            ReportUtil.logPass("✅ Login page welcome message displayed.");
        } catch (AssertionError e) {
            ReportUtil.logFail(driver, e.getMessage());
            throw e;
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Unexpected error while verifying welcome message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // ✅ Test 6: Verify user logged in
    public void verifyUserIsLoggedIn() {
        try {
            LoginPage login = new LoginPage(driver, wait);
            Assert.assertTrue(login.isUserLoggedIn(), "❌ User not logged in successfully!");
            ReportUtil.logPass("✅ User logged in successfully!");
        } catch (AssertionError e) {
            ReportUtil.logFail(driver, e.getMessage());
            throw e;
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Unexpected error while verifying user login: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // ✅ Test 7: Verify account welcome text
    public void verifyAccountWelcomeText() {
        try {
            LoginPage login = new LoginPage(driver, wait);
            Assert.assertTrue(login.verifyAccountWelcomeMessageText(), "❌ Account welcome text mismatch!");
            ReportUtil.logPass("✅ Account welcome message text verified!");
        } catch (AssertionError e) {
            ReportUtil.logFail(driver, e.getMessage());
            throw e;
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Unexpected error while verifying account welcome text: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
