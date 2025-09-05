package com.planit.tests;

import com.planit.pages.ContactPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactFormErrorsTest extends TestBase {

    @Test
    public void verifyErrorsAppearAndDisappear(){
        ContactPage contact = home.goToContact();
        contact.submitEmpty();
        Assert.assertTrue(contact.isErrorVisibleForename(), "Forename error should be visible");
        Assert.assertTrue(contact.isErrorVisibleEmail(), "Email error should be visible");
        Assert.assertTrue(contact.isErrorVisibleMessage(), "Message error should be visible");

        contact.fillMandatory("John", "john@example.com", "Nice toys!");
        // After typing, errors should not be visible
        Assert.assertFalse(contact.isErrorVisibleForename(), "Forename error should be gone");
        Assert.assertFalse(contact.isErrorVisibleEmail(), "Email error should be gone");
        Assert.assertFalse(contact.isErrorVisibleMessage(), "Message error should be gone");
    }
}
