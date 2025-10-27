package com.itg.training.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.itg.training.base.HomeBase;
import com.itg.training.pages.*;
import com.itg.training.util.*;

public class HomeValidation extends HomeBase {

    @Test(priority = 1)
    public void verifyHomePageLogoIsDisplayed() {
        ReportManager.createTest("Verify Home Page Logo", "Check if homepage logo is displayed");
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isLogoDisplayed(), "❌ Logo should be visible!");
        ReportUtil.logPass("✅ Logo is displayed successfully!");
    }

    @Test(priority = 2, dependsOnMethods = "verifyHomePageLogoIsDisplayed")
    public void clickSignInOrRegisterButton() {
        ReportManager.createTest("Click Sign In/Register", "Navigate to login page");
        HomePage home = new HomePage(driver);
        home.clickSignInOrRegister();
        ReportUtil.logPass("✅ 'Sign In/Register' clicked!");
    }

    @Test(priority = 3, dependsOnMethods = "clickSignInOrRegisterButton")
    public void verifyLoginPageWelcomeMessageIsVisible() {
        ReportManager.createTest("Verify Login Page", "Checking login form visibility");
        LoginPage login = new LoginPage(driver);
        Assert.assertTrue(login.isLoginFormVisible(), "❌ Login form not visible!");
        ReportUtil.logPass("✅ Login page visible successfully!");
    }

    @Test(priority = 4, dependsOnMethods = "verifyLoginPageWelcomeMessageIsVisible")
    public void performLoginWithValidCredentials() {
        ReportManager.createTest("Login with Valid Credentials", "Entering login credentials and verifying actual login");

        // ✅ قراءة البيانات من الإكسل
        ExcelUtil excel = new ExcelUtil(ConfigManager.getProperty("dataSheetPath"));
        String email = excel.getCellData("users", 1, 1);
        String password = excel.getCellData("users", 1, 2);
        excel.close();

        System.out.println("📂 Email from Excel: " + email);
        System.out.println("📂 Password from Excel: " + password);

        LoginPage login = new LoginPage(driver);
        login.enterCredentials(email, password);
        login.clickLoginButton();

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));

        try {
            // 🔎 ننتظر ظهور واحدة من العلامات التي تدل على نجاح تسجيل الدخول
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("myaccount"),
                    ExpectedConditions.urlContains("account"),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Welcome to your account')]")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Sign Out']"))
            ));

            String currentUrl = driver.getCurrentUrl();
            String pageSource = driver.getPageSource();

            System.out.println("🔎 Current URL after login: " + currentUrl);

            boolean loginSuccess =
                    currentUrl.toLowerCase().contains("myaccount") ||
                    currentUrl.toLowerCase().contains("account") ||
                    pageSource.contains("Welcome to your account") ||
                    pageSource.contains("Sign Out") ||
                    pageSource.contains("Logout");

            Assert.assertTrue(loginSuccess, "❌ Login did not succeed — still on login page!");
            ReportUtil.logPass("✅ Login verified successfully — user reached account page!");

        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Login verification failed: " + e.getMessage());
            Assert.fail("❌ Login verification failed: " + e.getMessage());
        }
    }



    @Test(priority = 5, dependsOnMethods = "performLoginWithValidCredentials")
    public void verifyUserMyAccountLinkIsDisplayed() {
        ReportManager.createTest("Verify My Account", "Check if 'My Account' appears");
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isMyAccountVisible(), "❌ 'My Account' not visible!");
        ReportUtil.logPass("✅ My Account link displayed successfully!");
    }

    @Test(priority = 6, dependsOnMethods = "performLoginWithValidCredentials")
    public void verifyUserAccountWelcomeTextMessage() {
        ReportManager.createTest("Verify Welcome Text", "Checking user welcome message after login");
        LoginPage login = new LoginPage(driver);
        Assert.assertTrue(login.isAccountWelcomeTextCorrect(), "❌ Welcome text incorrect!");
        ReportUtil.logPass("✅ Welcome text verified successfully!");
    }

    @Test(priority = 7, dependsOnMethods = "performLoginWithValidCredentials")
    public void verifyWelcomeTextContainsUserName() {
        ReportManager.createTest("Verify Welcome Text Contains Username", "Checking if username appears in welcome text");
        LoginPage login = new LoginPage(driver);

        ExcelUtil excel = new ExcelUtil(ConfigManager.getProperty("dataSheetPath"));
        String expectedUserName = excel.getCellData("users", 1, 0);
        excel.close();

        String actualWelcomeText = login.getWelcomeMessageText();
        String firstName = expectedUserName.split(" ")[0];

        Assert.assertTrue(actualWelcomeText.toLowerCase().contains(firstName.toLowerCase()),
                "❌ Username not found in welcome message!");

        ReportUtil.logPass("✅ Welcome text contains the correct username: " + expectedUserName);
    }
}
