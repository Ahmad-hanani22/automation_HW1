package com.itg.training.lancome;

import org.openqa.selenium.WebDriver;
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
        System.out.println(" Driver Path: " + driverPath);
        System.out.println(" Site URL: " + siteURL);

        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(siteURL);

        System.out.println(" Browser launched and site opened successfully!");
    }

    
    
    @Test(priority = 1)
    public void verifyLogo() {
        boolean isLogoDisplayed = driver.findElement(HomePageSelectors.LOGO).isDisplayed();
        Assert.assertTrue(isLogoDisplayed, " Logo should be displayed on the homepage");
        System.out.println(" Logo is displayed successfully!");
    }

    @Test(priority = 2, dependsOnMethods = "verifyLogo")
    public void clickAccountIconAndVerifyWelcome() throws InterruptedException {
        driver.findElement(HomePageSelectors.ACCOUNT_LINK).click();
        System.out.println(" Clicked on Account successfully");

        Thread.sleep(3000); 

        boolean isWelcomeDisplayed =
                driver.findElement(LoginPageSelectors.WELCOME_MSG).isDisplayed();

        
        Assert.assertTrue(isWelcomeDisplayed, " 'Welcome back!' message was not displayed!");
        System.out.println("'Welcome back!' message is displayed — Sign In page loaded successfully!");
    }
   
    
    
    
    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("✅ Browser closed successfully!");
    }
}
