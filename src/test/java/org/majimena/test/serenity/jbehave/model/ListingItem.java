package org.majimena.test.serenity.jbehave.model;

import lombok.*;

/**
 * Created by akiba on 6/14/15.
 */
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ListingItem {

    @Getter
    private final String name;

    @Getter
    private final double price;

}
