package com.planit.tests;

import com.planit.pages.CartPage;
import com.planit.pages.ShopPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTotalsTest extends TestBase {

    @Test
    public void verifyPricesSubtotalsAndTotal(){
        ShopPage shop = home.goToShop();
        shop.buy("Stuffed Frog", 2);
        shop.buy("Fluffy Bunny", 5);
        shop.buy("Valentine Bear", 3);

        CartPage cart = shop.goToCart();

        double frogPrice = cart.getUnitPrice("Stuffed Frog");
        double bunnyPrice = cart.getUnitPrice("Fluffy Bunny");
        double bearPrice = cart.getUnitPrice("Valentine Bear");

        int frogQty = cart.getQty("Stuffed Frog");
        int bunnyQty = cart.getQty("Fluffy Bunny");
        int bearQty = cart.getQty("Valentine Bear");

        double frogSub = cart.getSubtotal("Stuffed Frog");
        double bunnySub = cart.getSubtotal("Fluffy Bunny");
        double bearSub = cart.getSubtotal("Valentine Bear");

        Assert.assertEquals(frogSub, frogPrice * frogQty, 0.01, "Frog subtotal should equal price*qty");
        Assert.assertEquals(bunnySub, bunnyPrice * bunnyQty, 0.01, "Bunny subtotal should equal price*qty");
        Assert.assertEquals(bearSub, bearPrice * bearQty, 0.01, "Bear subtotal should equal price*qty");

        double expectedTotal = frogSub + bunnySub + bearSub;
        double total = cart.getTotal();
        Assert.assertEquals(total, expectedTotal, 0.01, "Total should equal sum of subtotals");
    }
}
