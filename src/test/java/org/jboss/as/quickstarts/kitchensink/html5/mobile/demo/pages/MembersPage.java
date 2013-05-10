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

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.enricher.findby.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Represents the members list page.
 * 
 * @author <a href="mailto:aemmanou@redhat.com">Tolis Emmanouilidis</a>
 * 
 */
public class MembersPage extends KitchensinkDemoPage {

    /**
     * Locator for members
     */
    @FindBy(id = "members")
    private WebElement members;

    /**
     * The head text which indicates the id column.
     */
    // /private static final String columnHeadTextId = "Id";

    /**
     * The head text which indicates the name column.
     */
    private static final String columnHeadTextName = "Name";

    /**
     * The head text which indicates the email column.
     */
    private static final String columnHeadTextEmail = "Email";

    /**
     * The head text which indicates the phone column.
     */
    private static final String columnHeadTextPhone = "Phone #";

    /**
     * The class name of the header inside a column.
     */
    private static final String headClass = "head";

    /**
     * The class name of the data inside a column.
     */
    private static final String dataClass = "data";

    /**
     * The class name for the columns inside a row.
     */
    private static final String columnClassName = "col";

    /**
     * Locator for rows.
     */
    @FindBy(className = "row")
    List<WebElement> rowList;

    /**
     * Returns the row list.
     * 
     * @return a {@List} of {@link WebElement]
     */
    public List<WebElement> getRows() {
        return rowList;
    }

    /**
     * Grabs the specified column inside a specified row.
     * 
     * @param rowNo The row number.
     * @param colNo The column number.
     * @return a {@link WebElement}
     */
    public WebElement getColumnInRow(int rowNo, int colNo) {
        if (!CollectionUtils.isEmpty(rowList) && rowList.size() >= rowNo) {
            final List<WebElement> columnsInRow = getColumnsInRow(rowNo);
            if (!CollectionUtils.isEmpty(columnsInRow) && columnsInRow.size() >= colNo) {
                return columnsInRow.get(colNo);
            }
            return null;
        }
        return null;
    }

    /**
     * Find the columns inside a row.
     * 
     * @param rowNo The row number.
     * @return a {@link List} of {@link WebElement}
     */
    public List<WebElement> getColumnsInRow(int rowNo) {
        return (!CollectionUtils.isEmpty(rowList) && rowList.size() >= rowNo) ? rowList.get(rowNo).findElements(
                By.className(columnClassName)) : null;
    }

    /**
     * Waits until the car registration page is loaded. The AndroidWeb
     */
    @Override
    public void waitUntilPageIsLoaded() {
        super.waitUntilPageIsLoaded();
        // wait until the members sections is visible
        Graphene.waitModel().withTimeout(10, TimeUnit.SECONDS).until(element(members).isVisible());
    }

    /**
     * Finds the rows count.
     * 
     * @return the number of rows.
     */
    public int getMembersCount() {
        return !CollectionUtils.isEmpty(getRows()) ? getRows().size() : 0;
    }

    /**
     * Finds whether a member exists in the list.
     * 
     * @param name The name.
     * @param email The email.
     * @param phone The phone.
     * @return true or false
     */
    public boolean memberExists(String name, String email, String phone) {
        final List<WebElement> membersList = getRows();
        if (!CollectionUtils.isEmpty(membersList)) {
            for (WebElement row : membersList) {
                if (row != null) {
                    List<WebElement> columns = row.findElements(By.className(columnClassName));
                    int matchesFound = 0;
                    for (int j = 0; j < columns.size(); j++) {

                        if (matchesFound == 3)
                            return true;

                        final WebElement head = columns.get(j).findElement(By.className(headClass));
                        final WebElement data = columns.get(j).findElement(By.className(dataClass));

                        if ((columnHeadTextName.equals(head.getText()) && name.equalsIgnoreCase(data.getText()))
                                || (columnHeadTextEmail.equals(head.getText()) && email.equalsIgnoreCase(data.getText()))
                                || (columnHeadTextPhone.equals(head.getText()) && phone.equalsIgnoreCase(data.getText()))) {
                            matchesFound++;
                        }
                    }
                }
            }
        }
        return false;
    }
}