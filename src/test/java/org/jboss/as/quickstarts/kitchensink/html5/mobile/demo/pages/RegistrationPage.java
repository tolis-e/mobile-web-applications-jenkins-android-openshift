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

package org.jboss.as.quickstarts.kitchensink.html5.mobile.demo.pages;

import static org.jboss.arquillian.graphene.Graphene.element;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.enricher.findby.ByJQuery;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.jboss.as.quickstarts.kitchensink.html5.mobile.demo.dto.NameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Represents the registration page.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class RegistrationPage extends KitchensinkDemoPage {

    /**
     * The id attribute's value for the name field.
     */
    private static final String nameFieldId = "name";

    /**
     * The id attribute's value for the email field.
     */
    private static final String emailFieldId = "email";

    /**
     * The id attribute's value for the phone number field.
     */
    private static final String phoneNumberFieldId = "phoneNumber";

    /**
     * The id attribute's value for the cancel field.
     */
    private static final String cancelFieldId = "cancel";

    /**
     * The id attribute's value for the submit field.
     */
    private static final String submitFieldId = "register";

    /**
     * The class value for the success message.
     */
    private static final String successMessageClassName = "success";

    /**
     * Error message which appears in registration when the email is already used.
     */
    private static final String emailTakenErrorMessage = "Email taken";

    /**
     * Error message which appears in registration when the name is empty or invalid.
     */
    private static final String invalidNameErrorMessage = "1-25 letters and spaces";

    /**
     * Error message which appears in registration when the email is empty.
     */
    private static final String emptyEmailErrorMessage = "may not be empty";

    /**
     * Error message which appears in registration when the email is invalid.
     */
    private static final String invalidEmailErrorMessage = "Invalid format";

    /**
     * Error message which appears in registration when the phone number is empty.
     */
    private static final String emptyPhoneErrorMessage = "Not valid";

    /**
     * Error message which appears in registration when the phone number is empty or invalid.
     */
    private static final String invalidPhoneErrorMessage = "10-12 Numbers";

    /**
     * Locator for the form.
     */
    @FindBy(id = "reg")
    private WebElement form;

    /**
     * Locator for the form messages.
     */
    @FindBy(id = "formMsgs")
    private WebElement formMsg;

    /**
     * Pattern to locate the parent div for each input field.
     */
    private static final String inputFieldParentPattern = "div:has(input[id=\"{0}\"])";

    /**
     * Invalid message class name.
     */
    private static final String invalidMsg = "span.invalid";

    /**
     * Waits until the car registration page is loaded. The AndroidWeb
     */
    @Override
    public void waitUntilPageIsLoaded() {
        super.waitUntilPageIsLoaded();
        // wait until the form is visible
        waitUntilFormIsVisible();
    }

    /**
     * Waits until the form is visible.
     */
    public void waitUntilFormIsVisible() {
        Graphene.waitModel().withTimeout(40, TimeUnit.SECONDS).until(element(form).isVisible());
    }

    /**
     * Fills the form dynamically.
     * 
     * @param nameValuePairs The {@link NameValuePair} array which contains the field name and the field value.
     */
    public void fillForm(NameValuePair[] nameValuePairs) {
        if (!ArrayUtils.isEmpty(nameValuePairs)) {
            for (NameValuePair p : nameValuePairs) {
                if (p != null) {
                    final WebElement field = form.findElement(By.id(p.getName()));
                    clearField(field);
                    fillField(field, p.getValue());
                }
            }
        }
    }

    /**
     * Submits the form by pressing the submit button.
     * 
     * @param id The submit's button id.
     */
    public void submitFormByButton(String id) {
        final WebElement submitButton = form.findElement(By.id(id));
        Graphene.waitModel().withTimeout(40, TimeUnit.SECONDS).until(element(submitButton).isVisible());
        submitButton.click();
    }

    /**
     * Cancels the form submission.
     * 
     * @param id The cancel's button id.
     */
    public void cancel(String id) {
        final WebElement cancelButton = form.findElement(By.id(id));
        Graphene.waitModel().withTimeout(40, TimeUnit.SECONDS).until(element(cancelButton).isVisible());
        cancelButton.click();
    }

    /**
     * Finds a WebElement given the id.
     * 
     * @param id The element id.
     * @return a {@link WebElement}
     */
    public WebElement getWebElementById(String id) {
        return form.findElement(By.id(id));
    }

    /**
     * Fills the form and submits it.
     * 
     * @param nameValuePairs The {@link NameValuePair} array which contains the field name and the field value.
     * @param submitButtonId the submit's button id.
     */
    public void fillFormAndSubmit(NameValuePair[] nameValuePairs, String submitButtonId) {
        fillForm(nameValuePairs);
        submitFormByButton(submitButtonId);
    }

    /**
     * Clears a field.
     * 
     * @param field The {@link WebElement} to be cleared.
     */
    public void clearField(WebElement field) {
        field.clear();
    }

    /**
     * Fills a field with a value.
     * 
     * @param field The {@link WebElement} to be filled.
     * @param keys The value.
     */
    public void fillField(WebElement field, String keys) {
        field.sendKeys(keys);
    }

    /**
     * Waits until the success message is visible.
     * 
     * @param className The success message span className.
     */
    public void waitUntilSuccessMessageIsVisible(String className) {
        Graphene.waitModel().withTimeout(40, TimeUnit.SECONDS).until(element(formMsg).isVisible());
        final WebElement successMsg = formMsg.findElement(By.className(className));
        Graphene.waitModel().withTimeout(40, TimeUnit.SECONDS).until(element(successMsg).isVisible());
    }

    /**
     * Finds the invalid message which corresponds to an input field.
     * 
     * @param inputId The input field id.
     * @return The error message or empty String.
     */
    public String getInvalidMessage(String inputId) {
        final WebElement divContainer = form.findElement(ByJQuery.jquerySelector(MessageFormat.format(inputFieldParentPattern,
                inputId)));
        final WebElement invalidMessage = divContainer.findElement(ByJQuery.jquerySelector(invalidMsg));

        Graphene.waitModel().withTimeout(40, TimeUnit.SECONDS).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver notUsed) {
                return invalidMessage != null && !StringUtils.isEmpty(invalidMessage.getText());
            }
        });

        return (invalidMessage != null) ? invalidMessage.getText() : "";
    }

    /**
     * Performs a member registration.
     * 
     * @param name The member's name.
     * @param email The member's email.
     * @param phoneNumber The member's phone number.
     */
    public void registerMember(String name, String email, String phoneNumber) {
        final NameValuePair[] nameValuePairs = { new NameValuePair(nameFieldId, name), new NameValuePair(emailFieldId, email),
                new NameValuePair(phoneNumberFieldId, phoneNumber) };
        fillFormAndSubmit(nameValuePairs, submitFieldId);
    }

    /**
     * Waits for the success message to appear.
     */
    public void waitUntilSuccessMessageIsVisible() {
        waitUntilSuccessMessageIsVisible(successMessageClassName);
    }

    /**
     * Cancels the registration procedure.
     */
    public void cancelRegistration() {
        cancel(cancelFieldId);
    }

    /**
     * Gets the email invalid message.
     * 
     * @return email invalid message
     */
    public String getEmailInvalidMessage() {
        return getInvalidMessage(emailFieldId);
    }

    /**
     * Gets the name invalid message.
     * 
     * @return name invalid message
     */
    public String getNameInvalidMessage() {
        return getInvalidMessage(nameFieldId);
    }

    /**
     * Gets the phone invalid message.
     * 
     * @return phone invalid message
     */
    public String getPhoneInvalidMessage() {
        return getInvalidMessage(phoneNumberFieldId);
    }

    /**
     * Gets the email taken error message.
     * 
     * @return email taken error message
     */
    public String getEmailTakenErrorMessage() {
        return emailTakenErrorMessage;
    }

    /**
     * Gets the invalid name error message.
     * 
     * @return invalid name error message
     */
    public String getInvalidNameErrorMessage() {
        return invalidNameErrorMessage;
    }

    /**
     * Gets the empty email error message.
     * 
     * @return empty email error message
     */
    public String getEmptyEmailErrorMessage() {
        return emptyEmailErrorMessage;
    }

    /**
     * Gets the invalid email error message.
     * 
     * @return invalid email error message
     */
    public String getInvalidEmailErrorMessage() {
        return invalidEmailErrorMessage;
    }

    /**
     * Gets the empty phone error message.
     * 
     * @return empty phone error message
     */
    public String getEmptyPhoneErrorMessage() {
        return emptyPhoneErrorMessage;
    }

    /**
     * Gets the invalid phone error message.
     * 
     * @return invalid phone error message
     */
    public String getInvalidPhoneErrorMessage() {
        return invalidPhoneErrorMessage;
    }
    
}
