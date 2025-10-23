package com.itg.training.lancome;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.itg.training.selectors.*;
import com.itg.training.util.*;

public class FirstSeleniumTest {

    WebDriver driver;

    @Parameters({"driverPath", "siteURL"})
    @BeforeClass
    public void setup(String driverPath, String siteURL) {
        ReportManager.initReport(); 
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(siteURL);
        ReportManager.createTest("Setup", "Browser launched and site opened successfully");
        ReportUtil.logInfo("✅ Browser launched and site opened successfully!");
    }

    @Test(priority = 1)
    public void verifyLogo() {
        ReportManager.createTest("Verify Logo", "Checking if site logo is visible");
        WebElement logo = driver.findElement(HomePageSelectors.LOGO);
        Assert.assertTrue(logo.isDisplayed(), "❌ Logo should be visible!");
        ReportUtil.logPass("✅ Logo is displayed successfully!");
    }

    @Test(priority = 2)
    public void clickSignInRegister() throws InterruptedException {
        ReportManager.createTest("Click Sign In/Register", "Navigate to login page");
        WebElement accountLink = driver.findElement(HomePageSelectors.ACCOUNT_LINK);
        accountLink.click();
        Thread.sleep(4000);
        ReportUtil.logPass("✅ 'Sign In/Register' clicked!");
    }

    @Test(priority = 3, dependsOnMethods = "clickSignInRegister")
    public void verifyWelcomeMessage() {
        ReportManager.createTest("Verify Welcome Message", "Checking welcome text");
        WebElement welcomeMsg = driver.findElement(LoginPageSelectors.WELCOME_MSG);
        Assert.assertTrue(welcomeMsg.isDisplayed(), "❌ 'Welcome back!' message not displayed!");
        ReportUtil.logPass("✅ 'Welcome back!' message is displayed!");
    }

    @Test(priority = 4)
    public void loginWithValidCredentials() throws InterruptedException {
        ReportManager.createTest("Login with Valid Credentials", "Entering login credentials and submitting form");
        try {
            WebElement emailField = driver.findElement(LoginPageSelectors.EMAIL_FIELD);
            WebElement passwordField = driver.findElement(LoginPageSelectors.PASSWORD_FIELD);
            WebElement loginButton = driver.findElement(LoginPageSelectors.SIGNIN_BUTTON);

            emailField.clear();
            emailField.sendKeys("ahmadj7hanani0@gmail.com");
            passwordField.clear();
            passwordField.sendKeys("0569630981Aa$");
            Thread.sleep(3000);
            loginButton.click();
            Thread.sleep(5000);
            ReportUtil.logPass("✅ Login button clicked successfully!");
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Failed during login step: " + e.getMessage());
            Assert.fail("Login test failed!");
        }
    }

    @Test(priority = 5, dependsOnMethods = "loginWithValidCredentials")
    public void verifyUserLoggedIn() {
        ReportManager.createTest("Verify User Logged In", "Check if 'My Account' link appears");
        WebElement accountLink = driver.findElement(HomePageSelectors.ACCOUNT_LINK);
        String linkText = accountLink.getText().trim();
        Assert.assertEquals(linkText, "My Account", "❌ Link text not changed!");
        ReportUtil.logPass("✅ 'Sign In/Register' changed to 'My Account' successfully!");
    }

    @Test(priority = 6, dependsOnMethods = "verifyUserLoggedIn")
    public void verifyAccountWelcomeText() {
        ReportManager.createTest("Verify Account Welcome Text", "Checking user welcome message");
        WebElement welcomeText = driver.findElement(LoginPageSelectors.ACCOUNT_WELCOME_TEXT);
        String actualText = welcomeText.getText().trim();
        Assert.assertEquals(actualText, "Welcome to your account at Ballard Designs.",
                "❌ Welcome text is incorrect!");
        ReportUtil.logPass("✅ Verified account welcome text successfully!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        ReportManager.flushReport(); //   
        System.out.println("✅ Browser closed and report generated successfully!");
    }
}
