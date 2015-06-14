package org.majimena.test.serenity.jbehave.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by akiba on 6/14/15.
 */
// tag::header[]
@DefaultUrl("http://www.etsy.com")
public class HomePage extends PageObject {
// end::header[]
// tag::searchByKeyword[]

    @FindBy(css = "button[value='Search']")
    WebElement searchButton;

    public void searchFor(String keywords) {
        $("#search-query").sendKeys(keywords);
        searchButton.click();
    }
// end::searchByKeyword[]
// tag::tail[]
}
// end::tail[]