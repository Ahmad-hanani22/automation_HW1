package com.itg.training.lancome.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.itg.training.selectors.HomePageSelectors;

public class HomePage {
	@SuppressWarnings("unused")
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean verifyHomePageLogo() {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageSelectors.LOGO));
        return logo.isDisplayed();
    }

    public void clickSignInOrRegisterLink() {
        WebElement accountLink = wait.until(ExpectedConditions.elementToBeClickable(HomePageSelectors.ACCOUNT_LINK));
        accountLink.click();
    }
}
