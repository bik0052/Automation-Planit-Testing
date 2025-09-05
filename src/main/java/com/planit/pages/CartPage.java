package com.planit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver){ super(driver); }

    private By rowFor(String product){
        String xpath = "//table//tr[.//td[contains(.,'"+product+"')]]";
        return By.xpath(xpath);
    }

    public double getUnitPrice(String product){
        WebElement row = waitVisible(rowFor(product));
        String priceText = row.findElement(By.cssSelector("td:nth-child(2)")).getText().replace("$","").trim();
        return Double.parseDouble(priceText);
    }

    public int getQty(String product){
        WebElement row = waitVisible(rowFor(product));
        String val = row.findElement(By.cssSelector("input")).getAttribute("value");
        return Integer.parseInt(val);
    }

    public double getSubtotal(String product){
        WebElement row = waitVisible(rowFor(product));
        String subText = row.findElement(By.cssSelector("td:nth-child(4)")).getText().replace("$","").trim();
        return Double.parseDouble(subText);
    }

    public double getTotal(){
        String totalText = textOf(By.id("total")); // fallback if present
        if (totalText.startsWith("$")) totalText = totalText.substring(1);
        try { return Double.parseDouble(totalText); }
        catch(Exception e){
            // Fallback: last row strong element
            String t = textOf(By.xpath("(//strong[contains(.,'Total')]/../following-sibling::td)[last()]")).replace("$","").trim();
            return Double.parseDouble(t);
        }
    }
}
