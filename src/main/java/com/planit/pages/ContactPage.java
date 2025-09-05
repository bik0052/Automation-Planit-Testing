package com.planit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage {

    private final By forename = By.id("forename");
    private final By email = By.id("email");
    private final By message = By.id("message");
    private final By submit = By.linkText("Submit");

    // Error labels (commonly used ids on Jupiter site)
    private final By forenameErr = By.id("forename-err");
    private final By emailErr = By.id("email-err");
    private final By messageErr = By.id("message-err");

    // Success banner (alert-success inside div content)
    private final By successAlert = By.cssSelector(".alert-success, div.alert-success");

    public ContactPage(WebDriver driver){ super(driver); }

    public void submitEmpty() { click(submit); }

    public boolean isErrorVisibleForename(){ return isVisible(forenameErr); }
    public boolean isErrorVisibleEmail(){ return isVisible(emailErr); }
    public boolean isErrorVisibleMessage(){ return isVisible(messageErr); }

    public ContactPage fillMandatory(String name, String mail, String msg){
        type(forename, name);
        type(email, mail);
        type(message, msg);
        return this;
    }

    public void submit(){ click(submit); }

    public boolean isSuccessShown(){ return isVisible(successAlert); }
}
