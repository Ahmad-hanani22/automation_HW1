package com.itg.training.lancome;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement closeButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("button[aria-label='Close'] , button[title='Close'] , div[class*='close'] , svg[aria-label='Close']")
            ));

            if (closeButton.isDisplayed()) {
                js.executeScript("arguments[0].click();", closeButton);
                System.out.println("🍪 Cookies popup closed successfully (X clicked)!");
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.out.println("ℹ️ No cookie popup found — continuing test normally.");
        }

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
        Thread.sleep(5000);
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
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageSelectors.EMAIL_FIELD));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPageSelectors.PASSWORD_FIELD));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(LoginPageSelectors.SIGNIN_BUTTON));

            js.executeScript("arguments[0].scrollIntoView(true);", emailField);
            Thread.sleep(500);

            js.executeScript("arguments[0].value='ahmadj7hanani0@gmail.com';", emailField);
            js.executeScript("arguments[0].value='0569630981Aa$';", passwordField);

            Thread.sleep(1000);

            try {
                loginButton.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", loginButton);
            }

            ReportUtil.logPass("✅ Login button clicked successfully!");
            Thread.sleep(4000);

        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Failed during login step: " + e.getMessage());
            Assert.fail("Login test failed!");
        }
    }
    @Test(priority = 5, dependsOnMethods = "loginWithValidCredentials")
    public void verifyUserLoggedIn() {
        ReportManager.createTest("Verify User Logged In", "Check if 'My Account' link appears");
        try {
            WebElement accountLink = driver.findElement(HomePageSelectors.ACCOUNT_LINK);
            String linkText = accountLink.getText().trim();

            // 🔍 تحقق من النص بالضبط
            Assert.assertEquals(linkText, "My Account", "❌ Link text not changed!");

            ReportUtil.logPass("✅ 'Sign In/Register' changed to 'My Account' successfully!");
        } catch (AssertionError ae) {
            ReportUtil.logFail(driver, "❌ Assertion failed: " + ae.getMessage());
            Assert.fail("❌ Assertion failed: " + ae.getMessage());
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Unexpected error during verification: " + e.getMessage());
            Assert.fail("❌ Unexpected error during verification: " + e.getMessage());
        }
    }


    @Test(priority = 6)
    public void verifyAccountWelcomeText() {
        ReportManager.createTest("Verify Account Welcome Text", "Checking user welcome message");
        try {
            WebElement welcomeText = driver.findElement(LoginPageSelectors.ACCOUNT_WELCOME_TEXT);
            String actualText = welcomeText.getText().trim();

            // 🔍 تحقق من النص بالضبط
            Assert.assertEquals(actualText, "Welcome to your account at Ballard Designs.",
                    "❌ Welcome text is incorrect!");

            ReportUtil.logPass("✅ Verified account welcome text successfully!");
        } catch (AssertionError ae) {
            ReportUtil.logFail(driver, "❌ Text verification failed: " + ae.getMessage());
            Assert.fail("❌ Text verification failed: " + ae.getMessage());
        } catch (Exception e) {
            ReportUtil.logFail(driver, "❌ Unexpected error during welcome text check: " + e.getMessage());
            Assert.fail("❌ Unexpected error during welcome text check: " + e.getMessage());
        }
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
        ReportManager.flushReport();
        System.out.println("✅ Browser closed and report generated successfully!");
    }
}
