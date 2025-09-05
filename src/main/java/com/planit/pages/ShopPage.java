package com.planit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopPage extends BasePage {
    public ShopPage(WebDriver driver){ super(driver); }

    private By buyButtonFor(String product){
        // card with product title, then 'Buy' button
        String xpath = "//h4[text()='"+product+"']/following::a[contains(.,'Buy')][1]";
        return By.xpath(xpath);
    }

    public void buy(String product, int times){
        for(int i=0;i<times;i++){ click(buyButtonFor(product)); }
    }

    public CartPage goToCart(){ clickLink("Cart"); return new CartPage(driver); }
}
