package org.majimena.test.serenity.jbehave.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static java.util.stream.Collectors.toList;

/**
 * Created by akiba on 6/14/15.
 */
public class Spinners {

    public static ExpectedCondition<Boolean> noSpinnerToBeVisible() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElements(By.cssSelector(".spinner-small"))
                        .stream()
                        .filter(this::isVisible)
                        .collect(toList())
                        .isEmpty();
            }

            private boolean isVisible(WebElement element) {
                try {
                    return !element.isDisplayed();
                } catch (Exception e) {
                    return true;
                }
            }
        };
    }
}
