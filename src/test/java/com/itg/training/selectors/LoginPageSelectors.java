package com.itg.training.selectors;

import org.openqa.selenium.By;

public final class LoginPageSelectors {
    private LoginPageSelectors() {}

    
    public static final By WELCOME_MSG = 
        By.xpath("//p[contains(normalize-space(), 'Welcome back!')]\r\n"
        		+ ""); //*[@id="BDLoginMessageNormal"]/p[1]
}


