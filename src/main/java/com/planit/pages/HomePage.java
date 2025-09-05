package com.planit.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){ super(driver); }
    public ContactPage goToContact(){ clickLink("Contact"); return new ContactPage(driver); }
    public ShopPage goToShop(){ clickLink("Shop"); return new ShopPage(driver); }
}
