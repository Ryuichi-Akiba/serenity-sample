package org.majimena.test.serenity.jbehave;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.assertj.core.data.Offset;
import org.hamcrest.Matcher;
import org.majimena.test.serenity.jbehave.model.ListingItem;
import org.majimena.test.serenity.jbehave.model.OrderCostSummary;
import org.majimena.test.serenity.jbehave.model.SessionVariables;
import org.majimena.test.serenity.jbehave.pages.CartPage;
import org.majimena.test.serenity.jbehave.pages.HomePage;
import org.majimena.test.serenity.jbehave.pages.ItemDetailsPage;
import org.majimena.test.serenity.jbehave.pages.SearchResultsPage;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by akiba on 6/14/15.
 */
// tag::header[]
public class BuyerSteps {
// end::header[]
// tag::searchByKeywordSteps[]

    HomePage homePage;

    SearchResultsPage searchResultPage;

    @Step
    public void opens_etsy_home_page() {
        homePage.open();
    }

    @Step
    public void searches_for_items_containing(String keywords) {
        homePage.searchFor(keywords);
    }

    @Step
    public void should_see_items_related_to(String keywords) {
        List<String> resultTitles = searchResultPage.getResultTitles();
        resultTitles.stream().forEach(title -> assertThat(title.contains(keywords)));
    }

    // end::searchByKeywordStep[]
// tag::filterByType[]
    @Step
    public void filters_results_by_type(String type) {
        searchResultPage.filterByType(type);
    }

    public int get_matching_item_count() {
        return searchResultPage.getItemCount();
    }

    @Step
    public void should_see_item_count(Matcher<Integer> itemCountMatcher) {
        itemCountMatcher.matches(searchResultPage.getItemCount());
    }
// end::filterByType[]

    @Step
    public void selects_item_number(int number) {
        ListingItem selectedItem = searchResultPage.selectItem(number);

        Serenity.setSessionVariable(SessionVariables.SELECTED_LISTING).to(selectedItem);
    }

    @Step
    public void should_see_matching_details(String searchTerm) {
        String itemName = detailsPage.getItemName();
        assertThat(itemName.toLowerCase()).contains(searchTerm);
    }

    @Step
    public void should_see_items_of_type(String type) {
        Optional<String> selectedType = searchResultPage.getSelectedType();
        assertThat(selectedType.isPresent()).describedAs("No item type was selected").isTrue();
        assertThat(selectedType.get()).isEqualTo(type);
    }

    // tag::shoppingCartSteps[]

    ItemDetailsPage detailsPage;
    CartPage cartPage;

    @Step
    public void selects_any_product_variations() {
        detailsPage.getProductVariationIds()
                .stream()
                .forEach(id -> detailsPage.selectVariation(id, 2));
    }

    @Step
    public void adds_current_item_to_shopping_cart() {
        detailsPage.addToCart();
    }

    @Step
    public void should_see_item_in_cart(ListingItem selectedItem) {
        assertThat(
                cartPage.getOrderCostSummaries()
                        .stream()
                        .anyMatch(order -> order.getName().equals(selectedItem.getName()))
        ).isTrue();
    }

    @Step
    public void should_see_total_including_shipping_for(ListingItem selectedItem) {
        OrderCostSummary orderCostSummary = cartPage.getOrderCostSummaryFor(selectedItem).get();

        double itemTotal = orderCostSummary.getItemTotal();
        double shipping = orderCostSummary.getShipping();
        double totalCost = orderCostSummary.getTotalCost();

        assertThat(itemTotal).isEqualTo(selectedItem.getPrice());
        assertThat(shipping).isGreaterThan(0.0);
        assertThat(totalCost).isCloseTo(itemTotal + shipping, Offset.offset(0.001));
    }

    @Step
    public void filters_results_to_local_region() {
        searchResultPage.filterByLocalRegion();
    }
    // end::shoppingCartSteps[]

// tag::tail[]
}
// end::tail[]