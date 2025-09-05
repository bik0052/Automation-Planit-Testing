package com.planit.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static ThreadLocal<WebDriver> tl = new ThreadLocal<>();

    public static WebDriver createDriver() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless=new");
        options.addArguments("--window-size=1440,900","--disable-gpu","--no-sandbox","--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        tl.set(driver);
        driver.get("http://jupiter.cloud.planittesting.com");
        return driver;
    }

    public static WebDriver getDriver(){ return tl.get(); }
}
