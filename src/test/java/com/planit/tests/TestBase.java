package com.planit.tests;

import com.planit.pages.*;
import com.planit.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestBase {
    protected WebDriver driver;
    protected HomePage home;

    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.createDriver();
        home = new HomePage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
