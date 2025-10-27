package com.itg.training.lancome.tests;

import org.testng.annotations.Test;
import com.itg.training.lancome.base.HomeBase;
import com.itg.training.lancome.pages.LoginPage;
import com.itg.training.lancome.config.ConfigManager;
import com.itg.training.lancome.util.*;

public class HomeTest extends HomeBase {

    @Test(priority = 1)
    public void verifyHomePageLogo() {
        ReportManager.createTest("Test #1 - Verify Home Page Logo", "Verify that the home page logo is visible");
        homeValidation.verifyHomePageLogoIsDisplayed();
    }

    @Test(priority = 2)
    public void clickSignInRegister() {
        ReportManager.createTest("Test #2 - Click Sign In/Register", "Click the sign in/register link on homepage");
        homePage.clickSignInOrRegisterLink();
    }

    @Test(priority = 3)
    public void verifyLoginWelcomeMessage() {
        ReportManager.createTest("Test #3 - Verify Login Welcome Message", "Check that 'Welcome back!' message appears");
        homeValidation.verifyLoginPageWelcomeMessageDisplayed();
    }

    @Test(priority = 4)
    public void enterValidCredentials() {
        ReportManager.createTest("Test #4 - Enter Valid Credentials", "Enter valid email and password");
        LoginPage login = new LoginPage(driver, wait);
        login.enterCredentials(ConfigManager.getValidEmail(), ConfigManager.getValidPassword());
    }

    @Test(priority = 5)
    public void clickLoginButton() {
        ReportManager.createTest("Test #5 - Click Login Button", "Click the login button to sign in");
        LoginPage login = new LoginPage(driver, wait);
        login.clickLoginButton();
    }

    @Test(priority = 6)
    public void verifyUserLoggedIn() {
        ReportManager.createTest("Test #6 - Verify User Logged In", "Check that user is logged in and 'My Account' appears");
        homeValidation.verifyUserIsLoggedIn();
    }

    @Test(priority = 7)
    public void verifyAccountWelcomeText() {
        ReportManager.createTest("Test #7 - Verify Account Welcome Text", "Validate the welcome message inside the account page");
        homeValidation.verifyAccountWelcomeText();
    }
}
