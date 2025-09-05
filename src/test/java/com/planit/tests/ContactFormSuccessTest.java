package com.planit.tests;

import com.planit.pages.ContactPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactFormSuccessTest extends TestBase {

    @Test(invocationCount = 5)
    public void submitContactFormSuccessfully(){
        ContactPage contact = home.goToContact();
        contact.fillMandatory("Alice", "alice@example.com", "Checking submission flow");
        contact.submit();
        Assert.assertTrue(contact.isSuccessShown(), "Success message should be visible after submission");
    }
}
