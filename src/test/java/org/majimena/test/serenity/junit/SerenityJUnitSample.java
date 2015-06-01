package org.majimena.test.serenity.junit;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.majimena.test.serenity.logic.Status;

/**
 * Created by akiba on 5/31/15.
 */
@RunWith(SerenityRunner.class)
public class SerenityJUnitSample {

    @Steps
    TravellerSteps travellerSteps;

    @Test
    @Title("Members earn Gold status after 5000 points(50000km)")
    public void earnGoldAfter5000Points() {

        // Given
        travellerSteps.a_traveller_joins_the_frequent_flyer_program();

        // When
        travellerSteps.the_traveller_flies(50000);

        // Then
        travellerSteps.traveller_should_have_a_status_of(Status.Gold);

    }

}
