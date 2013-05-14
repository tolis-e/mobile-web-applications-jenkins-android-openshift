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

package org.jboss.as.quickstarts.kitchensink.html5.mobile.demo.common;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Contains utility methods.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class KitchensinkUtilities {

    /**
     * Generates a random test email.
     * 
     * @param length The email id's length.
     * @param suffix The email's suffix.
     * 
     * @return Random email
     */
    public static final String generateRandomEmail(int length, String suffix) {
        return (new StringBuilder()).append(RandomStringUtils.random(length, true, false)).append(suffix).toString();
    }
}
