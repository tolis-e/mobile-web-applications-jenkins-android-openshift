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

import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.jboss.as.quickstarts.kitchensink.html5.mobile.demo.pages.fragment.AndroidFooterBar;

/**
 * Represents an Android Page. The footerRight element is used for ensuring that the page is loaded. The reason for using the
 * footerRight element and not the addMember is that an AndroidWebElement cannot be cast to WrapsElement (selenium bug).
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class AndroidPage extends KitchensinkDemoPage {

    @FindBy(className = "footer")
    private AndroidFooterBar androidFooterBar;

    /**
     * Waits until the page is visible.
     */
    @Override
    public void waitUntilPageIsLoaded() {
        super.waitUntilPageIsLoaded();
        // wait until the entire footer is visible
        androidFooterBar.waitUntilIsVisible();
    }

    /**
     * Navigates to the registration page.
     */
    public void navigateToRegistrationPage() {
        androidFooterBar.clickAddMemberButton();
    }

    /**
     * Navigates to the list page.
     */
    public void navigateToListPage() {
        androidFooterBar.clickListButton();
    }

    /**
     * Navigates to the home page.
     */
    public void navigateToHomePage() {
        androidFooterBar.clicHomeButton();
    }
}
