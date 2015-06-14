package org.majimena.test.serenity.jbehave.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.majimena.test.serenity.jbehave.model.ListingItem;
import org.majimena.test.serenity.jbehave.model.OrderCostSummary;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by akiba on 6/14/15.
 */
public class CartPage extends PageObject {

    public List<OrderCostSummary> getOrderCostSummaries() {
        return findAll(".order-wrapper")
                .stream()
                .map(CartPage::convertToOrderCostSummary)
                .collect(Collectors.toList());
    }

    public Optional<OrderCostSummary> getOrderCostSummaryFor(ListingItem selectedItem) {
        return getOrderCostSummaries()
                .stream()
                .filter(item -> item.getName().equals(selectedItem.getName()))
                .findFirst();
    }

    public static OrderCostSummary convertToOrderCostSummary(WebElementFacade summaryElement) {
        String name = summaryElement.find(By.tagName("h3")).getText();
        double itemTotal = Double.parseDouble(summaryElement.findBy(".item-total .currency-value").getText());
        double shipping = summaryElement.containsElements(".shipping .currency-value") ? Double.parseDouble(summaryElement.findBy(".shipping .currency-value").getText()) : 0.0;
        double grandTotal = summaryElement.containsElements(".grand-total .currency-value") ? Double.parseDouble(summaryElement.findBy(".grand-total .currency-value").getText()) : 0.0;

        return new OrderCostSummary(name, itemTotal, shipping, grandTotal);
    }

}
