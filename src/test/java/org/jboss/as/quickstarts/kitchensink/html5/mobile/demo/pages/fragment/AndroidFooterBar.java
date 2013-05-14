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

package org.jboss.as.quickstarts.kitchensink.html5.mobile.demo.pages.fragment;

import static org.jboss.arquillian.graphene.Graphene.element;

import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.openqa.selenium.WebElement;

/**
 * Represents the android footer bar.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class AndroidFooterBar {

    /**
     * Locator for footer right.
     */
    @FindBy(className = "footer_right")
    private WebElement footerRight;

    /**
     * Locator for footer left.
     */
    @FindBy(className = "footer_left")
    private WebElement footerLeft;

    /**
     * Locator for the add member button.
     */
    @FindBy(jquery = "a[href=\"#register-art\"]")
    private WebElement addMemberButton;

    /**
     * Locator for the home button.
     */
    @FindBy(jquery = "a[href=\"#intro-art\"]")
    private WebElement homeButton;

    /**
     * Locator for the list button.
     */
    @FindBy(jquery = "a[href=\"#member-art\"]")
    private WebElement listButton;

    /**
     * Clicks the add member button.
     */
    public void clickAddMemberButton() {
        addMemberButton.click();
    }

    /**
     * Clicks the list button.
     */
    public void clickListButton() {
        listButton.click();
    }

    /**
     * Clicks the home button.
     */
    public void clicHomeButton() {
        homeButton.click();
    }

    /**
     * Wait until the entire footer is visible.
     */
    public void waitUntilIsVisible() {
        Graphene.waitModel().withTimeout(40, TimeUnit.SECONDS).until(element(footerRight).isVisible());
        Graphene.waitModel().withTimeout(40, TimeUnit.SECONDS).until(element(footerLeft).isVisible());
    }
}
