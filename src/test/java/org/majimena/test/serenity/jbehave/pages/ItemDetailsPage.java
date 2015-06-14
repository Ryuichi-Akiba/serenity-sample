package org.majimena.test.serenity.jbehave.pages;

import lombok.Getter;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.majimena.test.serenity.jbehave.model.Spinners.noSpinnerToBeVisible;

/**
 * Created by akiba on 6/14/15.
 */
public class ItemDetailsPage extends PageObject {

    @FindBys({
            @FindBy(id = "listing-page-cart"),
            @FindBy(tagName = "h1")
    })
    WebElement itemName;

    public String getItemName() {
        return itemName.getText();
    }

    public String getItemDescription() {
        return $("#description-text").getText();
    }

    public void addToCart() {
        withAction().moveToElement($("#item-tabs")).perform();
        $(".buy-button button").click();
    }

    public List<String> getProductVariationIds() {
        return findAll(".variation")
                .stream()
                .map(elt -> elt.getAttribute("id"))
                .filter(id -> !id.isEmpty())
                .collect(toList());
    }

    public void selectVariation(String variationId, int optionIndex) {
        find(By.id(variationId)).selectByIndex(optionIndex);
        if (spinnerIsVisible()) {
            waitFor(noSpinnerToBeVisible());
        }

    }

    private boolean spinnerIsVisible() {
        return containsElements(".spinner-small");
    }
}
