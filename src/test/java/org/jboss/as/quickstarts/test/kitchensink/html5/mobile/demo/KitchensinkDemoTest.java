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

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * A test skeleton which uses the Arquillian controller as a test controller.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
@RunWith(Arquillian.class)
public abstract class KitchensinkDemoTest {

	private static final String OPENSHIFT_URL = "http://kitchensinkhtml5-aemmanou.rhcloud.com/";
	
    /**
     * The browser instance.
     */
    @Drone
    protected WebDriver driver;

    /**
     * Initializes the page url.
     */
    public void initializePageUrl() {
        try {
            driver.get(OPENSHIFT_URL);
        } catch (final Exception ignore) {
        }
    }

    /**
     * Navigates to page.
     */
    public void navigateToURL(String page) {
        try {
            driver.get((new StringBuilder()).append(OPENSHIFT_URL).append(page).toString());
        } catch (final Exception ignore) {
        }
    }

    /**
     * The abstract method whose implementation defines which page will be initialized in the initializePageUrl method.
     * 
     * @return A string which specifies the path.
     */
    protected abstract String getPagePath();
}
