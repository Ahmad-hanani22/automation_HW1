package com.itg.training.selectors;

import org.openqa.selenium.By;

public final class LoginPageSelectors {
    private LoginPageSelectors() {}
    
    public static final By WELCOME_MSG =
        By.xpath("//p[contains(normalize-space(),'Welcome back!')]");

   
    public static final By EMAIL_FIELD = By.id("email");
    
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By SIGNIN_BUTTON = By.cssSelector("button[data-analytics-name='login']");

    public static final By ACCOUNT_WELCOME_TEXT =
        By.xpath("//p[contains(normalize-space(),'Welcome to your account at Ballard Designs')]");
}
