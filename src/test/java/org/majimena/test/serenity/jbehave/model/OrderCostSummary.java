package org.majimena.test.serenity.jbehave.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by akiba on 6/14/15.
 */
@AllArgsConstructor
public class OrderCostSummary {

    @Getter
    private final String name;

    @Getter
    private final double itemTotal;
    
    @Getter
    private final double shipping;

    @Getter
    private final double totalCost;
}
