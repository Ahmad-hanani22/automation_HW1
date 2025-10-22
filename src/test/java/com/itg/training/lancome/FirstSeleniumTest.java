package com.itg.training.lancome;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.itg.training.selectors.HomePageSelectors;
import com.itg.training.selectors.LoginPageSelectors;

public class FirstSeleniumTest {

    WebDriver driver;

    @Parameters({"driverPath", "siteURL"})
    @BeforeClass
    public void setup(String driverPath, String siteURL) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(siteURL);
        System.out.println("✅ Browser launched and site opened successfully!");
    }

    // 1️⃣ Verify logo is displayed
    @Test(priority = 1)
    public void verifyLogo() {
        WebElement logo = driver.findElement(HomePageSelectors.LOGO);
        Assert.assertTrue(logo.isDisplayed(), "❌ Logo should be visible!");
        System.out.println("✅ Logo is displayed successfully!");
    }

    
    @Test(priority = 2)
    public void clickSignInRegister() throws InterruptedException {
        WebElement accountLink = driver.findElement(HomePageSelectors.ACCOUNT_LINK);
        accountLink.click();
        System.out.println("✅ 'Sign In/Register' clicked!");
        Thread.sleep(4000);
    }

    
    @Test(priority = 3, dependsOnMethods = "clickSignInRegister")
    public void verifyWelcomeMessage() throws InterruptedException {
        WebElement welcomeMsg = driver.findElement(LoginPageSelectors.WELCOME_MSG);
        Assert.assertTrue(welcomeMsg.isDisplayed(), "❌ 'Welcome back!' message not displayed!");
        System.out.println("✅ 'Welcome back!' message is displayed!");
        Thread.sleep(3000);
    }

    
    @Test(priority = 4)
    public void loginWithValidCredentials() throws InterruptedException {
        WebElement emailField = driver.findElement(LoginPageSelectors.EMAIL_FIELD);
        WebElement passwordField = driver.findElement(LoginPageSelectors.PASSWORD_FIELD);
        WebElement loginButton = driver.findElement(LoginPageSelectors.SIGNIN_BUTTON);

        emailField.clear();
        emailField.sendKeys("ahmadj7hanani0@gmail.com");
        passwordField.clear();
        passwordField.sendKeys("0569630981Aa$");

        Thread.sleep(3500); 

        loginButton.click();
        System.out.println("✅ Login button clicked!");
        Thread.sleep(5000);
    }

    
    @Test(priority = 5, dependsOnMethods = "loginWithValidCredentials")
    public void verifyUserLoggedIn() throws InterruptedException {
        WebElement accountLink = driver.findElement(HomePageSelectors.ACCOUNT_LINK);
        String linkText = accountLink.getText().trim();
        Assert.assertEquals(linkText, "My Account", "❌ 'Sign In/Register' did not change to 'My Account'!");
        System.out.println("✅ 'Sign In/Register' link successfully changed to 'My Account'!");
        Thread.sleep(2000);
    }

    
    @Test(priority = 6, dependsOnMethods = "verifyUserLoggedIn")
    public void verifyAccountWelcomeText() throws InterruptedException {
        WebElement welcomeText = driver.findElement(LoginPageSelectors.ACCOUNT_WELCOME_TEXT);
        String actualText = welcomeText.getText().trim();
        Assert.assertEquals(actualText, "Welcome to your account at Ballard Designs.",
                "❌ Welcome text is incorrect!");
        System.out.println("✅ Verified account welcome text: " + actualText);
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("✅ Browser closed successfully!");
    }
}
