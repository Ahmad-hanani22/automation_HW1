package com.itg.training.selectors;

import org.openqa.selenium.By;

public final class HomePageSelectors {
    private HomePageSelectors() {}

    public static final By LOGO = By.cssSelector("svg#Layer_1");
    public static final By ACCOUNT_LINK = By.cssSelector("a[title='Account'] span");
    public static final By MY_ACCOUNT_LINK = By.xpath("//a[contains(.,'Account')]");
}
