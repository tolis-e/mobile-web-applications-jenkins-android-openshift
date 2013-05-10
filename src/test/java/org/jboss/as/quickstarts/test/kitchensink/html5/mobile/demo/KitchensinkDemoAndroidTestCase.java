/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.as.quickstarts.test.kitchensink.html5.mobile.demo;

import org.jboss.arquillian.graphene.spi.annotations.Page;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.as.quickstarts.kitchensink.html5.mobile.demo.pages.AndroidPage;
import org.jboss.as.quickstarts.kitchensink.html5.mobile.demo.pages.MembersPage;
import org.jboss.as.quickstarts.kitchensink.html5.mobile.demo.pages.RegistrationPage;
import org.junit.Assert;
import org.junit.Test;

/**
 * The class which contains the test cases / testing business logic for the kitchensink html mobile demo.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class KitchensinkDemoAndroidTestCase extends KitchensinkDemoTest {

    /**
     * Finds whether the test is performed on Android platform.
     */
    // private static final boolean isAndroid = GrapheneContext
    // .holdsInstanceOf(AndroidDriver.class);

    /**
     * Specifies the page's path for the test case.
     */
    @Override
    protected String getPagePath() {
        return "";
    }

    /**
     * Injects the registration page.
     */
    @Page
    RegistrationPage registrationPage;

    /**
     * Injects an android page.
     */
    @Page
    AndroidPage androidPage;

    /**
     * Injects the members list page.
     */
    @Page
    MembersPage membersPage;

    /**
     * Tests that the default member exist in the list.
     */
    @Test
    @InSequence(1)
    public void initialMemberListTest() {
        // initialize main page
        initializePageUrl();
        // wait until android page is loaded
        androidPage.waitUntilPageIsLoaded();
        // navigate to list page
        androidPage.navigateToListPage();
        // wait for the row list page to load
        membersPage.waitUntilPageIsLoaded();
        // assert that one member exist
        Assert.assertTrue (membersPage.getMembersCount() == 1);
        // assert that the default member exists in the list
        Assert.assertTrue (membersPage.memberExists(defaultUserName, defaultUserEmail, defaultUserPhoneNumber));
    }

    /**
     * Adds a new member and tests that it is added to the members list.
     */
    @Test
    @InSequence(2)
    public void addMemberTest() {
        // initialize main page
        initializePageUrl();
        // wait until the android page is loaded
        androidPage.waitUntilPageIsLoaded();
        // navigate to registration page
        androidPage.navigateToRegistrationPage();
        // wait for the registration page to load
        registrationPage.waitUntilPageIsLoaded();
        // register the new member
        registrationPage.registerMember(memberRegistrationName, emailRegistrationName, phoneRegistrationName);
        // wait until the success message is visible
        registrationPage.waitUntilSuccessMessageIsVisible();
        // initialize main page
        initializePageUrl();
        // wait until the android page is visible
        androidPage.waitUntilPageIsLoaded();
        // navigate to list page
        androidPage.navigateToListPage();
        // wait for the row list page to load
        membersPage.waitUntilPageIsLoaded();
        // assert that two members exist
        Assert.assertTrue (membersPage.getMembersCount() == 2);
        // assert that the previously registered member exists in the list
        Assert.assertTrue (membersPage.memberExists(memberRegistrationName, emailRegistrationName, phoneRegistrationName));
    }

    /**
     * Tests that the email is unique in registration and a new member cannot register with an e-mail which already exists.
     */
    @Test
    @InSequence(3)
    public void addMemberWithExistingEmailTest() {
        // initialize main page
        initializePageUrl();
        // wait until the android page is visible
        androidPage.waitUntilPageIsLoaded();
        // navigate to registration page
        androidPage.navigateToRegistrationPage();
        // wait for the registration page to load
        registrationPage.waitUntilPageIsLoaded();
        // register the new member
        registrationPage.registerMember(memberRegistrationName, defaultUserEmail, phoneRegistrationName);
        // assert that a specific error message appears
        Assert.assertTrue (registrationPage.getEmailTakenErrorMessage().equals(registrationPage.getEmailInvalidMessage()));
    }

    /**
     * Tests that a registration cannot proceed with empty fields.
     */
    @Test
    @InSequence(4)
    public void addMemberWithEmptyFieldsTest() {
        // initialize main page
        initializePageUrl();
        // wait until the android page is visible
        androidPage.waitUntilPageIsLoaded();
        // navigate to registration page
        androidPage.navigateToRegistrationPage();
        // wait for the registration page to load
        registrationPage.waitUntilPageIsLoaded();
        // register the new member
        registrationPage.registerMember("", "", "");
        // assert that a specific error message appears
        Assert.assertTrue (registrationPage.getEmptyEmailErrorMessage().equals(registrationPage.getEmailInvalidMessage()));
        // assert that a specific error message appears
        Assert.assertTrue (registrationPage.getInvalidNameErrorMessage().equals(registrationPage.getNameInvalidMessage()));
        // assert that a specific error message appears
        Assert.assertTrue (registrationPage.getEmptyPhoneErrorMessage().equals(registrationPage.getPhoneInvalidMessage()) || registrationPage
                .getInvalidPhoneErrorMessage().equals(registrationPage.getPhoneInvalidMessage()));
    }

    /**
     * Tests that a registration cannot proceed with bad formatted fields.
     */
    @Test
    @InSequence(5)
    public void addMemberWithBadFormatFieldsTest() {
        // initialize main page
        initializePageUrl();
        // wait until the android page is loaded
        androidPage.waitUntilPageIsLoaded();
        // navigate to registration page
        androidPage.navigateToRegistrationPage();
        // wait for the registration page to load
        registrationPage.waitUntilPageIsLoaded();
        // register the new member
        registrationPage.registerMember(userNameBadFormat, userEmailBadFormat, userPhoneNumberBadFormat);
        // assert that a specific error message appears
        Assert.assertTrue (registrationPage.getInvalidEmailErrorMessage().equals(registrationPage.getEmailInvalidMessage()));
        // assert that a specific error message appears
        Assert.assertTrue (registrationPage.getInvalidNameErrorMessage().equals(registrationPage.getNameInvalidMessage()));
        // assert that a specific error message appears
        Assert.assertTrue (registrationPage.getEmptyPhoneErrorMessage().equals(registrationPage.getPhoneInvalidMessage()) || registrationPage
                .getInvalidPhoneErrorMessage().equals(registrationPage.getPhoneInvalidMessage()));
    }

    /* -- Testing data begin -- */

    /**
     * Name to be used to test the registration functionality.
     */
    private static final String memberRegistrationName = "Chris";

    /**
     * Email to be used to test the registration functionality.
     */
    private static final String emailRegistrationName = "chris@example.com";

    /**
     * Phone to be used to test the registration functionality.
     */
    private static final String phoneRegistrationName = "0123456789";

    /**
     * Default user name.
     */
    private static final String defaultUserName = "John Smith";

    /**
     * Default user email.
     */
    private static final String defaultUserEmail = "john.smith@mailinator.com";

    /**
     * Default user phone number.
     */
    private static final String defaultUserPhoneNumber = "2125551212";

    /**
     * Bad formatted user name.
     */
    private static final String userNameBadFormat = "Wrong Username With More Than twenty five letters";

    /**
     * Bad formatted user email.
     */
    private static final String userEmailBadFormat = "bad.email.format";

    /**
     * Bad formatted user phone number.
     */
    private static final String userPhoneNumberBadFormat = "bad phone format 123456";

    /* -- Testing data end -- */
}
