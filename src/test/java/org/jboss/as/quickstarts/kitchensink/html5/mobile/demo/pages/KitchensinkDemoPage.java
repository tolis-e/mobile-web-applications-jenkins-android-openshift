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

import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.openqa.selenium.WebElement;

/**
 * A common page structure for all the pages of the specific example.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class KitchensinkDemoPage {

    /**
     * Locator for the container div.
     */
    @FindBy(id = "container")
    private WebElement content;

    /**
     * Waits until the page is loaded.
     */
    public void waitUntilPageIsLoaded() {
        Graphene.waitModel().withTimeout(40, TimeUnit.SECONDS).until(element(content).isVisible());
    }
}
